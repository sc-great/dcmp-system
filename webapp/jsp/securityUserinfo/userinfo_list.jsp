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
        人员名称：
        <div class="layui-inline layui-search-input">
            <input name="userName" class="layui-input" id="userName"
                   autocomplete="off">
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

<table id="dataTable" lay-filter="dataList"></table>
<script id="toolBar" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-del" lay-event="del">删除</a>
</script>
<!-- 触发方式数据格式化 -->
<script type="text/html" id=triggerType>
    {{ triTypeFormat(d.triggerType) }}
</script>
<!-- 出生日期数据格式化 -->
<script type="text/html" id="userBirth">
    {{ dateFormat(d.userBirth,'yMd') }}
</script>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['table', 'form', 'jquery'], function () {
        var table = layui.table;
        var form = layui.form;
        var layerIndex;
        var layerInitWidth;
        var layerInitHeight;
        var $ = layui.jquery;
        var currpage = $('.layui-laypage-skip').children('.layui-input').val();
        $(window).resize(function () {
            resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
        });
        //表格数据初始化
        table.render({
            elem: '#dataTable',
            height: 'full-120',
            url: '${path}/userinfo/getPage.do',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {
                    type: 'checkbox',
                    LAY_CHECKED: false
                }, {
                    field: 'userName',
                    title: '人员名称'
                }, {
                    field: 'userBirth',
                    title: '出生日期',
                    templet: "#userBirth",
                    width: 250
                }, {
                    field: 'userSex',
                    title: '性别',
                    templet: function (d) {
                        return d.userSex == "1" ? "男" : "女";
                    }
                }, {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#toolBar',
                    width: 340
                }]],
            skin: 'line', //line row表格风格
            even: true, //,size: 'lg' //尺寸
            page: true, //是否显示分页,
            limits: [10, 15],
            limit: 10
        });
        //监听工具条
        table.on('tool(dataList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                top.layer.open({
                    type: 2,
                    title: '人员信息查看',
                    area: ['1200px', '500px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: ['${path}/jsp/securityUserinfo/userinfo_display.jsp?bsuId=' + data.bsuId, 'no'],
                    success: function (layero, index) {
                        layerIndex = index;
                        layerInitWidth = $("#layui-layer" + layerIndex).width();
                        layerInitHeight = $("#layui-layer" + layerIndex).height();
                        resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
                    },
                    end: function () {
// 							active.reload();
                    }
                });
            } else if (obj.event === 'edit') {
                top.layer.open({
                    type: 2,
                    title: '人员信息信息修改',
                    area: ['1200px', '550px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: ['${path}/jsp/securityUserinfo/userinfo_update.jsp?bsuId=' + data.bsuId, 'no'],
                    success: function (layero, index) {
                        layerIndex = index;
                        layerInitWidth = $("#layui-layer" + layerIndex).width();
                        layerInitHeight = $("#layui-layer" + layerIndex).height();
                        resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
                    },
                    end: function () {
                        table.reload('dataTable', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === 'del') {
                let id = [data.bsuId];
                //console.log(id);
                top.layer.confirm('确定要删除该数据吗？', {
                    btn: ['确定', '取消']
                }, function () {
                    var actionUrl = "${path}/userinfo/del.do";
                    var params = {
                        ids: id
                    };
                    $.post(actionUrl, params, function (data) {
                        if (data.success) {
                            top.layer.alert('删除成功！！');
                            active.reload();
                        } else {
                            top.layer.alert('删除失败！' + data.msg);
                            return;
                        }
                    });
                });

            }
        });
        var $ = layui.jquery,
            active = {
                //设备类型信息添加
                add: function () {
                    top.layer.open({
                        type: 2,
                        title: '人员信息信息添加',
                        area: ['1200px', '550px'],
                        fixed: false, //不固定
                        maxmin: false,
                        shade: [0.3],
                        anim: 5,
                        isOutAnim: true,
                        resize: false,
                        content: ['${path}/jsp/securityUserinfo/userinfo_add.jsp', 'no'],
                        success: function (layero, index) {
                            $(':focus').blur();
                            layerIndex = index;
                            layerInitWidth = $("#layui-layer" + layerIndex).width();
                            layerInitHeight = $("#layui-layer" + layerIndex).height();
                            resizeLayer($, layerIndex, layerInitWidth, layerInitHeight);
                        },
                        end: function () {
                            active.reload();
                        }
                    });
                },
                //设备类型信息批量删除
                delALL: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要删除的数据！！');
                    } else {
                        top.layer.confirm('确定要删除选中的数据吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/userinfo/del.do";
                            var params = {
                                ids: ids
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('删除成功！！');
                                    active.reload();
                                } else {
                                    top.layer.alert('删除失败！' + data.msg);
                                    return;
                                }
                            });
                        });
                    }
                },
                //刷新表格数据重新请求
                reload: function () {
                    //执行重载
                    table.reload('dataTable', {
                        page: {
                            curr: 1
                        },
                        //查询条件参数
                        where: {
                            userName: $("#userName").val(),
                        }
                    });
                },
                //设置设备类型状态启用
                normal: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要启用的人员信息！！');
                    } else {
                        var key = top.layer.confirm('确定要启用选中的人员信息吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/userinfo/changeStatus.do";
                            var params = {
                                ids: ids,
                                status: 0
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('启用成功！！');
                                    active.reload();
                                } else {
                                    top.layer.alert('启用失败！！');
                                    return;
                                }
                            });
                            parent.layer.close(key);
                        });
                    }
                },
                //设置设备类型状态停用
                unuse: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要停用的人员信息！！');
                    } else {
                        var key = top.layer.confirm('确定要停用选中的人员信息吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/userinfo/changeStatus.do";
                            var params = {
                                ids: ids,
                                status: 1
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('停用成功！！');
                                    active.reload();
                                } else {
                                    top.layer.alert('停用失败！！');
                                    return;
                                }
                            });
                            parent.layer.close(key);
                        });
                    }
                },
                //获取被选中的表格数据ID集合
                getCheckData: function () {
                    var data = table.checkStatus('dataTable').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].bsuId);
                    }
                    return ids;
                }
            };
        $('i').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
// 			$('#mdName').on('input', function() {
// 				active.reload();
// 			});
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

    //触发方式格式化方法
    function triTypeFormat(value) {
        var result = "未知";
        switch (value) {
            case "0000":
                result = "报警代码";
                break;
            case "0001":
                result = "阀值段";
                break;
            case "0002":
                result = "枚举值";
                break;
        }
        return result;
    }
</script>
</body>
</html>