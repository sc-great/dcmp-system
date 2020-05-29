//向Resource增加新的模型
function pushReource(newStr) {
    var newStr = new Array();
    for (var i in resource) {
        var point = resource[i].pos.split(",");
        for (var j in point) {
            Npoint = point[j].split(".");
            for (var k in Npoint) {
                intPoint = Number(Npoint[k]);
                if (k % 2 != 0) {
                    intPoint += 100000;
                    newStr.push(intPoint);
                } else {
                    newStr.push(intPoint);
                }
            }

        }
        resource.push({
            "type": 3,
            "name": "汽车",
            "pos": newStr[i * 5] + "." + newStr[i * 5 + 1] + "," + newStr[i * 5 + 2] + "." + newStr[i * 5 + 3] + "," + newStr[i * 5 + 4]
        });
    }
}

var state = false;

//
function pick() {
    if (state) {
        map.RemoveResponser("PickVectorResponser");
        state = false;
    } else {
        var resp = map.CreateResponserOptions("123");
        resp.AddConfig("PickLayerIdList", layerID);
        resp.AddConfig("PickColor", "1.0,1.0,0.0,0.8");
        resp.AddConfig("IsChangeColor", "true");
        pickResponser = map.CreateResponser("PickVectorResponser", resp);
        pickResponser.AddObserver();
        map.AddResponser(pickResponser);
        state = true;
    }
}

