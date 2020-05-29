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
        .layui-form-checkbox[lay-skin=primary] {
            height: 30px !important;
        }
    </style>
</head>
<body>
<form class="layui-form">
    <div id="xtree1" class="xtree_contianer"></div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn addRoleMenusBtn" lay-submit lay-filter="subimitForm">立即提交</button>
        </div>
    </div>
</form>
<script src="${path }/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="${path }/plugin/layui-xtree/layui-xtree.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

    //********************
    //      正式开始
    //********************

    //layui 的 form 模块是必须的
    layui.use(['form', 'jquery'], function () {
        $ = layui.jquery;
        var form = layui.form;
        var xtree1;
        getRoleMenus();

        function getRoleMenus() {
            $.ajax({
                url: '${path }/role/getRoleMenus.do',
                method: 'post',
                data: {roleId: "${param.roleId}"},
                dataType: 'JSON',
                success: function (data) {
                    xtree1 = new layuiXtree({
                        elem: 'xtree1'   //(必填) 放置tree的容器
                        , form: form     //(必填) layui 的 from
                        , data: data     //(必填) json数据
                        , ckall: true    //启用全选功能，默认值：false
                        , ckallback: function () {
                        } //全选框状态改变后执行的回调函数
                        , color: {       //三种图标颜色，独立配色，更改几个都可以
                            open: "#EE9A00"        //节点图标打开的颜色
                            , close: "#EEC591"     //节点图标关闭的颜色
                            , end: "#828282"       //末级节点图标的颜色
                        }
                        , click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
// 				                console.log(data.elem.checked); //开关是否开启，true或者false
// 				                console.log(data.value);  //开关value值，也可以通过data.elem.value得到
                        }
                    });
                },
                error: function (data) {
                    top.layer.alert("服务器异常！");
                }
            })
        };
        form.on('submit(subimitForm)', function (data) {
            var oCks = xtree1.GetChecked(); //这是方法
            var parentValues = [];
            for (var i = 0; i < oCks.length; i++) {
                var parentTree = xtree1.GetParent(oCks[i].value);
                var hasIn = false;
                for (var j = 0; j < parentValues.length; j++) {
                    console.log(parentTree);
                    if (parentTree != null) {
                        if (parentValues[j] == parentTree.value) {
                            hasIn = true;
                        }
                    } else {
                        hasIn = true;
                    }

                }
                if (!hasIn) {
                    parentValues.push(parentTree.value);
                }
            }
            var checkedValues = [];
            for (var i = 0; i < oCks.length; i++) {
                checkedValues.push(oCks[i].value);
            }
            console.log(parentValues + "wc");
            for (var i = 0; i < parentValues.length; i++) {
                checkedValues.push(parentValues[i]);
            }
            //console.log(checkedValues);
            $.ajax({
                url: '${path }/role/updateRoleMenus.do',
                method: 'post',
                data: {checkedMenuIds: checkedValues, roleId: "${param.roleId}"},
                dataType: 'JSON',
                success: function (res) {
                    if (res.success) {
                        var key = top.layer.alert("添加成功！", {icon: 1, closeBtn: 0},
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
                    top.layer.alert("服务器异常！");
                }
            })
            return false;
        });
        /* function getParentValues(value,datas){
            //console.log("1:"+value);
            var parentTree=xtree1.GetParent(value);
            if(parentTree!=null){
                var parentValue=parentTree.value;
                datas.push(parentTree.title);
                getParentValues(parentValue,datas);
            }

        } */

    });

</script>
</body>
</html>