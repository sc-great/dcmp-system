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
.layui-toolbar {
	width: calc(40% - 55px);
	display: flex;
	justify-content: flex-end;
}
</style>
</head>
<body>
<div class="serch-bar search-header">
	<div class="search-input" style="width:100%;">
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
		-
		<div class="layui-inline layui-search-date" style="margin-right:30px;">
			<input class="layui-input" id="endTime" name="endTime" type="text"
				placeholder="结束时间" autocomplete="off">
		</div>
		部门：
		<div class="layui-inline layui-search-date">
			<select class="layui-input" id="org">
				<option value="">部门一</option>
				<option>部门二</option>
				<option>部门三</option>
				<option>部门四</option>
			</select>
		</div>
		<button class="layui-btn layui-btn-orange layui-btn-search"
			data-type="reload">查询</button>
	</div>
</div>
<table class="layui-table" id="personList" style="width:100%;"></table>
<script src="${path}/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
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

	//日期范围
	var endDate = laydate.render({
		elem : '#endtime',
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


	var creatTable = function(start_time, end_time) {
		var end_time = $("#endTime").val();
		var start_time = $("#startTime").val();
		var arrcols = [{
			'type' : 'checkbox',
			'LAY_CHECKED' : false
		}, {
			'field' : 'name',
			'title' : "姓名"
		}];
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
		console.log(arrcols);
		//表格数据初始化
		table.render({
			elem : '#personList',
			height : 'full-120',
			url : '${path}/api/getRecordToTable',
			where:{"startTime":$("#startTime").val(),"endTime":$("#endTime").val()},
			cellMinWidth : 80,
			cols : [ arrcols ],
			skin : 'line', //line row表格风格
			even : true, //,size: 'lg' //尺寸
			page : true, //是否显示分页,
			limits : [ 10, 15 ],
			limit : 10
		});
	}

//	creatTable();
	var $ = layui.jquery,
		active = {
			//刷新表格数据重新请求
			reload : creatTable
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
<script type="text/javascript">

$(function() {
	$.post("${path}/campusOrg/getOrgList", function (data) {
		if (data == undefined || data.length == 0)
			return;
		$("#org").html("");
		var option = "";
		for (var i = 0; i < data.length; i ++) {
			option = "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
			$("#org").append(option);
		}
	}, "json");
})
/*
$(".layui-btn-search").click(function() {
	var startTime = $("#startTime").val();
	if (startTime == undefined || startTime == "") {
		alert("请添加查询条件！");
		return;
	}
	var userName = $("#userName").val();
	var endTime = $("#endTime").val();
	var org = $("#org").val();
	$.post("${path}/api/getRecord", {
		"userName" : userName,
		"startTime" : startTime,
		"endTime" : endTime,
		"org" : org
	}, function (data) {
		if (data.code != 200) {
			alert("大佬，您请求失败了哦！");
			return;
		}
		var personList = data.data.personList;
		var dateList = data.data.attendanceList.jsonDate;
		var attendanceList = data.data.attendanceList.jsonObject;
//		console.log(attendanceList);
		$("#personList").html("");
		var num = dateList.length;
		var th = "<td>序号</td><td>姓名</td>";
		for (var i = 0; i < num; i ++) {
			th += ("<td>" + dateList[i] + "</td>");
		}
		$("#personList").append(th);
		var tr = "";
		var td = "";
 		for (var i = 0; i < personList.length; i ++) {
			tr = "<tr id=\"tr_" + personList[i].PId + "\"><td>" + (i + 1) + "</td><td>" + personList[i].PName + "</td>";
			for (var m = 0; m < num; m ++) {
				tr += "</td>--<td>";
			}
			tr += "</tr>";
			$("#personList").append(tr);
		}
		for (var m = 0; m < attendanceList.length; m ++) {
			$("#tr_" + attendanceList[m][0]).html("");
			td = "<td>" + attendanceList[m][0] + "</td><td>" + attendanceList[m][1] + "</td>";
			
  			for (var n = 2; n < attendanceList[m].length; n = n + 2) {
 				var max = attendanceList[m][n];
				var min = attendanceList[m][n + 1];
				if (max == "") {
					max = "--";
				} else {
					max = dateFormat(max, "hms");
				}
				if (min == "") {
					min = max;
					max = "--";
				} else {
					min = dateFormat(min, "hms");
				}
				td = td + "<td>" + min + "<br>" + max + "</td>";
			}
			$("#tr_" + attendanceList[m][0]).append(td);
		}
	}, "json");
});
*/
/**
* 根据两个日期，判断相差天数
* @param sDate1 开始日期 如：2016-11-01
* @param sDate2 结束日期 如：2016-11-02
* @returns {number} 返回相差天数
*/
function daysBetween(sDate1, sDate2) {
	//Date.parse() 解析一个日期时间字符串，并返回1970/1/1 午夜距离该日期时间的毫秒数
	var time1 = Date.parse(sDate1);
	var time2 = Date.parse(sDate2);
	var nDays = Math.abs(parseInt((time2 - time1) / 1000/ 3600 / 24));
	return nDays;
};
</script>
<script type="text/javascript">
var selectAreaId;
var selectAreaName;
layui.use([ 'table', 'laydate', 'form', 'jquery' ], function() {
	var table = layui.table;
	var laydate = layui.laydate;
	var $ = layui.jquery;
	var layerIndex;
	var layerInitWidth;
	var layerInitHeight;
	$(window).resize(function() {
		resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
	});
	var areaName = decodeURI('${param.areaName }');
	// 查询条件开始时间实发始化
	var endDate = laydate.render({
		elem : '#endtime', // 选择器结束时间
		type : 'datetime',
		min : "1970-1-1", // 设置min默认最小值
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
					month : date.month - 1, // 关键
					date : date.date,
					hours : date.hours,
					minutes : date.minutes,
					seconds : date.seconds - 1
				}
			}
		}
	});
	// 日期范围
	var startDate = laydate.render({
		elem : '#startTime',
		type : 'datetime',
		max : "2099-12-31", // 设置一个默认最大值
		done : function(value, date) {
			endDate.config.min = {
				year : date.year,
				month : date.month - 1, // 关键
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
});
</script>
</body>
</html>