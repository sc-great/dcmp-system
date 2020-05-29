//加载单个本地模型
function loadSingleModel() {
    var tlo = map.CreateLayerOptions("cpm"); // 创建cpm图层配置，给配置起个名称，任意名称
    tlo.AddConfig("LayerOptionsName", "ModelLayerOptions"); // 创建配置类型, ModelLayerOptions代表模型数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "cpm"); // 数据源类型,代表CPM插件，必须是此键值对
    tlo.AddConfig("Url", "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out1\\Lod1\\sceneName.L000_000.wrl"); /////要加载的数据路径，此数据需为CPM数据，不支持压缩数据
    //调度优先级 = priority * PriorityScale + PriorityOffset;
    //其中priority由vp根据PagedNode结点的范围(minExtent, maxExtent)、其距离视点的距离、LOD层级mLODScale计算得到
    //调度优先级越大，优先调度并显示
    // tlo.AddConfig("PriorityScale","1.0");		//结点调度优先级的缩放值PriorityScale,默认为1
    // tlo.AddConfig("PriorityOffset","1.0");		//结点调度优先级的偏移值PriorityOffset,
    // tlo.AddConfig("Compress","false");			//支持压缩和未压缩数据(根据数据实际情况设置)
    // tlo.AddConfig("RenderOrder", "-100"); 		//绘制顺序(越小越先绘制)
    modelLayer = map.CreateLayer("ModelLayer", tlo); //创建模型图层，第一项参数必须为ModelLayer
    map.AddLayer(modelLayer); ///添加模型图层
    layerID.push(modelLayer.getLayerId());
    cpmLayer.Locate(); ////模型图层定位
}

//加载多个cpm本地模型
function loadCpmModel() {
    mLayer = new _LayerGroup();
    var tlo = map.CreateLayerOptions("cpm");
    tlo.AddConfig("LayerOptionsName", "ModelLayerOptions");
    tlo.AddConfig("DataSourceTypeName", "cpm");

    for (var i in url) {
        tlo.AddConfig("Url", url[i].url);
        modelLayer = map.CreateLayer("ModelLayer", tlo);
        map.AddLayer(modelLayer);
        mLayer.addLayer(modelLayer, url[i].name);
        layerID.push(modelLayer.getLayerID());
        modelLayer.Locate();
    }
}

//加载多个c3s本地模型
function loadC3sModel() {
    mLayer = new _LayerGroup();
    var tlo = map.CreateLayerOptions("c3s"); // 创建cpm图层配置，给配置起个名称，任意名称
    tlo.AddConfig("LayerOptionsName", "ModelLayerOptions"); // 创建配置类型, ModelLayerOptions代表模型数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "c3s"); // 数据源类型,代表CPM插件，必须是此键值对

    for (var i in C3Surl) {
        tlo.AddConfig("Url", C3Surl[i].url);
        tlo.AddConfig("Compress", "false"); //支持压缩和未压缩数据(根据数据实际情况设置)
        modelLayer = map.CreateLayer("ModelLayer", tlo); //创建模型图层，第一项参数必须为ModelLayer
        map.AddLayer(modelLayer); //添加模型图层
        mLayer.addLayer(modelLayer, C3Surl[i].name);
        layerID.push(modelLayer.getLayerID());
        modelLayer.Locate(); //模型图层定位
    }
}

//通过网络加载c3s模型
function loadNetC3sModel() {
    var tlo = map.CreateLayerOptions("c3ss"); // 创建cpm图层配置，给配置起个名称，任意名称
    tlo.AddConfig("LayerOptionsName", "ModelLayerOptions"); // 创建配置类型, ModelLayerOptions代表模型数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "c3ss"); // 数据源类型,代表CPM插件，必须是此键值对
    tlo.AddConfig("Compress", "false"); //支持压缩和未压缩数据(根据数据实际情况设置)
//	tlo.AddConfig("Url", "http://172.16.12.166:8088/gj/lod_Compressed/gsOutPut/cssj/model/0.c3s.zip");
    tlo.AddConfig("Url", "http://" + serverIp + ":" + serverPort + "/Out1c3s/Lod1/sceneName.L000_000.c3s");
    modelLayer = map.CreateLayer("ModelLayer", tlo);
    map.AddLayer(modelLayer);
    layerID.push(modelLayer.getLayerId());
    modelLayer.Locate();
}

