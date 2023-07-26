function DeleteCategory(deleteButton){
    let deleteCategory = confirm("Delete Category?\nThis action will cause related Paragraph to be deleted together");
    if (deleteCategory) {
        $.ajax({
            type: 'POST',
            url: 'CategoryList',
            dataType: 'text',
            data:  {
                categoryId: deleteButton.id,
                op: 'deleteCategory',
            },

            success: function(data){
                alert("Delete Success");
                location.reload();
            }
        });
    }
}

function returnToParagraphPage() {
    window.location.href="ParagraphList";
}

function addCategory() {
    if ($('#categoryName').val()) {
        $.ajax({
            type: 'POST',
            url: 'CategoryList',
            dataType: 'text',
            data: {
                categoryName: document.getElementById("categoryName").value,
                op: 'addCategory',
            },

            success: function(data){
                alert("Add Success");
                window.location.href="CategoryList";
            }
        });
    }
    else {
        alert("請先輸入Category Name");
    }
}