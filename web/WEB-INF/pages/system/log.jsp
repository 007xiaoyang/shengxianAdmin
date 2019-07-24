<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理后台 - 业务日志</title>
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
                    <h5>业务日志</h5>
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
                        <div class="col-sm-4 m-b-xs">
                            <div class="input-group">
									<span class="input-group-btn">
											<button type="button" class="btn btn-sm btn-default" disabled>账号：</button> </span>
                                <input type="text" id="name" class="input-sm form-control" name="name" value="">
                            </div>
                        </div>
                        <span class="input-group-btn"><button class="btn btn-sm btn-primary buttonlog">搜索</button> </span>

                    </div>

                    <div class="table-responsive">
                        <table class="table table-striped">
                             <thead>
                             <tr>
                                 <th>
                                     <input id="checkbox" type="radio" name='id'>
                                 </th>
                                 <th>用户登录的账号</th>
                                 <th>执行的方法</th>
                                 <th>执行的模块</th>
                                 <th>执行时间</th>
                                 <th>执行描述</th>
                            </tr>
                            </thead>
                            <!--内容渲染-->
                             <tbody class="log">
                            <%--     <c:forEach var="log" items="${page.records}">
                                 <tr>
                                     <td class='xuan'>
                                         <input id="checkbox3" type="radio" name='id' value="${seller.id}">
                                     </td>
                                     <td>${log.userid}</td>
                                     <td>${log.method}</td>
                                     <td>${log.module}</td>
                                     <td>${log.data}</td>
                                     <td>${log.commite}</td>
                                 </tr>
                                 </c:forEach>--%>
                             </tbody>
                         </table>
                         <div class="col-sm-6">
                             <div class="dataTables_paginate paging_simple_numbers" id="pagecount">

                             </div>
                         </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="mask" id="mask"></div>
<div class="xsdj xsdj1">
    <form action="" method="post" name="donjie">
        <input type="hidden" name="id" class="wxid">
        <div class="dj_tit">请输入原因</div>

        <div class="shu_input">
            <textarea name="reason" placeholder="请输入原因" class="reason">
            </textarea>
        </div>
        <div class="row1">
            <ul class="dj_ul">
                <li class="bor" style="cursor:pointer;" >确定</li>
                <li class="quxiao"  style="cursor:pointer;">取消</li>
            </ul>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/statics/js/common.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script><!--分页-->
</body>

<script type="text/javascript">

    //进入页面加载
    $(function () {
        logFind();
    });
    //条件查询客户信息
    $(".buttonlog").on("click",function () {
        name = $("#name").val();
        logFind("",name);
    });
    //点击页数
    function logfun(){
        $("#pagecount span a").on('click',function(){
            var rel = $(this).attr("rel");
            if(rel){
                logFind(rel,"");
            }
        });
    };
    $(document).on("click",".go",function () {
        var jumpPage = $(".jumpPage").val();
        logFind(jumpPage,"");
    });



    //数据渲染接口
   function logFind(pageNo ,name) {
       $.ajax({
           url:"../log/logList.do" ,
           type:"post",
           data:{pageNo:pageNo,name:name},
           beforeSend:function(){
               $(".customer").append("<td class='xuan'>loading...</td>");//显示加载动画
           },
           success:function (rel) {
               var log = rel.data.records;
               total = rel.data.totalRecordsNum; //总记录数
               pageSize = rel.data.pageSize; //每页显示条数
               curPage = rel.data.pageNum; //当前页
               totalPage = rel.data.totalPageNum; //总页数
               var logHtml ='';
               for (var i = 0 ; i <log.length ; i++ ){
                    logHtml += '<tr>' +
                                    '<td class="xuan">' +
                                    '<input id="checkbox3" type="radio" name="id" value="">' +
                                    '</td>' +
                                    '<td>'+log[i].userid+'</td>' +
                                    '<td>'+log[i].method+'</td>' +
                                    '<td>'+log[i].module+'</td>' +
                                    '<td>'+log[i].data+'</td>'+
                                    '<td>'+log[i].commite+'</td>'+
                               '</tr>'
               }
               $(".log").empty().append(logHtml);
           },
           complete:function(){ //生成分页条
               getPageBar();
               logfun();
           },
           error:function (res) {
               toastr.error(res.info());
           }

       });
   }

</script>
</html>
