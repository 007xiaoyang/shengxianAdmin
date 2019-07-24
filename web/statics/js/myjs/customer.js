//客户页面数据渲染
function find(pageNo,phone) {
    $.ajax({
        url:"../customer/customerList.do" ,
        type:"post",
        data:{pageNo:pageNo,phone:phone},
        beforeSend:function(){
            $(".customer").append("<td class='xuan'>loading...</td>");//显示加载动画
        },
        success:function (ret) {

            if (ret.code == 1){
                var custromer = ret.data.records;
                total = ret.data.totalRecordsNum; //总记录数
                pageSize = ret.data.pageSize; //每页显示条数
                curPage = ret.data.pageNum; //当前页
                totalPage = ret.data.totalPageNum; //总页数
                var html ='';
                for ( var i=0; i < custromer.length;i++){

                    var is_disable = "";
                    if(custromer[i].is_disable == 0){
                        is_disable ='<button class="btn btn-success btn-xs enable " type="button" val="'+custromer[i].id+'">' +
                                    '<i class="fa fa-check"></i>' +
                                    '<span class="bold" value="">已启用</span>' +
                                    '</button>'
                    }else {
                        is_disable ='<button class="btn btn-danger btn-xs disable " type="button" val="'+custromer[i].id+'">' +
                            '<i class="fa fa-remove"></i>' +
                            '<span class="bold" value="">已禁用</span>'+
                            '</button>'
                    }

                    html += '<tr>' +
                                '<td class="xuan">' +
                                '<input id="checkbox3" type="radio" name="id" value="'+custromer[i].id+'">' +
                                '</td>' +
                                '<td>'+custromer[i].id+' </td>'+
                                '<td>'+custromer[i].phone+'</td>' +
                                '<td>'+is_disable+'</td>' +
                                '<td>'+timestampToTime(custromer[i].cate_time)+'</td>'+
                                '<td class="operation">' +
                                    '<button class="btn btn-success btn-sm detail" style="margin-right: 5px" type="button" val="'+custromer[i].id+'">' +
                                    '<i class="fa fa-edit"></i>' +
                                        '<span class="bold">详情</span>' +
                                    '</button>'+
                                    '<button class="btn btn-warning btn-sm push"  style="margin-right: 5px" type="button" val="'+custromer[i].id+'">' +
                                    '<i class="fa fa-trash-o"></i>' +
                                    '<span class="bold">发送通知</span>' +
                                    '</button>'+
                                    '<button class="btn btn-danger btn-sm detele"  style="margin-right: 5px" type="button" val="'+custromer[i].id+'">' +
                                    '<i class="fa fa-trash-o"></i>' +
                                    '<span class="bold">删除</span>' +
                                    '</button>'+
                                '</td>'+
                            '</tr>'
                }
                $(".customer").empty().append(html);

                //详情 根据当前id查询此账号绑定的所有商家资料
                $(".operation .detail").on("click",function () {
                    var id = $(this).attr("val");
                    layer.open({
                        type: 2,
                        title: '客户绑定商家列表',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['100%', '100%'],
                        content:"../customer/jumpCustomerBindingBusiness.do?id="+id,
                    });
                });

                //重置客户密码
                $(".resetPassword").click(function(){
                    var val = $('input:radio[name="id"]:checked').val();
                    if(val==undefined | val == 'on'){
                        alert("请您勾选要重置密码的商家");
                    }else {
                        layer.confirm("是否确认重置密码?默认密码123456",{
                            btn:["确认","取消"]
                        },function(){
                            $.ajax({
                                type:"POST",
                                url:"../customer/resetPassword.do",
                                data:{id:val},
                                success:function (result) {
                                    debugger
                                    if(result.code==1){
                                        toastr.success(result.info);
                                        setTimeout(function(){
                                            location.reload();
                                        }, 1000);
                                    }else {
                                        toastr.error(result.info);
                                    }
                                },
                                error:function (res) {
                                    alert(1)
                                    toastr.error(res.info)
                                }
                            });
                        });
                    };
                });

            }else {
                toastr.error(ret.info);
            }
        },
        complete:function(){ //生成分页条
            getPageBar();
            fun();
        },
        error:function (res) {
            toastr.error(res.info());
        }

    });
};

