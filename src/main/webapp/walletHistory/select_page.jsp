<%@ page import="com.wallet.history.model.WalletHistoryService" %>
<%@ page import="com.wallet.history.model.WalletHistoryVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    WalletHistoryService walletHistorySvc = new WalletHistoryService();
    List<WalletHistoryVO> list = walletHistorySvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>CIA10126-Webapp WalletHistory: Home</title>

    <style>
        table#table-1 {
            width: 450px;
            background-color: #CCCCFF;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 3px ridge Gray;
            height: 80px;
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

</head>
<body>

<table id="table-1">
    <tr>
        <td><h3>CIA10126-Webapp WalletHistory: Home</h3><h4>( MVC )</h4></td>
    </tr>
</table>

<p>This is the Home page for CIA10126-Webapp WalletHistory: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <p style="color:red">請修正以下錯誤:</p>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='listAllWalletHistory.jsp'>List</a> all WalletHistories. <br><br></li>

    <li>
        <FORM METHOD="post" ACTION="walletHistory.do">
            <b>選擇交易編號:</b>
            <label>
                <select size="1" name="walletHistoryId">
                    <c:forEach var="walletHistoryVO" items="${list}">
                    <option value="${walletHistoryVO.walletHistoryId}">${walletHistoryVO.walletHistoryId}
                        </c:forEach>
                </select>
            </label>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="walletHistory.do">
            <b>輸入交易編號:</b>
            <label>
                <input type="text" name="walletHistoryId">
            </label>
            <input type="hidden" name="action" value="getOne_For_Display">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <%--    <jsp:useBean id="walletHistorySvc" scope="page" class="com.wallet.history.model.WalletHistoryService"/>--%>


    <%--    <li>--%>
    <%--        <FORM METHOD="post" ACTION="walletHistory.do">--%>
    <%--            <b>選擇會員編號:</b>--%>
    <%--            <label>--%>
    <%--                <select size="1" name="walletHistoryId">--%>
    <%--                    <c:forEach var="walletHistoryVO" items="${list}">--%>
    <%--                    <option value="${walletHistoryVO.walletHistoryId}">${walletHistoryVO.memberId}--%>
    <%--                        </c:forEach>--%>
    <%--                </select>--%>
    <%--            </label>--%>
    <%--            <input type="hidden" name="action" value="getOne_For_Display">--%>
    <%--            <input type="submit" value="送出">--%>
    <%--        </FORM>--%>
    <%--    </li>--%>
</ul>


<h3>交易紀錄管理</h3>

<ul>
    <li><a href='addWalletHistory.jsp'>Add</a> a new WalletHistory.</li>
</ul>

</body>
</html>