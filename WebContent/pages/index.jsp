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
		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 10px;">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="col-xs-12 col-sm-12 col-md-12 navbar-header">
						<!--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            				<span class="sr-only">Toggle navigation</span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
            				<span class="icon-bar"></span>
          				</button>-->
						<div class="col-xs-6 col-sm-6 col-md-6" style="text-align: left;margin: 0 0;">
							<a class="navbar-brand aHeader" href="#" ng-click="pageNum=0">Optimierung der Ablaufplanungssystem</a>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6" style="text-align: right;margin: 0 0;">
							<a class="navbar-brand aHeader" href="#" style="display: none;" ng-click="pageNum=1" ng-class="{'showDetail':pageNum==1}">Ergebnisse der Optimierung</a>
						</div>

					</div>
					<!--<div id="navbar" class="navbar-collapse collapse">
						<form class="navbar-form navbar-right" role="form">
							<div class="form-group">
								<input type="text" placeholder="Email" class="form-control">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Password" class="form-control">
							</div>
							<button type="submit" class="btn btn-success">Sign in</button>
						</form>
					</div>-->
					<!--/.navbar-collapse -->
				</div>
			</nav>
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 75%;display: none;" ng-class="{'showDetail':pageNum==0}">
			<div class="col-xs-12  col-sm-12 col-md-12">
				<ul class="col-xs-12  col-sm-12 col-md-12 nav nav-pills factoryUl" style="display: inline;">
					<li ng-click="oldState=1" ng-class="{'selectStyle':oldState==1}">
						<a ng-click="getCustomerOrderList()" style="font-size: 8px;" href="#">Kundenauftragszentrum</a>
					</li>
					<li ng-click="oldState=2" ng-class="{'selectStyle':oldState==2}">
						<a ng-click="getPurchaseMaterialList()" style="font-size: 8px;" href="#">Zentrale Beschaffung</a>
					</li>
					<li ng-click="oldState=3" ng-class="{'selectStyle':oldState==3}">
						<a ng-click="getCurrentOrder()" href="#" style="font-size: 8px;">Produktionsliste</a>
					</li>
					<li ng-click="oldState=4" ng-class="{'selectStyle':oldState==4}">
						<a ng-click="getConfig()" href="#" style="font-size: 8px;">Betriebszustand der Produktionslinienund Anlagen</a>
					</li>
					<li ng-click="oldState=5" ng-class="{'selectStyle':oldState==5}">
						<a ng-click="getOrderStatus()" href="#" style="font-size: 8px;">Zustand der bearbeiteten Auftäge</a>
					</li>
					<li ng-click="oldState=6" ng-class="{'selectStyle':oldState==6}">
						<a ng-click="getProductLine()" href="#" style="font-size: 8px;">Auftragszerlegung</a>
					</li>
					<li ng-click="oldState=7" ng-class="{'selectStyle':oldState==7}">
						<a ng-click="getDFHRSStatus()" href="#" style="font-size: 8px;">DFHRS</a>
					</li>
				</ul>
			</div>
			<div class="col-xs-12  col-sm-12 col-md-12" style="height: 100%;">
				<div class="well well-lg" style="height: 100%;overflow-x: auto;overflow-y: auto;">
					<div style="display: none;" ng-class="{'showDetail':oldState==1}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：订单列表
                       -->
						<table class="table">
							<thead>
								<tr>
									<th class="td">Auftragsnummer</th>
									<th class="td">Kundensname</th>
									<th class="td">Bestellte Waren und Volumen</th>
									<th class="td">Auftragsumsatz</th>
									<th class="td">Bestellungsdatum</th>
									<th class="td">Lieferungsdatum</th>
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
									<th class="td">Beschaffungsfirma</th>
									<th class="td">Beschaffungswaren</th>
									<th class="td">Beschaffungsvolumen</th>
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
									<th class="td">Auftragsnummer</th>
									<th class="td">Kundensname</th>
									<th class="td">Bestellte Waren und Volumen</th>
									<th class="td">Auftragsumsatz</th>
									<th class="td">Bestellungsdatum</th>
									<th class="td">Lieferungsdatum</th>
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
						<p>Bis jetzt haben schon 200 LS1 und 100 LS2 schon produziert.</p>
						<p>Noch 300 LS1 und 400 LS2 noch nicht produziert werden</p>
					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==6}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：常规排产
                       -->
						<p>LS1=ZS1+GH2+ZT+SH</p>
						<p>LS2 = ZS2 + GH1 + ZT + SH</p>
						<p>A1=500*LS1+500*LS2</p>
						<p>A2=200*LS1+800*LS2+800LS3</p>
						<p>A3=700*LS2+2000LS3</p>
						<p>A3=1000*LS2+1500LS3</p>
						<p>Produktionslinie I(Zahnstange):</p>
						<p>ZS1 = HT1 + BR2 + SL2</p>
						<p>ZS2 = HT2 + BR3 + SL1</p>
						<p>ZS3 = HT3 + BR1 + SL3</p>
						<p>Produktionslinie II(Gehäuse):</p>
						<p>GH1 = TF1 + BR1 + SD2</p>
						<p>GH2 = TF2 + BR1 + SD1</p>
						<p>GH3 = TF3 + BR3 + SD3</p>
						<p>Beschaffungswaren:</p>
						<p>ZT und SH</p>
					</div>
					<div style="display: none;" ng-class="{'showDetail':oldState==7}">
						<p style="color: red;">Funktionen BR2 ist defekt und gesperrt werden.</p>
						<p>Funktionen von HT1,HT2, HT3, BR1，BR3, SL1, SL2, SL3,TF1,TF2,TF3,SD1,SD2,SD3,GH1,GH2,GH3,ZT,SH sind verfügbar</p>
					</div>

				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12" style="height: 75%;display: none;" ng-class="{'showDetail':pageNum==1}">
			<div class="col-xs-12  col-sm-12 col-md-12">
				<ul class="col-xs-12  col-sm-12 col-md-12 nav nav-pills factoryUl">
					<li class="col-xs-3  col-sm-3 col-md-3" ng-click="newState=1" ng-class="{'selectStyle':newState==1}" style="margin: 0 0;background: transparent;">
						<a href="#" ng-click="getPriorityOrders()">Verfügbarkeit und Prioritöt der Aufträge</a>
					</li>
					<li class="col-xs-3  col-sm-3 col-md-3" ng-click="newState=2" ng-class="{'selectStyle':newState==2}" style="margin: 0 0;background: transparent;">
						<a href="#">Betriebszustand der Produktionslinien und Anlagen</a>
					</li>
					<li class="col-xs-3  col-sm-3 col-md-3" ng-click="newState=3" ng-class="{'selectStyle':newState==3}" style="margin: 0 0;background: transparent;">
						<a href="#" ng-click="getSuggestion()" >Optimierte Ablaufplanung für die lokale Unternehme</a>
					</li>
					<li class="col-xs-3  col-sm-3 col-md-3" ng-click="newState=4" ng-class="{'selectStyle':newState==4}" style="margin: 0 0;background: transparent;">
						<a href="#" ng-click="getCoorationCompanySuggestion()" >Optimierte Ablaufplanung für die remote Partnerunternehme</a>
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
									<th class="td">Auftragsnummer</th>
									<th class="td">Kundensname</th>
									<th class="td">Bestellte Waren und Volumen</th>
									<th class="td">Auftragsumsatz</th>
									<th class="td">Bestellungsdatum</th>
									<th class="td">Lieferungsdatum</th>
									<th class="td">Simulation</th>
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
										<button ng-click="startAnalysisPrority(item.id)" type="button" style="height: auto;">Klicken</button>
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
						<p>Produktion:</p>
						<p>Die neue Ablaufplanung: A3, A4 Reduzierte der Zwischenproduktion von ZS1,GH2.Erhörte der Zwischenproduktion von ZS2,ZS3,GH1und GH3.</p>
						<p>Beschaffung:</p>
						<p>Reduzierung des Einkaufen von SH auf 2000. Reduzierung des Einkaufen von ZT auf 0.</p>
					</div>
					<div style="display: none;" ng-click="getCoorationCompanySuggestion()" ng-class="{'showDetail':newState==4}">
						<!--
                        	作者：offline
                        	时间：2017-09-23
                        	描述：企业合作排产措施
                        -->
						<p>Firma X: Reduzierung der Produktion von ZT</p>
						<p>Firma Y: Reduzierung der Produktion von SH</p>
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
					Analysieren
				</h4>
					</div>
					<div class="modal-body" style="max-height: 400px;overflow-y: auto;">
						<div style="width:100%;height: 100%; margin: 0 auto;overflow-y: auto;">
							<span ng-if="l_step!=3">Betrachten Sie die Faktoren：</span>
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
							<span ng-if="l_step==3">Gewichtsverteilungsanalyse：</span>
							<div ng-show="l_step == 3">
								<div style="text-align: center;" ng-show="!isInitialized"> <span class="badge factor-badge">
										<i class="fa fa-spinner fa-spin" style="font-size:60px;color: cornflowerblue;"></i>
									</span>
									<p style="padding-top: 10px;">Datenkollation......</p>
								</div>
								<div ng-show="isInitialized">
									<form class="form-horizontal" role="form">
										<table class="table table-bordered">
											<!--<caption>权重分配</caption>-->
											<thead>
												<tr>
													<th class="col-sm-2">Bestellnummer</th>
													<th class="col-sm-1">Produktname</th>
													<th class="col-sm-1">Auftragsumsatz B</th>
													<th class="col-sm-2">Auftragsgewinn(0-0.6)C1</th>
													<th class="col-sm-2">Bedeutung des Kunden(0-0.3)C2</th>
													<th class="col-sm-2">Bestellen Sie Wichtigkeit(0-0.1)C3</th>
													<th class="col-sm-2">Priorität PA=B*(C1+C2+C3)</th>
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
						<button type="button" class="btn btn-primary" ng-show="false" id="primaryButton" data-loading-text="Analyse...">Run</button>
						<button type="button" class="btn btn-default" ng-disabled="!isInitialized" data-dismiss="modal">Geschlossen</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

		<div class="col-xs-12 col-sm-12 col-md-12" style="margin-top: 80px;margin-bottom: 10px;height: 40px;">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<button type="button" ng-click="getPriorityOrders()" class="col-xs-12 col-sm-12 col-md-12 btn btn-lg" style="background-color: gray;">Optimierung der Ablaufplanung					
				</button>
			</div>
		</div>
	</body>
	<script>
		window.setTimeout(function() {
			alert("Funktionen BR2 ist defekt und gesperrt werden.");
		}, 60000);
	</script>

</html>