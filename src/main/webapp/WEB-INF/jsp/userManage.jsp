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
								<a href="javascript:void(0);"  onclick = "$('#userListTable').hide();" id="icon_user_list1" class="wminimize">
									<i class="icon-chevron-up"></i>
								</a>
								<a href="javascript:void(0);"  onclick = "$('#userListTable').show();" id="icon_user_list2" class="wminimize">
									<i class="icon-chevron-down"></i>
								</a>
								<!-- <a href="#" class="wclose"><i class="icon-remove"></i></a> -->
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content" id="userListTable">
							<div class="col-lg-12">
								<hr>
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-lg-2 control-label" style="width: 120px">用户名称</label>
										<div class="col-lg-3">
											<input type="text" id="userNameSearch" class="form-control" placeholder="用户名称">
										</div>
										<label class="col-lg-2 control-label" style="width: 120px">手机号</label>
										<div class="col-lg-3">
											<input type="text" id="userMobileSearch" class="form-control" placeholder="手机号">
										</div>
										<div class="col-lg-3">
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
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Matter -->
	<div class="matter">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="widget">
						<div class="widget-head">
							<div class="pull-left">用户详情</div>
							<div class="widget-icons pull-right">
								<a href="javascript:void(0);"  onclick = "$('#userDetailsTable').hide();" id="icon_user_list1" class="wminimize">
									<i class="icon-chevron-up"></i>
								</a>
								<a href="javascript:void(0);"  onclick = "$('#userDetailsTable').show();" id="icon_user_list2" class="wminimize">
									<i class="icon-chevron-down"></i>
								</a>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="widget-content"  style="display: none;" id="userDetailsTable">
							<!-- Bootstrap tab -->
							<div class="col-lg-12">
								 <ul class="nav nav-tabs" id="myTab">
							      <li class="active"><a href="#home">用户详情</a></li>
							      <li><a href="#userGroup" >用户与用户组</a></li>
							      <li><a href="#userResource"  >用户与资源</a></li>
							      <li><a href="#userResourceGroup"  >用户与资源组</a></li>
							    </ul>
							       
							    <div class="tab-content">
							      <div class="tab-pane active" id="home">
							      		<!-- Bootstrap 表单 -->
									  <form class="form-horizontal" role="form">
										    <div class="form-group">
				                                  <label class="col-lg-2 control-label">ID</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control" disabled="true" placeholder="ID" id="userId_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label">用户名</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true" id="userName_InForm" placeholder="用户名">
				                                  </div>
			                                </div>
			                                <div class="form-group">
				                                  <label class="col-lg-2 control-label">手机号</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true" placeholder="手机号" id="userMobile_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label">性别</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true" id="userSex_InForm" placeholder="性别">
				                                  </div>
			                                </div>
										    <div class="form-group">
				                                  <label class="col-lg-2 control-label">邮箱</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true" placeholder="邮箱" id="userEmail_InForm">
				                                  </div>
				                                   <label class="col-lg-2 control-label">生日</label>
				                                  <div class="col-lg-3">
				                                    <input type="text" class="form-control"  disabled="true" id="userBirth_InForm" placeholder="生日">
				                                  </div>
			                                </div>
			                                <div class="form-group">
			                                	  <label class="col-lg-2 control-label">客户关系</label>
				                                  <div class="col-lg-3">
				                                    <select class="form-control"  disabled="true"  id="userRelation_InForm">
				                                      <option></option>
				                                      <option value="1">来访</option>
				                                      <option value="2">业主</option>
				                                    </select>
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
								      <div class="col-lg-9">
											<hr>
											<form class="form-horizontal" role="form">
												<div class="form-group">
													<label class="col-lg-2 control-label" style="width: 120px">用户组名称</label>
													<div class="col-lg-3">
														<input type="text" id="userGroupNameSearch" class="form-control" placeholder="用户组名称">
													</div>
													<div class="col-lg-3">
														<button type="button" onclick = "$('#userGroupListTableId').bootstrapTable('refresh');" class="btn btn-primary">
															<i class="icon-search"></i> 查询
														</button>
													</div>
												</div>
											</form>
										</div>
							      	<div class="col-lg-12">
										<table class="table table-striped table-bordered table-hover"
											id="userGroupListTableId">
											
										</table>
									</div>
								  </div>
								  
							      <div class="tab-pane" id="userResource">...</div>
							      <div class="tab-pane" id="userResourceGroup">...</div>
							    </div>
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

<input type="hidden" id="userId_hidden" >

