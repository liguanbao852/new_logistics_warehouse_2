package com.lwh.common.api;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.jctp.CThostFtdcInputOrderField;
import net.jctp.CThostFtdcInstrumentMarginRateField;
import net.jctp.CThostFtdcInvestorField;
import net.jctp.CThostFtdcQryInstrumentMarginRateField;
import net.jctp.CThostFtdcQryInvestorField;
import net.jctp.CThostFtdcQryTradeField;
import net.jctp.CThostFtdcQryTradingAccountField;
import net.jctp.CThostFtdcReqUserLoginField;
import net.jctp.CThostFtdcRspInfoField;
import net.jctp.CThostFtdcRspUserLoginField;
import net.jctp.CThostFtdcTradeField;
import net.jctp.CThostFtdcTradingAccountField;
import net.jctp.CThostFtdcUserLogoutField;
import net.jctp.TraderApiAdapter;
import net.jctp.TraderApiListener;
import net.jtrader.JTraderException;

import org.apache.commons.lang3.StringUtils;
import org.springside.modules.mapper.JsonMapper;
import org.zeromq.ZMQ;

public class ZdbTraderApi {

	private ZMQ.Context context = ZMQ.context(1);
	private ZMQ.Socket socket = context.socket(ZMQ.REQ);
	private ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
	private Thread subscbThread = new SubscbThread();
	private TraderApiListener listener = new TraderApiAdapter();

	public TraderApiListener getListener() {
		return listener;
	}

	public void setListener(TraderApiListener listener) {
		this.listener = listener;
	}

	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

