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
<style type="text/css">
.layui-table-body:hover {
	overflow-x: auto;
}
</style>
</head>
<body>
	<div class="serch-bar">
		<div class="search-input" style="width: 100%;">
			<label class="layui-form-label">姓名</label>
			<div class="layui-inline layui-search-input">
				<input name="userName" class="layui-input" id="userName"
					autocomplete="off">
			</div>
			<label class="layui-form-label">部门</label>
			<div class="layui-inline layui-search-date">
				<input type="text" id="orgId" lay-filter="tree" class="layui-input">
			</div>
			<label class="layui-form-label">考勤时间</label>
			<div class="layui-inline layui-search-date">
				<input class="layui-input" id="startTime" name="startTime"
					type="text" placeholder="开始时间" autocomplete="off">
			</div>
			&nbsp;-&nbsp;
			<div class="layui-inline layui-search-date">
				<input class="layui-input" id="endTime" name="endTime" type="text"
					placeholder="结束时间" autocomplete="off">
			</div>
			<button class="layui-btn layui-btn-orange layui-btn-search"
				data-type="reload">搜索</button>
			<button class="layui-btn layui-btn-orange layui-btn-search"
				data-type="exports" id="export">导出</button>
		</div>
	</div>
	<table class="layui-table" id="attReport"></table>

	<script src="${path}/plugin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		layui.config({
			base : '${path }/plugin/layui2.4/lay/modules/' // js地址 
		}).extend({
			treeSelect : 'treeSelect'
		});
		layui
				.use(
						[ 'table', 'laydate', 'form', 'treeSelect', 'jquery' ],
						function() {
							var table = layui.table;
							var form = layui.form;
							var $ = layui.jquery;
							var layerIndex;
							var layerInitWidth;
							var layerInitHeight;
							var currpage = $('.layui-laypage-skip').children(
									'.layui-input').val();
							var laydate = layui.laydate;
							var treeSelect = layui.treeSelect;

							//查询条件开始时间实发始化
							var startDate = laydate.render({
								elem : '#startTime', //选择器结束时间
								type : 'date',
								min : "1970-1-1", //设置min默认最小值
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
											month : date.month - 1, //关键
											date : date.date,
											hours : date.hours,
											minutes : date.minutes,
											seconds : date.seconds - 1
										}
									}

									$("#startDate").val(
											date.year + "-" + date.month + "-"
													+ date.date);
								}
							});

							//日期范围
							var endDate = laydate
									.render({
										elem : '#endTime',
										type : 'date',
										max : "2099-12-31", //设置一个默认最大值
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
												return;
											}
											$("#endDate").val(
													endyear + "-" + endmonth
															+ "-" + enddate);
										}
									});
							$(window)
									.resize(
											function() {
												resizeLayer($, layerIndex,
														layerInitWidth,
														layerInitHeight);
											});

							var creatTable = function(start_time, end_time) {
								var arrcols = [ {
									'field' : 'index',
									'title' : "序号", 
									"width" : 80, 
									"sort" : true
								}, {
									'field' : 'name',
									'title' : '姓名'
								}, {
									'field' : 'org',
									'title' : '组织机构'
								}, {
									'field' : 'holiday',
									'title' : '放假'
								}, {
									'field' : 'takeOff',
									'title' : '请假'
								}, {
									'field' : 'changeOff',
									'title' : '换休'
								}, {
									'field' : 'beLate',
									'title' : '迟到'
								}, {
									'field' : 'leaveEarly',
									'title' : '早退'
								}, {
									'field' : 'absent',
									'title' : '旷工'
								} ];
								// 表格数据初始化
								table.render({
									elem : '#attReport',
									height : 'full-120',
									url : '${path}/api/attendanceReport',
									where : {
										"userName" : $("#userName").val(),
										"startTime" : $("#startTime").val(),
										"endTime" : $("#endTime").val(),
										"org" : $("#orgId").val()
									},
									cellMinWidth : 80,
									cols : [ arrcols ],
									skin : 'line', //line row表格风格
									even : true, //,
									page : false, //是否显示分页,
									limits : [ 10, 1000 ],
									limit : 1000
								});
							}

							var exportRecordToExcel = function() {
								var userName = $("#userName").val();
								var startTime = $("#startTime").val();
								var endTime = $("#endTime").val();
								var org = $("#orgId").val();

								if (startTime == undefined || startTime == "") {
									alert("请添加查询条件！");
									return;
								}

								window.location.href = "${path}/api/exportReportToExcel?userName="
										+ userName
										+ "&startTime="
										+ startTime
										+ "&endTime=" + endTime + "&org=" + org;
							}

							//			creatTable();
							treeSelect.render({
								// 选择器
								elem : '#orgId',
								// 数据
								data : '${path}/campusOrg/getOrgTree.do',
								// 异步加载方式：get/post，默认get
								type : 'post',
								// 占位符
								placeholder : '请选择组织机构',
								// 是否开启搜索功能：true/false，默认false
								search : true,
								// 点击回调
								click : function(d) {
									$("#orgId").val(d.current.id);
								},
								// 加载完成后的回调函数
								success : function(d) {
								}
							});
							var $ = layui.jquery, active = {
								//机构信息添加
								attExport : function() {
									top.layer
											.open({
												type : 2,
												title : '机构信息添加',
												area : [ '1000px', '520px' ],
												fixed : false, //不固定
												maxmin : false,
												shade : [ 0.3 ],
												anim : 5,
												isOutAnim : true,
												resize : false,
												content : '${path}/jsp/campusOrg/org_add.jsp',
												success : function() {
													$(':focus').blur();
												},
												end : function() {
													active.reload();
												}
											});
								},
								//刷新表格数据重新请求
								reload : creatTable,
								exports : function() {
									creatTable();
									exportRecordToExcel();
								}
							};
							$('i').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
							$('.layui-btn').on('click', function() {
								var type = $(this).data('type');
								active[type] ? active[type].call(this) : '';
							});
						});
	</script>
</body>
</html>