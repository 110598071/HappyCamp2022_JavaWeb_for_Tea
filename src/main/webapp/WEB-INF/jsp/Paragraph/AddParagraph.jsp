<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/AddModifyParagraph.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/AddModifyParagraph.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/IncludeTitle.jsp" />

        <div class="titleDiv">Add Paragraph</div>
        <table class="formTable">
            <col style="width: 20%">
            <col style="width: 80%">
            <tr>
                <td class="firstCol">
                    Title
                </td>
                <td>
                    <input id="title" maxlength="25">
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Date
                </td>
                <td>
                    <input id="date" type="date">
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Category
                </td>
                <td>
                    <select id="category">
                        <c:if test="${not empty categoryList}">
                            <c:forEach var="category" items="${categoryList}">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Content
                </td>
                <td>
                    <textarea id="text" maxlength="150"></textarea>
                </td>
            </tr>
        </table>
        <div class="buttonDiv">
            <button type="button" onclick="ReturnToParagraphList()">RETURN</button>
            <button type="button" onclick="AddParagraph()">ADD</button>
        </div>
    </div>
</body>
</html>