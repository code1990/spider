package com.dao;

import com.entity.FundBase;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface FundBaseDao extends JpaRepository<FundBase,Integer> {

//    @Query(value = "select fund_code from t_fund_performance", nativeQuery = true)
//    List<Object[]> selectInfo(@Param("pkid") BigDecimal pkid);
}

