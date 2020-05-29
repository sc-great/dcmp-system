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
<form class="layui-form  layui-from-header" lay-filter="roleForm">
    <input type="hidden" name="roleId" value="${param.roleId }"/>
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">角色名</label>
                <div class="layui-input-inline">
                    <input name="roleName" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建人</label>
                <div class="layui-input-inline">
                    <input name="createBy" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required" id="createTime">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新人</label>
                <div class="layui-input-inline">
                    <input name="updateBy" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required" id="createTime">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-inline">
                    <input name="createTime" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新时间</label>
                <div class="layui-input-inline">
                    <input name="updateTime" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input name="status" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;">
						<textarea class="layui-textarea" name="description"
                                  placeholder="请输入描述"></textarea>
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

        //表单初始赋值，通用编号获取用户数据初始化表单
        $.post("${path}/role/getById.do", {id: "${param.roleId}"}, function (data) {
            if (data.success) {
                form.val('roleForm', {
                    "roleName": data.job.roleName,
                    "description": data.job.description,
                    "createBy": data.job.createBy,
                    "createTime": dateFormat(data.job.createTime, 'yMdhms'),
                    "updateBy": data.job.updateBy,
                    "updateTime": dateFormat(data.job.updateTime, 'yMdhms'),
                    "status": data.job.status == 0 ? "启用" : "停用"
                });
            } else {
                top.layer.alert('获取用户数据失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>