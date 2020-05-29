<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE html>
<html class="iframe-h">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${path }/css/list.css"/>
</head>
<body>
<form class="layui-form  layui-from-header" lay-filter="logForm">
    <input type="hidden" name="logId" value="${param.logId }"/>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">日志类型</label>
                <div class="layui-input-block">
                    <input name="logType" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" disabled="">
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">日志内容</label>
                <div class="layui-input-block">
						<textarea class="layui-textarea" name="logMsg"
                                  placeholder="请输入日志内容" disabled=""></textarea>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;

        //表单初始赋值，通过编号获取日志数据初始化表单
        $.post("${path}/log/getById.do", {id: "${param.logId}"}, function (data) {
            if (data.success) {
                var logType;
                if (data.job.logType == "login") {
                    logType = "登陆日志";
                } else if (data.job.logType == "do") {
                    logType = "操作日志";
                } else if (data.job.logType == "db") {
                    logType = "数据日志";
                } else if (data.job.logType == "WeChat") {
                    logType = "微信日志";
                } else {
                    logType = "其他日志";
                }
                form.val('logForm', {
                    "logType": logType,
                    "logMsg": data.job.logMsg,
                });
            } else {
                top.layer.alert('获取日志数据失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>