package com.shana.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shana.user.pojo.ShanaAccount;

/**
 * @author Lidan Li
 */

public interface ShanaAccountRepository extends JpaRepository<ShanaAccount, String> {



}
