<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common_taglib.jsp"%>
<%@ include file="/common/common_css.jsp"%>
<%@ include file="/common/common_js.jsp"%>
<!DOCTYPE html>
<html class="iframe-h">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title></title>
<link rel="stylesheet" type="text/css" href="${path }/css/list.css" />
<link rel="stylesheet" type="text/css" href="${path }/css/admin.css" />
<style>
.layui-toolbar {
	width: calc(40% - 55px);
	display: flex;
	justify-content: flex-end;
}
</style>
</head>
<body>
<div class="serch-bar">
    <div class="search-input">
        名称：
        <div class="layui-inline layui-search-input">
            <input name="pName" class="layui-input" id="pName"
                   autocomplete="off">
        </div>
        检测时间：
        <div class="layui-inline layui-search-date">
            <input class="layui-input" id="startTime" name="startTime"
                   type="text" placeholder="开始时间" autocomplete="off">
        </div>
        -
        <div class="layui-inline layui-search-date">
            <input class="layui-input" id="endTime" name="endTime" type="text"
                   placeholder="结束时间" autocomplete="off">
        </div>
        <button class="layui-btn layui-btn-orange layui-btn-search" data-type="reload">搜索</button>
    </div>
    <span class="layui-toolbar">
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="add"><div
                    class="layui-btn-toolbar-add"></div>添加</button>
				<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
                    class="layui-btn-toolbar-del"></div>删除</button>
		</span>
</div>
	<!-- <div class="serch-bar search-header">
		<div class="search-input">
			姓名：
			<div class="layui-inline layui-search-input">
				<input name="pName" class="layui-input" id="pName"
					autocomplete="off">
			</div>
			检测时间：
			<div class="layui-inline layui-search-date">
				<input class="layui-input" id="startTime" name="startTime"
					type="text" placeholder="开始时间" autocomplete="off">
			</div>
			-
			<div class="layui-inline layui-search-date">
				<input class="layui-input" id="endTime" name="endTime" type="text"
					placeholder="结束时间" autocomplete="off">
			</div>
			<button class="layui-btn layui-btn-orange layui-btn-search"
				data-type="reload">搜索</button>
				<span class="layui-toolbar">
				<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="add"><div
                    class="layui-btn-toolbar-add"></div>添加</button>
				<button class="layui-btn layui-btn-orange layui-btn-search" data-type="add">添加</button>
				<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
                    class="layui-btn-toolbar-del"></div>删除</button>
                    </span>
		</div>
	</div> -->

	<table id="user" lay-filter="userList"></table>
	<script id="toolBar" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="changePas">查看</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">修改</a>
</script>
	<!-- 创建时间数据格式化 -->
	<script type="text/html" id="lastLoginTime">
    {{ dateFormat(d.lastLoginTime,'yMdhms') }}
</script>
	<!-- 出生日期数据格式化 -->
	<script type="text/html" id="createUser">
    {{ creaetUser(d.createUser) }}
</script>
	<!-- 用户状态数据格式化 -->
	<script type="text/html" id="status">
    {{ statusFormat(d.status) }}
</script>
	<!-- 用户性别数据格式化 -->
	<script type="text/html" id="userSex">
    {{ userSexFormat(d.userSex) }}
