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
        area: ['60%', '80%'],
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
        area: ['60%', '80%'],
        content:"../homePage/JumpUpdateBackgroundImage.do?id="+id
    });
});


//更换图片
$('.grxx_tx').click(function () {
    $('#file1').click();
});

function file() {
    //loading层
    var index = layer.open({type: 2});
    $.ajax({
        url: 'http://apijava.gdswlw.com/fileUpload/upload',
        type: 'POST',
        cache: false,
        data: new FormData($('#formId')[0]),
        processData: false,
        contentType: false
    }).done(function (res) {
        layer.close(index);
        if (res.data[0].file1 != undefined) {
            var value = $("#value").val();
            var src = "http://apijava.gdswlw.com/" + res.data[0].file1;
            $('.grxx_tx').attr("src", src);
            $('#figure').val(src);
        }
    })
}




//添加

$("#addTheme").click(function () {
    var dataJson=$(this).closest("form").serialize();
    var distinguish = $("#distinguish").val();
    var sort = $("#sort").val();
    $.ajax({
        type: "POST",
        url: "../theme/addTheme.do",
        data:dataJson,
        success: function (result) {
            if(result.code==1){
                toastr.success(result.info);
                setTimeout(function(){
                    parent.location.href = "../homePage/addBackgroundImage.do";
                    parent.layer.closeAll();
                }, 2000);
            }else{
                toastr.error(result.info);
            }
        }
    });
});
















$(".operation .btn-white").click(function() {
    var id = $(this).attr("val");
    layer.confirm("删除该角色将无法恢复,是否确认?", {
        btn: ["确定", "取消"]
    }, function() {
        $.ajax({
            type: "POST",
            url: "../theme/deleteTheme.do",
            data:{id:id},
            success: function (result) {
                if(result.code==1){
                    location.reload();
                }else{
                    toastr.error(result.info);
                }
            }
        });
    })
});

$(document).on("click",".brand-logo-img .btn-upload",function(){
    $("#fileupload").click();
});

$(document).on("click",".imglist .dels,.delguige",function(){
    $(this).parent().remove();
    $(".btn-upload").show();
});

$(function () {
    $("#fileupload").wrap("<form id='myupload' action='http://apijava.gdswlw.com/fileUpload/upload' method='post' enctype='multipart/form-data'><input type='hidden' name='appkey' value='2da45f08-cdd4-4'></form>");
    $("#fileupload").change(function(){ //选择文件
        if($(this).val()!=""){
            $("#myupload").ajaxSubmit({
                dataType:  'json', //数据格式为json
                success: function(data) { //成功
                    //console.log(data);
                    setTimeout(function(){
                        if(data.code == 1){
                            var html = "";
                            html +='<div class="imglist">';
                            html +='<img src="../img/dels.png" class="dels">';
                            html +='<img src="http://apijava.gdswlw.com/'+data.data[0].mypic+'" class="tupian imgclass"/>';
                            html +='<input type="hidden" name="imagelist" value="'+data.data[0].mypic+'"/>';
                            html +='</div>';
                            $(".btn-upload").before(html);
                            $(".btn-upload").hide();
                            $(this).off("change");//清除上传图片事件
                        }else{
                            toastr.error(data.info);
                        }
                    }, 500);
                },
                error:function(xhr){ //上传失败
                    toastr.error("上传失败");
                }
            });
        }
    });




    $("#updateTheme").click(function () {
        var dataJson=$(this).closest("form").serialize();
        $.ajax({
            type: "POST",
            url: "../homePage/updateTheme.do",
            data:dataJson,
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../theme/findThemeByPage.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });
});