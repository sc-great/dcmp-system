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
                    <input name="chName" class="layui-input" type="text" lay-verify="required" maxlength="64"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人</label>
                <div class="layui-input-inline">
                    <input name="master" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="64">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构编号</label>
                <div class="layui-input-inline">
                    <input name="chCode" class="layui-input" type="text" lay-verify="required" maxlength="100"
                           autocomplete="off" lay-verify="required" maxlength="100">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">父级机构</label>
                <div class="layui-input-inline">
                    <input id="parentOrg" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人电话</label>
                <div class="layui-input-inline">
                    <input name="masterPhone" class="layui-input" type="tel"
                           autocomplete="off" lay-verify="phone|require">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构类型</label>
                <div class="layui-input-inline">
                    <select name="chType" id="typeSelectId" lay-verify="required">
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;">
						<textarea class="layui-textarea" name="description"
                                  maxlength="1000" placeholder="请输入描述"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-submit-block">
                    <button class="layui-btn" lay-submit lay-filter="subimitForm">立即提交</button>
                    <button type="button" class="layui-btn layui-btn-primary layui-close-layer">取消</button>
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
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //表单提交
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/campusOrg/update.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
                        var key = top.layer.alert("修改成功！", {icon: 1, closeBtn: 0},
                            function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.layer.close(key);
                            });
                    } else {
                        top.layer.alert("修改失败！");
                    }
                },
                error: function (data) {
                    var key = top.layer.alert("服务器异常！",
                        function () {
                            location.reload();
                            parent.layer.close(key);
                        });
                }
            })
            return false;
        });
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
                var url = '${path}/dic/getDicValue.do?dicCode=orgType';
                selectUtil($, url, "typeSelectId", data.job.chType, function () {
                    form.render();
                });

            } else {
                top.layer.alert('获取机构数据失败！！');
                return;
            }
        });

        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
</script>
</body>
</html>