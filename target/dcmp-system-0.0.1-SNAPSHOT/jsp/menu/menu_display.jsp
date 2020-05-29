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
        <input name="menuName" class="layui-input" type="text" disabled="disabled"
        autocomplete="off" lay-verify="required">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">排序序号</label>
        <div class="layui-input-inline">
        <input name="menuOrder" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required|number">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">创建人</label>
        <div class="layui-input-inline">
        <input name="createBy" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required|number">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">更新人</label>
        <div class="layui-input-inline">
        <input name="updateBy" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required|number">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单图标</label>
        <div class="layui-input-inline">
        <input name="menuIcon" class="layui-input" type="text" disabled="disabled"
        autocomplete="off">
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
        <div class="layui-col-md6">
        <div class="layui-form-item">
        <label class="layui-form-label">菜单类型</label>
        <div class="layui-input-inline">
        <select name="menuType" id="selectId" lay-verify="required" disabled="disabled">
        </select>
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">菜单路径</label>
        <div class="layui-input-inline">
        <input name="menuUrl" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">创建时间</label>
        <div class="layui-input-inline">
        <input name="createTime" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">更新时间</label>
        <div class="layui-input-inline">
        <input name="updateTime" class="layui-input" type="tel" disabled="disabled"
        autocomplete="off" lay-verify="required">
        </div>
        </div>
        <div class="layui-form-item">
        <label class="layui-form-label">上级菜单</label>
        <div class="layui-input-inline">
        <select name="parentId" id="parentId" disabled="disabled">
        <option value="">无</option>
        </select>
        </div>
        </div>
        </div>
        <!-- <div class="layui-col-md12"> -->
        <!-- <div class="layui-form-item "> -->
        <!-- <label class="layui-form-label">备注</label> -->
        <!-- <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;"> -->
        <!-- <textarea class="layui-textarea" name="description" -->
        <!-- placeholder="请输入备注"></textarea> -->
        <!-- </div> -->
        <!-- </div> -->
        <!-- </div> -->
        </div>


        </form>
        <script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
        <script type="text/javascript">
        layui.use([ 'layer', 'form', 'jquery' ], function() {
        var form = layui.form;
        var $ = layui.jquery;
        //表单初始赋值，通过编号获取菜单数据初始化表单
        $.post("${path}/menu/getById.do", {
        id : "${param.menuId}"
        }, function(data) {
        console.log(data);
        if (data.success) {
        form.val('menuForm', {
        "menuName" : data.job.menuName,
        "menuOrder" : data.job.menuOrder,
        "menuUrl" : data.job.menuUrl,
        "menuIcon" : data.job.menuIcon,
        "status":data.job.status==0?"启用":"停用",
        "createBy":data.job.createBy,
        "createTime":data.job.createTime==""?"无":dateFormat(data.job.createTime,'yMdhms'),
        "updateBy":data.job.updateBy,
        "updateTime":data.job.updateTime==""?"无":dateFormat(data.job.updateTime,'yMdhms'),
        });
        var url = '${path}/dic/getDicValue.do?dicCode=menuType';
        selectUtil($, url, "selectId", data.job.menuType, function() {
        form.render();
        });
        var url = '${path}/menu/getList.do?menuId=${param.menuId }';
        selectUtil($, url, "parentId", data.job.parentMenu.menuId, function() {
        form.render();
        });
        } else {
        top.layer.alert('获取菜单数据失败！！');
        return;
        }
        });

        });
        </script>
        </body>
        </html>