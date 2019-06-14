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

import com.wxhx.gate.plat.bean.cxf.HXJaxWsDynamicClientFactory;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.service.bean.WebServiceBean;
import com.wxhx.gate.plat.service.bean.WebServiceResult;

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
	public static  WebServiceResult xmlToBean(String xmlStr,Class type) throws Exception {
		JAXBContext context = JAXBContext.newInstance(WebServiceResult.class,type);
		Unmarshaller unmarshaller = context.createUnmarshaller(); 
		//照片不用解码
		if(xmlStr.contains("<zp>")&&xmlStr.contains("</zp>")) {
			int zpStart = xmlStr.indexOf("<zp>");
			int zpEnd = xmlStr.indexOf("</zp>");
			String firstStr = xmlStr.substring(0,zpStart);
			String endStr = xmlStr.substring(zpEnd+5,xmlStr.length());
			String zpBase64Str = xmlStr.substring(zpStart+4,zpEnd);
			xmlStr = URLDecoder.decode(firstStr, "utf-8")+"<zp>"+zpBase64Str+"</zp>"+URLDecoder.decode(endStr, "utf-8");
//			xmlStr.indexOf("<zp>")
		}
		else {
			xmlStr = URLDecoder.decode(xmlStr, "utf-8");
		}
		WebServiceResult webServiceBean = (WebServiceResult) unmarshaller.unmarshal(new StringReader(xmlStr)); 
        return webServiceBean; 
	}
	
	
	/**
	 * 返回xml转换成bean
	 * @param <T>
	 * @param <T>
	 * @param xmlStr
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static <T> T xmlToBean(String xmlStr,Class type,boolean isWriteReturn) throws Exception {
//		System.out.println("原始字符串：" + xmlStr);
		JAXBContext context = JAXBContext.newInstance(WebServiceResult.class,type);
		if(isWriteReturn) {
			context = JAXBContext.newInstance(WebServiceBean.class,type);
		}
		Unmarshaller unmarshaller = context.createUnmarshaller(); 
		//照片不用解码
		if(xmlStr.contains("<zp>")&&xmlStr.contains("</zp>")) {
			int zpStart = xmlStr.indexOf("<zp>");
			int zpEnd = xmlStr.indexOf("</zp>");
			String firstStr = xmlStr.substring(0,zpStart);
			String endStr = xmlStr.substring(zpEnd+5,xmlStr.length());
			String zpBase64Str = xmlStr.substring(zpStart+4,zpEnd);
			xmlStr = URLDecoder.decode(firstStr, "utf-8")+"<zp>"+zpBase64Str+"</zp>"+URLDecoder.decode(endStr, "utf-8");
//			xmlStr.indexOf("<zp>")
		}
		else {
			xmlStr = URLDecoder.decode(xmlStr, "utf-8");
		}
		WebServiceBean webServiceBean = (WebServiceBean) unmarshaller.unmarshal(new StringReader(xmlStr));
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
		HXJaxWsDynamicClientFactory dcf = HXJaxWsDynamicClientFactory.newInstance();
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
	 * 封装调用webservice查询接口
	 * @param xtlb
	 * @param jkxlh
	 * @param jkid
	 * @param callXml
	 * @return
	 * @throws Exception 
	 */
	public static String writeWebService(String xtlb, String jkxlh, String jkid, String callXml, String url)
			throws Exception {
		HXJaxWsDynamicClientFactory dcf = HXJaxWsDynamicClientFactory.newInstance();
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
