package com.wxhx.gate.plat.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.bean.cxf.HXJaxWsDynamicClientFactory;

/**
 * webservice client factory
 * @author geliang
 *
 */
public class HXWebServiceClientFactory {
	
	private static Logger logger = LoggerFactory.getLogger(HXWebServiceClientFactory.class);
	
	private static HXJaxWsDynamicClientFactory dcf = HXJaxWsDynamicClientFactory.newInstance();

	
	//存放client客户端
	private static Map<String, Object> webServiceClientCache = new HashMap<String, Object>();
	
	//查询条件缓存
	private static Map<String, Object> webServiceOprationCache = new HashMap<String, Object>();

	
	/**
	 * 创建缓存
	 * @param type
	 */
	private static void createCallInfo(int type,String url) {
		//查询
		if(type==1) {
			//创建客户端
	        QName service = new QName("http://tempuri.org/", "TmriOutAccess");
	        QName port = new QName("http://tempuri.org/","BasicHttpBinding_ITmriOutAccess");
	        Client client = dcf.createClient(url, service, port);
	        //开始调用查询接口
	        QName opration = new QName("http://tempuri.org/", "queryObjectOut");
	        webServiceClientCache.put("hx_query_client", client);
	        webServiceOprationCache.put("hx_query_opration", opration);
	        
		}
		//写入
		else {
			//创建客户端
	        QName service = new QName("http://tempuri.org/", "TmriOutAccess");
	        QName port = new QName("http://tempuri.org/","BasicHttpBinding_ITmriOutAccess");
	        Client client = dcf.createClient(url, service, port);
	        //开始调用写入接口
	        QName opration = new QName("http://tempuri.org/", "writeObjectOut");
	        webServiceClientCache.put("hx_write_client", client);
	        webServiceOprationCache.put("hx_write_opration", opration);
		}
	}
	
	
	/**
	 * 判断客户端是否存在
	 * @param type
	 * @return
	 */
	private static boolean isExits(int type) {
		boolean result = false;
		if(type==1) {
			if(webServiceClientCache.get("hx_query_client")!=null && webServiceOprationCache.get("hx_query_opration")!=null) {
				result = true;
			}
		}
		else {
			if(webServiceClientCache.get("hx_write_client")!=null && webServiceOprationCache.get("hx_write_opration")!=null) {
				result = true;
			}
		}
		return result;
	}

	/**
	 *  查询接口
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param callXml
	 * @return
	 */
	public static Object[] queryObject(String xtlb,String jkxlh,String jkid,String callXml,String url) {
		Object[] result = null;
		if(!isExits(1)) {
			createCallInfo(1,url);
		}
		else {
			Client client = (Client) webServiceClientCache.get("hx_query_client");
			QName opration = (QName) webServiceOprationCache.get("hx_query_opration");
			try {
				result = client.invoke(opration, xtlb,jkxlh,jkid,callXml);
			} catch (Exception e) {
				HXLogUtil.error(logger, "调用精英平台查询接口错误，接口id，调用入参，调用地址params{0},{1},{2}", jkid,callXml,url);
			}
		}
		return result;
	}
	
	/**
	 * 写入接口
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param callXml
	 * @return
	 */
	public static Object[] writeObject(String xtlb,String jkxlh,String jkid,String callXml,String url) {
		Object[] result = null;
		if(!isExits(2)) {
			createCallInfo(2,url);
		}
		else {
			Client client = (Client) webServiceClientCache.get("hx_write_client");
			QName opration = (QName) webServiceOprationCache.get("hx_write_opration");
			try {
				result = client.invoke(opration, xtlb,jkxlh,jkid,callXml);
			} catch (Exception e) {
				HXLogUtil.error(logger, "调用精英平台写入接口错误，接口id，调用入参，调用地址params{0},{1},{2}", jkid,callXml,url);
			}
		}
		return result;
	}
}
