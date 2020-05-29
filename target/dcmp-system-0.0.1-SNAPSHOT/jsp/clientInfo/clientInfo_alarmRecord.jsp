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
<!-- 引入依赖文件 -->
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" href="${path }/plugin/layui/css/layui.css" />
<link rel="stylesheet" href="${path }/plugin/layui/css/my.css" />
<script type="text/javascript"
	src="${path }/plugin/layui/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${path }/plugin/layui/layui.all.js"></script>
</head>
<body>
	<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
		<ul class="layui-tab-title">
			<li class="layui-this">体温监测报警</li>
			<li>口罩佩戴报警</li>
		</ul>
		<div class="layui-tab-content">
			<!-- 体温监测报警 -->
			<div class="layui-tab-item layui-show">
				<div class="serch-bar search-header">
					<div class="search-input">
						姓名：
						<div class="layui-inline layui-search-input">
							<input name="PName" class="layui-input" id="PName"
								autocomplete="off">
						</div>
						最近监测时间：
						<div class="layui-inline layui-search-date">
							<input class="layui-input" id="startMkTime" name="startMkTime"
								type="text" placeholder="开始时间" autocomplete="off">
						</div>
						-
						<div class="layui-inline layui-search-date">
							<input class="layui-input" id="endMkTime" name="endMkTime"
								type="text" placeholder="结束时间" autocomplete="off">
						</div>
						<button class="layui-btn layui-btn-orange layui-btn-search"
							data-type="reload">搜索</button>
					</div>
				</div>
				<!-- table表单 -->
				<table id="alarmList" lay-filter="alarmList"></table>
				<!-- 创建时间数据格式化 -->
				<script type="text/html" id="createTime">
    			{{ dateFormat(d.createTime,'yMdhms') }}
				</script>
				<!-- 数据处理 -->
				<script type="text/javascript">
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
										$(window).resize(
												function() {
													resizeLayer($, layerIndex,
															layerInitWidth,
															layerInitHeight);
												});
										var areaName = decodeURI('${param.areaName }');
										//查询条件开始时间实发始化
										var endMkDate = laydate
												.render({
													elem : '#endMktime',//选择器结束时间
													type : 'datetime',
													min : "1970-1-1",//设置min默认最小值
													done : function(value, date) {
														if (value == null
																|| value == "") {
															startMkDate.config.max = {
																year : "2099",
																month : "12",
																date : "31",
																hours : "0",
																minutes : "0",
																seconds : "0"
															}
														} else {
															startMkDate.config.max = {
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
										var startMkDate = laydate
												.render({
													elem : '#startMkTime',
													type : 'datetime',
													max : "2099-12-31",//设置一个默认最大值
													done : function(value, date) {
														endMkDate.config.min = {
															year : date.year,
															month : date.month - 1, //关键
															date : date.date,
															hours : date.hours,
															minutes : date.minutes,
															seconds : date.seconds
														};
														var nowMkStrDate = date.year
																+ "-"
																+ date.month
																+ "-"
																+ date.date
																+ " "
																+ date.hours
																+ ":"
																+ date.minutes
																+ ":"
																+ date.seconds;
														var endmkyear = endMkDate.config.dateTime.year;
														var endmkmonth = endMkDate.config.dateTime.month + 1;
														var endmkdate = endMkDate.config.dateTime.date;
														var endmkhours = endMkDate.config.dateTime.hours;
														var endmkminutes = endMkDate.config.dateTime.minutes;
														var endmkseconds = endMkDate.config.dateTime.seconds;
														var nowMkEndDate = endmkyear
																+ "-"
																+ endmkmonth
																+ "-"
																+ endmkdate
																+ " "
																+ endmkhours
																+ ":"
																+ endmkminutes
																+ ":"
																+ endmkseconds;
														nowMkEndDate = new Date(
																nowMkEndDate);
														nowMkStrDate = new Date(
																nowMkStrDate);
														if (nowMkEndDate < nowMkStrDate) {
															layer
																	.msg('选择的结束时间小于开始时间了');
														}
													}
												});

										//表格数据初始化
										table
												.render({
													elem : '#alarmList',
													//height : 'full-120',
													url : '${path}/temperatureRecord/getRecordAlarm.do?code=${param.code}',
													cols : [ [ //标题栏
															{
																type : 'checkbox',
																LAY_CHECKED : false
															},
															{
																field : 'PName',
																title : '姓名',
																width : '10%',
																templet : function(
																		d) {
																	if (d.PName)
																		return d.PName;
																	else
																		return '外来人员';
																}
															},
															{
																field : 'person',
																title : '出生日期',
																width : '5%',
																templet : function(
																		d) {
																	if (d.person.PBirth)
																		return dateFormat(
																				d.person.PBirth,
																				'yMd');
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'person',
																title : '手机号',
																width : '5%',
																templet : function(
																		d) {
																	if (d.person.PPhone)
																		return d.person.PPhone;
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'person',
																title : '身份证号',
																width : '15%',
																templet : function(
																		d) {
																	if (d.person.idCardNo)
																		return d.person.idCardNo;
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'person',
																title : '健康状态',
																width : '15%',
																templet : function(
																		d) {
																	if (d.isAlarm == 0)
																		return '<font color=\"green\">健康</font>';
																	else if (d.isAlarm == 1)
																		return '<font color=\"red\">不健康</font>';
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'temperature',
																title : '体温',
																width : '10%',
																templet : function(
																		d) {
																	let temperature = Math
																			.round(d.temperature * 10)
																			/ 10
																			+ "℃";
																	if (d.isAlarm == 0)
																		return '<font color=\"green\">'
																				+ temperature
																				+ '</font>';
																	else if (d.isAlarm == 1)
																		return '<font color=\"red\">'
																				+ temperature
																				+ '</font>';
																}
															},
															{
																field : 'createTime',
																title : '检测时间',
																width : '15%',
																templet : function(
																		d) {
																	if (d.createTime == "") {
																		return '<a style="color:#ed2a4a" >--</a>'
																	} else {
																		return dateFormat(
																				d.createTime,
																				'yMdhms');
																	}
																}
															} ] ],
													skin : 'line', //line row表格风格
													even : true, //,size: 'lg' //尺寸
													page : true, //是否显示分页,
													limits : [ 3, 5 ],
													limit : 3
												});
										//查询处理方法
										var $ = layui.jquery, active = {
											//刷新表格数据重新请求
											reload : function() {
												debugger
												//执行重载
												table.reload('alarmList', {
													page : {
														curr : 1
													},
													//查询条件参数
													where : {
														userName : $("#PName")
																.val(),
														startTime : $(
																"#startMkTime")
																.val(),
														endTime : $(
																"#endMkTime")
																.val()
													}
												});
											},
										};
										$('.layui-btn').on(
												'click',
												function() {
													var type = $(this).data(
															'type');
													active[type] ? active[type]
															.call(this) : '';
												});

									})
				</script>
			</div>




			<!-- 口罩佩戴报警 -->
			<div class="layui-tab-item">
				<div class="serch-bar search-header">
					<div class="search-input">
						姓名：
						<div class="layui-inline layui-search-input">
							<input name="userName" class="layui-input" id="userName"
								autocomplete="off">
						</div>
						最近监测时间：
						<div class="layui-inline layui-search-date">
							<input class="layui-input" id="startTime" name="startTime"
								type="text" placeholder="开始时间" autocomplete="off">
						</div>
						-
						<div class="layui-inline layui-search-date">
							<input class="layui-input" id="endTime" name="endTime"
								type="text" placeholder="结束时间" autocomplete="off">
						</div>
						<button class="layui-btn layui-btn-orange layui-btn-search"
							data-type="reload">搜索</button>
					</div>
				</div>
				<!-- table表单 -->
				<table id="userList" lay-filter="userList"></table>
				<!-- 数据处理 -->
				<script type="text/javascript">
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
										$(window).resize(
												function() {
													resizeLayer($, layerIndex,
															layerInitWidth,
															layerInitHeight);
												});
										var areaName = decodeURI('${param.areaName }');
										//查询条件开始时间实发始化
										var endDate = laydate
												.render({
													elem : '#endtime',//选择器结束时间
													type : 'datetime',
													min : "1970-1-1",//设置min默认最小值
													done : function(value, date) {
														if (value == null
																|| value == "") {
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
														var nowStrDate = date.year
																+ "-"
																+ date.month
																+ "-"
																+ date.date
																+ " "
																+ date.hours
																+ ":"
																+ date.minutes
																+ ":"
																+ date.seconds;
														var endyear = endDate.config.dateTime.year;
														var endmonth = endDate.config.dateTime.month + 1;
														var enddate = endDate.config.dateTime.date;
														var endhours = endDate.config.dateTime.hours;
														var endminutes = endDate.config.dateTime.minutes;
														var endseconds = endDate.config.dateTime.seconds;
														var nowEndDate = endyear
																+ "-"
																+ endmonth
																+ "-"
																+ enddate
																+ " "
																+ endhours
																+ ":"
																+ endminutes
																+ ":"
																+ endseconds;
														nowEndDate = new Date(
																nowEndDate);
														nowStrDate = new Date(
																nowStrDate);
														if (nowEndDate < nowStrDate) {
															layer
																	.msg('选择的结束时间小于开始时间了');
														}
													}
												});
										//表格数据初始化
										table
												.render({
													elem : '#userList',
													//height : 'full-120',
													url : '${path}/maskRecord/getRecord.do?code=${param.code}',
													//cellMinWidth : 80,
													cols : [ [ //标题栏
															{
																type : 'checkbox',
																LAY_CHECKED : false
															},
															{
																field : 'PName',
																title : '姓名',
																width : "15%",
																templet : function(
																		d) {
																	if (d.PName)
																		return d.PName;
																	else
																		return '外来人员';
																}
															},
															{
																field : 'person',
																title : '出生日期',
																width : "10%",
																templet : function(
																		d) {
																	if (d.person.PBirth)
																		return dateFormat(
																				d.person.PBirth,
																				'yMd');
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'PPhone',
																title : '手机号',
																width : "15%",
																templet : function(
																		d) {
																	if (d.person.PPhone)
																		return d.person.PPhone;
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'person',
																title : '身份证号',
																width : "22%",
																templet : function(
																		d) {
																	if (d.person.idCardNo)
																		return d.person.idCardNo;
																	else
																		return '<a style="color:#ed2a4a" >--</a>';
																}
															},
															{
																field : 'createTime',
																title : '报警时间',
																width : "20%",
																templet : function(
																		d) {
																	if (d.createTime == "") {
																		return '<a style="color:#ed2a4a" >--</a>'
																	} else {
																		return dateFormat(
																				d.createTime,
																				'yMdhms');
																	}
																}

															} ] ],
													skin : 'line', //line row表格风格
													even : true, //,size: 'lg' //尺寸
													page : true, //是否显示分页,
													limits : [ 3, 5 ],
													limit : 3
												});
										//查询处理方法
										var $ = layui.jquery, active = {
											//刷新表格数据重新请求
											reload : function() {
												debugger
												//执行重载
												table.reload('userList', {
													page : {
														curr : 1
													},
													//查询条件参数
													where : {
														userName : $(
																"#userName")
																.val(),
														startTime : $(
																"#startTime")
																.val(),
														endTime : $("#endTime")
																.val()
													}
												});
											},
										};
										$('.layui-btn').on(
												'click',
												function() {
													var type = $(this).data(
															'type');
													active[type] ? active[type]
															.call(this) : '';
												});
									});
				</script>
			</div>
		</div>
	</div>
</body>
</html>