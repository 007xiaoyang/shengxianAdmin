<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台 - 权限列表</title>
    <meta name="keywords" content="诚亿森管理后台">
    <meta name="description" content="诚亿森管理后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>权限列表</h5>
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
                    <form method="get" action="${pageContext.request.contextPath}/adminRole/findAdminRoleByPage.do" name="formljj" class=" m-t" id="biaod">
                        <div class="row">
                            <div class="col-sm-2 m-b-xs">
                                <div class="input-group">
									<span class="input-group-btn">
									<button type="button" class="btn btn-sm btn-default" disabled>ID</button> </span>
                                    <input type="text" class="input-sm form-control" name="id" value="${id}">
                                </div>
                            </div>
                            <input type='text' value="${pageNo}" name='pageNo' id='pageNo' style='display: none'>
                            <div class="col-sm-2 m-b-xs">
                                <div class="input-group">
									<span class="input-group-btn"><button type="button" class="btn btn-sm btn-default" disabled>角色</button> </span>
                                    <input type="text" class="input-sm form-control" name="rolename" value="${rolename}">
                                    <span class="input-group-btn"><button class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2 " >
                                <button class="btn btn-sm btn-primary m-r adds" type="button">
                                    <i class="fa fa-plus"></i>添加
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
                                <th>ID</th>
                                <th>角色名称</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="adminRole" items="${page.records}">
                            <tr>
                                <td class='xuan'>
                                    <input id="checkbox3" type="checkbox" name='id' val="${adminRole.id}">
                                </td>
                                <td>${adminRole.id}</td>
                                <td>${adminRole.rolename}</td>
                                <td><fmt:formatDate value="${adminRole.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td><fmt:formatDate value="${adminRole.updatetime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td class="operation">
                                    <button class="btn btn-success btn-sm updateRole" type="button" val="${adminRole.id}">
                                        <i class="fa fa-edit"></i>
                                        <span class="bold">修改</span>
                                    </button>
                                    <button class="btn btn-danger btn-sm" type="button" val="${adminRole.id}" <c:if test="${adminRole.id==1}">disabled</c:if>>
                                        <i class="fa fa-trash-o"></i>
                                        <span class="bold">删除</span>
                                    </button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="col-sm-6">
                            <div class="dataTables_paginate paging_simple_numbers" id="editable_paginate">
                                <ul class='pagination'>
                                    <c:set var="num" value="${page.totalPageNum}"></c:set>
                                    <c:forEach begin="1" end="${num}" var="v" >
                                    <li class="paginate_button <c:if test="${page.pageNum==v}">active</c:if>" aria-controls="editable" tabindex="0">
                                        <a href="javascript:findPage(${v});">${v}</a>
                                    </li>
                                    </c:forEach>
                                    <c:if test="${page.pageNum<page.totalPageNum}">
                                        <li class="paginate_button next">
                                            <a href="javascript:findPage(${page.pageNum+1});">下一页 → </a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改权限模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" style="color: #aa1111;" id="myModalLabel">
                        请输入验证码
                    </h4>
                </div>
                <div class="modal-body">
                    <input type="text" class="uid" value="" style="display:none;"/>
                    <input type="text"  class="input-group form-control sendSMS" style="display:none" value=""   />
                    <input type="text" class="input-group form-control sendsms" value=""   />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button"  class="btn btn-primary confirm" >
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
    <script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
    <script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/statics/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/myjs/adminRole.js"></script>
</body>
</html>