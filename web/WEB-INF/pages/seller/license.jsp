<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台 -营业执照 </title>
    <meta name="keywords" content="诚亿森管理后台">
    <meta name="description" content="诚亿森管理后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
        .license{
            float: left;
            overflow: hidden;
            position: relative;
            width: 180px;
            height:180px;
        }
        .license .grxx_tx{
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
        }
        .license .close{
            position: absolute;
            top: -10px;
            right: 0;
        }
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
                    <h5>营业执照</h5>
                    <div class="ibox-tools">
                        <a class="refresh-link" href="javascript:location.reload()">
                            <i class="fa fa-refresh"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="form-horizontal">
                        <input type="hidden" id="bid" value="${id}">
                        <div class="col-sm-3 touxiang" style="display: none">
                            <input name="img" id="figure" value="" style="display: none">
                        </div>
                        <div class="row">
                            <input type='text' value="" name='id' id='broadcastPictureid' style='display: none'>
                            <div class="col-sm-12 m-b-xs">
                                <div class="col-sm-12 m-b-xs">
                                     <img id="img" class="jiahao" src="../statics/img/jiahao.png" style="width:60px;height:60px;cursor:pointer;">
                                </div>
                                <div class="col-sm-12 m-b-xs gr">

                                </div>
                            </div>
                            <div class="col-sm-12">
                                <button class="btn btn-link btn-outline" type="button">
                                    <i class="fa fa-mail-reply"></i>
                                    <h1 class="bold">返回</h1>
                                </button>
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
    <script src="${pageContext.request.contextPath}/statics/js/myjs/seller.js"></script>
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
                        var bid = $("#bid").val();
                        var img = res.data
                        layer.closeAll('loading');
                        console.log(img);
                        //默认添加到数据库
                        $.ajax({
                            url:"../seller/addLicense.do", //上传营业执照
                            type:'POST',
                            data:{bid:bid,img:img},
                            success: function (res) {
                                console.log(res)
                                if (res.code == "1") {
                                    location.reload();
                                } else {
                                    toastr.error(result.info);
                                }
                            }
                        });
                    }
                },
                error: function(res){
                    console.log(res);
                    layer.closeAll('loading'); //关闭loading
                }
            });
        });
    </script>
</body>
</html>