//模型符号化加载
function loadPointAndModel() {
    //	removeModel(cameraLayer);
    //	console.log(resource);
    for (var i in resource) {
        for (var j in vLayer) {
            var Npoint;
            var intPoint;
            var point = resource[i].pos.split(",");
            var name = resource[i].name;
            var model = null;
            var addFeature = map.CreateFeature();
            if (resource[i].type == vLayer[j].type) {
                model = vLayer[j].model;
                var tSymbol = map.CreateSymbol("ModelSymbol"); /// 创建类型为ModelSymbol的符号，必须为ModelSymbol字符串 
                tSymbol.AddConfig("Heading", resource[i].ZRotate); /// 绕Z轴(世界坐标系方向相同 far->near)旋转,从far(-Z)向near(+Z)看去,逆时针为正，弧度表示
                tSymbol.AddConfig("Pitch", resource[i].YRotate); /// 绕Y轴(世界坐标系方向相同 down(-Y)向up(Y))旋转,从down(-Y)向up(+Y)看去,逆时针为正，弧度表示
                tSymbol.AddConfig("Roll", resource[i].XRotate); /// 绕X轴(世界坐标系方向相同 left->right)旋转,从left(-X)向right(+X)看去,逆时针为正，弧度表示
                tSymbol.AddConfig("IsLocalRotation", "true"); ///进行编辑时需要设置为true
                tSymbol.AddConfig("XScale", "1"); /// 模型x方向放大比例
                tSymbol.AddConfig("YScale", "1"); /// 模型y方向放大比例
                tSymbol.AddConfig("ZScale", "1"); /// 模型z方向放大比例
                tSymbol.AddConfig("ModelOriginDir", "0,0,1"); /// 模型原始轴向

                tSymbol.AddConfig("Url", "http://172.16.12.166:8088/123/" + model); /// 模型资源路径
                tSymbol.AddConfig("LibraryName", "reslib"); /// 资源名称

                var res = map.CreateResource("ModelSymbol"); /// 创建模型资源，此处必须为ModelSymbol
                res.AddConfig("Uri", "http://172.16.12.166:8088/123/" + model); /// 模型资源路径
                var reslib = map.CreateResourceLibrary("reslib"); /// 创建资源库，名称和图层配置LibraryName设置的名称对应
                reslib.AddResource(res); /// 将资源添加至资源库

                var pStyle = map.CreateStyle("Point"); /// 创建名称为Point的样式，名称任意
                pStyle.SetName("point"); /// 设置别名point
                //pStyle.AddSymbol("PointSymbol", pSymbol.GetConfig());									/// 将点符号配置添加到该样式
                pStyle.AddFilterName("BuildGeometryFilter"); /// 设置构建器符号为BuildGeometryFilter，必须为BuildGeometryFilter字符串
                pStyle.AddSymbol("ModelSymbol", tSymbol.GetConfig()); /// 将符号配置添加到该样式，第一参必须为ModelSymbol字符串
                pStyle.AddFilterName("SubstituteModelFilter"); /// 设置构建器符号为SubstituteModelFilter，必须为SubstituteModelFilter字符串，此为图标符号化和模型符号化共有

                var styleSheet = map.CreateStyleSheet(); /// 创建样式表
                styleSheet.AddStyle(pStyle.GetConfig()); /// 将样式配置添加至样式表
                styleSheet.AddResLib(reslib.GetConfig()); /// 将资源库添加至样式表

                var tlo = map.CreateLayerOptions("shp"); /// 创建图层配置对象
                tlo.AddConfig("LayerOptionsName", "FeatureModelLayerOptions"); /// 创建配置类型, FeatureModelLayerOptions代表矢量数据配置，必须是此键值对
                tlo.AddConfig("DataSourceTypeName", "fmgeom"); /// 数据源类型,代表fmgeom插件，必须是此键值对
                tlo.AddConfig("Driver", "ESRI Shapefile"); /// 数据驱动，针对shp、dxf数据源必须是ESRI Shapefile
                //tlo.AddConfig("Url", "");																/// 初次创建需选择没有数据的目录，其在保存后会自动生成。当前设置的路径为不存在
                tlo.AddConfig("FeatureSourceType", "ogr"); /// 要素数据源类型，针对shp、dxf数据源必须是ogr
                tlo.AddConfig("GeometryType", "Point"); /// 几何类型     Point为点 Polyline为线 Polygon为面 此项配置不能少或字符串一定不能错误，否则保存文件不成功
                tlo.AddConfig("TileSizeFactor", "1.0"); /// 瓦片大小的影响因子，建议是1.0
                tlo.AddConfig("TileSize", "300000"); /// 瓦片大小，根据数据实际情况设置，根据数据面积来，面积越大值越大
                tlo.AddConfig("LiftUp", "0"); /// 抬升高度，任意值
                tlo.AddConfig("MaxRange", "1000000.0"); /// 最大显示范围，大于最小显示范围-无穷大
                tlo.AddConfig("MinRange", "0.0"); /// 最小显示范围，0-无穷大
                tlo.AddConfig("Fields", "Name:String:100:0,Height:Double:100:3,Width:Float:100:3,ID:String:100:0,XRotate:Double:100:3,YRotate:Double:100:3,ZRotate:Double:100:3,Type:String:100:3");
                tlo.AddConfig("StyleSheet", styleSheet.GetConfig()); /// 将样式表配置添加至图层配置对象，第一参必须为StyleSheet字符串
                cameraLayer = map.CreateLayer("FeatureModelLayer", tlo);
                layerID.push(cameraLayer.GetLayerID());
                map.AddLayer(cameraLayer);
                polygoneditLayer = map.GetFeatureModelLayer(cameraLayer.GetLayerID()); /// 获取矢量图层

                //					for(var u = 0; u < 3000; u++) {

                addFeature.SetGeometryType(1); /// 设置要素几何类型(1:点; 2:线; 3:环; 4:面; 5:多结构)
                addFeature.SetComponentType(1); /// 创建子几何类型（当GeometryType为5时生效）
                addFeature.AddAttribute("Height", "100", 4); /// 添加属性值(1:int; 2:long; 3:float; 4:double; 5:string; 6:bool)
                addFeature.AddAttribute("Name", name, 5); /// 添加属性值
                addFeature.AddAttribute("Type", "model", 5); /// 添加属性值
                addFeature.AddAttribute("Width", "100", 3); /// 添加属性值
                //						addFeature1.AddPoint(point[0] * 1 + 0.0001 * u, point[1], point[2]);
                addFeature.addPoint(point[0], point[1], point[2]);
                var featureId = polygoneditLayer.GetMaxFeatureID(); /// 获取矢量图层要素最大ID
                addFeature.SetFeatureId(featureId + 1); /// 设置FeatureID
                polygoneditLayer.AddFeature(addFeature);
                //				layerID.push(polygoneditLayer.GetLayerID());
                loadCameraPicLayer();
            }
        }
    }
}

