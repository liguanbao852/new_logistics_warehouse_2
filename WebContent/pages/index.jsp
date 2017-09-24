<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
		
		<script type="text/javascript" >
			var path = '<%=path %>';	
		</script>
		
		<script type="text/javascript" src="<%=path %>/pages/js/factory.js"></script>
		<link rel="stylesheet" href="<%=path %>/pages/css/factory.css" />
	</head>

	<body ng-app="homeFactory" ng-controller="homeCtrl" >
		<div class="col-xs-12 col-sm-12 col-md-12"  style="height: 50px;">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            				<span class="sr-only">Toggle navigation</span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
          				</button>
						<a class="navbar-brand aHeader" href="#">排产管理列表</a>
						<a class="navbar-brand aHeader" href="newOptResult.html" style="display: none;" ng-class="{'showDetail':newResult==1}">优化产线结果</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<form class="navbar-form navbar-right" role="form">
							<div class="form-group">
								<input type="text" placeholder="Email" class="form-control">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Password" class="form-control">
							</div>
							<button type="submit" class="btn btn-success">Sign in</button>
						</form>
					</div>
					<!--/.navbar-collapse -->
				</div>
			</nav>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 80%;">
			<div class="col-xs-12  col-sm-12 col-md-12">
				<ul class="col-xs-12  col-sm-12 col-md-12 nav nav-pills factoryUl">
					<li ng-click="oldState=1" ng-class="{'selectStyle':oldState==1}">
						<a ng-click="getCustomerOrderList()" href="#">客户订单中心</a>
					</li>
					<li ng-click="oldState=2" ng-class="{'selectStyle':oldState==2}">
						<a ng-click="getPurchaseMaterialList()" href="#">采购订单详情</a>
					</li>
					<li ng-click="oldState=3" ng-class="{'selectStyle':oldState==3}">
						<a ng-click="getCurrentOrder()" href="#">生产列表</a>
					</li>
					<li ng-click="oldState=4" ng-class="{'selectStyle':oldState==4}">
						<a ng-click="getConfig()" href="#">产线配置</a>
					</li>
					<li ng-click="oldState=5" ng-class="{'selectStyle':oldState==5}">
						<a ng-click="getDeviceStatus()" href="#">设备功能运行状况</a>
					</li>
					<li ng-click="oldState=6" ng-class="{'selectStyle':oldState==6}">
						<a ng-click="getOrderStatus()" href="#">加工订单状态</a>
					</li>
					<li ng-click="oldState=7" ng-class="{'selectStyle':oldState==7}">
						<a ng-click="getProductLine()" href="#">常规排产</a>
					</li>
				</ul>
			</div>
			<div class="col-xs-12  col-sm-12 col-md-12">
				<div class="well well-lg" style="height: 100%;overflow: auto;">
					<div style="display: none;" ng-class="{'showDetail':oldState==1}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：订单列表
                       -->
						<table class="table">
							<thead>
								<tr>
									<th class="td">订单号</th>
									<th class="td">客户名</th>
									<th class="td">订购物料</th>
									<th class="td">数量</th>
									<th class="td">订单总额</th>
									<th class="td">订货日期</th>
									<th class="td">交货日期</th>
									<th class="td">正在生产</th>
									<th class="td">是否完成</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in orders">
									<td class="td">
										<a class="td">{{item.order_no}}</textarea>
									</td>
									<td class="td">
										<a class="td">{{item.order_cust}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.materialName}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.product_num}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.total}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.startDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.endDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.isRunning}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.isCompleted}}</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==2}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：订单详情
                        -->
						<table class="table">
							<thead>
								<tr>
									<th class="td">采购单位</th>
									<th class="td">采购物料</th>
									<th class="td">采购数量</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in orderdetails">
									<td class="td">
										<a>{{item.purchaseName}}</a>
									</td>
									<td class="td">
										<a>{{item.purchaseDetail}}</a>
									</td>
									<td class="td">
										<a>{{item.purchaseNumber}}</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==3}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：正在生产的订单
                       -->
						<table class="table">
							<thead>
								<tr>
									<th class="td">订单号</th>
									<th class="td">客户名</th>
									<th class="td">订购物料</th>
									<th class="td">数量</th>
									<th class="td">订单总额</th>
									<th class="td">订货日期</th>
									<th class="td">交货日期</th>
									<th class="td">正在生产</th>
									<th class="td">是否完成</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in runningorder">
									<td class="td">
										<a class="td">{{item.id}}</textarea>
									</td>
									<td class="td">
										<a class="td">{{item.customerName}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.materialName}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.numbers}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.total}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.startDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.endDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.isRunning}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.isCompleted}}</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==4}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：产线配置
                       -->
						<div class="col-xs-6  col-sm-6 col-md-6 pTitle">Product der Zahnstange</div>
						<div class="col-xs-6  col-sm-6 col-md-6 pTitle">Product der Gehause</div>
						<div class="col-xs-6  col-sm-6 col-md-6" style="height: 40%;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Harting</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" ng-value="1" ng-model="hartingValue" >HT1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" ng-value="2" ng-model="hartingValue">HT2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" ng-value="3" ng-model="hartingValue">HT3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" ng-value="1" ng-model="bohrenValue">BR1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" ng-value="2" ng-model="bohrenValue">BR2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" ng-value="3" ng-model="bohrenValue">BR3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schleifen</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" ng-value="1" ng-model="schleifenValue">SL1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" ng-value="2" ng-model="schleifenValue">SL2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" ng-value="3" ng-model="schleifenValue">SL3</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>

						<div class="col-xs-6  col-sm-6 col-md-6" style="height: 40%;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Themerform</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" ng-value="1" ng-model="themerformValue">TF1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" ng-value="2" ng-model="themerformValue">TF2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" ng-value="3" ng-model="themerformValue">TF3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren II</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" ng-value="1" ng-model="bohreniiValue">BR1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" ng-value="2" ng-model="bohreniiValue">BR2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" ng-value="3" ng-model="bohreniiValue">BR3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schneiden I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" ng-value="1" ng-model="schneidenValue">SD1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" ng-value="2" ng-model="schneidenValue">SD2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" ng-value="3" ng-model="schneidenValue">SD3</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>

						<div class="col-xs-12  col-sm-12 col-md-12" style="height: 40%;margin-top: 5px;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Assembly GH</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" ng-value="1" ng-model="assemblyGhValue">GH1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" ng-value="2" ng-model="assemblyGhValue">GH2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" ng-value="3" ng-model="assemblyGhValue">GH3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1 " style="height:60px;font-size: 70%;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Assembly ZT</p>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1 " style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Assembly SH</p>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>
						<div class="col-xs-12  col-sm-12 col-md-12" style="font-weight: bolder;font-size: 15px;text-align: center;">Assembly des Lenkungssystemes</div>

					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==5}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：设备功能运行
                       -->
						<div class="col-xs-6  col-sm-6 col-md-6 pTitle">Product der Zahnstange</div>
						<div class="col-xs-6  col-sm-6 col-md-6 pTitle">Product der Gehause</div>
						<div class="col-xs-6  col-sm-6 col-md-6" style="height: 40%;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Harting</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" value="HT1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" value="HT2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="ht" value="HT3">3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" value="BR1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" value="BR2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="bre" value="BR3">3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schleifen</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" value="SL1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" value="SL2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sl" value="SL3">3</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>

						<div class="col-xs-6  col-sm-6 col-md-6" style="height: 40%;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Themerform</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" value="TF1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" value="TF2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="tf" value="TF3">3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren II</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" value="BR1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" value="BR2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="br2" value="BR3">3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schneiden I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" value="SD1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" value="SD2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="sd" value="SD3">3</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>

						<div class="col-xs-12  col-sm-12 col-md-12" style="height: 40%;margin-top: 5px;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Assembly GH</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" value="gh1">1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" value="gh2">2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="gh" value="gh3">3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1 " style="height:60px;font-size: 70%;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Assembly ZT</p>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1 " style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Assembly SH</p>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>
						<div class="col-xs-12  col-sm-12 col-md-12" style="font-weight: bolder;font-size: 15px;text-align: center;">Assembly des Lenkungssystemes</div>

					</div>	
					<div style="display: none;" ng-class="{'showDetail':oldState==6}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：加工订单状态
                       -->
						<h1>目前已生产了200个LS1, 100个LS2</h1>
						<h1>还差300个LS1,400个LS2未生产</h1>
					</div>	
					<div style="display: none;" ng-class="{'showDetail':oldState==7}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：常规排产
                       -->
						<h1>订单中需要的产品：LS1 = ZS1 + GH2 + ZT</h1>
						<h1>订单中需要的产品：LS2 = ZS2 + GH1 + ZT + SH</h1>
						<h2>其中部件来自Product der Zahnstange产线：ZS1 = HT1 + BR2 + SL2</h2>
						<h2>其中部件来自Product der Zahnstange产线：ZS2 = HT2 + BR3 + SL1</h2>
						<h2>其中部件来自Product der Gehause产线：GH1 = TF1 + BR1 + SD2</h2>
						<h2>其中部件来自Product der Gehause产线：GH2 = TF2 + BR1 + SD1</h2>
						<h2>外购部件为： ZT和SH</h2>
					</div>				
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 10px;margin-bottom: 10px;height: 40px;">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<button type="button" ng-click="newResult=1" class="col-xs-12 col-sm-12 col-md-12 btn btn-lg" style="background-color: gray;">开始排产优化					
				</button>
			</div>
		</div>
	</body>

</html>