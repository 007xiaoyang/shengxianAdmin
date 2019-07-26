
//点击获取商家列表
$(".dropdown").on("click",function () {
    $.ajax({
        url:"../goods/selectBusienss.do",
        success:function (res) {
            if (res.code == 1){
                var business = res.data;
                var businessHtml = "";
                for ( var i=0; i < business.length;i++){
                    businessHtml += '<li>' +
                        '<a class="a_name">' +
                            '<input class="Bid" value="'+business[i].id+'" style="display: none"/>'+
                            '<span>'+business[i].store_name+'</span>' +
                            '<span style="padding: 2px; color: #cc0000">'+"/"+'</span>' +
                            '<span>'+business[i].number+'</span>' +
                        '</a>'+
                        '</li>'
                }
                $(".businesssName").empty().append(businessHtml)
            }else {
                toastr.error(res.info);
            }
        } ,
        error:function (res) {
            toastr.error(res.info);
        }
    });
});

//商家数据渲染
function findBuseinss(pageNo,business_id,store_name) {
    $.ajax({
       url:"../seller/sellerList.do",
       type:"post",
       data:{pageNo:pageNo,business_id:business_id,storeName:store_name},
        beforeSend:function(){
            $(".business").append("<td class='xuan'>loading...</td>");//显示加载动画
        },
        success:function (res) {
            if (res.code == 1){
                var business = res.data.records;
                total = res.data.totalRecordsNum; //总记录数
                pageSize = res.data.pageSize; //每页显示条数
                curPage = res.data.pageNum; //当前页
                totalPage = res.data.totalPageNum; //总页数
                var businessHtml ='';
                for ( var i=0; i < business.length;i++){
                    var img ="";
                    if (business[i].img == null){
                        img = '<img class="img" src="../statics/img/user.png" style="width:100%"/>'
                    }else {
                        img = '<img class="img" src="'+business[i].img+'" style="width:100%"/>'
                    }

                    var if_adopt= "";
                    if(business[i].if_adopt == 0){
                        if_adopt =  '<button class="btn btn-danger btn-xs noAdopt " type="button" val="'+business[i].id+'">' +
                                        '<span class="bold" value="">未审核</span>' +
                                    '</button>'
                    }else {
                        if_adopt =  '<button class="btn btn-success btn-xs adopt " type="button" val="'+business[i].id+'">' +
                                        '<span class="bold" value="">已审核</span>'+
                                    '</button>'
                    }

                    var is_disable = "";
                    if(business[i].is_disable == 0){
                        is_disable ='<button class="btn btn-success btn-xs enable " type="button" val="'+business[i].id+'">' +
                                        '<i class="fa fa-check"></i>' +
                                        '<span class="bold" value="">已启用</span>' +
                                    '</button>'
                    }else {
                        is_disable ='<button class="btn btn-danger btn-xs disable " type="button" val="'+business[i].id+'">' +
                                        '<i class="fa fa-remove"></i>' +
                                        '<span class="bold" value="">已禁用</span>'+
                                     '</button>'
                    }

                    var source = "";
                    if (business[i].source == 0){
                        source =    '<button class="btn btn-primary btn-xs upper " type="button" val="'+business[i].id+'">' +
                                        '<span class="bold" value="">商城</span>' +
                                    '</button>'
                    }else {
                        source =    '<button class="btn btn-warning btn-xs lower " type="button" val="'+business[i].id+'">' +
                                    '<span class="bold" value="">线下</span>' +
                                    '</button>'
                    }



                    businessHtml += ' <tr>' +
                                        '<td class="xuan">' +
                                            '<input id="checkbox3" type="radio" name="id" value="'+business[i].id+'">' +
                                        '</td>' +
                                        '<td style="width:80px;">'+ img+  '</td>' +
                                        '<td>'+business[i].phone+'</td>' +
                                        '<td style="font-weight: bold">'+business[i].store_name+'</td>' +
                                        '<td>'+business[i].number+'</td>' +
                                        '<td style="width:68px; ">'+if_adopt+'</td>'+
                                        '<td class="days" style="color: #ff0000; font-size: 15px;">'+
                                            '<span class="day">'+business[i].days+'</span>'+'<span>天</span>'+
                                        '</td>'+
                                        '<td>'+is_disable+'</td>'+
                                        '<td>'+source+'</td>'+
                                        '<td style="cursor:pointer; width: 70px; color: #1E0FBE; font-weight: bold; text-decoration: underline">'+
                                            '<span class="notice">'+"公告内容"+'</span>'+
                                            '<input class="notice1" value="'+business[i].notice+'" style="display: none"/>'+
                                        '</td>'+
                                        '<td>'+timestampToTime(business[i].create_time)+'</td>'+

                                        '<td style="margin: 20px;" class="operation">' +
                                            '<button  class="btn btn-success btn-sm update" style="margin-right: 2px" type="button" val="'+business[i].id+'">'+
                                                '<i class="fa fa-edit"></i>'+
                                                '<span class="bold">修改</span>'+
                                            '</button>'+
                                            '<button  class="btn btn-group btn-sm detail" style="margin-right: 2px" type="button" val="'+business[i].id+'">'+
                                            '<i class="fa fa-edit"></i>'+
                                            '<span class="bold">详情</span>'+
                                            '</button>'+
                                            '<button class="btn btn-primary btn-sm license" style="margin-right: 3px" type="button" val="'+business[i].id+'">'+
                                                '<i class="fa fa-edit"></i>'+
                                                '<span class="bold">营业执照</span>'+
                                            '</button>'+
                                            '<button class="btn btn-warning btn-sm sendInformation" style="margin-right: 3px" type="button" val="'+business[i].id+'">'+
                                                '<i class="fa fa-edit"></i>'+
                                                '<span class="bold">发送通知</span>'+
                                            '</button>'+
                                            '<button class="btn btn-danger btn-sm detele" style="margin-right: 3px" type="button" val="'+business[i].id+'" >'+
                                                '<i class="fa fa-trash-o"></i>'+
                                                '<span class="bold">删除</span>'+
                                            '</button>'+
                                        '</td>'
                                    '</tr>'
                }
                $(".business").empty().append(businessHtml);
            }
        },
        complete:function(){ //生成分页条
            getPageBar();
            funBuseinss();
        },
        error:function (res) {
            toastr.error(res.info);
        }
    });
};

