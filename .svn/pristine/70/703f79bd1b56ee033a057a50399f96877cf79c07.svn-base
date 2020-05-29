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
</head>
<body>
<form class="layui-form  layui-from-header" lay-filter="personneForm">
    <input type="hidden" name="PId" value="${param.PId }"/>
    <input type="hidden" id="PPic" name="PPic" value="">
    <div class="layui-row">
        <div class="layui-col-md4">
             <div class="layui-form-item">
                <label class="layui-form-label">人员名称</label>
                <div class="layui-input-inline">
                    <input name="PName" class="layui-input" type="text" lay-verify="required"
                           autocomplete="off">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话</label>
                <div class="layui-input-inline">
                    <input name="PPhone" class="layui-input" type="text"  lay-verify="required|phone"
                           autocomplete="off" maxlength="32" >
                </div>
            </div>

           <!-- <div class="layui-form-item">
                <label class="layui-form-label">健康状态</label>
                <div class="layui-input-inline">
                    <select name="status" id="status">
                        <option value="0">异常</option>
                        <option value="1" selected="selected">正常</option>
                        <select>
                </div>
            </div> -->
            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="idCardNo" name="idCardNo" lay-verify="required|identity"
                           type="text"  autocomplete="off">
                </div>
            </div>
          
           
           
        </div>
        <div class="layui-col-md4">
             <div class="layui-form-item">
                <label class="layui-form-label">组织机构</label>
                <div class="layui-input-inline">
                    <div class="layui-unselect layui-form-select downpanel">
                        <div class="layui-select-title">
                            <span class="layui-input layui-unselect" id="treeclass">机构选择</span>
                            <input type="hidden" id="orgId" name="orgId">
                            <input type="hidden" id="orgName" name="orgName">
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
                <label class="layui-form-label">性别</label>
                <div class="layui-input-inline">
                     <input name="PSex" title="男" type="radio" checked="" value="1" >
                    <input name="PSex" title="女" type="radio" value="0" >
                </div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">出生日期</label>
                <div class="layui-inline layui-search-date">
				<input class="layui-input" id="PBirth" name="PBirth" 
					type="text" placeholder="开始时间" autocomplete="off">
			</div>
            </div>
             <div class="layui-form-item">
                <label class="layui-form-label">人员编号</label>
                <div class="layui-input-inline">
                    <input name="userCode" id="userCode" class="layui-input" type="text"  lay-verify="required"
                           autocomplete="off">
                </div> 
            </div>
        </div>

           
        <div class="layui-col-md4">
              <div class="layui-form-item layui-lay-flex">
               <div class="layui-upload-drag" style="height: 195px;width: 300px; padding: 0px;" id="uploadPic">
                    <!-- 						<i class="layui-icon">&#xe67c;</i> -->
                    <p>暂无图片</p>
                </div>
               <!--  <div class="layui-upload-drag" id="uploadPic" name="uploadPic"
                     style="text-align: center; height: 195px;width: 300px; padding: 0px;">
                    <i class="layui-icon" style="position: relative; top: 50px;">&#xe67c;</i>
                    <p style="position: relative; top: 50px;">点击上传图片，或将文件拖拽到此处</p>
                </div> -->
            </div>
          
        </div>
        <div class="layui-col-md12">
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block" style="padding-left: 130px;padding-right: 60px;">
						<textarea name="description" class="layui-textarea" type="text"
                                  ></textarea>
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="margin-bottom: 0;">
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
    layui.use(['layer', 'form', 'upload', 'jquery'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var $ = layui.jquery;
        //表单初始赋值，通过编号获取人员数据初始化表单
        $.post("${path}/personnelFileInfo/getPersonneValueById.do", {PId: "${param.PId}"}, function (data) {
            if (data.success) {
                form.val('personneForm', {
                    "PName": data.data.PName,
                    "PPhone": data.data.PPhone,
                    "health": data.data.health,
                    "idCardNo": data.data.idCardNo,
                  	"userCode": data.data.userCode,	
                    "PBirth":dateFormat(data.data.PBirth,'yMd'),
                    "orgId": data.data.orgId,
                    "orgName": data.data.orgName,
                    "PSex": data.data.PSex,
                    "status": data.data.status,
                    "description": data.data.description,
                    "pPic":data.data.pPic
                });
                //图片判断，如果有路径，就显示
                if (data.data.userPic != "") {
                    $("#PPic").val(data.data.PPic);
                    $("#uploadPic").empty();
                    console.log($("#uploadPic"));
                    var image = $("<image width='100%' height='100%' src='" + data.data.PPic + "'/>");
                    $("#uploadPic").append(image);
                }
                //初始化默认选中值
               $("#treeclass").html(data.data.orgName);
                
            } else {
                top.layer.alert('详细信息初始化失败！！');
                return;
            }
        });
    });

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
	        $('*[lay-verify*="required"]').parent().prev('label').prepend('<span"></span>');
	        //用户生日时间格式化
	        laydate.render({
	            elem: '#PBirth'
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
	                    $("#PPic").val(res.job.url);
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
	                url: '${path }/personnelFileInfo/update.do',
	                method: 'post',
	                data: data.field,
	                dataType: 'JSON',
	                success: function (res) {
	                    if (res.success) {
	                        parent.selectAreaId = nodeId;
	                        parent.selectAreaName = nodeName;
	                        $("*[lay-submit]").addClass("layui-btn-disabled").attr("disabled", "disabled");
	                        var key = top.layer.alert("修改成功！", {
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
	                            top.layer.alert("修改失败！");
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
	    });
	  //日期范围
		var startDate = laydate
				.render({
					elem : '#startTime',
					type : 'datetime',
					max : "2099-12-31",//设置一个默认最大值
					done : function(value, date) {
						endDate.config.min = {
							year : date.year,
							month : date.month - 1, //关键
							date : date.date,
							hours : date.hours,
							minutes : date.minutes,
							seconds : date.seconds
						};
						var nowStrDate = date.year + "-"
								+ date.month + "-"
								+ date.date + " "
								+ date.hours + ":"
								+ date.minutes + ":"
								+ date.seconds;
						var endyear = endDate.config.dateTime.year;
						var endmonth = endDate.config.dateTime.month + 1;
						var enddate = endDate.config.dateTime.date;
						var endhours = endDate.config.dateTime.hours;
						var endminutes = endDate.config.dateTime.minutes;
						var endseconds = endDate.config.dateTime.seconds;
						var nowEndDate = endyear + "-"
								+ endmonth + "-" + enddate
								+ " " + endhours + ":"
								+ endminutes + ":"
								+ endseconds;
						nowEndDate = new Date(nowEndDate);
						nowStrDate = new Date(nowStrDate);
						if (nowEndDate < nowStrDate) {
							layer.msg('选择的结束时间小于开始时间了');
						}
					}
				});
</script>
<script type="text/javascript">
layui.use(['layer', 'form', 'tree', 'jquery'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    $('*[lay-verify*="required"]').parent().prev('label').prepend('<span style="color:red">*</span>&nbsp;&nbsp;');
    //表单提交方法
    form.on('submit(subimitFormm)', function (data) {
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
                $('ul li').remove();
                var layuiTree = layui.tree({
                    elem: '#orgTree', //指定元素
                    target: '_blank', //是否新选项卡打开（比如节点返回href才有效）
                    click: function (node) { //点击节点回调
                        var $select = $($(this)[0].elem).parents(".layui-form-select");
                        $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end();//.find("input:hidden[name='selectID']").val(node.id);
                        $("#orgId").val(node.id);
                        $("#orgName").val(node.name);
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