<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/CategoryList.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/CategoryList.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/IncludeTitle.jsp" />

        <div class="actionContainer">
            <c:if test="${role == 1}">
                <input id="categoryName" placeholder="Category Name" maxlength="20">
                <button class="addCategoryButton" type="button" onclick="addCategory()">Add</button>
            </c:if>
            <button type="button" onclick="returnToParagraphPage()">Return</button>
        </div>

        <div class="categoryListContainer">
            <table class="categoryListTable">
                <col style="width: 35%">
                <col style="width: 40%">
                <c:if test="${role == 1}">
                    <col style="width: 25%">
                </c:if>

                <tr class="categoryListTableFirstRow">
                    <td>
                        Category Name
                    </td>
                    <c:if test="${role == 1}">
                        <td class="amountTd">
                            Amount of Paragraph<br>(All User)
                        </td>
                        <td class="deleteTd">
                            Delete
                        </td>
                    </c:if>
                    <c:if test="${role == 2}">
                        <td class="amountTd">
                            Amount of Paragraph
                        </td>
                    </c:if>
                </tr>
                <c:if test="${not empty categoryWithParagraphAmountList}">
                    <c:forEach var="category" items="${categoryWithParagraphAmountList}">
                        <tr>
                            <td>
                                    ${category.name}
                            </td>
                            <td class="amountTd">
                                    ${category.amountOfParagraph}
                            </td>
                            <c:if test="${role == 1}">
                                <td class="deleteTd">
                                    <button id="delete${category.id}" type="button" onclick="DeleteCategory(this)">DELETE</button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>