<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html style="height: 100%;">
	<head>
		<meta charset="utf-8" />
		<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
		<!--<link rel="stylesheet" href="css/font-awesome.min.css" />
		<meta name="viewport" content="width=device-width, initial-scale=1">-->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<script type="text/javascript">
			var path = '<%=path %>';
		</script>
		<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
		<script type="text/javascript" src="<%=path %>/pages/js/factory.js"></script>
		<link rel="stylesheet" href="<%=path %>/pages/css/factory.css" />
	</head>

	<body ng-app="homeFactory" ng-controller="homeCtrl" style="height: 100%;">
		<div class="col-xs-12 col-sm-12 col-md-12"  style="height: 10px;">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            				<span class="sr-only">Toggle navigation</span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
          				</button>
						<a class="navbar-brand aHeader" href="#" ng-click="pageNum=0">排产管理列表</a>
						<a class="navbar-brand aHeader" style="display: none;" href="#" ng-click="pageNum=1" ng-class="{'showDetail':pageNum==1}">优化产线结果</a>
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

		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 80%;display: none;" ng-class="{'showDetail':pageNum==0}">
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
						<a ng-click="getConfig()" href="#">产线配置和设备功能运行状况</a>
					</li>
					<li ng-click="oldState=5" ng-class="{'selectStyle':oldState==5}">
						<a ng-click="getOrderStatus()" href="#">加工订单状态</a>
					</li>
					<li ng-click="oldState=6" ng-class="{'selectStyle':oldState==6}">
						<a ng-click="getProductLine()" href="#">常规排产</a>
					</li>
					<li ng-click="oldState=7" ng-class="{'selectStyle':oldState==7}">
						<a ng-click="getDFHRSStatus()" href="#">DFHRS</a>
					</li>
				</ul>
			</div>
			<div class="col-xs-12  col-sm-12 col-md-12" style="height: 100%;">
				<div class="well well-lg" style="height: 100%;overflow: auto;overflow-x: auto;">
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
									<th class="td">订购物料-数量</th>
									<th class="td">订单总额</th>
									<th class="td">订货日期</th>
									<th class="td">交货日期</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in orders">
									<td class="td">
										<a class="td">{{item.id}}</textarea>
									</td>
									<td class="td">
										<a class="td">{{item.customerName}}</a>
									</td>
									<!--
                                    	作者：offline
                                    	时间：2017-09-24
                                    	描述：item.orderProductInfoList, TBD
                                    -->
									<td class="td">
										<a class="td">{{item.materialNameAndNum}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.orderPrice}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.startDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.endDate}}</a>
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
								<tr ng-repeat="item in purchaseMaterials">
									<td class="td">
										<a>{{item.company_name}}</a>
									</td>
									<td class="td">
										<a>{{item.material_name}}</a>
									</td>
									<td class="td">
										<a>{{item.material_num}}</a>
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
									<th class="td">订购物料-数量</th>
									<th class="td">订单总额</th>
									<th class="td">订货日期</th>
									<th class="td">交货日期</th>
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
									<!--
                                    	作者：offline
                                    	时间：2017-09-24
                                    	描述：item.orderProductInfoList, TBD
                                    -->
									<td class="td">
										<a class="td">{{item.materialNameAndNum}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.orderPrice}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.startDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.endDate}}</a>
									</td>
								</tr>
							</tbody>
						</table>

					</div>
					<div style="display: none;height:70%;" ng-class="{'showDetail':oldState==4}">
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
										<input type="radio" name="oldht" ng-value="1" ng-model="hartingValue">HT1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldht" ng-value="2" ng-model="hartingValue">HT2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldht" ng-value="3" ng-model="hartingValue">HT3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbre" ng-value="1" ng-model="bohrenValue">BR1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbre" ng-value="2" ng-model="bohrenValue">BR2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbre" ng-value="3" ng-model="bohrenValue">BR3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schleifen</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsl" ng-value="1" ng-model="schleifenValue">SL1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsl" ng-value="2" ng-model="schleifenValue">SL2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsl" ng-value="3" ng-model="schleifenValue">SL3</label>
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
										<input type="radio" name="oldtf" ng-value="1" ng-model="themerformValue">TF1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldtf" ng-value="2" ng-model="themerformValue">TF2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldtf" ng-value="3" ng-model="themerformValue">TF3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Bohren II</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbr2" ng-value="1" ng-model="bohreniiValue">BR1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbr2" ng-value="2" ng-model="bohreniiValue">BR2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldbr2" ng-value="3" ng-model="bohreniiValue">BR3</label>
									</div>
								</div>
								<div class="col-xs-1 col-sm-1 col-md-1" style="height:70%;font-size: 15px;text-align: center;background: transparent;">
								</div>
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;">
									<p>Schneiden I</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsd" ng-value="1" ng-model="schneidenValue">SD1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsd" ng-value="2" ng-model="schneidenValue">SD2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldsd" ng-value="3" ng-model="schneidenValue">SD3</label>
									</div>
								</div>
							</div>
							<div class="col-xs-12  col-sm-12 col-md-12" style="padding: 0px;background: green;height: 20%;">
							</div>
						</div>

						<div class="col-xs-12  col-sm-12 col-md-12" style="height: 60%;margin-top: 5px;">
							<div class="col-xs-12  col-sm-12 col-md-12 silicon pull-left" style="height: 80%;">
							
								<div class="col-xs-3 col-sm-3 col-md-3 silicon pull-left" style="height:70%;font-size: 15px;text-align: center;background: gray;border:2px solid gray;margin-left: 15px;">
									<p>Assembly GH</p>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldgh" ng-value="1" ng-model="assemblyGhValue">GH1</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldgh" ng-value="2" ng-model="assemblyGhValue">GH2</label>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="radio" name="oldgh" ng-value="3" ng-model="assemblyGhValue">GH3</label>
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
                        	描述：加工订单状态
                       -->
						<h3>目前已生产了200个LS1, 100个LS2</h3>
						<h3>还差300个LS1,400个LS2未生产</h3>
					</div>	
					<div style="display: none;" ng-class="{'showDetail':oldState==6}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：常规排产
                       -->
						<h3>订单中需要的产品：LS1 = ZS1 + GH2 + ZT</h3>
						<h3>订单中需要的产品：LS2 = ZS2 + GH1 + ZT + SH</h3>
						<h3>其中部件来自Product der Zahnstange产线：ZS1 = HT1 + BR2 + SL2</h3>
						<h3>其中部件来自Product der Zahnstange产线：ZS2 = HT2 + BR3 + SL1</h3>
						<h3>其中部件来自Product der Gehause产线：GH1 = TF1 + BR1 + SD2</h3>
						<h3>其中部件来自Product der Gehause产线：GH2 = TF2 + BR1 + SD1</h3>
						<h3>外购部件为： ZT和SH</h3>
					</div>				
					<div style="display: none;" ng-class="{'showDetail':oldState==7}">
						<p style="color: red;">设备BR2功能出现问题</p>
					</div>
				
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 80%;display: none;" ng-class="{'showDetail':pageNum==1}">
			<div class="col-xs-12  col-sm-12 col-md-12">
				<ul class="col-xs-12  col-sm-12 col-md-12 nav nav-pills factoryUl">
					<li ng-click="newState=1" ng-class="{'selectStyle':newState==1}">
						<a href="#" ng-click="getPriorityOrders()">新任务优先级列表</a>
					</li>
					<li ng-click="newState=2" ng-class="{'selectStyle':newState==2}">
						<a href="#">新的产线设备</a>
					</li>
					<li ng-click="newState=3" ng-class="{'selectStyle':newState==3}">
						<a href="#" ng-click="getSuggestion()">本地生产优化措施</a>
					</li>
					<li ng-click="newState=4" ng-class="{'selectStyle':newState==4}">
						<a href="#" ng-click="getCoorationCompanySuggestion()">合作企业排产措施</a>
					</li>
				</ul>
			</div>
			<div class="col-xs-12  col-sm-12 col-md-12" style="height: 100%;">
				<div class="well well-lg" style="height: 100%;overflow: auto;overflow-x: auto;">
					<div style="display: none;" ng-class="{'showDetail':newState==1}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：订单优先级详情
                        -->
						<table class="table">
							<thead>
								<tr>
									<th class="td">订单号</th>
									<th class="td">客户名</th>
									<th class="td">订购物料-数量</th>
									<th class="td">订单总额</th>
									<th class="td">订货日期</th>
									<th class="td">交货日期</th>
									<th class="td">模拟</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="item in priorityOrders">
									<td class="td">
										<a class="td">{{item.id}}</textarea>
									</td>
									<td class="td">
										<a class="td">{{item.customerName}}</a>
									</td>
									<!--
                                    	作者：offline
                                    	时间：2017-09-24
                                    	描述：item.orderProductInfoList, TBD
                                    -->
									<td class="td">
										<a class="td">{{item.materialNameAndNum}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.orderPrice}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.startDate}}</a>
									</td>
									<td class="td">
										<a class="td">{{item.endDate}}</a>
									</td>
									<td class="td">
										<!--
                                        	作者：offline
                                        	时间：2017-09-24
                                        	描述：
                                        -->
										<button ng-click="startAnalysisPrority(item.id)" type="button" style="height: auto;">点击模拟</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div style="display: none;height:70%;" ng-class="{'showDetail':newState==2}">
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
										<input type="radio" name="ht" ng-value="1" ng-model="hartingValue">HT1</label>
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
					<div style="display: none;" ng-click="getSuggestion()" ng-class="{'showDetail':newState==3}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：本地生产优化措施
                        -->

						<h3 style="font-weight: bolder;">生产:<p ng-model="localCompanySuggestion">当前生产订单变为：A3, A3不含有产品：LS1,ZS产线生产变成生产：ZS2,ZS3,不生产：ZS1,GH产线生产变成生产：GH1,GH3,不生产：GH2</p></h3>

						<h3 style="font-weight: bolder;">采购:<p ng-model="prioritySuggestion">对于装配产线的影响是：可以适当减少SH采购， 减少到2000根 ，可以适当减少ZT采购， 减少到0根</p></h3>

					</div>
					<div style="display: none;" ng-click="getCoorationCompanySuggestion()" ng-class="{'showDetail':newState==4}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：企业合作排产措施
                        -->
						<h3 style="font-weight: bolder;" ng-model="cooperationSuggestion">合作公司的方案优化：减少 SH的采购，通知Firma Y公司适当的减少SH的生产，减少 ZT的采购，通知Firma X公司适当的减少ZT的生产<a ng-model="cooperationSuggestion"></a></h3>
					</div>
				</div>
			</div>
		</div>
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="processModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" id="show-container">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
							<h4 class="modal-title" id="myModalLabel">
					分析
				</h4>
						</div>
						<div class="modal-body" style="max-height: 400px;overflow-y: auto;">
							<div style="width:100%;height: 100%; margin: 0 auto;overflow-y: auto;">
								<span ng-if="l_step!=3">考量因素：</span>
								<!-- L1 -->
								<!-- recursive template -->
								<script type="text/ng-template" id="factorsTree">
									{{ factor.text }}
									<span class="badge factor-badge" ng-show="factor.status==1">
										<i class="fa fa-spinner fa-spin" style="font-size:20px;color: cornflowerblue;"></i>
									</span>
									<span ng-if="factor.valueType==0 && factor.status!=1" style="position: absolute;right: 26px;">
								 		{{factor.value}}
									</span>
									<ul ng-if="factor.valueType==1" style="list-style: none;padding-left: 15px;">
										<li ng-repeat="val in factor.value track by $index">
											{{val.text}} <span ng-if="val.text1" style="margin-left: 26px;">{{val.text1}}</span>
											<span class="badge factor-badge" style="right: 16px;position: absolute;" ng-if="val.status ==1">
										<i class="icon-ok-sign" style="font-size:20px;color: darkcyan;"></i>
									</span>
											<span class="badge factor-badge" style="right: 16px;position: absolute;" ng-if="val.status ==0">
										<i class="icon-warning-sign" style="font-size:20px;color: red;"></i>
									</span>
										</li>
									</ul>
								</script>
								<ul ng-show="l_step == 0" class="list-group">
									<li class="list-group-item" ng-repeat="factor in l0list track by $index" ng-include="'factorsTree'"></li>
								</ul>
								<!-- L2 -->
								<ul ng-show="l_step == 1" class="list-group">
									<li class="list-group-item" ng-repeat="factor in l1list track by $index" ng-include="'factorsTree'"></li>
								</ul>
								<!-- L3 -->
								<ul ng-show="l_step == 2" class="list-group">
									<li class="list-group-item" ng-repeat="factor in l2list track by $index" ng-include="'factorsTree'"></li>
								</ul>
								<!--优先级分析-->
								<span ng-if="l_step==3">权重分配分析：</span>
								<div ng-show="l_step == 3">
									<div style="text-align: center;" ng-show="!isInitialized"> <span class="badge factor-badge">
										<i class="fa fa-spinner fa-spin" style="font-size:60px;color: cornflowerblue;"></i>
									</span>
										<p style="padding-top: 10px;">数据整理中......</p>
									</div>
									<div ng-show="isInitialized">
										<form class="form-horizontal" role="form">
											<table class="table table-bordered">
												<!--<caption>权重分配</caption>-->
												<thead>
													<tr>
														<th class="col-sm-2">订单号</th>
														<th class="col-sm-1">产品名称</th>
														<th class="col-sm-1">订单收益B</th>
														<th class="col-sm-2">订单收益(0-0.6)C1</th>
														<th class="col-sm-2">客户重要性(0-0.3)C2</th>
														<th class="col-sm-2">订单重要性(0-0.1)C3</th>
														<th class="col-sm-2">优先级PA=B*(C1+C2+C3)</th>
													</tr>
												</thead>
												<tbody>
													<tr ng-repeat="data in productData track by $index">
														<td class="col-sm-2">{{data.order_no}}</td>
														<td class="col-sm-1">{{data.product_name}}</td>
														<td class="col-sm-1">{{data.total_amount - data.total_cost}}</td>
														<td class="col-sm-2">{{data.factor1}}</td>
														<td class="col-sm-2">{{data.factor2}}</td>
														<td class="col-sm-2">{{data.customer_important}}</td>
														<td class="col-sm-2">{{(data.total_amount - data.total_cost)* (data.factor1+ data.customer_important +data.factor2)}}</td>
													</tr>
												</tbody>
											</table>
										</form>
									</div>
								</div>
								<div class="stepwizard" ng-show="l_step !=4">
									<div class="stepwizard-row">
										<div class="stepwizard-step">
											<button type="button" ng-class="{'btn-primary': l_step==0}" ng-disabled="!isInitialized" class="btn btn-default btn-circle" ng-click="switchTab($event,'0')">L0</button>
										</div>
										<div class="stepwizard-step">
											<button type="button" ng-class="{'btn-primary': l_step==1}" ng-disabled="!isInitialized" class="btn btn-default btn-circle" ng-click="switchTab($event,'1')">L1</button>
										</div>
										<div class="stepwizard-step">
											<button type="button" ng-class="{'btn-primary': l_step==2}" ng-disabled="!isInitialized" class="btn btn-default btn-circle" ng-click="switchTab($event,'2')">L2</button>
										</div>
										<div class="stepwizard-step">
											<button type="button" ng-class="{'btn-primary': l_step==3}" ng-disabled="!isInitialized" class="btn btn-default btn-circle" ng-click="switchTab($event,'3')">PA</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" ng-show="false" id="primaryButton" data-loading-text="分析中...">Run</button>
							<button type="button" class="btn btn-default" ng-disabled="!isInitialized" data-dismiss="modal">关闭</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		
		<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 10px;margin-bottom: 10px;height: 40px;">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<button type="button" ng-click="getPriorityOrders()" class="col-xs-12 col-sm-12 col-md-12 btn btn-lg" style="background-color: gray;">开始排产优化					
				</button>
			</div>
		</div>
	</body>
	<script>
		window.setTimeout(function(){ 
			alert("DFHRS BR2设备不能用,请开始进行排产优化");},60000); 
	</script>
</html>