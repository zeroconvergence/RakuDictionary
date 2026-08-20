package me.zeroconvergence.RakuDictionary;

import me.zeroconvergence.RakuDictionary.entity.VerbEntity;
import me.zeroconvergence.RakuDictionary.entity.WordEntity;

import me.zeroconvergence.RakuDictionary.service.VerbService;
import me.zeroconvergence.RakuDictionary.service.WordService;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.VerbGroup;
import me.zeroconvergence.RakuDictionary.types.VerbTransitivity;
import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class RakuDictionaryController {
    private final WordService wordService;
    private final VerbService verbService;

    @GetMapping("/words/categories")
    public List<WordCategory> listCategories() {
        return wordService.listCategories();
    }

    //-------------------------------------- WORDS --------------------------------------//
    public RakuDictionaryController(WordService wordService, VerbService verbService) {
        this.wordService = wordService;
        this.verbService = verbService;
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
                                   @RequestParam(required = false) String meaning,
                                   @RequestParam(required = false) WordCategory category,
                                   @RequestParam(required = false) JLPTLevel level) {
        return wordService.findWords(kanji, reading, meaning, category, level);
    }

    @GetMapping("/words/random")
    public WordEntity randomWord(@ModelAttribute("word.kanji") WordEntity word) {
        return wordService.randomWord();
    }

    //-------------------------------------- VERBS --------------------------------------//
    @PostMapping("/verbs")
    public ResponseEntity<VerbEntity> addVerb(@RequestBody VerbEntity verb) throws IllegalArgumentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(verbService.addVerb(verb));
    }

    @GetMapping("/verbs")
    public List<VerbEntity> listVerbs() {
        return verbService.listVerbs();
    }

    @GetMapping("/verbs/{id}")
    public List<VerbEntity> listVerb(@PathVariable("id") Integer id) {
        return verbService.listVerb(id);
    }

    @PatchMapping("/verbs/{id}")
    public VerbEntity updateVerb(@PathVariable("id") Integer id, @RequestBody VerbEntity verb) {
        return verbService.updateVerb(id, verb);
    }

    @DeleteMapping("/verbs/{id}")
    public void removeVerb(@PathVariable("id") Integer id) {
        verbService.removeVerb(id);
    }

    @GetMapping("/verbs/filter")
    public List<VerbEntity> filter(@RequestParam(required = false) String dictionaryForm,
                                   @RequestParam(required = false) String reading,
                                   @RequestParam(required = false) String meaning,
                                   @RequestParam(required = false) WordCategory categories,
                                   @RequestParam(required = false) VerbGroup verbGroup,
                                   @RequestParam(required = false) VerbTransitivity verbTransitivity,
                                   @RequestParam(required = false) JLPTLevel level) {
        return verbService.findVerbs(dictionaryForm, reading, meaning, categories, verbGroup, verbTransitivity, level);
    }

    @GetMapping("/verbs/random")
    public VerbEntity randomVerb() {
        return verbService.randomVerb();
    }
}