</script>
	<script src="${path }/plugin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		var selectAreaId;
		var selectAreaName;
		layui
				.use(
						[ 'table', 'laydate', 'form', 'jquery' ],
						function() {
							var table = layui.table;
							var laydate = layui.laydate;
							var $ = layui.jquery;
							var layerIndex;
							var layerInitWidth;
							var layerInitHeight;
							$(window)
									.resize(
											function() {
												resizeLayer($, layerIndex,
														layerInitWidth,
														layerInitHeight);
											});
							var areaName = decodeURI('${param.areaName }');
							//查询条件开始时间实发始化
							var endDate = laydate.render({
								elem : '#endtime',//选择器结束时间
								type : 'datetime',
								min : "1970-1-1",//设置min默认最小值
								done : function(value, date) {
									if (value == null || value == "") {
										startDate.config.max = {
											year : "2099",
											month : "12",
											date : "31",
											hours : "0",
											minutes : "0",
											seconds : "0"
										}
									} else {
										startDate.config.max = {
											year : date.year,
											month : date.month - 1,//关键
											date : date.date,
											hours : date.hours,
											minutes : date.minutes,
											seconds : date.seconds - 1
										}
									}
								}
							});
							//日期范围
							var startDate = laydate
									.render({
										elem : '#startTime',
										type : 'datetime',
										max : "2099-12-31",//设置一个默认最大值
										done : function(value, date) {
											endDate.config.min = {
												year : date.year,
												month : date.month - 1, //关键
												date : date.date,
												hours : date.hours,
												minutes : date.minutes,
												seconds : date.seconds
											};
											var nowStrDate = date.year + "-"
													+ date.month + "-"
													+ date.date + " "
													+ date.hours + ":"
													+ date.minutes + ":"
													+ date.seconds;
											var endyear = endDate.config.dateTime.year;
											var endmonth = endDate.config.dateTime.month + 1;
											var enddate = endDate.config.dateTime.date;
											var endhours = endDate.config.dateTime.hours;
											var endminutes = endDate.config.dateTime.minutes;
											var endseconds = endDate.config.dateTime.seconds;
											var nowEndDate = endyear + "-"
													+ endmonth + "-" + enddate
													+ " " + endhours + ":"
													+ endminutes + ":"
													+ endseconds;
											nowEndDate = new Date(nowEndDate);
											nowStrDate = new Date(nowStrDate);
											if (nowEndDate < nowStrDate) {
												layer.msg('选择的结束时间小于开始时间了');
											}
										}
									});
							//表格数据初始化
							table
									.render({
										elem : '#user',
										height : 'full-120',
										url : '${path}/personnelFileInfo/getPage.do}',
										cellMinWidth : 80,
										cols : [ [ //标题栏
												{
													type : 'checkbox',
													LAY_CHECKED : false
												},
												{
													field : 'PName',
													title : '姓名',

												},
												{
													field : 'userCode',
													title : '编号',

												},
											/* 	{
													field : 'PBirth',
													title : '出生日期',
													templet:function (d) {
														if(d.PBirth==""){
															return '<a style="color:#ed2a4a" >--</a>';
														}else{
															return dateFormat(d.PBirth,'yMd');
														} 	
													}
												}, */
												/* {
													field : 'PPhone',
													title : '手机号',
													templet:function (d) {
														if(d.PPhone==""){
															return '<a style="color:#ed2a4a" >--</a>';
														}else{
															return d.PPhone;
														} 	
													}
												}, */
											/* 	{
													field : 'idCardNo',
													title : '身份证号',
													templet:function (d) {
														if(d.idCardNo==""){
															return '<a style="color:#ed2a4a" >--</a>';
														}else{
															return d.idCardNo;
														} 	
													}
												}, */
												{
													field : 'health',
													title : '健康状态',
													templet:function (d) {
														 if(d.health==0){
															return '<a style="color:#00A600" >正常</a>';
														}else if(d.health==1){
															return '<a style="color:#ed2a4a" >异常</a>';
														}
														}
												},
												{
													field : 'lastTemp',
													title : '体温',
													templet : function(d) {
														let temperature = Math.round(d.lastTemp * 10) / 10 + "℃";
														if (d.health == 0)
															return '<font color=\"green\">' + temperature + '</font>';
														else if (d.health == 1)
															return '<font color=\"red\">' + temperature + '</font>';
													}
													/* templet : function(d) {
														let temperature = Math.round(d.temperature * 10) / 10 + "℃";
														if (d.isAlarm == 0)
															return '<font color=\"green\">' + temperature + '</font>';
														else if (d.isAlarm == 1)
															return '<font color=\"red\">' + temperature + '</font>';
													} */
												},
												{
													field : 'lastDetection',
													title : '检测时间',
													templet:function (d) {
														if(d.lastDetection==""){
															return '<a style="color:#ed2a4a" >--</a>'
														}else{
															return dateFormat(d.lastDetection,'yMdhms');
														}
														}
												},
												{
													fixed : 'right',
													title : '操作',
													toolbar : '#toolBar',
													width : 280
												} ] ],
										skin : 'line', //line row表格风格
										even : true, //,size: 'lg' //尺寸
										page : true, //是否显示分页,
										limits : [ 10, 15 ],
										limit : 10
									});
							//监听工具条
							table
									.on(
											'tool(userList)',
											function(obj) {
												var data = obj.data;
												if (obj.event === 'changePas') {
													top.layer.open({
									                    type: 2,
									                    title: '人员档案详细信息',
									                    area: ['1200px', '550px'],
									                    fixed: false, //不固定
									                    maxmin: false,
									                    shade: [0.3],
									                    anim: 5,
									                    isOutAnim: true,
									                    resize: false,
									                    content: ['${path}/jsp/personnelFileInfo/personnelFileinfo_display.jsp?PId=' + data.PId, 'no'],
									                    success: function (layero, index) {
									                        layerIndex = index;
									                        layerInitWidth = $("#layui-layer" + layerIndex).width();
									                        layerInitHeight = $("#layui-layer" + layerIndex).height();
									                        resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
									                    },
									                    end: function () {
									                        table.reload('dataTable', {page: {curr: currpage}});
									                    }
									                });
												} else if (obj.event === 'edit') {
													if ("${sessionScope.loginUser.userId }" !== data.userId) {
														top.layer
																.open({
																	type : 2,
																	title : '个人档案信息修改',
																	area : [
																			'1200px',
																			'650px' ],
																	fixed : false, //不固定
																	maxmin : false,
																	shade : [ 0.3 ],
																	anim : 5,
																	isOutAnim : true,
																	resize : false,
																	content : '${path}/jsp/personnelFileInfo/personnelFileinfo_update.jsp?PId='
																			+ data.PId,
																	success : function(
																			layero,
																			index) {
																		layerIndex = index;
																		layerInitWidth = $(
																				"#layui-layer"
																						+ layerIndex)
																				.width();
																		layerInitHeight = $(
																				"#layui-layer"
																						+ layerIndex)
																				.height();
																		resizeLayer(
																				$,
																				layerIndex,
																				layerInitWidth,
																				layerInitHeight);
																	},
																	end : function() {
																		table
																				.reload('user');
																	}
																});
													} else {
														top.layer
																.alert("不能编辑自己！");
													}
												} else if (obj.event === 'del') {
													let id = [ data.PId ];
													//console.log(id);
													top.layer
															.confirm(
																	'确定要删除选中的数据吗？',
																	{
																		btn : [
																				'确定',
																				'取消' ]
																	},
																	function() {
																		var actionUrl = "${path}/user/del.do";
																		var params = {
																			ids : id
																		};
																		$
																				.post(
																						actionUrl,
																						params,
																						function(
																								data) {
																							if (data.success) {
																								top.layer
																										.alert('删除成功！！');
																								active
																										.reload();
																							} else {
																								top.layer
																										.alert('删除失败！！');
																								return;
																							}
																						});
																	});

												}
											});
							var $ = layui.jquery, active = {
								//人员档案信息添加
								add : function() {
									top.layer
											.open({
												type : 2,
												title : '人员档案信息添加',
												area : [ '1200px', '650px' ],
												fixed : false, //不固定
												maxmin : false,
												shade : [ 0.3 ],
												anim : 5,
												isOutAnim : true,
												resize : false,
												content : '${path}/jsp/personnelFileInfo/personnelFileinfo_add.jsp?PId='
														+ '${param.PId }'
														+ '&PName='
														+ decodeURI('${param.PName }'),
												success : function(layero,
														index) {
													$(':focus').blur();
													layerIndex = index;
													layerInitWidth = $(
															"#layui-layer"
																	+ layerIndex)
															.width();
													layerInitHeight = $(
															"#layui-layer"
																	+ layerIndex)
															.height();
													resizeLayer($, layerIndex,
															layerInitWidth,
															layerInitHeight);
												},
												end : function() {
													if (typeof (selectAreaId) != "undefined"
															&& typeof (selectAreaName) != "undefined") {
														parent.creatMenu(
																selectAreaId,
																selectAreaName);
													} else {
														active.reload();
													}
												}
											});
								},
								//用户信息批量删除
								delALL : function() {
									var ids = active.getCheckData();
									if (ids.length == 0) {
										top.layer.alert('请选择需要删除的数据！！');
									} else {
										top.layer
												.confirm(
														'确定要删除选中的所有数据吗？',
														{
															btn : [ '确定', '取消' ]
														},
														function() {
															debugger;
															var actionUrl = "${path}/personnelFileInfo/del.do";
															var params = {
																ids : ids
															};
															$
																	.post(
																			actionUrl,
																			params,
																			function(
																					data) {
																				if (data.success) {
																					top.layer
																							.alert('删除成功！！');
																					active
																							.reload();
																				} else {
																					top.layer
																							.alert('删除失败！！');
																					return;
																				}
																			});
														});
									}
								},
								//刷新表格数据重新请求
								reload : function() {
									//执行重载
									table.reload('user', {
										page : {
											curr : 1
										},
										//查询条件参数
										where : {
											pName : $("#pName").val(),
											startTime : $("#startTime").val(),
											endTime : $("#endTime").val(),
										}
									});
								},
								//设置用户状态启用
								userNormal : function() {
									var ids = active.getCheckData();
									if (ids.length == 0) {
										top.layer.alert('请选择需要启用的用户！！');
									} else {
										top.layer
												.confirm(
														'确定要启用选中的用户吗？',
														{
															btn : [ '确定', '取消' ]
														},
														function() {
															var actionUrl = "${path}/user/changeStatus.do";
															var params = {
																ids : ids,
																status : 0
															};
															$
																	.post(
																			actionUrl,
																			params,
																			function(
																					data) {
																				if (data.success) {
																					top.layer
																							.alert('启用成功！！');
																					active
																							.reload();
																				} else {
																					top.layer
																							.alert('启用失败！！');
																					return;
																				}
																			});
														});
									}
								},
								//设置用户状态停用
								userUnuser : function() {
									var ids = active.getCheckData();
									if (ids.length == 0) {
										top.layer.alert('请选择需要停用的用户！！');
									} else {
										top.layer
												.confirm(
														'确定要停用选中的用户吗？',
														{
															btn : [ '确定', '取消' ]
														},
														function() {
															var actionUrl = "${path}/user/changeStatus.do";
															var params = {
																ids : ids,
																status : 1
															};
															$
																	.post(
																			actionUrl,
																			params,
																			function(
																					data) {
																				if (data.success) {
																					top.layer
																							.alert('停用成功！！');
																					active
																							.reload();
																				} else {
																					top.layer
																							.alert('停用失败！！');
																					return;
																				}
																			});
														});
									}
								},
								//获取被选中的表格数据ID集合
								getCheckData : function() {
									var data = table.checkStatus('user').data;
									var ids = [];
									for (var i = 0; i < data.length; i++) {
										ids.push(data[i].PId);
									}
									return ids;
								}
							};
							$('i').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
							// 			$('#userName').on('click', function() {
							// 				active.reload();
							// 			});
							$('.layui-btn').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
						});
		
		
		$("#withExport").click(function(){
			layer.msg("点击事件");
			});

		//用户性别数据格式化方法
		function userSexFormat(value) {
			return value == "0" ? "女" : "男";
		}

		//用户状态格式化方法
		function statusFormat(value) {
			return value == "1" ? "正常" : "异常";
		}

		function creaetUser(value) {
			if (value == "") {
				return "无";
			}
			return value.userName;
		}
		
	</script>
</body>
</html>