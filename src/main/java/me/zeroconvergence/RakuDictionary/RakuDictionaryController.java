package me.zeroconvergence.RakuDictionary;

import me.zeroconvergence.RakuDictionary.entity.WordEntity;
import me.zeroconvergence.RakuDictionary.repository.WordRepository;
import me.zeroconvergence.RakuDictionary.service.WordService;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RakuDictionaryController {
    private final WordService wordService;
    private final WordRepository wordRepository;

    public RakuDictionaryController(WordService wordService, WordRepository wordRepository) {
        this.wordService = wordService;
        this.wordRepository = wordRepository;
    }

    @GetMapping("/words/categories")
    public List<WordCategory> listCategories() {
        return wordService.listCategories();
    }

    @GetMapping("/words")
    public List<WordEntity> listWords() {
        return wordService.listWords();
    }

    @GetMapping("/words/{id}")
    public List<WordEntity> listWord(@PathVariable("id") Integer id) {
        return wordService.listWord(id);
    }

    @PatchMapping("/words/{id}")
    public WordEntity updateWord(@PathVariable("id") Integer id, @RequestBody WordEntity word) {
        return wordService.updateWord(id, word);
    }

    @DeleteMapping("/words/{id}")
    public void removeWord(@PathVariable("id") Integer id) {
        wordService.removeWord(id);
    }

    @PostMapping("/words")
    public ResponseEntity<WordEntity> addWord(@RequestBody WordEntity word) throws IllegalArgumentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(wordService.addWord(word));
    }

    @GetMapping("/words/filter")
    public List<WordEntity> filter(@RequestParam(required = false) String kanji,
                                   @RequestParam(required = false) String reading,
                                   @RequestParam(required = false) WordCategory category,
                                   @RequestParam(required = false) JLPTLevel level) {
        return wordService.findWords(kanji, reading, category, level);
    }
}
