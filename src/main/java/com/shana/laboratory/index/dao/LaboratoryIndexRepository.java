package com.shana.laboratory.index.dao;

import org.springframework.data.repository.CrudRepository;

import com.shana.laboratory.index.pojo.LaboratoryIndex;

/**
 * @author Lidan Li
 */

public interface LaboratoryIndexRepository extends CrudRepository<LaboratoryIndex, String> {

	LaboratoryIndex findByCode(String code);

}
