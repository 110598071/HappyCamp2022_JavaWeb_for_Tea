function logout() {
    $.ajax({
        type : 'post',
        url : 'Logout',
        dataType : 'text',

        success : function(data) {
            window.location.href="Login";
        }
    });
}