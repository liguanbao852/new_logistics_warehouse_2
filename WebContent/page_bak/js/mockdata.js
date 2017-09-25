function mockCusotmerOrders() {
	return [
        {
            "order_end_date":{
                "date":27,
                "day":3,
                "hours":10,
                "minutes":19,
                "month":8,
                "nanos":0,
                "seconds":8,
                "time":1506478748000,
                "timezoneOffset":-480,
                "year":117
            },
            "order_name":"A2",
            "customer_name":"李四",
            "order_begin_date":{
                "date":14,
                "day":4,
                "hours":10,
                "minutes":19,
                "month":8,
                "nanos":0,
                "seconds":0,
                "time":1505355540000,
                "timezoneOffset":-480,
                "year":117
            },
            "orderProductInfoList":[
                {
                    "isFull":"0",
                    "orderNo":"O00002",
                    "productCnt":0,
                    "productId":"p00001",
                    "productName":"LS1",
                    "productNum":1000,
                    "productPrice":400
                },
                {
                    "isFull":"0",
                    "orderNo":"O00002",
                    "productCnt":0,
                    "productId":"p00003",
                    "productName":"LS3",
                    "productNum":900,
                    "productPrice":420
                }
            ],
            "is_full":"0",
            "order_cust":"C00002",
            "order_no":"O00002"
        },
        {
            "order_end_date":{
                "date":28,
                "day":4,
                "hours":10,
                "minutes":39,
                "month":8,
                "nanos":0,
                "seconds":38,
                "time":1506566378000,
                "timezoneOffset":-480,
                "year":117
            },
            "order_name":"A3",
            "customer_name":"王五",
            "order_begin_date":{
                "date":20,
                "day":3,
                "hours":10,
                "minutes":39,
                "month":8,
                "nanos":0,
                "seconds":24,
                "time":1505875164000,
                "timezoneOffset":-480,
                "year":117
            },
            "orderProductInfoList":[
                {
                    "isFull":"0",
                    "orderNo":"O00003",
                    "productCnt":0,
                    "productId":"p00002",
                    "productName":"LS2",
                    "productNum":1000,
                    "productPrice":600
                },
                {
                    "isFull":"0",
                    "orderNo":"O00003",
                    "productCnt":0,
                    "productId":"p00003",
                    "productName":"LS3",
                    "productNum":1000,
                    "productPrice":450
                }
            ],
            "is_full":"0",
            "order_cust":"C00003",
            "order_no":"O00003"
        },
        {
            "order_end_date":{
                "date":28,
                "day":4,
                "hours":14,
                "minutes":34,
                "month":8,
                "nanos":0,
                "seconds":20,
                "time":1506580460000,
                "timezoneOffset":-480,
                "year":117
            },
            "order_name":"A1",
            "customer_name":"张三",
            "order_begin_date":{
                "date":13,
                "day":3,
                "hours":14,
                "minutes":34,
                "month":8,
                "nanos":0,
                "seconds":14,
                "time":1505284454000,
                "timezoneOffset":-480,
                "year":117
            },
            "orderProductInfoList":[
                {
                    "isFull":"0",
                    "orderNo":"O00001",
                    "productCnt":0,
                    "productId":"p00001",
                    "productName":"LS1",
                    "productNum":1000,
                    "productPrice":400
                },
                {
                    "isFull":"0",
                    "orderNo":"O00001",
                    "productCnt":0,
                    "productId":"p00002",
                    "productName":"LS2",
                    "productNum":800,
                    "productPrice":600
                }
            ],
            "is_full":"0",
            "order_cust":"C00001",
            "order_no":"O00001"
        }
    ];
	}

	function mockPurchaseMaterialList() {
		return [{
				"material_name": "Zugstange",
				"company_id": null,
				"company_name": "Firm Z",
				"is_material": "1",
				"material_id": "m0001",
				"material_num": 7500
			},
			{
				"material_name": "Staubschutzh眉lle",
				"company_id": null,
				"company_name": "Firm X",
				"is_material": "1",
				"material_id": "m0002",
				"material_num": 7500
			},
			{
				"material_name": "Zahnstange ",
				"company_id": null,
				"company_name": "Firm Z",
				"is_material": "1",
				"material_id": "m0003",
				"material_num": 7500
			},
			{
				"material_name": "Stahlblech ",
				"company_id": null,
				"company_name": "Firm Y",
				"is_material": "1",
				"material_id": "m0004",
				"material_num": 8000
			},
			{
				"material_name": "ZT",
				"company_id": null,
				"company_name": "Firm ZT",
				"is_material": "0",
				"material_id": "a00003",
				"material_num": 7500
			},
			{
				"material_name": "SH",
				"company_id": null,
				"company_name": "Firm T",
				"is_material": "0",
				"material_id": "a00006",
				"material_num": 7500
			}
		];
	}

	// 本地生产优化措施
	function mockLocalCompanyProductSuggestion() {
		return {
			"newProductMeasures": "当前生产订单变为：A3, A3不含有产品：LS1,ZS产线生产变成生产：ZS2,ZS3,不生产：ZS1,GH产线生产变成生产：GH1,GH3,不生产：GH2"
		};
	}

	// 采购优化措施
	function mockLocalCompanyPurchaseSuggestion() {
		return {
			"newProductMaterial": "对于装配产线的影响是：可以适当减少SH采购， 减少到2000根 ，可以适当减少ZT采购， 减少到0根 。"
		};
	}

	// 合作公司生产优化措施
	function mockcoorationCompanySuggestion() {
		return {
			"newCompanyMeasures": "减少 SH的采购，通知Firma Y公司适当的减少SH的生产，减少 ZT的采购，通知Firma X公司适当的减少ZT的生产。"
		};
	}

	function get_list0() {
		return [{
				'index': '0',
				'text': 'AA1: 可用的功能及加工时间',
				'method': 'new_logistics_warehouse_2/factor/availableFuction.htm?orderId=',
				'hasParameter': true,
				'status': '0', // 0 not start, 1 in progress, 2 success, 3 error
				'value': [],
				'valueType': 1
			},
			{
				'index': '1',
				'text': 'AA2: 可用的设备',
				'method': 'new_logistics_warehouse_2/factor/availableMachine.htm?orderId=',
				'hasParameter': true,
				'status': '0',
				'value': [],
				'valueType': 1
			},
			{
				'index': '2',
				'text': 'AA3: 可用的生产物料',
				'method': 'new_logistics_warehouse_2/factor/availableMaterial.htm?orderId=',
				'hasParameter': true,
				'status': '0',
				'value': [],
				'valueType': 1
			},
			{
				'index': '3',
				'text': 'AA4可用的生产配件',
				'method': 'new_logistics_warehouse_2/factor/availableAccessory.htm?orderId=',
				'hasParameter': true,
				'status': '0',
				'value': [],
				'valueType': 1
			}
		];
	}

	function get_list1() {
		return [{
				'index': '0',
				'text': 'PA1: 产线的组成是否构成',
				'method': 'new_logistics_warehouse_2/factor/isProductlineOk.htm',
				'hasParameter': false,
				'status': '0', // 0 not start, 1 in progress, 2 success, 3 error
				'value': '',
				'valueType': 0
			},
			{
				'index': '1',
				'text': 'PA2: 生产所需要的人工是否有',
				'method': 'new_logistics_warehouse_2/factor/isHumanCostOk.htm',
				'hasParameter': false,
				'status': '0',
				'value': '',
				'valueType': 0
			},
			{
				'index': '2',
				'text': 'PA3: 本地生产所需的物流是否供应',
				'method': 'new_logistics_warehouse_2/factor/isLogisticsOk.htm',
				'hasParameter': false,
				'status': '0',
				'value': '',
				'valueType': 0
			},
			{
				'index': '3',
				'text': 'PA4: 新产线的组成是否对其它生产有影响',
				'method': 'new_logistics_warehouse_2/factor/isChangeOtherPl.htm',
				'hasParameter': false,
				'status': '0',
				'value': '',
				'valueType': 0
			}
		];
	}

	function get_list2() {
	return [{
			'index': '0',
			'text': 'UA1: 待生产的任务列表',
			'method': '',
			'status': '0', // 0 not start, 1 in progress, 2 success, 3 error
			'value': []
		},
		{
			'index': '1',
			'text': 'UA2: 供货时间',
			'method': '',
			'status': '0',
			'value': []
		},
		{
			'index': '2',
			'text': 'UA3: 每一任务的生产成本',
			'method': '',
			'status': '0',
			'value': []
		},
		{
			'index': '3',
			'text': 'UA4: 每一任务的生产收益',
			'method': '',
			'status': '0',
			'value': []
		},
		{
			'index': '4',
			'text': 'UA5: 每一任务的优先级权重',
			'method': '',
			'status': '0',
			'value': []
		},
		{
			'index': '5',
			'text': 'UA6: 每一任务所来客户的优先级权重',
			'method': '',
			'status': '0',
			'value': []
		}
	];
}

