package com.shana.laboratory.index.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shana.laboratory.index.pojo.LaboratoryIndexDraftApproval;

/**
 * @author Lidan Li
 */

public interface LaboratoryIndexDraftApprovalRepository extends JpaRepository<LaboratoryIndexDraftApproval, String>,JpaSpecificationExecutor<LaboratoryIndexDraftApproval> {

	List<LaboratoryIndexDraftApproval> findByIndexDraftId(String indexDraftId);
}
