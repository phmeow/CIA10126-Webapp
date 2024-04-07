<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.wallet.history.model.WalletHistoryVO" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
    //WalletHistoryServlet.java(Controller), 存入req的walletHistoryVO物件
    WalletHistoryVO walletHistoryVO = (WalletHistoryVO) request.getAttribute("walletHistoryVO");
%>

<html>
<head>
    <title>錢包交易紀錄 - listOneWalletHistory.jsp</title>

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
            width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>錢包交易紀錄 - listOneWalletHistory.jsp</h3>
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
    <tr>
        <td><%=walletHistoryVO.getWalletHistoryId()%>
        </td>
        <td><%=walletHistoryVO.getChangeTime()%>
        </td>
        <td><%=walletHistoryVO.getMemberId()%>
        </td>
        <td><%=walletHistoryVO.getChangeAmount()%>
        </td>
        <td><%=walletHistoryVO.getChangeType()%>
        </td>
        <td>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/walletHistory/walletHistory.do"
                  style="margin-bottom: 0;">
                <input type="submit" value="修改">
                <input type="hidden" name="walletHistoryId" value="${walletHistoryVO.getWalletHistoryId()}">
                <input type="hidden" name="action" value="getOne_For_Update"></FORM>
        </td>
    </tr>
</table>

</body>
</html>