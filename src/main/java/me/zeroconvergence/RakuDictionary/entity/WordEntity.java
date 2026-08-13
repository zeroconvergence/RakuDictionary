package me.zeroconvergence.RakuDictionary.entity;

import jakarta.persistence.*;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.WordCategory;

import java.util.HashSet;
import java.util.Set;

@Table(name = "words")
@Entity
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kanji")
    private String kanji;

    @Column(name = "reading")
    private String reading;

    @ElementCollection(targetClass = WordCategory.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "word_categories",
            joinColumns = @JoinColumn(name = "word_id")
    )
    @Column(name = "categories")
    private Set<WordCategory> categories = new HashSet<>();

    @Column(name = "meaning")
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "jlptLevel")
    private JLPTLevel jlptLevel;

    public WordEntity() {
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public JLPTLevel getJlptLevel() {
        return jlptLevel;
    }

    public void setJlptLevel(JLPTLevel jlptLevel) {
        this.jlptLevel = jlptLevel;
    }

    public Set<WordCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<WordCategory> categories) {
        this.categories = categories;
    }
}
