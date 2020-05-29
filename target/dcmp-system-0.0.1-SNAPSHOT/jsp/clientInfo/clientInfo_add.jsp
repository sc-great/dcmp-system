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
    <link rel="stylesheet" type="text/css"
          href="${path }/plugin/layui/css/modules/formSelects-v4.css"/>
    <style type="text/css">
        .downpanel .layui-select-title span {
            line-height: 38px;
        }

        /*继承父类颜色*/
        .downpanel dl dd:hover {
            background-color: inherit;
        }
    </style>
    <style type="text/css">
        body {
            height: 100%;
            width: 100%;
            background-size: cover;
            margin: 0 auto;
        }

        td {
            font-size: 12px !important;
        }

        .layui-form-checkbox span {
            height: 30px;
        }

        .layui-field-title {
            border-top: 1px solid white;
        }

        table {
            width: 100% !important;
        }

    </style>

</head>
<body>
<form class="layui-form layui-from-header" lay-filter="orgForm">
    <div class="layui-row">
        <div class="layui-col-md4">
             <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input name="name" class="layui-input" type="text" 
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">编号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="code" name="code"  lay-verify="required"
                           type="text"  autocomplete="off">
                </div>
            </div>
          
           
           
        </div>
        <div class="layui-col-md4">
             <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                  <div class="layui-input-inline">
                    <select name="status" id="status" lay-verify="required">
                        <option value="0" selected="selected">启用</option>
                        <option value="1" >停用</option>
                        <select>
                </div>
            </div>
        </div>
    <div class="layui-col-md2">
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
 layui.config({
        base: '${path}/plugin/layui/lay/modules/' //此处路径请自行处理, 可以使用绝对路径
    }).extend({
        formSelects: 'formSelects-v4'
    });
    var nodeId;
    var nodeName;
    layui.use(['laydate', 'layer', 'form', 'tree', 'upload', 'jquery', 'formSelects'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var upload = layui.upload;
        var $ = layui.jquery;
        var formSelects = layui.formSelects;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //用户生日时间格式化
        laydate.render({
            elem: '#PBirth'
        });
        //图片上传控件格式化
      //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
            	url: '${path }/clientInfo/add.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                	console.log(res)
                    if (res.success) {
                        parent.selectAreaId = nodeId;
                        parent.selectAreaName = nodeName;
                        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
                        var key = top.layer.alert("添加成功！", {
                                icon: 1,
                                closeBtn: 0
                            },
                            function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.layer.close(key);
                            });
                    } else {
                        if (res.code == 500) {
                            top.layer.alert(res.msg);
                        } else {
                            top.layer.alert("添加失败！");
                        }

                    }
                },
                error: function (data) {
                    var key = top.layer.alert("服务器异常！",
                        function () {
                            location.reload();
                            parent.layer.close(key);
                        });
                }
            })
            return false;
        });
        
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
//日期范围
var startDate = laydate
		.render({
			elem : '#startTime',
			type : 'datetime',
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
    })

</script>
</body>
</html>