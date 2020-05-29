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
    <style>
        .xm-select-parent dl dd.xm-select-this div i {
            color: #0484dd
        }

        .xm-form-checkbox:hover > i {
            border-color: #666
        }

        .xm-form-checkbox > i {
            border: 1px solid #666
        }

        .downpanel .layui-select-title span {
            line-height: 38px;
        }

        .layui-upload-drag img {
            width: auto
        }

        /*继承父类颜色*/
        .downpanel dl dd:hover {
            background-color: inherit;
        }
    </style>
</head>
<body>
<form class="layui-form  layui-from-header" lay-filter="userForm">
    <input type="hidden" id="userPic" name="userPic" value="">
   <%--  <input type="hidden" id=areaId name="areaCode" value="${param.areaId }"> --%>
    <div class="layui-row">
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">登录名</label>
                <div class="layui-input-inline">
                    <input name="loginName" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="32">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                    <input name="userName" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="64">
                </div>
            </div>
           <%--  <div class="layui-form-item">
                <label class="layui-form-label"><span style="color:red">*</span>&nbsp;&nbsp;所属区域</label>
                <div class="layui-input-inline">
                    <div class="layui-unselect layui-form-select downpanel">
                        <div class="layui-select-title">
                            <span class="layui-input layui-unselect" id="treeclass">${param.areaName }</span>
                            <input type="hidden" id="areaId" name="areaId" lay-verify="required"
                                   value="${param.areaId }">
                            <i class="layui-edge"></i>
                        </div>
                        <dl class="layui-anim layui-anim-upbit">
                            <dd>
                                <ul id="areaTree"></ul>
                            </dd>
                        </dl>
                    </div>
                    <input type="hidden" id="areaId" name="areaId" value="${param.areaId }">
                    <input id="areaName" class="layui-input" type="text" value="${param.areaName }"
                        disabled="disabled">
                </div>
            </div> --%>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span style="color:red">*</span>&nbsp;&nbsp;类型
                </label>
                <div class="layui-input-inline">
                    <select name="userType" id="userType" xm-select="userType"
                            lay-verify="required">
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开启手机端</label>
                <div class="layui-input-inline">
                    <select name="mobileOpen" id="mobileOpen">
                        <option value="1">是</option>
                        <option value="0" selected="selected">否</option>
                        <select>
                </div>
            </div>
			<div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input name="userSex" title="男" type="radio" checked="" value="1">
                    <input name="userSex" title="女" type="radio" value="0">
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input name="userPhone" class="layui-input" type="tel"
                           autocomplete="off" lay-verify="required|phone">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input name="passwd" class="layui-input" type="password"
                           autocomplete="off" lay-verify="required" maxlength="1000">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">职务</label>
                <div class="layui-input-inline">
                    <input name="userJob" class="layui-input" type="text"
                           autocomplete="off" maxlength="32">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">
                    <span style="color:red">*</span>&nbsp;&nbsp;角色
                </label>
                <div class="layui-input-inline">
                    <select name="role" id="role" xm-select="selectId" lay-verify="required">
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input name="birthday" class="layui-input" id="birthday"
                           type="text" placeholder="yyyy-MM-dd" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item layui-lay-flex">
                <div class="layui-upload-drag" id="uploadPic"
                     style="text-align: center; height: 195px;width: 300px; padding: 0px;">
                    <i class="layui-icon" style="position: relative; top: 50px;">&#xe67c;</i>
                    <p style="position: relative; top: 50px;">点击上传图片，或将文件拖拽到此处</p>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block" style="padding-left: 130px;padding-right: 60px;">
						<textarea class="layui-textarea" name="description"
                                  maxlength="1000" placeholder="请输入备注"></textarea>
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
            elem: '#birthday'
        });
        //图片上传控件格式化
        upload.render({
            elem: '#uploadPic',
            url: '${path}/attachment/addPic.do',
            multiple: true,
            size: 1024,
            exts: "jpg|png|gif|bmp|jpeg|pdf",
            done: function (res) {
                //如果上传失败
                if (res.success) {
                    $("#userPic").val(res.job.url);
                    $("#uploadPic").empty();
                    var image = $("<image width='100%' height='100%' src='" + res.job.url + "'/>");
                    $("#uploadPic").append(image);
                } else {
                    layer.msg('上传失败');
                }
            }
        });
        //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/user/add.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
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

/*         $.ajax({
            url: '${path }/org/currentOrgTree.do',
            method: 'post',
            data: {},
            dataType: 'JSON',
            success: function (data) {
                $('ul li').remove();
                var layuiTree = layui.tree({
                    elem: '#areaTree', //指定元素
                    target: '_blank', //是否新选项卡打开（比如节点返回href才有效）
                    click: function (node) { //点击节点回调
                        var $select = $($(this)[0].elem).parents(".layui-form-select");
                        $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end();
                        $("#areaId").val(node.id);
                        nodeId = node.id;
                        nodeName = node.name;
                    },
                    nodes: data
                });
            },
            error: function (data) {
                top.layer.alert("服务器异常！");
            }
        }); */
        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });
        $.post('${path}/dic/getSelectList.do', {
            dicCode: "userType"
        }, function (data) {
            if (data != null) {
                formSelects.data('userType', 'local', {
                    arr: data
                });
            }
        });
        //server模式
        formSelects.data('selectId', 'server', {
            url: '${path}/role/getSelectList.do'
        });
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
</script>
</body>
</html>