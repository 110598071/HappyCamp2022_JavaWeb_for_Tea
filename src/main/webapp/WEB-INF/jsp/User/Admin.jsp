<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/Admin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/Admin.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/IncludeTitle.jsp" />

        <div class="actionContainer">
            <input id="account" placeholder="Account" maxlength="20" autocomplete="off">
            <input id="password" placeholder="Password" maxlength="20" autocomplete="off">
            <select id="role">
                <option value="1">Administrator</option>
                <option value="2">User</option>
            </select>
            <button class="addUserButton" type="button" onclick="addUser()">Add</button>
            <button type="button" onclick="returnToParagraphList()">Return</button>
        </div>

        <div class="userListContainer">
            <table class="userListTable">
                <col style="width: 19%">
                <col style="width: 28%">
                <col style="width: 28%">
                <col style="width: 25%">
                <tr class="userListTableFirstRow">
                    <td>
                        Role
                    </td>
                    <td>
                        Account
                    </td>
                    <td>
                        Password
                    </td>
                    <td class="deleteTd">
                        Delete
                    </td>
                </tr>
                <c:if test="${not empty users}">
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>
                                <c:if test="${user.role == 1}">
                                    Administrator
                                </c:if>
                                <c:if test="${user.role == 2}">
                                    User
                                </c:if>
                            </td>
                            <td>
                                ${user.account}
                            </td>
                            <td>
                                ${user.password}
                            </td>
                            <td class="deleteTd">
                                <button id="delete${user.id}" type="button" onclick="deleteUser(this)">DELETE</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>