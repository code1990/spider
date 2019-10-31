package com.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wei
 * @description columnDefinition 导致自动建表失效
 * @date 2019/10/31
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_fund_snapshot")
public class FundSnapshot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "num")
    private String num;
    @Column(name = "fund_code")
    private String fundCode;
    @Column(name = "fund_name")
    private String fundName;
    @Column(name = "fund_type")
    private String fundType;
    @Column(name = "lever3")
    private String lever3;
    @Column(name = "lever5")
    private String lever5;
    @Column(name = "value_date")
    private Date valueDate;
    @Column(name = "unit_value")
    private String unitValue;
    @Column(name = "value_day_change")
    private String valueDayChange;
    @Column(name = "year_pay_percent")
    private String yeaPayPercent;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "page_size")
    private Integer pageSize;
    @Column(name="fund_url")
    private String fundUrl;
}

