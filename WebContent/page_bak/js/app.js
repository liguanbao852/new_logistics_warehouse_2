var app = angular.module('product', []);

var baseUrl = "http://127.0.0.1:8080/";

function get_list0() {
	return [{
			'index': '0',
			'text': 'AA1: 可用的功能及加工时间',
			'method': 'new_logistics_warehouse_2/factor/availableFuction.htm?orderId=',
			'hasParameter': true,
			'status': '0', // 0 not start, 1 in progress, 2 success, 3 error
			'value': [],
			'valueType':1
		},
		{
			'index': '1',
			'text': 'AA2: 可用的设备',
			'method': 'new_logistics_warehouse_2/factor/availableMachine.htm?orderId=',
			'hasParameter': true,
			'status': '0',
			'value': [],
			'valueType':1
		},
		{
			'index': '2',
			'text': 'AA3: 可用的生产物料',
			'method': 'new_logistics_warehouse_2/factor/availableMaterial.htm?orderId=',
			'hasParameter': true,
			'status': '0',
			'value': [],
			'valueType':1
		},
		{
			'index': '3',
			'text': 'AA4可用的生产配件',
			'method': 'new_logistics_warehouse_2/factor/availableAccessory.htm?orderId=',
			'hasParameter': true,
			'status': '0',
			'value': [],
			'valueType':1
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
			'valueType':0
		},
		{
			'index': '1',
			'text': 'PA2: 生产所需要的人工是否有',
			'method': 'new_logistics_warehouse_2/factor/isHumanCostOk.htm',
			'hasParameter': false,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '2',
			'text': 'PA3: 本地生产所需的物流是否供应',
			'method': 'new_logistics_warehouse_2/factor/isLogisticsOk.htm',
			'hasParameter': false,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '3',
			'text': 'PA4: 新产线的组成是否对其它生产有影响',
			'method': 'new_logistics_warehouse_2/factor/isChangeOtherPl.htm',
			'hasParameter': false,
			'status': '0',
			'value': '',
			'valueType':0
		}
	];
}

function get_list2() {
	return [{
			'index': '0',
			'text': 'UA1: 待生产的任务列表',
			'method': 'new_logistics_warehouse_2/factor/waitingTasklist.htm?orderId=',
			'hasParameter': true,
			'status': '0', // 0 not start, 1 in progress, 2 success, 3 error
			'value': '',
			'valueType':0
		},
		{
			'index': '1',
			'text': 'UA2: 供货时间',
			'method': '',
			'hasParameter': true,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '2',
			'text': 'UA3: 每一任务的生产成本',
			'method': '',
			'hasParameter': true,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '3',
			'text': 'UA4: 每一任务的生产收益',
			'method': '',
			'hasParameter': true,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '4',
			'text': 'UA5: 每一任务的优先级权重',
			'method': '',
			'hasParameter': true,
			'status': '0',
			'value': '',
			'valueType':0
		},
		{
			'index': '5',
			'text': 'UA6: 每一任务所来客户的优先级权重',
			'method': '',
			'hasParameter': true,
			'status': '0',
			'value': '',
			'valueType':0
		}
	];
}