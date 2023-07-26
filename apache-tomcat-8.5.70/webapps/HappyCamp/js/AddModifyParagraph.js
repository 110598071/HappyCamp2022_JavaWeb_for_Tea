function AddParagraph(){
    if ($('#title').val() && $('#date').val() && $('#text').val()) {
        $.ajax({
            type: 'POST',
            url: 'AddParagraph',
            dataType: 'text',
            data: InputToData(),

            success: function(data){
                alert("Add Success");
                window.location.href="ParagraphList";
            }
        });
    }
    else {
        alert("尚未填寫完成");
    }
}

function ModifyParagraph(){
    $.ajax({
        type: 'POST',
        url: 'ModifyParagraph',
        dataType: 'text',
        data: InputToData(),

        success: function(data){
            alert("Modify Success");
            window.location.href="ParagraphList";
        }
    });
}

function InputToData(){
    let data = {
        title: document.getElementById("title").value,
        date: document.getElementById("date").value,
        text: document.getElementById("text").value,
        categoryId: document.getElementById("category").value,
    };
    console.log("data:", data);
    return data;
}

function ReturnToParagraphList(){
    window.location.href="ParagraphList";
}