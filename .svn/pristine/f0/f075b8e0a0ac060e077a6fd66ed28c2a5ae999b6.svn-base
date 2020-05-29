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
        content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
        <title></title>
        <link rel="stylesheet" type="text/css" href="${path }/css/admin.css" />
        </head>
        <body>
        <div class="main-layout" id='main-layout'>
        <!--侧边栏-->
        <div class="main-layout-count-side">
        <ul id="brTree" style="margin-top: 30px;margin-left: 30px;"></ul>
        </div>
        <!--右侧内容-->
        <div class="main-layout-container" style="background: white;">
        <!--主体内容-->
        <iframe src="" width="100%" height="100%" id="menuframe"
        name="menuframe" scrolling="auto" class="iframe" framborder="0"></iframe>
        </div>
        </div>
        <script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>
        <script type="text/javascript">
        var creatMenu;
        layui.use([ 'layer', 'tree', 'form', 'jquery' ], function() {
        var $ = layui.jquery;
        var form = layui.form;
        creatMenu = function(selectmenuId) {
        if(typeof(selectmenuId)!="undefined"){
        $('#menuframe').attr('src','menu_right.jsp?menuId='+selectmenuId);
        }
        $.ajax({
        url : '${path }/user/getUserMenuList.do',
        method : 'post',
        dataType : 'JSON',
        success : function(data) {
        $('ul li').remove();
        var layuiTree = layui.tree({
        elem : '#brTree', //指定元素
        target : '_blank', //是否新选项卡打开（比如节点返回href才有效）
        click : function(item) { //点击节点回调
        if (item.children.length == 0) {
        $('#menuframe').attr('src','menu_right.jsp?addNew=block&menuId='+item.id+'&menuName='+encodeURI(encodeURI(item.name)));
        } else {
        $('#menuframe').attr('src','menu_right.jsp?addNew=block&menuId='+item.id+'&menuName='+encodeURI(encodeURI(item.name)));
        }

        },
        nodes : data
        });
        $(".layui-tree li a").on("click", function() {
        $(".layui-tree li a").removeClass("active");
        $(this).addClass("active");
        })
        $(".layui-tree > li:first-child > a").addClass("active");
        if(typeof(selectmenuId)=="undefined"){
        $('#menuframe').attr('src','menu_right.jsp?menuId='+data[0].id+'&menuName='+encodeURI(encodeURI(data[0].name)));
        }

        },
        error : function(data) {
        top.layer.alert("服务器异常！");
        }
        })
        }
        creatMenu();
        });
        </script>
        </body>
        </html>
