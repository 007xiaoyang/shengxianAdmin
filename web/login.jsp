<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <title>生鲜管理后台 - 登录</title>
  <meta name="keywords" content="生鲜管理后台">
  <meta name="description" content="生鲜管理后台">
  <link rel="shortcut icon" href="statics/img/APPLOGO.jpg">
  <link href="statics/css/bootstrap.min.css" rel="stylesheet">
  <link href="statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
  <link href="statics/css/animate.min.css" rel="stylesheet">
  <link href="statics/css/style.min.css" rel="stylesheet">
  <link href="statics/css/login.min.css" rel="stylesheet">
  <link href="statics/css/plugins/iCheck/custom.css" rel="stylesheet">
  <!--[if lt IE 9]>
  <meta http-equiv="refresh" content="0;ie.html" />
  <![endif]-->
  <script>
      if(window.top!==window.self){window.top.location=window.location};
  </script>
</head>
<style>
  .col-sm-5 i.reloadCode{
    position: absolute;
    width: 15px;
    height: 15px;
    margin-top: 9px;
    right: 58px;
    cursor: pointer;
  }
  .col-sm-5 img {
    margin: 0 auto;
    width: 233px;
    height: 50px;
    margin-bottom: 15px;
  }
  span.help-block{
    display:none!important;
  }
  .layui-layer .layui-layer-content{
    color: #5A5A5A;
  }
  @media screen and (max-width: 768px){
    .col-sm-5 img {
      width: 318px!important;
      height: 69px!important;
    }
    .col-sm-5 i {
      right: 62px!important;
    }
  }
  .btn-default.active{
    background-color: #00B9FC;
  }
  .col-sm-5{
    background-color: #ffffee;
  }
</style>
<body class="signin" style="background-image: url('statics/img/OOOPIC53_1024.jpg')">
<div class="signinpanel">
  <div class="row">
    <div class="col-sm-3">

    </div>
    <div class="col-sm-5">
      <form method="post" action="admin/login.do" id="signupForm" class=" m-t">
        <h2 style="color: rgb(102, 100, 100);font-weight:900;">生鲜管理后台</h2>
        <p class="m-t-md" style="  color: rgb(102, 100, 100);"></p>

        <div class="form-group">
          <input type="text" class="form-control uname" placeholder="账号" name="account" aria-required="true" value=""/>
        </div>
        <div class="form-group">
          <input type="password" class="form-control pword m-b" placeholder="密码" name="password"  aria-required="true" value=""/>
          <i class="reloadCode"></i>
        </div>
        <%--<div class="form-group">
          <input type="text" id="verify" name="verify" value="" class="form-control reload m-b" placeholder="验证码" aria-required="true" required="验证码"/>
        </div>

        <img class="verifyimg reloadCode m-b" title="看不清楚请点击这里" alt="点击切换" src="authImage"/>--%>
        <div style="color: red"><%=request.getParameter("result")==null?"":request.getParameter("result") %></div>
        <button class="btn btn-primary btn-block">登录</button>
      </form>
    </div>
  </div>
  <div class="signup-footer">
    <div class="pull-left" style="width: 94%;text-align: center;color: rgb(102, 100, 100);color: #030303">
      &copy; 2018 All Rights Reserved. 生鲜管理后台
    </div>
  </div>
</div>
</body>
<script src="statics/js/jquery.min.js?v=2.1.4"></script>
<script src="statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="statics/js/plugins/iCheck/icheck.min.js"></script>
<script src="statics/js/plugins/validate/jquery.validate.min.js"></script>
<script src="statics/js/plugins/validate/messages_zh.min.js"></script>
<script src="statics/js/demo/form-validate-demo.min.js"></script>
<script src="statics/layer/layer.js"></script>
<script src="statics/layer/extend/layer.ext.js"></script>
<script src="statics/js/index.js"></script>
<script>
    $(function(){
        //刷新验证码
        $(".reloadCode").click(function(){
            var rand=new Date().getTime();
            $(".verifyimg").attr("src", "authImage?abc="+rand);
        });
    });


</script>
</html>