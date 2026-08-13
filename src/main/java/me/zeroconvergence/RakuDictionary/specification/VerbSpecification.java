package me.zeroconvergence.RakuDictionary.specification;

import me.zeroconvergence.RakuDictionary.entity.VerbEntity;

import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.VerbGroup;
import me.zeroconvergence.RakuDictionary.types.VerbTransitivity;
import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.data.jpa.domain.Specification;

public class VerbSpecification {
    public static Specification<VerbEntity> hasDictionaryForm(String dictionaryForm) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("dictionaryForm"),
                        dictionaryForm
                )
        );
    }

    public static Specification<VerbEntity> hasReading(String reading) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("reading"),
                        "%" + reading + "%"
                )
        );
    }

    public static Specification<VerbEntity> hasMeaning(String meaning) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("meaning"),
                        "%" + meaning + "%"
                )
        );
    }

    public static Specification<VerbEntity> hasCategory(WordCategory category) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("category"),
                        "%" + category + "%"
                )
        );
    }

    public static Specification<VerbEntity> hasVerbGroup(VerbGroup verbGroup) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("verbGroup"),
                        "%" + verbGroup + "%"
                )
        );
    }

    public static Specification<VerbEntity> hasVerbTransitivity(VerbTransitivity verbTransitivity) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("verbTransitivity"),
                        "%" + verbTransitivity + "%"
                )
        );
    }

    public static Specification<VerbEntity> hasJlptLevel(JLPTLevel jlptLevel) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("jlptLevel"),
                        "%" + jlptLevel + "%"
                )
        );
    }
}
