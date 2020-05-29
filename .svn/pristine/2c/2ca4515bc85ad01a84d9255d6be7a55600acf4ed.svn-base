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
        <link rel="stylesheet" type="text/css" href="${path }/css/admin.css" />
        </head>
        <body>
        <div class="serch-bar">
        <div class="search-input">
        菜单名：
        <div class="layui-inline layui-search-input">
        <input name="menuName" class="layui-input" id="menuName"
        autocomplete="off">
        </div>
        创建时间：
        <div class="layui-inline layui-search-date">
        <input class="layui-input" id="startTime" name="startTime"
        type="text" placeholder="开始时间" autocomplete="off">
        </div>
        -
        <div class="layui-inline layui-search-date">
        <input class="layui-input" id="endTime" name="endTime" type="text"
        placeholder="结束时间" autocomplete="off">
        </div>
        <button class="layui-btn layui-btn-orange layui-btn-search" data-type="reload">搜索</button>
        </div>
        <span class="layui-toolbar">
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="add"><div
        class="layui-btn-toolbar-add"></div>添加</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="normal"><div
        class="layui-btn-toolbar-up"></div>启用</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="unuse"><div
        class="layui-btn-toolbar-stop"></div>停用</button>
        <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
        class="layui-btn-toolbar-del"></div>删除</button>
        </span>
        </div>

        <table id="menu" lay-filter="menuList"></table>
        <script id="toolBar" type="text/html">
        <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
        <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-primary layui-btn-tool-del" lay-event="del">删除</a>
        </script>
        <script type="text/html" id="status">
        {{ statusFormat(d.status) }}
        </script>
        <script type="text/html" id="parentMenu">
        {{ menuFormat(d.parentMenu) }}
        </script>
        <script src="${path }/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
        <script type="text/javascript">
        layui.use([ 'table', 'laydate', 'form', 'jquery' ], function() {
        var table = layui.table;
        var form = layui.form;
        var layerIndex;
        var layerInitWidth;
        var layerInitHeight;
        var $ = layui.jquery;
        var currpage = $('.layui-laypage-skip').children('.layui-input').val();
        var laydate = layui.laydate;
        $(window).resize(function() {
        resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
        });
        //查询条件开始时间实发始化
        var endDate= laydate.render({
        elem: '#endtime',//选择器结束时间
        type: 'datetime',
        min:"1970-1-1",//设置min默认最小值
        done: function(value,date){
        if(value==null || value=="") {
        startDate.config.max={
        year:"2099",
        month:"12",
        date: "31",
        hours: "0",
        minutes: "0",
        seconds : "0"
        }
        } else {
        startDate.config.max={
        year:date.year,
        month:date.month-1,//关键
        date: date.date,
        hours: date.hours,
        minutes: date.minutes,
        seconds : date.seconds-1
        }
        }
        }
        });
        //日期范围
        var startDate=laydate.render({
        elem: '#startTime',
        type: 'datetime',
        max:"2099-12-31",//设置一个默认最大值
        done: function(value, date){
        endDate.config.min ={
        year:date.year,
        month:date.month-1, //关键
        date: date.date,
        hours: date.hours,
        minutes: date.minutes,
        seconds : date.seconds
        };
        var nowStrDate = date.year + "-" + date.month + "-" + date.date + " " + date.hours + ":" + date.minutes + ":" +
        date.seconds;
        var endyear = endDate.config.dateTime.year;
        var endmonth = endDate.config.dateTime.month + 1;
        var enddate = endDate.config.dateTime.date;
        var endhours = endDate.config.dateTime.hours;
        var endminutes = endDate.config.dateTime.minutes;
        var endseconds = endDate.config.dateTime.seconds;
        var nowEndDate = endyear + "-" + endmonth + "-" + enddate + " " + endhours + ":" + endminutes + ":" +
        endseconds;
        nowEndDate = new Date(nowEndDate);
        nowStrDate = new Date(nowStrDate);
        if(nowEndDate < nowStrDate) {
        layer.msg('选择的结束时间小于开始时间了');
        }
        }
        });
        //表格数据初始化
        table.render({
        elem : '#menu',
        height : 'full-120',
        url : '${path}/menu/getPage.do',
        cellMinWidth : 80,
        cols : [ [ //标题栏
        {
        type : 'checkbox',
        LAY_CHECKED : false
        }, {
        field : 'menuName',
        title : '菜单名'
        }, {
        field : 'menuIcon',
        title : '菜单图标'
        }, {
        field : 'menuUrl',
        title : '菜单路径',
        templet : "#menuUrl"
        }, {
        field : 'menuOrder',
        title : '排序编号'
        }, {
        field : 'parentMenu',
        title : '上级菜单',
        templet : "#parentMenu"
        }, {
        field : 'status',
        title : '状态',
        templet : "#status"
        }, {
        fixed : 'right',
        title : '操作',
        toolbar : '#toolBar',
        width : 270
        } ] ],
        skin : 'line', //line row表格风格
        even : true, //,size: 'lg' //尺寸
        page : true, //是否显示分页,
        limits : [ 10, 15 ],
        limit : 10
        });
        //监听工具条
        table.on('tool(menuList)', function(obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
        top.layer.open({
        type : 2,
        title : '菜单信息查看',
        area : [ '1000px', '450px' ],
        fixed : false, //不固定
        maxmin : false,
        shade: [0],
        anim: 5,
        isOutAnim: true,
        resize: false,
        content : '${path}/jsp/menu/menu_display.jsp?menuId=' + data.menuId,
        end : function() {
        // active.reload();
        }
        });
        } else if (obj.event === 'edit') {
        top.layer.open({
        type : 2,
        title : '菜单信息修改',
        area : [ '1000px', '350px' ],
        fixed : false, //不固定
        maxmin : false,
        shade: [0],
        anim: 5,
        isOutAnim: true,
        resize: false,
        content : '${path}/jsp/menu/menu_update.jsp?menuId=' + data.menuId,
        end : function() {
        table.reload('menu',{page:{curr:currpage}});
        }
        });
        }else if(obj.event==='del'){
        let id=[data.menuId];
        //console.log(id);
        top.layer.confirm('确定要删除选中的数据吗？', {
        btn : [ '确定', '取消' ]
        }, function() {
        var actionUrl = "${path}/menu/del.do";
        var params = {
        ids : id
        };
        $.post(actionUrl, params, function(data) {
        if (data.success) {
        top.layer.alert('删除成功！'+data.msg);
        active.reload();
        } else {
        top.layer.alert('删除失败！'+data.msg);
        return;
        }
        });
        });

        }
        });
        var $ = layui.jquery,
        active = {
        //菜单信息添加
        add : function() {
        top.layer.open({
        type : 2,
        title : '菜单信息添加',
        area : [ '1000px', '350px' ],
        fixed : false, //不固定
        maxmin : false,
        shade: [0],
        anim: 5,
        isOutAnim: true,
        resize: false,
        content : '${path}/jsp/menu/menu_add.jsp',
        success: function(layero, index) {
        layerIndex = index;
        layerInitWidth = $("#layui-layer"+layerIndex).width();
        layerInitHeight = $("#layui-layer"+layerIndex).height();
        resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
        },
        end : function() {
        active.reload();
        }
        });
        },
        //菜单信息批量删除
        delALL : function() {
        var ids = active.getCheckData();
        if (ids.length == 0) {
        top.layer.alert('请选择需要删除的数据！！');
        } else {
        top.layer.confirm('确定要删除选中的所有数据吗？', {
        btn : [ '确定', '取消' ]
        }, function() {
        var actionUrl = "${path}/menu/del.do";
        var params = {
        ids : ids
        };
        $.post(actionUrl, params, function(data) {
        if (data.success) {
        top.layer.alert('删除成功！'+data.msg);
        active.reload();
        } else {
        top.layer.alert('删除失败！'+data.msg);
        return;
        }
        });
        });
        }
        },
        //刷新表格数据重新请求
        reload : function() {
        //执行重载
        table.reload('menu', {
        page : {
        curr : 1
        },
        //查询条件参数
        where : {
        menuName : $("#menuName").val(),
        startTime : $("#startTime").val(),
        endTime : $("#endTime").val(),
        }
        });
        },
        //设置菜单状态启用
        normal : function() {
        var ids = active.getCheckData();
        if (ids.length == 0) {
        top.layer.alert('请选择需要启用的菜单！！');
        } else {
        top.layer.confirm('确定要启用选中的菜单吗？', {
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
        active.reload();
        } else {
        top.layer.alert('启用失败！！');
        return;
        }
        });
        });
        }
        },
        //设置菜单状态停用
        unuse : function() {
        var ids = active.getCheckData();
        if (ids.length == 0) {
        top.layer.alert('请选择需要停用的菜单！！');
        } else {
        top.layer.confirm('确定要停用选中的菜单吗？', {
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
        active.reload();
        } else {
        top.layer.alert('停用失败！！');
        return;
        }
        });
        });
        }
        },
        //获取被选中的表格数据ID集合
        getCheckData : function() {
        var data = table.checkStatus('menu').data;
        var ids = [];
        for (var i = 0; i < data.length; i++) {
        ids.push(data[i].menuId);
        }
        return ids;
        }
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
        function menuFormat(value) {
        if (value == "") {
        return "无";
        }
        return value.menuName;
        }
        </script>
        </body>
        </html>