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
<form class="layui-form  layui-from-header" lay-filter="personneForm">
    <input type="hidden" name="clientId" value="${param.clientId }"/>
    <!-- <input type="hidden" id="PPic" name="PPic" value=""> -->
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input name="name" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">编号</label>
                <div class="layui-input-inline">
                    <input name="code" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" maxlength="32" lay-verify="required">
                </div>
            </div>

           
        </div>
        <div class="layui-col-md6">
        <div class="layui-form-item">
                <label class="layui-form-label">最后登录时间</label>
                <div class="layui-input-inline">
                    <input name="lastDate" id="lastDate" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off">
                </div> 
            </div>
                      <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                 <select name="status" id="status" lay-verify="required" disabled="disabled">
                        <option value="0" selected="selected">启用</option>
                        <option value="1" >停用</option>
                        <select>
                </div>
            </div>
            
             
             
        </div>
    </div>


</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'upload', 'jquery'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var $ = layui.jquery;
        //表单初始赋值，通过编号获取人员数据初始化表单
        $.post("${path}/clientInfo/getClientValueById.do", {clientId: "${param.clientId}"}, function (data) {
            if (data.success) {
                form.val('personneForm', {
                    "name": data.data.name,
                    "code": data.data.code,
                    "status": data.data.status,
                    "lastDate":dateFormat(
                    		data.data.lastDate,
							'yMdhms')
                });
            } else {
                top.layer.alert('详细信息初始化失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>