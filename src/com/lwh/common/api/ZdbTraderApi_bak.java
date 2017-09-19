package com.lwh.common.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jctp.CThostFtdcInputOrderField;
import net.jctp.CThostFtdcReqUserLoginField;
import net.jctp.CThostFtdcRspInfoField;
import net.jctp.CThostFtdcRspUserLoginField;
import net.jctp.CThostFtdcTradeField;
import net.jctp.CThostFtdcUserLogoutField;
import net.jctp.TraderApiAdapter;
import net.jctp.TraderApiListener;
import net.jtrader.JTraderException;
import net.sf.json.JSONObject;

import org.springside.modules.mapper.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.zeromq.ZMQ;


public class ZdbTraderApi_bak {

	private ZMQ.Context context = ZMQ.context(1);
	private ZMQ.Socket socket = context.socket(ZMQ.REQ);
	private ZMQ.Socket subscriber = context.socket(ZMQ.SUB);  Thread subscbThread = new SubscbThread();
	private TraderApiListener listener = new TraderApiAdapter();

	public TraderApiListener getListener() {
		return listener;
	}

	public void setListener(TraderApiListener listener) {
		this.listener = listener;
	}

	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

	public ZdbTraderApi_bak() {
	}

	public void SyncConnect(String frontUrl, int port, int subPort)
			throws JTraderException {
		try {
			socket.connect(frontUrl + ":" + port);
			subscriber.connect(frontUrl + ":" + subPort);
			subscriber.subscribe("".getBytes());
		} catch (Exception e) {
			throw new JTraderException(-1);
		}
		subscbThread.start();
	}

	public String Close() {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10010");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(dataMap);
		reqMap.put("data", data);
		String requestString = mapper.toJson(reqMap) + " ";
		byte[] request = requestString.getBytes();
		request[request.length - 1] = 0;
		socket.send(request, 0);
		byte[] reply = socket.recv();
		String replyString = new String(reply);
		return replyString;
	}

	protected volatile boolean connected;
	protected volatile boolean login;

	private class SubscbThread extends Thread {
		@Override
		public void run() {
			while (true) {
				byte[] reply = subscriber.recv();
				String replyString = new String(reply);
				Map<String, Object> replyMap = mapper.fromJson(replyString,
						Map.class);
				String funcId = (String) replyMap.get("funcId");
				if (listener == null)
					return;
				switch (funcId) {

				case "10001": {
					CThostFtdcRspUserLoginField f = new CThostFtdcRspUserLoginField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.UserID = (String) data.get("userId");
					f2.ErrorID = Integer.parseInt((String) data.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspUserLogin(f, f2, -1, true);
					break;
				}

				case "10002": {
					CThostFtdcUserLogoutField f = new CThostFtdcUserLogoutField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.UserID = (String) data.get("userId");
					f2.ErrorID = Integer.parseInt((String) data.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspUserLogout(f, f2, -1, true);
					break;
				}

				case "10003": {
					CThostFtdcTradeField f = new CThostFtdcTradeField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.UserID = (String) data.get("userId");
					f.InstrumentID = (String) data.get("instrumentId");
					String direction = (String) data.get("direction");
					f.Direction = direction.toCharArray()[0];
					String vol = (String) data.get("vol");
					f.Volume = Integer.parseInt(vol);
					f.Price = Double.parseDouble((String) data.get("price"));
					f.TradeID = (String) data.get("tradeId");
					f.OrderRef = (String) data.get("orderRef");
					listener.OnRtnTrade(f);
					break;
				}

				case "10010": {
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					String errorId = (String) data.get("errorId");
					if (errorId.equals("0")) {
						socket.close();
						subscriber.close();
						return;
					}
				}

				}
			}
		}
	}

	public String ReqUserLogout(CThostFtdcUserLogoutField f)
			throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10002");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.UserID))
			userId = f.UserID;
		dataMap.put("userId", userId);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(dataMap);
		reqMap.put("data", data);
		String requestString = mapper.toJson(reqMap) + " ";
		byte[] request = requestString.getBytes();
		request[request.length - 1] = 0;
		socket.send(request, 0);
		byte[] reply = socket.recv();
		String replyString = new String(reply);
		return replyString;
	}

	public String ReqUserLogin(CThostFtdcReqUserLoginField f)
			throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10001");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.UserID))
			userId = f.UserID;
		dataMap.put("userId", userId);
		String passwd = "";
		if (StringUtils.isNotBlank(f.Password))
			passwd = f.Password;
		dataMap.put("passwd", passwd);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(dataMap);
		reqMap.put("data", data);
		String requestString = mapper.toJson(reqMap) + " ";
		byte[] request = requestString.getBytes();
		request[request.length - 1] = 0;
		socket.send(request, 0);
		byte[] reply = socket.recv();
		String replyString = new String(reply);
		return replyString;
	}

