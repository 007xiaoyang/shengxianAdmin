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


//跳转到添加系统公告页面
$(".addSystemBulletin").click(function(){

    layer.open({
        type:2,
        title: '添加系统公告',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpAddSystemBulletin.do",
    });
});

//修改管理员验证码
$(".UpdateSystemBulletin").click(function(){
    var id = $(this).attr("val");
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(id);
        $(".sendSMS").val("666888");
        $('#myModal').modal('show');
        /*$.ajax({
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
        });*/
    });
});

//跳转到修改系统公告页面
$("#myModal").click(function(){
    var id = $(".uid").val();
    var  sendSMS = $(".sendSMS").val();//短信验证码
    var  send = $(".sendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    }
    layer.open({
        type:2,
        title: '修改系统公告',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpUpdateSystemBulletin.do?id="+id,
    });

});



//修改
$("#UpdateSystemBulletin").click(function(){
    var id = $("#noticeid").val();
    var noticeContent =$("#content").val();
    $.ajax({
        type:"POST",
        url: "../homePage/UpdateSystemBulletin.do",
        data:{id:id ,noticeContent:noticeContent},
        success: function (result) {
            if(result.code==1){
                toastr.success(result.info);
                setTimeout(function(){
                    parent.location.href = "../homePage/JumpSystemBulletin.do";
                    parent.layer.closeAll();
                }, 2000);
            }else{
                toastr.error(result.info);
            }
        }
    });
});


//var content = UE.getEditor('content').getContent();



//跳转到添加电子协议页面
$(".JumpAddAgreement").click(function(){
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '查看详情',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpAddAgreement.do",
    });
});

//跳转到修改电子协议页面
$(".JumpUpdateAgreement").click(function(){
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '查看详情',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpUpdateAgreement.do?id="+id,
    });
});



//添加
$("#addAgreement").click(function(){
    var distinguish = $("#notice") .val();//选中的值
    var noticeContent =$("#content").val();
    if(distinguish == 0){
        alert("请挑选所属哪端的电子协议")
    }else if(content == ''){
        alert("电子协议不能为空")
    }else {
        $.ajax({
            type:"POST",
            url: "../homePage/addAgreement.do",
            data:{distinguish:distinguish,noticeContent:noticeContent},
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../homePage/JumpAgreementList.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    }

});

//修改
$("#updateAgreement").click(function(){
    var id = $("#noticeid").val();
    var noticeContent =$("#content").val();
    $.ajax({
        type:"POST",
        url: "../homePage/updateAgreement.do",
        data:{id:id ,noticeContent:noticeContent},
        success: function (result) {
            if(result.code==1){
                toastr.success(result.info);
                setTimeout(function(){
                    parent.location.href = "../homePage/JumpAgreementList.do";
                    parent.layer.closeAll();
                }, 2000);
            }else{
                toastr.error(result.info);
            }
        }
    });
});


//修改
$("#UpdateSystemBulletin").click(function(){
    var id = $("#noticeid").val();
    var noticeContent =$("#content").val();
    $.ajax({
        type:"POST",
        url: "../homePage/UpdateSystemBulletin.do",
        data:{id:id ,noticeContent:noticeContent},
        success: function (result) {
            if(result.code==1){
                toastr.success(result.info);
                setTimeout(function(){
                    parent.location.href = "../homePage/JumpSystemBulletin.do";
                    parent.layer.closeAll();
                }, 2000);
            }else{
                toastr.error(result.info);
            }
        }
    });
});



$(".deleNotice").click(function() {
    var id = $(this).attr("val");
    layer.confirm("删除将无法恢复，是否确认删除?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../homePage/deleNotice.do",
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
