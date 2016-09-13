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
			<span class="divider">/</span> <a href="#" class="bread-current">资源组列表</a>
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
								<a href="#" class="wminimize" id="icon_group_list1"><i class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="groupGroupListTable">
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form" id="groupSearchForm">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
										<div class="col-lg-3">
											<input type="text" name="name" class="form-control" placeholder="资源组名称">
										</div>
										<div class="col-lg-3">
											<button type="button"  class="btn btn-primary" id="doGroupsearch">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#createNewGroupModal">
												<i class="icon-plus"></i> 新增资源组
											</button>
										</div>
									</div>
								</form>
							</div>
							
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover"
									id="resourceGroupTableId">
									
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Matter ends -->
	
	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">资源组详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="groupGroupDetailsTable">
						 
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">资源组详情</a></li>
							      <li><a href="#resourceGroupResource" >资源组与资源</a></li>
							    </ul>
							    <div class="tab-content">
							      <div class="tab-pane active" id="home">
							      		<!-- Bootstrap 表单 -->
							      		<form class="form-horizontal" role="form">
							      			<div class="form-group">
				                                  <label class="col-lg-2 control-label">ID</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="resourceGroupId_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label"  style="width: 120px">资源组名称</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" id="resourceGroupName_InForm" placeholder="用户组名称" name="name">
				                                  </div>
			                                </div>
			                                <div class="form-group">
				                                   <label class="col-lg-2 control-label">创建时间</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  id="resourceGroupCreateTime_InForm" placeholder="创建时间">
				                                  </div>
				                                    <label class="col-lg-2 control-label"  style="width: 120px">创建人</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  placeholder="创建人" id="resourceGroupCreategroup_InForm">
				                                  </div>
			                                </div>
										</form>
										<div class="control-group">
									          <label class="col-lg-2 control-label"></label>
									          <!-- Button -->
									          <div class="controls">
									            <button class="btn btn-success" id="updateResourceGroup_Button">保存</button>
									          </div>
									     	</div>
					      		  </div>
							      <div class="tab-pane" id="resourceGroupResource">
								  	    <!-- 资源树节点 -->
							      		<div class="col-lg-3">
												<div class="widget treeMinHeight" id="jstree_resource"></div>
										</div>
										<!-- 资源table -->
									  	<div class="col-lg-9">
										<hr>
										<form class="form-horizontal" role="form" id="resourceSearchform">
											<label class="col-lg-2 control-label">已绑定</label>
			                                  <div class="col-lg-2">
			                                  <div id="selectBindUserFlag" class="make-switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
												 <input type="checkbox" checked id="ifBindGroupId" name="ifBindGroup"/>
											  </div>
			                                  </div>
											<div class="form-group">
												<div class="col-lg-2">
													<input type="text" class="form-control" placeholder="资源名称" name="name">
												</div>
												<div class="col-lg-2">
													<select class="form-control" name="permissionAttrId">
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
												
												<div class="col-lg-2">
													<button type="button" class="btn btn-primary" id="doSearchResource">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
											<input type="hidden" id="resourceNodeId_hidden" name="nodeId">
											<input type="hidden" id="resourceGroupId_hidden" name=resourceGroupId>
										</form>
										<table class="table table-striped table-bordered table-hover"
											id="resourceTableId">
		
										</table>
   									</div>
								  </div>
							</div>
						</div>
					      	<!-- Bootstrap 表单 -->
							  
					</div>
				</div>
			</div>
		</div>
	</div>
						
