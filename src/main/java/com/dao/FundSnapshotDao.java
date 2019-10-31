package com.dao;

import com.entity.FundSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface FundSnapshotDao extends JpaRepository<FundSnapshot,Integer> {
}

