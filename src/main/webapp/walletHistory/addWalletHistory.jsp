<%@ page import="com.wallet.history.model.WalletHistoryVO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% //見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
    WalletHistoryVO walletHistoryVO = (WalletHistoryVO) request.getAttribute("walletHistoryVO");
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>錢包交易紀錄新增 - addWalletHistory.jsp</title>

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
            <h3>錢包交易紀錄新增 - addWalletHistory.jsp</h3></td>
        <td>
            <h4><a href="select_page.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<h3>資料新增:</h3>

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
            <td>會員編號:</td>
            <td><label>
                <input type="TEXT" name="memberId"
                       value="<%= (walletHistoryVO==null)? "1" : walletHistoryVO.getMemberId()%>" size="45"/>
            </label></td>
        </tr>
        <tr>
            <td>異動金額:</td>
            <td><label>
                <input type="TEXT" name="changeAmount"
                       value="<%= (walletHistoryVO==null)? "100" : walletHistoryVO.getChangeAmount()%>" size="45"/>
            </label></td>
        </tr>

        <tr>
            <td>金流種類:<span style="color: red; "><b>*</b></span></td>
            <td><label>
                <select size="1" name="changeType">
                    <c:forEach var="i" begin="0" end="5">
                        <c:set var="typeLabel">
                            <c:choose>
                                <c:when test="${i eq 0}">付款</c:when>
                                <c:when test="${i eq 1}">儲值</c:when>
                                <c:when test="${i eq 2}">提領</c:when>
                                <c:when test="${i eq 3}">退款</c:when>
                                <c:when test="${i eq 4}">入帳</c:when>
                                <c:when test="${i eq 5}">手續費</c:when>
                            </c:choose>
                        </c:set>
                        <option value="${i}" ${(i==0)? 'selected':''}>${typeLabel}</option>
                    </c:forEach>
                </select>
            </label></td>
        </tr>


        <%--        <jsp:useBean id="walletHistorySvc" scope="page" class="com.wallet.history.model.WalletHistoryService"/>--%>

    </table>
    <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增"></FORM>

</body>
</html>