
function submitForm(){

    var checkDay = [];
    $(".dayBt:checked").each(function(){
        checkDay.push($(this).val());
    });

    $("#designerDto_free").val(checkDay);
    $("#designerForm").submit();

}