//跳转到删除客户窗口
$(document).on("click",".detele",function () {
    var cid = $(this).attr("val");
    var phone ="17689704036";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(cid);
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

//确认验证码是否正确
$(document).on("click",".deleteUser",function () {
    var id =  $(".uid").val();
    var  sendSMS = $(".sendSMS").val();//短信验证码
    var  send = $(".sendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    };
    layer.confirm("删除客户将无法恢复,确认删除?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../customer/deleteCustomer.do",
            data:{id:id},
            success: function (result) {
                if(result.code==1){
                    location.reload();
                }else{
                    toastr.error(result.info);
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    });
});

//禁用
$(document).on("click",".enable",function () {
    var id = $(this).attr("val");
    layer.confirm("是否禁用商家?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../customer/updateDisable.do",
            data:{id:id,is_disable:1},
            success:function (result) {
                if(result.code==1){
                    location.reload();

                }else {
                    toastr.error(result.info);
                }
            }
        });
    });
});


//启用
$(document).on("click",".disable",function () {
    var id = $(this).attr("val");
    layer.confirm("是否启用商家?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../customer/updateDisable.do",
            data:{id:id,is_disable:0},
            success:function (result) {
                if(result.code==1){
                    location.reload();

                }else {
                    toastr.error(result.info);
                }
            }
        });
    });
});

//多人登录
$(document).on("click",".single",function () {
    var id = $(this).attr("val");
    layer.confirm("是否给多人同时登录权限?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../customer/updatePower.do",
            data:{id:id,power:1},
            success:function (result) {
                if(result.code==1){
                    location.reload();

                }else {
                    toastr.error(result.info);
                }
            }
        });
    });
});


//单人登录
$(document).on("click",".many",function () {
    var id = $(this).attr("val");
    layer.confirm("是否取消多人同时登录权限?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../customer/updatePower.do",
            data:{id:id,power:0},
            success:function (result) {
                if(result.code==1){
                    location.reload();

                }else {
                    toastr.error(result.info);
                }
            }
        });
    });
});



//平台发送消息
$(document).on("click",".push",function () {
    var id=$(this).attr("val");
    $(".wxid").val(id);
    $(".reason").val("");
    $(".xsdj").show();
    $(".mask").show();
});

//确认推送消息给客户
$("#CBor").click(function () {
    var push_id =  $(".wxid").val();
    var  title = $(".title").val();
    var reason = $(".reason").val();
    if (title == ""){
        alert("请输入标题！");
        return;
    }
    if (reason == ""){
        alert("请输入内容！");
        return;
    }
    $.ajax({
        url:"../customer/addPushContent.do",
        type: "POST",
        data:{push_id:push_id,title:title,content:reason,type:1},
        success:function (res) {
            if(res.code == "1"){
                toastr.success(res.info);
                location.reload();
            }else {
                toastr.error(res.info)
            }
        },
        error:function (res) {
            toastr.error(res.info);
        }
    });
});
// 取消
$("#CQuxiao").on("click",function () {
    $(".xsdj").hide();
    $(".mask").hide();
});


//客户绑定商家页面数据渲染
function findUserPhone() {
    var uid = $("#user_id").val();
    $.ajax({
        url:"../customer/selectUserPhone.do",
        type:"post",
        data:{id:uid},
        success:function (res) {
            if (res.code == "1"){
                var userHtml = '<div class="col-sm-3 m-b-xs">' +
                                    '<div class="input-group">' +
                                        '<span class="input-group-btn">'+
                                            '<button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>当前客户账号：</button>' +
                                        '</span>'+
                                        '<input  type="text" class="input-sm form-control _phone" style="color: #880000" name="user_phone" disabled value="'+res.data+'"/>'+
                                    '</div>'+
                                '</div>'

                $(".userPhone").empty().append(userHtml);
            }else {
                toastr.error(res.info);
            }
        }
    });
}

