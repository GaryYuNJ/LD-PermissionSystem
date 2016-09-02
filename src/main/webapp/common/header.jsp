<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- Title and other stuffs -->
<title>绿地权限管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="">
<!-- Stylesheets -->
<link href="<c:url value="/style/bootstrap.css" />" rel="stylesheet">
<!-- Font awesome icon -->
<link rel="stylesheet" href="<c:url value="/style/font-awesome.css" />">
<!-- Date picker -->
<link rel="stylesheet" href="<c:url value="/style/bootstrap-datetimepicker.min.css" />">
<!-- Bootstrap toggle -->
<link href="<c:url value="/style/style.css" />" rel="stylesheet">
<link href="<c:url value="/js/themes/default/style.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrap-table.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrap-switch.css" />" rel="stylesheet">
<link href="<c:url value="/style/bootstrapValidator.min.css" />" rel="stylesheet">
<link href="<c:url value="/style/fileinput.min.css" />" rel="stylesheet">
<!-- HTML5 Support for IE -->
<!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->
<!-- Favicon -->
<link rel="shortcut icon" href="<c:url value="/img/favicon/favicon.png" />">
</head>

<body>
	<!-- Header starts -->
	<header>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="login-header"></div>
				</div>
				<div class="col-md-4" style="padding-top: 10px">
					<h1>绿地权限管理系统</h1>
				</div>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown pull-right"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i class="icon-user"></i>
							管理员 <b class="caret"></b>
					</a> <!-- Dropdown menu -->
						<ul class="dropdown-menu">
							<li><a href="#"><i class="icon-user"></i> 资料</a></li>
							<li><a href="#"><i class="icon-cogs"></i> 设置</a></li>
							<li><a href="login.html"><i class="icon-off"></i> 退出</a></li>
						</ul></li>
				</ul>
			</div>
	</header>
	<!-- Header ends -->