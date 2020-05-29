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
<form class="layui-form  serch-bar layui-inline" lay-filter="serchForm">
    日志类型：
    <div class="layui-inline layui-search-input">
        <select id="logType" name="logType">
            <option value="" selected="selected">所有日志</option>
            <option value="login">登录日志</option>
            <option value="do">操作日志</option>
            <option value="db">数据日志</option>
            <option value="WeChat">微信日志</option>
        </select>
    </div>
    创建时间：
    <div class="layui-inline">
        <input class="layui-input layui-search-date" id="startTime" name="startTime"
               type="text" placeholder="开始时间" autocomplete="off">
    </div>
    -
    <div class="layui-inline">
        <input class="layui-input layui-search-date" id="endTime" name="endTime" type="text"
               placeholder="结束时间" autocomplete="off">
    </div>
    <button class="layui-btn layui-btn-orange layui-btn-search" type="button" data-type="reload">搜索</button>
</form>
<table id="log" lay-filter="logList"></table>
<script id="toolBar" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
</script>
<!-- 创建时间数据格式化 -->
<script type="text/html" id="createTime">
    {{ dateFormat(d.createTime,'yMdhms') }}
</script>

<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['table', 'laydate', 'form', 'jquery'], function () {
        var table = layui.table;
        var laydate = layui.laydate;
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
            elem: '#log',
            height: 'full-120',
            url: '${path}/log/getPage.do',
            cellMinWidth: 80,
            cols: [[ //标题栏
                {
                    field: 'logType',
                    title: '日志类型',
                    templet: function (d) {
                        if (d.logType == "login") {
                            return d.logType = "登陆日志";
                        } else if (d.logType == "do") {
                            return d.logType = "操作日志";
                        } else if (d.logType == "db") {
                            return d.logType = "数据日志";
                        } else if (d.logType == "WeChat") {
                            return d.logType = "微信日志"
                        } else {
                            return d.logType = "其他日志";
                        }
                    }
                }, {
                    field: 'logMsg',
                    title: '日志内容'
                }, {
                    field: 'createBy',
                    title: '创建人'
                }, {
                    field: 'createTime',
                    title: '创建时间',
                    templet: "#createTime"
                }, {
                    fixed: 'right',
                    title: '操作',
                    toolbar: '#toolBar',
                    width: 100
                }]],
            skin: 'line', //line row表格风格
            even: true, //,size: 'lg' //尺寸
            page: true, //是否显示分页,
            limits: [10, 15],
            limit: 10
        });
        //监听工具条
        table.on('tool(logList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                layer.open({
                    type: 2,
                    title: '日志信息查看',
                    area: ['1000px', '400px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: 'log_display.jsp?logId=' + data.logId,
                    end: function () {
                        active.reload();
                    }
                });
            }
        });
        var $ = layui.jquery,
            active = {
                //刷新表格数据重新请求
                reload: function () {
                    //执行重载
                    table.reload('log', {
                        page: {
                            curr: 1
                        },
                        //查询条件参数
                        where: {
                            logType: $("#logType").val(),
                            startTime: $("#startTime").val(),
                            endTime: $("#endTime").val(),
                        }
                    });
                },
                //获取被选中的表格数据ID集合
                getCheckData: function () {
                    var data = table.checkStatus('log').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].logId);
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