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
<form class="layui-form  layui-from-header" lay-filter="personneForm">
    <input type="hidden" name="PId" value="${param.PId }"/>
    <input type="hidden" id="PPic" name="PPic" value="">
    <div class="layui-row">
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">人员名称</label>
                <div class="layui-input-inline">
                    <input name="PName" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input name="PPhone" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off" maxlength="32" lay-verify="required">
                </div>
            </div>

    <!--        <div class="layui-form-item">
                <label class="layui-form-label">健康状态</label>
                <div class="layui-input-inline" >
                    <select name="status" id="status" disabled="disabled" >
                        <option value="0">异常</option>
                        <option value="1" selected="selected">正常</option>
                        <select>
                </div>
            </div> -->
            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="idCardNo" name="idCardNo" disabled="disabled"
                           type="text"  autocomplete="off">
                </div>
            </div>
          
           
           
        </div>
        <div class="layui-col-md4">
                   <div class="layui-form-item">
                <label class="layui-form-label">组织机构</label>
                <div class="layui-input-inline">
                	<input type="hidden" id="orgId" name="orgId">
                    <input name="orgName" id="orgName" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off">
                </div>
            </div>
             
            
              <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                     <input name="PSex" title="男" type="radio" checked="" value="1" disabled="disabled">
                    <input name="PSex" title="女" type="radio" value="0" disabled="disabled">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">出生日期</label>
                <div class="layui-input-inline">
                    <input name="PBirth" class="layui-input" id="muBirthday" disabled="disabled"
                           type="text" placeholder="yyyy-MM-dd" autocomplete="off">
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">人员编号</label>
                <div class="layui-input-inline">
                    <input name="userCode" id="userCode" class="layui-input" type="text" disabled="disabled"
                           autocomplete="off">
                </div> 
            </div>
             
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item layui-lay-flex">
                <div class="layui-upload-drag" style="height: 195px;width: 300px; padding: 0px;" id="uploadPic">
                    <!-- 						<i class="layui-icon">&#xe67c;</i> -->
                    <p>暂无图片</p>
                </div>
            </div>
          
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block" style="padding-left: 130px;padding-right: 60px;">
						<textarea name="description" class="layui-textarea" type="text"
                                  disabled="Disabled"></textarea>
                </div>
            </div>
        </div>
    </div>


</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    layui.use(['layer', 'form', 'upload', 'jquery'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var $ = layui.jquery;
        //表单初始赋值，通过编号获取人员数据初始化表单
        $.post("${path}/personnelFileInfo/getPersonneValueById.do", {PId: "${param.PId}"}, function (data) {
            if (data.success) {
                console.log("data:" + data);
                form.val('personneForm', {
                    "PName": data.data.PName,
                    "PPhone": data.data.PPhone,
                    "health": data.data.health,
                    "idCardNo": data.data.idCardNo,
                    "status": data.data.status,
                    "PBirth":dateFormat(data.data.PBirth,'yMd'),
                    "orgId": data.data.orgId,
                    "orgName": data.data.orgName,
                    "PSex": data.data.PSex,
                    "userCode":data.data.userCode,
                    "description": data.data.description,
                });
                if (data.data.userPic != "") {
                    $("#PPic").val(data.data.PPic);
                    $("#uploadPic").empty();
                    console.log($("#uploadPic"));
                    var image = $("<image width='100%' height='100%' src='" + data.data.PPic + "'/>");
                    $("#uploadPic").append(image);
                }
            } else {
                top.layer.alert('详细信息初始化失败！！');
                return;
            }
        });
    });
</script>
</body>
</html>