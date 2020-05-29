var myCharts = {
    version: "1.0.0"
};
/*
 * 初始化option @param backgroundColor 图表背景色 @param animation 是否开启动画效果
 */
myCharts.initOption = function (backgroundColor, animation) {
    myCharts.option = {
        backgroundColor: backgroundColor || '',
        animation: animation,
        yAxis: [],
        xAxis: [],
        series: []
    }
}
/*
 * 创建title @param title 标题内容 @param x,y 标题坐标位置 可设置"50%",50,"center、bottom、top"
 */
myCharts.creatTitle = function (title, x, y, color) {
    var title = {
        text: title || '',
        x: x || 0,
        y: y || 0,
        textStyle: {
            color: color || ''
        }
    }
    myCharts.option.title = title;
};
// 创建工具栏
myCharts.creatToolbox = function (fun) {
    var toolbox = {
        show: true,
        x: "left",
        y: 10,
        //backgroundColor: '#00FA9A',
        itemGap: 20,
        itemSize: 20,
        feature: {
            restore: {
                show: true
            },
            dataView: {
                show: false,
                readOnly: false
            },
            magicType: {
                show: true,
                type: ['line', 'bar']
            },
            myTool: {
                show: true,
                title: "返回",
                icon: 'path://M368.3,550.3c0-123.1,89.1-225.6,206.2-246.7v120.3l246.1-206.9L574.5,10v102.9c-221.6,22.4-395.1,210-395.1,437.4c0,242.4,197.2,439.7,439.7,439.7h33.7V801.1h-33.7C480.8,801.1,368.3,688.6,368.3,550.3z',
                onclick: function () {
                    fun();
                }
            }
        },
        iconStyle: {
            normal: {
                color: 'white', //设置颜色
            }
        }
    }
    myCharts.option.toolbox = toolbox;
};
/*
 * 创建提示信息 @param trigger 提示信息方式 包括“item、axis” @param formatter 提示信息格式化"{a}
 * <br/>{b} : {c} ({d}%)",({d}%)为饼图显示百分比,
 */
myCharts.creattTooltip = function (trigger, formatter) {
    var tooltip = {
        trigger: trigger || 'axis',
        formatter: formatter || ''
    };
    myCharts.option.tooltip = tooltip;
};
/*
 * 创建图例 @param textcolor 图例文字色彩 @param orient 图例的排列方式，默认水平方式，vertical坚排 @param
 * x,y 坐标位置 可设置"50%",50,"center、bottom、top" @param data 图例信息集合[]
 */
myCharts.creatLegend = function (textcolor, orient, x, y, data) {

    var legend = {
        textStyle: { // 图例文字的样式
            color: textcolor || ''
        },
        orient: orient || 'horizontal',
        x: x || 0,
        y: y || 0,
        data: data || []
    };
    myCharts.option.legend = legend;
}
/*
 * 创建Y坐标信息 @param yAxis_type y轴类型，默认'value' @param showLine y轴分割线是否显示，默认显示
 * @param lineColor 分割线色彩 @param data
 * y轴数据内容,当yAxis_type=category，需要指定yAxis_data内容
 */
myCharts.creatYAxis = function (yAxis_type, showLine, lineColor, data, unit, formatter) {
    unit = typeof (unit) == "undefined" ? "" : unit;
    var yAxi = {
        type: yAxis_type || 'value',
        axisLine: {
            lineStyle: {
                color: lineColor || ''
            }
        },
        splitLine: {
            show: showLine
        },
        axisLabel: {
            formatter: formatter || '{value}' + unit
        }
    }
    if (yAxis_type == 'category') {

        yAxi.data = data;

    }
    myCharts.option.yAxis.push(yAxi);
};
/*
 * 创建X坐标信息 @param xAxis_type x轴类型，默认'category'其它有'value','time','log' @param
 * showLine x轴分割线是否显示，默认显示 @param lineColor 分割线色彩 @param data x轴数据内容
 */
myCharts.creatXAxis = function (xAxis_type, showLine, lineColor, data) {
    var xAxis = [{
        type: xAxis_type || 'category',
        axisLine: {
            lineStyle: {
                color: lineColor || ''
            }
        },
        splitLine: {
            show: showLine
        },
        data: data || []
    }];
    myCharts.option.xAxis = xAxis;
}
/*
 * 创建饼图内容信息 @param name 名称 @param radius 饼图的填充度[50,80] @param center
 * 饼图显示位置["50%","50%"] @param textColor 饼图签文字色彩 @param series_data 饼图娄内容
 * [{value:1,"name":"fdsafa"}] @param dataColor 饼图色彩填充[ '#006feb', '#009bf4',
 * '#00d8ff', '#60f6ff', '#ff7900', '#ffba00' ]
 */
myCharts.CreatSeriesForPie = function (name, radius, center, textColor,
                                       series_data, dataColor) {
    var pieSerie = {
        name: name || '',
        type: 'pie',
        showContent: true,
        radius: radius,
        center: center,
        label: {
            normal: {
                show: true,
                formatter: '{b}: {c}({d}%)'
            }
        },
        itemStyle: {
            normal: {
                label: {
                    textStyle: {
                        color: textColor
                    }
                }
            }
        },
        x: '60%',
        width: '35%',
        funnelAlign: 'left',
        data: series_data,
        color: dataColor || []
    }
    myCharts.option.series.push(pieSerie);
};
/*
 * 创建折线、柱状图 @param name 名称 @param series_type 图形类型 line,bar @param
 * itemStyle_color 柱状图的色彩 @param 是否显示标签 @param stack 是否叠加显示 @param showLabel
 * 是否显示标签 @param ilp 标签显示位置 @param series_data [1,2,3,4,5]
 */
myCharts.CreatSeries = function (name, series_type, itemStyle_color, stack,
                                 showLabel, ilp, series_data) {
    var serie = {
        name: name || '',
        type: series_type || 'bar',
        itemStyle: {
            normal: {
                color: itemStyle_color || '',
                label: {
                    show: showLabel
                }
            }
        },
        data: series_data || []
    }
    if (stack) {
        serie.stack = stack;
    }
    if (ilp) {
        serie.itemStyle.normal.label.position = ilp;
    }
    myCharts.option.series.push(serie);
};
/*
 * 显示图表渲染 @param containerId 渲染的DIV ID
 */
myCharts.build = function (containerId) {
    myCharts.chart = echarts.init(document.getElementById(containerId));
    myCharts.chart.setOption(myCharts.option, true);
}
//重新加载数据切换
myCharts.reBuild = function () {
    myCharts.chart.setOption(myCharts.option, true);
}
/*
 * 调用自动提示信息切换 @param showType 图形切换类型pie 需要单独说明 @param showTipTime 切换间隔时间
 */
myCharts.autoShowTip = function (showType, showTipTime) {
    var total = 0;
    if (showType == "pie") {
        total = myCharts.option.series[0].data.length;
    } else {
        total = myCharts.option.xAxis[0].data.length;
    }
    var count = 0;
    myCharts.timer = setInterval(function () {
        if (count > 0) {
            myCharts.chart.dispatchAction({
                type: 'downplay',
                seriesIndex: 0,
                dataIndex: count - 1
            });
        }
        count = count < total ? count : 0;
        // 3.0以上版本的showTip使用方式
        myCharts.chart.dispatchAction({
            type: 'showTip',
            seriesIndex: '0',
            dataIndex: count + ""
        });
        myCharts.chart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: count
        });
        count++;
    }, showTipTime);
}
myCharts.stopShowTip = function () {
    clearInterval(myCharts.timer);
}