//加载本地倾斜摄影数据(sigma处理数据)
function loadSmesh() {
    var tlo = map.CreateLayerOptions("osgb"); // 创建osgb图层配置，给配置起个名称，任意名称
    tlo.AddConfig("LayerOptionsName", "ModelLayerOptions"); // 创建配置类型, ModelLayerOptions代表模型数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "smesh"); // 数据源类型,代表SMESH插件，必须是此键值对

    //////////////以下部分可以通过图层信息获取获得相应的配置////////////
    //加载sigma处理数据
    tlo.AddConfig("MaxRange", "1000000.0");
    tlo.AddConfig("MinRange", "0.0");
    tlo.AddConfig("Srs", "PROJCS[\"Transverse_Mercator\",GEOGCS[\"Geographic Coordinate System\",DATUM[\"WGS84\",SPHEROID[\"WGS84\",6378137,298.257223560493]],PRIMEM[\"Greenwich\",0],UNIT[\"degree\",0.0174532925199433]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"scale_factor\",1],PARAMETER[\"central_meridian\",120],PARAMETER[\"latitude_of_origin\",0],PARAMETER[\"false_easting\",500000],PARAMETER[\"false_northing\",0],UNIT[\"Meter\",1]]");
    tlo.AddConfig("Compress", "false");
    tlo.AddConfig("Url", DataPath + "\\倾斜摄影\\LAYER00\\PRIFIXION_L00_0.osgb");
    //	tlo.AddConfig("Url",DataPath+"\\倾斜摄影\\LAYER02\\0\\GROUP_PRIFIXION_L02_00.osgb");
    tlo.AddConfig("BasePath", DataPath + "\\倾斜摄影\\");
    //tlo.AddConfig("OriginPoint","513090,3343953,0");
    tlo.AddConfig("OriginPoint", "513095,3343953,-15.8");
    tlo.AddConfig("BuildSpatialIndex", "true");

    osgbLayer = map.CreateLayer("ModelLayer", tlo); ////创建倾斜摄影图层，第一项参数必须为ModelLayer
    map.AddLayer(osgbLayer); ///添加倾斜摄影图层
}

//删除模型图层
/*
 * @parameter layer
 * 传入要删除的模型对象，例如需要删除的是cpm模型，
 * 那么调用的时候则传入cpmLayer。
 */
function removeModel(layer) {
    map.RemoveLayer(layer);
}

//开启模型拾取响应器
function OpenPick() {
    console.log(layerID);
    var resp = map.CreateResponserOptions("123"); //创建响应器配置，参数任意名称
    resp.AddConfig("PickLayerIdList", layerID); //拾取图层id //c3sLayer.GetLayerID() 
    resp.AddConfig("PickColor", "1.0,0.0,0.0,0.5"); //拾取颜色
    resp.AddConfig("IsChangeColor", "true"); //是否变色
    resp.AddConfig("IsGetLayerID", "true"); //是否需要获取节点所在图层ID
    res = map.CreateResponser("PickModelResponser", resp); //创建模型拾取响应器，第一参必须为PickModelResponser字符串
    res.AddObserver(); //添加监听
    map.AddResponser(res); //添加响应器
    GetPickLayerAtt();
}

//关闭模型拾取响应器
function ClosePick() {
    map.RemoveResponser("PickModelResponser"); //移除响应器 
    res = null;
}

//
function GetPickLayerAtt() {
    var str = res.GetResponserResult().GetConfigValueByKey("PickPointList");
    var PickName = res.GetResponserResult().GetConfigValueByKey("PickName");
    PickLayerId = res.GetResponserResult().GetConfigValueByKey("PickLayerList");
    console.log("pickLayerid" + PickLayerId);
    //	alert("PickPoint:" + str + " PickName: " + PickName + " PickLayerId: " + PickLayerId);
    var pickLayerId = PickLayerId.split(",");
    for (var j in url) {
        for (var i in pickLayerId) {
            var name = url[pickLayerId[i]].name;
            console.log(name);
            mLayer.hide(name);
        }
    }
}