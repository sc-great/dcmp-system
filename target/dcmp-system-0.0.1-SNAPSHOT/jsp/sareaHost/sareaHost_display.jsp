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
<form class="layui-form  layui-from-header" lay-filter="areaForm">
    
     <div class="layui-row">
        <div class="layui-col-md12">

            <div class="layui-form-item">
                <label class="layui-form-label">主机名称</label>
                <div class="layui-input-block">
                    <input name="name" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="50" disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">主机编号</label>
                <div class="layui-input-block">
                    <input name="code" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="50"  disabled="disabled" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-block">
                    <input name="status" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required" id="status">
                </div>
            </div>
           <div class="layui-form-item">
                <label class="layui-form-label">主机IP</label>
                <div class="layui-input-block">
                    <input name="ipAddr" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required|ip" maxlength="16" disabled="disabled">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">端口</label>
                <div class="layui-input-block">
                    <input name="port" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required|number" maxlength="50" disabled="disabled">
                </div>
            </div>
             
            <div class="layui-form-item">
                <div class="layui-submit-block">
                   
                    <button type="button" class="layui-btn layui-btn-primary layui-close-layer">取消</button>
                </div>
            </div>
          
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['laydate', 'layer', 'form', 'jquery'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.jquery;
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //表单初始赋值，通用编号获取用户数据初始化表单
        $.post("${path}/sareaHost/getById.do", {id: "${param.hostId}"}, function (data) {
            if (data.success) {
                form.val('areaForm', {
                    "name": data.job.name,
                    "status": data.job.status == 0 ? "启用" : "停用" ,    
                    "code": data.job.code, 
                    "ipAddr": data.job.ipAddr,
                    "port": data.job.port,                                   
                });
                           
                
            } else {
                top.layer.alert('获取数据失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>