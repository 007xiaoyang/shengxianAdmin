<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - 客户列表</title>
    <meta name="keywords" content="蓝诚亿森管理系统总后台牙狗后台">
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
                    <h5>客户列表</h5>
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
                        <input type='text' value="${sessionScope.pwd}" name='' id='password' style='display: none'>
                        <div class="col-sm-6 m-b-xs">
                            <div class="input-group">
								<span class="input-group-btn">
                                    <button type="button" class="btn btn-sm btn-default" disabled>账号：</button>
                                </span>
                                <input type="text" id="phone" class="input-sm form-control" name="phone" value=""/>
                            </div>
                        </div>
                        <span class="input-group-btn"><button class="btn btn-sm btn-primary buttonCustromer" >搜索</button> </span>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <span class="btn btn-sm btn-primary  resetPassword">重置密码</span>
                        </div>
                        <div class="col-sm-2">
                            <span class="btn btn-sm btn-primary  addCustomer">注册客户</span>
                        </div>

                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="all" type="radio" name='id'>
                                </th>
                                <th>ID</th>
                                <th>账号</th>
                                <th>状态</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!--内容渲染-->
                            <tbody class="customer">

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
    <!-- 删除模态框（Modal） -->
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
                    <input type="text"  class="input-group form-control uid" style="display:none" value=""   />
                    <input type="text"  class="input-group form-control sendSMS" style="display:none" value=""   />
                    <input type="text" class="input-group form-control sendsms" value=""   />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button"  class="btn btn-primary deleteUser" >
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<%--发送信息窗口--%>
<div class="mask" id="mask"></div>
<div class="xsdj xsdj1 " style="width: 550px; height: 350px;">
    <form action="" method="post" name="donjie">
        <input type="hidden" name="id" class="wxid" value="">

        <span class="dj_tit" style="font-size: 20px">标题：</span>
        <input  style="margin: 15px 0px; width: 350px; height: 35px;"  type="text" class="input-group-sm title" value=""/>

        <div class="shu_input">
           <textarea style="height: 220px" name="reason" placeholder="请输入内容" class="reason">
                </textarea>
        </div>
        <div class="row1">
            <ul class="dj_ul" style="background-color: #f0f0f0">
                <li id="CBor" class="bor" style="cursor:pointer;" >确定</li>
                <li id="CQuxiao" class="quxiao"  style="cursor:pointer; ">取消</li>
            </ul>
        </div>
    </form>
</div>
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


    //进入页面加载
    $(function () {
        find();
    });
    //条件查询客户信息
    $(".buttonCustromer").on("click",function () {
        phone = $("#phone").val();
        find("",phone);
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
    $(document).on("click",".go",function () {
        var jumpPage = $(".jumpPage").val();
        find(jumpPage,"");
    });
</script>

</body>
</html>
