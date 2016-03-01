package com.mls.pay.access.databean;

/**
 * Created by whuyi123 on 15-4-1.
 */
public class AcctransInfo {
    private String realAmount;
    private String originalAmount;
    private String payAmount;

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getIncomeExpenseType() {
        return incomeExpenseType;
    }

    public void setIncomeExpenseType(String incomeExpenseType) {
        this.incomeExpenseType = incomeExpenseType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String accountType;
    private String incomeExpenseType;
    private String memo;
}
