package com.wxhx.gate.plat.service.out;

import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXHttpClient;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

/**
 * 
 * @author coyi
 *
 */
@Service
public class ManagerPlatServiceImpl implements IManagerPlatService{

	private static String XTLB = "17";	//系统类别
	
	private static String jkxlh = "";	//接口序列号
	
	private static String url = "";		//访问地址
	
	/**
	 * 注册写入
	 */
	public RegisterResponse register(RegisterInfoVo registerVo) {
		RegisterResponse result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(registerVo);
			String jkid = "17E05"; //接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取预约信息
	 */
	public ExaminationInfo getAppointmentInfo(ExamineeInfoVO examineeInfoVO) {
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E13";	//获取预约信息接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}

	/**
	 * 获取排考信息
	 * 
	 */
	public ExaminationInfo getSortInfo(ExamineeInfoVO examineeInfoVO) {
		// TODO 预约信息和排考信息共享VO，是否有问题
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E11";	//获取排考信息接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}
	

	/**
	 *  获取考生照片
	 */
	public ExaminationInfo getZP(ExamineeInfoVO examineeInfoVO) {
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E04";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}

	/**
	 * 写入考生门禁照片
	 */
	public RegisterResponse uploadFacePhoto(ExamineeInfoVO examineeInfoVO) {
		RegisterResponse result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E25";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
