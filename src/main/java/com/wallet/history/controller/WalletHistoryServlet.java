package com.wallet.history.controller;

import com.wallet.history.model.*;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WalletHistoryServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
            List<String> errorMsgs = new LinkedList<>();
            req.setAttribute("errorMsgs", errorMsgs);

            // ***************************1.接收請求參數 - 輸入格式的錯誤處理**********************
            String str = req.getParameter("walletHistoryId");
            if (str == null || (str.trim()).isEmpty()) {
                errorMsgs.add("請輸入交易編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView =
                        req.getRequestDispatcher("/walletHistory/select_page.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            Integer walletHistoryId = null;
            try {
                walletHistoryId = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("交易編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView =
                        req.getRequestDispatcher("/walletHistory/select_page.jsp");
                failureView.forward(req, res);
                // 程式中斷
            }

            // ***************************2.開始查詢資料*****************************************
            WalletHistoryService walletHistorySvc = new WalletHistoryService();
            WalletHistoryVO walletHistoryVO = walletHistorySvc.getWalletHistory(walletHistoryId);
            if (walletHistoryVO == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView =
                        req.getRequestDispatcher("/walletHistory/select_page.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            // ***************************3.查詢完成,準備轉交(Send the Success view)*************
            req.setAttribute("walletHistoryVO", walletHistoryVO); // 資料庫取出的walletHistoryVO物件,存入req
            String url = "/walletHistory/listOneWalletHistory.jsp";
            RequestDispatcher successView =
                    req.getRequestDispatcher(url); // 成功轉交 listOneWalletHistory.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllWalletHistory.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            //
            // ***************************1.接收請求參數****************************************
            Integer walletHistoryId = Integer.valueOf(req.getParameter("walletHistoryId").trim());

            //
            // ***************************2.開始查詢資料****************************************
            WalletHistoryService walletHistorySvc = new WalletHistoryService();
            WalletHistoryVO walletHistoryVO = walletHistorySvc.getWalletHistory(walletHistoryId);

            // ***************************3.查詢完成,準備轉交(Send the Success view)************
            req.setAttribute("walletHistoryVO", walletHistoryVO); // 資料庫取出的walletHistoryVO物件,存入req
            String url = "/walletHistory/update_walletHistory_input.jsp";
            RequestDispatcher successView =
                    req.getRequestDispatcher(url); // 成功轉交 update_walletHistory_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自 update_walletHistory_input.jsp 的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            // ***************************1.接收請求參數 - 輸入格式的錯誤處理**********************
            Integer walletHistoryId = Integer.valueOf(req.getParameter("walletHistoryId").trim());

            Timestamp changeTime;
            try {
                changeTime = Timestamp.valueOf(req.getParameter("changeTime").trim());
            } catch (IllegalArgumentException e) {
                changeTime = new Timestamp(System.currentTimeMillis());
                errorMsgs.add("請輸入異動時間!");
            }

            int memberId = 0;
            try {
                memberId = Integer.parseInt(req.getParameter("memberId").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("會員編號請填數字.");
            }

            int changeAmount = 0;
            try {
                changeAmount = Integer.parseInt(req.getParameter("changeAmount").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("異動金額請填數字.");
            }

            Byte changeType = Byte.valueOf(req.getParameter("changeType").trim());

            WalletHistoryVO walletHistoryVO = new WalletHistoryVO();
            walletHistoryVO.setWalletHistoryId(walletHistoryId);
            walletHistoryVO.setChangeTime(changeTime);
            walletHistoryVO.setMemberId(memberId);
            walletHistoryVO.setChangeAmount(changeAmount);
            walletHistoryVO.setChangeType(changeType);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute(
                        "walletHistoryVO", walletHistoryVO); // 含有輸入格式錯誤的walletHistoryVO物件,也存入req
                RequestDispatcher failureView =
                        req.getRequestDispatcher("/walletHistory/update_walletHistory_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            // ***************************2.開始修改資料*****************************************
            WalletHistoryService walletHistorySvc = new WalletHistoryService();
            walletHistoryVO =
                    walletHistorySvc.updateWalletHistory(
                            walletHistoryId, changeTime, memberId, changeAmount, changeType);

            // ***************************3.修改完成,準備轉交(Send the Success view)*************
            req.setAttribute(
                    "walletHistoryVO", walletHistoryVO); // 資料庫update成功後,正確的的walletHistoryVO物件,存入req
            String url = "listOneWalletHistory.jsp";
            RequestDispatcher successView =
                    req.getRequestDispatcher(url); // 修改成功後,轉交listOneWalletHistory.jsp
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addWalletHistory.jsp的請求

            List<String> errorMsgs = new LinkedList<>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            // ***********************1.接收請求參數 - 輸入格式的錯誤處理*************************

            Timestamp changeTime;
            try {
                changeTime = Timestamp.valueOf(req.getParameter("changeTime").trim());
            } catch (IllegalArgumentException e) {
                changeTime = new Timestamp(System.currentTimeMillis());
                errorMsgs.add("請輸入異動時間!");
            }

            int memberId = 0;
            try {
                memberId = Integer.parseInt(req.getParameter("memberId").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("會員編號請填數字.");
            }

            int changeAmount = 0;
            try {
                changeAmount = Integer.parseInt(req.getParameter("changeAmount").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("異動金額請填數字.");
            }

            Byte changeType = Byte.valueOf(req.getParameter("changeType").trim());

            WalletHistoryVO walletHistoryVO = new WalletHistoryVO();
            walletHistoryVO.setChangeTime(changeTime);
            walletHistoryVO.setMemberId(memberId);
            walletHistoryVO.setChangeAmount(changeAmount);
            walletHistoryVO.setChangeType(changeType);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute(
                        "walletHistoryVO", walletHistoryVO); // 含有輸入格式錯誤的walletHistoryVO物件,也存入req
                RequestDispatcher failureView =
                        req.getRequestDispatcher("/walletHistory/update_walletHistory_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            // ***************************2.開始新增資料***************************************
            WalletHistoryService walletHistorySvc = new WalletHistoryService();
            walletHistoryVO =
                    walletHistorySvc.addWalletHistory(
                            changeTime, memberId, changeAmount, changeType);

            // ***************************3.新增完成,準備轉交(Send the Success view)***********
            String url = "/walletHistory/listAllWalletHistory.jsp";
            RequestDispatcher successView =
                    req.getRequestDispatcher(url); // 新增成功後轉交listAllWalletHistory.jsp
            successView.forward(req, res);
        }
    }
}
