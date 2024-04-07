<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.wallet.history.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    WalletHistoryService walletHistorySvc = new WalletHistoryService();
    List<WalletHistoryVO> list = walletHistorySvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>所有交易資料 - listAllWalletHistory.jsp</title>

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
            width: 800px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }

        table, th, td {
            border: 1px solid #CCCCFF;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>

</head>
<body>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>所有交易資料 - listAllWalletHistory.jsp</h3>
            <h4><a href="select_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<table>
    <tr>
        <th>交易編號</th>
        <th>異動時間</th>
        <th>會員編號</th>
        <th>異動金額</th>
        <th>金流種類</th>
        <th>修改</th>
    </tr>
    <%@ include file="page1.jsp" %>
    <c:forEach var="walletHistoryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

        <tr>
            <td>${walletHistoryVO.walletHistoryId}</td>
            <td>${walletHistoryVO.changeTime}</td>
            <td>${walletHistoryVO.memberId}</td>
            <td>${walletHistoryVO.changeAmount}</td>
            <td>${walletHistoryVO.changeType}</td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/walletHistory/walletHistory.do"
                      style="margin-bottom: 0;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="walletHistoryId" value="${walletHistoryVO.walletHistoryId}">
                    <input type="hidden" name="action" value="getOne_For_Update"></FORM>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="page2.jsp" %>

</body>
</html>