</div>
<!-- Mainbar ends -->
<div class="clearfix"></div>
</div>
<!-- Content ends -->
<!--创建新用户组 弹窗 -->
<!-- Modal -->
<div class="modal fade" id="createNewGroupModal" tabindex="-1" role="dialog"
	aria-labelledby="createNewGroupModalModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="createNewGroupModalLabel">添加资源组</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
                       <label class="col-lg-3 control-label" style="width:120px">资源组名称</label>
                       <div class="col-lg-4">
                         <input type="text" class="form-control" placeholder="资源组名称" name="name" id="groupGroupNameId">
                       </div>
                    </div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="closeCreateGroupWindow">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createResourceGroup();" >保存</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	
	var getgroupURL = "<c:url value='/manage/resourceGroupSearch.json' />";
	var pageNumber = 1;
	$('#resourceGroupTableId').bootstrapTable({
		method: 'get',
	    url: getgroupURL, 
	    dataType: "json",
	    queryParams: resrouceGroupQueryParams,
	    pageSize: 10,
	    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
	    pageNumber: pageNumber,
	    pagination: true, //分页
	    singleSelect: false,
	    idField: "id",  //标识哪个字段为id主键
	    locale: "zh-CN", //表格汉化
	    sidePagination: "server", //服务端处理分页
       	columns: [
			{
			    title: '用户组ID',
			      field: 'id',
			      align: 'center',
			      valign: 'middle'
			  }, 
               {
                 title: '用户组名称',
                   field: 'name',
                   align: 'center',
                   valign: 'middle'
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
                   title: '创建人',
                   field: 'createUser',
                   align: 'center'
               },
               {
                   title: '操作',
                   field: 'id',
                   align: 'center',
                   formatter:function(value,row,index){
	                 var e = '<a href="javascript:void(0);" mce_href="#" onclick="showResourceGroup(\''+ row.id + '\')">详情</a> ';  
	                 var d = '<a href="javascript:void(0);" mce_href="#" onclick="deletegroupGroupById(\''+ row.id +'\')">删除</a> ';  
                    return e+d;  
                 } 
               }
           ],
		formatLoadingMessage: function () {
	    	return "请稍等，正在加载中...";
	  	}
      });
	  
	function resrouceGroupQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search:function(){
	    	 	var search = {};  
			    $.each($("#groupSearchForm").serializeArray(), function(i, field) {  
			        search[field.name] = field.value;  
			    });
			    return JSON.stringify(search);
	      }
	    };
	    return temp;
	  }
	
	$('#doGroupsearch').click(function() {  
		$('#resourceGroupTableId').bootstrapTable('refresh');
    }); 
	  //创建新的resourceGroup
	 function createResourceGroup(){
		var resourceGroupName =   $("#groupGroupNameId").val();
		if(null == resourceGroupName || resourceGroupName == ""){
			alert("资源组名称不能为空");
		}
		$.ajax( {  
		    url:"<c:url value='/manage/addResourceGroup.json' />",
		    data:{"resourceGroupName" : resourceGroupName },  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');
		    		$("#closeCreateGroupWindow").click();
		    		$("#groupGroupNameId").val("");
		    	}else{
		    		alert("已存在同名用户组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名用户组！");
		     }
		});
	  };
	  
	  //删除resGroup 
		 function deletegroupGroupById(resGroupId){
			if(null == resGroupId || resGroupId == ""){
				alert("用户组ID为空");
				return;
			}
			if (!confirm('您确定要删除选中的资源组吗？')) {
				return;
			}
			$.ajax( {  
			    url:"<c:url value='/manage/deleteResGroupById.json' />",
			    data:{   resGroupId : resGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
			    		$('#resourceGroupTableId').bootstrapTable('refresh');
			    	}else{
			    		alert("操作失败！");
			    	}
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
		  };
		  
		  
      //显示用户详情内容
	  var showResourceGroup = function (resourceGroupId) {
		  $("#icon_group_list1").click();
		  if($("#icon_group_list2 i.icon-chevron-down").length>0){
			  console.log("1");
			  $("#icon_group_list2").click();
			}
		  initgroupGroupDetailForm(resourceGroupId);
	  };

	  
	  //初始化 groupGroupDetailForm 
	  var initgroupGroupDetailForm = function (resourceGroupId) {
		  $.ajax( {  
			    url:"<c:url value='/manage/showResourceGroup.json' />",
			    data:{resourceGroupId : resourceGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$("#resourceGroupId_hidden").val(data.id);
			    	$("#resourceGroupId_InForm").val(data.id);
			    	$("#resourceGroupName_InForm").val(data.name);
			    	$("#resourceGroupCreateTime_InForm").val(data.createDate);
			    	$("#resourceGroupCreategroup_InForm").val(data.createUser);
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
	  };
	  
	  
	//更新 资源组
	 $('#updateResourceGroup_Button').click(function() {
		 var resourceGroupId = $("#resourceGroupId_InForm").val();
		 var resourceGroupName = $("#resourceGroupName_InForm").val();
		 if(null ==  resourceGroupName || '' == resourceGroupName){
			 alert("资源组名称不能为空");
			 return;
		 }
		 $.ajax({
		    url:"<c:url value='/manage/updateResourceGroup.json' />",
		    data:{resourceGroupId : resourceGroupId, resourceGroupName : resourceGroupName},  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');
		    		alert("保存成功");
		    	}else{
		    		alert("已存在同名资源组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名资源组！");
		     }
		});
	 });
	
	 //tab 切换
    $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
          //点击tab调用对应function
          if($(this).attr("href") == "#resourceGroupResource"){
        	  resourceTableInit();
        	  //$("#groupListTableId").bootstrapTable('refresh');
          } 
        })
      })
</script>

