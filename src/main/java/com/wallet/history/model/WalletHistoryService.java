package com.wallet.history.model;

import java.sql.Timestamp;
import java.util.List;

public class WalletHistoryService {

    private final WalletHistoryDAO_interface dao;

    public WalletHistoryService() {
        dao = new WalletHistoryJDBCDAO();
    }

    public WalletHistoryVO addWalletHistory(
            Timestamp changeTime, Integer memberId, Integer changeAmount, Byte changeType) {
        WalletHistoryVO walletHistoryVO = new WalletHistoryVO();

        walletHistoryVO.setChangeTime(changeTime);
        walletHistoryVO.setMemberId(memberId);
        walletHistoryVO.setChangeAmount(changeAmount);
        walletHistoryVO.setChangeType(changeType);
        dao.insert(walletHistoryVO);

        return walletHistoryVO;
    }

    public WalletHistoryVO updateWalletHistory(
            Integer walletHistoryId,
            Timestamp changeTime,
            Integer memberId,
            Integer changeAmount,
            Byte changeType) {

        WalletHistoryVO walletHistoryVO = new WalletHistoryVO();

        walletHistoryVO.setWalletHistoryId(walletHistoryId);
        walletHistoryVO.setChangeTime(changeTime);
        walletHistoryVO.setMemberId(memberId);
        walletHistoryVO.setChangeAmount(changeAmount);
        walletHistoryVO.setChangeType(changeType);
        dao.insert(walletHistoryVO);

        return walletHistoryVO;
    }

    public WalletHistoryVO getWalletHistory(Integer walletHistoryId) {
        return dao.findByPrimaryKey(walletHistoryId);
    }

    public List<WalletHistoryVO> getAll() {
        return dao.getAll();
    }
}
