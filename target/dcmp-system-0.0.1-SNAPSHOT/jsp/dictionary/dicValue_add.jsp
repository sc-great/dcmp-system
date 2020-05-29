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
<form class="layui-form  serch-bar" lay-filter="dvForm">
    <input type="hidden" name="dicId" value="${param.dicId }">
    <div class="layui-col-md12" style="width: 100%;">
        <div class="layui-form-item">
            <label class="layui-form-label">数据值名称</label>
            <div class="layui-input-block">
                <input name="dvName" class="layui-input" type="text"
                       autocomplete="off" lay-verify="required" maxlength="32">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数据值编码</label>
            <div class="layui-input-block">
                <input name="dvValue" id="dvValue" class="layui-input"
                       autocomplete="off" lay-verify="required" maxlength="64">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序编号</label>
            <div class="layui-input-block">
                <input name="dvOrder" id="dvOrder" class="layui-input"
                       autocomplete="off" lay-verify="required|znumber" maxlength="11">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数据值说明</label>
            <div class="layui-input-block">
					<textarea class="layui-textarea" name="dvRemark" maxlength="1000"
                              placeholder="请输入备注"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="margin: 0;">
            <div class="layui-submit-block">
                <button class="layui-btn" lay-submit lay-filter="subimitForm">立即提交</button>
                <button type="button" class="layui-btn layui-btn-primary layui-close-layer">取消</button>
            </div>
        </div>
    </div>

</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/dic/addDicValue.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
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
                        /* top.layer.alert("添加失败！"); */
                        top.layer.alert(res.msg);
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

        /* 			$("#dvValue").on('mouseout',function (e){
                        var url ="
        ${path}/dic/getDicValueByValue.do";
			    $.post(url,{dvValue:this.value},function(data){
			    	if(data!=""){
			    		top.layer.alert("数据值已存在，请重新输入！");
			    		$("#dvValue").val("");
			    	}
			    });
			}); */

    });
</script>
</body>
</html>