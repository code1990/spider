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
    /*基准指数*/
    @Column(name = "base_pay_percent")
    private String basePayPercent;
    //=========================================
    /**insert时间*/
    @Column(name = "create_time")
    private Date createTime;
    /**基金明细url*/
    @Column(name="fund_url")
    private String fundUrl;
}

