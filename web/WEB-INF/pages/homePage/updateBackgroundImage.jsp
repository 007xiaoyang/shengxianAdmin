<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - 修改轮播图</title>
    <meta name="keywords" content="诚亿森管理系统总后台">
    <meta name="description" content="诚亿森管理系统总后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
        input[type="file"] {
            display: none;
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>修改轮播图</h5>
                    <div class="ibox-tools">
                        <a class="refresh-link" href="javascript:location.reload()">
                            <i class="fa fa-refresh"></i>
                        </a>
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="form-horizontal">
                        <input type="hidden" id="id" value="">
                        <div class="row">
                            <input type='text' value="${broadcastPicture.id}" name='id' id='broadcastPictureid' style='display: none'>
                            <div class="form-group" style="margin-left: 185px">
                                <label class="col-sm-2 control-label">所属：</label>
                                <div class="col-sm-2">
                                    <input id="storeName" name="distinguish"  placeholder="" class="form-control" type="text" required="true" disabled  class="valid" value="${broadcastPicture.distinguish}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label">轮播图：</label>
                                <div class="col-sm-3 touxiang">
                                    <img id="img" class="grxx_tx" src="${broadcastPicture.figure}" style="width:150px;height:150px;cursor:pointer;">
                                </div>
                            </div>
                            <div class="form-group" style="margin:30px 0px 30px 180px">
                                <label class="col-sm-2 control-label">排序：</label>
                                <div class="col-sm-2">
                                    <input id="sort" name="sort"  placeholder="" class="form-control" type="text" required="true" class="valid" value="${broadcastPicture.sort}">
                                </div>
                            </div>
                            <%--<div class="form-group">
                                <label class="col-sm-2 control-label">URL连接：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name"  placeholder="请输入区域" class="form-control" type="text" required="true" class="valid" value="">
                                </div>
                            </div>--%>


                            <div class="form-group" style="margin-left: 100px">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <button class="btn btn-primary postfrom" id="updateTheme" type="button">
                                        <i class="fa fa-check"></i>
                                        <span class="bold">提交</span>
                                    </button>
                                    <button class="btn btn-link btn-outline" type="button">
                                        <i class="fa fa-mail-reply"></i>
                                        <span class="bold">返回</span>
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input id="fileupload" type="file" name="mypic" style="display:none">
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
    <script src="${pageContext.request.contextPath}/statics/layui/layui.all.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/myjs/backgroundImageLsit.js"></script>
    <script type="text/javascript">
        //上传图片
        layui.use('upload', function(){
            var upload = layui.upload;
            //执行实例
            var uploadInst = upload.render({
                elem: '#img', //绑定元素
                url: 'http://www.bxy8888.com:8052/upload/upload',
                choose:function(){
                    layer.load();
                },
                done: function(res){
                    if(res.code == 1){
                        $('.grxx_tx').attr("src", res.data);
                        layer.closeAll('loading');
                    }
                },
                error: function(res){
                    console.log(res);
                    layer.closeAll('loading'); //关闭loading
                }
            });
        });

       $(document).on("click","#updateTheme",function () {
            var id = $("#broadcastPictureid").val();
            var figure =  $('.grxx_tx').attr("src");
            var sort = $("#sort").val();
            $.ajax({
                type: "POST",
                url: "../homePage/updateBackgroundImage.do",
                data:{id:id,figure:figure,sort:sort},
                success: function (result) {
                    if(result.code==1){
                        toastr.success(result.info);
                        setTimeout(function(){
                            parent.location.href = "../homePage/JumpBackgroundImageList.do";
                            parent.layer.closeAll();
                        }, 2000);
                    }else{
                        toastr.error(result.info);
                    }
                }
            });
        });
    </script>
</body>
</html>