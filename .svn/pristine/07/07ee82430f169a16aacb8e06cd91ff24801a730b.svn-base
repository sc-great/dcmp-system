//创建获取VPSDKCtrl对象
var obj;
//创建对应SDK中的GetIMapMgrPtr对象
var map;
//创建tool对象
var tools;
//创建刷新SDK对象
var refresh;
//创建点击状态控制器,默认为false
var OpenState = false;
//创建鼠标左键点击状态控制器,默认为false
var openLButtonState = false;
//创建鼠标右键点击状态控制器,默认为false
var openRButtonState = false;
//创建动态创建模型控制器,默认为false
var creatModelState = false;
//创建坐标转换对象
var transformate;
//创建导航器对象
var navigation;
//创建模型图层对象
var modelLayer;
//创建经纬度对象
var postioninfo;
//创建路径图层对象
var pathLayer;
//创建点坐标数组
var pointLeft = new Array();
//创建坐标点数组拆箱对象
var path;

var serverIp;
var serverPort;

var val;
//创建矢量图层对象
var curLayer;
//创建拾取器对象
var pickResp;
//创建矢量图层ID
var layerID = new Array();
var layermap = new Array();

var addPoints = false;
//var tempLayer;
//创建矢量图层对象
var polygoneditLayer;

var cameraLayer;

var cameraPicLayer;

var pointqwe;

var paths = null;

//var newStr = new Array();
//创建矢量模型加载对象
var vLayer = [
    //	{"type":1,"layerObj":null,"model":"sceneName.L000_000.wrl"},
    {
        "type": 10,
        "layerObj": null,
        "model": "v_body.wrl.001.wrl"
    },
    {
        "type": 20,
        "layerObj": null,
        "model": "枪型.wrl.004.wrl"
    },
    {
        "type": 30,
        "layerObj": null,
        "model": "球型.wrl.004.wrl"
    },
    //	{"type":4,"layerObj":null,"model":"fqmx01.wrl.004.wrl"}, //杆带球
    {
        "type": 5,
        "layerObj": null,
        "model": "bjz01.wrl.004.wrl"
    }
];

//模型加载路径
var url = [{
    "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out1\\Lod1\\sceneName.L000_000.wrl",
    "name": "地下二层"
},
    {
        "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out4\\Lod4\\sceneName.L000_000.wrl",
        "name": "地下一层"
    },
    {
        "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out3\\Lod3\\sceneName.L000_000.wrl",
        "name": "海康园区"
    },
    {
        "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out5\\Lod5\\sceneName.L000_000.wrl",
        "name": "展厅"
    },
    {
        "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out6\\Lod6\\sceneName.L000_000.wrl",
        "name": "会议室"
    },
    {
        "url": "F:\\3DGIS\\HK展厅2.3\\模型数据\\Out2\\Lod2\\sceneName.L000_000.wrl",
        "name": "海康大楼"
    },
];

var C3Surl = [{
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L001_000.c3s",
    "name": "1层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_000.c3s",
    "name": "2层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_001.c3s",
    "name": "3层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_002.c3s",
    "name": "4层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_002.c3s",
    "name": "5层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_003.c3s",
    "name": "6层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_004.c3s",
    "name": "7层"
}, {
    "url": "F:\\Out1c3s\\Lod1\\RangeData\\sceneName.L002_005.c3s",
    "name": "8层"
}];

//创建静态资源
var resource = [
    //	{"type":1,"name":"楼栋1","pos":""},
    //	{"type":1,"name":"警察A","pos":"120.216579513,30.2115889851,-1.06485142279"},
    //	{"type":3,"name":"警察B","pos":"120.216805351,30.2118024791,-0.981135065667"},
    //	{"type":3,"name":"警察C","pos":"120.216679914,30.2118227525,-1.0647023757"},
    //	{"type":3,"name":"警察D","pos":"120.2167286,30.2117626598,-0.898916158825"},
    //	{"type":3,"name":"A楼门禁","pos":"120.216759244,30.2113292158,1.48207829706"},
    //	{"type":3,"name":"警察D","pos":"120.216574513,30.2115889851,-1.06485142279"},
    //	{"type":3,"name":"警察D","pos":"120.216575513,30.2115889851,-1.06485142279"},
    //	{"type":3,"name":"警察D","pos":"120.216575513,30.2115888851,-1.06485142279"},
    //	{"type":3,"name":"警察D","pos":"120.216574513,30.2115887851,-1.06485142279"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,2"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,4"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,6"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,8"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,10"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,12"},
    //	{"type":3,"name":"警察D","pos":"120.216805351,30.2118024791,14"},
    //	{"type":3,"name":"camera","pos":"120.216705351,30.2118024791,0"},
    //	{"type":3,"name":"camera","pos":"120.137973461,30.2694669432,13.632700745"},
    //	{"type":4,"name":"汽车","pos":"120.138973461,30.2694669432,13.632700745"},
    //	{"type":3,"name":"camera","pos":"120.137952973,30.2694717171,13.8387350589"},
    {
        "type": 30,
        "name": "camera",
        "pos": "120.137966569,30.269467068,13.6327014398",
        "XRotate": "0.2076367",
        "YRotate": "1.1058046",
        "ZRotate": "-0.3854842",
        "Azimuth": "0.0006109938871674577",
        "Pitch": "-0.7853911723224358",
        "Range": "10.078247432797",
    },
    {
        "type": 10,
        "name": "我们的爱",
        "pos": "120.137776569,30.268467068,13.6328014398",
        "XRotate": "0.000",
        "YRotate": "0.000",
        "ZRotate": "0.000",
        "Azimuth": "0.0006109938871674577",
        "Pitch": "-0.7853911723224358",
        "Range": "10.078247432797",
    },

];

var int = "Lon:120.21800903;Lat:30.2122715547;Height:-2.53618954495;Azimuth:0.0006109938871674577;Pitch:-0.7853911723224358;Range:233.078247432797;";
//创建文件路径
var DataPath = "F://3DGIS";

var KeyPositionArray = new Array();
var PositionArray = new Array();
var buffer = 0.5;
var index = 0;

var addPoints = false;
//			var s = "120.215522475,30.2108558335,25.0946463626;120.215202523,30.2107136476,25.0938017908;120.21537284,30.2110130871,25.0940024946;120.215002536,30.2111010471,25.0928537585;120.215134925,30.2114230578,25.0931203756;120.214756804,30.211502514,25.0921494663;";

var polyline;

var model;

var addFeature1;

var polygoneditLayers;

var addState;

var result;