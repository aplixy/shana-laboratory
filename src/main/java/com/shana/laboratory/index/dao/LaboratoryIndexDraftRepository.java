package com.shana.laboratory.index.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraft;

/**
 * @author Lidan Li
 */

public interface LaboratoryIndexDraftRepository extends JpaRepository<LaboratoryIndexDraft, String>,JpaSpecificationExecutor<LaboratoryIndexDraft> {

	List<LaboratoryIndexDraft> findByCode(String code);

}
