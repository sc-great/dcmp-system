<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="text/javascript" src="${path }/js/chart/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${path }/js/chart/echarts.min.js"></script>
    <script type="text/javascript" src="${path }/js/chart/myCharts.js"></script>
</head>
<body>
<div id="container" style="width: 100%;height: 100%"></div>
</body>
<script type="text/javascript">
$(function() {
	loadData();
})
setInterval("loadData()", 30000); 
function loadData() {
    var colorIndex = 4;
    var time = 5000;
    var color = ['#7FFF00', '#C4C4C4', '#8B1C62', '#8B6914', '#708090', '#8B1A1A', '#5F9EA0', '#CD5B45', '#CDCD00', '#EE6AA7', '#548B54', '#8B4726'];
    myCharts.initOption("", true);
    myCharts.creatTitle('当日口罩报警分析', 'center', 15, '#008B00');
    myCharts.creattTooltip("axis", "");
    var url = "${path}/api/report/report3";
    var param = {};
	$.post(url, param, function (data, textStatus, jqXHR) {
		var t_monday = data.data.week.temp.monday;
		var t_tuesday = data.data.week.temp.tuesday;
		var t_wednesday = data.data.week.temp.wednesday;
		var t_thursday = data.data.week.temp.thursday;
		var t_friday = data.data.week.temp.friday;
		var t_saturday = data.data.week.temp.saturday;
		var t_sunday = data.data.week.temp.sunday;
		myCharts.CreatSeries("体温报警", "line",'#EE4000', false, true, "top",
            [t_monday, t_tuesday, t_wednesday, t_thursday, t_friday, t_saturday, t_sunday]);
		var m_monday = data.data.week.mask.monday;
		var m_tuesday = data.data.week.mask.tuesday;
		var m_wednesday = data.data.week.mask.wednesday;
		var m_thursday = data.data.week.mask.thursday;
		var m_friday = data.data.week.mask.friday;
		var m_saturday = data.data.week.mask.saturday;
		var m_sunday = data.data.week.mask.sunday;
        myCharts.CreatSeries("口罩报警", "line", '#EEEE00', false, true, "top",
        	[m_monday, m_tuesday, m_wednesday, m_thursday, m_friday, m_saturday, m_sunday]);
        myCharts.creatLegend("#000", 'horizontal', "center", "bottom",
            ["体温报警","口罩报警"]);
        myCharts.creatYAxis("value", true, "#000", null, "次");
        myCharts.creatXAxis("category", false, "#000",["周一","周二","周三","周四","周五","周六","周日"]);
        myCharts.build("container");
//        myCharts.autoShowTip("", time);
	}, "json");
}
</script>
</html>
