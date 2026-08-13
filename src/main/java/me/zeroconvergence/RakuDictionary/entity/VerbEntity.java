package me.zeroconvergence.RakuDictionary.entity;

import jakarta.persistence.*;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.VerbGroup;
import me.zeroconvergence.RakuDictionary.types.VerbTransitivity;
import me.zeroconvergence.RakuDictionary.types.WordCategory;

import java.util.HashSet;
import java.util.Set;

@Table(name = "verbs")
@Entity
public class VerbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dictionaryForm")
    private String dictionaryForm;

    @Column(name = "reading")
    private String reading;

    @Column(name = "meaning")
    private String meaning;

    @ElementCollection(targetClass = WordCategory.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "verb_categories",
            joinColumns = @JoinColumn(name = "verb_id")
    )
    @Column(name = "categories")
    private Set<WordCategory> categories = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "verbGroup")
    private VerbGroup verbGroup;

    @Enumerated(EnumType.STRING)
    @Column(name = "verbTransitivity")
    private VerbTransitivity verbTransitivity;

    @Enumerated(EnumType.STRING)
    @Column(name = "jlptLevel")
    private JLPTLevel jlptLevel;

    public VerbEntity() {}

    public String getDictionaryForm() {
        return dictionaryForm;
    }

    public void setDictionaryForm(String dictionaryForm) {
        this.dictionaryForm = dictionaryForm;
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

    public Set<WordCategory> getCategories() {
        return categories;
    }

    public void setCategory(Set<WordCategory> categories) {
        this.categories = categories;
    }

    public VerbGroup getVerbGroup() {
        return verbGroup;
    }

    public void setVerbGroup(VerbGroup verbGroup) {
        this.verbGroup = verbGroup;
    }

    public VerbTransitivity getVerbTransitivity() {
        return verbTransitivity;
    }

    public void setVerbTransitivity(VerbTransitivity verbTransitivity) {
        this.verbTransitivity = verbTransitivity;
    }

    public JLPTLevel getJlptLevel() {
        return jlptLevel;
    }

    public void setJlptLevel(JLPTLevel jlptLevel) {
        this.jlptLevel = jlptLevel;
    }
}
