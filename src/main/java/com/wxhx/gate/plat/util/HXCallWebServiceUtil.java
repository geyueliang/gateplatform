package com.wxhx.gate.plat.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.service.bean.WebServiceBean;

/**
 * 调用webservice 工具类
 * @author geliang
 *
 */
public class HXCallWebServiceUtil {

	/**
	 * 将bean转换成xml
	 * @param <T>
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	public static <T> String beanToXml(T t) throws Exception {
		JAXBContext context = JAXBContext.newInstance(WebServiceBean.class,t.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
		WebServiceBean<T> query = new WebServiceBean<T>();
		query.setContent(t);
		StringWriter writer = new StringWriter();
		marshaller.marshal(query,writer);
		return writer.toString();
	}
	
	/**
	 * 中午字符转换
	 * @param str
	 * @return
	 */
	public static String enCodeStr(String str) {
		try {
			return URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * 返回xml转换成bean
	 * @param <T>
	 * @param xmlStr
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static <T> T xmlToBean(String xmlStr,Class<T> type) throws Exception {
		JAXBContext context = JAXBContext.newInstance(WebServiceBean.class,type);
		Unmarshaller unmarshaller = context.createUnmarshaller(); 
		xmlStr = URLDecoder.decode(xmlStr, "utf-8");
		WebServiceBean<T> webServiceBean = (WebServiceBean<T>) unmarshaller.unmarshal(new StringReader(xmlStr)); 
        return (T) webServiceBean.getContent(); 
	}
	
	
	/**
	 * 封装调用webservice查询接口接口
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param callXml
	 * @return
	 * @throws Exception 
	 */
	public static String queryWebService(String xtlb, String jkxlh, String jkid, String callXml, String url)
			throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		//创建客户端
        QName service = new QName("http://tempuri.org/", "TmriOutAccess");
        QName port = new QName("http://tempuri.org/","BasicHttpBinding_ITmriOutAccess");
        Client client = dcf.createClient(url, service, port);
        //开始调用查询接口
        QName opration = new QName("http://tempuri.org/", "queryObjectOut");
        Object[] resultObj = client.invoke(opration, xtlb,jkxlh,jkid,callXml);
        String result = "";
        if(resultObj!=null&&resultObj.length>0) {
        	result =  (String)resultObj[0];
        }
		return result;
	}
	
	
	/**
	 * 封装调用webservice查询写入接口
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param callXml
	 * @return
	 * @throws Exception 
	 */
	public static String writeWebService(String xtlb, String jkxlh, String jkid, String callXml, String url)
			throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		//创建客户端
        QName service = new QName("http://tempuri.org/", "TmriOutAccess");
        QName port = new QName("http://tempuri.org/","BasicHttpBinding_ITmriOutAccess");
        Client client = dcf.createClient(url, service, port);
        //开始调用查询接口
        QName opration = new QName("http://tempuri.org/", "writeObjectOut");
        Object[] resultObj = client.invoke(opration, xtlb,jkxlh,jkid,callXml);
        String result = "";
        if(resultObj!=null&&resultObj.length>0) {
        	result =  (String)resultObj[0];
        }
		return result;
	}
	

	/**
	 * 根据接口id 和 内容调用查询内容
	 * @param jkid
	 * @param queryXml
	 * @return
	 * @throws Exception 
	 */
	public static String queryWebService(String jkid,String queryXml) throws Exception {
		String xtlb = jkid.substring(0, 2);
		String jkxlh = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.JKXLH);
		String url = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.WEBSERVICEURL)+"?wsdl";
		return queryWebService(xtlb,jkxlh,jkid,queryXml,url);
	}
	
	/***
	 * 重载接口调用写入方法 
	 * @param jkid
	 * @param writeXml
	 * @return
	 * @throws Exception
	 */
	public static String writeWebService(String jkid,String writeXml)
			throws Exception {
		String xtlb = jkid.substring(0, 2);
		String jkxlh = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.JKXLH);
		String url = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.WEBSERVICEURL)+"?wsdl";
		return writeWebService(xtlb,jkxlh,jkid,writeXml,url);
	}
}
