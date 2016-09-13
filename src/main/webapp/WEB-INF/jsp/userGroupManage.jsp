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
			<i class="icon-home"></i> 用户组管理页面
		</h2>
		<!-- Breadcrumb -->
		<div class="bread-crumb pull-right">
			<a href="index.html"><i class="icon-home"></i> 用户组管理页面</a>
			<!-- Divider -->
			<span class="divider">/</span> <a href="#" class="bread-current">用户组列表</a>
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
							<div class="pull-left">用户组列表</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list1"><i class="icon-chevron-up"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="userGroupListTable">
							<div class="col-lg-9">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">用户组名称</label>
										<div class="col-lg-3">
											<input type="text" id="userGroupNameSearch" class="form-control" placeholder="用户组名称">
										</div>
										<div class="col-lg-3">
											<button type="button" onclick = "$('#userGroupTableId').bootstrapTable('refresh');" class="btn btn-primary">
												<i class="icon-search"></i> 查询
											</button>
										</div>
										<div class="col-lg-2">
											<button type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#createNewGroupModal">
												<i class="icon-plus"></i> 新增用户组
											</button>
										</div>
									</div>
								</form>
							</div>
							
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover"
									id="userGroupTableId">
									
								</table>
							</div>
						</div>
						<div class="clearfix"></div>
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
							<div class="pull-left">用户组详情</div>
							<div class="widget-icons pull-right">
								<a href="#" class="wminimize" id="icon_group_list2"><i class="icon-chevron-down"></i></a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="userGroupDetailsTable">
						 
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">用户组详情</a></li>
							      <li><a href="#userAndUserGroup" >用户组与用户</a></li>
							      <li><a href="#userGroupResource"  >用户组与资源</a></li>
							      <li><a href="#userGroupResourceGroup"  >用户组与资源组</a></li>
							    </ul>
							    <div class="tab-content">
							      <div class="tab-pane active" id="home">
							      		<!-- Bootstrap 表单 -->
							      		<form class="form-horizontal" role="form">
							      			<div class="form-group">
				                                  <label class="col-lg-2 control-label">ID</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="userGroupId_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label">用户组名称</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" id="userGroupName_InForm" placeholder="用户组名称">
				                                  </div>
			                                </div>
			                                <div class="form-group">
				                                   <label class="col-lg-2 control-label">创建时间</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  id="userGroupCreateTime_InForm" placeholder="创建时间">
				                                  </div>
				                                  <!-- 
				                                  <label class="col-lg-2 control-label">状态</label>
				                                  <div class="col-lg-3">
					                                  <div class="make-switch" data-on="primary" data-off="info">
				                         					<input type="checkbox" checked id = "userGroupStatus_InForm">
				                     				  </div>
				                                  </div>
				                                   -->
				                                    <label class="col-lg-2 control-label">创建人</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  placeholder="创建人" id="userGroupCreateUser_InForm">
				                                  </div>
			                                </div>
							      		
									    	<div class="control-group">
									          <label class="control-label"></label>
									          <!-- Button -->
									          <div class="controls">
									            <button class="btn btn-success" id="updateUserGroup_Button">保存</button>
									          </div>
									     	</div>
										</form>
					      		  </div>
					      		  <!-- usergroup table -->
							      <div class="tab-pane" id="userAndUserGroup">
								      <div class="col-lg-12">
									  <hr>
										<form class="form-horizontal" role="form">
											<div class="form-group">
												<label class="col-lg-2 control-label">只看绑定</label>
				                                  <div class="col-lg-1">
				                                  	<div id="selectBindUserFlag" class="make-switch" data-on-label="<i class='icon-ok icon-white'></i>" data-off-label="<i class='icon-remove'></i>">
													    <input type="checkbox" checked id="selectBindUserCheckBox"/>
													</div>
				                                  </div>
												<label class="col-lg-2 control-label" style="width: 120px">用户名称</label>
												<div class="col-lg-2">
													<input type="text" id="userNameSearch" class="form-control" placeholder="用户名称">
												</div>
												
												<label class="col-lg-2 control-label" style="width: 120px">手机号</label>
												<div class="col-lg-3">
													<input type="text" id="userMobileSearch" class="form-control" placeholder="手机号">
												</div>
												
												<div class="col-lg-1">
													<button type="button" onclick = "$('#userListTableId').bootstrapTable('refresh');" class="btn btn-primary">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
										</form>
										</div>
								      	<div class="col-lg-12">
											<table class="table table-striped table-bordered table-hover"
												id="userListTableId">
												
											</table>
										</div>
								  </div>
							      <div class="tab-pane" id="userGroupResource">
								  	    <!-- 资源树节点 -->
							      		<div class="col-lg-3">
												<div class="widget treeMinHeight" id="jstree_resource"></div>
										</div>
										<!-- 资源table -->
									  	<div class="col-lg-9">
										<hr>
										<form class="form-horizontal" role="form" id="resourceSearchform">
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
												
												<div class="col-lg-3">
													<button type="button" class="btn btn-primary" id="doSearchResource">
														<i class="icon-search"></i> 查询
													</button>
												</div>
											</div>
											<input type="hidden" id="resourceNodeId_hidden" name="nodeId">
											<input type="hidden" id="userGroupId_hidden" name="specificCusGroupId">
										</form>
										<table class="table table-striped table-bordered table-hover"
											id="resourceTableId">
		
										</table>
   									</div>
								  </div>
							      <div class="tab-pane" id="userGroupResourceGroup">
										<div class="col-lg-9">
												<hr>
												<form class="form-horizontal" role="form" id="groupSearchForm">
													<div class="form-group">
														<label class="col-lg-2 control-label" style="width: 120px">资源组名称</label>
														<div class="col-lg-3">
															<input type="text" name="name" class="form-control" placeholder="资源组名称">
														</div>
														<div class="col-lg-3">
															<button type="button"  class="btn btn-primary" id="doResGroupsearch">
																<i class="icon-search"></i> 查询
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
					<div class="clearfix"></div>
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
				<h4 class="modal-title" id="createNewGroupModalLabel">添加用户组</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form">
					<div class="form-group">
                       <label class="col-lg-2 control-label">用户组名称</label>
                       <div class="col-lg-4">
                         <input type="text" class="form-control" placeholder="用户组名称" id="userGroupName_CreateForm">
                       </div>
                    </div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="closeCreateGroupWindow">关闭</button>
				<button type="button" class="btn btn-primary" onclick="createUserGroup();" >保存</button>
			</div>
		</div>
	</div>
