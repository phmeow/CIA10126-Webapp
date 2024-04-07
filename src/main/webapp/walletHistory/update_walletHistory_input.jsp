<%@ page import="com.wallet.history.model.WalletHistoryVO" %>
<%@ page import="com.wallet.history.model.WalletHistoryService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
    WalletHistoryVO walletHistoryVO = (WalletHistoryVO) request.getAttribute("walletHistoryVO");
%>
<%
    WalletHistoryService walletHistorySvc = new WalletHistoryService();
    List<WalletHistoryVO> list = walletHistorySvc.getAll();
    pageContext.setAttribute("list", list);
%>
<%----<%= walletHistoryVO == null %>--${list}-- <!-- line 100 -->--%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>錢包交易紀錄修改 - update_walletHistory_input.jsp</title>

    <style>
        table#table-1 {
            background-color: #CCCCFF;
            border: 2px solid black;
            text-align: center;
        }

        table#table-1 h4 {
            color: red;
            display: block;
            margin-bottom: 1px;
        }

        h4 {
            color: blue;
            display: inline;
        }
    </style>

    <style>
        table {
            width: 450px;
            background-color: white;
            margin-top: 1px;
            margin-bottom: 1px;
        }

        table, th, td {
            border: 0 solid #CCCCFF;
        }

        th, td {
            padding: 1px;
        }
    </style>

</head>
<body>

<table id="table-1">
    <tr>
        <td>
            <h3>錢包交易紀錄修改 - update_walletHistory_input.jsp</h3>
            <h4><a href="select_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <span style="color:red">請修正以下錯誤:</span>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="walletHistory.do" name="form1">
    <table>
        <tr>
            <td>交易編號:</td>
            <td>
                <%=walletHistoryVO.getWalletHistoryId()%>
            </td>
        </tr>
        <tr>
            <td>異動時間:</td>
            <td><label>
                <input type="TEXT" name="changeTime" value="<%=walletHistoryVO.getChangeTime()%>" size="45"/>
            </label></td>
        </tr>
        <tr>
            <td>會員編號:</td>
            <td><label>
                <input type="TEXT" name="memberId" value="<%=walletHistoryVO.getMemberId()%>" size="45"/>
            </label></td>
        </tr>
        <tr>
            <td>異動金額:</td>
            <td><label>
                <input name="changeAmount" type="text" value="<%=walletHistoryVO.getChangeAmount()%>">
            </label></td>
        </tr>
        <tr>
            <td>金流種類:</td>
            <td><label>
                <input type="TEXT" name="changeType" value="<%=walletHistoryVO.getChangeType()%>" size="45"/>
            </label></td>
        </tr>

        <%--        <jsp:useBean id="deptSvc" scope="page" class="com.wallet.history.model.WalletHistoryService" />--%>

    </table>
    <br>
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="walletHistoryId" value="<%=walletHistoryVO.getWalletHistoryId()%>">
    <input type="submit" value="送出修改"></FORM>
</body>

</html>