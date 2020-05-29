<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${path }/css/admin.css"/>
</head>
<body>
<form class="layui-form" v style="width: 90%;padding-top: 20px;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-block">
            <input type="text" id="username" name="username" disabled autocomplete="off"
                   class="layui-input layui-disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">旧密码：</label>
        <div class="layui-input-block">
            <input id="oldPass" type="password" name="password1" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码：</label>
        <div class="layui-input-block">
            <input type="password" name="password2" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">重复密码：</label>
        <div class="layui-input-block">
            <input type="password" name="password3" required lay-verify="required" placeholder="请输入密码"
                   autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="adminPassword">立即提交</button>
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    //Demo
    layui.use(['form', 'element', 'jquery'], function () {
        var form = layui.form;
        var element = layui.element;
        var $ = layui.jquery;
        form.render();
        $.ajax({
            url: '${path }/user/getLoginUsers.do',
            method: 'post',
            dataType: 'JSON',
            success: function (data) {
                $('#username').val(data.job.loginName);
            },
            error: function (data) {
                top.layer.alert("服务器异常！");
            }
        });
        //监听信息提交
        /*  form.on('submit(adminInfo)', function (data) {
             layer.msg(JSON.stringify(data.field));
             return false;
         }); */
        //监听密码提交
        form.on('submit(adminPassword)', function (data) {
            $.ajax({
                url: '${path }/user/changePassword.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        top.layer.alert("修改成功！");
                        top.layer.close(index);
                        top.layer.close(key);
                    } else {
                        top.layer.alert(res.msg);

                    }
                },
                error: function (data) {
                    top.layer.alert("服务器异常！");
                }
            });
            return false;
        });
    });
</script>
</body>
</html>