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
    <input type="hidden" id="orgPic" name="orgPic" value="">
    <div class="layui-row">
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">机构名称</label>
                <div class="layui-input-inline">
                    <input name="chName" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="64">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人</label>
                <div class="layui-input-inline">
                    <input name="master" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="64">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构编号</label>
                <div class="layui-input-inline">
                    <input name="chCode" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required" maxlength="100">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-form-item">
                <label class="layui-form-label">父级机构</label>
                <div class="layui-input-inline">
                    <div class="layui-unselect layui-form-select downpanel">
                        <div class="layui-select-title">
                            <span class="layui-input layui-unselect" id="treeclass">机构选择</span>
                            <input type="hidden" id="parentId" name="parentId" value="0">
                            <i class="layui-edge"></i>
                        </div>
                        <dl class="layui-anim layui-anim-upbit">
                            <dd>
                                <ul id="orgTree"></ul>
                            </dd>
                        </dl>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">负责人电话</label>
                <div class="layui-input-inline">
                    <input name="masterPhone" class="layui-input" type="text"
                           autocomplete="off" lay-verify="required|phone">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构类型</label>
                <div class="layui-input-inline">
                    <select name="chType" id="typeSelectId" lay-verify="required">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">描述</label>
                <div class="layui-input-block" style="padding-left: 130px; padding-right: 110px;">
						<textarea class="layui-textarea" name="description"
                                  maxlength="1000" placholder="请输入描述"></textarea>
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
    layui.use(['layer', 'form', 'tree', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
        //表单提交方法
        form.on('submit(subimitForm)', function (data) {
            $.ajax({
                url: '${path }/campusOrg/add.do',
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
                        top.layer.alert("添加失败！");
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
        window.creatArea = function () {
            $.ajax({
                url: '${path }/campusOrg/getOrgTree.do',
                method: 'post',
                data: {},
                dataType: 'JSON',
                success: function (data) {
                    console.log(data);
                    $('ul li').remove();
                    var layuiTree = layui.tree({
                        elem: '#orgTree', //指定元素
                        target: '_blank', //是否新选项卡打开（比如节点返回href才有效）
                        click: function (node) { //点击节点回调
                            var $select = $($(this)[0].elem).parents(".layui-form-select");
                            $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end();//.find("input:hidden[name='selectID']").val(node.id);
                            $("#parentId").val(node.id);
                        },
                        nodes: data
                    });
                },
                error: function (data) {
                    top.layer.alert("服务器异常！");
                }
            })
        };
        creatArea();
        $(".downpanel").on("click", ".layui-select-title", function (e) {
            $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
            $(this).parents(".downpanel").toggleClass("layui-form-selected");
            layui.stope(e);
        }).on("click", "dl i", function (e) {
            layui.stope(e);
        });

        var url = '${path}/dic/getDicValue.do?dicCode=orgType';
        selectUtil($, url, "typeSelectId", "", function () {
            form.render();
        });

        $('.layui-close-layer').click(function () {
            let index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });

    });
</script>
</body>
</html>