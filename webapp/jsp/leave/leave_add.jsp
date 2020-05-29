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
<form class="layui-form  layui-from-header" lay-filter="leaveForm">
   
    <div class="layui-row" >
        <div class="layui-col-md12">
          
            <div class="layui-form-item">
                <label class="layui-form-label">员工姓名</label>
                <div class="layui-input-block">
                <select name="personId" id="personId" lay-verify="required"  lay-search>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">日期</label>
                <div class="layui-input-block">
                    <input  id="leaveDate"  name="leaveDate" class="layui-input" type="text" 
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
           <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-block">
                    <select name="typeId" id="typeId" lay-verify="required"  lay-search>
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>

        </div>
        
         <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;">
						<textarea class="layui-textarea" name="note" 
                                  ></textarea>
                </div>
            </div>
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
    layui.use(['laydate', 'layer', 'form', 'jquery'], function () {
        var form = layui.form;
        var laydate = layui.laydate;
        var $ = layui.jquery;
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
         //用户生日时间格式化
        laydate.render({
            elem: '#leaveDate'
        });
        //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/leave/add.do',
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
                        //layer.alert("添加失败！");
                        top.layer.alert("添加失败！" + res.msg);
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
        var url = '${path}/leave/getPersonValue.do?hostId=';
        selectUtil($, url, "personId", "", function () {
            form.render();
        });
        
         var url = '${path}/leave/getTypeValue.do?typeCode='+'leaveType';
        selectUtil($, url, "typeId", "", function () {
            form.render();
        });
 
        
    });
</script>
</body>
</html>