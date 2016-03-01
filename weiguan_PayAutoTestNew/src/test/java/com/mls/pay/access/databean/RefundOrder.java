package com.mls.pay.access.databean;

/**
 * Created by whuyi123 on 15-4-1.
 */
public class RefundOrder {
    private String refundAmount;
    private String refundId;
    private String status;
    private String gateStatus;
    private String sharaData;

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(String gateStatus) {
        this.gateStatus = gateStatus;
    }

    public String getSharaData() {
        return sharaData;
    }

    public void setSharaData(String sharaData) {
        this.sharaData = sharaData;
    }


}
