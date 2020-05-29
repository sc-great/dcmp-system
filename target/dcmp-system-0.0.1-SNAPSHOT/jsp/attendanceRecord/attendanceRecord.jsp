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
<link rel="stylesheet" type="text/css" href="${path}/css/list.css" />
<link rel="stylesheet" type="text/css" href="${path}/css/admin.css" />
<style>
.layui-table-body:hover {
	overflow-x: auto;
}

.layui-table-cell {
	height: auto;
	line-height: 30px;
}
</style>
</head>
<body>
	<div class="serch-bar search-header">
		<div class="search-input" style="width: 100%;">
			姓名：
			<div class="layui-inline layui-search-input">
				<input name="userName" class="layui-input" id="userName"
					autocomplete="off">
			</div>
			查询时间：
			<div class="layui-inline layui-search-date">
				<input class="layui-input" id="startTime" name="startTime"
					type="text" placeholder="开始时间" autocomplete="off">
			</div>
			&nbsp;-&nbsp;
			<div class="layui-inline layui-search-date"
				style="margin-right: 30px;">
				<input class="layui-input" id="endTime" name="endTime" type="text"
					placeholder="结束时间" autocomplete="off">
			</div>
			部门：
			<div class="layui-inline layui-search-date">
				<input type="text" id="orgId" lay-filter="tree" class="layui-input">
			</div>
			<button class="layui-btn layui-btn-orange layui-btn-search"
				data-type="reload">查询</button>
			<button class="layui-btn layui-btn-orange layui-btn-search"
				data-type="exports" id="export">导出</button>
		</div>
	</div>
	<table class="layui-table" id="personList" style="width: 100%;"></table>
	<script src="${path}/plugin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript" src="${path}/js/chart/jquery-1.9.1.js"></script>
	<script>
		layui.config({
			base : '${path }/plugin/layui2.4/lay/modules/' // js地址 
		}).extend({
			treeSelect : 'treeSelect'
		});
		layui.use([ 'table', 'laydate', 'form', 'treeSelect', 'jquery' ], function() {
			var table = layui.table;
			var form = layui.form;
			var $ = layui.jquery;
			var layerIndex;
			var layerInitWidth;
			var layerInitHeight;
			var currpage = $('.layui-laypage-skip').children('.layui-input').val();
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
	
					$("#statrDate").val(date.year + "-" + date.month + "-" + date.date);
				}
			});
	
			// 日期范围
			var endDate = laydate.render({
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
					var nowStrDate = date.year + "-" + date.month + "-" + date.date + " " + date.hours + ":" + date.minutes + ":" + date.seconds;
					var endyear = endDate.config.dateTime.year;
					var endmonth = endDate.config.dateTime.month + 1;
					var enddate = endDate.config.dateTime.date;
					var endhours = endDate.config.dateTime.hours;
					var endminutes = endDate.config.dateTime.minutes;
					var endseconds = endDate.config.dateTime.seconds;
					var nowEndDate = endyear + "-" + endmonth + "-" + enddate + " " + endhours + ":" + endminutes + ":" + endseconds;
					nowEndDate = new Date(nowEndDate);
					nowStrDate = new Date(nowStrDate);
					if (nowEndDate < nowStrDate) {
						layer.msg('选择的结束时间小于开始时间了');
						return;
					}
					$("#endDate").val(endyear + "-" + endmonth + "-" + enddate);
				}
			});
			$(window).resize(function() {
				resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
			});
	
			var creatTable = function() {
				var end_time = $("#endTime").val();
				end_time = end_time == "" || end_time == undefined ? new Date() : end_time;
				var start_time = $("#startTime").val();
				var arrcols = [ {
					'field' : "index",
					'title' : "序号"
				}, {
					'field' : "name",
					'title' : "姓名"
				} ];
				var begin = new Date(start_time),
					end = new Date(end_time);
				var begin_time = begin.getTime(),
					end_time = end.getTime(),
					time_diff = end_time - begin_time;
				for (var i = 0; i <= time_diff; i += 86400000) {
					var ds = new Date(begin_time + i);
					var m = ds.getMonth() + 1;
					m = m > 10 ? m : ("0" + m)
					var d = ds.getDate();
					d = d > 10 ? d : ("0" + d);
					arrcols.push({
						'field' : ds.getFullYear() + "-" + m + "-" + d,
						'title' : ds.getFullYear() + "-" + m + "-" + d
					});
				}
				//表格数据初始化
				table.render({
					elem : '#personList',
					height : 'full-120',
					url : '${path}/api/getRecordToTable',
					where : {
						"userName" : $("#userName").val(),
						"startTime" : $("#startTime").val(),
						"endTime" : $("#endTime").val(),
						"org" : $("#orgId").val()
					},
					cellMinWidth : 150,
					cols : [ arrcols ],
					skin : 'line', //line row表格风格
					even : true, //,size: 'lg' //尺寸
					page : false, //是否显示分页,
					limits : [ 10, 15 ],
					limit : 10
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
				
				window.location.href = "${path}/api/exportRecordToExcel?userName=" + userName + "&startTime=" + startTime + "&endTime=" + endTime + "&org=" + org;
			}
	
//			creatTable();
			var $ = layui.jquery,
				active = {
					// 刷新表格数据重新请求
					reload : creatTable
				};
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
				success : function(d) {}
			});
			var $ = layui.jquery,
				active = {
					// 机构信息添加
					attExport : function() {
						top.layer.open({
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
					// 刷新表格数据重新请求
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