	public String ReqOrderInsert(CThostFtdcInputOrderField f) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10003");
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String orderRef = "";
		if (StringUtils.isNotBlank(f.OrderRef))
			orderRef = f.OrderRef;
		dataMap.put("orderRef", orderRef);

		String userId = "";
		if (StringUtils.isNotBlank(f.UserID))
			userId = f.UserID;
		dataMap.put("userId", userId);

		String instrumentId = "";
		if (StringUtils.isNotBlank(f.InstrumentID))
			instrumentId = f.InstrumentID;
		dataMap.put("instrumentId", instrumentId);

		dataMap.put("direction", String.valueOf(f.Direction));

		dataMap.put("price", String.valueOf(f.LimitPrice));

		dataMap.put("stopProfitPoint", String.valueOf(f.StopProfitPoint));

		dataMap.put("stopLossPoint", String.valueOf(f.StopLossPoint));

		dataMap.put("volumn", String.valueOf(f.VolumeTotalOriginal));

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		data.add(dataMap);
		reqMap.put("data", data);
		String requestString = mapper.toJson(reqMap) + " ";
		byte[] request = requestString.getBytes();
		request[request.length - 1] = 0;
		socket.send(request, 0);
		byte[] reply = socket.recv();
		String replyString = new String(reply);
		return replyString;
	}

	public static void main(String[] args) throws Exception {
		test1();
	}

	public static void test2() throws Exception {
		ZdbTraderApi_bak zdbTraderApi = new ZdbTraderApi_bak();
		zdbTraderApi.setListener(new TraderApiAdapter() {
			@Override
			public void OnRspUserLogin(
					CThostFtdcRspUserLoginField pRspUserLogin,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pRspUserLogin.UserID="
						+ pRspUserLogin.UserID + ",pRspInfo.ErrorID="
						+ pRspInfo.ErrorID + ",pRspInfo.ErrorMsg="
						+ pRspInfo.ErrorMsg);
			}

			@Override
			public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pUserLogout.UserID=" + pUserLogout.UserID
						+ ",pRspInfo.ErrorID=" + pRspInfo.ErrorID
						+ ",pRspInfo.ErrorMsg=" + pRspInfo.ErrorMsg);
			}

			@Override
			public void OnRtnTrade(CThostFtdcTradeField pTrade) {
				System.out.println(pTrade);
			}
		});

		zdbTraderApi.SyncConnect("tcp://120.24.49.63", 9090, 9091);

		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
		f.UserID = "008869";
		f.Password = "89902415";
		String reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);

		f = new CThostFtdcReqUserLoginField();
		f.UserID = "038530";
		f.Password = "123456";
		reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);

		f = new CThostFtdcReqUserLoginField();
		f.UserID = "038531";
		f.Password = "123456";
		reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);

		f = new CThostFtdcReqUserLoginField();
		f.UserID = "038515";
		f.Password = "123456";
		reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);
	}

	public static void test1() throws Exception {
		ZdbTraderApi_bak zdbTraderApi = new ZdbTraderApi_bak();
		zdbTraderApi.setListener(new TraderApiAdapter() {
			@Override
			public void OnRspUserLogin(
					CThostFtdcRspUserLoginField pRspUserLogin,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pRspUserLogin.UserID="
						+ pRspUserLogin.UserID + ",pRspInfo.ErrorID="
						+ pRspInfo.ErrorID + ",pRspInfo.ErrorMsg="
						+ pRspInfo.ErrorMsg);
			}

			@Override
			public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pUserLogout.UserID=" + pUserLogout.UserID
						+ ",pRspInfo.ErrorID=" + pRspInfo.ErrorID
						+ ",pRspInfo.ErrorMsg=" + pRspInfo.ErrorMsg);
			}

			@Override
			public void OnRtnTrade(CThostFtdcTradeField pTrade) {
				System.out.println(pTrade);
			}
		});

		zdbTraderApi.SyncConnect("tcp://120.24.49.63", 9090, 9091);

		String userID = "038515";

		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
		f.UserID = userID;
		f.Password = "123456";
		String reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);

		try{
			JSONObject replyJsonStr= JSONObject.fromObject(reply);
			String result = replyJsonStr.getString("result");
			
			JSONObject resultJsonStr= JSONObject.fromObject(result);
			String errorId = resultJsonStr.getString("errorId");
			
			System.out.println("errorId: " + errorId);
		}
		catch(Exception e)
		{
			System.out.println("error: " + e);
		}
		
		
		Thread.sleep(1000);

		CThostFtdcInputOrderField inputOrder = new CThostFtdcInputOrderField();
		// 濮旀墭鍙凤紝鍞竴
		inputOrder.OrderRef = "2015102715200001";
		inputOrder.UserID = userID;
		inputOrder.InstrumentID = "rb1601";
		// 1涔帮紝鐪嬪 2鍗栵紝鐪嬬┖
		inputOrder.Direction = '1';
		// 0甯備环濮旀墭
		inputOrder.LimitPrice = 0;
		// 姝㈢泩鐐�
		inputOrder.StopProfitPoint = 10;
		// 姝㈡崯鐐�
		inputOrder.StopLossPoint = 10;
		inputOrder.VolumeTotalOriginal = 1;
		reply = zdbTraderApi.ReqOrderInsert(inputOrder);
		System.out.println(reply);
		
		Thread.sleep(2000);
		reply = zdbTraderApi.Close();
		System.out.println("close replay: "+reply);
	}
	
	
	public static void test3() throws Exception {
		ZdbTraderApi_bak zdbTraderApi = new ZdbTraderApi_bak();
		zdbTraderApi.setListener(new TraderApiAdapter() {
			@Override
			public void OnRspUserLogin(
					CThostFtdcRspUserLoginField pRspUserLogin,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pRspUserLogin.UserID="
						+ pRspUserLogin.UserID + ",pRspInfo.ErrorID="
						+ pRspInfo.ErrorID + ",pRspInfo.ErrorMsg="
						+ pRspInfo.ErrorMsg);
			}

			
			@Override
			public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout, CThostFtdcRspInfoField pRspInfo, int nRequestID, boolean bIsLast) {
				
			};
			
			@Override
			public void OnRtnTrade(CThostFtdcTradeField pTrade) {
				System.out.println(pTrade);
			}
		});

		zdbTraderApi.SyncConnect("tcp://120.24.49.63", 9090, 9091);

		String userID = "008869";

