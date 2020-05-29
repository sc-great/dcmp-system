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
    <script src="${path }/js/chart/jquery-1.9.1.js"></script>
    <style>
        .layui-table-cell {
            height: 35px;
            line-height: 35px;
            padding: 0;
            position: relative;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
            box-sizing: border-box
        }

        #dicTree > .layui-table-cell:hover {
            color: #0484dd;
            cursor: pointer;
        }

        #dicTree > .active:hover {
            background-color: #0484dd;
            color: white;
            cursor: pointer;
        }

        #dicTree > .layui-table-cell:active, .active {
            background-color: #0484dd;
            color: white;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="main-layout" id='main-layout'>
    <!--侧边栏-->
    <div class="main-layout-count-side">
        <ul id="dicTree" style="margin-top: 20px"></ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container" style="background: white;">
        <!--主体内容-->
        <iframe src="" width="100%" height="100%" id="dicframe"
                name="dicframe" scrolling="auto" class="iframe" framborder="0"></iframe>
    </div>
</div>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>

<script type="text/javascript">
    var initId;
    layui.use(['layer', 'tree', 'form', 'jquery'], function () {
        var $ = layui.jquery;
        var form = layui.form;


        window.creatMenu = function () {
            $.ajax({
                url: '${path }/dic/getDicList.do',
                method: 'post',
                data: {},
                dataType: 'JSON',
                success: function (data) {/*
						$('ul li').remove();
						var layuiTree = layui.tree({
							elem : '#dicTree', //指定元素
							target : '_blank', //是否新选项卡打开（比如节点返回href才有效）
							click : function(item) { //点击节点回调
								if(item.parent&& typeof(c)!='"undefined"'){
									$('#dicframe').attr('src','dic_list.jsp');
								}else{
									$('#dicframe').attr('src','dicValue_list.jsp?dicId='+item.id);
								}
							},
							nodes : data
						}); */
                    initId = data[0].id;
                    $('#dicframe').attr('src', 'dicValue_list.jsp?dicId=' + initId);
                    var parentMenu = [];
                    var childMenu = [];
                    $(data).each(function (index, element) {
                        childMenu.push(this);
                    });
                    /* 	$(parentMenu).each(function(index,element){
                            var parent ="<li class='layui-nav-item'><a href='javascript:;'><i class='iconfont'>"+this.menuIcon+"</i>"+this.name+"<i id='layui-down' class='layui-icon layui-icon-down'></i></a><dl class='layui-nav-child' id='"+this.id+"'></dl></li>";
                            $("#dicTree").append(parent);
                        });		 */
                    $(childMenu).each(function (index, element) {
                        if (index == 0) {
                            var child = "<li class='layui-table-cell active' data=" + this.id + " onclick='setFrame(this)' data-id='" + this.id + "' data-text='" + this.name + "'><span class='menu-left'></span>" + this.name + "</li>";
                        } else {
                            var child = "<li class='layui-table-cell' data=" + this.id + " onclick='setFrame(this)' data-id='" + this.id + "' data-text='" + this.name + "'><span class='menu-left'></span>" + this.name + "</li>";
                        }
                        $("#dicTree").append(child);
                    })
                    /* element.init(); */
                    $("li.layui-nav-item").on('click', function () {
                        var chickItem = this;
                        if ($(chickItem).hasClass("layui-nav-itemed")) {
                            $("li.layui-nav-item").each(function () {
                                if (this != chickItem) {
                                    $(this).removeClass("layui-nav-itemed");
                                }
                            });
                        }
                    });
                },
                error: function (data) {
                    top.layer.alert("服务器异常！");
                }
            })
        }
        //设置frame


        creatMenu();

    });

    function setFrame(value) {
// 			alert("设置iframe");
// 			$('#dicframe').attr('src','dicValue_list.jsp?dicId='+value);
        var data = $(value).attr("data");
        $("#dicTree li").removeClass("active");
        $(value).addClass("active");
        var childFrame = $("#dicframe")[0].contentWindow;
        childFrame.reload(data);
    }
</script>
</body>
</html>
