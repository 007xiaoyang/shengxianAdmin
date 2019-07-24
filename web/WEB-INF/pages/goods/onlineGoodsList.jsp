<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - 上架商品列表</title>
    <meta name="keywords" content="诚亿森管理系统总后台">
    <meta name="description" content="诚亿森管理系统总后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
        .seller{
            position: relative;
        }
        .menu{
            width: 20px;
            height: 50px;
            position: absolute;
            top: 0;
            right: 20px;
            z-index: 999;
        }
        .dropdown:hover{
            background-color: #0000cc;
        }

    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>上架商品列表</h5>
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
                    <div class="row">
                        <input type='text' value="" name='pageNo' id='pageNo' style='display: none'>
                        <div class="col-sm-4 m-b-xs ">
                            <div class="input-group seller">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>店铺名称/编号</button> </span>
                                <input type="text" id="store_name" class="input-sm form-control" name="number" value="">
                            </div>
                            <div class="btn-group-sm menu ">
                                <button type="button" class="btn btn-default dropdown-toggle dropdown" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu businesssName" role="menu">

                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-4 m-b-xs">
                            <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>产品名称/编号</button> </span>
                                <input type="text" id="gname" class="input-sm form-control" name="storeName" value="">
                            </div>
                        </div>
                        <span class="input-group-btn search"><button class="btn btn-sm btn-primary ">搜索</button> </span>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="checkbox" >
                                </th>
                                <th>服务商</th>
                                <th>产品名称</th>
                                <th>规格</th>
                                <th>图片</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!--内容渲染-->
                            <tbody class="goods">

                            </tbody>
                        </table>
                        <!--分页-->
                        <div class="col-sm-6">
                            <div class="dataTables_paginate paging_simple_numbers" id="pagecount">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="mask" id="mask"></div>
<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/goods.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script>
<script type="text/javascript">
    $(function () {
        onlineGoods();
    });
    //商家挑选搜索
    $(document).on("click",".a_name",function () {
        var bid = $(this).find("input").val();
        onlineGoods("",bid,"","");
    });
    //输入框搜索
    $(document).on("click",".search",function () {
        var store_name = $("#store_name").val();
        var gname = $("#gname").val();
        onlineGoods(1,"",store_name,gname);
    });
    //点击页数
    function funOnlineGoods(){
        $("#pagecount span a").on('click',function(){
            var store_name = $("#store_name").val();
            var gname = $("#gname").val();
            var rel = $(this).attr("rel");
            if(rel){
                onlineGoods(rel,"",store_name,gname);
            }
        });
    };
    $(document).on("click",".go",function () {
        var jumpPage = $(".jumpPage").val();
        onlineGoods(jumpPage,"");
    });
</script>
</body>
</html>
