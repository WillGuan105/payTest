package com.mls.pay.access.databean;

/**
 * Created by whuyi123 on 15-4-1.
 */
public class DanbaoConfirm {
    private String totalNo;
    private String orderNo;
    private String status;
    private String payAmount;
    private String confirmAmount;
    private String shareData;

    public String getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(String totalNo) {
        this.totalNo = totalNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getConfirmAmount() {
        return confirmAmount;
    }

    public void setConfirmAmount(String confirmAmount) {
        this.confirmAmount = confirmAmount;
    }

    public String getShareData() {
        return shareData;
    }

    public void setShareData(String shareData) {
        this.shareData = shareData;
    }


}
