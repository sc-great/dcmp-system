//转到预设的坐标或者视角(漫游)
function fly(postioninfo) {
    if (postioninfo != "") {
        //定义一数组
        var infos = new Array();
        //字符分割
        infos = postioninfo.split(":");
        //转为double
        for (i = 0; i < infos.length; i++) {
            infos[i] = parseFloat(infos[i]).toFixed(10);
        }
        //3维坐标点
        var pointPos = map.CreatePosition(infos[1], infos[2], infos[3]);
        //飞行
        navigation.FlyToDest(pointPos, infos[4], infos[5], infos[6], 1);
    }
}

//画巡更路线
function draw2DPolyline() {
    var tlo = map.CreateLayerOptions("polygon"); // 创建分析图层配置，给配置起个名称，任意名称
    tlo.AddConfig("LayerOptionsName", "AnalysisLayerOptions"); // 创建配置类型, AnalysisLayerOptions代表分析图层数据配置，必须是此键值对
    tlo.AddConfig("PointColor", "0.0,0.0,1.0,1.0"); //设置点击点的颜色透明度（RGBA） 1为不透明 0为透明
    tlo.AddConfig("PointSize", "5"); //设置点击点的大小
    //				tlo.AddConfig("Points", responseStr);
    tlo.AddConfig("DrawLineColor", "1.0,1.0,0.0,1.0"); //设置线的颜色（RGBA）
    tlo.AddConfig("VisiableLine", "true"); //是否绘制线框（顶点和线框是同时显示/隐藏的）
    tlo.AddConfig("DrawType", "3"); /////0 是矩形  1是圆   2是多边形   3是线 
    tlo.AddConfig("LiftUp", "2"); /////抬高高度
    tlo.AddConfig("DataSourceTypeName", "as_draw2dobject"); /////// 数据源类型,代表2D对象，必须是此键值对
    pathLayer = map.CreateLayer("AnalysisLayer", tlo); ////创建分析图层，第一项参数必须为AnalysisLayer
    pathLayer.addObserver(); //添加监听接口
    map.AddLayer(pathLayer); ///添加分析图层
    //				click();
    //				OpenState = true;
    addPoints = true;
    layermap[pathLayer.getlayerid()] = pathLayer;
}

function deleteLine() {
    for (var i in layermap) {
        removeLayer(layermap[i]);
    }
    paths = "";
    path = "";
}

//修改巡更路径
function updatePath() {
    var newPath = path.split(";", -1);
    newPath.pop();
    removeLayer(pathLayer);
    path = newPath.join(";");
    //	showPath(path);
}

//巡更路径封装
//function path(pointLeft) {
//	console.log(pointLeft);
//	for(var i = 0; i <= pointLeft.length; i++) {
//		if(i === 0) {
//			path = pointLeft[0];
//		} else {
//			path = path + pointLeft[i - 1];
//		}
//	}
//}

function SetVisibleRoamPathFalse() {
    pathLayer.SetVisible(false);
}

//巡更
function keepWatch() {
//	SetVisibleRoamPathFalse();
    if (paths == null && path != null) {
        var newPath = path;
    } else if (paths != null && path != null) {
        var newPath = paths + path;
    } else if (paths != null && path == null) {
        var newPath = paths;
    } else {
        var newPath = "";
    }
    console.log("newPath" + newPath);
    var tlo = map.CreateLayerOptions("dynamicpathlayer");
    tlo.AddConfig("LayerOptionsName", "DynamicPathLayerOptions"); /////动态路径配置信息 必须为DynamicPathLayerOptions
    tlo.AddConfig("PlayerMode", "PLAYER_ONEWAY");
    tlo.AddConfig("PlayerState", "PLAYER_PLAY");
    tlo.AddConfig("ViewObjectMode", "0,-0.5,4");

    tlo.AddConfig("KeyPoints", newPath);
    tlo.AddConfig("Velocity", "5.0"); /////m/s
    tlo.AddConfig("LineWidth", "2.0");
    tlo.AddConfig("LineStipple", "65535");
    //								tlo.AddConfig("LineColor", "0.0,1.0,0.0,0.0");
    tlo.AddConfig("CornerSpeedScale", "5"); ///转角速度比例(0-1之间)

    //				tlo.AddConfig("InterpLineColor", "1.0,0.0,0.0,1.0");
    //								tlo.AddConfig("InterpLineWidth", "2.0"); ///差值线 线宽
    //								tlo.AddConfig("InterpLineStipple", "65535"); ///差值线线样式
    //								tlo.AddConfig("InterpLineColor", "0.0,0.0,1.0,1.0"); //差值线颜色,不需要差值线去掉颜色
    tlo.AddConfig("LiftUp", "5"); //抬高
    tlo.AddConfig("Radius", "20");

    tlo.AddConfig("NodeActive", "true");

    pathLayer = map.CreateLayer("DynamicPathLayer", tlo);
    map.AddLayer(pathLayer);
    layermap[pathLayer.GetLayerID()] = pathLayer;
    pathLayer.AddObserver();
    //
    //				OpenState = true;
    //				GetCoordinate();
    //				fly(postioninfo);
}

