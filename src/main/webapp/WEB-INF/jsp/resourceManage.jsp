<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/nav.jsp"%>
<!-- Main bar -->
<div class="mainbar">
	<!-- Page heading -->
	<div class="page-head">
		<h2 class="pull-left">
			<i class="icon-home"></i> 资源管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 资源管理页面</a>
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
								<a href="#" class="wminimize" id="icon_group_list1"><i
									class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content">
							<div class="col-lg-3">
								<hr>
								<button type="button" class="btn btn-primary"
									onclick="nodeCreate()">
									<i class="icon-plus"></i> 添加
								</button>
								<button type="button" class="btn btn-warning"
									onclick="nodeRename()">
									<i class="icon-pencil"></i> 修改
								</button>
								<button type="button" class="btn btn-danger"
									onclick="nodeDelete()">
									<i class="icon-remove"></i> 删除
								</button>
								<hr>
								<div class="widget treeMinHeight" id="jstree_resource"></div>
							</div>
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form" id="searchform">
									<div class="form-group">
										<div class="col-lg-2">
											<input type="text" class="form-control" placeholder="资源名称"
												name="name">
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
											<select class="form-control" name="buildingId"
												id="buildingId">
												<option value="">选择楼栋</option>
											</select>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary" id="dosearch">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												id="addResourceButton">
												<i class="icon-plus"></i> 新增资源
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#importModal">
												<i class="icon-plus"></i> 导入资源
											</button>
										</div>
									</div>
								</form>
								<table class="table table-striped table-bordered table-hover"
									id="resourceTableId">
								</table>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Matter ends -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">资源组详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i
									class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" style="display: none;"
							id="groupGroupDetailsTable">
							<ul class="nav nav-tabs" id="myTab">
								<li class="active"><a href="#home">资源详情</a></li>
								<li><a href="#resourceGroupResource">资源与资源组</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="home">
									<div class="col-lg-3">
										<div class="widget treeMinHeight" id="jstree_updateResource"></div>
									</div>
									<div class="col-lg-9">
										<form class="form-horizontal" role="form"
											id="resourceFormId">
											<div class="form-group">
												<label class="col-lg-2 control-label">资源名称</label>
												<div class="col-lg-4">
													<input type="text" class="form-control" placeholder="资源名称"
														name="name" id="updateResourceName">
												</div>
												<label class="col-lg-2 control-label">显示顺序</label>
												<div class="col-lg-4">
													<input type="text" class="form-control" placeholder="1000"
														name="sequence" id="updateResourceSequenceId">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">楼栋</label>
												<div class="col-lg-4">
													<select class="form-control" id="updateResourceBuilding"
														name="buildingId">
														<option value="">请选择楼栋</option>
													</select>
												</div>
												<label class="col-lg-2 control-label">楼层</label>
												<div class="col-lg-4">
													<input type="text" class="form-control"
														placeholder="楼层(数字)" name="floor" id="updateResourceFloor">
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">资源类型</label>
												<div class="col-lg-4">
													<select class="form-control" name="deviceType"
														id="updateResourceDeviceType">
														<option value="">资源类型</option>
														<option value="1">通行</option>
														<option value="2">家居</option>
														<option value="3">其它</option>
													</select>
												</div>
												<label class="col-lg-2 control-label">资源属性</label>
												<div class="col-lg-4">
													<select class="form-control" name="permissionAttrId"
														id="updateResourcePermissionAttrId">
														<option value="1">公共资源</option>
														<option value="2">基础资源</option>
														<option value="3">私有资源</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-2 control-label">可用状态</label>
												<div class="col-lg-4">
													<div class="make-switch" data-on="primary" data-off="info">
														<input type="checkbox" checked name="status"
															id="updateResourceStatus">
													</div>
												</div>
												<label class="col-lg-2 control-label">可分享</label>
												<div class="col-lg-4">
													<div class="make-switch" data-on="primary" data-off="info">
														<input type="checkbox" checked name="shareEnable"
															id="updateResourceShareEnable">
													</div>
												</div>
											</div>
											<input type="hidden" name="id" id="updateResourceId">
											<input type="hidden" name="nodeId" id="updateResourceNodeId">
											<input type="hidden" name="nodePath" id="updateResourceNodePath">
											
										</form>
										<hr>
										<form class="form-horizontal" role="form"
											id="ResourceKeyFormId">
											
										</form>
									<div class="center">
									<button type="button" class="btn btn-primary"
										id="updateResourceKeyButtonId">
										<i class="icon-plus"></i>新增钥匙
									</button>
									<button type="button" class="btn btn-primary"
										id="updateResourceButtonId">保存</button>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="resourceGroupResource"></div>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>