	public ZdbTraderApi() {
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
				String replyString = "{}";
				try {
					replyString = new String(reply, "gbk");
				} catch (UnsupportedEncodingException e) {

				}
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
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt((String) data
								.get("errorId"));
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
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt((String) data
								.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspUserLogout(f, f2, -1, true);
					break;
				}

				case "10003": {
					CThostFtdcTradeField f = new CThostFtdcTradeField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.UserID = (String) data.get("userId");
					f.InvestorID = (String) data.get("userId");
					f.InstrumentID = (String) data.get("instrumentId");
					String direction = (String) data.get("direction");
					if (direction != null)
						f.Direction = direction.toCharArray()[0];
					String vol = (String) data.get("vol");
					if (vol != null)
						f.Volume = Integer.parseInt(vol);
					String price = (String) data.get("price");
					if (price != null)
						f.Price = Double.parseDouble(price);
					f.TradeID = (String) data.get("tradeId");
					f.OrderRef = (String) data.get("orderRef");
					listener.OnRtnTrade(f);
					break;
				}

				case "10004": {
					CThostFtdcTradingAccountField f = new CThostFtdcTradingAccountField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.AccountID = (String) data.get("userId");
					String balance = (String) data.get("balance");
					if (balance != null)
						f.Balance = Double.parseDouble(balance);
					String withdrawQuota = (String) data.get("withdrawQuota");
					if (withdrawQuota != null)
						f.WithdrawQuota = Double.parseDouble(withdrawQuota);
					String available = (String) data.get("available");
					if (available != null)
						f.Available = Double.parseDouble(available);
					String deposit = (String) data.get("deposit");
					if (deposit != null)
						f.Deposit = Double.parseDouble(deposit);
					String withdraw = (String) data.get("withdraw");
					if (withdraw != null)
						f.Withdraw = Double.parseDouble(withdraw);
					String currMargin = (String) data.get("currMargin");
					if (currMargin != null)
						f.CurrMargin = Double.parseDouble(currMargin);
					String commission = (String) data.get("commission");
					if (commission != null)
						f.Commission = Double.parseDouble(commission);
					String closeProfit = (String) data.get("closeProfit");
					if (closeProfit != null)
						f.CloseProfit = Double.parseDouble(closeProfit);
					f.CurrencyID = (String) data.get("currencyID");
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt(errorId);
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspQryTradingAccount(f, f2, -1, true);
					break;
				}

				case "10005": {
					CThostFtdcInstrumentMarginRateField f = new CThostFtdcInstrumentMarginRateField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.InvestorID = (String) data.get("userId");
					f.InstrumentID = (String) data.get("instrumentId");
					String hedgeFlag = (String) data.get("hedgeFlag");
					if (hedgeFlag != null)
						f.HedgeFlag = hedgeFlag.toCharArray()[0];
					String longMarginRatioByMoney = (String) data
							.get("longMarginRatioByMoney");
					if (longMarginRatioByMoney != null)
						f.LongMarginRatioByMoney = Double
								.parseDouble(longMarginRatioByMoney);
					String longMarginRatioByVolume = (String) data
							.get("longMarginRatioByVolume");
					if (longMarginRatioByVolume != null)
						f.LongMarginRatioByVolume = Double
								.parseDouble(longMarginRatioByVolume);
					String shortMarginRatioByMoney = (String) data
							.get("shortMarginRatioByMoney");
					if (shortMarginRatioByMoney != null)
						f.ShortMarginRatioByMoney = Double
								.parseDouble(shortMarginRatioByMoney);
					String shortMarginRatioByVolume = (String) data
							.get("shortMarginRatioByVolume");
					if (shortMarginRatioByVolume != null)
						f.ShortMarginRatioByVolume = Double
								.parseDouble(shortMarginRatioByVolume);
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt((String) data
								.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspQryInstrumentMarginRate(f, f2, -1, true);
					break;
				}

				case "10006": {
					CThostFtdcTradeField f = new CThostFtdcTradeField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.UserID = (String) data.get("userId");
					f.InvestorID = (String) data.get("userId");
					f.InstrumentID = (String) data.get("instrumentId");
					String direction = (String) data.get("direction");
					if (direction != null)
						f.Direction = direction.toCharArray()[0];
					String offsetFlag = (String) data.get("offsetFlag");
					if (offsetFlag != null)
						f.OffsetFlag = offsetFlag.toCharArray()[0];
					String vol = (String) data.get("vol");
					if (vol != null)
						f.Volume = Integer.parseInt(vol);
					String price = (String) data.get("price");
					if (price != null)
						f.Price = Double.parseDouble(price);
					f.TradeID = (String) data.get("tradeId");
					f.OrderRef = (String) data.get("orderRef");
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt((String) data
								.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspQryTrade(f, f2, -1, true);
					break;
				}

				case "10007": {
					CThostFtdcInvestorField f = new CThostFtdcInvestorField();
					CThostFtdcRspInfoField f2 = new CThostFtdcRspInfoField();
					Map<String, Object> data = (Map<String, Object>) replyMap
							.get("data");
					f.InvestorID = (String) data.get("investorID");
					f.BrokerID = (String) data.get("brokerID");
					f.InvestorGroupID = (String) data.get("investorGroupID");
					f.InvestorName = (String) data.get("investorName");
					String identifiedCardType = (String) data
							.get("identifiedCardType");
					if (identifiedCardType != null)
						f.IdentifiedCardType = identifiedCardType.toCharArray()[0];
					f.IdentifiedCardNo = (String) data.get("identifiedCardNo");
					String isActive = (String) data.get("isActive");
					if (isActive != null)
						f.IsActive = Boolean.parseBoolean(isActive);
					f.Telephone = (String) data.get("telephone");
					f.Address = (String) data.get("address");
					f.OpenDate = (String) data.get("openDate");
					f.Mobile = (String) data.get("mobile");
					f.CommModelID = (String) data.get("commModelID");
					f.MarginModelID = (String) data.get("marginModelID");
					String errorId = (String) data.get("errorId");
					if (errorId != null)
						f2.ErrorID = Integer.parseInt((String) data
								.get("errorId"));
					f2.ErrorMsg = (String) data.get("errorMsg");
					listener.OnRspQryInvestor(f, f2, -1, true);
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

	public String ReqQryTradingAccount(CThostFtdcQryTradingAccountField f)
			throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10004");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.InvestorID))
			userId = f.InvestorID;
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

	public String ReqQryInstrumentMarginRate(
			CThostFtdcQryInstrumentMarginRateField f) throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10005");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.InvestorID))
			userId = f.InvestorID;
		dataMap.put("userId", userId);
		String instrumentId = "";
		if (StringUtils.isNotBlank(f.InstrumentID))
			instrumentId = f.InstrumentID;
		dataMap.put("instrumentId", instrumentId);
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

	public String ReqQryTrade(CThostFtdcQryTradeField f)
			throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10006");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.InvestorID))
			userId = f.InvestorID;
		dataMap.put("userId", userId);
		String instrumentId = "";
		if (StringUtils.isNotBlank(f.InstrumentID))
			instrumentId = f.InstrumentID;
		dataMap.put("instrumentId", instrumentId);
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

	public String ReqQryInvestor(CThostFtdcQryInvestorField f)
			throws JTraderException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10007");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String userId = "";
		if (StringUtils.isNotBlank(f.InvestorID))
			userId = f.InvestorID;
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

	public String ReqOrderInsert(CThostFtdcInputOrderField f) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("funcId", "10003");
		Map<String, Object> dataMap = new HashMap<String, Object>();

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
		test4();
	}

	public static void test4() throws Exception {
		ZdbTraderApi zdbTraderApi = new ZdbTraderApi();
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

			@Override
			public void OnRspQryTrade(CThostFtdcTradeField pTrade,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pTrade: " + pTrade);
			}

			@Override
			public void OnRspQryTradingAccount(
					CThostFtdcTradingAccountField pTradingAccount,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pTradingAccount: " + pTradingAccount);
			}

			@Override
			public void OnRspQryInstrumentMarginRate(
					CThostFtdcInstrumentMarginRateField pInstrumentMarginRate,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pInstrumentMarginRate: "
						+ pInstrumentMarginRate);
			}

			@Override
			public void OnRspQryInvestor(CThostFtdcInvestorField pInvestor,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println("pInvestor: " + pInvestor);
			}

			
			
		});

		zdbTraderApi.SyncConnect("tcp://127.0.0.1", 9090, 9091);

		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
		// f.UserID = "038515";
		// f.Password = "123456";
		f.UserID = "038515";
		f.Password = "123456";
		String reply1 = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply1);
		Thread.sleep(2000);

		
		CThostFtdcInputOrderField inputOrder = new CThostFtdcInputOrderField();
		inputOrder.UserID = "038515";
		inputOrder.InstrumentID = "IF1512";
		// 1涔帮紝鐪嬪 2鍗栵紝鐪嬬┖
		inputOrder.Direction = '1';
		// 0甯備环濮旀墭
		inputOrder.LimitPrice = 0;
		// 姝㈢泩鐐�
		inputOrder.StopProfitPoint = 50;
		// 姝㈡崯鐐�
		inputOrder.StopLossPoint = 50;
		inputOrder.VolumeTotalOriginal = 1;
		String reply = zdbTraderApi.ReqOrderInsert(inputOrder);
		System.out.println(reply);
		
	}

	public static void test3() throws Exception {
		ZdbTraderApi zdbTraderApi = new ZdbTraderApi();
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

			@Override
			public void OnRspQryTrade(CThostFtdcTradeField pTrade,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println(pTrade);
			}

			@Override
			public void OnRspQryTradingAccount(
					CThostFtdcTradingAccountField pTradingAccount,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println(pTradingAccount);
			}

			@Override
			public void OnRspQryInstrumentMarginRate(
					CThostFtdcInstrumentMarginRateField pInstrumentMarginRate,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				System.out.println(pInstrumentMarginRate);
			}

		});

		zdbTraderApi.SyncConnect("tcp://120.24.49.63", 9090, 9091);

		CThostFtdcQryTradingAccountField tdAcctFld = new CThostFtdcQryTradingAccountField();
		tdAcctFld.InvestorID = "008869";
		String reply = zdbTraderApi.ReqQryTradingAccount(tdAcctFld);
		System.out.println(reply);

		CThostFtdcQryInstrumentMarginRateField marginRateFld = new CThostFtdcQryInstrumentMarginRateField();
		marginRateFld.InvestorID = "008869";
		reply = zdbTraderApi.ReqQryInstrumentMarginRate(marginRateFld);
		System.out.println(reply);

		CThostFtdcQryTradeField tdFld = new CThostFtdcQryTradeField();
		tdFld.InvestorID = "008869";
		reply = zdbTraderApi.ReqQryTrade(tdFld);
		System.out.println(reply);
	}

	public static void test2() throws Exception {
		ZdbTraderApi zdbTraderApi = new ZdbTraderApi();
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
		ZdbTraderApi zdbTraderApi = new ZdbTraderApi();
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

		String userID = "008869";

		CThostFtdcReqUserLoginField f = new CThostFtdcReqUserLoginField();
		f.UserID = userID;
		f.Password = "89902415";
		String reply = zdbTraderApi.ReqUserLogin(f);
		System.out.println(reply);

		Thread.sleep(1000);

		CThostFtdcInputOrderField inputOrder = new CThostFtdcInputOrderField();
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
	}
}
