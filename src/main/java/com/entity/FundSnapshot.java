package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wei
 * @description 基金快照表 添加columnDefinition 导致JPA自动建表失效
 * @date 2019/10/31
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_fund_snapshot")
public class FundSnapshot implements Serializable {
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
    /**基金类型*/
    @Column(name = "fund_type")
    private String fundType;
    /**三年评级*/
    @Column(name = "lever3")
    private String lever3;
    /**5年评级*/
    @Column(name = "lever5")
    private String lever5;
    /**净值日期*/
    @Column(name = "value_date")
    private Date valueDate;
    /**单位净值*/
    @Column(name = "unit_value")
    private String unitValue;
    /**净值日变动*/
    @Column(name = "value_day_change")
    private String valueDayChange;
    /**今年以来回报*/
    @Column(name = "year_pay_percent")
    private String yearPayPercent;
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

