package com.dao;

import com.entity.FundPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wei
 * @description
 * @date 2019/10/30
 */
public interface FundPortfolioDao extends JpaRepository<FundPortfolio,Integer> {

//    @Query(value = "select fund_code from t_fund_performance", nativeQuery = true)
//    List<Object[]> selectInfo(@Param("pkid") BigDecimal pkid);
}

