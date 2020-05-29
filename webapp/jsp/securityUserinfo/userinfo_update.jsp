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
    <style type="text/css">
        .downpanel .layui-select-title span {
            line-height: 38px;
        }

        /*继承父类颜色*/
        .downpanel dl dd:hover {
            background-color: inherit;
        }

        #addEnum {
            position: relative;
            left: 10px;
            top: 5px;
            display: inline-block;
            width: 100px;
        }
    </style>
</head>
<body>
<form class="layui-form  layui-from-header" style="height: calc(100% - 27px); overflow: auto;" lay-filter="mdForm">
    <input type="hidden" id="userPic" name="userPic" value="">
    <input type="hidden" name="bsuId" id="bsuId" value="${param.bsuId }"/>
    <div class="layui-row ">
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">人员名称</label>
                <div class="layui-input-inline">
                    <input name="userName" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="100">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">生日</label>
                <div class="layui-input-inline">
                    <input name="userBirth" class="layui-input" id="userBirth"
                           type="text" placeholder="yyyy-MM-dd" autocomplete="off">
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                    <input name="userSex" title="男" type="radio" checked="" value="1">
                    <input name="userSex" title="女" type="radio" value="0">
                </div>
            </div>
            <div class="layui-form-item type00">
                <div class="layui-form-item">
                    <label class="layui-form-label">身份证号码</label>
                    <div class="layui-input-inline">
                        <input name="userIdcard" class="layui-input" type="text" lay-verify="identity"
                               autocomplete="off" maxlength="100">
                    </div>
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
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
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
    layui.use(['layer', 'laydate', 'upload', 'form', 'tree', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var laydate = layui.laydate;
        var upload = layui.upload;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //用户生日时间格式化
        laydate.render({
            elem: '#userBirth'
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
                url: '${path }/userinfo/update.do',
                method: 'post',
                data: data.field,
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
                        var key = top.layer.alert("更新成功！", {
                                icon: 1,
                                closeBtn: 0
                            },
                            function () {
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.layer.close(key);
                            });
                    } else {
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

        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });
        //表单初始赋值，通过编号获取设备类型数据初始化表单
        $.post("${path}/userinfo/getById.do", {
            bsuId: "${param.bsuId}"
        }, function (data) {
            form.val('mdForm', {
                "userName": data.job.userName,
                "userSex": data.job.userSex,
                "userBirth": dateFormat(data.job.userBirth, 'yMd'),
                "userIdcard": data.job.userIdcard
            });
            console.log("msg= " + data.msg);
            if (data.msg != "") {
                //$("#userPic").val(data.msg);
                $("#uploadPic").empty();
                console.log($("#uploadPic"));
                var image = $("<image width='100%' height='100%' src='data:image/png;base64," + data.msg + "'/>");
                $("#uploadPic").append(image);
            }
        });

    });
</script>
</body>
</html>