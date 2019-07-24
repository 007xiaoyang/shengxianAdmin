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


function findWitnesses(pageNo,business_id,store_name) {
    $.ajax({
        url:"../goods/witnesses.do",
        type:"post",
        data:{pageNo:pageNo,bid:business_id,store_name:store_name},
        beforeSend:function () {
            $(".witnesses").append("<td class='xuan'>loading...</td>");//显示加载动画
        },
        success:function (res) {
            console.log(res)
          if (res.code == "1"){
              var witnesses = res.data.records;
              total = res.data.totalRecordsNum; //总记录数
              pageSize = res.data.pageSize; //每页显示条数
              curPage = res.data.pageNum; //当前页
              totalPage = res.data.totalPageNum; //总页数
              var witnessesHtml ='';
              for ( var i=0; i < witnesses.length;i++){
                  var img1="" ;
                  var img2 = "";
                  var img3="";
                  if (witnesses[i].img1 != null){
                      img1 = witnesses[i].img1;
                  }
                  if (witnesses[i].img2 != null){
                      img2 = witnesses[i].img2;
                  }
                  if (witnesses[i].img3 != null){
                      img3 = witnesses[i].img3;
                  }
                  var state="";
                  var dispose = "";
                  if(witnesses[i].state == 0){
                      state = '<button class="btn btn-danger btn-xs state " type="button" val="'+witnesses[i].id+'">' +
                                '<span class="bold" value="">举报</span>' +
                              '</button>'
                      dispose = '<button class="btn btn-primary btn-sm dispose" style="margin-right: 5px" type="button" val="'+witnesses[i].id+'">' +
                                  '<i class="fa fa-edit"></i>' +
                                  '<span class="bold">处理</span>' +
                                '</button>'+
                                '<button class="btn btn-danger btn-sm malice" style="margin-right: 5px" type="button" val="'+witnesses[i].id+'">' +
                                    '<i class="fa fa-edit"></i>' +
                                    '<span class="bold">恶意举报</span>' +
                                '</button>'
                  }else if(witnesses[i].state == 1){
                      state = '<button class="btn btn-success btn-xs state " type="button" val="'+witnesses[i].id+'">' +
                                '<span class="bold" value="">处理中</span>' +
                              '</button>'
                      dispose = '<button class="btn btn-success btn-sm success" style="margin-right: 5px" type="button" val="'+witnesses[i].id+'">' +
                                  '<i class="fa fa-edit"></i>' +
                                  '<span class="bold">完成</span>' +
                                '</button>'
                  }else {
                      state = '<button class="btn btn-large btn-xs state " type="button" val="'+witnesses[i].id+'">' +
                                '<span class="bold" value="">完成</span>' +
                              '</button>'
                  }

                  witnessesHtml += '<tr>'+
                                      '<td class="xuan">' +
                                        '<input id="checkbox3" type="radio" name="id" value="'+witnesses[i].id+'">' +
                                      '</td>' +
                                      '<td>'+witnesses[i].phone+'</td>'+
                                      '<td>' +witnesses[i].store_name+'</td>'+
                                      '<td>' +witnesses[i].number+'</td>'+
                                      '<td>' +state+'</td>'+
                                      '<td class="content" style="width: 200px; cursor: pointer; text-decoration: underline">' +witnesses[i].content+'</td>'+
                                      '<td>' +
                                          '<img class="img1" src="'+img1+'" style="width: 50px;"/>'+
                                          '<img class="img3" src="'+img2+'" style="width: 50px;"/>'+
                                          '<img class="img3" src="'+img3+'" style="width: 50px;"/>'+
                                      '</td>'+
                                      '<td>'+timestampToTime(witnesses[i].create_time)+'</td>'+
                                      '<td class="operation">' + dispose+'</td>'+
                                    '</tr>'
              }
              $(".witnesses").empty().append(witnessesHtml);

          } else {
              toastr.error("哎呀，出了点小问题");
          }
        },
        complete:function () {
            getPageBar();
            funWitnesses();
        },
        error:function (res) {
            toastr.error("哎呀，出了点小问题");
        }
    });
};
//处理举报
$(document).on("click",".dispose",function () {
    var wid = $(this).attr("val");
    layer.confirm("是否处理该举报？",{
        btn:["确定","取消"]
    },function () {
        $.ajax({
            url:"../goods/updateWitnesses.do",
            type:"post",
            data:{wid:wid,state:1},
            success:function (res) {
                if(res.code == "1"){
                    location.reload();
                }else {
                    toastr.error(res.info);
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    });
});

//恶意举报
$(document).on("click",".malice",function () {
    var wid = $(this).attr("val");
    layer.confirm("该举报是否为恶意的？",{
        btn:["确定","取消"]
    },function () {
        $.ajax({
            url:"../goods/updateWitnesses.do",
            type:"post",
            data:{wid:wid,state:3},
            success:function (res) {
                if(res.code == "1"){
                    location.reload();
                }else {
                    toastr.error(res.info);
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    });
});

//处理完成了
$(document).on("click",".success",function () {
    var wid = $(this).attr("val");
    layer.confirm("该举报是否处理完成了？",{
        btn:["确定","取消"]
    },function () {
        $.ajax({
            url:"../goods/updateWitnesses.do",
            type:"post",
            data:{wid:wid,state:2},
            success:function (res) {
                if(res.code == "1"){
                    location.reload();
                }else {
                    toastr.error(res.info);
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    });
});

//举报内容
$(document).on("click",".content" ,function () {
    var content = $(this).text();
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '700px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: '<textarea disabled style="width:100%;height:350px; font-size: 18px;color: white;background-color: #0000cc">'+content+'</textarea>'
    });
});

//图片1
$(document).on("click",".img1",function(){
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
//图片2
$(document).on("click",".img2",function(){
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
//图片3
$(document).on("click",".img3",function(){
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

