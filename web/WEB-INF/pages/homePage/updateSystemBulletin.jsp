<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>诚亿森管理系统总后台 - 修改内容</title>
    <meta name="keywords" content="诚亿森管理系统总后台">
    <meta name="description" content="诚亿森管理系统总后台">
    <link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/statics/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<style>
    .fixed-table-container{
        border-bottom: none;
        padding-bottom: 0px;
    }
    .attachments{
        width: 243px;
        height: 161px;
        cursor: pointer;
    }
    .col-sm-2 .subnet{
        width: 95%;
        height: 100%;
        position: absolute;
        background: rgba(0, 0, 0, 0.7);
        text-align: center;
        display: none;
    }
    @media (min-width: 768px){
        .col-sm-2 .subnet{
            width: 89%;
        }
    }
    .col-sm-2 .subnet button{
        margin-top: 27%;
        display:none;
    }
    .progress {
        height: 20px;
        overflow: hidden;
        background-color: #ECECEC;
        -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,.1);
        box-shadow: inset 0 1px 2px rgba(0,0,0,.1);
        position: relative;
        width: 70%;
        margin-left: 15%;
        padding: 1px;
        margin-top: 27%;
        display:none;
    }
    .bar {
        background-color: green;
        display: block;
        width: 0%;
        height: 20px;

    }
    .percent {
        position: absolute;
        height: 20px;
        display: inline-block;
        top: 3px;
        left: 2%;
        color: #FFFFFF;
    }
    .col-sm-1{
        padding-top: 5px;
        color: rgb(197, 197, 197);
    }
    .hover{
        color: rgb(132, 132, 132);
    }
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>修改内容</h5>
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
                    <form class="form-horizontal m-t" id="signupForm" method="POST">
                        <input type="hidden" value="" name="id"/>
                        <div class="row" style="margin-left: 200px">
                            <div class="form-group">
                                <input type='text' value="${id}" name='noticeid' id='noticeid'style="display: none" >
                                <label class="col-sm-2 control-label">系统公告内容：：</label>

                                <div class="col-sm-1 noticeContent">
                                 <textarea class="system" name="内容"  value="" id="noEd" style="color: #2E2D3C ; font-size: 16px; background-color: transparent; width:500px; height:300px; ">
                                 </textarea>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12 col-sm-offset-2">
                                <button class="col-sm-4 btn btn-primary updateSystem" type="button">
                                    <i class="fa fa-edit"></i>
                                    <span class="bold">修改</span>
                                </button>
                                <button class="col-sm-4 btn btn-warning btn-outline" type="button">
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
                    <input type="text"  class="input-group form-control uid" style="display:none" value=""   />
                    <input type="text"  class="input-group form-control sendSMS" style="display:none" value=""   />
                    <input type="text" class="input-group form-control sendsms" value=""   />
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button"  class="btn btn-primary upateSystem" >
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<input id="fileupload" type="file" name="mypic" style="display:none">
<script src="${pageContext.request.contextPath}/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery.form.js?v=3.1.5"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/toastr.options.js"></script>
<script src="${pageContext.request.contextPath}/statics/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/statics/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/myjs/notice.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/jquery.cookie.js?v=1.4.1"></script>
<script src="${pageContext.request.contextPath}/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath}/statics/js/content.min.js?v=1.0.0"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/plugins/validate/messages_zh.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/js/demo/form-validate-demo.min.js"></script>
<script type="text/javascript">
    $(function () {
        var nid = $("#noticeid").val();
        systemBulletinById(nid);
    });
    //获取验证码
    $(document).on("click",".updateSystem",function () {
        var phone ="18927429991";
        layer.confirm("是否获取手机验证码?",{
            btn:["确认","取消"]
        },function(d){
            layer.close(d);
          /*  $(".sendSMS").val("666888");
            $('#myModal').modal('show');*/
            $.ajax({
                url:"../seller/sendSms.do",
                type:"post",
                data:{phone:phone},
                success:function (res) {
                    if (res.code == 1){
                        layer.close(d);
                        $(".sendSMS").val(res.data);
                        $('#myModal').modal('show');
                    }else {
                        toastr.error("获取验证码失败");
                    }
                },
                error:function (res) {
                    toastr.error(res.info);
                }
            });
        });
    });

    //确认验证码是否正确 正确则修改
    $(document).on("click",".upateSystem",function () {
        var  sendSMS = $(".sendSMS").val();//短信验证码
        var  send = $(".sendsms").val();//输入的验证码
        if(sendSMS != send){
            alert("验证码不正确！");
            return;
        };
        var nid = $("#noticeid").val();
        var content = $("#noEd").val();
        $.ajax({
            type:"POST",
            url: "../homePage/UpdateSystemBulletin.do",
            data:{id:nid ,noticeContent:content},
            success: function (result) {
                if(result.code==1){
                    toastr.success(result.info);
                    setTimeout(function(){
                        parent.location.href = "../homePage/JumpSystemBulletin.do";
                        parent.layer.closeAll();
                    }, 2000);
                }else{
                    toastr.error(result.info);
                }
            }
        });
    });



</script>

</body>
</html>

