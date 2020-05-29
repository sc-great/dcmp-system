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
    myCharts.creatTitle('当日检测分析', 'center', 15, '#008B00');
    myCharts.creattTooltip("axis", "");
    var url = "${path}/api/report/report3";
    var param = {};
	$.post(url, param, function (data, textStatus, jqXHR) {
		var org = data.data.org;
		var orgList = new Array();
		var tempList = new Array();
		var maskList = new Array();
		for (var i = 0; i < org.length; i ++) {
			orgList[i] = org[i].name;
			tempList[i] = org[i].tempAlarm;
			maskList[i] = org[i].maskAlarm;
		}
        colorIndex = colorIndex == color.length ? 0 : colorIndex;
        myCharts.CreatSeries("体温报警", "bar",'#EE4000', false, true, "top", tempList);
        myCharts.CreatSeries("口罩报警", "bar", '#EEEE00', false, true, "top", maskList);
        myCharts.creatLegend("#000", 'horizontal', "center", "bottom",
            ["体温报警","口罩报警"]);
        myCharts.creatYAxis("value", true, "#000", null, "人");
        myCharts.creatXAxis("category", false, "#000", orgList);
        myCharts.build("container");
//        myCharts.autoShowTip("", time);
	}, "json");
}
</script>
</html>
