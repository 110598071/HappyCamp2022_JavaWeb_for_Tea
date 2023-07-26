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

        <div class="titleDiv">Modify Paragraph</div>
        <table class="formTable">
            <col style="width: 20%">
            <col style="width: 80%">
            <tr>
                <td class="firstCol">
                    Title
                </td>
                <td>
                    <input id="title" value="${paragraph.title}" maxlength="25">
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Date
                </td>
                <td>
                    <input id="date" type="date" value="${paragraph.date}">
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
                                <c:if test="${category.id == paragraph.category.id}">
                                    <option selected="selected" value="${category.id}">${category.name}</option>
                                </c:if>
                                <c:if test="${category.id != paragraph.category.id}">
                                    <option value="${category.id}">${category.name}</option>
                                </c:if>
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
                    <textarea id="text" maxlength="150">${paragraph.text}</textarea>
                </td>
            </tr>
        </table>
        <div class="buttonDiv">
            <button type="button" onclick="ReturnToParagraphList()">RETURN</button>
            <button type="button" onclick="ModifyParagraph()">MODIFY</button>
        </div>
    </div>
</body>
</html>