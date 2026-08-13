package me.zeroconvergence.RakuDictionary.repository;

import me.zeroconvergence.RakuDictionary.entity.VerbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VerbRepository extends JpaRepository<VerbEntity, Integer>, JpaSpecificationExecutor<VerbEntity> {
    boolean existsByDictionaryForm(String dictionaryForm);
}
