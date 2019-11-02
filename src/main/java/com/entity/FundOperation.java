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
@Table(name = "t_fund_operation")
public class FundOperation implements Serializable {
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
    /**成立日期*/
    @Column(name = "start_date")
    private Date startDate;
    /**申购状态*/
    @Column(name = "apply_status")
    private String applyStatus;
    /**赎回状态*/
    @Column(name = "ransom_status")
    private String ransomStatus;
    /**最小投资额(元)*/
    @Column(name = "invest_min")
    private String investMin;
    /**前端收费(%)*/
    @Column(name = "pre_cost")
    private String preCost;
    /**后端收费(%)*/
    @Column(name = "back_cost")
    private String backCost;
    /**赎回费(%)*/
    @Column(name = "ransom_cost")
    private String ransomCost;
    /**管理费(%)*/
    @Column(name = "manage_cost")
    private String manageCost;
    /**托管费(%)*/
    @Column(name = "host_cost")
    private String hostCost;
    /**销售服务费(%)*/
    @Column(name = "sale_cost")
    private String saleCost;
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

