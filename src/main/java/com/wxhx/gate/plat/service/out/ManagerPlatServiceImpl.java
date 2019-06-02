package com.wxhx.gate.plat.service.out;

import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

@Service
public class ManagerPlatServiceImpl implements IManagerPlatService{
	
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