</div>

<!--用户组授权 弹窗 -->
<!-- Modal -->
<div class="modal fade" id="addPermissionModal" tabindex="-1" role="dialog"
	aria-labelledby="addPermissionModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="addPermissionModalLabel">权限更新</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addResPermissionForm">
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">资源ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="resourceId_addPer" name="resourceId"  readonly="true" placeholder="资源ID">
	                     </div>
	                      <label class="col-lg-2 control-label">资源名称</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="resourceName_addPer" disabled="true" placeholder="资源名称">
	                     </div>
	                   </div>
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">用户组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="cusGrpId_addPer" name="cgroupId"  readonly="true" placeholder="资源ID">
	                     </div>
	                      <label class="col-lg-2 control-label">用户组名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="cusGrpName_addPer" disabled="true" placeholder="用户名">
	                     </div>
	                   </div>
	                   
	                   <div class="form-group">
	                     <label class="col-lg-2 control-label">授权状态</label>
	                     <div class="col-lg-4">
	                      <div class="make-switch" id="permissionStatus_addPer" data-on="success" data-off="warning" data-off="info" data-on-label="启用" data-off-label="禁用">
	            					<input type="checkbox" disabled checked name="enable">
	        				  </div>
	                     </div>
	                     <label class="col-lg-2 control-label">连带授权</label>
	                     <div class="col-lg-4">
	                      	  <div class="make-switch" id="jointAuthFlagSwitch_addPer" data-on="success" data-off="warning" data-off="info" data-on-label="是" data-off-label="否">
	            					<input type="checkbox" checked name="jointAuthFlag_addPer">
	        				  </div>
	                     </div>
	                  </div>
	                  
	                    <div class="form-group">  
	                    	<label class="col-lg-2 control-label">起始时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;'>
			                    <input type='text' class="form-control"  id="startDate_addPer" name="startDateStr"  placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	   </div>  
                	   <div class="form-group">  
	                    	<label class="col-lg-2 control-label">过期时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;' >
			                    <input type='text' class="form-control" id="endDate_addPer" name="endDateStr" placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	  </div>
	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeButton_addPer" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveButton_addPer" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 用户组与资源组关系 -->
