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
								<a href="javascript:void(0);"  onclick = "$('#userGroupListTable').hide();" id="icon_user_list1" class="wminimize">
									<i class="icon-chevron-up"></i>
								</a>
								<a href="javascript:void(0);"  onclick = "$('#userGroupListTable').show();" id="icon_user_list2" class="wminimize">
									<i class="icon-chevron-down"></i>
								</a>
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
												data-toggle="modal" data-target="#myModal">
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
								<a href="javascript:void(0);"  onclick = "$('#userGroupDetailsTable').hide();" id="icon_user_list1" class="wminimize">
									<i class="icon-chevron-up"></i>
								</a>
								<a href="javascript:void(0);"  onclick = "$('#userGroupDetailsTable').show();" id="icon_user_list2" class="wminimize">
									<i class="icon-chevron-down"></i>
								</a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="userGroupDetailsTable">
						 
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">用户详情</a></li>
							      <li><a href="#userGroup" >用户与用户组</a></li>
							      <li><a href="#userGroupResource"  >用户组与资源</a></li>
							      <li><a href="#userGroupResourceGroup"  >用户与资源组</a></li>
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
				                                  <label class="col-lg-2 control-label">状态</label>
				                                  <div class="col-lg-3">
					                                  <div class="make-switch" data-on="primary" data-off="info">
				                         					<input type="checkbox" checked id = "userGroupStatus_InForm">
				                     				  </div>
				                                  </div>
			                                </div>
										    <div class="form-group">
				                                  <label class="col-lg-2 control-label">创建人</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true"  placeholder="创建人" id="userGroupCreateUser_InForm">
				                                  </div>
			                                </div>
							      		
									    	<div class="control-group">
									          <label class="control-label"></label>
									          <!-- Button -->
									          <div class="controls">
									            <button class="btn btn-success disabled">保存</button>
									          </div>
									     	</div>
										</form>
					      		  </div>
					      		  <!-- usergroup table -->
							      <div class="tab-pane" id="userGroup">
							      	<div class="col-lg-12">
										<table class="table table-striped table-bordered table-hover"
											id="userGroupListTableId">
											
										</table>
									</div>
								  </div>
							      <div class="tab-pane" id="userGroupResource">...</div>
							      <div class="tab-pane" id="userGroupResourceGroup">...</div>
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
				<h4 class="modal-title" id="myModalLabel">添加用户组</h4>
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

<input type="hidden" id="userGroupId_hidden" >

<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	
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
                   title: '状态',
                   field: 'status',
                   align: 'center',
                   valign: 'middle',
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
                 var e = '<a href="javascript:void(0);" mce_href="#" onclick="showUserGroup(\''+ row.id + '\')">详情</a> ';  
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
	  
	  //创建新的userGroup
		 function deleteUserGroupById(customerGroupId){
			if(null == customerGroupId || customerGroupId == ""){
				alert("用户组ID为空");
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
	  var showUserGroup = function (userGroupId) {
		  $("#userGroupId_hidden").val(userGroupId);
		  $("#userGroupListTable").hide();
		  $("#userGroupDetailsTable").show();
		  initUserGroupDetailForm(userGroupId);
	  };

	  //初始化 UserDetailForm 
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
	
	 //tab 切换
    $(function () {
        $('#myTab a:first').tab('show');//初始化显示哪个tab
      
        $('#myTab a').click(function (e) {
          e.preventDefault();//阻止a链接的跳转行为
          $(this).tab('show');//显示当前选中的链接及关联的content
          
          //点击tab调用对应function
          if($(this).attr("href") == "#userGroup"){
        	  userGroupListTableInit();
        	  //$("#userGroupListTableId").bootstrapTable('refresh');
          } 
        })
      })
</script>
<%@ include file="/common/footer.html"%>