//		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
//		f.UserID = userID;
//		f.Password = "89902415";
//		String reply = zdbTraderApi.ReqUserLogin(f);
//		System.out.println(reply);

		
		
		
		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
		f.UserID = "038515";
		f.Password = "123456";
		String reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println("login replay: "+reply);

		CThostFtdcReqUserLoginField f1 = new CThostFtdcReqUserLoginField();
		f1.UserID = "038530";
		f1.Password = "123456";
		String reply1 = zdbTraderApi.ReqUserLogin(f1);
		System.out.println("login replay: "+reply1);
		
		CThostFtdcReqUserLoginField f2 = new CThostFtdcReqUserLoginField();
		f2.UserID = "038531";
		f2.Password = "123456";
		String reply2 = zdbTraderApi.ReqUserLogin(f2);
		System.out.println("login replay: "+reply2);
		
		Thread.sleep(1000);

		Thread.sleep(1000);
		
		CThostFtdcInputOrderField inputOrder = new CThostFtdcInputOrderField();
		inputOrder.OrderRef = "2015102715200001";
		inputOrder.UserID = "038515";
		inputOrder.InstrumentID = "rb1601";
		inputOrder.Direction = '1';
		inputOrder.LimitPrice = 0;
		inputOrder.VolumeTotalOriginal = 1;
		reply = zdbTraderApi.ReqOrderInsert(inputOrder);
		System.out.println("order replay: "+reply);
		
		CThostFtdcInputOrderField inputOrder1 = new CThostFtdcInputOrderField();
		inputOrder1.OrderRef = "2015102715200002";
		inputOrder1.UserID = "038530";
		inputOrder1.InstrumentID = "rb1601";
		inputOrder1.Direction = '1';
		inputOrder1.LimitPrice = 0;
		inputOrder1.VolumeTotalOriginal = 1;
		reply = zdbTraderApi.ReqOrderInsert(inputOrder1);
		System.out.println("order replay: "+reply);
		
		CThostFtdcInputOrderField inputOrder2 = new CThostFtdcInputOrderField();
		inputOrder2.OrderRef = "2015102715200003";
		inputOrder2.UserID = "038531";
		inputOrder2.InstrumentID = "rb1601";
		inputOrder2.Direction = '1';
		inputOrder2.LimitPrice = 0;
		inputOrder2.VolumeTotalOriginal = 1;
		reply = zdbTraderApi.ReqOrderInsert(inputOrder2);
		System.out.println("order replay: "+reply);
		
		
		Thread.sleep(2000);
		reply = zdbTraderApi.Close();
		System.out.println("close replay: "+reply);
	}

}
