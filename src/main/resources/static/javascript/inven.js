
$(function() {

    $(document).ready(function() {
        // 구매 버튼 클릭 시
        $("#buyBtn").on("click", function () {
            $("#buyModal").modal("show");
        });

        // 판매 버튼 클릭 시
        $("#sellBtn").on("click", function() {
            $("#sellModal").modal("show");
        });

        // 수정 버튼 클릭 시
        $("#editBtn").on("click", function() {
            $("#editModal").modal("show");
        });

        // 제품 코드 클릭 시 옵션 표시
        $("#productCode").on("click", function() {
            $("#productOptions").toggle();
        });
    });

});