<%@ include file="/common/script.jsp"%>
<script type="text/javascript">
	//用户列表table
	$('#userListTableId').bootstrapTable({
		method: 'get',
	    url: "<c:url value='/user/showUserList.json' />", 
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
                   formatter:function(value,row,index){
                 var e = '<a href="javascript:void(0);" mce_href="#" onclick="showUser(\''+ row.id + '\')">详情</a> ';  
                 //var d = '<a href="#" mce_href="#" onclick="delete(\''+ row.id +'\')">删除</a> ';  
                 //   return e+d;
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
	      mobile: $("#userMobileSearch").val()
	    };
	    return temp;
	  }
	  
      //显示用户详情内容
	  var showUser = function (userId) {
		  $("#userId_hidden").val(userId);
		  $("#userListTable").hide();
		  $("#userDetailsTable").show();
		  initUserDetailForm(userId);
	  };
      
	  //初始化 UserDetailForm 
	  var initUserDetailForm = function (userId) {
		  $.ajax( {  
			    url:"<c:url value='/user/showUserDetail.json' />",
			    data:{   userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	$("#userId_InForm").val(data.id);
			    	$("#userName_InForm").val(data.name);
			    	$("#userMobile_InForm").val(data.mobile);
			    	$("#userSex_InForm").val(data.sex);
			    	$("#userEmail_InForm").val(data.email);
			    	$("#userBirth_InForm").val(data.birthdayStr);
			    	$("#userRelation_InForm").val(data.relation);
			     },  
			     error : function() {  
			          alert("系统异常！");  
			     }  
			});
	  };
	
      //userGroupListTableId 加载
	  var userGroupListTableInit = function () {
		    var oUserGroupListTableInit = new Object();
		    $('#userGroupListTableId').bootstrapTable({
				method: 'get',
			    url: "<c:url value='/userGroup/showUserGroupJoinUserId.json' />", 
			    dataType: "json",
			    queryParams: userGroupQueryParams,
			    pageSize: 10,
			    pageList: [10, 25, 50],  //可供选择的每页的行数（*）
			    pageNumber: 1,
			    pagination: true, //分页
			    singleSelect: false,
			    idField: "id",  //标识哪个字段为id主键
			    //showColumns: true, //显示隐藏列  
			    //showRefresh: true,  //显示刷新按钮
			    locale: "zh-CN", //表格汉化
			    //search: true, //显示搜索框
			    sidePagination: "server", //服务端处理分页
			    height: 390, 
			    cache: true,
			    uniqueId: "id",           //每一行的唯一标识，一般为主键列
			    toolbar: '#toolbar',        //工具按钮用哪个容器
		        striped: true,           //是否显示行间隔色
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
		                	   if(row.extendLong1 != null){
		                		   var e = '<a href="javascript:void(0)" mce_href="#" onclick="delUserGroupRelation(this, \''+ row.id +'\')">移除</a> ';  
		                	   }else{
		                		   var e = '<a href="javascript:void(0)" mce_href="#" onclick="addUserGroupRelation(this, \''+ row.id + '\')">加入</a> ';  
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
	
	  //userGroup table 入参
	 function userGroupQueryParams(params) {  //配置参数
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
	      pageNumber: params.pageNumber,  //页码
	      limit: params.limit,   //页面行数大小
	      offset: params.offset, //分页偏移量
	      sort: params.sort,  //排序列名
	      sortOrder: params.order ,//排位命令（desc，asc）
	      search: $("#userGroupNameSearch").val(),
	      userId: $("#userId_hidden").val()
	    };
	    return temp;
	  }
	  
	  //删除usergroup与user关系
	 function delUserGroupRelation(obj, groupId) {
		 var userId = $("#userId_hidden").val();
		 $.ajax( {  
			    url:"<c:url value='/user/delUserGroupRelation.json' />",
			    data:{   groupId : groupId, userId : userId },  
			    type:'get',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) {
			    	if(data.status == 1){
				    	$(obj).attr("onclick", "addUserGroupRelation(this, "+groupId+")");
				    	$(obj).html("加入");
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
		 function addUserGroupRelation(obj, groupId) {
			 var userId = $("#userId_hidden").val();
			 $.ajax( {  
				    url:"<c:url value='/user/addUserGroupRelation.json' />",
				    data:{   groupId : groupId, userId : userId },  
				    type:'get',  
				    cache:false,  
				    dataType:'json',  
				    success:function(data) {
				    	if(data.status == 1){
					    	$(obj).attr("onclick", "delUserGroupRelation(this, "+groupId+")");
					    	$(obj).html("移除");
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
          if($(this).attr("href") == "#userGroup"){
        	  userGroupListTableInit();
        	  //$("#userGroupListTableId").bootstrapTable('refresh');
          } 
        })
      })
</script>
<%@ include file="/common/footer.html"%>
