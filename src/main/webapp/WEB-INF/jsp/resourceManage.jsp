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
							<div class="pull-left">资源列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize"><i class="icon-chevron-up"></i></a>
								<a href="#" class="wclose"><i class="icon-remove"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content">
							<div class="col-lg-3">
								<div class="widget treeMinHeight" id="jstree_resource"></div>
							</div>
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form" id="searchform">
									<div class="form-group">
										<div class="col-lg-2">
											<input type="text" class="form-control" placeholder="资源名称" name="name">
										</div>
										<div class="col-lg-2">
											<select class="form-control" name="deviceType">
											  <option value="">资源属性</option>
											  <option value="1">公共资源</option>
											  <option value="2">基础资源</option>
											  <option value="3">私有资源</option>
											</select>
										</div>
										<div class="col-lg-2">
											<select class="form-control" name="buildingId" id="buildingId">
											  <option value="">选择楼栋</option>
											</select>
										</div>
										<div class="col-lg-3">
											<button type="button" class="btn btn-primary" id="dosearch">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#myModal">
												<i class="icon-plus"></i> 新增资源
											</button>
										</div>
									</div>
								</form>
								<table class="table table-striped table-bordered table-hover"
									id="resourceTableId">

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
	<div class="modal-dialog" role="document" style="width:800px;height:600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加资源</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="newResourceFormId">
                                <div class="form-group">
                                  <label class="col-lg-2 control-label">资源名称</label>
                                  <div class="col-lg-4">
                                    <input type="text" class="form-control" placeholder="资源名称">
                                  </div>
                                   <label class="col-lg-2 control-label">Mac地址</label>
                                  <div class="col-lg-4">
                                    <input type="text" class="form-control" placeholder="设备Mac地址">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-2 control-label">楼栋</label>
                                  <div class="col-lg-4">
                                    <select class="form-control" id="addbuildsId">
                                      <option>请选择楼栋</option>
                                    </select>
                                  </div>
                                   <label class="col-lg-2 control-label">楼层</label>
                                  <div class="col-lg-4">
                                    <input type="text" class="form-control" placeholder="楼层(数字)">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-2 control-label">资源类型</label>
                                  <div class="col-lg-4">
                                    <select class="form-control">
                                      <option>资源类型</option>
                                      <option value="1">通行</option>
                                      <option value="2">家居</option>
                                      <option value="3">其它</option>
                                    </select>
                                  </div>
                                   <label class="col-lg-2 control-label">资源属性</label>
                                  <div class="col-lg-4">
                                    <select class="form-control">
                                      <option>资源属性</option>
                                      <option value="1">公共资源</option>
                                      <option value="2">基础资源</option>
                                      <option value="3">私有资源</option>
                                    </select>
                                  </div>
                                </div>
                                 <div class="form-group">
                                  <label class="col-lg-2 control-label">生产厂家</label>
                                  <div class="col-lg-4">
	                                   <select class="form-control">
	                                   	  <option>生产厂家</option>
	                                      <option value="1">厂家1</option>
	                                      <option value="2">厂家2</option>
	                                    </select>
                                  </div>
                                   <label class="col-lg-2 control-label">显示顺序</label>
                                  <div class="col-lg-4">
                                    <input type="text" class="form-control" placeholder="1">
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-lg-2 control-label">可用状态</label>
                                  <div class="col-lg-4">
	                                  <div class="make-switch" data-on="primary" data-off="info">
                         					<input type="checkbox" checked>
                     				  </div>
                                  </div>
                                  <label class="col-lg-2 control-label">可分享</label>
                                  <div class="col-lg-4">
	                                  <div class="make-switch" data-on="primary" data-off="info">
                         					<input type="checkbox" checked>
                     				  </div>
                                  </div>
                                </div>
                              </form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="newResourceId">保存</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
			//给楼栋赋值Ajax
			var buildings;
			$.get("<c:url value="/manage/allBuildings.json" />",function(data,status){
				if(status=4){
					$.each(data, function (n,value) {
						$("#buildingId").append("<option value='"+value.id+"'>"+value.name+"</option>");
						$("#addbuildsId").append("<option value='"+value.id+"'>"+value.name+"</option>");
						buildings=data;
					});
				}
  				});
			function findBuildName(id){
				var tempvalue;
				$.each(buildings, function (n,value) {
					if(id==value.id) {
						tempvalue=value.name;
						return false;
					}
				});
				return tempvalue;
			}
			$('#jstree_resource').jstree({
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
			var pageNumber = 1;
			$('#resourceTableId').bootstrapTable({
				method: 'get',
			    url: "<c:url value='/manage/resourceSearch.json' />", 
			    dataType: "json",
			    pageSize: 2,
			    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
			    pageNumber: pageNumber,
			    pagination: true, //分页
			    singleSelect: false,
			    striped: true,
			    idField: "id",  //标识哪个字段为id主键
			    sidePagination: "server", //服务端处理分页
		       	columns: [
					{
					    title: '名称',
					      field: 'name',
					      align: 'center',
					      valign: 'middle'
					  }, 
		               {
		                 title: '类型',
		                   field: 'deviceType',
		                   align: 'center',
		                   valign: 'middle',
		                   formatter:function (value, row, index) {
		                	   if(value=="1") return "公共资源";
		                	   if(value=="2") return "基础资源";
		                	   if(value=="3") return "私有资源";
	                        }
		               }, 
		               {
		                   title: '楼栋',
		                   field: 'buildingId',
		                   align: 'center',
		                   valign: 'middle',
		                   formatter:function (value, row, index) {
		                	   return findBuildName(value);
	                        }
		               }, 
		               {
		                   title: '状态',
		                   field: 'status',
		                   align: 'center',
		                   formatter:function (value, row, index) {
		                	   if(value=="Y"){
		                		   return '<span class="label label-success">可用</span>';
		                	   }else{
		                		   return '<span class="label label-danger">不可用</span>';
		                	   }
	                        }
		               },
		               {
		                   title: '创建时间',
		                   field: 'createDate',
		                   align: 'center',
		                   formatter:function (value, row, index) {
		                	   return new Date(value).format("yyyy-MM-dd"); 
	                        }
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
			//自定义查询
			 $('#dosearch').click(function() {  
			        var params = $('#resourceTableId').bootstrapTable('getOptions')  
			        params.queryParams = function(params) {  