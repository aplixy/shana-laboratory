package com.shana.laboratory.index.dao;

import org.springframework.data.repository.CrudRepository;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;

/**
 * @author Lidan Li
 */

public interface LaboratoryIndexDraftRepository extends CrudRepository<LaboratoryIndexDraft, String> {

	LaboratoryIndexDraft findByCode(String code);

}
