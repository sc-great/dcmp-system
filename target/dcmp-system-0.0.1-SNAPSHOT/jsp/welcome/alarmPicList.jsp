<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common_taglib.jsp"%>
<%@ include file="/common/common_css.jsp"%>
<%@ include file="/common/common_js.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style type="text/css">
p {
	font-size: 14px;
	height: 25px;
}
</style>
<script type="text/javascript" src="${path }/js/chart/jquery-1.9.1.js"></script>
</head>
<body>
	<div id="container" style="width: 100%;height: 100%;"></div>
</body>
<script type="text/javascript">
	$(function() {
		loadData();
	})
	setInterval("loadData()", 30000);
	function loadData() {
		var url = "${path}/api/report/report4";
		$.post(url, function(data) {
			if (data.data.length > 0) {
			$("#container").html("");
				var dataList = data.data;
				for (var i=0;i< dataList.length;i++) {
					var div = "<div class=\"layui-col-md3\" ><img alt=\"\" src=\"" + dataList[i].image + "\" width=\"98%\" height=\"85%\"><div>"
						+ "<div style='margin-top: 10px'><p>姓名：" + dataList[i].pName + "</p>"
						+ "<p style='color: red;'>" + (dataList[i].type == "tem" ? ("状态：发热（体温：" + (Math.round(dataList[i].temperature * 10) / 10) + "℃）") : "状态：未正确佩戴口罩") + "</p>"
						+ "<p>检测通道：" + dataList[i].client + "</p>"
						+ "<p>检测时间：" + dataList[i].time + "</p>"
						+ "</div>";
					$("#container").append(div);
				}
			}

			$("#container div").height($("#container div").width());
		}, "json");
	}
</script>
</html>
