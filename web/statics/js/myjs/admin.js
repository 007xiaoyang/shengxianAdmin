function findPage(num){
    $("#pageNo").val(num);
    document.formljj.submit();
}

//添加管理员是发送验证码
$(".adds").click(function(){
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
       /* $(".addsendSMS").val("666888");
        $('#addAdmin').modal('show');*/
        $.ajax({
            url:"../seller/sendSms.do",
            type:"post",
            data:{phone:phone},
            success:function (res) {
                if (res.code == 1){
                    layer.close(d);
                    $(".addsendSMS").val(res.data);
                    $('#addAdmin').modal('show');
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
//跳转到添加页面
$(".addAdmin").on("click",function () {
    var  sendSMS = $(".addsendSMS").val();//短信验证码
    var  send = $(".addsendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    }
    layer.open({
        type: 2,
        title: '添加管理员',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../admin/jumpSaveAdmin.do"
    });
});



//修改管理员验证码
$(".update").click(function(){
    var id = $(this).attr("val");
    var phone ="17689704036";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(id);
       /* $(".updatesendSMS").val("666888");
        $('#updateMyModal').modal('show');*/
        $.ajax({
            url:"../seller/sendSms.do",
            type:"post",
            data:{phone:phone},
            success:function (res) {
                if (res.code == 1){
                    layer.close(d);
                    $(".updatesendSMS").val(res.data);
                    $('#updateMyModal').modal('show');
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
//跳转到修改管理员页面
$(".updateMyModal").click(function(){
    var id = $(".uid").val();
    var  sendSMS = $(".updatesendSMS").val();//短信验证码
    var  send = $(".updatesendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    }
    layer.open({
        type: 2,
        title: '修改管理员',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../admin/findAdminById.do?id="+id
    });
});
//删除管理员
$(".deteleAdmind").click(function() {
    var id = $(this).attr("val");
    layer.confirm("删除管理员将无法恢复,确认删除?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../admin/deleteAdmin.do",
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

//修改时间权限
//发送验证码权限
$(".adopt").click(function(){
    var phone ="17689704036";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
      /*  $(".sendSMS").val("666888");
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
//确认修改权限
$(".confirm").on("click",function () {
    var id = $(".adopt").attr("val");//管理员id
    var jur = $(".jur").val();
    var  sendSMS = $(".sendSMS").val();//短信验证码
    var  send = $(".sendsms").val();//输入的验证码
   if(sendSMS != send){
       alert("验证码不正确！");
       return;
   }
    $.ajax({
            type:"POST",
            url:"../seller/upateDurationJurisdiction.do",
            data:{uid:id,jur:jur},
            success:function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    location.reload();
                }else {
                    toastr.error(result.info);
                }
            }
        });

});


$(function () {
    $("#saveAdmin").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../admin/saveAdmin.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../admin/findAdmin.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });

    $("#updateAdmin").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../admin/updateAdmin.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../admin/findAdmin.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });
});