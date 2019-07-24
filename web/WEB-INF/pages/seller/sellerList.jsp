<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台 - 商家列表</title>
    <meta name="keywords" content="诚亿森管理后台">
    <meta name="description" content="诚亿森管理后台">
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
            width:20px;
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
                    <h5>商家列表</h5>
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
                        <div class="col-sm-4 m-b-xs">
                            <div class="input-group seller">
								<span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-default" disabled>服务商/编号：</button>
                                </span>
                                <input type="text"  value="" id="" autocomplete="off" data-id="" alt="" class="input-sm form-control" name=""/>
                            </div>
                            <div class="btn-group-sm menu ">
                                <button type="button" class="btn btn-default dropdown-toggle dropdown" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu businesssName" role="menu">

                                </ul>
                            </div>
                        </div>
                        <span class="input-group-btn search"><button class="btn btn-sm btn-primary ">搜索</button> </span>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <span class="btn btn-sm btn-primary  resetPassword">重置密码</span>
                        </div>
                        <div class="col-sm-2">
                            <span class="btn btn-sm btn-primary  addBuseinss">注册服务商</span>
                        </div>
                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="radio" name='id'>
                                </th>
                                <th>服务商图片</th>
                                <th>账号</th>
                                <th>服务商名称</th>
                                <th>编号</th>
                                <th>审核</th>
                                <th style="width: 68px;">剩余天数</th>
                                <th>状态</th>
                                <th>来源</th>
                                <th>公告</th>
                                <th>创建时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!--数据渲染-->
                            <tbody class="business">

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
    <!-- 模态框（Modal） -->
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
                    <button type="button"  class="btn btn-primary deleteSeller" >
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <!-- 来源模态框（Modal） -->
    <div class="modal fade" id="myModalSource" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" style="color: #aa1111;" id="myModalLabelSource">
                        请输入验证码
                    </h4>
                </div>
                <div class="modal-body">
                    <input type="text"  class="input-group form-control uid" style="display:none" value=""   />
                    <input type="text"  class="input-group form-control sce" style="display:none" value=""   />
                    <input type="text"  class="input-group form-control sourceSendSMS" style="display:none" value=""   />
                    <input type="text" class="input-group form-control sourceSendsms" value=""   />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button"  class="btn btn-primary updateSource" >
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
<script src="${pageContext.request.contextPath}/statics/js/myjs/seller.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script>
<script type="text/javascript">

    //进入页面加载
    $(function () {
       findBuseinss(curPage,"");
    });
    //商家挑选搜索
    $(document).on("click",".a_name",function () {
        var bid = $(this).find("input").val();
        findBuseinss("",bid);
    });
    //输入框搜索
    $(document).on("click",".search",function () {
        var store_name = $("#store_name").val();
        findBuseinss(1,"",store_name);
    });
    //点击页数
    function funBuseinss(){
        $("#pagecount span a").on('click',function(){
            var rel = $(this).attr("rel");
            var store_name = $("#store_name").val();
            if(rel){
                findBuseinss(rel,store_name);
            }
        });
    };
    $(document).on("click",".go",function () {
        var jumpPage = $(".jumpPage").val();
        findBuseinss(jumpPage,"");
    });
</script>
</body>


</html>
