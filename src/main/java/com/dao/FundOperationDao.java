package com.dao;

import com.entity.FundOperation;
import com.entity.FundPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface FundOperationDao extends JpaRepository<FundOperation,Integer> {
}

