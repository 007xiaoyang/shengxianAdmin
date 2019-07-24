//系统公告数据渲染
function systemBulletin() {
    $.ajax({
       url:"../homePage/systemBulletin.do",
       type:"post",
       success:function (res) {
           if(res.code == 1){
               var bulletin = res.data;
               var systemBulletinHtml ="";
               for(var i = 0; i < bulletin.length; i++){
                   var distinguish = "";
                   if (bulletin[i].distinguish == 1){
                       distinguish="服务商端系统公告"
                   }else {
                       distinguish="客户端系统公告"
                   }

                   systemBulletinHtml+='<tr>' +
                                           '<td>' +
                                               '<input id="checkbox3" type="checkbox" name="id" val="'+bulletin[i].id+'"/>'+
                                           '</td>' +
                                           '<td>'+distinguish+'</td>' +
                                           '<td>' +
                                            '<textarea class="dd" style="width: 600px; height: 150px; color: #030303" disabled="disabled">'+bulletin[i].noticeContent+'</textarea>'+
                                           '</td>'+
                                           '<td>'+timestampToTime(bulletin[i].releaseTime)+'</td>' +
                                           '<td>' +
                                               '<button class="btn btn-success btn-sm updateSystemBulletin" type="button" val="'+bulletin[i].id+'">' +
                                               '<i class="fa fa-edit"></i>' +
                                               '<span class="bold">修改</span>' +
                                               '</button>' +
                                           '</td>' +
                                       '</tr>'
               }
                $(".bulletin1").empty().append(systemBulletinHtml);
           }else {
               toastr.error(res.info);
           }
       }
    });
}

//系统电子协议数据渲染
function agreement() {
    $.ajax({
        url:"../homePage/agreement.do",
        type:"post",
        success:function (res) {
            if(res.code == 1){
                var bulletin = res.data;
                var systemBulletinHtml ="";
                for(var i = 0; i < bulletin.length; i++){
                    var distinguish = "";
                    if (bulletin[i].distinguish == 3){
                        distinguish='<span style="font-size: 30px;">服务商端电子协议</span>'
                    }else {
                        distinguish='<span style="font-size: 30px;">客户端电子协议</span>'
                    }

                    systemBulletinHtml+='<tr>' +
                        '<td>' +
                        '<input id="checkbox3" type="checkbox" name="id" val="'+bulletin[i].id+'"/>'+
                        '</td>' +
                        '<td>'+distinguish+'</td>' +
                        '<td>'+timestampToTime(bulletin[i].releaseTime)+'</td>' +
                        '<td>' +
                        '<button class="btn btn-success btn-sm updateAgreemet" type="button" val="'+bulletin[i].id+'">' +
                        '<i class="fa fa-edit"></i>' +
                        '<span class="bold">修改</span>' +
                        '</button>' +
                        '</td>' +
                        '</tr>'

                }
                $(".agreement").empty().append(systemBulletinHtml);

            }else {
                toastr.error(res.info);
            }
        },
    });
}
//跳转到修改系统公告页面
$(document).on("click",".updateSystemBulletin",function () {
    var id = $(this).attr("val");
    layer.open({
        type:2,
        title: '修改系统公告',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpUpdateSystemBulletin.do?id="+id,
    });
});

//跳转到修改电子协议页面
$(document).on("click",".updateAgreemet",function () {
    var id = $(this).attr("val");
    layer.open({
        type:2,
        title: '修改系统公告',
        shadeClose: true,
        shade: 0.8,
        area: ['100%', '100%'],
        content:"../homePage/JumpUpdateAgreement.do?id="+id,
    });
});




//通过id获取内容
function systemBulletinById(nid) {
    $.ajax({
        url:"../homePage/findNotice.do",
        type:"post",
        data:{id:nid},
        success:function (res) {
            if (res.code == 1){
                var notice = res.data;
                $("#noEd").text(notice.noticeContent);
            }
        },
        error:function (res) {
            alert("哎呀！出现一点异常了")
        },
        emplete:function () {
            onEditor();
        },
    });
}