function blockModel() {
    var tSymbol = map.CreateSymbol("ModelSymbol"); /// 创建类型为ModelSymbol的符号，必须为ModelSymbol字符串 
    tSymbol.AddConfig("Heading", "[ZRotate]"); ////绕Z轴(世界坐标系方向相同 far->near)旋转,从far(-Z)向near(+Z)看去,逆时针为正，弧度表示
    tSymbol.AddConfig("Pitch", "[YRotate]"); //////绕Y轴(世界坐标系方向相同 down(-Y)向up(Y))旋转,从down(-Y)向up(+Y)看去,逆时针为正，弧度表示
    tSymbol.AddConfig("Roll", "[XRotate]"); ////绕X轴(世界坐标系方向相同 left->right)旋转,从left(-X)向right(+X)看去,逆时针为正，弧度表示
    tSymbol.AddConfig("IsLocalRotation", "true"); ///进行编辑时需要设置为true

    tSymbol.AddConfig("XScale", "1"); /// 模型x方向放大比例
    tSymbol.AddConfig("YScale", "1"); /// 模型y方向放大比例
    tSymbol.AddConfig("ZScale", "1"); /// 模型z方向放大比例
    tSymbol.AddConfig("ModelOriginDir", "0,0,1"); /// 模型原始轴向
    tSymbol.AddConfig("Url", "http://172.16.12.166:8088/123/" + model); /// 模型资源路径
    tSymbol.AddConfig("LibraryName", "reslib"); /// 资源名称

    var res = map.CreateResource("ModelSymbol"); /// 创建模型资源，此处必须为ModelSymbol
    res.AddConfig("Uri", "http://172.16.12.166:8088/123/" + model); /// 模型资源路径
    var reslib = map.CreateResourceLibrary("reslib"); /// 创建资源库，名称和图层配置LibraryName设置的名称对应
    reslib.AddResource(res); /// 将资源添加至资源库

    var pStyle = map.CreateStyle("Point"); /// 创建名称为Point的样式，名称任意
    pStyle.SetName("point"); /// 设置别名point
    //pStyle.AddSymbol("PointSymbol", pSymbol.GetConfig());									/// 将点符号配置添加到该样式
    pStyle.AddFilterName("BuildGeometryFilter"); /// 设置构建器符号为BuildGeometryFilter，必须为BuildGeometryFilter字符串
    pStyle.AddSymbol("ModelSymbol", tSymbol.GetConfig()); /// 将符号配置添加到该样式，第一参必须为ModelSymbol字符串
    pStyle.AddFilterName("SubstituteModelFilter"); /// 设置构建器符号为SubstituteModelFilter，必须为SubstituteModelFilter字符串，此为图标符号化和模型符号化共有

    var styleSheet = map.CreateStyleSheet(); /// 创建样式表
    styleSheet.AddStyle(pStyle.GetConfig()); /// 将样式配置添加至样式表
    styleSheet.AddResLib(reslib.GetConfig()); /// 将资源库添加至样式表

    var tlo = map.CreateLayerOptions("shp"); /// 创建图层配置对象
    tlo.AddConfig("LayerOptionsName", "FeatureModelLayerOptions"); /// 创建配置类型, FeatureModelLayerOptions代表矢量数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "fmgeom"); /// 数据源类型,代表fmgeom插件，必须是此键值对
    tlo.AddConfig("Driver", "ESRI Shapefile"); /// 数据驱动，针对shp、dxf数据源必须是ESRI Shapefile
    //tlo.AddConfig("Url", "");																/// 初次创建需选择没有数据的目录，其在保存后会自动生成。当前设置的路径为不存在
    tlo.AddConfig("FeatureSourceType", "ogr"); /// 要素数据源类型，针对shp、dxf数据源必须是ogr
    tlo.AddConfig("GeometryType", "Point"); /// 几何类型     Point为点 Polyline为线 Polygon为面 此项配置不能少或字符串一定不能错误，否则保存文件不成功
    tlo.AddConfig("TileSizeFactor", "1.0"); /// 瓦片大小的影响因子，建议是1.0
    tlo.AddConfig("TileSize", "300000"); /// 瓦片大小，根据数据实际情况设置，根据数据面积来，面积越大值越大
    tlo.AddConfig("LiftUp", "0"); /// 抬升高度，任意值
    tlo.AddConfig("MaxRange", "1000000.0"); /// 最大显示范围，大于最小显示范围-无穷大
    tlo.AddConfig("MinRange", "0.0"); /// 最小显示范围，0-无穷大
    tlo.AddConfig("Fields", "Name:String:100:0,Height:Double:100:3,Width:Float:100:3,ID:String:100:0,XRotate:Double:100:3,YRotate:Double:100:3,ZRotate:Double:100:3,Type:Double:100:3");
    tlo.AddConfig("StyleSheet", styleSheet.GetConfig()); /// 将样式表配置添加至图层配置对象，第一参必须为StyleSheet字符串
    cameraLayer = map.CreateLayer("FeatureModelLayer", tlo);
    map.AddLayer(cameraLayer);
    layerID.push(cameraLayer.GetLayerID());
    polygoneditLayer = map.GetFeatureModelLayer(cameraLayer.GetLayerID()); /// 获取矢量图层

    addState = false;
}

