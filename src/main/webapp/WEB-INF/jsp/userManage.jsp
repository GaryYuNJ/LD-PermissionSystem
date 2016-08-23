<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/nav.jsp"%>

<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 用户管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 用户管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">用户列表</a>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- Page heading ends -->

	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">用户列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
								<a href="#" class="wclose"><i class="icon-remove"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content">
							<div class="col-lg-12">
								<hr>
								<table class="table table-striped table-bordered table-hover"
									id="demoTableId">
									
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Matter ends -->
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>
<!-- Content ends -->
<!-- 弹窗 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加用户</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	
	var getUserURL = "<c:url value='/user/showUser.json' />";
	var pageNumber = 1;
	$('#demoTableId').bootstrapTable({
		method: 'get',
	    url: getUserURL, 
	    dataType: "json",
	    pageSize: 10,
	    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
	    pageNumber: pageNumber,
	    pagination: true, //分页
	    singleSelect: false,
	    idField: "id",  //标识哪个字段为id主键
	    showColumns: true, //显示隐藏列  
	    showRefresh: true,  //显示刷新按钮
	    locale: "zh-CN", //表格汉化
	    search: true, //显示搜索框
	    sidePagination: "server", //服务端处理分页
       	columns: [
			{
			    title: '用户ID',
			      field: 'id',
			      align: 'center',
			      valign: 'middle'
			  }, 
               {
                 title: '用户名称',
                   field: 'name',
                   align: 'center',
                   valign: 'middle'
               }, 
               {
                   title: '性别',
                   field: 'sex',
                   align: 'center',
                   valign: 'middle',
               }, 
               {
                   title: '手机号',
                   field: 'mobile',
                   align: 'center'
               },
               {
                   title: '邮箱',
                   field: 'email',
                   align: 'center'
               },
               {
                   title: '生日',
                   field: 'birthdayStr',
                   align: 'center',
               },
               {
                   title: '客户关系',
                   field: 'relation',
                   align: 'center',
               },
               {
                   title: '操作',
                   field: 'id',
                   align: 'center',
                   formatter:function(value,row,index){
                 var e = '<a href="#" mce_href="#" onclick="edit(\''+ row.id + '\')">编辑</a> ';  
                 var d = '<a href="#" mce_href="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                    return e+d;  
                 } 
               }
           ],
		formatLoadingMessage: function () {
	    	return "请稍等，正在加载中...";
	  	}
      });
			
</script>
<%@ include file="/common/footer.html"%>
