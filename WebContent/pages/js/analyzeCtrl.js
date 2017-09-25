app.controller('analyzeCtrl', function($scope, $http, $log) {
	$scope.l_step = 0; // 0 for L0, 1 for L1, 2 for L2
	$scope.order = {
		'orderid': ''
	};
	//	Priority modal 
	$scope.isInitialized = false;

	$scope.showDialog = function(name) {
		$("#" + name).modal({
			keyboard: false,
			backdrop: false
		});
	}
	$scope.init = function() {
		$scope.showDialog('processModal');
		$scope.l_step = 0;
		$scope.l0list = get_list0();
		$scope.l1list = get_list1();
		$scope.l2list = get_list2();
		//$("#primaryButton").button('loading')
		processRequest(0);
	};
	$scope.init();

	$scope.processLayer = function(e) {

		//TODO:
	}

	$scope.switchTab = function(obj, val) {
		$scope.l_step = val;
		obj.target.blur();
	};

	var singleRequestFlag = false;

	function processRequest(step) {
		$scope.isInitialized = false;
		var params = getParams(step);
		(function iterator(i) {
			if(i == params.length) {
				//to do something.
				step = step + 1;
				$scope.l_step = step;
				if(step < 4) {
					processRequest(step);
				} else {
					//$log.error('*');
					analysisPriority();
					$scope.l_step = 3;
				}
				return;
			}
			checkFlag();
			singleRequestFlag = false;
			updateStatus(step, i, 1);
			var requestUrl = baseUrl + params[i].method;
			if(params[i].hasParameter) {
				requestUrl += $scope.orderid;
			}
			$http.get(requestUrl).then(function(response) {
				setTimeout(function() {
					$scope.$apply(function() {
						updateStatus(step, i, 2);
						updateValue(step, i, response);
						$log.info('Pass=========' + i + ' ' + step);
						singleRequestFlag = true;
						i = i + 1;
						iterator(i);
					});
				}, 500);
			}, function(errorData) {
				$log.info('Error=========' + errorData);
				setTimeout(function() {
					//alert(123); 
					$scope.$apply(function() {
						updateValue(step, i, 'item');
						updateStatus(step, i, 2);
						singleRequestFlag = true;
						i = i + 1;
						iterator(i);
					});
				}, 500);
			});
		})(0);
	}

	function checkFlag() {
		if(singleRequestFlag == false) {
			window.setTimeout(checkFlag, 200); /* this checks the flag every 100 milliseconds*/
		} else {
			/* do something*/
		}
	}

	function getParams(step) {
		if(step == 0) {
			return $scope.l0list;
		}
		if(step == 1) {
			return $scope.l1list;
		}
		if(step == 2) {
			return $scope.l2list;
		}
		return [];
	}

	function updateStatus(step, i, val) {

		if(step == 0) {
			$scope.l0list[i].status = val;
		}
		if(step == 1) {
			$scope.l1list[i].status = val;
		}
		if(step == 2) {
			//L2 很特别很特别很特别特别特别。。。。。
			$scope.l2list[i].status = val;
		}
	}

	function updateValue(step, i, val) {
		//TODO:
		if(step == 0) {
			switch(i) {
				case 0:
					val = mockFunctions;
					angular.forEach(val.list, function(item) {
						$scope.l0list[0].value.push({
							'text': item.function_name,
							'text1': item.elapsed_time + '秒',
							'status': item.is_available
						});
					});
					break;

				case 1:
					val = mockDevices;
					angular.forEach(val.list, function(item) {
						$scope.l0list[1].value.push({
							'text': item.machine_name,
							'status': item.is_available
						});
					});
					break;
				case 2:
					val = mockMaterials;
					angular.forEach(val.list, function(item) {
						$scope.l0list[2].value.push({
							'text': item.material_name,
							'status': item.is_material
						});
					});
					break;
				case 3:
					val = mockAccessory;
					angular.forEach(val.list, function(item) {
						$scope.l0list[3].value.push({
							'text': item.accessory_name,
							'status': item.is_buy
						});
					});
					break;
				default:
					break;
			}
		}
		if(step == 1) {

			val = mockProductline;
			$scope.l1list[i].value = val.result ? "是" : "否";
			//$log.info($scope.l1list[i].value.length+"-------------------");
		}
		if(step == 2) {
			//TODO:Remove L2
			$scope.l2list[i].value = '';
		}
	}

	$scope.productData = [];

	function analysisPriority() {
		$scope.productData.length = 0;
		var requestUrl = baseUrl + "new_logistics_warehouse_2/factor/waitingTasklist.htm?orderId=" + $scope.orderid;
		$http.get(requestUrl).then(function(response) {
			$scope.isInitialized=true;
			response.data = priorityData;
			setTimeout(function() {
				$scope.$apply(function() {
					$log.info('Analysis Pass=========');
					angular.forEach(response.data.list, function(data) {
						$scope.productData.push(new analysisData(data))
					});
				});
			}, 1000);
		}, function(errorData) {
			$scope.isInitialized=true;
			$log.info('Error=========' + errorData);
			setTimeout(function() {
				$scope.$apply(function() {
					//$log.info('Analysis Pass=========');
					//TODO: remvoe mock data
					angular.forEach(priorityData.list, function(data) {
						$scope.productData.push(new analysisData(data))
					});
				});
			}, 1000);
		});
	}

	var analysisData = function(data) {
		var model = this;
		model.order_no = data.order_no;
		model.product_name = data.product_name;
		model.total_amount = data.total_amount;
		model.total_cost = data.total_cost;
		model.factor1 = data.factor1; // 订单收益 C1
		model.customer_important = data.customer_important; // 客户重要性 C2
		model.factor2 = data.factor2; //订单重要性 C3
		model.order_end_date = data.order_end_date;
		model.product_num = data.product_num;
		model.product_id = data.product_id;
		model.product_price = data.product_price;
	}

})