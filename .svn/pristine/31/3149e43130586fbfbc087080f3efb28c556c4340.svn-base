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
<form class="layui-form  layui-from-header" lay-filter="dicForm">
    <input type="hidden" value="${param.dicId}" name="dicId"/>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">数据项名称</label>
                <div class="layui-input-block">
                    <input name="dicName" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="64">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数据项编码</label>
                <div class="layui-input-block">
                    <input name="dicCode" class="layui-input"
                           autocomplete="off" lay-verify="required" maxlength="16">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序编号</label>
                <div class="layui-input-block">
                    <input name="dicOrder" class="layui-input"
                           autocomplete="off" lay-verify="required" maxlength="16">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">数据项说明</label>
                <div class="layui-input-block">
								<textarea class="layui-textarea" name="dicRemark" maxlength="1000"
                                          placeholder="请输入备注"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-submit-block">
                    <button class="layui-btn" lay-submit lay-filter="subimitForm">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
        //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
            $.ajax({
                url: '${path }/dic/updateDic.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        var key = top.layer.alert("修改成功！", {
                                icon: 1,
                                closeBtn: 0
                            },
                            function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.layer.close(key);
                            });
                    } else {
                        // layer.alert("修改失败！");
                        top.layer.alert(res.msg);
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
        $.post("${path}/dic/getDicById.do", {id: "${param.dicId}"}, function (data) {
            if (data.success) {
                form.val('dicForm', {
                    "dicName": data.job.dicName,
                    "dicCode": data.job.dicCode,
                    "dicOrder": data.job.dicOrder,
                    "dicRemark": data.job.dicRemark
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