function outKeepWatch() {
    //	updatePath();
    //	SetVisibleRoamPathFalse();
    //				if(pathLayer != null) {
    //					map.RemoveLayer(pathLayer);
    //				}s
    //				Path(pointLeft);
    //				var tempH = 14.5;
    //				var path = "120.215522475,30.2108558335,25.0946463626;120.215202523,30.2107136476,25.0938017908;120.21537284,30.2110130871,25.0940024946;120.215002536,30.2111010471,25.0928537585;120.215134925,30.2114230578,25.0931203756;120.214756804,30.211502514,25.0921494663;";
    var tlo = map.CreateLayerOptions("dynamicpathlayer");
    tlo.AddConfig("LayerOptionsName", "DynamicPathLayerOptions"); /////动态路径配置信息 必须为DynamicPathLayerOptions
    tlo.AddConfig("PlayerMode", "PLAYER_ONEWAY");
    tlo.AddConfig("PlayerState", "PLAYER_PLAY");
    tlo.AddConfig("ViewObjectMode", "0,50,20");

    tlo.AddConfig("KeyPoints", val);
    tlo.AddConfig("Velocity", "5.0"); /////m/s
    tlo.AddConfig("LineWidth", "2.0");
    tlo.AddConfig("LineStipple", "65535");
    //								tlo.AddConfig("LineColor", "0.0,1.0,0.0,0.0");
    tlo.AddConfig("CornerSpeedScale", "5"); ///转角速度比例(0-1之间)
    tlo.AddConfig("Url", "F:\\3DGIS\\HK展厅2.3\\SDK\\data\\model\\primary\\v_body.wrl.001.wrl");

    //				tlo.AddConfig("InterpLineColor", "1.0,0.0,0.0,1.0");
    //								tlo.AddConfig("InterpLineWidth", "2.0"); ///差值线 线宽
    //								tlo.AddConfig("InterpLineStipple", "65535"); ///差值线线样式
    //								tlo.AddConfig("InterpLineColor", "0.0,0.0,1.0,1.0"); //差值线颜色,不需要差值线去掉颜色
    tlo.AddConfig("LiftUp", "0"); //抬高
    tlo.AddConfig("Radius", "20");

    tlo.AddConfig("NodeActive", "true");

    pathLayer = map.CreateLayer("DynamicPathLayer", tlo);
    map.AddLayer(pathLayer);
    layermap[pathLayer.GetLayerID()] = pathLayer;
    pathLayer.AddObserver();
    //
    //				OpenState = true;
    //				GetCoordinate();
    //				fly(postioninfo);
}

//显示巡更路线
function showPath(paths) {
    var tlo = map.CreateLayerOptions("dynamicpathlayer");
    tlo.AddConfig("LayerOptionsName", "DynamicPathLayerOptions"); /////动态路径配置信息 必须为DynamicPathLayerOptions
    //				tlo.AddConfig("PlayerMode", "PLAYER_ONEWAY");
    //				tlo.AddConfig("PlayerState", "PLAYER_PLAY");
    tlo.AddConfig("ViewObjectMode", "0,-0.2,1");

    tlo.AddConfig("KeyPoints", paths);
    //				tlo.AddConfig("Velocity", "5.0"); /////m/s
    tlo.AddConfig("LineWidth", "2.0");
    tlo.AddConfig("LineStipple", "65535");
    //				tlo.AddConfig("CornerSpeedScale", "5"); ///转角速度比例(0-1之间)

    tlo.AddConfig("InterpLineColor", "1.0,0.0,0.0,1.0");
    tlo.AddConfig("InterpLineWidth", "2.0"); ///差值线 线宽
    tlo.AddConfig("InterpLineStipple", "65535"); ///差值线线样式
    tlo.AddConfig("InterpLineColor", "0.0,0.0,1.0,1.0"); //差值线颜色,不需要差值线去掉颜色
    tlo.AddConfig("LiftUp", "1.5"); //抬高
    //				tlo.AddConfig("Radius", "0.6");

    //				tlo.AddConfig("NodeActive", "true");

    pathLayer = map.CreateLayer("DynamicPathLayer", tlo);
    map.AddLayer(pathLayer);
    layermap[pathLayer.GetLayerID()] = pathLayer;
    pathLayer.AddObserver();
}