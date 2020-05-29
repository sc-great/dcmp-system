function dateFormat(value, pattern) {
    if (value == null || value == "") {
        return "";
    }
    var year = parseInt(value.year) + 1900;
    var month = parseInt(value.month) + 1;
    month = month > 9 ? month : ('0' + month);
    var date = parseInt(value.date);
    date = date > 9 ? date : ('0' + date);
    var hours = parseInt(value.hours);
    hours = hours > 9 ? hours : ('0' + hours);
    if (pattern == "yMdhms") {
        var minutes = parseInt(value.minutes);
        minutes = minutes > 9 ? minutes : ('0' + minutes);
        var seconds = parseInt(value.seconds);
        seconds = seconds > 9 ? seconds : ('0' + seconds);
        return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes
            + ':' + seconds;
    } else if (pattern == "hms") {
        var minutes = parseInt(value.minutes);
        minutes = minutes > 9 ? minutes : ('0' + minutes);
        var seconds = parseInt(value.seconds);
        seconds = seconds > 9 ? seconds : ('0' + seconds);
        return hours + ':' + minutes + ':' + seconds;
    } else {
        return year + '-' + month + '-' + date;
    }
}

/**
 * 下拉框初始化工具，后台数据封装[{name:"",id:""}]
 */
function selectUtil($, url, domId, defValue, fun) {
    $.ajax({
        url: url,
        method: 'post',
        dataType: 'JSON',
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var d = data[i];
                var option;
                if (d.id == defValue) {
                    option = "<option value='" + d.id + "' selected>" + d.name + "</option>";
                } else if (d.name == defValue) {
                    option = "<option value='" + d.id + "' selected>" + d.name + "</option>";
                } else {
                    option = "<option value='" + d.id + "'>" + d.name + "</option>";
                }
                $("#" + domId).append(option);
            }
            fun();
        },
        error: function (data) {
            layer.alert("服务器异常！");
        }
    });
}

//动态控制弹出层的大小
function resizeLayer($, layerIndex, layerInitWidth, layerInitHeight) {
    var docWidth = $(window).width();
    var docHeight = $(window).height();
    var minWidth = layerInitWidth > docWidth ? docWidth : layerInitWidth;
    var minHeight = layerInitHeight > docHeight ? docHeight : layerInitHeight;
    if (docWidth < layerInitWidth || docHeight < layerInitHeight) {
        layer.style(layerIndex, {
            top: 10,
            left: 0,
            width: minWidth,
            height: minHeight
        });
    }
}

