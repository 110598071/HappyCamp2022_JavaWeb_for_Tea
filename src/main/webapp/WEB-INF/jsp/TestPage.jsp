<%@ page import="java.util.ArrayList" %>
<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%!
    String getContent() {
        return "Scriptlet element";
    }
%>

<%
    String localVariable = getContent();
%>

<%
    List<User> users = new ArrayList();
    User user1 = new User();
    user1.setAccount("user1");

    User user2 = new User();
    user2.setAccount("user2");

    users.add(user1);
    users.add(user2);

    request.setAttribute("users", users);
%>

<jsp:useBean id="user" class="Entity.User" scope="page" />
<jsp:setProperty name="user" property="account" value="testAccount" />

<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/TestPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
<%--  兩種include方式可依情境選擇使用  --%>
    <jsp:include page="/WEB-INF/jsp/InitialTitle.jsp" />
    <%@include file="/WEB-INF/jsp/InitialTitle.jsp"%>

    <div class="innerContainer">
    <%--  scriptlet element 輸出java宣告的變數  --%>
        <div>
            scriptlet element local variable:
            <%= localVariable %>
            <input value="${localVariable}">
        </div>

    <%--  action element useBean 定義的物件可用三種方式輸出至頁面  --%>
        <div>
            action element useBean:
            <br>
            scriptlet element:
            <%= user.getAccount()%>
            <br>
            action element:
            <jsp:getProperty name="user" property="account"/>
            <br>
            EL:
            ${user.account}
        </div>

    <%--  EL可取得request或session中傳遞的資料  --%>
        <div>
            EL get request or session attribute:
            <br>
            Request attribute:
            ${requestAttribute}
            <br>
            Session attribute:
            ${sessionAttribute}
        </div>

    <%--  EL可以取得某些隱藏物件的資料  --%>
        <div>
            EL get hidden data:
            <br>
            header.host:
            ${header.host}
            <br>
            pageContext.request.remoteAddr:
            ${pageContext.request.remoteAddr}
        </div>

    <%--  EL也可用於運算或邏輯判斷  --%>
        <div>
            EL numerical and logical operation:
            <br>
            1+10 = ${1+10}
            <br>
            User.account == "testAccount" : ${user.account == "testAccount"}
            <br>
            empty User.account: ${empty user.account}
            <br>
            empty User.password: ${empty user.password}
        </div>

    <%--  comparison between scriptlet & EL in getting request attribute  --%>
        <div>
            <%= request.getAttribute("requestAttribute").toString() %>
            <br>
            ${requestAttribute}
        </div>

    <%--  JSTL out  --%>
        <div>
            c:out user.account:
            <c:out value="${user.account}"/>
            <br>
            c:out user.password:
            <c:out value="${user.password}" default="default password" />
        </div>

    <%--  comparison between scriptlet & JSTL+EL if  --%>
        <div>
            <% if (request.getAttribute("requestAttribute").equals("request attribute successfully obtained")) {%>
            scriptlet if
            <% } %>
            <br>
            <c:if test="${requestAttribute == 'request attribute successfully obtained'}">
                JSTL if
            </c:if>
        </div>

    <%--  JSTL choose & when & otherwise  --%>
        <div>
            <c:choose>
                <c:when test="${requestAttribute == 'A'}">
                    A
                </c:when>
                <c:otherwise>
                    otherwise
                </c:otherwise>
            </c:choose>
        </div>

    <%--  comparison between scriptlet & JSTL for loop  --%>
        <div>
            <c:forEach var="i" begin="0" end="10" step="1">
                <span>${ i }</span>
            </c:forEach>

            <br>

            <%
                for(int i=0; i <= 10; i++)
                {
            %>
            <span><%= i %></span>
            <%
                }
            %>
        </div>

    <%--  comparison between scriptlet & JSTL object array for loop  --%>
        <div>
            <c:forEach var="JstlForUser" items="${users}">
                user account: ${JstlForUser.account}
                <br>
            </c:forEach>

            <%for(User scriptletForUser: users)
            {
            %>
            user account: <%= scriptletForUser.getAccount()%>
            <br>
            <%
            }
            %>
        </div>
    </div>
</div>
</body>
</html>