var addState = false;

//模拟点击一个点，将模型加载进去
function pointModelLayer() {
    if (addState) {
        addState = false;
    } else {
        addState = true;
    }
    var index = selectValue();
    for (var i in vLayer) {
        var layerType = vLayer[i].type;
        if (layerType == index) {
            model = vLayer[i].model;
        }
    }
}

var iconshpLayer;

//摄像头位置标注
function loadCameraPicLayer() {
    var pSymbol = map.CreateSymbol("TextSymbol"); //创建类型为TextSymbol的符号，必须为TextSymbol字符串
    pSymbol.AddConfig("FillingColor", "0, 0, 0, 1.0"); //文字颜色（RGBA），颜色值0-1，最后一位代表透明度，0为透明，1为不透
    pSymbol.AddConfig("Font", "C:\\WINDOWS\\Fonts\\simhei.ttf"); //文字字体，从系统字体目录中取，字体文件必须存在，配置一些参数时，如果没生效可能与字体文件相关，例如中文
    pSymbol.AddConfig("Size", "50"); //字体精度大小
    pSymbol.AddConfig("CharacterSize", "8"); //文字大小
    pSymbol.AddConfig("FieldPrecision", "-20"); //字段精度
    pSymbol.AddConfig("CharacterMode", "2"); //字符大小变化模式，0：随对象变化显示，1:随相机远近变化，2：随相机远近变化，同时不超过上限值
    pSymbol.AddConfig("Align", "5"); //设置文字位于要素的位置
    pSymbol.AddConfig("AxisAlignment", "6"); //设置文字旋转模式0 - 7 ， 6: 自动
    pSymbol.AddConfig("RemoveDuplicateLabels", "false"); //是否移除重复的多重标注
    pSymbol.AddConfig("IsEmbolden", "false"); //是否加粗
    pSymbol.AddConfig("IsTransform", "false"); //是否斜体
    pSymbol.AddConfig("IsUnderline", "false"); //是否加下划线
    pSymbol.AddConfig("IsHorizontal", "true"); //是否水平排列
    pSymbol.AddConfig("HorizonSpacingSize", "1.0"); //字符水平间隔距离
    pSymbol.AddConfig("IsBack", "false"); //是否有背景
    pSymbol.AddConfig("BackColor", "1,0,0,0.001"); //设置文字背景色
    pSymbol.AddConfig("LineColor", "0.6,0.6,0.6,1.0");
    pSymbol.AddConfig("IsAddGroundLine", "false"); //是否开启接地线
    pSymbol.AddConfig("Content", "[NAME]"); //[]里代表矢量的某字段名称
    pSymbol.AddConfig("LibraryName", "Library"); //设置资源库名称
    pSymbol.AddConfig("BackdropMarginLeft", "2.0"); //背景边框左边大小
    pSymbol.AddConfig("BackdropMarginRight", "2.0"); //背景边框右边大小
    pSymbol.AddConfig("BackdropMarginUp", "2.0"); //背景边框上边大小
    pSymbol.AddConfig("BackdropMarginDown", "20.0");

    var tSymbol = map.CreateSymbol("IconSymbol");
    tSymbol.AddConfig("AlignmentMode", "1");
    tSymbol.AddConfig("AxisAlignment", "5");
    tSymbol.AddConfig("CharacterMode", "2");
    tSymbol.AddConfig("Scale", "0.5");
    tSymbol.AddConfig("XScale", "0.4"); //图片x方向放大比例
    tSymbol.AddConfig("YScale", "0.4"); //图片y方向放大比例
    tSymbol.AddConfig("ZScale", "0.4"); //图片z方向放大比例
    //				tSymbol.AddConfig("LineColor", "1,1,1,1"); //接地线颜色
    tSymbol.AddConfig("IsAddGroundLine", "false"); //是否开启接地线
    tSymbol.AddConfig("Url", "http://172.16.12.166:8088/123.png"); //图标资源路径
    tSymbol.AddConfig("LibraryName", "reslib"); //资源名称

    var res = map.CreateResource("IconSymbol"); /// 创建图标资源，此处必须为IconSymbol
    res.AddConfig("Uri", "http://172.16.12.166:8088/123.png"); /// 图标资源路径
    var reslib = map.CreateResourceLibrary("reslib"); /// 创建资源库，名称和图层配置LibraryName设置的名称对应
    reslib.AddResource(res); /// 将资源添加至资源库
    var pStyle = map.CreateStyle("Point"); //创建名称为Point的样式，名称任意
    pStyle.AddSymbol("TextSymbol", pSymbol.GetConfig()); //将符号配置添加到该样式，第一参必须为TextSymbol字符串
    pStyle.AddFilterName("BuildTextFilter"); //设置文字构建器符号为BuildTextFilter，必须为BuildGeometryFilter字符串
    /////////////////////此部分是图片在场景中显示的配置/////////////////

    pStyle.SetName("point"); /// 设置别名point
    //pStyle.AddSymbol("PointSymbol", pSymbol.GetConfig());			/// 将点符号配置添加到该样式，第一参必须为PointSymbol字符串
    //pStyle.AddFilterName("BuildGeometryFilter");					/// 设置构建器符号为BuildGeometryFilter，必须为BuildGeometryFilter字符串
    pStyle.AddSymbol("IconSymbol", tSymbol.GetConfig()); /// 将图片符号配置添加到该样式，第一参必须为IconSymbol字符串
    pStyle.AddFilterName("SubstituteModelFilter"); /// 设置图片构建器符号为SubstituteModelFilter，此为图标符号化和模型符号化共有

    var styleSheet = map.CreateStyleSheet(); /// 创建样式表
    styleSheet.AddStyle(pStyle.GetConfig()); /// 将样式配置添加至样式表
    styleSheet.AddResLib(reslib.GetConfig()); /// 将资源库添加至样式表

    var tlo = map.CreateLayerOptions("shp"); /// 创建图层配置对象，名称任意
    tlo.AddConfig("LayerOptionsName", "FeatureModelLayerOptions"); /// 创建配置类型, FeatureModelLayerOptions代表矢量数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "fmgeom"); /// 数据源类型,代表fmgeom插件，必须是此键值对
    tlo.AddConfig("Driver", "ESRI Shapefile"); /// 数据驱动，针对shp、dxf数据源必须是ESRI Shapefile
    tlo.AddConfig("Fields", "Name:String:100:0,Height:Double:100:3,Width:Float:100:3,ID:Float:100:3,Type:String:100:0"); ////创建矢量的属性字段，属性名：属性类型：类型长度：小数点后几位
    tlo.AddConfig("FeatureSourceType", "ogr"); /// 要素数据源类型，针对shp、dxf数据源必须是ogr
    tlo.AddConfig("TileSizeFactor", "1.0"); /// 瓦片大小的影响因子，建议是1.0
    tlo.AddConfig("TileSize", "500"); /// 瓦片大小，根据数据实际情况设置，根据数据面积来，面积越大值越大
    tlo.AddConfig("LiftUp", "2"); /// 抬升高度，任意值
    tlo.AddConfig("MaxRange", "600.0"); /// 最大显示范围，大于最小显示范围-无穷大
    tlo.AddConfig("MinRange", "0.0"); /// 最小显示范围，0-无穷大
    tlo.AddConfig("StyleSheet", styleSheet.GetConfig()); /// 将样式表配置添加至图层配置对象，第一参必须为StyleSheet字符串

    iconshpLayer = map.CreateLayer("FeatureModelLayer", tlo); /// 创建矢量图层，第一项参数必须为FeatureModelLayer
    layerID.push(iconshpLayer.GetLayerID());
    map.AddLayer(iconshpLayer); /// 添加矢量图层
    var id = iconshpLayer.GetLayerID(); /// 获取图层id
    cameraPicLayer = map.GetFeatureModelLayer(id);

    for (var u in resource) {
        var addFeature = map.CreateFeature(); //创建要素对象
        addFeature.SetGeometryType(1); //设置要素几何类型(1:点; 2:线; 3:环; 4:面; 5:多结构)
        addFeature.SetComponentType(1); //创建子几何类型（当GeometryType为5时生效）
        var point = resource[u].pos.split(",");
        addFeature.AddPoint(point[0], point[1], point[2]); ////向编辑图层添加坐标点信息
        addFeature.AddAttribute("Height", "43.5", 4); //添加属性值(1:int; 2:long; 3:float; 4:double; 5:string; 6:bool)
        addFeature.AddAttribute("Name", resource[u].name, 5); //添加属性值
        addFeature.AddAttribute("Type", "Icon", 5); //添加属性值
        addFeature.AddAttribute("Width", "54", 3); //添加属性值
        var featureId = cameraPicLayer.GetMaxFeatureID(); //获取矢量图层要素最大ID
        addFeature.SetFeatureId(featureId + 1); //设置FeatureID
        cameraPicLayer.AddFeature(addFeature);
    }

}

