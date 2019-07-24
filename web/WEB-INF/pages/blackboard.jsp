<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>生鲜后台 - 主页</title>
	<meta name="keywords" content="生鲜后台">
	<meta name="description" content="生鲜后台">
	<!--[if lt IE 9]>
	<meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->
	<link rel="shortcut icon" href="${pageContext.request.contextPath }/statics/img/APPLOGO.jpg">
	<link href="${pageContext.request.contextPath }/statics/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/statics/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/statics/css/animate.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath }/statics/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<style>
		.ord1-list,.ord2-list,.ord3-list,.ord4-list{
			padding-left:10px;
		}
		.ord1-list>li,.ord2-list>li,.ord3-list>li,.ord4-list>li{
			list-style: none;
			overflow: hidden;
			margin-top: 15px;
		}
		.ord1-list>li>span{
			float: left;
			width:100px;
		}
		.ord1-list>li>input{
			float: left;
		}

		.ord2-list>li>span{
			float: left;
			width:60px;
			text-align: center;
			margin-right: 25px;
		}
		.ord3-list>li>span{
			float: left;
			width:70px;
			text-align: center;
			margin-right: 25px;
		}
		.ord4-list>li>span{
			float: left;
			width:120px;
			text-align: center;
		}
	</style>
</head>
<body>
<div class="wrapper wrapper-content">
	<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-3 m-b-xs">
			<div class="input-group">
				<span class="input-group-btn"><button type="button" class="btn btn-sm btn-default" disabled>商家</button> </span>
				<select class="input-sm form-control" name="type">
					<option value=""></option>
					<c:forEach var="noteType" items="${noteTypes}">
						<option value="${noteType.id}" <c:if test="${noteType.id==type}">selected</c:if>>${noteType.type}</option>
					</c:forEach>
				</select>
				<span class="input-group-btn"><button class="btn btn-sm btn-primary"> 搜索</button> </span>
			</div>
		</div>
	</div>
	</div>
	<br/>
	<div class="col-sm-12">
	<div class="row">
			<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div style="display: none">
					<input type="text" value="1" id="type">
				</div>
				<div class="ibox-title">
					<h5>销售统计</h5>
					<div class="ibox-tools">
						<a class="	refresh-link" href="javascript:location.reload()">
							<i class="fa fa-refresh"></i>
						</a>
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<div>
					<ul class="ord1-list" id="ord1list">
						<li><span>总销售额</span><input type="text" readonly></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>订单统计</h5>
					<div class="ibox-tools">
						<a class="refresh-link" href="javascript:location.reload()">
							<i class="fa fa-refresh"></i>
						</a>
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<ul class="ord2-list" id="ord2-list">
					<li><span>订单总数</span><input type="text" readonly></li>

				</ul>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>订单金额统计</h5>
					<div class="ibox-tools">
						<a class="refresh-link" href="javascript:location.reload()">
							<i class="fa fa-refresh"></i>
						</a>
						<a class="collapse-link">
							<i class="fa fa-chevron-up"></i>
						</a>
					</div>
				</div>
				<ul class="ord3-list" id="ord3-list">
					<li><span>订单总金额</span><input type="text" readonly></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>统计报表</h5>
					<div class="pull-right">
						<div class="btn-group">
							<button type="button" class="btn btn-xs btn-white active" id="today">今天</button>
							<button type="button" class="btn btn-xs btn-white" id="yestoday">昨天</button>
							<button type="button" class="btn btn-xs btn-white" id="lastweek">最近一周</button>
							<button type="button" class="btn btn-xs btn-white" id="lastmonth">最近一个月</button>
							<button type="button" class="btn btn-xs btn-white" id="lastyear">一年</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
			<div class="col-sm-8">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>柱状图</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">

						<div class="echarts" id="echarts-bar-chart"></div>
					</div>
				</div>
			</div>
		<div class="col-sm-4">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<div class="col-sm-3" id="col3">

					</div>
				</div>
			</div>
		</div>
	</div>

</div>
</div>
<script src="${pageContext.request.contextPath }/statics/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath }/statics/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${pageContext.request.contextPath }/statics/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/plugins/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/contabs.min.js"></script>
<script src="${pageContext.request.contextPath }/statics/js/plugins/pace/pace.min.js"></script>
</body>
</html>