<!-- 导入资源Modal -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
	aria-labelledby="importModalLabel">
	<div class="modal-dialog" role="document"
		style="width: 800px; height: 600px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="importModal">导入资源</h4>
				<a href="<c:url value="/common/ResourceUploadSample.xlsx" />">示例文件下载</a>
			</div>
			<div class="modal-body">
				<form action="uploadFile" id="uploadFileForm" method="post"
					enctype="multipart/form-data">
					<input id="input-folder-1" type="file" name="file"
						class="file-loading">
					<!-- <input type="submit" value="submit"> -->
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="uploadModalClose">关闭</button>
				<!-- <button type="button" class="btn btn-primary" id="importResourceButtId">保存</button> -->
			</div>
		</div>
	</div>
</div>


<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	function deleteKey(obj) {
		$(obj).parent().parent().remove();
	}
	//添加新钥匙
	function addResourceKey(objForm) {
		console.log(objForm);
		$(objForm)
				.append(
						'<div class="form-group resourceKeyForm">'
								+ '<label class="col-lg-3 control-label">钥匙信息</label>'
								+ '<div class="col-lg-3">'
								+ '<select class="form-control keyName" name="manufacturerId">'
								+ ' <option value="1">特斯连</option>'
								+ '<option value="2">立方</option>'
								+ ' </select>'
								+ '</div>'
								+ '<div class="col-lg-3">'
								+ '<input type="text" class="form-control keyName" placeholder="设备Mac地址" name="mac">'
								+ '</div>'
								+ '<div class="col-lg-3">'
								+ '<input type="text" class="form-control keyName" placeholder="密码" name="password">'
								+ ' </div>'
								+ '<div class="col-lg-1">'
								+ '<button type="button" class="btn btn-danger" onclick="deleteKey(this)"><i class="icon-remove"></i>删除</button>'
								+ '</div>' + ' </div>');
	}

	$("#updateResourceKeyButtonId").click(function() {
		addResourceKey($("#ResourceKeyFormId"))
	});
	//重命名节点触发
	$('#jstree_resource').on(
			"rename_node.jstree",
			function(e, node) {
				console.log(node);
				//新建节点
				if (node.node.id.indexOf("j") == 0) {
					var nodeModel = {
						name : node.text,
						parentId : node.node.parent
					};
					$.post("<c:url value='/manage/addorUpdateNode.json' />",
							nodeModel, function(data) {
								if (data.status == "0") {
									node.instance.set_id(node.node,
											data.message);
								} else {
									node.instance.refresh();
								}
							});
				} else {
					if (node.text != node.old) {
						var nodeModel = {
							id : node.node.id,
							name : node.text,
							parentId : node.node.parent
						};
						//更新节点
						$.post("<c:url value='/manage/updateNode.json' />",
								nodeModel, function(data) {
									if (data.status == "1") {
										node.instance.refresh();
									}
								});
					}
				}
				//console.log(node);
			});

	//删除节点触发方法
	$('#jstree_resource').on("delete_node.jstree", function(e, node) {
		if (node.node.children.length > 0) {
			alert("请先删除子节点");
			node.instance.refresh();
			return false;
		}
		$.post("<c:url value='/manage/deleteNode.json' />", {
			"nodeId" : node.node.id
		}, function(data) {
			if (data.status == "1") {
				node.instance.refresh();
			}
		});
	});

	//创建节点
	function nodeCreate() {
		var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
		if (!sel.length) {
			return false;
		}
		sel = sel[0];
		sel = ref.create_node(sel);
		if (sel) {
			ref.edit(sel);
		}
	};

	//重命名节点
	function nodeRename() {
		var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
		if (!sel.length) {
			return false;
		}
		sel = sel[0];
		ref.edit(sel);
	};

	//删除节点
	function nodeDelete() {
		var ref = $('#jstree_resource').jstree(true), sel = ref.get_selected();
		if (!sel.length) {
			return false;
		}
		ref.delete_node(sel);
	};

	//初始化FORM
	function initResourceForm(){
		$("#updateResourceName").val("");
		$("#updateResourceMac").val("");
		$("#updateResourceBuilding").val("");
		$("#updateResourceFloor").val("");
		$("#updateResourceDeviceType").val("");
		$("#updateResourcePermissionAttrId").val("1");
		$("#updateResourceManufacturerId").val("");
		$("#updateResourceShareEnable").val("");
		$("#updateResourceStatus").val("");
		$("#updateResourceSequenceId").val("");
		$("#updateResourceId").val("");
		$("#updateResourceNodeId").val("");
		$("#updateResourceNodePath").val("");
		$('#jstree_updateResource').jstree(true).deselect_all();
		$("#ResourceKeyFormId").html('<div class="form-group resourceKeyForm">'+
				'<label class="col-lg-2 control-label">钥匙信息</label>'+
				'<div class="col-lg-3">'+
					'<select class="form-control keyName" name="manufacturerId">'+
						'<option value="1">特斯连</option>'+
						'<option value="2">立方</option>'+
					'</select>'+
				'</div>'+
				'<div class="col-lg-3">'+
					'<input type="text" class="form-control keyName"'+
						'placeholder="设备Mac地址" name="mac">'+
				'</div>'+
				'<div class="col-lg-3">'+
					'<input type="text" class="form-control keyName"'+
						'placeholder="密码" name="password">'+
				'</div>'+
			'</div>');
	}
	//编辑资源
	function resourceEditPre(row) {
			$.post("<c:url value='/manage/getResourceById.json' />",
						{
							"id" : row
						},
						function(data) {
							$("#updateResourceName").val(data.name);
							$("#updateResourceMac").val(data.mac);
							$("#updateResourceBuilding").val(data.buildingId);
							$("#updateResourceFloor").val(data.floor);
							$("#updateResourceDeviceType").val(data.deviceType);
							$("#updateResourcePermissionAttrId").val(
									data.permissionAttrId);
							$("#updateResourceManufacturerId").val(
									data.manufacturerId);
							$("#updateResourceShareEnable").val(
									data.shareEnable);
							$("#updateResourceStatus").val(data.status);
							$("#updateResourceSequenceId").val(data.sequence);
							$("#updateResourceId").val(data.id);
							$("#ResourceKeyFormId").html("");
							$("#updateResourceNodeId").val(data.nodeId);
							$("#updateResourceNodePath").val(data.nodePath);
							$('#jstree_updateResource').jstree(true).deselect_all();
							$('#jstree_updateResource').jstree(true).select_node(data.nodeId);
							$.each(data.resourceKeys,function(n, value) {
												var tempHtml = '<div class="form-group resourceKeyForm">'
														+ '<label class="col-lg-3 control-label">钥匙信息</label>'
														+ '<div class="col-lg-3">'
														+ '<select class="form-control keyName" name="manufacturerId">';
												if (value.manufacturerId == 1)
													tempHtml += '<option value="1">特斯连</option>';
												else
													tempHtml += '<option value="2">立方</option>';

												tempHtml += '</select>'
														+ '</div>'
														+ '<div class="col-lg-3">'
														+ '<input type="text" class="form-control keyName" placeholder="设备Mac地址" name="mac" value="'+value.mac+'">'
														+ '</div>'
														+ '<div class="col-lg-3">'
														+ '<input type="text" class="form-control keyName" placeholder="密码" name="password" value="'+value.password+'">'
														+ ' </div>'
														+ '<div class="col-lg-1">';
												if (n > 0)
													tempHtml += '<button type="button" class="btn btn-danger" onclick="deleteKey(this)"><i class="icon-remove"></i>删除</button>';
												tempHtml += '</div>'
														+ '<input type="hidden" class="keyName" value="'+value.id+'" name="id"></div>';
												$("#ResourceKeyFormId")
														.append(tempHtml);
											});
						});
	}
	//添加资源
	function addResource(){
			$("#updateResourceButtonId").attr('disabled', "true");
			$('#resourceFormId').bootstrapValidator('validate');
			$("#ResourceKeyFormId").bootstrapValidator('validate');
			if ($('#resourceFormId').data('bootstrapValidator').isValid()&&$('#ResourceKeyFormId').data('bootstrapValidator').isValid()){
				var resourceModel = {};
				$.each($("#resourceFormId").serializeArray(),
								function(i, field) {
									resourceModel[field.name] = field.value;
								});
				var resourcekeys = new Array();
				$.each(
						$("#ResourceKeyFormId .resourceKeyForm"),
						function(i) {
							var resourceKey = {};
							$.each($($("#ResourceKeyFormId .resourceKeyForm")[i]).find(".keyName").serializeArray(),
											function(i,field) {
												resourceKey[field.name] = field.value;
											});
							resourcekeys[i] = resourceKey;
						});
				resourceModel["resourceKeys"] = resourcekeys;
				var data = {
					"resourceModelJson" : JSON
							.stringify(resourceModel)
				}
				$.post(
						"<c:url value='/manage/addResource.json' />",
						data, function(data,state) {
							console.log(state);
							if (0 == data.status) {
								alert("保存成功！");
							} else {
								alert("后台异常！");
							}
						});
			}
			$("#updateResourceButtonId").removeAttr('disabled');
	}
	//更新资源数据
	function updateResource(){
		$("#updateResourceButtonId").attr('disabled', "true");
		$('#resourceFormId').bootstrapValidator('validate');
		$("#ResourceKeyFormId").bootstrapValidator('validate');
		if ($('#resourceFormId').data('bootstrapValidator').isValid()&&$('#ResourceKeyFormId').data('bootstrapValidator').isValid()){
			var resourceModel = {};
			$.each($("#resourceFormId").serializeArray(),function(i, field) {
								resourceModel[field.name] = field.value;
							});
			var resourcekeys = new Array();
			$.each(
					$("#ResourceKeyFormId .resourceKeyForm"),
							function(i) {
								var resourceKey = {};
								$.each(
										$($("#ResourceKeyFormId .resourceKeyForm")[i]).find(".keyName").serializeArray(),
											function(i,
													field) {
												resourceKey[field.name] = field.value;
											});
								resourcekeys[i] = resourceKey;
							});
			resourceModel["resourceKeys"] = resourcekeys;
			var data = {
				"resourceModelJson" : JSON
						.stringify(resourceModel)
			}
			$.post("<c:url value='/manage/updateResource.json' />",
					data, function(data) {
						if (0 == data.status) {
							alert("保存成功！");
						} else {
							alert("后台异常！");
						}
					});

		}
		$("#updateResourceButtonId").removeAttr('disabled');
	}
	
	$('#updateResourceButtonId').click(
					function() {
						if($("#updateResourceId").val()!=null&&$("#updateResourceId").val()!=""){
							updateResource();
						}else{
							addResource();
						}
					});

	//查询刷新
	function refreshSearch() {
		var params = $('#resourceTableId').bootstrapTable('getOptions')
		params.queryParams = function(params) {
			//定义参数  
			var search = {};
			//遍历form 组装json  
			$.each($("#searchform").serializeArray(), function(i, field) {
				//console.info(field.name + ":" + field.value + " ");  
				//可以添加提交验证  
				search[field.name] = field.value;
			});
			//参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
			params.search = JSON.stringify(search)
			return params;
		}
		console.info(params);
		$('#resourceTableId').bootstrapTable('refresh', params)
	}

	//删除
	function resourceDel(resourceId) {
		var data = {
			"resourceId" : resourceId
		};
		$.post("<c:url value='/manage/delResource.json' />", data, function(
				data) {
			if (data.status == "0") {
				refreshSearch();
			}
		});
	}

	//给楼栋赋值Ajax
	var buildings;
	$.get("<c:url value="/manage/allBuildings.json" />",
			function(data, status) {
				if (status = 4) {
					$.each(data, function(n, value) {
						$("#buildingId").append(
								"<option value='"+value.id+"'>" + value.name
										+ "</option>");
						$("#updateResourceBuilding").append(
								"<option value='"+value.id+"'>" + value.name
										+ "</option>");
						buildings = data;
					});
				}
			});
	function findBuildName(id) {
		var tempvalue;
		$.each(buildings, function(n, value) {
			if (id == value.id) {
				tempvalue = value.name;
				return false;
			}
		});
		return tempvalue;
	}
	$('#jstree_resource').jstree({
		"core" : {
			"multiple" : false,
			"animation" : 0,
			"check_callback" : true,
			"themes" : {
				"stripes" : true
			},
			'data' : {
				'url' : "<c:url value="/manage/showNode.json" />",
				'data' : function(node) {
				}
			}
		},
		"types" : {
			"#" : {
				"max_children" : 1,
				"max_depth" : 6,
				"valid_children" : [ "root" ]
			},
			"root" : {
				"icon" : "<c:url value="/js/themes/default/tree_icon.png" />",
				"valid_children" : [ "default" ]
			},
			"default" : {
				"valid_children" : [ "default", "file" ]
			},
			"file" : {
				"icon" : "glyphicon glyphicon-file",
				"valid_children" : []
			}
		},
		"plugins" : [ "contextmenu", "search", "types", "wholerow" ],
		"contextmenu" : {
			"items" : {
				"create" : null,
				"rename" : null,
				"remove" : null,
				"ccp" : null,
				"add" : {
					"label" : "add",
					"action" : function(obj) {
						var inst = jQuery.jstree.reference(obj.reference);
						var clickedNode = inst.get_node(obj.reference);
						nodeCreate();
					}
				},
				"delete" : {
					"label" : "delete",
					"action" : function(obj) {
						var inst = jQuery.jstree.reference(obj.reference);
						var clickedNode = inst.get_node(obj.reference);
						nodeDelete();
					}
				},
				"update" : {
					"label" : "update",
					"action" : function(obj) {
						nodeRename();
					}
				}
			}
		}
	});
	//JSTree 事件
	$('#jstree_resource').on("changed.jstree", function(e, data) {
		
	});
	//分页
	var pageNumber = 1;
	$('#resourceTableId')
			.bootstrapTable(
					{
						method : 'get',
						url : "<c:url value='/manage/resourceSearch.json' />",
						dataType : "json",
						pageSize : 10,
						pageList : [ 10, 25, 50 ], //可供选择的每页的行数（*）
						pageNumber : pageNumber,
						pagination : true, //分页
						singleSelect : false,
						striped : true,
						idField : "id", //标识哪个字段为id主键
						sidePagination : "server", //服务端处理分页
						columns : [
								{
									title : '名称',
									field : 'name',
									align : 'center',
									valign : 'middle'
								},
								{
									title : '类型',
									field : 'permissionAttrId',
									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {
										if (value == "1")
											return "公共资源";
										if (value == "2")
											return "基础资源";
										if (value == "3")
											return "私有资源";
									}
								},
								{
									title : '楼栋',
									field : 'buildingId',
									align : 'center',
									valign : 'middle',
									formatter : function(value, row, index) {
										return findBuildName(value);
									}
								},
								{
									title : '状态',
									field : 'status',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == "Y") {
											return '<span class="label label-success">可用</span>';
										} else {
											return '<span class="label label-danger">不可用</span>';
										}
									}
								},
								{
									title : '创建时间',
									field : 'createDate',
									align : 'center',
									formatter : function(value, row, index) {
										return new Date(value)
												.format("yyyy-MM-dd");
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<button class="btn btn-xs btn-warning" onclick="showResourceGroup('
												+ value
												+ ')"><i class="icon-pencil"></i> </button>  ';
										var d = '<button class="btn btn-xs btn-danger" onclick="resourceDel(\''
												+ row.id
												+ '\')"><i class="icon-remove"></i> </button>';
										return e + d;
									}
								} ],
						formatLoadingMessage : function() {
							return "请稍等，正在加载中...";
						}
					});
	//自定义查询
	$('#dosearch').click(function() {
		refreshSearch();
	});

	//FORM输入验证
	$('#resourceFormId').bootstrapValidator({
		message : '输入值错误',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			name : {
				validators : {
					notEmpty : {
						message : '资源名称不能为空'
					}
				}
			},
			floor : {
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '不能超过10位'
					},
					integer : {
						message : '楼层只能输入数字'
					}
				}
			},
			sequence : {
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '不能超过10位'
					},
					integer : {
						message : '排序只能使用数字'
					}
				}
			}
		}
	});
