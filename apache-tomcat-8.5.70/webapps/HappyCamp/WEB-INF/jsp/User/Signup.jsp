<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/LoginSignup.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/Signup.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/InitialTitle.jsp" />

        <div class="loginDiv">
            <table>
                <col style="width: 30%">
                <col style="width: 70%">
                <tr class="tableTitleTr">
                    <td colspan="2" class="tableTitleTd">
                        Signup
                    </td>
                </tr>
                <tr>
                    <td class="firstColumn">
                        Account
                    </td>
                    <td class="secondColumn">
                        <input class="tableInput" id="account" placeholder="Account" maxlength="20" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <td class="firstColumn">
                        Password
                    </td>
                    <td class="secondColumn">
                        <input class="tableInput" type="password" id="password" placeholder="Password" maxlength="20">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="buttonColumn">
                        <button class="tableButton" type="button" onclick="returnToLoginPage()">Return</button>
                        <button class="tableButton" type="button" onclick="signup()">Signup</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>