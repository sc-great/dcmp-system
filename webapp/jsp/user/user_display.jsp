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
    <style>
        .layui-upload-drag img {
            width: auto
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${path }/css/list.css"/>
    <link rel="stylesheet" type="text/css"
          href="${path }/plugin/layui/css/modules/formSelects-v4.css"/>
</head>
<body>
<form class="layui-form  layui-from-header" lay-filter="userForm">
    <input type="hidden" name="userId" value="${param.userId }"/> <input
        type="hidden" id="userPic" name="userPic" value="">
    <div class="layui-row">
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">登录名</label>
                <div class="layui-input-inline">
                    <input name="loginName" class="layui-input" type="text"
                           disabled="disabled" autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input name="userName" class="layui-input" type="text"
                           disabled="disabled" autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">状态</label>
                <div class="layui-input-inline">
                    <input name="status" class="layui-input" type="text"
                           disabled="disabled" autocomplete="off" lay-verify="required">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input name="birthday" class="layui-input" id="birthday"
                           disabled="disabled" type="text" placeholder="yyyy-MM-dd"
                           autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">创建人</label>
                <div class="layui-input-inline">
                    <input name="createUser" class="layui-input" disabled="disabled"
                           type="text" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新人</label>
                <div class="layui-input-inline">
                    <input name="updateUser" class="layui-input" disabled="disabled"
                           type="text" autocomplete="off">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <!-- <input name="userSex" title="男" type="radio" checked="" value="1" disabled="disabled"
                        autocomplete="off">
                    <input name="userSex" title="女" type="radio" value="0" disabled="disabled"
                        autocomplete="off"> -->
                    <input name="userSex" class="layui-input" disabled="disabled"
                           type="text" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input name="userPhone" class="layui-input" type="tel"
                           disabled="disabled" autocomplete="off"
                           lay-verify="required|phone">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">所属区域</label>
                <div class="layui-input-inline">
                    <input id="areaName" class="layui-input" type="text" value=""
                           disabled="disabled">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职务</label>
                <div class="layui-input-inline">
                    <input name="userJob" class="layui-input" type="text"
                           disabled="disabled" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-inline">
						<textarea class="layui-textarea" style="min-height: 60px"
                                  id="role" name="role" placeholder="请输入描述" disabled="disabled"></textarea>
                </div>
            </div>
            <!-- <div class="layui-form-item">
                <label class="layui-form-label">创建时间</label>
                <div class="layui-input-inline">
                    <input name="createTime" class="layui-input" type="text"
                        disabled="disabled" autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">更新时间</label>
                <div class="layui-input-inline">
                    <input name="updateTime" class="layui-input" type="text"
                        disabled="disabled" autocomplete="off">
                </div>
            </div> -->
            <div class="layui-form-item">
                <label class="layui-form-label">类型</label>
                <div class="layui-input-inline">
						<textarea class="layui-textarea" style="min-height: 60px" id="userType" name="userType"
                                  disabled="disabled"></textarea>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item layui-lay-flex">
                <div class="layui-upload-drag" id="uploadPic"
                     style="height: 195px;width: 300px; padding: 0px;">
                    <!-- 						<i class="layui-icon">&#xe67c;</i> -->
                    <p>暂无图片</p>
                </div>
            </div>

        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block"
                     style="padding-left: 130px;padding-right: 60px;">
						<textarea class="layui-textarea" name="description"
                                  disabled="disabled"></textarea>
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
    layui.use(['laydate', 'layer', 'form', 'upload', 'formSelects'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var formSelects = layui.formSelects;
        //表单初始赋值，通用编号获取用户数据初始化表单
        $.post("${path}/user/getById.do", {
            id: "${param.userId}"
        }, function (data) {
            if (data.success) {
                form.val('userForm', {
                    "userName": data.job.userName,
                    "loginName": data.job.loginName,
                    "userPhone": data.job.userPhone,
                    "birthday": dateFormat(data.job.birthday, 'yMd'),
                    "userJob": data.job.userJob,
                    "userSex": data.job.userSex == "1" ? "男" : "女",
                    "status": data.job.status == 0 ? "启用" : "停用",
                    "description": data.job.description,
                    "createUser": data.job.createUser.userName,
                    //"createTime":data.job.createTime==""?"无":dateFormat(data.job.createTime,'yMdhms'),
                    "updateUser": data.job.updateBy.userName,
                    //"updateTime":data.job.updateTime==""?"无":dateFormat(data.job.updateTime,'yMdhms'),
                });
                if (data.job.userPic != "") {
                    $("#uploadPic").empty();
                    console.log($("#uploadPic"));
                    var image = $("<image width='100%' height='100%' src='" + data.job.userPic + "'/>");
                    $("#uploadPic").append(image);
                }
                $("#areaName").val(data.msg);
            } else {
                top.layer.alert('获取用户数据失败！！');
                return;
            }
        });


        $.get("${path}/role/getSelectedRole.do?userId=${param.userId}", function (data) {
            var temp = "";
            for (var list in data) {
                var datalist = data[list];
                if (datalist.selected == "selected") {
                    for (var map in datalist) {
                        if (map == "name") {
                            temp += datalist[map] + "\n";
                        }
                    }
                }
            }
            $("#role").val(temp);
        });

        $.post('${path}/dic/getSelectList.do', {
            dicCode: "userType"
        }, function (data) {
            if (data != null) {
                var datas = data;
                setUserType(datas);
            }
        })

        function setUserType(datas) {
            $.post("${path}/user/getUserTypeByUserId.do", {
                userId: "${param.userId}"
            }, function (data) {
                var selectValue = [];
                var selectValues = "";
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        selectValue.push(data[i].typeCode);
                    }
                    for (var list in selectValue) {
                        for (var map in datas) {
                            if (selectValue[list] == datas[map].value) {
                                selectValues += datas[map].name + "\n";
                            }
                        }
                    }
                }
                $("#userType").val(selectValues);
            });
        }
    });
</script>
</body>
</html>