<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${path }/css/admin.css"/>
</head>
<body>
<div class="main-layout" id='main-layout'>
    <!--侧边栏-->
    <div class="main-layout-count-side areaSide">
        <ul id="brTree" style="margin-top: 30px;margin-left: 30px;"></ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container areaContainer" style="background: white;">
        <!--主体内容-->
        <iframe src="" width="100%" height="100%" id="areaframe"
                name="areaframe" scrolling="auto" class="iframe" framborder="0"></iframe>
    </div>
</div>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
<script type="text/javascript">
    var creatMenu;
    layui.use(['layer', 'tree', 'form', 'jquery'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        creatMenu = function (selectAreaId, selectAreaName) {
            if (typeof(selectAreaId) != "undefined" && typeof(selectAreaName) != "undefined") {
                $('#areaframe').attr('src', 'user_list.jsp?areaId=' + selectAreaId + '&areaName=' + encodeURI(encodeURI(selectAreaName)));
            }
            $.ajax({
                url: '${path }/org/currentOrgTree.do?selectOrgId=' + selectAreaId,
                method: 'post',
                data: {},
                dataType: 'JSON',
                success: function (data) {
                    console.log(data);
                    $('ul li').remove();
                    var layuiTree = layui.tree({
                        elem: '#brTree', //指定元素
                        target: '_blank', //是否新选项卡打开（比如节点返回href才有效）
                        click: function (item) { //点击节点回调
                            $('#areaframe').attr('src', 'user_list.jsp?areaId=' + item.id + '&areaName=' + encodeURI(encodeURI(item.name)));
                        },
                        nodes: data
                    });
                    $(".layui-tree li a").on("click", function () {
                        $(".layui-tree li a").removeClass("active");
                        $(this).addClass("active");
                    })
                    $(".layui-tree > li > a").addClass("active");
                    if (typeof(selectAreaId) == "undefined") {
                        $('#areaframe').attr('src', 'user_list.jsp?areaId=' + data[0].id + '&areaName=' + encodeURI(encodeURI(data[0].name)));
                    }
                },
                error: function (data) {
                    top.layer.alert("服务器异常！");
                }
            })
        }
        creatMenu();
    });
</script>
</body>
</html>