</script>

<script type="text/javascript">
	//文件选择器
	$(document)
			.on(
					'ready',
					function() {

						$("#input-folder-1")
								.fileinput(
										{
											language : 'zh', //设置语言,
											uploadUrl : "uploadFile",
											uploadAsync : true,
											//browseLabel: 'Select Folder...',
											allowedFileExtensions : [ 'xls',
													'xlsx' ],//接收的文件后缀
											showUpload : true, //是否显示上传按钮
											showCaption : false,//是否显示标题
											showPreview : true,
											browseClass : "btn btn-primary", //按钮样式             
											previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
											//elErrorContainer: "#errorBlock",
											maxFilePreviewSize : 10240,
											uploadExtraData : function() {
												//return "";
												/* var extraValue = null;
												 var radios = document.getElementsByName('excelType');
												 for(var i=0;i<radios.length;i++){
												     if(radios[i].checked){
												         extraValue = radios[i].value;
												     }
												 }
												 return {"excelType": extraValue};
												 */

												return {
													"nodeId" : "123123"
												};
											}
										});
						//上传失败
						$('#input-folder-1').on('fileuploaderror',
								function(event, data, previewId, index) {
									alert("文件上传失败！");
									//var form = data.form, files = data.files, extra = data.extra,
									//        response = data.response, reader = data.reader;
									//console.log(data);
									//console.log('File upload error');
								});
						//上传失败
						$('#input-folder-1').on('fileerror',
								function(event, data) {
									alert("文件解析失败！");
									//console.log(data.id);
									//console.log(data.index);
									//console.log(data.file);
									//console.log(data.reader);
									//console.log(data.files);
								});
						//上传成功返回监听
						$('#input-folder-1')
								.on(
										'fileuploaded',
										function(event, data, previewId, index) {
											var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
											if (response.status != 0) {
												alert(response.message);
											} else {
												alert("资源导入成功！");
												$("#uploadModalClose").click();
											}
											//console.log('File uploaded triggered');
										});
					});
