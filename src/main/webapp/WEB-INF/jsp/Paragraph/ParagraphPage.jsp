<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" prefix="e"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
    <title>Happy Camp</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/ParagraphPage.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="js/ParagraphPage.js"></script>
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/jsp/IncludeTitle.jsp" />
        <div class="titleDiv">Paragraph Page</div>
        <table class="formTable">
            <col style="width: 30%">
            <col style="width: 70%">
            <tr>
                <td class="firstCol">
                    Title
                </td>
                <td>
                    ${paragraph.title}
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Date
                </td>
                <td>
                    ${paragraph.date}
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Category
                </td>
                <td>
                    ${paragraph.category.name}
                </td>
            </tr>
            <tr>
                <td class="firstCol">
                    Content
                </td>
                <td>
                    ${content}
                </td>
            </tr>
        </table>
        <div class="buttonDiv">
            <button type="button" onclick="ReturnToParagraphList()">RETURN</button>
        </div>
    </div>
</body>
</html>