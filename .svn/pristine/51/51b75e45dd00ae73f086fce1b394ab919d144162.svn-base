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
        content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <title></title>
        <link rel="stylesheet" type="text/css" href="${path }/css/list.css" />
        </head>
        <body>
        <form class="layui-form layui-from-header" lay-filter="menuForm">
        <input type="hidden" name="menuId" value="${param.menuId }" />
        <div class="layui-row">
        <div class="layui-col-md6">
        <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-inline">
        <input name="menuName" class="layui-input" type="text"
        autocomplete="off" lay-verify="required" maxlength="64">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">排序编号</label>
        <div class="layui-input-inline">
        <input name="menuOrder" class="layui-input" type="tel"
        autocomplete="off" lay-verify="required|lengthInt">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单类型</label>
        <div class="layui-input-inline">
        <select name="menuType" id="selectId" lay-verify="required">
        </select>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单路径</label>
        <div class="layui-input-inline">
        <input name="menuUrl" class="layui-input" type="tel"
        autocomplete="off" lay-verify="required" maxlength="500">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单编码</label>
        <div class="layui-input-inline">
        <input name="menuCode" class="layui-input" type="text"
        autocomplete="off" lay-verify="">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">上级菜单</label>
        <div class="layui-input-inline">
        <select name="parentId" id="parentId" >
        <option value="">请选择</option>
        </select>
        </div>
        </div>
        </div>
        <div class="layui-col-md6">
        <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-inline">
        <input name="menuIcon" id="menuIcon" class="layui-input" type="hidden"
        autocomplete="off" maxlength="500">
        <div class="layui-upload-drag" id="icon" style="text-align: center; height: 195px;width: 300px; padding: 0px;">
        <i class="layui-icon" style="position: relative; top: 50px;">&#xe67c;</i>
        <p style="position: relative; top: 50px;">点击上传图片，或将文件拖拽到此处</p>
        </div>
        </div>
        </div>
        </div>
        <div class="layui-col-md12">
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
        layui.use([ 'layer', 'form', 'jquery','upload' ], function() {
        var form = layui.form;
        var $ = layui.jquery;
        var upload=layui.upload;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        $('.layui-close-layer').click(function() {
        let index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        });
        //图片上传控件格式化
        upload.render({
        elem : '#icon',
        url : '${path}/attachment/addPic.do',
        multiple : true,
        size : 1024,
        exts : "jpg|png|gif|bmp|jpeg|pdf",
        done : function(res) {
        //如果上传失败
        if (res.success) {
        $("#menuIcon").val(res.job.url);
        $("#icon").empty();
        var image = $("<image width='100%' height='100%' src='" + res.job.url + "'/>");
        $("#icon").append(image);
        } else {
        layer.msg('上传失败');
        }
        }
        });
        //表单提交
        form.on('submit(subimitForm)', function(data) {
        if($("#parentId").val()=="${param.menuId }"){
        top.layer.alert("请选择非自身菜单作为父级菜单！");
        return false;
        }
        $.ajax({
        url : '${path }/menu/update.do',
        method : 'post',
        data : data.field,
        dataType : 'JSON',
        success : function(res) {
        if (res.success) {
        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
        var key=top.layer.alert("修改成功！", {
        icon : 1,
        closeBtn : 0
        },
        function() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
        parent.layer.close(key);
        });
        } else {
        top.layer.alert("修改失败！");
        }
        },
        error : function(data) {
        var key=top.layer.alert("服务器异常！",
        function() {
        location.reload();
        parent.layer.close(key);
        });
        }

        })
        return false;
        });
        //表单初始赋值，通过编号获取菜单数据初始化表单
        $.post("${path}/menu/getById.do", {
        id : "${param.menuId}"
        }, function(data) {
        if (data.success) {
        form.val('menuForm', {
        "menuName" : data.job.menuName,
        "menuOrder" : data.job.menuOrder,
        "menuUrl" : data.job.menuUrl,
        "menuCode":data.job.menuCode,
        "menuIcon" : data.job.menuIcon
        });
        var url = '${path}/dic/getDicValue.do?dicCode=menuType';
        selectUtil($, url, "selectId", data.job.menuType, function() {
        form.render();
        });
        var url = '${path}/menu/getList.do?menuId=${param.menuId }';
        selectUtil($, url, "parentId", data.job.parentMenu.menuId, function() {
        form.render();
        });

        if(data.job.menuIcon!=""){

        $("#icon").empty();
        var image = $("<image width='100%' height='100%' src='" + data.job.menuIcon + "'/>");
        $("#icon").append(image);
        }
        if(data.job.parentMenu.menuId===""||data.job.parentMenu===null||data.job.parentMenu===""){
        $("#parentId").attr("disabled", "disabled");
        }
        } else {
        top.layer.alert('获取菜单数据失败！！');
        return;
        }
        });

        });
        </script>
        </body>
        </html>