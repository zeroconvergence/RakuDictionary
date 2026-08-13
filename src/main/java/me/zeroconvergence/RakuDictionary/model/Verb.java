package me.zeroconvergence.RakuDictionary.model;

import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.VerbGroup;
import me.zeroconvergence.RakuDictionary.types.VerbTransitivity;
import me.zeroconvergence.RakuDictionary.types.WordCategory;

public record Verb (
        Integer id,
        String dictionaryForm,
        String reading,
        String meaning,
        WordCategory categories,
        VerbGroup verbGroup,
        VerbTransitivity verbTransitivity,
        JLPTLevel jlptLevel
) {
}
