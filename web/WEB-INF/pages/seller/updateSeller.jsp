<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生鲜后台 - 修改商家</title>
    <meta name="keywords" content="生鲜后台">
    <meta name="description" content="生鲜后台">
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
                    <h5>修改商家</h5>
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
                        <input type="hidden" name="id" value="${seller.id}" />
                        <div class="form-group">
                            <label class="col-sm-2 control-label">账号：</label>
                            <div class="col-sm-9">
                                <input id="phone" <c:if test="${sessionScope.admin.admin_role != '超级管理员'}"> disabled </c:if> name="phone"  placeholder="" class="form-control" type="text" required="true" class="valid" value="${seller.phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">编号：</label>
                            <div class="col-sm-9">
                                <input id="number" disabled name="number"  placeholder="请输入店名称" class="form-control" type="text" required="true" class="valid" value="${seller.number}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">店名称：</label>
                            <div class="col-sm-9">
                                <input id="store_name" name="store_name"  placeholder="请输入店名称" class="form-control" type="text" required="true" class="valid" value="${seller.store_name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">区域：</label>
                            <div class="col-sm-9">
                                <input id="name" name="name"  placeholder="请输入区域" class="form-control" type="text" required="true" class="valid" value="${seller.name}">
                            </div>
                        </div>
                        <div class="form-group" >
                            <label class="col-sm-2 control-label">到期时间：</label>
                            <div class="col-sm-9">
                                <input  class="laydate-icon form-control layer-date"   <c:if test="${sessionScope.admin.jur == 0}">disabled</c:if>
                                        placeholder="请输入到期时间" id="duration"   name="duration"
                                        value='<fmt:formatDate value="${seller.duration }" pattern="yyyy-MM-dd HH:mm:ss" />' onclick="laydate({elem: &#39;#duration&#39;,istime: true,format: &#39;YYYY-MM-DD &#39;});"
                                />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">说明：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="illustrated" name="illustrated" value="${seller.illustrated}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注：</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" placeholder="" id="remarks" name="remarks" value="${seller.remarks}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-2">
                                <button class="btn btn-primary postfrom" id="updateSeller" type="button">
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
<script src="${pageContext.request.contextPath}/statics/js/myjs/seller.js"></script>

</body>
</html>