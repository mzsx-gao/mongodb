package com.gao.mongodb.springboot;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 名称: RepayPlanEntity.java
 * 描述: TODO
 *
 * @author gaoshudian
 * @date 2019/5/23 5:37 PM
 */
@Data
@Document(collection = "repayPlan")
public class RepayPlanEntity {

    private String subAcctNo;           //代发代扣账号
    private String acctSeq;             //贷款借据号
    private String merchantRequestSeq;  //商户流水号
    private String loanperiod;          //期数
    private String productCode;         //产品参数
    private String acctParamId;         //子产品参数
    private String repayDate;           //应还款日期
    private String repayAmt;            //应还款金额
    private String repayBal;            //应还本金
    private String repayInte;           //应还利息
    private String repayPint;           //应还罚息
    private String repayFee;            //应还费用

}
