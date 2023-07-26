<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<c:if test="${not empty errorMsg}">
    <script>
        alert("${errorMsg}");
    </script>
</c:if>

<c:if test="${empty errorMsg}">
    <script>
        alert("wrong url!");
    </script>
</c:if>

<script>
    $.ajax({
        type : 'post',
        url : 'Logout',
        dataType : 'text',

        success : function(data) {
            window.location.href="Login";
        }
    });
</script>