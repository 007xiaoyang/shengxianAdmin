<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台- 菜单管理列表</title>
    <meta name="keywords" content="诚亿森管理后台">
    <meta name="description" content="诚亿森管理后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>菜单管理列表</h5>
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
                    <form method="post" action="${pageContext.request.contextPath}/menulist.do" name="formljj" class=" m-t" id="biaod">
                        <div class="row">
                            <div class="col-sm-2 m-b-xs">
                                <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>菜单父级编号</button> </span>
                                    <input type="text" class="input-sm form-control" name="menuid" value="">
                                </div>
                            </div>
                            <input type='text' value="" name='pageNo' id='pageNo' style='display: none'>
                            <div class="col-sm-2 m-b-xs">
                                <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>菜单名称</button> </span>
                                    <input type="text" class="input-sm form-control" name="name" value="">
                                </div>
                            </div>
                            <span class="input-group-btn"><button class="btn btn-sm btn-primary">搜索</button> </span>

                        </div>
                        <div class="row">
                            <div class="col-sm-2 ">
                                <button class="btn btn-sm btn-primary m-r addMenu"  type="button">
                                    <i class="fa fa-plus"></i>
                                    添加菜单管理
                                </button>
                            </div>
                        </div>
                    </form>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="checkbox" >
                                </th>
                                <th>菜单名称</th>
                                <th>请求路径</th>
                                <th>排序</th>
                                <th>层级</th>
                                <th>菜单编号</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="menu" items="${menuList}">
                            <tr>
                                <td class='xuan'>
                                    <input id="checkbox3" type="checkbox" name='id' val="${menu.id}">
                                </td>
                                <td>${menu.name}</td>
                                <td>${menu.link}</td>
                                <td>${menu.sort}</td>
                                <td>${menu.level}</td>
                                <td>${menu.menuid}</td>
                                <td><c:if test="${menu.isDele ==0}">已启用</c:if><c:if test="${menu.isDele ==1}">已禁用</c:if> </td>
                                <td class="operation">
                                    <button class="btn btn-success btn-sm" type="button" val="${menu.id}">
                                        <i class="fa fa-edit"></i>
                                        <span class="bold">修改</span>
                                    </button>
                                    <button class="btn btn-danger btn-sm" type="button" val="${menu.id}">
                                        <i class="fa fa-trash-o"></i>
                                        <span class="bold">删除</span>
                                    </button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>

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
<script src="${pageContext.request.contextPath}/statics/js/myjs/menulist.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
</body>
</html>
