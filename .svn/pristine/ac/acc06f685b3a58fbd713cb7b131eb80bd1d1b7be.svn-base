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
           编号：
        <div class="layui-inline layui-search-input">
            <input name="code" class="layui-input" id="code"
                   autocomplete="off">
        </div>           
         姓名：
          <div class="layui-inline layui-search-input">
            <input name="name" class="layui-input" id="name"
                   autocomplete="off">
        </div>
        日期：
        <div class="layui-inline layui-search-date">
            <input class="layui-input" id="startTime" name="startTime"
                   type="text" placeholder="开始" autocomplete="off">
        </div>
        -
        <div class="layui-inline layui-search-date">
            <input class="layui-input" id="endTime" name="endTime" type="text"
                   placeholder="结束" autocomplete="off">
        </div>      
        <button class="layui-btn layui-btn-orange layui-btn-search" data-type="reload">搜索</button>
    </div>
    <span class="layui-toolbar-leave">
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="add"><div
                    class="layui-btn-toolbar-add"></div>添加</button>			
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div
                    class="layui-btn-toolbar-del"></div>删除</button>           
		</span>
</div>
<!--<table id="sarea" lay-filter="sareaList"></table>-->
<table id="leave" lay-filter="leaveList"></table>

<script id="toolBar" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">修改</a> 
     <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="del">删除</a> 
</script>

<!-- 员工编号 -->
<script type="text/html" id="personCode">
    {{ personCodeFormat(d.person) }}   
</script>

<!--员工姓名 -->
<script type="text/html" id="personName">
    {{ personNameFormat(d.person) }}
</script>
<!--类型 -->
<script type="text/html" id="typeName">
    {{ typeValFormat(d.typeVal) }}
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
								elem : '#endtime',//选择器结束时间
								type : 'date',
								min : "1970-1-1",//设置min默认最小值
								done : function(value, date) {
									if (value == null || value == "") {
										startDate.config.max = {
											year : "2099",
											month : "12",
											date : "31",
											hours : "0",
											minutes : "0",
											seconds : "0"
										}
									} else {
										startDate.config.max = {
											year : date.year,
											month : date.month - 1,//关键
											date : date.date,
											hours : date.hours,
											minutes : date.minutes,
											seconds : date.seconds - 1
										}
									}
								}
							});
							//日期范围
							var startDate = laydate
									.render({
										elem : '#startTime',
										type : 'date',
										max : "2099-12-31",//设置一个默认最大值
										done : function(value, date) {
											endDate.config.min = {
												year : date.year,
												month : date.month - 1, //关键
												date : date.date,
												hours : date.hours,
												minutes : date.minutes,
												seconds : date.seconds
											};
											var nowStrDate = date.year + "-"
													+ date.month + "-"
													+ date.date + " "
													+ date.hours + ":"
													+ date.minutes + ":"
													+ date.seconds;
											var endyear = endDate.config.dateTime.year;
											var endmonth = endDate.config.dateTime.month + 1;
											var enddate = endDate.config.dateTime.date;
											var endhours = endDate.config.dateTime.hours;
											var endminutes = endDate.config.dateTime.minutes;
											var endseconds = endDate.config.dateTime.seconds;
											var nowEndDate = endyear + "-"
													+ endmonth + "-" + enddate
													+ " " + endhours + ":"
													+ endminutes + ":"
													+ endseconds;
											nowEndDate = new Date(nowEndDate);
											nowStrDate = new Date(nowStrDate);
											if (nowEndDate < nowStrDate) {
												layer.msg('选择的结束时间小于开始时间了');
											}
										}
									});
      
      
        //表格数据初始化
        table.render({
            elem: '#leave',
            height: 'full-120',
            url: '${path}/leave/getPage.do',          
            cellMinWidth: 80,
            
            cols: [[ //标题栏
                {
                    type: 'checkbox',
                    LAY_CHECKED: false
                }, {
                    field: 'personCode',
                    title: '员工编号',
                    templet: '#personCode' 
                }, {
                    field: 'personName',
                    title: '员工姓名',  
                    templet: '#personName'                      
 
                }, {
                    field: 'leaveDate',
                    title: '日期'                       
                                 
                }, {
                    field: 'typeVal',
                    title: '类型',
                    templet: '#typeName'  	             
                           
                }, {

                    field: '',
                    title: ''                    
                                                      
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
        table.on('tool(leaveList)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                top.layer.open({
                    type: 2,
                    title: '查看',
                    area: ['700px', '580px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: '${path}/jsp/leave/leave_display.jsp?id=' + data.id,
                    end: function () {
// 							active.reload();
                        table.reload('sarea', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === 'edit') {
                top.layer.open({
                    type: 2,
                    title: '信息修改',
                    area: ['600px', '600px'],
                    fixed: false, //不固定
                    maxmin: false,
                    shade: [0.3],
                    anim: 5,
                    isOutAnim: true,
                    resize: false,
                    content: '${path}/jsp/leave/leave_update.jsp?id=' + data.id,
                    end: function () {
                        table.reload('leave', {page: {curr: currpage}});
                    }
                });
            } else if (obj.event === "addMenu") {  //改为视频联动 ,暂未改
                             
            } else if (obj.event === 'del') {
                let id = [data.id];
                //console.log(id);
                top.layer.confirm('确定要删除选中的数据吗？', {
                    btn: ['确定', '取消']
                }, function () {
                    var actionUrl = "${path}/leave/del.do";
                    var params = {
                        ids: id
                    };
                    $.post(actionUrl, params, function (data) {
                        if (data.success) {
                            top.layer.alert('删除成功！！');
                           // active.reload();
                            table.reload('leave', {page: {curr: currpage}});
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
                //信息添加
                add: function () {
                    top.layer.open({
                        type: 2,
                        title: '添加',
                        area: ['600px', '600px'],
                        fixed: false, //不固定
                        maxmin: false,
                        shade: [0.3],
                        anim: 5,
                        isOutAnim: true,
                        resize: false,
                        content: '${path}/jsp/leave/leave_add.jsp',
                        success: function () {
                            $(':focus').blur();
                        },
                        end: function () {
                            active.reload();
                            table.reload('leave', {page: {curr: currpage}});
                        }
                    });
                },
                //信息批量删除
                delALL: function () {
                    var ids = active.getCheckData();
                    if (ids.length == 0) {
                        top.layer.alert('请选择需要删除的数据！！');
                    } else {
                        top.layer.confirm('确定要删除选中的所有数据吗？', {
                            btn: ['确定', '取消']
                        }, function () {
                            var actionUrl = "${path}/leave/del.do";
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
                    table.reload('leave', {
                        page: {
                            curr: 1
                        },
                        //查询条件参数
                        where: {
                            code: $("#code").val(),
                            name: $("#name").val(),
                            startTime: $("#startTime").val(), 
                            endTime: $("#endTime").val(),                          
                        }
                    });
                },
                //设置用户状态启用
               
                //设置用户状态停用
               
                //获取被选中的表格数据ID集合
                getCheckData: function () {
                    var data = table.checkStatus('leave').data;
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
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

    //员工编号 
    function personCodeFormat(value) {       
        return value == "" ? "无" : value.userCode;
    }
    //员工姓名
    function personNameFormat(value){       
        if (value == "") {
           return "无";
        }
        return value.PName;
    }    
    //类型
    function typeValFormat(value){       
        if (value == "") {
           return "无";
        }
        return value.dvName;
    }    
    
</script>
</body>
</html>