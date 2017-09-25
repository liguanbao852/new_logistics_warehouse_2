var app = angular.module("homeFactory", []);
app.controller("homeCtrl", function($scope, $http,$log) {
	//config
	$scope.pageNum = 0;
	$scope.hartingValue = 2;
	$scope.schleifenValue = 1;
	$scope.bohrenValue = 1;
	$scope.schneidenValue = 2;
	$scope.bohreniiValue = 1;
	$scope.themerformValue = 3;
	$scope.assemblyGhValue = 1;
	$scope.oldState = 1;
	$scope.newState = 1;
	
	
	//本地生产优化措施
	$scope.localCompanySuggestion = "";
	//采购优化措施
	$scope.prioritySuggestion = "";
	// 合作公司生产优化措施
	$scope.cooperationSuggestion = "";
	//所有订单中心
	$scope.orders = [
		{id:'O00001',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:true,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"},
		{id:'O00002',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"},
		{id:'O00003',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"},
		{id:'O00004',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"}
	];
	//订单内容
	$scope.purchaseMaterials = [
		{company_name:"Firma X", material_name:"ZT", material_num: 600},
		{company_name:"Firma Y", material_name:"ZT", material_num: 600}	
	];
	//正在运行的订单
	$scope.runningorder = [
		{id:'O00001',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"}
	];
	
	//配置产线
	$scope.configs = [];
	
	//优先级订单
	$scope.priorityOrders = [
		{id:'O00001',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000", orderTotal:70000}
	];

	var orderModel = function(item) {
		var obj = this;
		obj.id = item.order_no;
		obj.customerName = item.order_name;
		obj.startDate = item.order_begin_date;
		obj.endDate = item.order_end_date;
		obj.isRunning = false;
		obj.isCompleted = false;
		obj.materialNameAndNum = "";
		obj.orderPrice = 0;
	}
	
	$scope.getCustomerOrderList = function() {
	//	$scope.orders = [];
		//获取订单列表
		var url1 = path + "/order/queryOrderList.htm";
		$http.get(url1).success(function(response) {
			// 请求成功执行代码
			/*console.log("call getCustomerOrderList true.");
			response = null;
			var list = angular.fromJson(response);
			angular.forEach(response,function(item,index,response){
				
				$scope.orders.push(new orderModel(item));
			});
			$scope.orders = angular.fromJson(response);*/
			
			$scope.orders = [
				         		{id:'O00001',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:true,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"},
				         		{id:'O00002',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"},
				         		{id:'O00003',customerName:'test',startDate:'2017-09-23',endDate:'2017-09-24',isRunning:true,isCompleted:false,orderPrice: 6500,materialNameAndNum:"LS2-500, LS3-1000"}		         	];
			
		}).error(function(response) {
			// 请求失败执行代码
			var result = angular.fromJson({"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"李四","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"王五","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"张三","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]});
			
			var list = result.toArray;
			angular.forEach(result,function(item,index,array){
				console.log(item);
				$scope.orders.push(new orderModel(item));
			});
			console.log("call getCustomerOrderList false.");
		});		
	}

	$scope.getPurchaseMaterialList = function() {
//		$scope.purchaseMaterials = [];
//		$http.get("newOptResult1.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getPurchaseMaterialList true.");
//			var list = angular.fromJson(response);
//
//			$scope.purchaseMaterials = angular.fromJson(response);
//		}).error(function(response) {
//			// 请求失败执行代码
//			
//			var result = mockPurchaseMaterialList();
//			var list = result.toArray;
//			angular.forEach(result,function(item,index,list){
//				$scope.purchaseMaterials.push(item);
//			});
//			console.log("call getCustomerOrderList false.");
//		});
	}

	$scope.getCurrentOrder = function() {
//		$scope.runningorder = [];
//		$http.get("newOptResult1.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCustomerOrderList true.");
//			response = '{"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"鏉庡洓","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"鐜嬩簲","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"寮犱笁","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]}';
//			var list = angular.fromJson(response);
//			angular.forEach(response,function(item,index,response){
//				var obj = new Object();
//				obj.id = item.order_no;
//				obj.customerName = item.order_name;
//				obj.materialNameAndNum = new Array();
//				var list = item.orderProductInfoList;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.materialNameAndNum.push(data.productName + '-' + data.productNum);
//				});
//				obj.orderPrice = 0;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.orderPrice += data.productPrice * data.productNum;
//				});
//				obj.startDate = item.order_begin_date.year + '-' + item.order_begin_date.month + '-' + item.order_begin_date.day;
//				obj.endDate = item.order_end_date.year + '-' + item.order_end_date.month + '-' + item.order_end_date.day;
//				obj.isRunning = false;
//				obj.isCompleted = false;
//				$scope.runningorder.push(obj);
//			});
//			
//		}).error(function(response) {
//			// 请求失败执行代码
//			var result = [
//      {
//          "order_end_date":{
//              "date":27,
//              "day":3,
//              "hours":10,
//              "minutes":19,
//              "month":8,
//              "nanos":0,
//              "seconds":8,
//              "time":1506478748000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "order_name":"A2",
//          "customer_name":"李四",
//          "order_begin_date":{
//              "date":14,
//              "day":4,
//              "hours":10,
//              "minutes":19,
//              "month":8,
//              "nanos":0,
//              "seconds":0,
//              "time":1505355540000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "orderProductInfoList":[
//              {
//                  "isFull":"0",
//                  "orderNo":"O00002",
//                  "productCnt":0,
//                  "productId":"p00001",
//                  "productName":"LS1",
//                  "productNum":1000,
//                  "productPrice":400
//              },
//              {
//                  "isFull":"0",
//                  "orderNo":"O00002",
//                  "productCnt":0,
//                  "productId":"p00003",
//                  "productName":"LS3",
//                  "productNum":900,
//                  "productPrice":420
//              }
//          ],
//          "is_full":"0",
//          "order_cust":"C00002",
//          "order_no":"O00002"
//      },
//      {
//          "order_end_date":{
//              "date":28,
//              "day":4,
//              "hours":10,
//              "minutes":39,
//              "month":8,
//              "nanos":0,
//              "seconds":38,
//              "time":1506566378000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "order_name":"A3",
//          "customer_name":"王五",
//          "order_begin_date":{
//              "date":20,
//              "day":3,
//              "hours":10,
//              "minutes":39,
//              "month":8,
//              "nanos":0,
//              "seconds":24,
//              "time":1505875164000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "orderProductInfoList":[
//              {
//                  "isFull":"0",
//                  "orderNo":"O00003",
//                  "productCnt":0,
//                  "productId":"p00002",
//                  "productName":"LS2",
//                  "productNum":1000,
//                  "productPrice":600
//              },
//              {
//                  "isFull":"0",
//                  "orderNo":"O00003",
//                  "productCnt":0,
//                  "productId":"p00003",
//                  "productName":"LS3",
//                  "productNum":1000,
//                  "productPrice":450
//              }
//          ],
//          "is_full":"0",
//          "order_cust":"C00003",
//          "order_no":"O00003"
//      },
//      {
//          "order_end_date":{
//              "date":28,
//              "day":4,
//              "hours":14,
//              "minutes":34,
//              "month":8,
//              "nanos":0,
//              "seconds":20,
//              "time":1506580460000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "order_name":"A1",
//          "customer_name":"张三",
//          "order_begin_date":{
//              "date":13,
//              "day":3,
//              "hours":14,
//              "minutes":34,
//              "month":8,
//              "nanos":0,
//              "seconds":14,
//              "time":1505284454000,
//              "timezoneOffset":-480,
//              "year":117
//          },
//          "orderProductInfoList":[
//              {
//                  "isFull":"0",
//                  "orderNo":"O00001",
//                  "productCnt":0,
//                  "productId":"p00001",
//                  "productName":"LS1",
//                  "productNum":1000,
//                  "productPrice":400
//              },
//              {
//                  "isFull":"0",
//                  "orderNo":"O00001",
//                  "productCnt":0,
//                  "productId":"p00002",
//                  "productName":"LS2",
//                  "productNum":800,
//                  "productPrice":600
//              }
//          ],
//          "is_full":"0",
//          "order_cust":"C00001",
//          "order_no":"O00001"
//      }
//  ];
//			var list = result[0].toArray;
//			angular.forEach(result,function(item,index,list){
//				var obj = new Object();
//				obj.id = item.order_no;
//				obj.customerName = item.order_name;
//				obj.materialNameAndNum = new Array();
//				var list = item.orderProductInfoList;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.materialNameAndNum.push(data.productName + '-' + data.productNum);
//				});
//				obj.orderPrice = 0;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.orderPrice += data.productPrice * data.productNum;
//				});
//				obj.startDate = item.order_begin_date.year + '-' + item.order_begin_date.month + '-' + item.order_begin_date.day;
//				obj.endDate = item.order_end_date.year + '-' + item.order_end_date.month + '-' + item.order_end_date.day;
//				obj.isRunning = false;
//				obj.isCompleted = false;
//				$scope.runningorder.push(obj);
//			});
//			console.log("call getCurrentOrder false.");
//		});
	}

	$scope.getConfig = function() {
//		$scope.configs = [];
//		$http.get("newOptResult.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCustomerOrderList true.");
//			response = '{"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"鏉庡洓","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"鐜嬩簲","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"寮犱笁","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]}';
//			var list = angular.fromJson(response);
//
//			$scope.configs = angular.fromJson(response);
//		}).error(function(response) {
//			// 请求失败执行代码
//			console.log("call getCustomerOrderList false.");
//		});
	}

	$scope.getDeviceStatus = function() {
//		$http.get("newOptResult.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCustomerOrderList true.");
//			response = '{"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"鏉庡洓","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"鐜嬩簲","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"寮犱笁","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]}';
//			var list = angular.fromJson(response);
//
//			$scope.orders = angular.fromJson(response);
//		}).error(function(response) {
//			// 请求失败执行代码
//			console.log("call getCustomerOrderList false.");
//		});
	}

	$scope.getOrderStatus = function() {
//		$http.get("newOptResult.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCustomerOrderList true.");
//			response = '{"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"鏉庡洓","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"鐜嬩簲","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"寮犱笁","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]}';
//			var list = angular.fromJson(response);
//
//			$scope.orders = angular.fromJson(response);
//		}).error(function(response) {
//			// 请求失败执行代码
//			console.log("call getCustomerOrderList false.");
//		});
	}

	$scope.getProductLine = function() {
//		$http.get("newOptResult.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCustomerOrderList true.");
//			response = '{"orderInfoList":[{"order_end_date":{"date":27,"day":3,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":8,"time":1506478748000,"timezoneOffset":-480,"year":117},"order_name":"A2","customer_name":"鏉庡洓","order_begin_date":{"date":14,"day":4,"hours":10,"minutes":19,"month":8,"nanos":0,"seconds":0,"time":1505355540000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00002","productCnt":0,"productId":"p00003","productName":"LS3","productNum":900,"productPrice":420}],"is_full":"0","order_cust":"C00002","order_no":"O00002"},{"order_end_date":{"date":28,"day":4,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":38,"time":1506566378000,"timezoneOffset":-480,"year":117},"order_name":"A3","customer_name":"鐜嬩簲","order_begin_date":{"date":20,"day":3,"hours":10,"minutes":39,"month":8,"nanos":0,"seconds":24,"time":1505875164000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00002","productName":"LS2","productNum":1000,"productPrice":600},{"isFull":"0","orderNo":"O00003","productCnt":0,"productId":"p00003","productName":"LS3","productNum":1000,"productPrice":450}],"is_full":"0","order_cust":"C00003","order_no":"O00003"},{"order_end_date":{"date":28,"day":4,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":20,"time":1506580460000,"timezoneOffset":-480,"year":117},"order_name":"A1","customer_name":"寮犱笁","order_begin_date":{"date":13,"day":3,"hours":14,"minutes":34,"month":8,"nanos":0,"seconds":14,"time":1505284454000,"timezoneOffset":-480,"year":117},"orderProductInfoList":[{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00001","productName":"LS1","productNum":1000,"productPrice":400},{"isFull":"0","orderNo":"O00001","productCnt":0,"productId":"p00002","productName":"LS2","productNum":800,"productPrice":600}],"is_full":"0","order_cust":"C00001","order_no":"O00001"}]}';
//			var list = angular.fromJson(response);
//
//			$scope.orders = angular.fromJson(response);
//		}).error(function(response) {
//			// 请求失败执行代码
//			console.log("call getCustomerOrderList false.");
//		});
	}

	$scope.getPriorityOrders = function() {
//		$scope.priorityOrders = [];
//		$http.get("newOptResul1t.html").success(function(response) {
//			// 请求成功执行代码
//			var result = priorityData.newOrderInfoList;
//			var list = result.toArray;
//			angular.forEach(result,function(item,index,list){
//				var obj = new Object();
//				obj.id = item.order_no;
//				obj.customerName = item.order_name;
//				obj.materialNameAndNum = new Array();
//				var list = item.orderProductInfoList;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.materialNameAndNum.push(data.productName + '-' + data.productNum);
//				});
//				obj.orderPrice = 0;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.orderPrice += data.productPrice * data.productNum;
//				});
//				obj.startDate = item.order_begin_date.year + '-' + item.order_begin_date.month + '-' + item.order_begin_date.day;
//				obj.endDate = item.order_end_date.year + '-' + item.order_end_date.month + '-' + item.order_end_date.day;
//				obj.orderTotal = item.ord_pa;
//				$scope.priorityOrders.push(obj);
//			});
//			console.log("call getCustomerOrderList false.");
//		}).error(function(response) {
//			// 请求失败执行代码
//			var result = mockpriorityData();
//			var list = result.toArray;
//			angular.forEach(result,function(item,index,list){
//				var obj = new Object();
//				obj.id = item.order_no;
//				obj.customerName = item.order_name;
//				obj.materialNameAndNum = new Array();
//				var list = item.orderProductInfoList;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.materialNameAndNum.push(data.productName + '-' + data.productNum);
//				});
//				obj.orderPrice = 0;
//				angular.forEach(item.orderProductInfoList, function(data, index, list){
//					obj.orderPrice += data.productPrice * data.productNum;
//				});
//				
//				obj.orderTotal = item.ord_pa;
//				$scope.priorityOrders.push(obj);
//			});
//			console.log("call getCustomerOrderList false.");
//		});
	}
	

	
	$scope.getSuggestion = function(){
//		$scope.localCompanySuggestion = "";
//		$scope.prioritySuggestion = "";
//		
//		// 本地生产优化措施
//		$http.get("newOptResult1.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getSuggestion true.");
//			
//			var msg = angular.fromJson(response);
//
//			$scope.localCompanySuggestion = angular.fromJson(msg.newProductMeasures);
//		}).error(function(response) {
//			// 请求失败执行代码
//			$scope.localCompanySuggestion = angular.fromJson(mockLocalCompanyProductSuggestion().newProductMeasures);
//			console.log("call getSuggestion false.");
//		});
//		// 采购优化措施
//		$http.get("newOptResult1.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getSuggestion true.");
//			
//			var msg = angular.fromJson(response);
//
//			$scope.prioritySuggestion = angular.fromJson(msg.newProductMaterial);
//		}).error(function(response) {
//			// 请求失败执行代码
//			$scope.localCompanySuggestion = angular.fromJson(mockLocalCompanyPurchaseSuggestion().newProductMaterial);		
//			console.log("call getSuggestion false.");
//		});
	}
	
	$scope.getCoorationCompanySuggestion = function(){
//		$scope.cooperationSuggestion = "";
//		
//		// 合作公司生产优化措施
//		$http.get("newOptResult1.html").success(function(response) {
//			// 请求成功执行代码
//			console.log("call getCoorationCompanySuggestion true.");
//			
//			var msg = angular.fromJson(response);
//
//			$scope.cooperationSuggestion = angular.fromJson(msg.newCompanyMeasures);
//		}).error(function(response) {
//			// 请求失败执行代码
//			$scope.cooperationSuggestion = angular.fromJson(mockcoorationCompanySuggestion().newCompanyMeasures);
//			console.log("call getCoorationCompanySuggestion false.");
//		});
	}
	
	
	//Anasys prority progress.
	$scope.l_step = 0; // 0 for L0, 1 for L1, 2 for L2
	$scope.orderid = "O00001";
	//	Priority modal 
	$scope.isInitialized = false;

	$scope.showDialog = function(name) {
		$("#" + name).modal({
			keyboard: false,
			backdrop: false
		});
	}
	$scope.startAnalysisPrority = function() {
		$scope.showDialog('processModal');
		$scope.l_step = 0;
		$scope.l0list = get_list0();
		$scope.l1list = get_list1();
		$scope.l2list = get_list2();
		//$("#primaryButton").button('loading')
		processRequest(0);
	};

	$scope.processLayer = function(e) {

		//TODO:
	}
	
	function get_list0() {
		
		var uaa1_url= path+ "/factor/availableFuction.htm?orderId=";
		var uaa2_url= path+ "/factor/availableMachine.htm?orderId=";
		var uaa3_url= path+ "/factor/availableMaterial.htm?orderId=";
		var uaa4_url= path+ "/factor/availableAccessory.htm?orderId=";
		
	return [{
			'index': '0',
			'text': 'AA1: 可用的功能及加工时间',
			'method': uaa1_url,
			'hasParameter': true,
			'status': '2', // 0 not start, 1 in progress, 2 success, 3 error
			'value': [],
			'valueType':1
		},
		{
			'index': '1',
			'text': 'AA2: 可用的设备',
			'method': uaa2_url,
			'hasParameter': true,
			'status': '2',
			'value': [],
			'valueType':1
		},
		{
			'index': '2',
			'text': 'AA3: 可用的生产物料',
			'method': uaa3_url,
			'hasParameter': true,
			'status': '2',
			'value': [],
			'valueType':1
		},
		{
			'index': '3',
			'text': 'AA4可用的生产配件',
			'method': uaa4_url,
			'hasParameter': true,
			'status': '2',
			'value': [],
			'valueType':1
		}
	];
}

function get_list1() {
	var pa1_url = path + "/factor/isProductlineOk.htm";
	var pa2_url = path + "/factor/isHumanCostOk.htm";
	var pa3_url = path + "/factor/isLogisticsOk.htm";
	var pa4_url = path + "/factor/isChangeOtherPl.htm";

	return [{
			'index': '0',
			'text': 'PA1: 产线的组成是否构成',
			'method': pa1_url,
			'hasParameter': false,
			'status': '2', // 0 not start, 1 in progress, 2 success, 3 error
			'value': '',
			'valueType':0
		},
		{
			'index': '1',
			'text': 'PA2: 生产所需要的人工是否有',
			'method': pa2_url,
			'hasParameter': false,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '2',
			'text': 'PA3: 本地生产所需的物流是否供应',
			'method': pa3_url,
			'hasParameter': false,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '3',
			'text': 'PA4: 新产线的组成是否对其它生产有影响',
			'method': pa4_url,
			'hasParameter': false,
			'status': '2',
			'value': '',
			'valueType':0
		}
	];
}

function get_list2() {
	var ua1_url = path +"/factor/waitingTasklist.htm?orderId=";	
	
	return [{
			'index': '0',
			'text': 'UA1: 待生产的任务列表',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2', // 0 not start, 1 in progress, 2 success, 3 error
			'value': '',
			'valueType':0
		},
		{
			'index': '1',
			'text': 'UA2: 供货时间',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '2',
			'text': 'UA3: 每一任务的生产成本',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '3',
			'text': 'UA4: 每一任务的生产收益',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '4',
			'text': 'UA5: 每一任务的优先级权重',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2',
			'value': '',
			'valueType':0
		},
		{
			'index': '5',
			'text': 'UA6: 每一任务所来客户的优先级权重',
			'method': ua1_url,
			'hasParameter': true,
			'status': '2',
			'value': '',
			'valueType':0
		}
	];
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
			//checkFlag();
			singleRequestFlag = true;
			updateStatus(step, i, 1);
			var requestUrl = params[i].method;
			if(params[i].hasParameter) {
				requestUrl += $scope.orderid;
			}
			$http.get(requestUrl).success(function(response) {
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
			window.setTimeout(checkFlag, 2); /* this checks the flag every 100 milliseconds*/
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
					//val = mockFunctions;
					angular.forEach(val.list, function(item) {
						$scope.l0list[0].value.push({
							'text': item.function_name,
							'text1': item.elapsed_time + '秒',
							'status': item.is_available
						});
					});
					break;

				case 1:
					//val = mockDevices;
					angular.forEach(val.list, function(item) {
						$scope.l0list[1].value.push({
							'text': item.machine_name,
							'status': item.is_available
						});
					});
					break;
				case 2:
					//val = mockMaterials;
					angular.forEach(val.list, function(item) {
						$scope.l0list[2].value.push({
							'text': item.material_name,
							'status': item.is_material
						});
					});
					break;
				case 3:
					//val = mockAccessory;
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

			//val = mockProductline;
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
		var requestUrl = path + "/factor/waitingTasklist.htm?orderId=" + $scope.orderid;
		$http.get(requestUrl).success(function(response) {
			$scope.isInitialized=true;
//			response.data = response;
			var list = response.list;
			setTimeout(function() {
				$scope.$apply(function() {
					$log.info('Analysis Pass=========');
					angular.forEach(list, function(data) {
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

});