</script>
<script type="text/javascript">
	$(function() {
		$('#myTab a:first').tab('show');//初始化显示哪个tab
		$('#myTab a').click(function(e) {
			e.preventDefault();//阻止a链接的跳转行为
			$(this).tab('show');//显示当前选中的链接及关联的content
			//点击tab调用对应function
			if ($(this).attr("href") == "#resourceGroupResource") {
				resourceTableInit();
				//$("#groupListTableId").bootstrapTable('refresh');
			}
		})
	})
	
	//显示用户详情内容
	var showResourceGroup = function(resourceId) {
		$("#icon_group_list1").click();
		if ($("#icon_group_list2 i.icon-chevron-down").length > 0) {
			//console.log("1");
			$("#icon_group_list2").click();
		}
		if(null!=resourceId&&""!=resourceId){
			resourceEditPre(resourceId);
		}else{
			initResourceForm();
		}
		//initgroupGroupDetailForm(resourceGroupId);
	};

	$("#addResourceButton").click(function() {
		showResourceGroup();
	});
	$('#jstree_updateResource').jstree({
		"core" : {
			"multiple" : false,
			"animation" : 0,
			"check_callback" : true,
			"themes" : {
				"stripes" : true
			},
			'data' : {
				'url' : "<c:url value="/manage/showNode.json" />",
				'data' : function(node) {
				}
			}
		},
		"types" : {
			"#" : {
				"max_children" : 1,
				"max_depth" : 6,
				"valid_children" : [ "root" ]
			},
			"root" : {
				"icon" : "<c:url value="/js/themes/default/tree_icon.png" />",
				"valid_children" : [ "default" ]
			},
			"default" : {
				"valid_children" : [ "default", "file" ]
			},
			"file" : {
				"icon" : "glyphicon glyphicon-file",
				"valid_children" : []
			}
		},
		"plugins" : [ "search", "types", "wholerow" ]
	});

	$('#jstree_updateResource').on("changed.jstree", function(e, data) {
		//console.log(data.node.id);
		if (data.node != null) {
			$("#updateResourceNodeId").val(data.node.id);
			$("#updateResourceNodePath").val(data.instance.get_path(data.node, '/'));
			//console.log($("#newnodeId").val());
			//console.log($("#newnodePath").val());
		}
	});
</script>
<%@ include file="/common/footer.html"%>
