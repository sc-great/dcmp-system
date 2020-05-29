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
        .layui-tab-card > .layui-tab-title li {
            width: 50%;
            padding: 0;
            margin: 0;
        }

        .layui-tab {
            margin: 0;
        }

        .layui-tab-content {
            margin: 0px;
            padding: 0px;
        }

        .layui-table-view .layui-table[lay-skin="line"] {
            border-width: 0px 1px 0px 0px;
            width: 100%;
        }

        .layui-tab-title {
            position: static;
        }

        .layui-btn-tool-add {
            width: 120px;
            float: right;
            margin-right: 50px !important;
        }

        .layui-tab-card {
            box-shadow: none;
        }
    </style>
</head>
<body>
<input type="hidden" name="orgId" value="${param.orgId }"/>
<div class="layui-tab layui-tab-card">
    <ul class="layui-tab-title">
        <li class="layui-this">未配置会客区域</li>
        <li>已配置会客区域</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <div class="serch-bar">
                <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="conf">启用会客区域</button>
                <br style="clear:both"/>
            </div>
            <table id="dataTable1" lay-filter="dataList1">
            </table>
        </div>
        <div class="layui-tab-item">
            <div class="serch-bar">
                <button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL">停用会客区域</button>
                <br style="clear:both"/>
            </div>
            <table id="dataTable2" lay-filter="dataList2"></table>
        </div>
    </div>
</div>
<!-- 创建时间数据格式化 -->
<script type="text/html" id="createTime">
    {{ dateFormat(d.createTime,'yMdhms') }}
</script>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script>
    layui.use('element', function () {
        var $ = layui.jquery,
            element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
// 			$('.site-demo-active').on('click', function() {
// 				var othis = $(this),
// 					type = othis.data('type');
// 				active[type] ? active[type].call(this, othis) : '';
// 			});
// 			element.tabChange('test', layid);
        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });
    });
</script>
<script type="text/javascript">
    layui.use(['table', 'form', 'jquery'], function () {
        var table = layui.table;
        //表格数据初始化获取未添加设备
        table.render({
            elem: '#dataTable1',
            url: '${path}/visitorArea/getVaUnSelectPage.do?orgId=${param.orgId}',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {
                    type: 'checkbox',
                    LAY_CHECKED: false
                }, {
                    field: 'vaName',
                    title: '区域名'
                }, {
                    field: 'vaOrder',
                    title: '排序'
                }, {
                    field: 'status',
                    title: '区域状态',
                    templet: function (d) {
                        return d.status == 0 ? "启用" : "禁用";
                    }
                }, {
                    field: 'createBy',
                    title: '创建人',
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    templet: "#createTime"
                }]],
            skin: 'line', //line row表格风格
            even: true, //,size: 'lg' //尺寸
            page: false, //是否显示分页,
            limits: [100, 200],
            limit: 100
        });
        //表格数据初始化获取已添加设备
        table.render({
            elem: '#dataTable2',
            url: '${path}/visitorArea/getVaSelectPage.do?orgId=${param.orgId}',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {
                    type: 'checkbox',
                    LAY_CHECKED: false
                }, {
                    field: 'vaName',
                    title: '区域名'
                }, {
                    field: 'vaOrder',
                    title: '排序'
                }, {
                    field: 'status',
                    title: '区域状态',
                    templet: function (d) {
                        return d.status == 0 ? "启用" : "禁用";
                    }
                }, {
                    field: 'createBy',
                    title: '创建人',
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    templet: "#createTime"
                }]],
            skin: 'line', //line row表格风格
            even: true, //,size: 'lg' //尺寸
            page: false, //是否显示分页,
            limits: [100, 200],
            limit: 100
        });
        var $ = layui.jquery,
            active = {
                //设备类型信息批量删除
                delALL: function () {
                    var ids = active.getCheckData2();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要停用的区域！！');
                    } else {
                        top.layer.confirm('确定要停用这些区域吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/visitorArea/delOrgArea.do?orgId=${param.orgId}";
                            var params = {
                                ids: ids
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('停用成功！！');
                                    active.reload2();
                                    active.reload1();
                                } else {
                                    top.layer.alert('停用失败！！');
                                    return;
                                }
                            });
                        });
                    }
                },//刷新表格数据重新请求
                reload1: function () {
                    //执行重载
                    table.reload('dataTable1', {});
                },
                //刷新表格数据重新请求
                reload2: function () {
                    //执行重载
                    table.reload('dataTable2', {});
                },
                //批量配置设备
                conf: function () {
                    var ids = active.getCheckData1();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要启用的会客区域！！');
                    } else {
                        top.layer.confirm('确定要启用这些会客区域？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/visitorArea/confOrgArea.do?orgId=${param.orgId}";
                            var params = {
                                ids: ids,
                                status: 0
                            };
                            $.post(actionUrl, params, function (data) {
                                if (data.success) {
                                    top.layer.alert('启用成功！！');
                                    active.reload1();
                                    active.reload2();
                                } else {
                                    top.layer.alert('启用失败！！');
                                    return;
                                }
                            });
                        });
                    }
                },
                //获取被选中的表格数据ID集合
                getCheckData1: function () {
                    var data = table.checkStatus('dataTable1').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].vaId);
                    }
                    return ids;
                },
                //获取被选中的表格数据ID集合
                getCheckData2: function () {
                    var data = table.checkStatus('dataTable2').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].vaId);
                    }
                    return ids;
                }
            };
        $('i').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>