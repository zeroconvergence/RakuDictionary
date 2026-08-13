package me.zeroconvergence.RakuDictionary.service;

import me.zeroconvergence.RakuDictionary.entity.VerbEntity;

import me.zeroconvergence.RakuDictionary.repository.VerbRepository;
import org.springframework.stereotype.Service;

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
}
