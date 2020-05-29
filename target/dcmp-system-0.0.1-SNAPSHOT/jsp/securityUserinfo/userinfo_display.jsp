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
    <style type="text/css">
        .downpanel .layui-select-title span {
            line-height: 38px;
        }

        /*继承父类颜色*/
        .downpanel dl dd:hover {
            background-color: inherit;
        }

        #addEnum {
            position: relative;
            left: 10px;
            top: 5px;
            display: inline-block;
            width: 100px;
        }
    </style>
</head>
<body>
<form class="layui-form  layui-from-header" style="height: calc(100% - 27px); overflow: auto;" lay-filter="mdForm">
    <div class="layui-row ">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">人员名称</label>
                <div class="layui-input-inline">
                    <input name="userName" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required" maxlength="100">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input name="userBirth" class="layui-input" id="userBirth" disabled="disabled"
                           type="text" placeholder="yyyy-MM-dd" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input name="userSex" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required" maxlength="100">
                </div>
            </div>
            <div class="layui-form-item type00">
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号码</label>
                    <div class="layui-input-inline">
                        <input name="userIdcard" class="layui-input" type="text" lay-verify="identity"
                               disabled="disabled"
                               autocomplete="off" maxlength="100">
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'laydate', 'form', 'tree', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var laydate = layui.laydate;
        //用户生日时间格式化
        laydate.render({
            elem: '#userBirth'
        });
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });
        //表单初始赋值，通过编号获取设备类型数据初始化表单
        $.post("${path}/userinfo/getById.do", {
            bsuId: "${param.bsuId}"
        }, function (data) {
            form.val('mdForm', {
                "userName": data.job.userName,
                "userSex": data.job.userSex == "1" ? "男" : "女",
                "userBirth": dateFormat(data.job.userBirth, 'yMd'),
                "userIdcard": data.job.userIdcard
            });

        });
    });
</script>
</body>
</html>