<!-- resource tab js -->
<script type="text/javascript">
	function resourceTableInit() {
		$('#resourceTableId').bootstrapTable({
			method: 'get',
		    url: "<c:url value='/manage/resourceSearchWithGroup.json' />", 
		    dataType: "json",
		    queryParams: resourceQueryParams,
		    pageSize: 10,
		    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
		    pageNumber: 1,
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
	                   field: 'permissionAttrId',
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
	                   valign: 'middle'
	               },
	               {
	                   title: '状态',
	                   field: 'resourceGroupId',
	                   align: 'center',
	                   valign: 'middle',
	                   formatter:function(value,row,index){
	                	   if(typeof(value) != "undefined"){
	                		   return '<span class="label label-success">已加入</span>';
	                	 }
		               } 
	               }, 
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 90,
	                   formatter:function(value,row,index){
	                	   if(typeof(row.resourceGroupId) != "undefined"){
	                		   return '<button type="button" class="btn btn-xs btn-warning"  onclick="deleteResourceGroupRel(this, \''+ row.id +'\')" data-toggle="modal" data-loading-text="Loading...">移除</button>';
	                		   //return '<button type="button" class="btn btn-warning" onclick="deleteResourceGroupRel('+value+')"><i class="icon-remove"></i> 移除</button>';
	                	 }else{
	                		 return '<button type="button" class="btn btn-xs btn-success"  onclick="addResourceGroupRel(this, \''+ row.id + '\')" data-toggle="modal" data-loading-text="Loading...">加入</button>';
	                		 //return '<button type="button" class="btn btn-primary" onclick="addResourceGroupRel('+value+')"><i class="icon-plus"></i> 加入</button>';;
	                	 }
	                 } 
	               }
	           ],
	           formatLoadingMessage: function () {
			    	return "请稍等，正在加载中...";
			  	},
               formatNoMatches: function () {  //没有匹配的结果
                   return '无符合条件的记录';
               }
	      });
	};
	
	function resourceQueryParams(params) {
        var search = {};  
        $.each($("#resourceSearchform").serializeArray(), function(i, field) {  
        	if(field.name=="ifBindGroup"&&field.value=="on"){
        		search["ifBindGroup"] = "Y";
        	}else{
        		search[field.name] = field.value;
        	}
        });  
        params.search = JSON.stringify(search);
        return params;  
    }  
	
	//自定义resource查询
	 $('#doSearchResource').click(function() {
	        //var params = $('#resourceTableId').bootstrapTable('getOptions');  
	        $("#resourceNodeId_hidden").val(null);
	        $("#resourceTableId").bootstrapTable('destroy');
	        resourceTableInit();
	        //$('#resourceTableId').bootstrapTable('refresh');   //直接用 refresh会有页码数缓存问题
	        //console.info(params);  
    });
	
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
	
   function addResourceGroupRel(buttonObj, resourceId){
	   $(buttonObj).button('loading');
	  // $("#resourceGroupId_hidden").val();
	   $.ajax({
		    url:"<c:url value='/manage/addResourceGroupRel.json' />",
		    data:{resourceGroupId : $("#resourceGroupId_hidden").val(), resourceId : resourceId},  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	$(buttonObj).button('reset');
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');
		    		//alert("保存成功");
		    	}else{
		    		alert("系统异常！");
		    	}
		     },  
		     error : function() {  
		    	 alert("系统异常！");
		    	 $(buttonObj).button('reset');
		     }
		});
	   
   }
   function deleteResourceGroupRel(buttonObj, resourceId){
	   $(buttonObj).button('loading');
	  // $("#resourceGroupId_hidden").val();
	   $.ajax({
		    url:"<c:url value='/manage/deleteResourceGroupRel.json' />",
		    data:{resourceGroupId : $("#resourceGroupId_hidden").val(), resourceId : resourceId},  
		    type:'post',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	$(buttonObj).button('reset');
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');
		    		//alert("保存成功");
		    	}else{
		    		alert("系统异常！");
		    	}
		     },  
		     error : function() {  
		    	 alert("系统异常！");
		    	 $(buttonObj).button('reset');
		     }
		});
   }
   
   //资源树
   $('#jstree_resource').jstree({
		"core": {
			"multiple" : false,
			"animation": 0,
			"check_callback": true,
			"themes": {
				"stripes": true
			},
			'data': {
				'url': "<c:url value="/manage/showNode.json" />",
				'data': function(node) {
				}
			}
       },
		"types": {
			"#": {
				"max_children": 1,
				"max_depth": 6,
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
			"search","types", "wholerow"
		]
	});
	//JSTree 点击事件
	$('#jstree_resource').on("changed.jstree", function (e, data) {
		//console.log(data.node.id);
		if(data.node!=null){
			$("#resourceNodeId_hidden").val(data.node.id);
			$('#resourceTableId').bootstrapTable('refresh');  
		}
	 });
</script>


<%@ include file="/common/footer.html"%>
