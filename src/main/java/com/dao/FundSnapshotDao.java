package com.dao;

import com.entity.FundSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface FundSnapshotDao extends JpaRepository<FundSnapshot,Integer> {

//    @Query(value = "select fund_code from t_fund_performance", nativeQuery = true)
//    List<Object[]> selectInfo(@Param("pkid") BigDecimal pkid);
}

