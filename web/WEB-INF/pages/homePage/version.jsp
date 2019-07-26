<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - app版本号管理</title>
    <meta name="keywords" content="诚亿森管理系统总后台">
    <meta name="description" content="诚亿森管理系统总后台">
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
                    <h5>app版本号管理</h5>
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
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <input id="checkbox" type="checkbox" >
                                </th>
                                <th>所属</th>
                                <th>版本号</th>
                                <th>发布时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!--内容渲染-->
                            <tbody class="version">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- 修改版本号模态框（Modal） -->
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
                    <button type="button"  class="btn btn-primary confirm" >
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<div class="mask" id="mask"></div>
<div class="xsdj xsdj1 " style="width: 550px; height: 80px;">
    <form action="" method="post" name="donjie">
        <input type="hidden" name="id" class="wxid" value="">

        <span class="dj_tit" style="font-size: 20px">版本号：</span>
        <input id="version" style="margin: 15px 0px; width: 350px; height: 35px;"  type="text" class="input-group-sm "/>
        <div class="row1">
            <ul class="dj_ul" style="background-color: #f0f0f0">
                <li class="bor" style="cursor:pointer;" >确定</li>
                <li class="quxiao"  style="cursor:pointer; ">取消</li>
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
<script src="${pageContext.request.contextPath}/statics/js/myjs/encapsulates.js"></script>
<script type="text/javascript">
    $(function () {
        findVersion();
    });


    //渲染数据
    function findVersion() {
        $.ajax({
            url:"../homePage/version.do",
            type:"post",
            success:function (res) {
                if(res.code == 1){
                    var version = res.data;
                    var versionHtml ="";
                    console.log(version)
                    for(var i=0 ; i < version.length; i++ ){
                        var type = "";
                        if(version[i].type == 1){
                            type ='<span style="font-size: 16px;">用户端APP</span>'
                        }else if(version[i].type == 2){
                            type ='<span style="font-size: 16px;">服务端APP</span>'
                        }else if(version[i].type == 3){
                            type ='<span style="font-size: 16px;">员工端APP</span>'
                        }
                        versionHtml += '<tr>' +
                                        '<td class="xuan">' +
                                            '<input id="checkbox3" type="radio" name="id" value="'+version[i].id+'">' +
                                        '</td>' +
                                        '<td>'+type+'</td>'+
                                        '<td>'+version[i].version +'</td>' +
                                        '<td>'+timestampToTime(version[i].create_time)+'</td>' +
                                        '<td>' +
                                            '<button class="btn btn-primary btn-sm updateVersion" style="margin-right: 5px" type="button" val="'+version[i].id+'">' +
                                                '<i class="fa fa-edit"></i>' +
                                                '<span class="bold">修改版本号</span>' +
                                            '</button>'
                                        '</td>' +
                                        '</tr>'
                    }
                    $(".version").empty().append(versionHtml);
                }else {
                    toastr.error(res.info);
                }

            },
            error:function (res) {
                toastr.error(res.info);
            }
        });
    };

    //点击修改版本号
    $(document).on("click",".updateVersion",function () {
        var vid = $(this).attr("val");
       layer.confirm("是否获取验证码？",{
           btn:["确定","取消"]
        },function (d) {
           layer.close(d);
           $(".uid").val(vid);
           $(".sendSMS").val("888");
           $('#myModal').modal('show');

        })
    });

    //确认
    $(document).on("click",".confirm",function () {
        var  sendSMS = $(".sendSMS").val();//短信验证码
        var  send = $(".sendsms").val();//输入的验证码
        if(sendSMS != send){
            alert("验证码不正确！");
            return;
        };
        $('#myModal').modal('hide');
        $(".xsdj").show();
        $(".mask").show();
    });

    //修改版本号
    $(document).on("click",".bor",function () {
        var vid = $(".uid").val();
        var version = $("#version").val();
        $.ajax({
            url:"../homePage/updateVersion.do",
            type: "POST",
            data:{id:vid,version:version},
            success:function (res) {
                if(res.code == 1){
                    toastr.success(res.info);
                    location.reload();
                }else {
                    toastr.error(res.info)
                }
            },
            error:function (res) {
                toastr.error(res.info);
            }
        });

    });
    // 取消
    $(".quxiao").on("click",function () {
        $(".xsdj").hide();
        $(".mask").hide();
    });


</script>
</body>
</html>