function newLoadCameraPicLayer(pos, name) {
    var pSymbol = map.CreateSymbol("TextSymbol"); //创建类型为TextSymbol的符号，必须为TextSymbol字符串
    pSymbol.AddConfig("FillingColor", "0, 0, 0, 1.0"); //文字颜色（RGBA），颜色值0-1，最后一位代表透明度，0为透明，1为不透
    pSymbol.AddConfig("Font", "C:\\WINDOWS\\Fonts\\simhei.ttf"); //文字字体，从系统字体目录中取，字体文件必须存在，配置一些参数时，如果没生效可能与字体文件相关，例如中文
    pSymbol.AddConfig("Size", "50"); //字体精度大小
    pSymbol.AddConfig("CharacterSize", "8"); //文字大小
    pSymbol.AddConfig("FieldPrecision", "-20"); //字段精度
    pSymbol.AddConfig("CharacterMode", "2"); //字符大小变化模式，0：随对象变化显示，1:随相机远近变化，2：随相机远近变化，同时不超过上限值
    pSymbol.AddConfig("Align", "5"); //设置文字位于要素的位置
    pSymbol.AddConfig("AxisAlignment", "6"); //设置文字旋转模式0 - 7 ， 6: 自动
    pSymbol.AddConfig("RemoveDuplicateLabels", "false"); //是否移除重复的多重标注
    pSymbol.AddConfig("IsEmbolden", "false"); //是否加粗
    pSymbol.AddConfig("IsTransform", "false"); //是否斜体
    pSymbol.AddConfig("IsUnderline", "false"); //是否加下划线
    pSymbol.AddConfig("IsHorizontal", "true"); //是否水平排列
    pSymbol.AddConfig("HorizonSpacingSize", "1.0"); //字符水平间隔距离
    pSymbol.AddConfig("IsBack", "false"); //是否有背景
    pSymbol.AddConfig("BackColor", "1,0,0,0.001"); //设置文字背景色
    pSymbol.AddConfig("LineColor", "0.6,0.6,0.6,1.0");
    pSymbol.AddConfig("IsAddGroundLine", "false"); //是否开启接地线
    pSymbol.AddConfig("Content", "[NAME]"); //[]里代表矢量的某字段名称
    pSymbol.AddConfig("LibraryName", "Library"); //设置资源库名称
    pSymbol.AddConfig("BackdropMarginLeft", "2.0"); //背景边框左边大小
    pSymbol.AddConfig("BackdropMarginRight", "2.0"); //背景边框右边大小
    pSymbol.AddConfig("BackdropMarginUp", "2.0"); //背景边框上边大小
    pSymbol.AddConfig("BackdropMarginDown", "20.0");

    var tSymbol = map.CreateSymbol("IconSymbol");
    tSymbol.AddConfig("AlignmentMode", "1");
    tSymbol.AddConfig("AxisAlignment", "5");
    tSymbol.AddConfig("CharacterMode", "2");
    tSymbol.AddConfig("Scale", "0.5");
    tSymbol.AddConfig("XScale", "0.4"); //图片x方向放大比例
    tSymbol.AddConfig("YScale", "0.4"); //图片y方向放大比例
    tSymbol.AddConfig("ZScale", "0.4"); //图片z方向放大比例
    //				tSymbol.AddConfig("LineColor", "1,1,1,1"); //接地线颜色
    tSymbol.AddConfig("IsAddGroundLine", "false"); //是否开启接地线
    tSymbol.AddConfig("Url", "http://172.16.12.166:8088/123.png"); //图标资源路径
    tSymbol.AddConfig("LibraryName", "reslib"); //资源名称

    var res = map.CreateResource("IconSymbol"); /// 创建图标资源，此处必须为IconSymbol
    res.AddConfig("Uri", "http://172.16.12.166:8088/123.png"); /// 图标资源路径
    var reslib = map.CreateResourceLibrary("reslib"); /// 创建资源库，名称和图层配置LibraryName设置的名称对应
    reslib.AddResource(res); /// 将资源添加至资源库
    var pStyle = map.CreateStyle("Point"); //创建名称为Point的样式，名称任意
    pStyle.AddSymbol("TextSymbol", pSymbol.GetConfig()); //将符号配置添加到该样式，第一参必须为TextSymbol字符串
    pStyle.AddFilterName("BuildTextFilter"); //设置文字构建器符号为BuildTextFilter，必须为BuildGeometryFilter字符串
    /////////////////////此部分是图片在场景中显示的配置/////////////////

    pStyle.SetName("point"); /// 设置别名point
    //pStyle.AddSymbol("PointSymbol", pSymbol.GetConfig());			/// 将点符号配置添加到该样式，第一参必须为PointSymbol字符串
    //pStyle.AddFilterName("BuildGeometryFilter");					/// 设置构建器符号为BuildGeometryFilter，必须为BuildGeometryFilter字符串
    pStyle.AddSymbol("IconSymbol", tSymbol.GetConfig()); /// 将图片符号配置添加到该样式，第一参必须为IconSymbol字符串
    pStyle.AddFilterName("SubstituteModelFilter"); /// 设置图片构建器符号为SubstituteModelFilter，此为图标符号化和模型符号化共有

    var styleSheet = map.CreateStyleSheet(); /// 创建样式表
    styleSheet.AddStyle(pStyle.GetConfig()); /// 将样式配置添加至样式表
    styleSheet.AddResLib(reslib.GetConfig()); /// 将资源库添加至样式表

    var tlo = map.CreateLayerOptions("shp"); /// 创建图层配置对象，名称任意
    tlo.AddConfig("LayerOptionsName", "FeatureModelLayerOptions"); /// 创建配置类型, FeatureModelLayerOptions代表矢量数据配置，必须是此键值对
    tlo.AddConfig("DataSourceTypeName", "fmgeom"); /// 数据源类型,代表fmgeom插件，必须是此键值对
    tlo.AddConfig("Driver", "ESRI Shapefile"); /// 数据驱动，针对shp、dxf数据源必须是ESRI Shapefile
    tlo.AddConfig("Fields", "Name:String:100:0,Height:Double:100:3,Width:Float:100:3,ID:Float:100:3"); ////创建矢量的属性字段，属性名：属性类型：类型长度：小数点后几位
    tlo.AddConfig("FeatureSourceType", "ogr"); /// 要素数据源类型，针对shp、dxf数据源必须是ogr
    tlo.AddConfig("TileSizeFactor", "1.0"); /// 瓦片大小的影响因子，建议是1.0
    tlo.AddConfig("TileSize", "500"); /// 瓦片大小，根据数据实际情况设置，根据数据面积来，面积越大值越大
    tlo.AddConfig("LiftUp", "2"); /// 抬升高度，任意值
    tlo.AddConfig("MaxRange", "600.0"); /// 最大显示范围，大于最小显示范围-无穷大
    tlo.AddConfig("MinRange", "0.0"); /// 最小显示范围，0-无穷大
    tlo.AddConfig("StyleSheet", styleSheet.GetConfig()); /// 将样式表配置添加至图层配置对象，第一参必须为StyleSheet字符串

    iconshpLayer = map.CreateLayer("FeatureModelLayer", tlo); /// 创建矢量图层，第一项参数必须为FeatureModelLayer
    layerID.push(iconshpLayer.GetLayerID());
    map.AddLayer(iconshpLayer); /// 添加矢量图层
    var id = iconshpLayer.GetLayerID(); /// 获取图层id
    cameraPicLayer = map.GetFeatureModelLayer(id);

    var addFeature = map.CreateFeature(); //创建要素对象
    addFeature.SetGeometryType(1); //设置要素几何类型(1:点; 2:线; 3:环; 4:面; 5:多结构)
    addFeature.SetComponentType(1); //创建子几何类型（当GeometryType为5时生效）
    var point = pos.split(",");
    addFeature.AddPoint(point[0], point[1], point[2]); ////向编辑图层添加坐标点信息
    addFeature.AddAttribute("Height", "43.5", 4); //添加属性值(1:int; 2:long; 3:float; 4:double; 5:string; 6:bool)
    addFeature.AddAttribute("Name", name, 5); //添加属性值
    addFeature.AddAttribute("Width", "54", 3); //添加属性值
    var featureId = cameraPicLayer.GetMaxFeatureID(); //获取矢量图层要素最大ID
    addFeature.SetFeatureId(featureId + 1); //设置FeatureID
    cameraPicLayer.AddFeature(addFeature);

}

