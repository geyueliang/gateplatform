package com.wxhx.gate.plat.util;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

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
		return URLEncoder.encode(writer.toString(), "utf-8");
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
	 * 
	
		public static void main(String[] args) throws Exception {
		/*
		 * QqOnlineWebService onlineWebService = new QqOnlineWebService();
		 * QqOnlineWebServiceSoap soap = onlineWebService.getQqOnlineWebServiceSoap();
		 * System.out.println(soap.qqCheckOnline("1249440309"));
		 
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        Client client = dcf.createClient("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl0");
        QName service = new QName("http://WebXml.com.cn/", "qqOnlineWebService");
        QName port = new QName("http://WebXml.com.cn/","qqOnlineWebServiceSoap");
        Client client = dcf.createClient("http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl", service, port);
        QName opration = new QName("http://WebXml.com.cn/", "qqCheckOnline");
        Object[] res = client.invoke(opration, "1249440309");
        System.out.println(res[0]);
		
	}*/
		
	 
}
