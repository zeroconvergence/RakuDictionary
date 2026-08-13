package me.zeroconvergence.RakuDictionary.specification;

import me.zeroconvergence.RakuDictionary.entity.WordEntity;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.data.jpa.domain.Specification;

public class WordSpecification {
    public static Specification<WordEntity> hasKanji(String kanji) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("kanji"),
                        kanji
                )
        );
    }

    public static Specification<WordEntity> hasReading(String reading) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("reading"),
                        "%" + reading + "%"
                )
        );
    }

    public static Specification<WordEntity> hasMeaning(String meaning) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("meaning"),
                        "%" + meaning + "%"
                )
        );
    }

    public static Specification<WordEntity> hasJlptLevel(JLPTLevel level) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("jlptLevel"),
                        level
                )
        );
    }

    public static Specification<WordEntity> hasCategory(WordCategory category) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("category"),
                        "%" + category + "%"
                )
        );
    }
}
