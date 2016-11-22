var getUserURL = rootUri + "/manage/roleSearch.json";
var pageNumber = 1;
$('#roleListTableId')
		.bootstrapTable(
				{
					method : 'get',
					url : getUserURL,
					dataType : "json",
					queryParams : roleQueryParams,
					pageSize : 10,
					pageList : [ 10, 25, 50 ], // 可供选择的每页的行数（*）
					pageNumber : pageNumber,
					pagination : true, // 分页
					singleSelect : false,
					idField : "id", // 标识哪个字段为id主键
					// showColumns: true, //显示隐藏列
					// showRefresh: true, //显示刷新按钮
					locale : "zh-CN", // 表格汉化
					// search: true, //显示搜索框
					sidePagination : "server", // 服务端处理分页
					columns : [
							{
								title : '角色ID',
								field : 'id',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '角色名称',
								field : 'name',
								align : 'center',
								valign : 'middle'
							}/*,
							{
								title : '状态',
								field : 'status',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									if (value == "Y") {
										return '<span class="label label-success">可用</span>';
									} else {
										return '<span class="label label-danger">不可用</span>';
									}
								}
							}*/,
							{
								title : '创建时间',
								field : 'createDate',
								align : 'center',
								formatter : function(value, row, index) {
									return new Date(value).format("yyyy-MM-dd");
								}
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var e = '<button class="btn btn-xs btn-warning" onclick="showRole(\''
											+ row.id
											+ '\',\''
											+ row.name
											+ '\')"><i class="icon-pencil"></i> </button>  ';
									var d = '<button class="btn btn-xs btn-danger" onclick="deleteRoleById(\''
											+ row.id
											+ '\')"><i class="icon-remove"></i> </button>';
									return e + d;
								}
							} ],
					formatLoadingMessage : function() {
						return "请稍等，正在加载中...";
					}
				});

function roleQueryParams(params) { // 配置参数
	var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		pageNumber : params.pageNumber, // 页码
		limit : params.limit, // 页面行数大小
		offset : params.offset, // 分页偏移量
		sort : params.sort, // 排序列名
		sortOrder : params.order,// 排位命令（desc，asc）
		search : $("#userNameSearch").val()
	};
	return temp;
}

// 给楼栋赋值Ajax
var buildings;
$.get(rootUri + "/manage/allBuildings.json", function(data, status) {
	if (status = 4) {
		$.each(data, function(n, value) {
			$("#buildingId").append(
					"<option value='" + value.id + "'>" + value.name
							+ "</option>");
			buildings = data;
		});
	}
	$('#buildingId').selectpicker('refresh');
});

function newRolePre(){
	$("#roleNameId").val("");
	$("#roleId").val("");
	$('#buildingId').selectpicker('deselectAll');
}

function saveRole(){
	/*var search = {};
	search["name"] = $("#roleNameId").val();
	search["roleId"] = $("#roleId").val();
	search["roleBuildings"] = $('#buildingId').val();
	console.log( JSON.stringify(search));*/
	// 参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件
	$("#saveButtonId").button('loading');
	$.ajax({
		url : rootUri + "/manage/saveRole.json",
		data : {
			name : $("#roleNameId").val(),
			roleId : $("#roleId").val(),
			roleBuildings : JSON.stringify($('#buildingId').val())
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			$("#saveButtonId").button('reset');
			if (data.status == 1) {
				$('#roleListTableId').bootstrapTable('refresh');
				alert("保存成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
			$("#saveButtonId").button('reset');
		}
	});
}
function showRole(roleId,roleName){
	$("#roleNameId").val(roleName);
	$("#roleId").val(roleId);
	$.ajax({
		url : rootUri + "/manage/getBuildingsByRoleId.json",
		data : {
			roleId : roleId
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
				console.log(data);
				$('#buildingId').selectpicker('val', data);
				//alert("删除成功");
		},
		error : function() {
			alert("系统异常！");
		}
	});
	$('#roleModal').modal('show');
}

function deleteRoleById(roleId){
	$.ajax({
		url : rootUri + "/manage/deleteRole.json",
		data : {
			roleId : $("#roleId").val()
		},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			if (data.status == 1) {
				$('#roleListTableId').bootstrapTable('refresh');
				alert("删除成功");
			} else {
				alert("系统异常！");
			}
		},
		error : function() {
			alert("系统异常！");
		}
	});
}

