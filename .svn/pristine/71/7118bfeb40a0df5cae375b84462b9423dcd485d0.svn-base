<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/common_taglib.jsp" %>
<%@ include file="/common/common_css.jsp" %>
<%@ include file="/common/common_js.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <title></title>
    <script type="text/javascript" src="${path}/js/chart/jquery-1.9.1.js"></script>
    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        p {
            font-size: 20px;
            height: 24px;
        }

        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }

        p {
            font-size: 20px;
            height: 24px;
        }

        .layui-card-header {
            text-align: center;
            font-size: 20px;
        }

        .layui-carousel-ind {
            position: absolute;
            top: -41px;
            text-align: right;
        }

        .layui-carousel-ind > ul {
            background-color: white;
        }

        .layui-carousel-ind > ul:hover {
            background-color: white;
        }

        .layui-carousel-ind li {
            background-color: #e2e2e2;
        }

        .layui-carousel-ind li.layui-this {
            background-color: #999;
        }

        .layui-carousel {
            height: 88% !important;
        }

        #main {
            position: relative;
            overflow: hidden;
            height: 300px;
            width: 1000px;
        }

        .layui-card-body-node {
            text-align: center;
            font-size: 22px;
            padding-top: 14px;
            padding-bottom: 14px;
        }

        .layui-text-color-red {
            color: red;
        }

        .layui-text-color-green {
            color: green;
        }

        .layui-icon {
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15" style="padding: 15px;">
        <div class="layui-col-md3">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当前档案人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-green" id="personNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当日检测人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-red" id="detectionNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当日来访人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-red" id="visitorNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当日检测体温正常人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-red" id="tempNotAlarmNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当日未佩戴口罩人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-red" id="maskAlarmNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">当日体温异常人数</div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-red" id="tempAlarmNum">0</div>
                    </div>
                </div>
            </div>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header"></div>
                        <div class="layui-card-body layui-card-body-node layui-text-color-green">
                          
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md9">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md6" style="height:50%">
                    <iframe id="dataFrame" frameborder="0"
                            scrolling="no" style="width: 100%;height:100%"
                            src="jsp/welcome/maskHealth.jsp"></iframe>
                </div>
                <div class="layui-col-md6" style="height:50%">
                    <iframe id="dataFrame" frameborder="0"
                            scrolling="no" style="width: 100%;height:100%"
                            src="jsp/welcome/weekWearing.jsp"></iframe>
                </div>
                <div class="layui-col-md12" style="height:50%">
                    <iframe id="dataFrame" frameborder="0"
                            scrolling="no" style="width: 100%;height:100%"
                            src="jsp/welcome/alarmPicList.jsp"></iframe>
                </div>
                
            </div>
        </div>
    </div>
</div>
<script src="${path}/plugin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function() {
	loadData();
})
setInterval("loadData()", 30000); 
function loadData() {
	var url = "${path}/api/report/count";
	$.post(url, function (data) {
		$("#personNum").html(data.personNum);
		$("#detectionNum").html(data.detectionNum);
		$("#dvisitorNum").html(data.dvisitorNum);
		$("#tempAlarmNum").html(data.tempAlarmNum);
		$("#tempNotAlarmNum").html(data.tempNotAlarmNum);
		$("#maskAlarmNum").html(data.maskAlarmNum);
	}, "json");
}
</script>
</body>
</html>