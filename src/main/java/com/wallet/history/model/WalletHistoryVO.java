package com.wallet.history.model;

import java.sql.Timestamp;

public class WalletHistoryVO implements java.io.Serializable {
    private Integer walletHistoryId;
    private Timestamp changeTime;
    private Integer memberId;
    private Integer changeAmount;
    private Byte changeType;

    public Integer getWalletHistoryId() {
        return walletHistoryId;
    }

    public void setWalletHistoryId(Integer walletHistoryId) {
        this.walletHistoryId = walletHistoryId;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(Integer changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Byte getChangeType() {
        return changeType;
    }

    public void setChangeType(Byte changeType) {
        this.changeType = changeType;
    }
}
