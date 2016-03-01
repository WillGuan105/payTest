package com.mls.pay.access.databean;

/**
 * Created by whuyi123 on 15-3-31.
 */
public class PayInfo {
    private String status;
    private String orderAmount;
    private String sharaData;
    private String payId;
    private String payAmount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getSharaData() {
        return sharaData;
    }

    public void setSharaData(String sharaData) {
        this.sharaData = sharaData;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
