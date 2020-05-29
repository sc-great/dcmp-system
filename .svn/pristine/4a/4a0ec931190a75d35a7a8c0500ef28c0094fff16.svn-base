<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common_taglib.jsp"%>
<%@ include file="/common/common_css.jsp"%>
<%@ include file="/common/common_js.jsp"%>
<!DOCTYPE html>
<html class="iframe-h">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title></title>
<link rel="stylesheet" type="text/css" href="${path }/css/list.css" />
<link rel="stylesheet" type="text/css" href="${path }/css/admin.css" />
</head>
<body>
	<div class="serch-bar">
		<div class="search-input">
			策略名称：
			<div class="layui-inline layui-search-input">
				<input name="tName" class="layui-input" id="tName"
					autocomplete="off">
			</div>
			
			<button class="layui-btn layui-btn-orange layui-btn-search" data-type="reload">搜索</button>
		</div>
		<span class="layui-toolbar">
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="add"><div class="layui-btn-toolbar-add"></div>添加</button>
			<button class="layui-btn layui-btn-primary layui-btn-tool-add" data-type="delALL"><div class="layui-btn-toolbar-del"></div>删除</button>
		</span>
	</div>

	<table id="data" lay-filter="dataList"></table>
	<script id="toolBar" type="text/html">
  <a class="layui-btn layui-btn-primary layui-btn-tool-check" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-primary layui-btn-tool-edit" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-primary layui-btn-tool-del" lay-event="del">删除</a>
</script>
	<!-- 创建时间数据格式化 -->
	<script type="text/html" id="createTime">  
  {{ dateFormat(d.createTime,'yMdhms') }} 
</script>
	<script src="${path }/plugin/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script type="text/javascript">
		layui.use([ 'table', 'form', 'jquery' ], function() {
			var table = layui.table;
			var form=layui.form;
			var $ = layui.jquery;
			var layerIndex;
			var layerInitWidth;
			var layerInitHeight;
			$(window).resize(function() {
			    resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
			});
			var currpage = $('.layui-laypage-skip').children('.layui-input').val();
			//表格数据初始化
			table.render({
				elem : '#data',
				height : 'full-120',
				url : '${path}/tactics/getPage.do?areaId='+'${param.areaId }',
				cellMinWidth : 80,
				cols : [ [ //标题栏
					{
						type : 'checkbox',
						LAY_CHECKED : false
					}, /* {
						field : 'area',
						title : '所属区域',
						templet:function(d){
							return d.area===""?"无":d.area.areaName;
						}
					}, */{
						field : 'tName',
						title : '策略名称'
					}, {
						field : 'createBy',
						title : '创建人'
					},  {
						field : 'createTime',
						title : '创建时间',
						templet : "#createTime"
					},   {
						fixed : 'right',
						title : '操作',
						toolbar : '#toolBar',
						width : 250
					} ] ],
				skin : 'line', //line row表格风格
				even : true, //,size: 'lg' //尺寸
				page : true, //是否显示分页,
				limits : [ 10, 15 ],
				limit : 10
			});
			//监听工具条
			table.on('tool(dataList)', function(obj) {
				var data = obj.data;
				if (obj.event === 'detail') {
					top.layer.open({
						type : 2,
						title : '策略信息查看',
						area : [ '1400px', '850px' ],
						fixed : false, //不固定
						maxmin : false,
						shade: [0.3],
						anim: 5,
						isOutAnim: true,
						resize: false,
						content : '${path}/jsp/tactics/tactics_display.jsp?tId=' + data.tId,
						success: function(layero, index) {
							layerIndex      = index;
			                layerInitWidth  = $("#layui-layer"+layerIndex).width();
			                layerInitHeight = $("#layui-layer"+layerIndex).height();
			                resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
						},
						end : function() {
// 							active.reload();
							table.reload('data',{page:{curr:currpage}});
						}
					});
				} else if (obj.event === 'edit') {
					top.layer.open({
						type : 2,
						title : '策略信息修改',
						area : [ '1400px', '850px' ],
						fixed : false, //不固定
						maxmin : false,
						shade: [0.3],
						anim: 5,
						isOutAnim: true,
						resize: false,
						content : '${path}/jsp/tactics/tactics_update.jsp?tId=' + data.tId,
						success: function(layero, index) {
							layerIndex      = index;
			                layerInitWidth  = $("#layui-layer"+layerIndex).width();
			                layerInitHeight = $("#layui-layer"+layerIndex).height();
			                resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
						},
						end : function() {
							table.reload('data',{page:{curr:currpage}});
						}
					});
				}else if(obj.event==='del'){
					let id=[data.tId];
					//console.log(id);
					top.layer.confirm('确定要删除选中的数据吗？', {
						btn : [ '确定', '取消' ]
					}, function() {
						var actionUrl = "${path}/tactics/del.do";
						var params = {
							ids : id
						};
						$.post(actionUrl, params, function(data) {
							if (data.success) {
								top.layer.alert('删除成功！！');
							//	active.reload();
								table.reload('data',{page:{curr:currpage}});
							} else {
								top.layer.alert('删除失败！！');
								return;
							}
						});
					});
					
				}
			});
			var $ = layui.jquery,
				active = {
					//策略信息添加
					add : function() {
						top.layer.open({
							type : 2,
							title : '策略信息添加',
							area : [ '1400px', '800px' ],
							fixed : false, //不固定
							maxmin : false,
							shade: [0.3],
							anim: 5,
							isOutAnim: true,
							resize: false,
							content : '${path}/jsp/tactics/tactics_add.jsp?areaId=${param.areaId}&areaName=${param.areaName}',
							success: function(layero, index) {
								$(':focus').blur();
								layerIndex      = index;
				                layerInitWidth  = $("#layui-layer"+layerIndex).width();
				                layerInitHeight = $("#layui-layer"+layerIndex).height();
				                resizeLayer($, layerIndex,layerInitWidth,layerInitHeight);
							},
							end : function() {
							//	active.reload();
								table.reload('data',{page:{curr:currpage}});
							}
						});
					},
					//策略信息批量删除
					delALL : function() {
						var ids = active.getCheckData();
						if (ids.length == 0) {
							top.layer.alert('请选择需要删除的数据！！');
						} else {
							top.layer.confirm('确定要删除选中的所有数据吗？', {
								btn : [ '确定', '取消' ]
							}, function() {
								var actionUrl = "${path}/tactics/del.do";
								var params = {
									ids : ids
								};
								$.post(actionUrl, params, function(data) {
									if (data.success) {
										top.layer.alert('删除成功！！');
										active.reload();
									} else {
										top.layer.alert('删除失败！！');
										return;
									}
								});
							});
						}
					},
					//刷新表格数据重新请求
					reload : function() {
						//执行重载
						table.reload('data', {
							page : {
								curr : 1
							},
							//查询条件参数
							where : {
								tName : $("#tName").val(),
							}
						});
					},
					//获取被选中的表格数据ID集合
					getCheckData : function() {
						var data = table.checkStatus('data').data;
						var ids = [];
						for (var i = 0; i < data.length; i++) {
							ids.push(data[i].tId);
						}
						return ids;
					}
				};
			$('i').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
// 			$('#serverName').on('input', function() {
// 				active.reload();
// 			});

			//下拉菜单数据格式 [{name:"",id:""}]
		/* 	var url = '${path}/dic/getDicValue.do?dicCode=serverType';
			selectUtil($, url, "serverType", "", function() {
				form.render();
			}); */
			$('.layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
</body>
</html>