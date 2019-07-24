<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>生鲜后台 - 服务商填写客户信息详情页面</title>
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
                    <h5>服务商填写客户信息详情页面</h5>
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
                    <form method="post" action="${pageContext.request.contextPath}/customer/bindingDetailsInfo.do?" name="formljj" class=" m-t" id="biaod">

                    </form>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>客户名称：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName"disabled value="${binding.customerName}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>配送时间段：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName" disabled value="${binding.distribution}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>说明1：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName" disabled value="${binding.illustrated1}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>说明2：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName" disabled value="${binding.illustrated2}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>备注1：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName" disabled value="${binding.remarks1}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>备注2：</button> </span>
                                <input type="text" class="input-sm form-control" name="storeName" disabled value="${binding.remarks2}">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 m-b-xs">
                            <div class="input-group">
                                        <span class="input-group-btn">
                                                <button type="button" class="btn btn-sm btn-default" style="color: #1E0FBE" disabled>来源：</button> </span>
                                <c:if test="${binding.source==0}">
                                    <input type="text" class="input-sm form-control" name="storeName" disabled value="线上">
                                </c:if>
                                <c:if test="${binding.source==1}">
                                    <input type="text" class="input-sm form-control" name="storeName" disabled value="线下">
                                </c:if>

                            </div>
                        </div>
                    </div>
                    <button class="btn btn-link btn-outline" type="button" >
                        <i class="fa fa-mail-reply"></i>
                        <span class="bold">返回</span>
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
</body>
</html>
