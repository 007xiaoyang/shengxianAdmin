<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台 - 修改管理员</title>
    <meta name="keywords" content="诚亿森管理后台">
    <meta name="description" content="诚亿森管理后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/toastr/toastr.min.css" rel="stylesheet">
</head>
<style>
    .fixed-table-container{
        border-bottom: none;
        padding-bottom: 0px;
    }
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>修改管理员</h5>
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
                    <form class="form-horizontal m-t" id="signupForm2" action="" method="POST">
                        <input type="hidden" name="id" value="${admin.get("id")}" />
                        <div class="form-group">
                            <label class="col-sm-2 control-label">账号：</label>
                            <div class="col-sm-9">
                                <input id="account" name="account"  placeholder="请输入账号" class="form-control" type="text" required="true" class="valid" value="${admin.get("account")}" <c:if test="${admin.get('id')==1}">readonly</c:if>>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名：</label>
                            <div class="col-sm-9">
                                <input id="nick" name="username"  placeholder="请输入用户名" class="form-control" type="text" required="true" class="valid" value="${admin.get("username")}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" placeholder="不输入默认不修改" id="password" name="password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认密码：</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" placeholder="不输入默认不修改" id="confirm_password" name="confirm_password"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色：</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="adminRoleId" id="adminRoleId" <c:if test="${admin.get('id')==1}">readonly</c:if>>
                                    <c:forEach items="${adminRoles}" var="adminRole">
                                        <option value="${adminRole.get("id")}" <c:if test="${admin.get('admin_role_id')==adminRole.get('id')}">selected</c:if>>${adminRole.get("rolename")}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-2">
                                <button class="btn btn-primary postfrom" id="updateAdmin" type="button">
                                    <i class="fa fa-check"></i>
                                    <span class="bold">修改</span>
                                </button>
                                <button class="btn btn-link btn-outline" type="button">
                                    <i class="fa fa-mail-reply"></i>
                                    <span class="bold">返回</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<input id="fileupload" type="file" name="mypic" style="display:none">
<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery.cookie.js?v=1.4.1"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery.form.js?v=3.1.5"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/validate/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demo/form-validate-demo.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/layer/laydate/laydate.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/fancybox/jquery.fancybox.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/admin.js"></script>
</body>
</html>