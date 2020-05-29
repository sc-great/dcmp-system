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
    <link rel="stylesheet" type="text/css" href="${path }/css/admin.css"/>
</head>
<body>
<div class="serch-bar">
    <div class="search-input">
        角色名：
        <div class="layui-inline layui-search-input">
            <input name="roleName" class="layui-input" id="roleName"
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
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="userNormal"><div
                    class="layui-btn-toolbar-up"></div>启用</button>
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="userUnuser"><div
                    class="layui-btn-toolbar-stop"></div>停用</button>
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
                    class="layui-btn-toolbar-del"></div>删除</button>
		</span>
</div>

<table id="role" lay-filter="roleList"></table>
<script id="toolBar" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-del" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="addMenu">添加菜单</a>
</script>
<!-- 创建时间数据格式化 -->
<script type="text/html" id="createTime">
    {{ dateFormat(d.createTime,'yMdhms') }}
</script>
<!-- 用户状态数据格式化 -->
<script type="text/html" id="status">
    {{ statusFormat(d.status) }}
</script>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['table', 'laydate', 'form', 'jquery'], function () {
        var table = layui.table;
        var form = layui.form;
        var layerIndex;
        var layerInitWidth;
        var layerInitHeight;
        var $ = layui.jquery;
        var currpage = $('.layui-laypage-skip').children('.layui-input').val();
        var laydate = layui.laydate;
        $(window).resize(function () {
            resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
        });
        //查询条件开始时间实发始化
        var endDate = laydate.render({
            elem: '#endtime',//选择器结束时间
            type: 'datetime',
            min: "1970-1-1",//设置min默认最小值
            done: function (value, date) {
                if (value == null || value == "") {
                    startDate.config.max = {
                        year: "2099",
                        month: "12",
                        date: "31",
                        hours: "0",
                        minutes: "0",
                        seconds: "0"
                    }
                } else {
                    startDate.config.max = {
                        year: date.year,
                        month: date.month - 1,//关键
                        date: date.date,
                        hours: date.hours,
                        minutes: date.minutes,
                        seconds: date.seconds - 1
                    }
                }
            }
        });
        //日期范围
        var startDate = laydate.render({
            elem: '#startTime',
            type: 'datetime',
            max: "2099-12-31",//设置一个默认最大值
            done: function (value, date) {
                endDate.config.min = {
                    year: date.year,
                    month: date.month - 1, //关键
                    date: date.date,
                    hours: date.hours,
                    minutes: date.minutes,
                    seconds: date.seconds
                };
                var nowStrDate = date.year + "-" + date.month + "-" + date.date + " " + date.hours + ":" + date.minutes + ":" + date.seconds;
                var endyear = endDate.config.dateTime.year;
                var endmonth = endDate.config.dateTime.month + 1;
                var enddate = endDate.config.dateTime.date;
                var endhours = endDate.config.dateTime.hours;
                var endminutes = endDate.config.dateTime.minutes;
                var endseconds = endDate.config.dateTime.seconds;
                var nowEndDate = endyear + "-" + endmonth + "-" + enddate + " " + endhours + ":" + endminutes + ":" + endseconds;
                nowEndDate = new Date(nowEndDate);
                nowStrDate = new Date(nowStrDate);
                if (nowEndDate < nowStrDate) {
                    layer.msg('选择的结束时间小于开始时间了');
                }
            }
        });
        //表格数据初始化
        table.render({
            elem: '#role',
            height: 'full-120',
            url: '${path}/role/getPage.do',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {
                    type: 'checkbox',
                    LAY_CHECKED: false
                }, {
                    field: 'roleName',
                    title: '角色名'
                }, {
                    field: 'description',
                    title: '描述'
                }, {
                    field: 'createBy',
                    title: '创建人'
                }, {
                    field: 'status',
                    title: '状态',
                    templet: "#status"
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    templet: "#createTime"
                }, {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#toolBar',
                    width: 320
                }]],
            skin: 'line', //line row表格风格
            even: true, //,size: 'lg' //尺寸
            page: true, //是否显示分页,
            limits: [10, 15],
            limit: 10
        });
        //监听工具条
        table.on('tool(roleList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                top.layer.open({
                    type: 2,
                    title: '角色信息查看',
                    area: ['1000px', '500px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: '${path}/jsp/role/role_display.jsp?roleId=' + data.roleId,
                    end: function () {
// 							active.reload();
                        table.reload('role', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === 'edit') {
                top.layer.open({
                    type: 2,
                    title: '角色信息修改',
                    area: ['1000px', '400px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: '${path}/jsp/role/role_update.jsp?roleId=' + data.roleId,
                    end: function () {
                        table.reload('role', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === "addMenu") {
                top.layer.open({
                    type: 2,
                    title: '角色添加菜单',
                    area: ['500px', '800px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: '${path}/jsp/role/role_addmenu.jsp?roleId=' + data.roleId,
                    success: function (layero, index) {
                        $(':focus').blur();
                        layerIndex = index;
                        layerInitWidth = $("#layui-layer" + layerIndex).width();
                        layerInitHeight = $("#layui-layer" + layerIndex).height();
                        resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
                    },
                    end: function () {
                        table.reload('role', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === 'del') {
                let id = [data.roleId];
                //console.log(id);
                top.layer.confirm('确定要删除选中的数据吗？', {
                    btn: ['确定', '取消']
                }, function () {
                    var actionUrl = "${path}/role/del.do";
                    var params = {
                        ids: id
                    };
                    $.post(actionUrl, params, function (data) {
                        if (data.success) {
                            top.layer.alert('删除成功！！');
                            //active.reload();
                            table.reload('role', {page: {curr: currpage}});
                        } else {
                            top.layer.alert(data.msg);
                            return;
                        }
                    });
                });

            }
        });
        var $ = layui.jquery,
            active = {
                //角色信息添加
                add: function () {
                    top.layer.open({
                        type: 2,
                        title: '角色信息添加',
                        area: ['1000px', '400px'],
                        fixed: false, //不固定
                        maxmin: false,
                        shade: [0.3],
                        anim: 5,
                        isOutAnim: true,
                        resize: false,
                        content: '${path}/jsp/role/role_add.jsp',
                        success: function () {
                            $(':focus').blur();
                        },
                        end: function () {
                            //	active.reload();
                            table.reload('role', {page: {curr: currpage}});
                        }
                    });
                },
                //角色信息批量删除
                delALL: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要删除的数据！！');
                    } else {
                        top.layer.confirm('确定要删除选中的所有数据吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/role/del.do";
                            var params = {
                                ids: ids
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('删除成功！！');
                                    active.reload();
                                } else {
                                    top.layer.alert(data.msg);
                                    return;
                                }
                            });
                        });
                    }
                },
                //刷新表格数据重新请求
                reload: function () {
                    //执行重载
                    table.reload('role', {
                        page: {
                            curr: 1
                        },
                        //查询条件参数
                        where: {
                            roleName: $("#roleName").val(),
                            startTime: $("#startTime").val(),
                            endTime: $("#endTime").val(),
                        }
                    });
                },
                //设置用户状态启用
                userNormal: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要启用的角色！！');
                    } else {
                        top.layer.confirm('确定要启用选中的角色吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/role/changeStatus.do";
                            var params = {
                                ids: ids,
                                status: 0
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('启用成功！！');
                                    //active.reload();
                                    table.reload('role', {page: {curr: currpage}});
                                } else {
                                    top.layer.alert('启用失败！！');
                                    return;
                                }
                            });
                        });
                    }
                },
                //设置用户状态停用
                userUnuser: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要停用的角色！！');
                    } else {
                        top.layer.confirm('确定要停用选中的角色吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/role/changeStatus.do";
                            var params = {
                                ids: ids,
                                status: 1
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('停用成功！！');
                                    //	active.reload();
                                    table.reload('role', {page: {curr: currpage}});
                                } else {
                                    top.layer.alert(data.msg);
                                    return;
                                }
                            });
                        });
                    }
                },
                //获取被选中的表格数据ID集合
                getCheckData: function () {
                    var data = table.checkStatus('role').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].roleId);
                    }
                    return ids;
                }
            };
        $('i').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
// 			$('#roleName').on('input', function() {
// 				active.reload();
// 			});
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

    //用户状态格式化方法
    function statusFormat(value) {
        return value == "0" ? "启用" : "停用";
    }
</script>
</body>
</html>