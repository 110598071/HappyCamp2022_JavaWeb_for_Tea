function setCategory() {
    $.ajax({
        type : 'post',
        url : 'ParagraphList',
        dataType : 'text',
        data : {
            categorySelect : $('select[name=categorySelect]').val(),
            op : 'setCategory'
        },

        success : function(data) {
            location.reload();
        }
    });
}

function addParagraph(){
    window.location.href="AddParagraph";
}

function DeleteParagraph(deleteButton){
    let deleteParagraph = confirm("Delete Paragraph?");
    if (deleteParagraph) {
        $.ajax({
            type: 'POST',
            url: 'DeleteParagraph',
            dataType: 'text',
            data:  {paragraphId: deleteButton.id},

            success: function(data){
                alert("Delete Success");
                window.location.href="ParagraphList";
            }
        });
    }
}

// function searchParagraph() {
//     $.ajax({
//         type : 'post',
//         url : 'ParagraphList',
//         dataType : 'text',
//         data : {
//             nameSearch : $('#searchFilter').val(),
//             op : 'searchParagraph'
//         },
//
//         success : function(data) {
//             location.reload();
//         }
//     });
// }

function searchParagraphByDoGet() {
    if ($('#searchFilter').val()) {
        window.location.href="ParagraphList"+"?nameSearch="+$('#searchFilter').val();
    }
    else {
        window.location.href="ParagraphList";
    }
}

function gotoCategoryPage() {
    window.location.href="CategoryList";
}