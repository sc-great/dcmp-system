(function () {
    require.config({
        paths: {
            echarts: '../js'
        },
        packages: [
            {
                name: 'BMap',
                location: '../src',
                main: 'main'
            }
        ]
    });

    require(
        [
            'echarts',
            'BMap',
            'echarts/chart/map'
        ],
        function (echarts, BMapExtension) {
            $('#main').css({
                height: $('body').height(),
                width: $('body').width()
            });

            // ��ʼ����ͼ
            var BMapExt = new BMapExtension($('#main')[0], BMap, echarts, {
                enableMapClick: false
            });
            var map = BMapExt.getMap();
            var container = BMapExt.getEchartsContainer();

            var startPoint = {
                x: 104.114129,
                y: 37.550339
            };
            var point = new BMap.Point(startPoint.x, startPoint.y);
            map.centerAndZoom(point, 5);
            map.enableScrollWheelZoom(true);
            // ��ͼ�Զ�����ʽ
            map.setMapStyle({
                styleJson: [
                    {
                        "featureType": "water",
                        "elementType": "all",
                        "stylers": {
                            "color": "#044161"
                        }
                    },
                    {
                        "featureType": "land",
                        "elementType": "all",
                        "stylers": {
                            "color": "#004981"
                        }
                    },
                    {
                        "featureType": "boundary",
                        "elementType": "geometry",
                        "stylers": {
                            "color": "#064f85"
                        }
                    },
                    {
                        "featureType": "railway",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "highway",
                        "elementType": "geometry",
                        "stylers": {
                            "color": "#004981"
                        }
                    },
                    {
                        "featureType": "highway",
                        "elementType": "geometry.fill",
                        "stylers": {
                            "color": "#005b96",
                            "lightness": 1
                        }
                    },
                    {
                        "featureType": "highway",
                        "elementType": "labels",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "arterial",
                        "elementType": "geometry",
                        "stylers": {
                            "color": "#004981"
                        }
                    },
                    {
                        "featureType": "arterial",
                        "elementType": "geometry.fill",
                        "stylers": {
                            "color": "#00508b"
                        }
                    },
                    {
                        "featureType": "poi",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "green",
                        "elementType": "all",
                        "stylers": {
                            "color": "#056197",
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "subway",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "manmade",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "local",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "arterial",
                        "elementType": "labels",
                        "stylers": {
                            "visibility": "off"
                        }
                    },
                    {
                        "featureType": "boundary",
                        "elementType": "geometry.fill",
                        "stylers": {
                            "color": "#029fd4"
                        }
                    },
                    {
                        "featureType": "building",
                        "elementType": "all",
                        "stylers": {
                            "color": "#1a5787"
                        }
                    },
                    {
                        "featureType": "label",
                        "elementType": "all",
                        "stylers": {
                            "visibility": "off"
                        }
                    }
                ]
            });

            option = {
                color: ['gold', 'aqua', 'lime'],
                title: {
                    text: 'ģ��Ǩ��',
                    subtext: '���ݴ����鹹',
                    x: 'center',
                    textStyle: {
                        color: '#fff'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: function (v) {
                        return v[1].replace(':', ' > ');
                    }
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['����', '�Ϻ�', '����'],
                    selectedMode: 'single',
                    selected: {
                        '�Ϻ�': false,
                        '����': false
                    },
                    textStyle: {
                        color: '#fff'
                    }
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    x: 'right',
                    y: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                dataRange: {
                    min: 0,
                    max: 100,
                    x: 'right',
                    calculable: true,
                    color: ['#ff3333', 'orange', 'yellow', 'lime', 'aqua'],
                    textStyle: {
                        color: '#fff'
                    }
                },
                series: [
                    {
                        name: '����',
                        type: 'map',
                        mapType: 'none',
                        data: [],
                        geoCoord: {
                            '�Ϻ�': [121.4648, 31.2891],
                            '��ݸ': [113.8953, 22.901],
                            '��Ӫ': [118.7073, 37.5513],
                            '��ɽ': [113.4229, 22.478],
                            '�ٷ�': [111.4783, 36.1615],
                            '����': [118.3118, 35.2936],
                            '����': [124.541, 40.4242],
                            '��ˮ': [119.5642, 28.1854],
                            '��³ľ��': [87.9236, 43.5883],
                            '��ɽ': [112.8955, 23.1097],
                            '����': [115.0488, 39.0948],
                            '����': [103.5901, 36.3043],
                            '��ͷ': [110.3467, 41.4899],
                            '����': [116.4551, 40.2539],
                            '����': [109.314, 21.6211],
                            '�Ͼ�': [118.8062, 31.9208],
                            '����': [108.479, 23.1152],
                            '�ϲ�': [116.0046, 28.6633],
                            '��ͨ': [121.1023, 32.1625],
                            '����': [118.1689, 24.6478],
                            '̨��': [121.1353, 28.6688],
                            '�Ϸ�': [117.29, 32.0581],
                            '���ͺ���': [111.4124, 40.4901],
                            '����': [108.4131, 34.8706],
                            '������': [127.9688, 45.368],
                            '��ɽ': [118.4766, 39.6826],
                            '����': [120.9155, 30.6354],
                            '��ͬ': [113.7854, 39.8035],
                            '����': [122.2229, 39.4409],
                            '���': [117.4219, 39.4189],
                            '̫ԭ': [112.3352, 37.9413],
                            '����': [121.9482, 37.1393],
                            '����': [121.5967, 29.6466],
                            '����': [107.1826, 34.3433],
                            '��Ǩ': [118.5535, 33.7775],
                            '����': [119.4543, 31.5582],
                            '����': [113.5107, 23.2196],
                            '�ȷ�': [116.521, 39.0509],
                            '�Ӱ�': [109.1052, 36.4252],
                            '�żҿ�': [115.1477, 40.8527],
                            '����': [117.5208, 34.3268],
                            '����': [116.6858, 37.2107],
                            '����': [114.6204, 23.1647],
                            '�ɶ�': [103.9526, 30.7617],
                            '����': [119.4653, 32.8162],
                            '�е�': [117.5757, 41.4075],
                            '����': [91.1865, 30.1465],
                            '����': [120.3442, 31.5527],
                            '����': [119.2786, 35.5023],
                            '����': [102.9199, 25.4663],
                            '����': [119.5313, 29.8773],
                            '��ׯ': [117.323, 34.8926],
                            '����': [109.3799, 24.9774],
                            '����': [113.5327, 27.0319],
                            '�人': [114.3896, 30.6628],
                            '��ͷ': [117.1692, 23.3405],
                            '����': [112.6318, 22.1484],
                            '����': [123.1238, 42.1216],
                            '����': [116.8286, 38.2104],
                            '��Դ': [114.917, 23.9722],
                            'Ȫ��': [118.3228, 25.1147],
                            '̩��': [117.0264, 36.0516],
                            '̩��': [120.0586, 32.5525],
                            '����': [117.1582, 36.8701],
                            '����': [116.8286, 35.3375],
                            '����': [110.3893, 19.8516],
                            '�Ͳ�': [118.0371, 36.6064],
                            '����': [118.927, 33.4039],
                            '����': [114.5435, 22.5439],
                            '��Զ': [112.9175, 24.3292],
                            '����': [120.498, 27.8119],
                            'μ��': [109.7864, 35.0299],
                            '����': [119.8608, 30.7782],
                            '��̶': [112.5439, 27.7075],
                            '����': [117.8174, 37.4963],
                            'Ϋ��': [119.0918, 36.524],
                            '��̨': [120.7397, 37.5128],
                            '��Ϫ': [101.9312, 23.8898],
                            '�麣': [113.7305, 22.1155],
                            '�γ�': [120.2234, 33.5577],
                            '�̽�': [121.9482, 41.0449],
                            'ʯ��ׯ': [114.4995, 38.1006],
                            '����': [119.4543, 25.9222],
                            '�ػʵ�': [119.2126, 40.0232],
                            '����': [120.564, 29.7565],
                            '�ĳ�': [115.9167, 36.4032],
                            '����': [112.1265, 23.5822],
                            '��ɽ': [122.2559, 30.2234],
                            '����': [120.6519, 31.3989],
                            '����': [117.6526, 36.2714],
                            '����': [115.6201, 35.2057],
                            'Ӫ��': [122.4316, 40.4297],
                            '��«��': [120.1575, 40.578],
                            '��ˮ': [115.8838, 37.7161],
                            '����': [118.6853, 28.8666],
                            '����': [101.4038, 36.8207],
                            '����': [109.1162, 34.2004],
                            '����': [106.6992, 26.7682],
                            '���Ƹ�': [119.1248, 34.552],
                            '��̨': [114.8071, 37.2821],
                            '����': [114.4775, 36.535],
                            '֣��': [113.4668, 34.6234],
                            '������˹': [108.9734, 39.2487],
                            '����': [107.7539, 30.1904],
                            '��': [120.0037, 29.1028],
                            'ͭ��': [109.0393, 35.1947],
                            '����': [106.3586, 38.1775],
                            '��': [119.4763, 31.9702],
                            '����': [125.8154, 44.2584],
                            '��ɳ': [113.0823, 28.2568],
                            '����': [112.8625, 36.4746],
                            '��Ȫ': [113.4778, 38.0951],
                            '�ൺ': [120.4651, 36.3373],
                            '�ع�': [113.7964, 24.7028]
                        },

                        markLine: {
                            smooth: true,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 1,
                                    lineStyle: {
                                        type: 'solid',
                                        shadowBlur: 10
                                    }
                                }
                            },
                            data: [
                                [{name: '����'}, {name: '�Ϻ�', value: 95}],
                                [{name: '����'}, {name: '����', value: 90}],
                                [{name: '����'}, {name: '����', value: 80}],
                                [{name: '����'}, {name: '����', value: 70}],
                                [{name: '����'}, {name: '�ϲ�', value: 60}],
                                [{name: '����'}, {name: '����', value: 50}],
                                [{name: '����'}, {name: '����', value: 40}],
                                [{name: '����'}, {name: '��ͷ', value: 30}],
                                [{name: '����'}, {name: '����', value: 20}],
                                [{name: '����'}, {name: '����', value: 10}]
                            ]
                        },
                        markPoint: {
                            symbol: 'emptyCircle',
                            symbolSize: function (v) {
                                return 10 + v / 10
                            },
                            effect: {
                                show: true,
                                shadowBlur: 0
                            },
                            itemStyle: {
                                normal: {
                                    label: {show: false}
                                }
                            },
                            data: [
                                {name: '�Ϻ�', value: 95},
                                {name: '����', value: 90},
                                {name: '����', value: 80},
                                {name: '����', value: 70},
                                {name: '�ϲ�', value: 60},
                                {name: '����', value: 50},
                                {name: '����', value: 40},
                                {name: '��ͷ', value: 30},
                                {name: '����', value: 20},
                                {name: '����', value: 10}
                            ]
                        }

                    },
                    {
                        name: '�Ϻ�',
                        type: 'map',
                        mapType: 'none',
                        data: [],
                        markLine: {
                            smooth: true,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 1,
                                    lineStyle: {
                                        type: 'solid',
                                        shadowBlur: 10
                                    }
                                }
                            },
                            data: [
                                [{name: '�Ϻ�'}, {name: '��ͷ', value: 95}],
                                [{name: '�Ϻ�'}, {name: '����', value: 90}],
                                [{name: '�Ϻ�'}, {name: '����', value: 80}],
                                [{name: '�Ϻ�'}, {name: '֣��', value: 70}],
                                [{name: '�Ϻ�'}, {name: '����', value: 60}],
                                [{name: '�Ϻ�'}, {name: '����', value: 50}],
                                [{name: '�Ϻ�'}, {name: '��ɳ', value: 40}],
                                [{name: '�Ϻ�'}, {name: '����', value: 30}],
                                [{name: '�Ϻ�'}, {name: '����', value: 20}],
                                [{name: '�Ϻ�'}, {name: '����', value: 10}]
                            ]
                        },
                        markPoint: {
                            symbol: 'emptyCircle',
                            symbolSize: function (v) {
                                return 10 + v / 10
                            },
                            effect: {
                                show: true,
                                shadowBlur: 0
                            },
                            itemStyle: {
                                normal: {
                                    label: {show: false}
                                }
                            },
                            data: [
                                {name: '��ͷ', value: 95},
                                {name: '����', value: 90},
                                {name: '����', value: 80},
                                {name: '֣��', value: 70},
                                {name: '����', value: 60},
                                {name: '����', value: 50},
                                {name: '��ɳ', value: 40},
                                {name: '����', value: 30},
                                {name: '����', value: 20},
                                {name: '����', value: 10}
                            ]
                        }
                    },
                    {
                        name: '����',
                        type: 'map',
                        mapType: 'none',
                        data: [],
                        markLine: {
                            smooth: true,
                            effect: {
                                show: true,
                                scaleSize: 1,
                                period: 30,
                                color: '#fff',
                                shadowBlur: 10
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: 1,
                                    lineStyle: {
                                        type: 'solid',
                                        shadowBlur: 10
                                    }
                                }
                            },
                            data: [
                                [{name: '����'}, {name: '����', value: 95}],
                                [{name: '����'}, {name: '̫ԭ', value: 90}],
                                [{name: '����'}, {name: '����', value: 80}],
                                [{name: '����'}, {name: '����', value: 70}],
                                [{name: '����'}, {name: '����', value: 60}],
                                [{name: '����'}, {name: '�ɶ�', value: 50}],
                                [{name: '����'}, {name: '����', value: 40}],
                                [{name: '����'}, {name: '����', value: 30}],
                                [{name: '����'}, {name: '����', value: 20}],
                                [{name: '����'}, {name: '����', value: 10}]
                            ]
                        },
                        markPoint: {
                            symbol: 'emptyCircle',
                            symbolSize: function (v) {
                                return 10 + v / 10
                            },
                            effect: {
                                show: true,
                                shadowBlur: 0
                            },
                            itemStyle: {
                                normal: {
                                    label: {show: false}
                                }
                            },
                            data: [
                                {name: '����', value: 95},
                                {name: '̫ԭ', value: 90},
                                {name: '����', value: 80},
                                {name: '����', value: 70},
                                {name: '����', value: 60},
                                {name: '�ɶ�', value: 50},
                                {name: '����', value: 40},
                                {name: '����', value: 30},
                                {name: '����', value: 20},
                                {name: '����', value: 10}
                            ]
                        }
                    },
                    {
                        name: 'ȫ��',
                        type: 'map',
                        mapType: 'none',
                        data: [],
                        markLine: {
                            smooth: true,
                            symbol: ['none', 'circle'],
                            symbolSize: 1,
                            itemStyle: {
                                normal: {
                                    color: '#fff',
                                    borderWidth: 1,
                                    borderColor: 'rgba(30,144,255,0.5)'
                                }
                            },
                            data: [
                                [{name: '����'}, {name: '��ͷ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '֣��'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��ɳ'}],
                                [{name: '����'}, {name: '�ɶ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��Ӫ'}],
                                [{name: '����'}, {name: '�Ӱ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '���ͺ���'}],
                                [{name: '����'}, {name: '�Ϸ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '������'}],
                                [{name: '����'}, {name: '��ɽ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�ϲ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '���Ƹ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�Ͼ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��ͨ'}],
                                [{name: '����'}, {name: '�Ϻ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��ͷ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�ൺ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '̫ԭ'}],
                                [{name: '����'}, {name: '��³ľ��'}],
                                [{name: '����'}, {name: 'Ϋ��'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�人'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��̨'}],
                                [{name: '����'}, {name: '�γ�'}],
                                [{name: '����'}, {name: '�麣'}],
                                [{name: '�Ϻ�'}, {name: '��ͷ'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '֣��'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '��ɳ'}],
                                [{name: '�Ϻ�'}, {name: '�ɶ�'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '���ͺ���'}],
                                [{name: '�Ϻ�'}, {name: '�Ϸ�'}],
                                [{name: '�Ϻ�'}, {name: '������'}],
                                [{name: '�Ϻ�'}, {name: '��ɽ'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '�ϲ�'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '���Ƹ�'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '�ػʵ�'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: 'ʯ��ׯ'}],
                                [{name: '�Ϻ�'}, {name: '��ͷ'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '�ൺ'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '���'}],
                                [{name: '�Ϻ�'}, {name: '̫ԭ'}],
                                [{name: '�Ϻ�'}, {name: '��³ľ��'}],
                                [{name: '�Ϻ�'}, {name: 'Ϋ��'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '�人'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '����'}],
                                [{name: '�Ϻ�'}, {name: '��̨'}],
                                [{name: '�Ϻ�'}, {name: '�麣'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '֣��'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��ɳ'}],
                                [{name: '����'}, {name: '�ɶ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '���ͺ���'}],
                                [{name: '����'}, {name: '�Ϸ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '������'}],
                                [{name: '����'}, {name: '��ɽ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�ϲ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '���Ƹ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�Ͼ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��ͨ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�Ϻ�'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: 'ʯ��ׯ'}],
                                [{name: '����'}, {name: '��ͷ'}],
                                [{name: '����'}, {name: '�ൺ'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '���'}],
                                [{name: '����'}, {name: '̫ԭ'}],
                                [{name: '����'}, {name: '��³ľ��'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '�人'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '����'}],
                                [{name: '����'}, {name: '��̨'}],
                                [{name: '����'}, {name: '�γ�'}]
                            ]
                        }
                    }
                ]
            };

            var myChart = BMapExt.initECharts(container);
            BMapExt.setOption(option);
        }
    );
})();