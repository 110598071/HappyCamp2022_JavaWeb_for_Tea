<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/LoginSignup.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/Login.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/InitialTitle.jsp" />

        <div class="loginDiv">
            <form action="Login" method="post">
                <table>
                    <col style="width: 30%">
                    <col style="width: 70%">
                    <tr class="tableTitleTr">
                        <td colspan="2" class="tableTitleTd">
                            Login
                        </td>
                    </tr>
                    <tr>
                        <td class="firstColumn">
                            Account
                        </td>
                        <td class="secondColumn">
                            <input class="tableInput" name="account" placeholder="Account" required="required" maxlength="30" autocomplete="off">
                        </td>
                    </tr>
                    <tr>
                        <td class="firstColumn">
                            Password
                        </td>
                        <td class="secondColumn">
                            <input class="tableInput" type="password" name="password" placeholder="Password" required="required" maxlength="30">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="buttonColumn">
                            <button class="tableButton" type="button" onclick="gotoSignupPage()">Signup</button>
                            <input class="tableButton" type="submit" name="edit" value="Login">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="errorMsgRow">
                            ${errorMsg}
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>