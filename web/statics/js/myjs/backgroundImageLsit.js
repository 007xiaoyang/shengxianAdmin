function findPage(num){
    $("#pageNo").val(num);
    document.formljj.submit();
}

$(document).on("click",".imgclass",function(){
    var img = $(this).attr('src');
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '700px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: '<img src="'+img+'" style="width:100%;height:auto;"/>'
    });
});

$(".adds").click(function(){
    layer.open({
        type: 2,
        title: '添加轮播图片',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content: "../homePage/JumpAddBackgroundImage.do"
    });
});

$(".operation .btn-success").click(function(){
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '修改轮播图片',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpUpdateBackgroundImage.do?id="+id
    });
});








//删除轮播图
$(".operation .detele").click(function() {
    var id = $(this).attr("val");
    layer.confirm("删除图片将无法恢复,确认删除?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../homePage/deleBackgroundImage.do",
            data:{id:id},
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