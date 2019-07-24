<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - 轮播图页面</title>
    <meta name="keywords" content="诚亿森管理系统总后台">
    <meta name="description" content="诚亿森管理系统总后台">
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
                    <h5>轮播图页面</h5>
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
                    <form method="get" action="${pageContext.request.contextPath}/homePage/JumpBackgroundImageList.do" name="formljj" class=" m-t" id="biaod">
                        <div class="row">
                            <input type='text' value="${pageNo}" name='pageNo' id='pageNo' style='display: none'>
                            <a><button class="btn btn-sm btn-primary m-r adds" style="float: left;margin-left: 15px " type="button">
                                <i class="fa fa-plus"></i>
                                添加</button></a>
                        </div>
                    </form>

                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="checkbox" >
                                </th>
                                <th>所属</th>
                                <th>轮播图</th>
                                <th>排序</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="record" items="${page.records}">
                                <tr>
                                    <td class='xuan'>
                                        <input id="checkbox3" type="checkbox" name='id' val="${record.id}">
                                    </td>
                                    <td>${record.distinguish}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${fn:contains(record.figure,'http')}">
                                                <img class="imgclass" src="${record.figure}" width="90" height="70">
                                            </c:when>
                                            <c:otherwise>
                                                <img class="imgclass" src="http://apijava.gdswlw.com/${record.figure}" width="90" height="70">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${record.sort}</td>
                                    <td class="operation">
                                        <button class="btn btn-success btn-sm " type="button" val="${record.id}">
                                            <i class="fa fa-edit"></i>
                                            <span class="bold">修改</span>
                                        </button>
                                        <button class="btn btn-danger btn-sm detele" type="button" val="${record.id}">
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
</div>
<input id="fileupload" type="file" name="mypic" style="display:none">
<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/backgroundImageLsit.js"></script>
</body>
</html>