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
        <div class="serch-bar">
        <!-- <span class="layui-toolbar"> -->
        <button class="layui-btn layui-btn-primary layui-btn-tool-add layui-btn-tool-addNEW" data-type="add"><div
        class="layui-btn-toolbar-add"></div>添加下级</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="update"><div
        class="layui-btn-toolbar-add"></div>编辑</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type=Normal><div
        class="layui-btn-toolbar-up"></div>启用</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="Unuser"><div
        class="layui-btn-toolbar-stop"></div>停用</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
        class="layui-btn-toolbar-del"></div>删除</button>
        <!-- </span> -->
        </div>
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
        autocomplete="off" lay-verify="">
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
        <!-- <textmenu class="layui-textmenu" name="description" -->
        <!-- placeholder="请输入备注"></textmenu> -->
        <!-- </div> -->
        <!-- </div> -->
        <!-- </div> -->
        </div>

        </form>

        <!-- 创建时间数据格式化 -->
        <script type="text/html" id="createTime">
        {{ dateFormat(d.createTime,'yMdhms') }}
        </script>


        <script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
        <script type="text/javascript">
        var selectmenuId;
        layui.use([ 'table', 'laydate', 'form', 'jquery' ], function() {
        var table = layui.table;
        var form = layui.form;
        var layerIndex;
        var layerInitWidth;
        var layerInitHeight;
        var $ = layui.jquery;
        var currpage = $('.layui-laypage-skip').children('.layui-input').val();
        var laydate = layui.laydate;
        $(".serch-bar .layui-btn-tool-addNEW").css('display', "${param.addNew}");
        $(window).resize(function() {
        resizeLayer(layerIndex,layerInitWidth,layerInitHeight);
        });
        var refreshForm=function(){
        console.log("refresh");
        //表单初始赋值，通过编号获取菜单数据初始化表单
        $.post("${path}/menu/getById.do", {id:"${param.menuId}"}, function(data) {
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
        }
        refreshForm();
        var $ = layui.jquery,
        active = {
        //菜单信息添加
        add : function() {
        top.layer.open({
        type : 2,
        title : '菜单信息添加',
        area : [ '1000px', '600px' ],
        fixed : false, //不固定
        maxmin : false,
        shade: [0.3],
        anim: 5,
        isOutAnim: true,
        resize: false,
        content : '${path}/jsp/menu/menu_add.jsp?parentmenuId='+'${param.menuId}'+'&parentmenuName='+'${param.menuName}',
        success: function(layero, index) {
        $(':focus').blur();
        layerIndex = index;
        layerInitWidth = $("#layui-layer"+layerIndex).width();
        layerInitHeight = $("#layui-layer"+layerIndex).height();
        resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
        },
        end : function() {
        if(typeof(selectmenuId)!="undefined"){
        parent.creatMenu(selectmenuId);
        }
        refreshForm();
        }
        });
        },
        //菜单信息修改
        update : function() {
        top.layer.open({
        type : 2,
        title : '菜单信息修改',
        area : [ '1000px', '600px' ],
        fixed : false, //不固定
        maxmin : false,
        shade: [0.3],
        anim: 5,
        isOutAnim: true,
        resize: false,
        content : '${path}/jsp/menu/menu_update.jsp?menuId=' + '${param.menuId}',
        success: function(layero, index) {
        layerIndex = index;
        layerInitWidth = $("#layui-layer"+layerIndex).width();
        layerInitHeight = $("#layui-layer"+layerIndex).height();
        resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
        },
        end : function() {
        refreshForm();
        }
        });
        },
        //菜单信息批量删除
        delALL : function() {
        var ids = [];
        ids.push('${param.menuId}');
        top.layer.confirm('确定要删除此菜单吗？', {
        btn : [ '确定', '取消' ]
        }, function() {
        var actionUrl = "${path}/menu/del.do";
        var params = {
        ids : ids
        };
        $.post(actionUrl, params, function(data) {
        if (data.success) {
        top.layer.alert('删除成功！！');
        parent.creatMenu();
        refreshForm();
        } else {
        top.layer.alert('删除失败！！'+data.msg);
        return;
        }
        });
        });
        },
        //设置菜单状态启用
        Normal : function() {
        var ids = [];
        ids.push('${param.menuId}');
        top.layer.confirm('确定要启用此菜单吗？', {
        btn : [ '确定', '取消' ]
        }, function() {
        var actionUrl = "${path}/menu/changeStatus.do";
        var params = {
        ids : ids,
        status : 0
        };
        $.post(actionUrl, params, function(data) {
        if (data.success) {
        top.layer.alert('启用成功！！');
        refreshForm();
        } else {
        top.layer.alert('启用失败！！');
        return;
        }
        });
        });
        },
        //设置菜单状态停用
        Unuser : function() {
        var ids = [];
        ids.push('${param.menuId}');
        top.layer.confirm('确定要停用此菜单吗？', {
        btn : [ '确定', '取消' ]
        }, function() {
        var actionUrl = "${path}/menu/changeStatus.do";
        var params = {
        ids : ids,
        status : 1
        };
        $.post(actionUrl, params, function(data) {
        if (data.success) {
        top.layer.alert('停用成功！！');
        refreshForm();
        active.reload();
        } else {
        top.layer.alert('停用失败！！'+data.msg);

        return;
        }
        });
        });
        },
        };
        $('i').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        });
        // $('#menuName').on('input', function() {
        // active.reload();
        // });
        $('.layui-btn').on('click', function() {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        });
        });
        //菜单状态格式化方法
        function statusFormat(value) {
        return value == "0" ? "启用" : "停用";
        }
        //是否掀顶格式化方法
        function creaetTakeOff(value) {
        return value == "0" ? "否" : "是";
        }
        //父级菜单格式化方法
        function creaetmenu(value) {
        if(value==""){
        return "无";
        }else{
        return value.menuName;
        }
        }
        //菜单类型格式化方法
        function creaetmenuType(value) {
        if(value==""){
        return "无";
        }else{
        return value.dvName;
        }
        }
        </script>
        </body>
        </html>