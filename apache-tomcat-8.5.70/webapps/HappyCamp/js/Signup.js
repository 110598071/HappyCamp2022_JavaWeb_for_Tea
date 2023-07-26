function signup() {
    if ($('#account').val() && $('#password').val()) {
        $.ajax({
            type: 'post',
            url: 'Signup',
            dataType: 'text',
            data: {
                account: document.getElementById("account").value,
                password: document.getElementById("password").value,
            },
            success: function (data) {
                alert("Signup success!");
                window.location.href = "Login";
            },
            error: function (data) {
                alert("Duplicate Account!");
                location.reload();
            }
        });
    }
    else {
        alert("Please enter Account and Password");
    }
}

function returnToLoginPage() {
    window.location.href = "Login";
}