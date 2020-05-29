<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${path }/js/code.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>格瑞特疾控监测平台-系统登录</title>
    <link rel="stylesheet" type="text/css" href="${path }/css/login.css"/>
</head>
<body>
<!--  <div class="s-login-bg">    -->
<div class="m-login-bg">
    <!-- <div class="c-login-bg">-->
    <div class="m-login">
        <!-- <div class="s-login-header">  -->
        <div class="m-login-header">
            <!-- <div class="c-login-header">  -->
        </div>
        <div class="m-login-warp">
            <form class="layui-form" lay-filter="loginForm">
                <div class="layui-form-item">
                    <div class="m-login-user"></div>
                    <input type="text" name="userName" id="userName" required
                           lay-verify="required" placeholder="请输入账号" autocomplete="off"
                           class="layui-input input">
                </div>
                <div class="layui-form-item">
                    <div class="m-login-pass"></div>
                    <input type="password" name="passWord" id="passWord" required
                           lay-verify="required" placeholder="请输入密码" autocomplete="off"
                           class="layui-input">
                </div>
                <div class="layui-form-item">
                    <div class="m-login-verifyInput">
                        <div class="m-login-verify"></div>
                        <input type="text" name="verity" id="verity" required
                               lay-verify="required" placeholder="请输入验证码" autocomplete="off"
                               class="layui-input">
                    </div>
                    <div class="m-login-verifyImg">
                        <!-- 							<img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" -->
                        <%-- 							src="${path }/images/login/yzm.jpg" /> --%>
                        <div id="v_container" style="width: 100%;height: 52px;"></div>
                    </div>
                </div>
                <div class="layui-form-item m-login-btn">
                    <button id="sub" class="layui-btn layui-btn-normal" lay-submit
                            lay-filter="login">登录
                    </button>
                </div>
            </form>
        </div>
        <p class="copyright">Copyright@ 2017-2018 by 四川格瑞特科技有限公司</p>
    </div>
</div>
<script src="${path }/plugin/layui/layui.js" type="text/javascript"
        charset="utf-8"></script>

<script>
    layui.use(['form', 'layedit', 'laydate', 'jquery'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            },
            password: [/(.+){6,12}$/, '密码必须6到12位'],
// 				verity : [ /(.+){6}$/, '验证码必须是6位' ],
        });
        //监听提交
        form.on('submit(login)', function (data) {
            var res = verifyCode.validate(document.getElementById("verity").value);
            if (res) {
                $.ajax({
                    url: '${path }/sys/login.do',
                    method: 'post',
                    data: data.field,
                    dataType: 'JSON',
                    success: function (res) {
                        if (res.success) {
                            window.location.href = "index.jsp";
                        } else {
                            layer.alert(res.msg);
                        }
                    },
                    error: function (data) {
                        layer.alert("服务器异常！");
                    }
                })
            } else {
                layer.alert("验证码错误");
            }
            return false;
        });
        form.val('loginForm', {
            "userName": "admin",
            "passWord": "123456",
// 				"verity" : 456
        });
    });
    var verifyCode = new GVerify("v_container");
    // 		document.getElementById("my_button").onclick = function(){
    // 			var res = verifyCode.validate(document.getElementById("code_input").value);
    // 			if(res){
    // 	        	alert("验证正确");
    // 			}else{
    // 	        	alert("验证码错误");
    // 			}
    // 		}
</script>
</body>
</html>