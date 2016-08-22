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
			<i class="icon-home"></i> 资源组管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 资源组管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">资源列表</a>
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
							<div class="pull-left">资源组列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
								<a href="#" class="wclose"><i class="icon-remove"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content">
							<div class="col-lg-3">
								<div class="widget treeMinHeight" id="jstree_demo"></div>
							</div>
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
										<div class="col-lg-3">
											<input type="text" class="form-control" placeholder="资源组名称">
										</div>
										<div class="col-lg-3">
											<button type="button" class="btn btn-primary">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#myModal">
												<i class="icon-plus"></i> 新增资源组
											</button>
										</div>
									</div>
								</form>
								<table class="table table-striped table-bordered table-hover"
									id="demoTableId">

								</table>
							</div>
							<div class="widget-foot">
								<ul class="pagination pull-right">
									<li><a href="#">Prev</a></li>
									<li><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">Next</a></li>
								</ul>
								<div class="clearfix"></div>
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
				<h4 class="modal-title" id="myModalLabel">添加资源组</h4>
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
			$('#jstree_demo').jstree({
				"core": {
					"animation": 0,
					"check_callback": true,
					"themes": {
						"stripes": true
					},
					'data': {
						'url': "<c:url value="/manage/showNode.json" />",
						'data': function(node) {
							return {
							};
						}
					}
				},
				"types": {
					"#": {
						"max_children": 1,
						"max_depth": 4,
						"valid_children": ["root"]
					},
					"root": {
						"icon": "<c:url value="/js/themes/default/tree_icon.png" />",
						"valid_children": ["default"]
					},
					"default": {
						"valid_children": ["default", "file"]
					},
					"file": {
						"icon": "glyphicon glyphicon-file",
						"valid_children": []
					}
				},
				"plugins": [
					"contextmenu", "dnd", "search",
					"state", "types", "wholerow"
				]
			});
		</script>
<%@ include file="/common/footer.html"%>
