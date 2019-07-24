function findPage(num){
    $("#pageNo").val(num);
    document.formljj.submit();
}

$(".addMenu").click(function(){
    layer.open({
        type: 2,
        title: '添加菜单管理',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../JumpSaveMenu.do"
    });
});

$(".operation .btn-success").click(function(){
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '修改菜单管理',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../JumpUpdateMenu.do?id="+id
    });
});

$(".operation .btn-white").click(function() {
    var id = $(this).attr("val");
    layer.confirm("是否删除菜单管理?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../updateMenu.do",
            data:{id:id ,isDele:1},
            success: function (result) {
                if(result.code==1){
                    location.reload();
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });
});

$(function () {
    $("#addMenua").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../addMenu.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../menulist.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });

    $("#updateMenu").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../updateMenu.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../menulist.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });
});