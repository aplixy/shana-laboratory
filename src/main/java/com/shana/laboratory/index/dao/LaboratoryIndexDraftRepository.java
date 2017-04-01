package com.shana.laboratory.index.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;

/**
 * @author Lidan Li
 */

public interface LaboratoryIndexDraftRepository extends CrudRepository<LaboratoryIndexDraft, String> {

	List<LaboratoryIndexDraft> findByCode(String code);

}