function mockpriorityData() {
	return [{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","ord_pa":352000,"customer_name":"王五","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","ord_pa":313600,"customer_name":"张三","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"},{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","ord_pa":242870,"customer_name":"李四","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}];
}
// 可用设备功能 及 加工时间
//http: //127.0.0.1:8080/new_logistics_warehouse_2/factor/availableFuction.htm?orderId=O00001

var mockFunctions = {
	"title": "鍙敤鐨勮澶囧姛鑳?",
	"list": [{
		"machine_cost": 20,
		"id": "mf0001",
		"machine_name": "Harting I",
		"elapsed_time": 4,
		"function_id": "mf0001",
		"is_available": "1",
		"accessory_name": "ZS1",
		"function_remark": "HT1",
		"function_name": "HT1",
		"human_cost": 0.8,
		"accessory_id": "a00001",
		"machine_id": "m0001"
	}, {
		"machine_cost": 18,
		"id": "mf0002",
		"machine_name": "Harting I",
		"elapsed_time": 3,
		"function_id": "mf0002",
		"is_available": "1",
		"accessory_name": "ZS2",
		"function_remark": "HT2",
		"function_name": "HT2",
		"human_cost": 0.6,
		"accessory_id": "a00002",
		"machine_id": "m0001"
	}]
}

//可用的设备
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/availableMachine.htm?orderId=O00001
var mockDevices = {
	"title": "鍙敤鐨勮澶?",
	"list": [{
		"machine_name": "Harting I",
		"is_available": "1",
		"belong_product_line": "pl0001",
		"machine_id": "m0001"
	}, {
		"machine_name": "Bohren  I",
		"is_available": "1",
		"belong_product_line": "pl0001",
		"machine_id": "m0002"
	}, {
		"machine_name": "Schleifen  I ",
		"is_available": "1",
		"belong_product_line": "pl0001",
		"machine_id": "m0003"
	}]
}

//可用的生产物料
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/availableMaterial.htm?orderId=O00001
var mockMaterials = {
	"title": "鍙敤鐨勭敓浜х墿鏂?",
	"list": [{
		"material_name": "Zugstange",
		"company_id": null,
		"company_name": null,
		"is_material": "1",
		"material_id": "m0001",
		"material_num": 7500
	}, {
		"material_name": "Staubschutzh眉lle",
		"company_id": null,
		"company_name": null,
		"is_material": "1",
		"material_id": "m0002",
		"material_num": 7500
	}, {
		"material_name": "Zahnstange ",
		"company_id": null,
		"company_name": null,
		"is_material": "1",
		"material_id": "m0003",
		"material_num": 7500
	}, {
		"material_name": "Stahlblech ",
		"company_id": null,
		"company_name": null,
		"is_material": "1",
		"material_id": "m0004",
		"material_num": 8000
	}]
}

// 可用的生产配件
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/availableAccessory.htm?orderId=O00001

var mockAccessory = {
	"title": "鍙敤鐨勭敓浜ч厤浠?",
	"list": [{
		"accessory_num": 0,
		"accessory_name": "ZS1",
		"is_buy": "0",
		"accessory_id": "a00001"
	}, {
		"accessory_num": 0,
		"accessory_name": "GH2",
		"is_buy": "0",
		"accessory_id": "a00002"
	}, {
		"accessory_num": 0,
		"accessory_name": "ZS2",
		"is_buy": "0",
		"accessory_id": "a00004"
	}, {
		"accessory_num": 0,
		"accessory_name": "GH1",
		"is_buy": "0",
		"accessory_id": "a00005"
	}]
}

// 产线是否构成
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/isProductlineOk.htm
var mockProductline = {
	"title": "浜х嚎鏄惁鏋勬垚",
	"result": true
}

//  人力是否足够
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/isHumanCostOk.htm
var mockHumanCost = {
	"title": "浜х嚎鏄惁鏋勬垚",
	"result": true
}

// 物流是否支持
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/isLogisticsOk.htm
var mockLogistics = {
	"title": "浜х嚎鏄惁鏋勬垚",
	"result": true
}

//  新产线是否影响其他产线
//http://127.0.0.1:8080/new_logistics_warehouse_2/factor/isChangeOtherPl.htm
var mockChangeOther = {
	"title": "浜х嚎鏄惁鏋勬垚",
	"result": true
}