package com.wxhx.gate.plat.service.out;

import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

/**
 * 
 * @author coyi
 *
 */
@Service
public class ManagerPlatServiceImpl implements IManagerPlatService{

	
	/**
	 * 注册写入
	 */
	public WebServiceResult<RegisterResponse> register(RegisterInfoVo registerVo) {
		WebServiceResult result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(registerVo);
			String jkid = "17E05"; //接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取预约信息
	 */
	public WebServiceResult<ExaminationInfo> getAppointmentInfo(ExamineeInfoVO examineeInfoVO) {
		WebServiceResult<ExaminationInfo> ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E13";	//获取预约信息接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
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
	public WebServiceResult<ExaminationInfo> getSortInfo(ExamineeInfoVO examineeInfoVO) {
		// TODO 预约信息和排考信息共享VO，是否有问题
		WebServiceResult<ExaminationInfo> ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E11";	//获取排考信息接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}
	

	/**
	 *  获取考生照片
	 */
	public WebServiceResult<ExaminationInfo> getZP(ExamineeInfoVO examineeInfoVO) {
		WebServiceResult<ExaminationInfo> ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E04";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}

	/**
	 * 写入考生门禁照片
	 */
	public WebServiceResult<RegisterResponse> uploadFacePhoto(ExamineeInfoVO examineeInfoVO) {
		WebServiceResult<RegisterResponse> result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E25";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
