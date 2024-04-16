package com.wallet.history.model;

import java.util.*;
import java.sql.*;

public class WalletHistoryJDBCDAO implements WalletHistoryDAO_interface {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/mmdf?serverTimezone=Asia/Taipei";
    String userid = "root";
    String passwd = "Tgk89bid";

    private static final String INSERT_STMT =
            "INSERT INTO wallet_history (change_time,member_id,change_amount,change_type) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_STMT =
            "SELECT wallet_history_id,change_time,member_id,change_amount,change_type FROM wallet_history ORDER BY wallet_history_id";
    private static final String GET_ONE_STMT =
            "SELECT wallet_history_id,change_time,member_id,change_amount,change_type FROM wallet_history WHERE wallet_history_id = ?";

    private static final String UPDATE =
            "UPDATE wallet_history set change_time=?, member_id=?, change_amount=?, change_type=? WHERE wallet_history_id = ?";

    @Override
    public void insert(WalletHistoryVO walletHistoryVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setTimestamp(1, walletHistoryVO.getChangeTime());
            pstmt.setInt(2, walletHistoryVO.getMemberId());
            pstmt.setInt(3, walletHistoryVO.getChangeAmount());
            pstmt.setByte(4, walletHistoryVO.getChangeType());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void update(WalletHistoryVO walletHistoryVO) {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(UPDATE);

            pstmt.setTimestamp(1, walletHistoryVO.getChangeTime());
            pstmt.setInt(2, walletHistoryVO.getMemberId());
            pstmt.setInt(3, walletHistoryVO.getChangeAmount());
            pstmt.setByte(4, walletHistoryVO.getChangeType());
            pstmt.setInt(5, walletHistoryVO.getWalletHistoryId());

            pstmt.executeUpdate();

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public WalletHistoryVO findByPrimaryKey(Integer walletHistoryId) {
        WalletHistoryVO walletHistoryVO = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, walletHistoryId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVo 也稱為 Domain objects
                walletHistoryVO = new WalletHistoryVO();
                walletHistoryVO.setWalletHistoryId(rs.getInt("wallet_history_id"));
                walletHistoryVO.setChangeTime(rs.getTimestamp("change_time"));
                walletHistoryVO.setMemberId(rs.getInt("member_id"));
                walletHistoryVO.setChangeAmount(rs.getInt("change_amount"));
                walletHistoryVO.setChangeType(rs.getByte("change_type"));
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return walletHistoryVO;
    }

    @Override
    public List<WalletHistoryVO> getAll() {
        List<WalletHistoryVO> list = new ArrayList<>();
        WalletHistoryVO walletHistoryVO;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userid, passwd);
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                // empVO 也稱為 Domain objects
                walletHistoryVO = new WalletHistoryVO();
                walletHistoryVO.setWalletHistoryId(rs.getInt("wallet_history_id"));
                walletHistoryVO.setChangeTime(rs.getTimestamp("change_time"));
                walletHistoryVO.setMemberId(rs.getInt("member_id"));
                walletHistoryVO.setChangeAmount(rs.getInt("change_amount"));
                walletHistoryVO.setChangeType(rs.getByte("change_type"));
                list.add(walletHistoryVO); // Store the row in the list
            }

            // Handle any driver errors
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
            // Clean up JDBC resources
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        WalletHistoryJDBCDAO dao = new WalletHistoryJDBCDAO();
        // 新增
        WalletHistoryVO walletHistoryVO1 = new WalletHistoryVO();
        walletHistoryVO1.setChangeTime(new Timestamp(System.currentTimeMillis()));
        walletHistoryVO1.setMemberId(3);
        walletHistoryVO1.setChangeAmount(2000);
        walletHistoryVO1.setChangeType((byte) 1);
        dao.insert(walletHistoryVO1);

        // 修改
//        WalletHistoryVO walletHistoryVO2 = new WalletHistoryVO();
//        walletHistoryVO2.setWalletHistoryId(1);
//        walletHistoryVO2.setChangeTime(new Timestamp(System.currentTimeMillis()));
//        walletHistoryVO2.setMemberId(1);
//        walletHistoryVO2.setChangeAmount(15000);
//        walletHistoryVO2.setChangeType((byte) 4);
//        dao.update(walletHistoryVO2);

        // 查詢
//        WalletHistoryVO walletHistoryVO3 = dao.findByPrimaryKey(2);
//        System.out.print(walletHistoryVO3.getWalletHistoryId() + ", ");
//        System.out.print(walletHistoryVO3.getChangeTime() + ", ");
//        System.out.print(walletHistoryVO3.getMemberId() + ", ");
//        System.out.print(walletHistoryVO3.getChangeAmount() + ", ");
//        System.out.print(walletHistoryVO3.getChangeType());
//        System.out.println();
//        System.out.println("---------------------");

        // 查詢
//        List<WalletHistoryVO> list = dao.getAll();
//        for (WalletHistoryVO aWalletHistoryVO : list) {
//            System.out.print(aWalletHistoryVO.getWalletHistoryId() + ", ");
//            System.out.print(aWalletHistoryVO.getChangeTime() + ", ");
//            System.out.print(aWalletHistoryVO.getMemberId() + ", ");
//            System.out.print(aWalletHistoryVO.getChangeAmount() + ", ");
//            System.out.print(aWalletHistoryVO.getChangeType());
//            System.out.println();
//            System.out.println("---------------------");
//        }
    }
}