<div class="modal fade" id="addResGroupPermissionLayer" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">权限更新</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="addResGrpPermissionForm">
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">资源组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="resGrpId_addGrpPer" name="rgroupId"  readonly="true" placeholder="资源组ID">
	                     </div>
	                      <label class="col-lg-2 control-label">资源组名称</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="reGrpName_addGrpPer" disabled="true" placeholder="资源组名称">
	                     </div>
	                   </div>
	                   <div class="form-group">
	                      <label class="col-lg-2 control-label">用户组ID</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control" id="userGrpId_addGrpPer" name="cgroupId"  readonly="true" placeholder="用户组ID">
	                     </div>
	                      <label class="col-lg-2 control-label">用户组名</label>
	                     <div class="col-lg-4">
	                       <input type="text" class="form-control"  id="userGrpName_addGrpPer" disabled="true" placeholder="用户组名">
	                     </div>
	                   </div>
	                   
	                    <div class="form-group">  
	                    	<label class="col-lg-2 control-label">起始时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;'>
			                    <input type='text' class="form-control"  id="startDate_addGrpPer" name="startDateStr"  placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	   </div>  
                	   <div class="form-group">  
	                    	<label class="col-lg-2 control-label">过期时间</label>
			                <div class='input-group date col-lg-4 datetimepicker'  style='padding-left:15px;' >
			                    <input type='text' class="form-control" id="endDate_addGrpPer" name="endDateStr" placeholder="无限制"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
                	  </div>
	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeButton_addGrpPer" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="saveButton_addGrpPer" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>


<input type="hidden" id="selectBindUserFlag_hidden" value="1">
<input type="hidden" id="cusGrpName_hidden" value="1">

