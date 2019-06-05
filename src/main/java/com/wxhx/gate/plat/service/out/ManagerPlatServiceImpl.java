package com.wxhx.gate.plat.service.out;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
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

	
	/**
	 * 注册写入
	 */
	public RegisterResponse register(RegisterInfoVo registerVo) {
		RegisterResponse result = null;
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
	public ExaminationInfo getAppointmentInfo(ExamineeInfoQueryVO examineeInfoQueryVO) {
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E13";	//获取预约信息接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
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
	public ExaminationInfo getSortInfo(ExamineeInfoQueryVO examineeInfoQueryVO) {
		// TODO 预约信息和排考信息共享VO，是否有问题
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E11";	//获取排考信息接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			ExaminationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExaminationInfo;
	}
	

	/**
	 *  获取考生照片
	 */
	public ExaminationInfo getZP(ExamineeInfoQueryVO examineeInfoQueryVO) {
		ExaminationInfo ExaminationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E04";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
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
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 写入视频认证
	 */
	public RegisterResponse writeVideoAttestation(ExamineeInfoVO examineeInfoVO) {
		RegisterResponse result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoVO);
			String jkid = "17E14";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
