$(function () {
    $("#queryBtn").click(function () {
        findByPagination(1);
    });

    findByPagination(1);
});

function findByPagination(pageNo) {
    $.ajax({
        type : "POST",
        url : ctx + "/user/findByPagination",
        dataType : "json",
        data : {
            pageNo : pageNo,
            size : 1
        },
        success : function (result) {
            if(result){
                pagination.page({
                    items : result.items,
                    itemsContainerId : "pageDataList",
                    itemsViewFormat : function (item) {
                        return "<tr>" +
                            "<td>" + item.username +"</td>" +
                            "<td>" + item.nickname +"</td>" +
                            "<td>" + item.createdTime +"</td>" +
                            "<td>" + item.lastLoginTime +"</td>" +
                            "<td>" + (item.enabled == 1 ? "正常" : "禁用") +"</td>" +
                            "</tr>";
                    },
                    total : result.total,
                    pageSize : result.pageSize,
                    pageNo : pageNo,
                    pageQueryFunName : "findByPagination"
                }).show();
            }

        },
        error : function (xmlHttpRequest, textStatus, errorThrown) {
            alert("error:" + xmlHttpRequest.responseText);
        }
    });
}