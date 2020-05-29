//初始化场景
function InitScene() {
    obj = document.getElementById("VPSDKCtrl");
    map = VPSDKCtrl.GetIMapMgrPtr();
    transformate = map.CreateTransformation();
    navigation = map.CreateNavigation();
    tools = VPSDKCtrl.GetIToolsCOMPtr();

    //	setTimeout(function() {

    //		showWebDialog();
    //		LoadSingleModel();
    //		loadCpmModel();
    loadNetC3sModel();
    //		loadC3sModel();
    //		pushReource();
    loadPointAndModel();
    //		LoadCameraPicLayer();
    //		pick();

//		getSession();
//		//		LoadSmesh();
//		//		LoadDynamicPathLayer();
//
//		//矢量图层添加
//		//		addResourceModel();
//
//		//		vLayer[0].layerObj.SetVisible(false);
//		//添加文字图层
//		//		addTextLayer();
//		//飞到指定地点
//		fly("Lon:120.137956946;Lat:30.269450193;Height:13.6327019194;Azimuth:0;Pitch:-0.7853977602425692;Range:191.0424857083564;");
//	}, 300);

}

//获取服务器IP和Port
function getServerInfo(serverIp, serverPort) {
    GisServerIp = serverIp;
    GisServerPort = serverPort;
}

//强制刷新网页和SDK(慎用)
function refresh() {
    refresh = VPSDKCtrl.RefreshCtrl();
//	location.reload();
}

//获取页面session
function getSession() {
    var session = sessionStorage.getItem("test");
    $("#" + session).click();
}