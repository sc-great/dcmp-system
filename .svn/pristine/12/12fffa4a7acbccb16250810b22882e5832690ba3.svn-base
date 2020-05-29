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
<form class="layui-form  layui-from-header" lay-filter="areaForm">
    <input type="hidden" name="areaId" value="${param.areaId }"/>
     <input type="hidden" name="areaId2" value="${param.areaId }"/>
    <input type="hidden" name="id" value="${param.id }"/>
    <input type="hidden" name="id2" value="${param.id2 }"/>
    <div class="layui-row">
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">视频名称</label>
                <div class="layui-input-inline">
                    <input name="movName" class="layui-input" type="text" 
                           autocomplete="off" lay-verify="required" maxlength="50">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">厂商</label>
                <div class="layui-input-inline">
                    <input name="firm" id="firm" class="layui-input" type="text" 
                           autocomplete="off"  maxlength="50">
                </div> 
            </div>          

        </div>
        <div class="layui-col-md4">
 
            <div class="layui-form-item">
                <label class="layui-form-label">IP地址</label>
                <div class="layui-input-inline">
                    <input name="ipAddr" class="layui-input" type="text" 
                           autocomplete="off" maxlength="32" lay-verify="required|ip" maxlength="30">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">端口</label>
                <div class="layui-input-inline">
                    <input class="layui-input"  name="port" 
                           type="text"  autocomplete="off"  lay-verify="required|number"  maxlength="10">
                </div>
            </div>              
            
        </div>
         <div class="layui-col-md4">
                                 
              <div class="layui-form-item">
                <label class="layui-form-label">帐号</label>
                <div class="layui-input-inline">
                <input style="display:none">
                     <input name="userName" class="layui-input" type="text"
                           autocomplete="off"  maxlength="20"  >
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
               <input type="password" name="password1"  style="display:none"/>
                    <input name="passWord" class="layui-input" type="password"
                           autocomplete="off"  maxlength="20"  >
                           
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block" style="padding-left: 130px;padding-right: 60px;">
						<textarea name="note" maxlength="200" class="layui-textarea"  ></textarea>
                </div>
            </div>
        </div>
   </div>
    <HR style="border:1 dashed #987cb9" width="100%" color=#987cb9 SIZE=1>   
    <div class="layui-row">
    <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">视频名称</label>
                <div class="layui-input-inline">
                    <input name="movName2" class="layui-input" type="text" 
                           autocomplete="off"  maxlength="50">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">厂商</label>
                <div class="layui-input-inline">
                    <input name="firm2" id="firm2" class="layui-input" type="text" 
                           autocomplete="off" maxlength="50">
                </div> 
            </div>          

        </div>
        <div class="layui-col-md4">
 
            <div class="layui-form-item">
                <label class="layui-form-label">IP地址</label>
                <div class="layui-input-inline">
                    <input name="ipAddr2" class="layui-input" type="text" 
                           autocomplete="off" maxlength="30" >
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">端口</label>
                <div class="layui-input-inline">
                    <input class="layui-input"  name="port2" 
                           type="text"  autocomplete="off"    maxlength="10" >
                </div>
            </div>              
            
        </div>
         <div class="layui-col-md4">
                                 
              <div class="layui-form-item">
                <label class="layui-form-label">帐号</label>
                <div class="layui-input-inline">
                <input style="display:none">
                     <input name="userName2" class="layui-input" type="text"
                           autocomplete="off"  maxlength="20" >
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                <input type="password" style="width:0;height:0;float:left;visibility:hidden"/>
                    <input name="passWord2" class="layui-input" type="password"
                           autocomplete="off"  maxlength="20" >
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block" style="padding-left: 130px;padding-right: 60px;">
						<textarea name="note2" maxlength="200" class="layui-textarea"   ></textarea>
                </div>
            </div>
        </div>
    </div>
         
    <div class="layui-col-md12">
        <div class="layui-form-item">
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
    layui.use(['laydate', 'layer', 'form', 'jquery'], function () {
        var form = layui.form;
      
            
        var laydate = layui.laydate;
        var $ = layui.jquery;
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //表单提交
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/sarea/update2.do',
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
                            });
                    } else {
                        top.layer.alert("修改失败！" + res.msg);
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
        //表单初始赋值，通用编号获取用户数据初始化表单
        $.post("${path}/sarea/getById2.do", {id: "${param.areaId}"}, function (data) {
            if (data.success) {
                form.val('areaForm', {
                    "movName": data.job.movName,
                    "ipAddr":  data.job.ipAddr,
                    "port": data.job.port,                    
                    "firm": data.job.firm,
                    "note": data.job.note, 
                    "movName2": data.job.movName2,
                    "ipAddr2":  data.job.ipAddr2,
                    "port2": data.job.port2,                    
                    "firm2": data.job.firm2,
                    "note2": data.job.note2, 
                    "id": data.job.id,                  
                    "id2": data.job.id2, 
                    "userName": data.job.userName,
                    "passWord": data.job.passWord, 
                    "userName2": data.job.userName2,
                    "passWord2": data.job.passWord2,                
                });
             //var hostCode = data.job.host == "" ? "无" : data.job.host.code;
             //  $("#hostSelectId").val(hostCode);
            //   var url = '${path}/sarea/getHostValue.do?hostId=';
             //  selectUtil($, url, "hostSelectId", data.job.host.name, function () {
             //    form.render();
              // });
            } else {
                top.layer.alert('获取用户数据失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>