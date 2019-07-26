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
                    <input type='text' value="${id}" name='id' id='id' style='display: none'>
                    <div class="row" >
                        <div class="col-sm-5 m-b-xs">
                            <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>客户绑定名称：</button> </span>
                                <input type="text" class="input-sm form-control store_name" name="store_name" value="">
                            </div>
                        </div>
                        <span class="input-group-btn search"><button class="btn btn-sm btn-primary ">搜索</button> </span>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="radio" name='id'>
                                </th>
                                <th>注册账号</th>
                                <th>绑定名称</th>
                                <th>绑定时间</th>
                            </tr>
                            </thead>
                            </thead>
                            <!--数据渲染-->
                            <tbody class="binding">

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
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script>

<script type="text/javascript">

    //商家数据渲染
    function findBingingList(pageNo,id,store_name) {
        $.ajax({
            url:"../seller/Details.do",
            type:"post",
            data:{pageNo:pageNo,id:id,name:store_name},
            beforeSend:function(){
                $(".binding").append("<td class='xuan'>loading...</td>");//显示加载动画
            },
            success:function (res) {
                if (res.code == 1){
                    var business = res.data.records;
                    console.log(business)
                    total = res.data.totalRecordsNum; //总记录数
                    pageSize = res.data.pageSize; //每页显示条数
                    curPage = res.data.pageNum; //当前页
                    totalPage = res.data.totalPageNum; //总页数
                    var businessHtml ='';
                    for ( var i=0; i < business.length;i++){

                        businessHtml += ' <tr>' +
                            '<td class="xuan">' +
                            '<input id="checkbox3" type="radio" name="id" value="'+business[i].id+'">' +
                            '</td>' +
                            '<td style="font-weight: bold">'+business[i].phone+'</td>' +
                            '<td style="font-weight: bold">'+business[i].user_name+'</td>' +
                            '<td>'+timestampToTime(business[i].binding_time)+'</td>'+
                        '</tr>'
                    }
                    $(".binding").empty().append(businessHtml);
                }
            },
            complete:function(){ //生成分页条
                getPageBar();
                funBingingList();
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    };
    var  id = $("#id").val();
    //进入页面加载
    $(function () {

        findBingingList(curPage,id ,"");
    });

    //输入框搜索
    $(document).on("click",".search",function () {
        var store_name = $(".store_name").val();
        findBingingList(1,id ,store_name);
    });
    //点击页数
    function funBingingList(){
        $("#pagecount span a").on('click',function(){
            var store_name = $(".store_name").val();
            var rel = $(this).attr("rel");
            if(rel){
                findBingingList(rel , id ,store_name);
            }
        });
    };
    $(document).on("click",".go",function () {
        var jumpPage = $(".jumpPage").val();
        findBingingList(jumpPage ,id ,"");
    });
</script>
</body>
</html>
