package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wei
 * @description 基金投资组合表
 * @date 2019/10/31
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_fund_portfolio")
public class FundPortfolio implements Serializable {
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
    /**投资风格*/
    @Column(name = "invest_style")
    private String investStyle;
    /**投资规模*/
    @Column(name = "invest_type")
    private String investType;
    /**股票仓位(%)*/
    @Column(name = "stock_percent")
    private String stockPercent;
    /**债券仓位(%)*/
    @Column(name = "bond_percent")
    private String bondPercent;
    /**前十大持股(%)*/
    @Column(name = "last10")
    private String last10;
    /**前五大债券(%)*/
    @Column(name = "last5")
    private String last5;
    /**净资产(亿元)*/
    @Column(name = "all_value")
    private String allValue;
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

