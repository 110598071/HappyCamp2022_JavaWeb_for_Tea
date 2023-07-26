<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/ParagraphList.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/ParagraphList.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/IncludeTitle.jsp" />

        <div class="actionContainer">
            <table class="actionTable">
                <col style="width: 40%">
                <col style="width: 60%">
                <tr>
                    <td class="categoryFilterTd">
                        <span>
                            <button type="button" onclick="gotoCategoryPage()">Category</button>
                        </span>
                        <select name="categorySelect">
                            <c:if test="${categoryId == 0}">
                                <option value="0" selected="selected">ALL</option>
                            </c:if>
                            <c:if test="${categoryId != 0}">
                                <option value="0">ALL</option>
                            </c:if>
                            <c:if test="${not empty categoryList}">
                                <c:forEach var="category" items="${categoryList}">
                                    <c:if test="${category.id == categoryId}">
                                        <option selected="selected" value="${category.id}">${category.name}</option>
                                    </c:if>
                                    <c:if test="${category.id != categoryId}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                    <td class="addParagraphAndSearchFilter">
                        <input name="searchFilter" type="text" id="searchFilter" placeholder="Search For Paragraph">
                        <button type="button" onclick="searchParagraphByDoGet()">SEARCH</button>
                        <button type="button" onclick="addParagraph()">ADD</button>
                    </td>
                </tr>
            </table>
        </div>

        <c:if test="${not empty nameSearch}">
            <div class="nameSearchDiv">
                Search for Paragraph: <span>${nameSearch}</span>
            </div>
        </c:if>

        <div class="paragraphListContainer">
            <table class="paragraphListTable">
                <col style="width: 18%">
                <col style="width: 20%">
                <col style="width: 32%">
                <col style="width: 15%">
                <col style="width: 15%">

                <tr class="paragraphFirstRow">
                    <td>
                        Date
                    </td>
                    <td>
                        Category
                    </td>
                    <td>
                        Title
                    </td>
                </tr>
                <c:if test="${not empty paragraphList}">
                    <c:forEach var="paragraph" items="${paragraphList}">
                        <tr class="paragraphRow">
                            <td class="dateTd">
                                ${paragraph.date}
                            </td>
                            <td class="categoryTd">
                                ${paragraph.category.name}
                            </td>
                            <td class="titleTd">
                                <form action="ParagraphList" method="post">
                                    <input type="submit" name="edit" value="${paragraph.title}">
                                    <input type="hidden" name="paragraphId" value="${paragraph.id}">
                                    <input type="hidden" name="op" value="gotoParagraphPage">
                                </form>
                            </td>
                            <td class="modifyButton">
                                <form action="ParagraphList" method="post">
                                    <input type="submit" name="edit" value="MODIFY">
                                    <input type="hidden" name="paragraphId" value="${paragraph.id}">
                                    <input type="hidden" name="op" value="modify">
                                </form>
                            </td>
                            <td class="deleteButton">
                                <button id="delete${paragraph.id}" type="button" onclick="DeleteParagraph(this)">DELETE</button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty paragraphList}">
                    <tr class="NotFound">
                        <td colspan="3">Paragraph Not Found</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</body>
<script>
    $('select[name=categorySelect]').change(setCategory);
</script>
</html>