//图片
$(document).on("click",".img",function(){
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

//修改
$(document).on("click",".update",function () {
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '修改商家信息',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../seller/JumpUpdateSeller.do?id="+id
    });
});




//跳转到删除商家窗口
$(document).on("click",".detele",function () {
    var sid = $(this).attr("val");
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(sid);
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
$(document).on("click",".deleteSeller",function () {
    var id =  $(".uid").val();
    var  sendSMS = $(".sendSMS").val();//短信验证码
    var  send = $(".sendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    };
    layer.confirm("删除服务商将无法恢复,确认删除?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../seller/deleteSeller.do",
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






//发送信息给服务商
$(document).on("click",".sendInformation",function () {
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
        data:{push_id:push_id,title:title,content:reason,type:0},
        success:function (res) {
            if(res.code == 1){
                toastr.success(res.info);
                location.reload();ssss
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


//跳转到添加商家页面
$(".addBuseinss").click(function(){
    layer.open({
        type: 2,
        title: '添加商家',
        shadeClose: true,
        shade: 0.8,
        area: ['80%', '100%'],
        content:"../seller/JumpAddSeller.do"
    });
});
//重置密码
$(".resetPassword").click(function(){
    debugger
    var id = $('input:radio[name="id"]:checked').val();
    if(id==undefined | id == 'on'){
        alert("请您勾选要重置密码的商家");
    }else {
        layer.confirm("是否确认重置密码?默认密码123456",{
            btn:["确认","取消"]
        },function(){
            $.ajax({
                type:"POST",
                url:"../seller/resetPassword.do",
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


//审核
$(document).on("click",".noAdopt",function () {
    var id = $(this).attr("val");
    layer.confirm("是否审核通过?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../seller/adopt.do",
            data:{id:id},
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
});


//禁用
$(document).on("click",".enable",function () {
    var id = $(this).attr("val");
    layer.confirm("是否禁用商家?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../seller/disableSeller.do",
            data:{id:id},
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


//启用商家
$(document).on("click",".disable",function () {
    var  days = $(this).parent("td").siblings(".days").find(".day").text();
    if (days < 0){
        alert("因没有使用期限，不能启用")
        return;
    }
    var id = $(this).attr("val");
    layer.confirm("是否启用商家?",{
        btn:["确认","取消"]
    },function(){
        $.ajax({
            type:"POST",
            url:"../seller/enableSeller.do",
            data:{id:id},
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

//商城转换线下
$(document).on("click",".upper",function () {
    var sid = $(this).attr("val");
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(sid);
        $(".sce").val(1)
      /*  $(".sourceSendSMS").val("888");
        $('#myModalSource').modal('show');*/
        $.ajax({
            url:"../seller/sendSms.do",
            type:"post",
            data:{phone:phone},
            success:function (res) {
                if (res.code == 1){
                    layer.close(d);
                    $(".sourceSendSMS").val(res.data);
                    $('#myModalSource').modal('show');
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

//线下转换商城
$(document).on("click",".lower" ,function () {
    var sid = $(this).attr("val");
    var phone ="18927429991";
    layer.confirm("是否获取手机验证码?",{
        btn:["确认","取消"]
    },function(d){
        layer.close(d);
        $(".uid").val(sid);
        $(".sce").val(0)
      /*  $(".sourceSendSMS").val("888");
        $('#myModalSource').modal('show');*/
        $.ajax({
            url:"../seller/sendSms.do",
            type:"post",
            data:{phone:phone},
            success:function (res) {
                if (res.code == 1){
                    layer.close(d);
                    $(".sourceSendSMS").val(res.data);
                    $('#myModalSource').modal('show');
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
$(document).on("click",".updateSource",function () {
    var id =  $(".uid").val();
    var source =  $(".sce").val();
    var  sendSMS = $(".sourceSendSMS").val();//短信验证码
    var  send = $(".sourceSendsms").val();//输入的验证码
    if(sendSMS != send){
        alert("验证码不正确！");
        return;
    };
    if (source == 1){
        layer.confirm("商城转换线下，客户APP绑定的商家将无效，是否确认?", {
            btn: ["确定", "取消"]
        }, function() {
             $.ajax({
                 type: "POST",
                 url: "../seller/updateBusinessSource.do",
                 data:{id:id,source:source},
                 success: function (result) {
                     if(result.code==1){
                         location.reload();
                     }else{
                         toastr.error(result.info);
                     }
                 }
             });
        });
    }else if (source == 0){
        layer.confirm("线下转换商城，客户APP将可以进行绑定商家，是否确认?", {
            btn: ["确定", "取消"]
        }, function() {
             $.ajax({
                 type: "POST",
                 url: "../seller/updateBusinessSource.do",
                 data:{id:id,source:source},
                 success: function (result) {
                     if(result.code==1){
                         location.reload();
                     }else{
                         toastr.error(result.info);
                     }
                 }
             });
        });
    }

});

$(function () {

    //添加商家
    $("#saveSeller").click(function () {
        var  phone = $("#phone").val();
        var  store_name = $("#store_name").val();
        if(phone == ""){
            alert("账号不能为空");
            return;
        }
        if(phone.length != 11){
            alert("账号只能是11位数");
            return;
        }
        if (store_name == "" ){
            alert("店铺名称不能为空");
            return;
        }
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
             type: "POST",
             url: "../seller/saveSeller.do",
             data:dataJson,
             success: function (result) {
                 if(result.code==1){
                     toastr.success(result.info);
                     setTimeout(function(){
                         parent.location.href = "../seller/JumpSellerList.do";
                         parent.layer.closeAll();
                     }, 2000);
                 }else if(result.code== -2){
                     toastr.success(result.info);
                 }
                 else{
                     toastr.error(result.info);
                 }
             }
         });


    });

//修改商家
    $("#updateSeller").click(function () {
        var  store_name = $("#store_name").val();
        if (store_name == "" ){
            alert("店铺名称不能为空");
            return
        }
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../seller/updateSeller.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../seller/JumpSellerList.do";
                        parent.layer.closeAll();
                    }, 1000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
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
                url:"../seller/singleLoginJurisdiction.do",
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
            url:"../seller/manyLoginJurisdiction.do",
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




//根据服务商跳转到绑定的客户详情
$(document).on("click",".detail",function () {
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '绑定的客户详情',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../seller/jumpDetails.do?id="+id
    });
});


//根据绑定表id查询绑定表信息
$(".bDetails").click(function(){
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title: '客户详情',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../seller/bindingdDetails.do?id="+id
    });
});


//跳转到营业执照页面
$(document).on("click",".license",function () {
    var id = $(this).attr("val");
    layer.open({
        type: 2,
        title:"营业执照",
        shade:0.8,
        area:['100%','100%'],
        content:"../seller/jumpLicense.do?id="+id
    });
});

//加载营业执照
$(function () {
    var id = $("#bid").val();
    $.ajax({
        type: "POST",
        url: "../seller/license.do",
        data:{id:id},
        success: function (result) {
            if(result.code==1){
                var license = result.data;
                var html = '';
                for (var i = 0; i < license.length; i++) {
                    html +=  '<div class="license" >'+
                                ' <input name="lId" id="lId" value="'+license[i].id+'" style="display: none">'+
                                '<img class="grxx_tx" src="'+license[i].img+'" style="width:150px;height:150px;cursor:pointer;">'+
                                '<img class="close" src="../statics/img/close.png" style="width:45px;height:45px;cursor:pointer;">'+
                        '</div>'
                }
                $(".gr").append(html);
                //删除营业执照
                $(".close").click(function () {
                    var  license_id = $(this).siblings("input").val()
                    $.ajax({
                        url:"../seller/deleteLicense.do",
                        type:"post",
                        data:{id:license_id},
                        success:function (ret) {
                            if (ret.code == 1){
                                toastr.success(ret.info);
                                location.reload();
                            }else{
                                toastr.error(ret.info);
                            }
                        }
                    });
                });
            }else{
                toastr.error(result.info);
            }
        }
    });
});



//公告
$(document).on("click",".notice",function () {
    var notice = $(this).siblings("input").val();
    var text = "";
    if (notice == "null"){
        text = '<textarea disabled style="width:100%;height:360px; text-align: center; font-size: 30px; color: white;background-color: #0000cc">'+"暂无公告！"+'</textarea>'
    }else {
        text = '<textarea disabled style="width:100%;height:350px; font-size: 16px;color: white;background-color: #0000cc">'+notice+'</textarea>'
    }
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '700px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: text
    });
});
