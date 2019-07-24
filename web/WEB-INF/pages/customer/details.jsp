<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生鲜后台 - 详情页面</title>
    <meta name="keywords" content="客户绑定商家列表">
    <meta name="description" content="客户绑定商家列表">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <style type="text/css">
        span{
            font-size: 16px;
            margin-right: 10px;
        }

    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>详情页面</h5>
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
                    <input type='text' value="${user_id}" name='user_id' id='user_id' style='display: none'>
                    <div class="row" style="">
                        <div class="col-sm-5 m-b-xs">
                            <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>服务商账号/编号：</button>
                                    </span>
                                    <input type="text"id="name" class="input-sm form-control" name="name" value="">
                            </div>
                        </div>
                        <span class="input-group-btn"><button class="btn btn-sm btn-primary buttonCustromer">搜索</button> </span>
                    </div>
                    <div class="row userPhone">

                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="radio" name='id'>
                                </th>
                                <th>服务商账号</th>
                                <th>服务商名称</th>
                                <th>服务商编号</th>
                                <th>服务商区域</th>
                                <th>绑定时间</th>
                            </tr>
                            </thead>
                            <!--内容渲染-->
                            <tbody class="userDetail">

                            </tbody>
                        </table>
                        <!--分页-->
                        <div class="col-sm-6">
                            <div class="dataTables_paginate paging_simple_numbers" id="pagecount">

                            </div>
                        </div>
                    </div>
                    <button class="btn btn-link btn-outline" type="button" >
                        <i class="fa fa-mail-reply"></i>
                        <span class="bold" style="font-size: 20px;">返回</span>
                    </button>
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
<script src="${pageContext.request.contextPath}/statics/js/myjs/customer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script>

<script type="text/javascript">
    $(function () {
        findUserPhone();
        userBusiness(curPage,"");
    });
    //条件查询客户信息
    $(".buttonCustromer").on("click",function () {
        var name = $("#name").val();
        userBusiness(curPage,name);
    });
    //点击页数
    function fun(){
        $("#pagecount span a").on('click',function(){
            var rel = $(this).attr("rel");
            if(rel){
                find(rel,"");
            }
        });
    };

</script>
</body>
</html>
