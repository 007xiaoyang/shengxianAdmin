

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


//线上产品数据渲染
function onlineGoods(pageNo,business_id,store_name,name) {
    $.ajax({
        url:"../goods/selectOnlineGoodsList.do",
        type:"post",
        data:{pageNo:pageNo,business_id:business_id,store_name:store_name,name:name},
        beforeSend:function(){
            $(".goods").append("<td class='xuan'>loading...</td>");//显示加载动画
        },
        success:function (res) {
            if (res.code == "1"){
                var goods = res.data.records;
                total = res.data.totalRecordsNum; //总记录数
                pageSize = res.data.pageSize; //每页显示条数
                curPage = res.data.pageNum; //当前页
                totalPage = res.data.totalPageNum; //总页数
                var goodsHtml ='';
                for ( var i=0; i < goods.length;i++){
                    var img1="";
                    var img2="";
                    var img3="";
                    if (goods[i].img1 == null){
                    }else {
                        img1 = goods[i].img1;
                    }
                    if (goods[i].img2 == null){

                    }else {
                        img2 = goods[i].img2;
                    }
                    if (goods[i].img3 == null){

                    }else {
                        img3 = goods[i].img3;
                    }

                    goodsHtml += '<tr>' +
                                    '<td class="xuan">' +
                                        '<input id="checkbox3" type="radio" name="id" value="'+goods[i].id+'">' +
                                    '</td>' +
                                    '<td style="color: #0000cc">'+goods[i].store_name+'</td>' +
                                    '<td>'+goods[i].name+'</td>'+
                                    '<td>'+goods[i].spec+goods[i].units+'</td>'+
                                    '<td>' +
                                        '<img class="img1" src="'+img1+'" style="width: 50px;"/>'+
                                        '<img class="img3" src="'+img2+'" style="width: 50px;"/>'+
                                        '<img class="img3" src="'+img3+'" style="width: 50px;"/>'+
                                    '</td>'+
                                    '<td class="operation">' +
                                        '<button class="btn btn-warning btn-sm lower" style="margin-right: 5px" type="button" val="'+goods[i].id+'">' +
                                            '<i class="fa fa-edit"></i>' +
                                            '<span class="bold">下架</span>' +
                                        '</button>'+
                                    '</td>'+
                                '</tr>'
                }
                $(".goods").empty().append(goodsHtml)

            }else {
                toastr.error(res.info);
            }
        }, complete:function(){ //生成分页条
            getPageBar();
            funOnlineGoods();
        },
        error:function (res) {
            toastr.error(res.info);
        }
    });
};

//线下产品数据渲染
function underGoods(pageNo,business_id,store_name,name) {
    $.ajax({
        url:"../goods/selectUnderGoodsList.do",
        type:"post",
        data:{pageNo:pageNo,business_id:business_id,store_name:store_name,name:name},
        beforeSend:function(){
            $(".goods").append("<td class='xuan'>loading...</td>");//显示加载动画
        },
        success:function (res) {
            console.info(res)
            if (res.code == "1"){
                var goods = res.data.records;
                total = res.data.totalRecordsNum; //总记录数
                pageSize = res.data.pageSize; //每页显示条数
                curPage = res.data.pageNum; //当前页
                totalPage = res.data.totalPageNum; //总页数
                var goodsHtml ='';
                for ( var i=0; i < goods.length;i++){
                    var img1="";
                    var img2="";
                    var img3="";
                    if (goods[i].img1 == null){
                    }else {
                        img1 = goods[i].img1;
                    }
                    if (goods[i].img2 == null){

                    }else {
                        img2 = goods[i].img2;
                    }
                    if (goods[i].img3 == null){

                    }else {
                        img3 = goods[i].img3;
                    }
                    var status ="";
                    if (goods[i].status == 0){
                        status = "本地";
                    }else if(goods[i].status == 3){
                        status = "未通过";
                    }else if(goods[i].status == 4){
                        status = "下架";
                    }

                    goodsHtml += '<tr>' +
                        '<td class="xuan">' +
                        '<input id="checkbox3" type="radio" name="id" value="'+goods[i].id+'">' +
                        '</td>' +
                        '<td style="color: #3D3D3D">'+goods[i].store_name+'</td>' +
                        '<td>'+goods[i].name+'</td>'+
                        '<td>'+goods[i].spec+goods[i].units+'</td>'+
                        '<td>' +
                            '<img class="img1" src="'+img1+'" style="width: 50px;"/>'+
                            '<img class="img3" src="'+img2+'" style="width: 50px;"/>'+
                            '<img class="img3" src="'+img3+'" style="width: 50px;"/>'+
                        '</td>'+
                        '<td style="color: #880000">'+status+'</td>'+
                    '</tr>'

                }
                $(".goods").empty().append(goodsHtml)


            }else {
                toastr.error(res.info);
            }
        }, complete:function(){ //生成分页条
            getPageBar();
            funUnderGoods();
        },
        error:function (res) {
            toastr.error(res.info);
        }
    });
};

$(document).on("click",".lower",function () {
    var  goods_id = $(this).attr("val");
    layer.confirm("我难道违规了，您因此忍心要让我下架？",{
        btn:["确定","取消"]
    },function () {
        $.ajax({
            url:"../goods/updateGoodsStatus.do",
            type:"post",
            data:{goods_id:goods_id,status:4},
            success:function (res) {
                if(res.code == "1"){
                    toastr.success(res.info);
                    location.reload();
                }else {
                    toastr.error(res.info);
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        })
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


