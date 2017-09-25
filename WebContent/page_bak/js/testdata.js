var priorityData = {
	"title": "寰呯敓浜х殑浠诲姟鍒楄〃",
	"list": [{
		"order_end_date": {
			"date": 28,
			"day": 4,
			"hours": 10,
			"minutes": 39,
			"month": 8,
			"nanos": 0,
			"seconds": 38,
			"time": 1506566378000,
			"timezoneOffset": -480,
			"year": 117
		},
		"total_amount": 600000,
		"product_num": 1000,
		"product_id": "p00002",
		"product_price": 600,
		"total_cost": 480000,
		"product_name": "LS2",
		"customer_important": 0.9,
		"factor2": 0.1,
		"factor1": 0.6,
		"order_no": "O00003",
		"product_cnt": 0
	}, {
		"order_end_date": {
			"date": 28,
			"day": 4,
			"hours": 10,
			"minutes": 39,
			"month": 8,
			"nanos": 0,
			"seconds": 38,
			"time": 1506566378000,
			"timezoneOffset": -480,
			"year": 117
		},
		"total_amount": 450000,
		"product_num": 1000,
		"product_id": "p00003",
		"product_price": 450,
		"total_cost": 350000,
		"product_name": "LS3",
		"customer_important": 0.9,
		"factor2": 0.1,
		"factor1": 0.6,
		"order_no": "O00003",
		"product_cnt": 0
	}]
};

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