function removeLayer(layer) {
    map.RemoveLayer(layer); //删除图层
}

function removeIconLayer() {
    alert("进入删除layer方法");
    map.removeLayer(iconshpLayer);
}

/**
 * 图层组管理
 */
function _LayerGroup() {
    this._Layers = new Array();
    this.curLayer = 0;

    this.addLayer = function (_Layer) {
        this._Layers.push(_Layer);
    }

    this.addLayer = function (SDKLayerObj, LayerName) {
        this._Layers.push(new _Layer(SDKLayerObj, LayerName));
    }

    this.delLayer = function (_Layer) {
        for (var i = 0; i < this._Layers.length; i++) {
            if (this._Layers[i].name = _Layer) {
                //删除图层
                break;
            }

        }
    }

    this.hide = function (LayerName) {
        if (typeof(LayerName) == "string") {
            return this.toggle(LayerName, false);
        } else if (null == LayerName) {
            this.toggle(false);
        }
        return false;
    };

    this.show = function (LayerName) {
        if (typeof(LayerName) == "string") {
            return this.toggle(LayerName, true);
        } else if (null == LayerName) {
            this.toggle(true);
        }
        return false;
    };

    this.toggle = function (LayerName, bool) {
        if (typeof(LayerName) == "boolean") {
            for (var i = 0; i < this._Layers.length; i++) {
                this._Layers[i].toggle(LayerName);
            }
        } else if (typeof(LayerName) == "string") {
            for (var i = 0; i < this._Layers.length; i++) {
                if (this._Layers[i].name == LayerName) {
                    if (null != bool) {
                        this._Layers[i].toggle(bool);
                    } else {
                        this._Layers[i].toggle();
                    }
                    return true;
                }
            }
            return false;
        } else {
            for (var i = 0; i < this._Layers.length; i++) {
                this._Layers[i].toggle();
            }
        }

    };

    this.nextName = function () {
        if (this.curLayer >= this._Layers.length) {
            this.curLayer = 0;
        }
        return this._Layers[this.curLayer++].name;

    };
}

/**
 * 图层管理
 */
function _Layer(SDKLayerObj, LayerName) {
    this.SDKObj = SDKLayerObj;
    this.name = LayerName;

    this.show = function () {
        this.toggle(true);
    }

    this.hide = function () {
        this.toggle(false);
    }

    this.toggle = function (bool) {
        if (null == bool) {
            this.SDKObj.GetVisible() ? this.hide() : this.show();
        } else {
            this.SDKObj.SetVisible(bool);
        }
    }

    this.id = function () {
        return this.SDKObj.GetLayerID();
    }

    this.options = function () {
        return this.SDKObj.GetLayerOption();
    }

}