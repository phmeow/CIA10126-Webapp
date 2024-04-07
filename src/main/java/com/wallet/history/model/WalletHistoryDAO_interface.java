package com.wallet.history.model;

import java.util.*;

public interface WalletHistoryDAO_interface {
    void insert(WalletHistoryVO walletHistoryVO);

    void update(WalletHistoryVO walletHistoryVO);

    WalletHistoryVO findByPrimaryKey(Integer walletHistoryId);

    List<WalletHistoryVO> getAll();

    // 萬用複合查詢(傳入參數型態Map)(回傳 List)
    //    public List<WalletHistoryVO> getAll(Map<String, String[]> map);
}