//客户绑定商家数据渲染
function userBusiness(pageNo,name) {
    var uid = $("#user_id").val();
    $.ajax({
        url:"../customer/customerBindingBusiness.do" ,
        type:"post",
        data:{pageNo:pageNo,id:uid,name:name},
        beforeSend:function(){
            $(".userDetail").append("<td class='xuan'>加载中...</td>");//显示加载动画
        },
        success:function (ret) {
            if (ret.code == 1){
                var userBusienss = ret.data.records;
                total = ret.data.totalRecordsNum; //总记录数
                pageSize = ret.data.pageSize; //每页显示条数
                curPage = ret.data.pageNum; //当前页
                totalPage = ret.data.totalPageNum; //总页数
                var businesHtml ='';
                var name="";
                for ( var i=0; i < userBusienss.length;i++){
                    if (userBusienss[i].name == null){
                        name = "";
                    }else {
                        name = userBusienss[i].name;
                    }

                    businesHtml += '<tr>' +
                        '<td class="xuan">' +
                        '<input id="checkbox3" type="radio" name="check" value="'+userBusienss[i].id+'">' +
                        '</td>' +
                        '<td>'+userBusienss[i].phone+'</td>' +
                        '<td>'+userBusienss[i].store_name+'</td>' +
                        '<td>'+userBusienss[i].number+'</td>' +
                        '<td>'+name+'</td>' +
                        '<td>'+timestampToTime(userBusienss[i].binding_time)+'</td>'+
                    '</tr>'
                }
                $(".userDetail").empty().append(businesHtml);

            }else {
                toastr.error(ret.info);
            }
        },
        complete:function(){ //生成分页条
            getPageBar();
            fun();
        },
    });
}

//跳转到添加客户页面
$(".addCustomer").click(function(){
    layer.open({
        type: 2,
        title: '添加客户账号',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../customer/jumpAddCustomer.do",
    });
});

//添加客户
$("#addCustomer").click(function(){
    var pattern = /^\d{11}$/;
    var phone = $("#phone").val();
    var pwd = $("#pwd").val();
    var pwd2 = $("#pwd2").val();
    if(phone == ""){
        alert("账号不能为空");
        return;
    }
    if(phone.length != 11 && !(pattern.test(phone))){
        alert("账号只能是11位数");
        return;
    }

    if (pwd != pwd2){
        alert("两次输入的密码不正确");
        return;
    }
    $.ajax({
        url:"../customer/addCustomer.do",
        type:"post",
        data:{phone:phone,password:pwd},
        success:function(result){
            if(result.code==1){
                toastr.success(result.info);
                setTimeout(function(){
                    parent.location.href = "../customer/JumpNewCustomerList.do";
                    parent.layer.closeAll();
                }, 2000);
            }else{
                toastr.error(result.info);
            }
        },
        error:function (res) {
            alert("dsd")
        }
    });
});





















//多人登录权限设置
$(".single").click(function(){
    var id = $(this).attr("val");
    if(id==undefined ){
        alert("请您勾选要设置登录权限的商家账号");
    }else {
        layer.confirm("是否确认设置为多人登录?",{
            btn:["确认","取消"]
        },function(){
            $.ajax({
                type:"POST",
                url:"../customer/singleLoginJurisdiction.do",
                data:{id:id,power:1},
                success:function (result) {
                    if(result.code==1){
                        location.reload();

                    }else {
                        toastr.error(result.info);
                    }
                }
            })
        })
    }
});

//单人登录权限设置
$(".many").click(function(){
    var id = $(this).attr("val");
    layer.confirm("是否确认设置为单人登录?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../customer/manyLoginJurisdiction.do",
            data:{id:id,power:0},
            success:function (result) {
                if(result.code==1){
                    location.reload();

                }else {
                    toastr.error(result.info);
                }
            }
        })
    });
});

//通过binding绑定表id查询客户在每一家的信息详情
$(".bDetails").click(function () {
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '客户绑定商家列表',
        shadeClose: true,
        shade: 0.8,
        area: ['50%', '100%'],
        content:"../customer/bindingDetailsInfo.do?id="+id,
    });
});




$(function(){
    $("#all").change(function(){//判断全选框的改变
        var flage =$(this).is(":checked");//全选选中为true，否则为false
        $("input[type=checkbox]").each(function(){
            $(this).prop("checked",flage);
        });
    });
});


