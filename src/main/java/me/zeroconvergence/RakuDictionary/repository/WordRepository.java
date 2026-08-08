package me.zeroconvergence.RakuDictionary.repository;

import me.zeroconvergence.RakuDictionary.entity.WordEntity;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;

import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface WordRepository extends JpaRepository<WordEntity, Integer>, JpaSpecificationExecutor<WordEntity> {
    boolean existsByKanji(String kanji);

    List<WordEntity> findByKanjiContaining(String word);
    List<WordEntity> findByReadingContaining(String word);
    List<WordEntity> findByCategories(WordCategory category);
    List<WordEntity> findByJlptLevel(JLPTLevel jlptLevel);

    List<WordEntity> findByReadingAndJlptLevelContaining(String reading, JLPTLevel jlptLevel);
}
