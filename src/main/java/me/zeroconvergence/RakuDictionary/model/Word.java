package me.zeroconvergence.RakuDictionary.model;

import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.WordCategory;

public record Word(
        Integer id,
        String kanji,
        WordCategory categories,
        String reading,
        String meaning,
        JLPTLevel jlptLevel
) {
}
