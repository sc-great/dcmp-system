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
</head>
<body>
	<div id="imageBox" style="float:left;margin:20px;"></div>
	<div id="imageInfo" style="float:right;margin:20px;width:400px;line-height:40px"></div>
	<script src="${path }/plugin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		layui.use([ 'laydate', 'layer', 'form', 'jquery' ], function() {
			var form = layui.form;
			var $ = layui.jquery;

		});

	</script>
</body>
</html>