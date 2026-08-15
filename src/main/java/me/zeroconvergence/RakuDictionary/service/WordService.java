package me.zeroconvergence.RakuDictionary.service;

import me.zeroconvergence.RakuDictionary.entity.WordEntity;
import me.zeroconvergence.RakuDictionary.repository.WordRepository;
import me.zeroconvergence.RakuDictionary.specification.WordSpecification;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;

import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public WordEntity addWord(WordEntity word) {
        if(wordRepository.existsByKanji(word.getKanji())) {
            throw new IllegalArgumentException("This word already exists. Please try again");
        }

        return wordRepository.save(word);
    }

    public WordEntity updateWord(Integer id, WordEntity updatedWord) {
        WordEntity existingWord = wordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task " + id + " not found"));

        if(updatedWord.getKanji() != null
                && !updatedWord.getKanji().equals(existingWord.getKanji()) &&
                wordRepository.existsByKanji(updatedWord.getKanji())) {
            throw new IllegalArgumentException("This word already exists. Please try again");
        }

        if(updatedWord.getKanji() != null) {
            existingWord.setKanji(updatedWord.getKanji());
        }

        if(updatedWord.getReading() != null) {
            existingWord.setReading(updatedWord.getReading());
        }

        if(updatedWord.getCategories() != null) {
            existingWord.setCategories(updatedWord.getCategories());
        }

        if(updatedWord.getMeaning() != null) {
            existingWord.setMeaning(updatedWord.getMeaning());
        }

        if(updatedWord.getJlptLevel() != null) {
            existingWord.setJlptLevel(updatedWord.getJlptLevel());
        }

        return wordRepository.save(existingWord);
    }

    public List<WordEntity> listWords() {
        return wordRepository.findAll()
                .stream()
                .toList();
    }

    public List<WordCategory> listCategories() {
        return Arrays.asList(WordCategory.values());
    }

    public void removeWord(@PathVariable("id") Integer id) {
        wordRepository.deleteById(id);
    }

    public List<WordEntity> listWord(Integer id) {
        return wordRepository.findById(id)
                .stream()
                .toList();
    }

    public List<WordEntity> findWords(String kanji,
                                   String reading,
                                   String meaning,
                                   WordCategory category,
                                   JLPTLevel level) {
        Specification<WordEntity> wordSpec = ((root, query, criteriaBuilder) -> null);

        if(kanji != null) {
            wordSpec = wordSpec.and(WordSpecification.hasKanji(kanji));
        }

        if(reading != null) {
            wordSpec = wordSpec.and(WordSpecification.hasReading(reading));
        }

        if(meaning != null) {
            wordSpec = wordSpec.and(WordSpecification.hasMeaning(meaning));
        }

        if(category != null) {
            wordSpec = wordSpec.and(WordSpecification.hasCategory(category));
        }

        if(level != null) {
            wordSpec = wordSpec.and(WordSpecification.hasJlptLevel(level));
        }

        return wordRepository.findAll(wordSpec);
    }

    public WordEntity randomWord() {
        List<WordEntity> words = wordRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());

        return words.get(randomIndex);
    }
}
