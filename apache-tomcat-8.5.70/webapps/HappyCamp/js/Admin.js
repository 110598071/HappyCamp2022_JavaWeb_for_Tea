function addUser() {
    if ($('#account').val() && $('#password').val()) {
        $.ajax({
            type: 'post',
            url: 'Admin',
            dataType: 'text',
            data: {
                account: document.getElementById("account").value,
                password: document.getElementById("password").value,
                role: document.getElementById("role").value,
                op: "addUser",
            },
            success: function (data) {
                alert("Add success!");
                location.reload();
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

function returnToParagraphList() {
    window.location.href = "ParagraphList";
}

function deleteUser(deleteButton) {
    let deleteUser = confirm("Delete User?");
    if (deleteUser) {
        $.ajax({
            type: 'POST',
            url: 'Admin',
            dataType: 'text',
            data:  {
                userId: deleteButton.id,
                op: 'deleteUser',
            },

            success: function(data){
                alert("Delete Success");
                location.reload();
            }
        });
    }
}