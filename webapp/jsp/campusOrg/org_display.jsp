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
<form class="layui-form layui-from-header" lay-filter="orgForm">
    <input type="hidden" name="chId" value="${param.chId }"/>
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">机构名称</label>
                <div class="layui-input-inline">
                    <input name="chName" class="layui-input" type="text"
                           disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人</label>
                <div class="layui-input-inline">
                    <input name="master" class="layui-input" type="text"
                           disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构编号</label>
                <div class="layui-input-inline">
                    <input name="chCode" class="layui-input" type="text"
                           disabled="disabled">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">父级机构</label>
                <div class="layui-input-inline">
                    <input id="parentOrg" class="layui-input" type="text" disabled="disabled"
                           disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人电话</label>
                <div class="layui-input-inline">
                    <input name="masterPhone" class="layui-input" type="tel"
                           disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构类型</label>
                <div class="layui-input-inline">
                    <input name="chType" id="chType" class="layui-input" disabled="disabled">
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;">
						<textarea class="layui-textarea" name="description"
                                  disabled="disabled" placeholder="请输入描述"></textarea>
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
        //表单初始赋值，通过编号获取组织机构数据初始化表单
        $.post("${path}/campusOrg/getById.do", {id: "${param.chId}"}, function (data) {
            if (data.success) {
                console.log("data:" + data);
                form.val('orgForm', {
                    "chName": data.job.chName,
                    "master": data.job.master,
                    "masterPhone": data.job.masterPhone,
                    "chCode": data.job.chCode,
                });
                var chName = data.job.parentOrg == "" ? "无" : data.job.parentOrg.chName;
                $("#parentOrg").val(chName);
                $.ajax({
                    type: "post",
                    url: "${path}/dic/getDicValueByValue.do",
                    data: "dvValue=" + data.job.chType,
                    async: false,
                    success: function (data) {
                        $("#chType").val(data.dvName);
                    }
                });

            } else {
                top.layer.alert('获取机构数据失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>