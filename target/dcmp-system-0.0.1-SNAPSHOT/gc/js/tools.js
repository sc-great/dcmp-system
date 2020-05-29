/*
 * 点击后弹出HTML
 * @parameter pos,url
 * pos是传入的经度、纬度、程高，类型为Array
 * url是传入的要打开的url地址，类型为String
 */
function showWegdit(pos) {
    var pos = pos.split(",");
    var pOption = map.CreateResponserOptions("123");
    pOption.AddConfig("Longitude", pos[0]);
    pOption.AddConfig("Latitude", pos[1]);
    pOption.AddConfig("PosHeight", pos[2]);
    pOption.AddConfig("ShowDistance", "300");
    //	pOption.AddConfig("Longitude", 120.138280952);
    //	pOption.AddConfig("Latitude", 30.2689942874);
    //	pOption.AddConfig("PosHeight", 19.8248816999);
    pOption.AddConfig("Widget", "400");
    pOption.AddConfig("Height", "180");
    //				pOption.AddConfig("ArrowSize", "10");
    pOption.AddConfig("Radial", "20");
    //	pOption.AddConfig("Url", url);
    pOption.AddConfig("Url", "http://www.baidu.com");
    pOption.AddConfig("MoveDelay", "1");

    pOption.AddConfig("CloseButtonState", "true");
    pOption.AddConfig("CloseButtonUrl", "F:\\3DGIS\\HK展厅2.3\\SDK\\data\\texture\\close.bmp");

    pOption.AddConfig("CloseBtnPosX", "365");
    pOption.AddConfig("CloseBtnPosY", "10");
    pOption.AddConfig("CloseBtnPosW", "20");
    pOption.AddConfig("CloseBtnPosH", "20");

    var webResp = map.CreateResponser("TipsDialogResponser", pOption);
    webResp.AddObserver();
    map.AddResponser(webResp);
}

function removeWegdit() {
    map.RemoveResponser("TipsDialogResponser"); ///< 移除响应器
    webResp = null;
}

//在固定位置产生一个HTML框
function showWebDialog() {
    var opt = tools.CreateToolsOptions("web"); ///< 创建配置项
    opt.AddConfig("Url", "http://localhost:6666/3DGIS/123.html"); ///< 网页链接地址
    opt.AddConfig("Left", "10"); ///< 屏幕位置x
    opt.AddConfig("Top", "100"); ///< 屏幕位置y
    opt.AddConfig("Widget", "400"); ///< 页面宽度
    opt.AddConfig("Height", "300"); ///< 页面高度		
    opt.AddConfig("Alpha", "0.001"); ///< 窗口透明度
    webobject = tools.CreateToolsObject("WebInfoTool", opt); ///< 创建工具类型
    webobject.AddObserver();
    webobject.Active();
}

//隐藏HTML框
function hideWebDialog() {
    webobject.Deactive(); ///< 移除网页弹窗界面
}

//获取选择框中的索引值
function selectValue() {
    var index = $('#testSelect option:selected').attr("id"); //选中的值
    return index;
}

function addResAxis() { //右手坐标系建轴
    var resp = map.CreateResponserOptions("AxisEditResponser");
    resp.AddConfig("PickLayerIdList", polygoneditLayer.GetLayerID());
    resp.AddConfig("PickColor", "1.0,1.0,0,0.01"); //拾取颜色r g b a
    resp.AddConfig("IsChangeColor", "true"); //是否变色
    resp.AddConfig("XRotate", "XRotate"); //x轴关联字段（红轴）
    resp.AddConfig("YRotate", "YRotate"); //y轴关联字段（绿轴）
    resp.AddConfig("ZRotate", "ZRotate"); //z轴关联字段（蓝轴）
    resp.AddConfig("Adjustment", "0.1"); //微调部件步进值，默认为0.01
    resp.AddConfig("DragSpeed", "1"); //拖拽速度系数，默认为1
    resCross = map.CreateResponser("AxisEditResponser", resp); //坐标轴响应器，必须为AxisEditResponser
    resCross.AddObserver();
    map.AddResponser(resCross);
}

//移除Axis响应器
function removeResAxis() {
    map.RemoveResponser("AxisEditResponser"); //移除辅助坐标系响应器，必须为AxisEditResponser
    removeIconLayer();
    getResult();
}

//获取结果
function getResult() {
    var index = selectValue();
    var name = resCross.GetResponserResult().GetConfigValueByKey("name"); //获取经纬度坐标值
    var xstr = resCross.GetResponserResult().GetConfigValueByKey("OXAngle"); //获取绕x轴旋转弧度
    var ystr = resCross.GetResponserResult().GetConfigValueByKey("OYAngle"); //获取绕y轴旋转弧度
    var zstr = resCross.GetResponserResult().GetConfigValueByKey("OZAngle"); //获取绕z轴旋转弧度
    var pos = resCross.GetResponserResult().GetConfigValueByKey("LonLatPoint"); //获取经纬度坐标值
    pos = pos.slice(0, pos.length - 1);
    resource.push({
        "type": index,
        "name": name,
        "pos": pos,
        "XRotate": xstr,
        "YRotate": ystr,
        "ZRotate": zstr,
        "Azimuth": "0.0006109938871674577",
        "Pitch": "-0.7853911723224358",
        "Range": "10.078247432797"
    });
    newLoadCameraPicLayer(pos, name);
}

