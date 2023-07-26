<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/IncludeTitle.css">
<script src="js/Logout.js"></script>
<script>
    function gotoTestPage() {
        window.location.href="TestPage";
    }

    function gotoAdminPage() {
        window.location.href="Admin";
    }
</script>

<div class="logoutDiv">
    <span>${account}</span>
    <button type="button" onclick="gotoTestPage()">Test</button>
    <c:if test="${role == 1}">
        <button type="button" onclick="gotoAdminPage()">Admin</button>
    </c:if>
    <button type="button" onclick="logout()">Logout</button>
</div>
<div class="webTitle">
    <span>2022 HAPPY CAMP JAVA WEB</span>
</div>