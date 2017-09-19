package com.lwh.common.api;

import net.jctp.CThostFtdcDepthMarketDataField;
import net.jctp.CThostFtdcForQuoteRspField;
import net.jctp.CThostFtdcRspInfoField;
import net.jctp.CThostFtdcRspUserLoginField;
import net.jctp.CThostFtdcSpecificInstrumentField;
import net.jctp.CThostFtdcUserLogoutField;
import net.jctp.MdApi;
import net.jctp.MdApiListener;

public class MdApiTest {

	public static void main(String[] args) throws Throwable {
		final MdApi mdApi = new MdApi();
		mdApi.setListener(new MdApiListener() {

			@Override
			public void OnRspUserLogout(CThostFtdcUserLogoutField pUserLogout,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				// TODO Auto-generated method stub

			}

			@Override
			public void OnRspUserLogin(
					CThostFtdcRspUserLoginField pRspUserLogin,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
			}

			@Override
			public void OnRspError(CThostFtdcRspInfoField pRspInfo,
					int nRequestID, boolean bIsLast) {
			}

			@Override
			public void OnHeartBeatWarning(int nTimeLapse) {
			}

			@Override
			public void OnFrontDisconnected(int nReason) {
			}

			@Override
			public void OnFrontConnected() {
				System.out.println("Listener.OnFrontConnected");
			}

			@Override
			public void OnRspSubMarketData(
					CThostFtdcSpecificInstrumentField pSpecificInstrument,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				// TODO Auto-generated method stub

			}

			@Override
			public void OnRspUnSubMarketData(
					CThostFtdcSpecificInstrumentField pSpecificInstrument,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				// TODO Auto-generated method stub

			}

			@Override
			public void OnRtnDepthMarketData(
					CThostFtdcDepthMarketDataField pDepthMarketData) {
				System.out.println(pDepthMarketData.InstrumentID + "-" + pDepthMarketData.UpdateTime + ":"
						+ pDepthMarketData.LastPrice);
			}

			@Override
			public void OnRspSubForQuoteRsp(
					CThostFtdcSpecificInstrumentField pSpecificInstrument,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				// TODO Auto-generated method stub

			}

			@Override
			public void OnRspUnSubForQuoteRsp(
					CThostFtdcSpecificInstrumentField pSpecificInstrument,
					CThostFtdcRspInfoField pRspInfo, int nRequestID,
					boolean bIsLast) {
				// TODO Auto-generated method stub

			}

			@Override
			public void OnRtnForQuoteRsp(CThostFtdcForQuoteRspField pForQuoteRsp) {
				// TODO Auto-generated method stub

			}
		});
		
		
		try{
    		mdApi.SyncConnect("tcp://180.168.146.187:10010", "9999", "", "");
    		Thread.sleep(1000);
    		
    		 		
    		mdApi.SubscribeMarketData(new String[] { "rb1601" });
    		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		finally {
			mdApi.Close();
		}
	}

}
