function findPage(num){
    $("#pageNo").val(num);
    document.formljj.submit();
}

$(".adds").click(function(){
    layer.open({
        type: 2,
        title: '添加角色',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content: "../adminRole/jumpSaveAdminRole.do"
    });
});

//获取修改权限验证码
$(".updateRole").click(function(){
    var id = $(this).attr("val");
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(id);
       /* $(".sendSMS").val("666888");
        $('#myModal').modal('show');*/
        $.ajax({
            url:"../seller/sendSms.do",
            type:"post",
            data:{phone:phone},
            success:function (res) {
                if (res.code == 1){
                    layer.close(d);
                    $(".sendSMS").val(res.data);
                    $('#myModal').modal('show');
                }else {
                    toastr.error("获取验证码失败");
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    });
});
//跳转到修改权限页面
$(".confirm").on("click",function () {
    var id = $(".uid").val();
    var  sendSMS = $(".sendSMS").val();//短信验证码
    var  send = $(".sendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    }
    layer.open({
        type: 2,
        title: '修改权限',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../adminRole/findAdminRoleById.do?id="+id
    });
});


$(".operation .btn-white").click(function() {
    var id = $(this).attr("val");
    layer.confirm("删除该角色将无法恢复,是否确认?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../adminRole/deleteAdminRole.do",
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

$(function () {
    $("#addAdminRole").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../adminRole/addAdminRole.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../adminRole/findAdminRoleByPage.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });

    $("#updateAdminRole").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../adminRole/updateAdminRole.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../adminRole/findAdminRoleByPage.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });
});