package com.wxhx.gate.plat.service.out;

import org.springframework.stereotype.Service;

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

	public ExaminationInfo getAppointmentInfo(ExamineeInfoVO examineeInfoVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExaminationInfo getSortInfo(ExamineeInfoVO examineeInfoVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public ExaminationInfo getZP(ExamineeInfoVO examineeInfoVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public RegisterResponse uploadFacePhoto(ExamineeInfoVO examineeInfoVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String XTLB = "17";
	
	private static String jkxlh = "";
	
	private static String url = "";;
	
	/**
	 * 注册写入
	 */
	public RegisterResponse register(RegisterInfoVo registerVo) {
		RegisterResponse result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(registerVo);
			String jkid = "17E05";
			String responsStr = HXCallWebServiceUtil.writeWebService(XTLB, jkxlh, jkid, writeXml, url);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
