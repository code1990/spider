package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wei
 * @description 基金业绩和风险表
 * @date 2019/10/31 标准差
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_fund_performance")
public class FundPerformance implements Serializable {
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
    /**1天回报(%)*/
    @Column(name = "pay_1_day")
    private String pay1day;
    /**1周回报(%)*/
    @Column(name = "pay_1_week")
    private String pay1week;
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
    /**设立以来总回报(%)*/
    @Column(name = "pay_start")
    private String payStart;
    /**三年标准差(%)*/
    @Column(name = "std3")
    private String std3;
    /**三年晨星风险系数*/
    @Column(name = "risk3")
    private String risk3;
    /**insert时间*/
    @Column(name = "create_time")
    private Date createTime;
    /**当前的页码*/
    @Column(name = "page_size")
    private Integer pageSize;
    /**基金明细url*/
    @Column(name="fund_url")
    private String fundUrl;
}

