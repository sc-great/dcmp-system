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
</head>
<body>
<form class="layui-form layui-from-header" lay-filter="attendanceTimeSetupForm">
    <input type="hidden" name="chId" value="${param.aId }"/>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">上班时间</label>
                  <div class="layui-input-inline">
        <input type="text" class="layui-input" id="workTime" name="workTime" >
      </div>
              <!--   <div class="layui-input-inline">
                    <input name="workTime" class="layui-input" type="text" lay-verify="required" maxlength="64"/>
                </div> -->
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">下班时间</label>
                  <div class="layui-input-inline">
        <input type="text" class="layui-input" id="endTime" name="endTime">
      </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <div class="layui-submit-block">
                    <button class="layui-btn" lay-submit lay-filter="subimitForm">修改时间</button>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'jquery','element', 'laydate'], function () {
    	var laydate = layui.laydate;
        var form = layui.form;
        var $ = layui.jquery;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //表单提交
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/attendanceTimeSetupInfo/update.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
                        var key = top.layer.alert("修改成功！", {icon: 1, closeBtn: 0},
                            function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.layer.close(key);
                                //刷新当前页面
                            	location.reload();
                            });
                    } else {
                        top.layer.alert("修改失败！");
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
        //表单初始赋值，通过编号获取组织机构数据初始化表单
        $.post("${path}/attendanceTimeSetupInfo/getClientValueById.do", {id: "${param.aId}"}, function (data) {
            if (data.success) {
                form.val('attendanceTimeSetupForm', {
                    "workTime": data.data.workTime,
                    "endTime": data.data.endTime,
                });
            } else {
                top.layer.alert('获取数据失败！！');
                return;
            }
        });
        
        //时间选择器
            laydate.render({
                elem: '#workTime'
                , type: 'time'
                , format: 'H:mm'
            });
        
            laydate.render({
                elem: '#endTime'
                , type: 'time'
                , format: 'H:mm'
            });
    });
    

</script>
</body>
</html>