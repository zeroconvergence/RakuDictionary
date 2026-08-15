package me.zeroconvergence.RakuDictionary.service;

import me.zeroconvergence.RakuDictionary.entity.VerbEntity;

import me.zeroconvergence.RakuDictionary.entity.WordEntity;
import me.zeroconvergence.RakuDictionary.repository.VerbRepository;
import me.zeroconvergence.RakuDictionary.specification.VerbSpecification;
import me.zeroconvergence.RakuDictionary.specification.WordSpecification;
import me.zeroconvergence.RakuDictionary.types.JLPTLevel;
import me.zeroconvergence.RakuDictionary.types.VerbGroup;
import me.zeroconvergence.RakuDictionary.types.VerbTransitivity;
import me.zeroconvergence.RakuDictionary.types.WordCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class VerbService {
    private final VerbRepository verbRepository;

    public VerbService(VerbRepository verbRepository) {
        this.verbRepository = verbRepository;
    }

    public VerbEntity addVerb(VerbEntity verbEntity) {
        return verbRepository.save(verbEntity);
    }

    public List<VerbEntity> listVerbs() {
        return verbRepository.findAll().stream().toList();
    }

    public List<VerbEntity> listVerb(Integer id) {
        return verbRepository.findById(id).stream().toList();
    }

    public VerbEntity updateVerb(Integer id, VerbEntity updatedVerb) {
        VerbEntity existingVerb = verbRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verb with " + id + " not found"));

        if(updatedVerb.getDictionaryForm() != null
                && !updatedVerb.getDictionaryForm().equals(existingVerb.getDictionaryForm()) &&
                verbRepository.existsByDictionaryForm(updatedVerb.getDictionaryForm())) {
            throw new IllegalArgumentException("This verb already exists. Please try again");
        }

        if(updatedVerb.getDictionaryForm() != null) {
            existingVerb.setDictionaryForm(updatedVerb.getDictionaryForm());
        }

        if(updatedVerb.getReading() != null) {
            existingVerb.setReading(updatedVerb.getReading());
        }

        if(updatedVerb.getCategories() != null) {
            existingVerb.setCategory(updatedVerb.getCategories());
        }

        if(updatedVerb.getMeaning() != null) {
            existingVerb.setMeaning(updatedVerb.getMeaning());
        }

        if(updatedVerb.getJlptLevel() != null) {
            existingVerb.setJlptLevel(updatedVerb.getJlptLevel());
        }

        return verbRepository.save(existingVerb);
    }

    public void removeVerb(Integer id) {
        verbRepository.deleteById(id);
    }

    public List<VerbEntity> findVerbs(String dictionaryForm,
                                      String reading,
                                      String meaning,
                                      WordCategory categories,
                                      VerbGroup verbGroup,
                                      VerbTransitivity verbTransitivity,
                                      JLPTLevel level) {
        Specification<VerbEntity> verbSpec = ((root, query, criteriaBuilder) -> null);

        if(dictionaryForm != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasDictionaryForm(dictionaryForm));
        }

        if(reading != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasReading(reading));
        }

        if(meaning != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasMeaning(meaning));
        }

        if(categories != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasCategory(categories));
        }

        if(verbGroup != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasVerbGroup(verbGroup));
        }

        if(verbTransitivity != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasVerbTransitivity(verbTransitivity));
        }

        if(level != null) {
            verbSpec = verbSpec.and(VerbSpecification.hasJlptLevel(level));
        }

        return verbRepository.findAll(verbSpec);
    }
}
