package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wei
 * @description 基金基本信息表
 * @date 2019/10/31
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_fund_base")
public class FundBase implements Serializable {
    /**主键id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**序号*/
    @Column(name = "num")
    private String num;
    /**基金代码*/
    @Column(name = "fund_code")
    private String fundCode;
    /**基金名称*/
    @Column(name = "fund_name")
    private String fundName;
    /**单位净值*/
    @Column(name = "unit_value")
    private String unitValue;
    /**净值日期*/
    @Column(name = "value_date")
    private String valueDate;
    /**净值日变动*/
    @Column(name = "value_day_change")
    private String valueDayChange;
    /**净值日变动%*/
    @Column(name = "value_day_percent")
    private String valueDayPercent;
    //===============================
    /**基金类型*/
    @Column(name = "fund_type")
    private String fundType;
    /**成立日期*/
    @Column(name = "start_date")
    private String startDate;
    /**成立日期*/
    @Column(name = "open_date")
    private String openDate;
    /**上市日期*/
    @Column(name = "sale_date")
    private String saleDate;
    /**申购状态*/
    @Column(name = "apply_status")
    private String applyStatus;
    /**赎回状态*/
    @Column(name = "ransom_status")
    private String ransomStatus;
    /**投资风格*/
    @Column(name = "invest_style")
    private String investStyle;
    /**净资产(亿元)*/
    @Column(name = "all_value")
    private String allValue;
    /**最小投资额(元)*/
    @Column(name = "invest_min")
    private String investMin;
    /**上市交易所*/
    @Column(name = "sale_place")
    private String salePlace;
    /**前端收费(%)*/
    @Column(name = "pre_cost")
    private String preCost;
    /**后端收费(%)*/
    @Column(name = "back_cost")
    private String backCost;
    //=====================================
    /**今年以来回报*/
    @Column(name = "year_pay_percent")
    private String yearPayPercent;
    /**基准指数*/
    @Column(name = "base_pay_percent")
    private String basePayPercent;
    /**同类平均指数*/
    @Column(name = "common_pay_percent")
    private String commonPayPercent;
    //=========================================
    /**1个月回报(%)*/
    @Column(name = "pay_1_month")
    private String pay1month;
    /**3个月回报(%)*/
    @Column(name = "pay_3_month")
    private String pay3month;
    /**6个月回报(%)*/
    @Column(name = "pay_6_month")
    private String pay6month;
    /**1年回报(%)*/
    @Column(name = "pay_1_year")
    private String pay1year;
    /**2年年化回报(%)*/
    @Column(name = "pay_2_year")
    private String pay2year;
    /**3年年化回报(%)*/
    @Column(name = "pay_3_year")
    private String pay3year;
    /**5年年化回报(%)*/
    @Column(name = "pay_5_year")
    private String pay5year;
    /**10年年化回报(%)*/
    @Column(name = "pay_10_year")
    private String pay10year;
    //=========================================
    /**三年评级*/
    @Column(name = "lever3")
    private String lever3;
    /**5年评级*/
    @Column(name = "lever5")
    private String lever5;
    //========================================
    /**标准差*/
    @Column(name = "avg3")
    private String avg3;
    /**标准差*/
    @Column(name = "std3")
    private String std3;
    /**晨星风险系数*/
    @Column(name = "risk3")
    private String risk3;
    /**夏普比率*/
    @Column(name = "sharp")
    private String sharp;
    //=================================
    /**基准指数-阿尔法系数*/
    @Column(name = "base_a1")
    private String baseA1;
    /**基准指数-贝塔系数*/
    @Column(name = "base_b1")
    private String baseB1;
    /**基准指数-R平方*/
    @Column(name = "base_sqrt")
    private String baseSqrt;
    /**同类平均-阿尔法系数*/
    @Column(name = "common_a1")
    private String commonA1;
    /**同类平均-贝塔系数*/
    @Column(name = "common_b1")
    private String commonB1;
    /**同类平均-R平方*/
    @Column(name = "common_sqrt")
    private String commonSqrt;
    //===========================
    /**风格*/
    @Column(name = "fund_style")
    private String fundStyle;
    /**规模*/
    @Column(name = "fund_scope")
    private String fundScope;
    //=======================
    /**﻿现金*/
    @Column(name = "money_percent")
    private String moneyPercent;
    /**股票*/
    @Column(name = "stock_percent")
    private String stockPercent;
    /**债券*/
    @Column(name = "bond_percent")
    private String bondPercent;
    /**其他*/
    @Column(name = "other_percent")
    private String otherPercent;
    /**行业分布*/
    @Column(name = "all_percent")
    private String allPercent;
    /**股票持仓*/
    @Column(name = "all_stock")
    private String allStock;
    /**分红信息*/
    @Column(name = "div_info")
    private String divInfo;
    /**拆分信息*/
    @Column(name = "split_info")
    private String splitInfo;
    //============================================
    /**insert时间*/
    @Column(name = "create_time")
    private Date createTime;
    /**基金明细url*/
    @Column(name="fund_url")
    private String fundUrl;
}