<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	
	//显示详情内容
	var showResourceGroup = function (resourceGroupId) {
		  $("#icon_group_list1").click();
		  if($("#icon_group_list2 i.icon-chevron-down").length>0){
			  console.log("1");
			  $("#icon_group_list2").click();
			}
	};
	
	var getUserURL = "<c:url value='/userGroup/showUserGroupList.json' />";
	var pageNumber = 1;
	$('#userGroupTableId').bootstrapTable({
		method: 'get',
	    url: getUserURL, 
	    dataType: "json",
	    queryParams: userGroupQueryParams,
	    pageSize: 10,
	    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
	    pageNumber: pageNumber,
	    pagination: true, //分页
	    singleSelect: false,
	    idField: "id",  //标识哪个字段为id主键
	    //showColumns: true, //显示隐藏列  
	    //showRefresh: true,  //显示刷新按钮
	    locale: "zh-CN", //表格汉化
	    //search: true, //显示搜索框
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
                   field: 'createDateStr',
                   align: 'center'
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
                 var e = '<a href="javascript:void(0);" mce_href="#" onclick="showUserGroup(\''+ row.id + '\',\''+ row.name + '\')">详情</a> ';  
                 var d = '<a href="javascript:void(0);" mce_href="#" onclick="deleteUserGroupById(\''+ row.id +'\')">删除</a> ';  
                    return e+d;  
                 } 
               }
           ],
		formatLoadingMessage: function () {
	    	return "请稍等，正在加载中...";
	  	}
      });
	
	  //userGroup table 入参
	 function userGroupQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search: $("#userGroupNameSearch").val()
	    };
	    return temp;
	  }
	  
	  //创建新的userGroup
	 function createUserGroup(){
		var userGroupName =   $("#userGroupName_CreateForm").val();
		if(null == userGroupName || userGroupName == ""){
			alert("用户组名称不能为空");
		}
		$.ajax( {  
		    url:"<c:url value='/userGroup/createNewGroup.json' />",
		    data:{   userGroupName : userGroupName },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#userGroupTableId').bootstrapTable('refresh');
		    		$("#closeCreateGroupWindow").click();
		    		$("#userGroupName_CreateForm").val("");
		    	}else{
		    		alert("已存在同名用户组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名用户组！");
		     }
		});
	  };
	  
	  //删除userGroup
		 function deleteUserGroupById(customerGroupId){
			if(null == customerGroupId || customerGroupId == ""){
				alert("用户组ID为空");
				return;
			}
			if (!confirm('您确定要删除选中的用户组吗？')) {
				return;
			}
			$.ajax( {  
			    url:"<c:url value='/userGroup/deleteUserGroupById.json' />",
			    data:{   customerGroupId : customerGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
			    		$('#userGroupTableId').bootstrapTable('refresh');
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
	  var showUserGroup = function (userGroupId, userGroupName) {
		  $("#userGroupId_hidden").val(userGroupId);
		  $("#cusGrpName_hidden").val(userGroupName);
		  $("#userGroupListTable").hide();
		  $("#userGroupDetailsTable").show();
		  initUserGroupDetailForm(userGroupId);
		  $('#resourceGroupTableId').bootstrapTable('refresh');
		  $('#resourceTableId').bootstrapTable('refresh');  
		  $('#userListTableId').bootstrapTable('refresh');
	  };

	  
	  //初始化 UserGroupDetailForm 
	  var initUserGroupDetailForm = function (userGroupId) {
		  $.ajax( {  
			    url:"<c:url value='/userGroup/showUserGroupDetail.json' />",
			    data:{   userGroupId : userGroupId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$("#userGroupId_InForm").val(data.id);
			    	$("#userGroupName_InForm").val(data.name);
			    	$("#userGroupStatus_InForm").val(data.status);
			    	$("#userGroupCreateTime_InForm").val(data.createDateStr);
			    	$("#userGroupCreateUser_InForm").val(data.createUser);
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
	  };
	  
	  
	//更新 userGroup
	 $('#updateUserGroup_Button').click(function() {
		 var userGroupId = $("#userGroupId_InForm").val();
		 var userGroupName = $("#userGroupName_InForm").val();
		 if(null ==  userGroupName || '' == userGroupName){
			 alert("用户组名称不能为空");
			 return;
		 }
		 $.ajax({
		    url:"<c:url value='/userGroup/updateUserGroup.json' />",
		    data:{userGroupId : userGroupId, userGroupName : userGroupName},  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#userGroupTableId').bootstrapTable('refresh');
		    		alsert("保存成功");
		    	}else{
		    		alert("已存在同名用户组！");
		    	}
		     },  
		     error : function() {  
		    	 alert("已存在同名用户组！");
		     }
		});
	 });
	
	//关联用户组查询用户标识
	 $('#selectBindUserFlag').on('switch-change', function (e, data) {
		    var value = data.value;
		    //启用
		    if(value == true){
		    	$("#selectBindUserFlag_hidden").val("1");
		    }else{
		    	$("#selectBindUserFlag_hidden").val("0");
		    }
		});
	 
	 var userListTableInit = function () {
		//用户列表table
		$('#userListTableId').bootstrapTable({
			method: 'get',
		    url: "<c:url value='/user/showUserListWithGroupId.json' />", 
		    dataType: "json",
		    queryParams: userQueryParams,
		    pageSize: 10,
		    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
		    pageNumber: 1, // 默认页面
		    pagination: true, //分页
		    singleSelect: false,
		    idField: "id",  //标识哪个字段为id主键
		    //showColumns: true, //显示隐藏列  
		    //showRefresh: true,  //显示刷新按钮
		    locale: "zh-CN", //表格汉化
		    //search: true, //显示搜索框
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
	                   width: 90,
	                   formatter:function(value,row,index){
	                	   if(row.extendSpecificFlag != null){
	                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="delUserGroupRelation(this, \''+ row.id +'\')" data-toggle="modal" data-loading-text="Loading...">移除</button>';
	                		   //var e = '<a href="javascript:void(0)" mce_href="#" onclick="delUserGroupRelation(this, \''+ row.id +'\')">移除</a> ';  
	                	   }else{
	                		   var e ='<button type="button" class="btn btn-xs btn-success"  onclick="addUserGroupRelation(this, \''+ row.id + '\')" data-toggle="modal" data-loading-text="Loading...">加入</button>';
	                		   //var e = '<a href="javascript:void(0)" mce_href="#" onclick="addUserGroupRelation(this, \''+ row.id + '\')">加入</a> ';  
	                	   }
	                	   return e;
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
		  //user table 入参
		 function userQueryParams(params) {  //配置参数
		    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		      pageNumber: params.pageNumber,  //页码
		      limit: params.limit,   //页面行数大小
		      offset: params.offset, //分页偏移量
		      sort: params.sort,  //排序列名
		      sortOrder: params.order ,//排位命令（desc，asc）
		      search: params.search,
		      userName: $("#userNameSearch").val(),
		      mobile: $("#userMobileSearch").val(),
		      bindUserFlag: $("#selectBindUserFlag_hidden").val(), //关联用户组查询用户标识
		      userGroupId: $("#userGroupId_hidden").val() //
		    };
		    return temp;
		  }
		  
		 //删除usergroup与user关系
		 function delUserGroupRelation(obj, userId) {
			 $(obj).button('loading');
			 var groupId = $("#userGroupId_hidden").val();
			 $.ajax( {  
				    url:"<c:url value='/user/delUserGroupRelation.json' />",
				    data:{   groupId : groupId, userId : userId },  
				    type:'get',  
				    cache:false,  
				    dataType:'json',  
				    success:function(data) {
				    	//$(obj).button('reset');//使用reset会恢复原始状态，导致后面的html文字修改失效
				    	if(data.status == 1){
					    	$(obj).attr("onclick", "addUserGroupRelation(this, "+userId+")");
					    	$(obj).html("加入");
					    	//更换属性
				    		$(obj).removeClass('btn-warning');
				    		$(obj).addClass('btn-success');
				    		$(obj).removeClass('disabled');
				    		$(obj).attr("disabled", false);
				    	}else{
				    		alert("操作失败！");
				    	}
				     },  
				     error : function() {  
				          alert("系统异常！");  
				     }  
				});
		  };
		  
			//添加usergroup与user关系
			 function addUserGroupRelation(obj, userId) {
				 $(obj).button('loading');
				 var  groupId = $("#userGroupId_hidden").val();
				 $.ajax( {  
					    url:"<c:url value='/user/addUserGroupRelation.json' />",
					    data:{   groupId : groupId, userId : userId },  
					    type:'get',  
					    cache:false,  
					    dataType:'json',  
					    success:function(data) {
					    	//$(obj).button('reset');//使用reset会恢复原始状态，导致后面的html文字修改失效
					    	if(data.status == 1){
						    	$(obj).attr("onclick", "delUserGroupRelation(this, "+userId+")");
						    	$(obj).html("移除");
						    	//更换属性
					    		$(obj).removeClass('btn-success');
					    		$(obj).addClass('btn-warning');
					    		$(obj).removeClass('disabled');
					    		$(obj).attr("disabled", false);
					    	}else{
					    		alert("操作失败！");
					    	}
					     },  
					     error : function() {  
					          alert("系统异常！");  
					     }  
					});
			  };
		  
	 //tab 切换
    $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
      
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
          
          //点击tab调用对应function
          if($(this).attr("href") == "#userAndUserGroup"){
        	  userListTableInit();
        	  //$("#userListTableId").bootstrapTable('refresh');
          } 
          //点击tab调用对应function
          if($(this).attr("href") == "#userGroupResource"){
        	  resourceTableInit();
        	  //$("#userListTableId").bootstrapTable('refresh');
          } 
        //点击tab调用对应function
          if($(this).attr("href") == "#userGroupResourceGroup"){
        	  resourceGroupTableInit();
        	  //$("#userListTableId").bootstrapTable('refresh');
          } 
        })
      })
</script>


<!-- resource tab js -->
<script type="text/javascript">
	function resourceTableInit() {
		$('#resourceTableId').bootstrapTable({
			method: 'get',
		    url: "<c:url value='/manage/resSearchWithCusGroupId.json' />", 
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
	                   title: '节点路径',
	                   field: 'nodePath',
	                   align: 'center',
	                   valign: 'middle',
	                   formatter:function (value, row, index) {
	                	   return findBuildName(value);
                        }
	               }, 
	               {
	                   title: '权限',
	                   field: 'cGrpResRelModel',
	                   align: 'center',
	                   formatter:function (value, row, index) {
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value 
		                			   && value.enable=="Y"){
		                		   if((null == row.cGrpResRelModel.startDate || new Date(row.cGrpResRelModel.startDate) < new Date() )
			                			   &&  (null == row.cGrpResRelModel.endDate || new Date(row.cGrpResRelModel.endDate) > new Date() )){
		                			   return '<span class="label label-success">有权限</span>';
		                		   }else{
		                			   return '<span class="label label-danger">已过期</span>';
		                		   }
		                	   }else{
		                		   return '<span class="label label-danger">无权限</span>';
		                	   }
	                	   }
	                	   
                        }
	               },
	               {
	                   title: '权限起始时间',
	                   field: 'cGrpResRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value){
		                		   if(null == value.startDate){
		                			   return "无限制";
		                		   }else{
		                			   return new Date(value.startDate).format("yyyy-MM-dd HH:mm");
		                		   }
		                	   }else{
		                		   return "-";
		                	   }
	                	   }
	                 } 
	               },
	               {
	                   title: '权限截至时间',
	                   field: 'cGrpResRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
	                		   if(null != value){
		                		   if(null == value.endDate){
		                			   return "无限制";
		                		   }else{
		                			   return new Date(value.endDate).format("yyyy-MM-dd HH:mm"); 
		                		   }
		                	   }else{
		                		   return "-";
		                	   }
	                	   }
	                 }
	               },
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 80,
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
		                	   if(null != row.cGrpResRelModel 
		                			   && row.cGrpResRelModel.enable == "Y"
		                			   && (null == row.cGrpResRelModel.startDate || new Date(row.cGrpResRelModel.startDate) < new Date() ) 
		                			   &&  (null == row.cGrpResRelModel.endDate || new Date(row.cGrpResRelModel.endDate) > new Date() ) ){
		                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="removePermission(\''+ row.id +'\',this)" data-toggle="modal"  data-loading-text="Loading...">禁用</button>';
		                	   }else{
		                		   var cGrpResRelModel = null;
		                		   var startDate = null;
		                		   var endDate = null;
		                		   if(null != row.cGrpResRelModel ){
		                			   if(null != row.cGrpResRelModel.startDate){
		                				   startDate = new Date(row.cGrpResRelModel.startDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                			   if(null != row.cGrpResRelModel.endDate){
		                				   endDate = new Date(row.cGrpResRelModel.endDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                		   }
		                		   var e ='<button type="button" class="btn btn-xs btn-success" data-toggle="modal" onclick="addPermmissionPreProcess(\''+ row.id +'\',\''+ row.name +'\',\''+ startDate +'\',\''+ endDate +'\')" data-target="#addPermissionModal" >授权</button>';
		                	   }
		                    	return e;  
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
        //定义参数  
        var search = {};  
        //遍历form 组装json  
        $.each($("#resourceSearchform").serializeArray(), function(i, field) {  
            //console.info(field.name + ":" + field.value + " ");  
            //可以添加提交验证  
            search[field.name] = field.value;  
        });  

        //参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
        params.search = JSON.stringify(search);
        console.info(params);  
        return params;  
    }  
	
	//自定义resource查询
	 $('#doSearchResource').click(function() {
	        //var params = $('#resourceTableId').bootstrapTable('getOptions');  
	        $("#resourceNodeId_hidden").val(null);
	        $('#resourceTableId').bootstrapTable('refresh');  
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
 	
	
	//更新用户组权限
	 $('#saveButton_addPer').click(function() {
		 //button失效，防止重复提交
		 //disabled="true"
		 $('#saveButton_addPer').attr("disabled", true);

		 //关联用户组查询用户标识
		 var jointAuthFlag = null;
        //定义参数  
        var array = {};  
        //遍历form 组装json  
        $.each($("#addResPermissionForm").serializeArray(), function(i, field) {  
            //可以添加提交验证  
            if('' == field.value){
            	array[field.name] = null;  
            }else{
            	array[field.name] = field.value;  
            }
            if(field.name == 'jointAuthFlag_addPer'){
            	jointAuthFlag = field.value;
            }
        });  

        //参数转为json字符串，并赋给变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
        var modelJsonStr = JSON.stringify(array);
        
        var startDateStr =$("#startDate_addPer").val();
        var endDateStr =$("#endDate_addPer").val();
 	    $.ajax({
 		    url:"<c:url value='/userGroup/authCusGrpResPermission.json' />",
 		    data:{   modelJsonStr : modelJsonStr, jointAuthFlag : jointAuthFlag, startDateStr : startDateStr, endDateStr : endDateStr },  
 		    type:'get',  
 		    cache:false,  
 		    dataType:'json',  
 		    success:function(data) {
 		    	if(data.status == 1){
 		    		$('#resourceTableId').bootstrapTable('refresh');  
 		    		$("#closeButton_addPer").click();
 		    	}else{
 		    		alert("操作失败！");
 		    	}
 		    	$('#saveButton_addPer').attr("disabled", false);
 		     },  
 		     error : function() {  
 		          alert("系统异常！");  
 		         $('#saveButton_addPer').attr("disabled", false);
 		     }  
 		});
        
	 });  
	
	
   function addPermmissionPreProcess(resourceId, resourceName, startDate, endDate){
	   $('#resourceId_addPer').val(resourceId);
	   $('#resourceName_addPer').val(resourceName);
	   $('#cusGrpId_addPer').val($("#userGroupId_hidden").val());
	   $('#cusGrpName_addPer').val($("#cusGrpName_hidden").val());
	   if(null != startDate && startDate != 'null'){
		   $('#startDate_addPer').val(startDate);
	   }
	   if(null != endDate && endDate != 'null'){
		   $('#endDate_addPer').val(endDate);
	   }
   }
   
   //禁用资源
   function removePermission(resourceId,buttonObj){
	   if (!confirm('您确定取消授权吗？')) {
			return;
		}
	   $(buttonObj).button('loading');
	   var cusGrpId = $("#userGroupId_hidden").val();
	   $.ajax({
		    url:"<c:url value='/userGroup/disableCusGrpResPermission.json' />",
		    data:{   resourceId : resourceId, cusGrpId : cusGrpId },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	$(buttonObj).button('reset');
		    	if(data.status == 1){
		    		$('#resourceTableId').bootstrapTable('refresh');  
		    	}else{
		    		alert("操作失败！");
		    	}
		     },  
		     error : function() {  
		          alert("系统异常！");  
		          $(this).button('reset');
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
			"contextmenu",  "search","types", "wholerow"
		],
		"contextmenu": {    
           "items": {    
               "create": null,    
               "rename": null,    
               "remove": null,    
               "ccp": null,    
               "add": {    
                   "label": "add",    
                   "action": function (obj) {  
                       var inst = jQuery.jstree.reference(obj.reference);    
                       var clickedNode = inst.get_node(obj.reference);   
                       nodeCreate();
                   }    
               },    
               "delete": {    
                   "label": "delete",    
                   "action": function (obj) {  
                       var inst = jQuery.jstree.reference(obj.reference);    
                       var clickedNode = inst.get_node(obj.reference);   
                       nodeDelete();
                   }    
               },
               "update": {    
                   "label": "update",    
                   "action": function (obj) {  
                       nodeRename();  
                   }    
               }    
           }   
       }  
	});
	//JSTree 点击事件
	$('#jstree_resource').on("changed.jstree", function (e, data) {
		console.log(data.node.id);
		if(data.node!=null){
			$("#resourceNodeId_hidden").val(data.node.id);
			$('#resourceTableId').bootstrapTable('refresh');  
		}
	 });
</script>

<!-- 用户组与资源组 -->
<script type="text/javascript">
	function resourceGroupTableInit() {
		var getgroupURL = "<c:url value='/manage/resGroupSearchWithCusGrpId.json' />";
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
	                   title: '权限',
	                   field: 'cusGrpResGrpRelModel',
	                   align: 'center',
	                   formatter:function (value, row, index) {
	               		   if(null != value ){
	                		   if((null == row.cusGrpResGrpRelModel.startDate || new Date(row.cusGrpResGrpRelModel.startDate) < new Date() )
		                			   &&  (null == row.cusGrpResGrpRelModel.endDate || new Date(row.cusGrpResGrpRelModel.endDate) > new Date() )){
	                			   return '<span class="label label-success">有权限</span>';
	                		   }else{
	                			   return '<span class="label label-danger">已过期</span>';
	                		   }
	                	   }else{
	                		   return '<span class="label label-danger">无权限</span>';
	                	   }
	                	   
	                    }
	               },
	               {
	                   title: '权限起始时间',
	                   field: 'cusGrpResGrpRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	               		   if(null != value){
	                		   if(null == value.startDate){
	                			   return "无限制";
	                		   }else{
	                			   return new Date(value.startDate).format("yyyy-MM-dd HH:mm");
	                		   }
	                	   }else{
	                		   return "-";
	                	   }
	                 } 
	               },
	               {
	                   title: '权限截至时间',
	                   field: 'cusGrpResGrpRelModel',
	                   align: 'center',
	                   formatter:function(value,row,index){
	               		   if(null != value){
	                		   if(null == value.endDate){
	                			   return "无限制";
	                		   }else{
	                			   return new Date(value.endDate).format("yyyy-MM-dd HH:mm"); 
	                		   }
	                	   }else{
	                		   return "-";
	                	   }
	                 }
	               },
	               {
	                   title: '操作',
	                   field: 'id',
	                   align: 'center',
	                   width: 80,
	                   formatter:function(value,row,index){
	                	   if(row.permissionAttrId == "1"){
	                		   return "-";
	                	   }else{
		                	   if(null != row.cusGrpResGrpRelModel 
		                			   && (null == row.cusGrpResGrpRelModel.startDate || new Date(row.cusGrpResGrpRelModel.startDate) < new Date() ) 
		                			   &&  (null == row.cusGrpResGrpRelModel.endDate || new Date(row.cusGrpResGrpRelModel.endDate) > new Date() ) ){
		                		   var e ='<button type="button" class="btn btn-xs btn-warning"  onclick="removeResGroupPermission(\''+ row.id +'\',this)" data-toggle="modal"  data-loading-text="Loading...">禁用</button>';
		                	   }else{
		                		   var cusGrpResGrpRelModel = null;
		                		   var startDate = null;
		                		   var endDate = null;
		                		   if(null != row.cusGrpResGrpRelModel ){
		                			   if(null != row.cusGrpResGrpRelModel.startDate){
		                				   startDate = new Date(row.cusGrpResGrpRelModel.startDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                			   if(null != row.cusGrpResGrpRelModel.endDate){
		                				   endDate = new Date(row.cusGrpResGrpRelModel.endDate).format("yyyy-MM-dd HH:mm");
		                			   }
		                		   }
		                		   var e ='<button type="button" class="btn btn-xs btn-success" data-toggle="modal" onclick="addResGroupPermPreProcess(\''+ row.id +'\',\''+ row.name +'\',\''+ startDate +'\',\''+ endDate +'\')" data-target="#addResGroupPermissionLayer">授权</button>';
		                	   }
		                    	return e;  
	                	   }
	                 } 
	               }
	           ],
			formatLoadingMessage: function () {
		    	return "请稍等，正在加载中...";
		  	}
	      });
	}
	
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
			    search['specificCusGrpId'] = $("#userGroupId_hidden").val();  
			    return JSON.stringify(search);
	      }
	    };
	    return temp;
	  }
	
	$('#doResGroupsearch').click(function() {
		$('#resourceGroupTableId').bootstrapTable('refresh');
    });
	
   function addResGroupPermPreProcess(resGrpId, resGrpName, startDate, endDate){
	   $('#resGrpId_addGrpPer').val(resGrpId);
	   $('#reGrpName_addGrpPer').val(resGrpName);
	   $('#userGrpId_addGrpPer').val($("#userGroupId_hidden").val());
	   $('#userGrpName_addGrpPer').val($("#cusGrpName_hidden").val());
	   if(null != startDate && startDate != 'null'){
		   $('#startDate_addGrpPer').val(startDate);
	   }
	   if(null != endDate && endDate != 'null'){
		   $('#endDate_addGrpPer').val(endDate);
	   }
   }
   
	//更新用户与资源组权限
	$('#saveButton_addGrpPer').click(function() {
		 //button失效，防止重复提交
		 //disabled="true"
		 $('#saveButton_addGrpPer').attr("disabled", true);

		 //关联用户组查询用户标识
		 var jointAuthFlag = null;
      //定义参数  
      var array = {};  
      //遍历form 组装json  
      $.each($("#addResGrpPermissionForm").serializeArray(), function(i, field) {  
          //可以添加提交验证  
          if('' == field.value){
          	array[field.name] = null;  
          }else{
          	array[field.name] = field.value;  
          }
      });  

      //参数转为json字符串，并赋给变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
      var modelJsonStr = JSON.stringify(array);
      
      var startDateStr =$("#startDate_addGrpPer").val();
      var endDateStr =$("#endDate_addGrpPer").val();
	    $.ajax({
		    url:"<c:url value='/userGroup/authResGrpPermission.json' />",
		    data:{   modelJsonStr : modelJsonStr, startDateStr : startDateStr, endDateStr : endDateStr },  
		    type:'get',  
		    cache:false,  
		    dataType:'json',  
		    success:function(data) {
		    	if(data.status == 1){
		    		$('#resourceGroupTableId').bootstrapTable('refresh');  
		    		$("#closeButton_addGrpPer").click();
		    	}else{
		    		alert("操作失败！");
		    	}
		    	$('#saveButton_addGrpPer').attr("disabled", false);
		     },  
		     error : function() {  
		          alert("系统异常！");  
		         $('#saveButton_addGrpPer').attr("disabled", false);
		     }  
		});
      
	 });  
	
	//禁用资源
	   function removeResGroupPermission(resGroupId,buttonObj){
		   if (!confirm('您确定取消授权吗？')) {
				return;
			}
		   $(buttonObj).button('loading');
		   var userGrpId = $("#userGroupId_hidden").val();
		   $.ajax({
			    url:"<c:url value='/manage/disableResGroupPermission.json' />",
			    data:{   resGroupId : resGroupId, userGrpId : userGrpId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$(buttonObj).button('reset');
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
	   }
   
</script>

<script type="text/javascript">
	//时间选择器
	$('.datetimepicker').datetimepicker({
		format: "yyyy-mm-dd hh:ii",
		language: 'zh-CN',
		autoclose:true,
		todayHighlight:true
	});
</script>            

<%@ include file="/common/footer.html"%>
