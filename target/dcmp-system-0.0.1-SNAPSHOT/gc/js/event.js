//左键获取位置坐标
function click(x, y) {
    if (openLButtonState) {
        var pos = transformate.ScreenPosToWorldPos(x, y); ////将屏幕坐标点转换成场景坐标
        responseStr = pos.GetX() + "," + pos.GetY() + "," + pos.GetZ() + ";";
        pointLeft.push(responseStr);
        openLButtonState = false;
        console.log(responseStr);
    }
}

var postioninfo;

//右键获取当前视角
function getCoordinate(x, y) {
    if (openRButtonState) {
        postioninfo = navigation.GetViewpoint();
        var postioninfo1 = postioninfo.split(";");
        var postioninfo2 = postioninfo1.splice(2, 3);
        var postioninfo5 = postioninfo2.toString().replace(",", ";")
        var postioninfo3 = postioninfo1[0].split(":");
        var postioninfo4 = postioninfo3[1].split(",");
        responseStr = "Lon:" + postioninfo4[0] + ";Lat:" + postioninfo4[1] + ";Height:" + postioninfo4[2];
        postioninfo6 = responseStr + ";" + postioninfo5;
        console.log(postioninfo);
        openRButtonState = false;
    }
}

function returnCoordinateValue() {
    var postioninfo = navigation.GetViewpoint();
    var postioninfo1 = postioninfo.split(";");
    var postioninfo2 = postioninfo1.splice(2, 3);
    var postioninfo5 = postioninfo2.toString().replace(",", ";")
    var postioninfo3 = postioninfo1[0].split(":");
    var postioninfo4 = postioninfo3[1].split(",");
    responseStr = "Lon:" + postioninfo4[0] + ";Lat:" + postioninfo4[1] + ";Height:" + postioninfo4[2];
    var postioninfo6 = responseStr + ";" + postioninfo5;
    return postioninfo6;
}

//选择模型返回选择的对象
//function onFireOnResponser(str, type) {
//	if("PickVectorResponser" == str) {
//		var opt = pickResponser.GetResponserResult();
//		pos = opt.GetConfigValueByKey("FeatureAttributePos");
//		name = opt.GetConfigValueByKey("name");
//		if("" != pos) {
//			if("camera" == name) {
//				alert("选择的对象是camera");
//				showWegdit(pos);
//			} else if("汽车" == name) {
//				alert("选择的对象是汽车");
//			}
//			pick(false);
//		}
//	}
//}

function onFireOnResponser(str, type) {
    if ("PickVectorResponser" == str) {
        var opt = pickResponser.GetResponserResult();
        pos = opt.GetConfigValueByKey("FeatureAttributePos");
        pointList = opt.GetConfigValueByKey("PickLayerList");
        type = opt.GetConfigValueByKey("type");
        name = opt.GetConfigValueByKey("name");
        //					url = opt.GetConfigValueByKey("url");
        if ("" != pos) {
            //						if("camera" == name) {
//			showWegdit(pos);
            //							alert("选择的对象是camera");
            //						} else if("汽车" == name) {
            //							alert("选择的对象是汽车");
            //						} else if() {
//			alert("选择的对象是" + name + "选择对象点:" + pos);
            for (var i in resource) {
                if (resource[i].name == name) {
                    var pos = resource[i].pos;
                    if (type == "model") {
                        showWegdit(pos);
                        removeIconLayer();
                    } else if (type == "Icon") {
                        pos = pos.split(",");
                        postioninfos = "Lon:" + pos[0] + ";Lat:" + pos[1] + ";Height:" + pos[2] + ";Azimuth:" + resource[i].Azimuth + ";Pitch:" + resource[i].Pitch + ";Range:" + resource[i].Range + ";";
                        fly(postioninfos);
                    }
                }
            }
            //						}
            pick(false);
        }
    }
}

//巡更漫游点
function roam(layerid, type) {
    var layer = layermap[layerid];
    var opt = layer.GetLayerResult();
    if (addPoints == true) {
        if (opt.GetConfigValueByKey("DataSourceTypeName") == "as_draw2dobject") {
            val = opt.GetConfigValueByKey("Points");
//			alert("漫游路径点获取:" + val);
//			alert(val);
            var avb = val.split(";", -1);
            avb.pop();
            if (paths != null) {
                var paths1 = paths.split(";");
                var paths2 = paths1[paths1.length - 2];
                var path1 = avb[0].split(",");
                var path2;
                for (var k in path1) {
                    if ((k + 1) % 3 == 0) {
                        path2 += path1[k] * 1 + 2 + ";";
                    } else {
                        if (path2 == undefined) {
                            path2 = path1[k] + ",";
                        } else {
                            path2 += path1[k] + ",";
                        }
                    }
                }
                var path3 = paths2 + ";" + path2;
                showPath(path3);
            }

            //						var paths = avb.splice();
            //					console.log(avb);
            var bbb;
            var ccc;
            for (var i in avb) {
                bbb = avb[i].split(",");
                for (var j in bbb) {
                    if ((j + 1) % 3 == 0) {
                        ccc = 1.3 + bbb[j] * 1 + "";
                        path += ccc + ";";
                    } else {
                        if (path == undefined) {
                            ccc = bbb[j];
                            path = ccc + ",";
                        } else {
                            ccc = bbb[j];
                            path += ccc + ",";
                        }
                    }
                }
            }
            addPoints = false;
        }
    }
}

function returnPath() {
    if (paths != null) {
        return paths + path;
    } else {
        return path;
    }

}

function pushPath(gis) {
    paths = gis;
    showPath(paths);
}

//
function creatModel(x, y) {
    var pos = transformate.ScreenPosToWorldPos(x, y);
    var name = document.getElementById("modelName").value;
    if (addState && name != "") {
        blockModel();
        addFeature = map.CreateFeature();
        addFeature.SetGeometryType(1); /// 设置要素几何类型(1:点; 2:线; 3:环; 4:面; 5:多结构)
        addFeature.SetComponentType(1); /// 创建子几何类型（当GeometryType为5时生效）
        addFeature.AddAttribute("Height", "43.5", 4); /// 添加属性值(1:int; 2:long; 3:float; 4:double; 5:string; 6:bool)
        addFeature.AddAttribute("Name", name, 5); /// 添加属性值
        addFeature.AddAttribute("Width", "54", 3); /// 添加属性值
        addFeature.addPoint(pos.GetX(), pos.GetY(), pos.GetZ());
        var featureId = polygoneditLayer.GetMaxFeatureID(); /// 获取矢量图层要素最大ID
        addFeature.SetFeatureId(featureId + 1); /// 设置FeatureID
        polygoneditLayer.AddFeature(addFeature);
//		addResAxis();
    } else if (addState && name == "") {
        alert("请输入名称");
    }

}