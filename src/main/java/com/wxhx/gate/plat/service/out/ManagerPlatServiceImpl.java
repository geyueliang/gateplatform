package com.wxhx.gate.plat.service.out;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.bean.exam.process.CarUsedInfo;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.RegisterInfoVo;
import com.wxhx.gate.plat.init.InitCarInfo;
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
	public RegisterResponse register(RegisterInfoVo registerVo) {
		RegisterResponse result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(registerVo);
			String jkid = "17E05"; //接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取预约信息
	 */
	public WebServiceResult<ExaminationInfo> getAppointmentInfo(ExamineeInfoQueryVO examineeInfoQueryVO) {
		WebServiceResult<ExaminationInfo> examinationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E13";	//获取预约信息接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			examinationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examinationInfo;
	}

	/**
	 * 获取排考信息
	 * 
	 */
	public WebServiceResult<ExaminationInfo> getSortInfo(ExamineeInfoQueryVO examineeInfoQueryVO) {
		WebServiceResult<ExaminationInfo> webServiceResult = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E11";	//获取排考信息接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			webServiceResult = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webServiceResult;
	}
	

	/**
	 *  获取考生照片
	 */
	public WebServiceResult<ExaminationInfo> getZP(ExamineeInfoQueryVO examineeInfoQueryVO) {
		WebServiceResult<ExaminationInfo> examinationInfo = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E04";	//获取考生照片接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			examinationInfo = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examinationInfo;
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
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class, true);
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
			String jkid = "17E14";	//获取视频认证开启接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			if(HXCoreUtil.isEmpty(responsStr)) {
				return result;
			}
			result = HXCallWebServiceUtil.xmlToBean(responsStr, RegisterResponse.class, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public WebServiceResult<ExaminationInfo> getExaminSortInfo() {
		ExamineeInfoQueryVO examineeInfoQueryVO =  new ExamineeInfoQueryVO();
		examineeInfoQueryVO.setKskm("2");
		examineeInfoQueryVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		//获取当前可用车牌信息
		CarUsedInfo carUsedInfo = InitCarInfo.getCanUseCar();
		examineeInfoQueryVO.setKchp(carUsedInfo.getKchp());
		WebServiceResult<ExaminationInfo> webServiceResult = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E07";	//获取排考信息接口序列号
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			webServiceResult = HXCallWebServiceUtil.xmlToBean(responsStr, ExaminationInfo.class);
			if(webServiceResult.getBodyContent()!=null&&webServiceResult.getBodyContent().getContent()!=null&&webServiceResult.getBodyContent().getContent().size()>0) {
				
				ExaminationInfo ex =  (ExaminationInfo)webServiceResult.getBodyContent().getContent().get(0);
				ex.setKchp(carUsedInfo.getKchp());
				ex.setKcbh(carUsedInfo.getKcbh());
				ex.setLxxh(carUsedInfo.getKslx());
				//成功 设置预约测试
				return webServiceResult;
			}
			//当前获取信息不对 尝试其他车辆
			else {
				int tryTime = 1;
				List<String> tryHp = new ArrayList<String>();
				tryHp.add(carUsedInfo.getKchp());
				while(tryTime<=InitCarInfo.tryTimes()&&tryHp.size()<InitCarInfo.tryTimes()) {
					CarUsedInfo tryCar = InitCarInfo.getTryCanUseCar(tryHp);
					tryHp.add(tryCar.getKchp());
					examineeInfoQueryVO.setKchp(tryCar.getKchp());
					String tryWriteXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
					String tryResponsStr = HXCallWebServiceUtil.queryWebService(jkid, tryWriteXml);
					webServiceResult = HXCallWebServiceUtil.xmlToBean(tryResponsStr, ExaminationInfo.class);
					if(webServiceResult.getBodyContent()!=null&&webServiceResult.getBodyContent().getContent()!=null&&webServiceResult.getBodyContent().getContent().size()>0) {
						ExaminationInfo ex =  (ExaminationInfo)webServiceResult.getBodyContent().getContent().get(0);
						ex.setKchp(carUsedInfo.getKchp());
						ex.setKcbh(carUsedInfo.getKcbh());
						
						//成功 设置预约测试
						return webServiceResult;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webServiceResult;
	}

	/**
	 * 获取系统检测信息
	 */
	public WebServiceResult<SystemCheckInfo> getSystemTests(ExamineeInfoQueryVO examineeInfoQueryVO) {
		WebServiceResult<SystemCheckInfo> webServiceResult = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(examineeInfoQueryVO);
			String jkid = "17E01";	//
			String responsStr = HXCallWebServiceUtil.queryWebService(jkid, writeXml);
			webServiceResult = HXCallWebServiceUtil.xmlToBean(responsStr, SystemCheckInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webServiceResult;
	}

	/**
	 * 写入检测结果
	 */
	public WebServiceResult<CheckResponse> writeCheckResult(CheckresultVO checkresultVO) {
		WebServiceResult<CheckResponse> result = null;
		try {
			String writeXml = HXCallWebServiceUtil.beanToXml(checkresultVO);
			String jkid = "17E02";	//获取视频认证开启接口序列号
			String responsStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			if(HXCoreUtil.isEmpty(responsStr)) {
				return result;
			}
			result = HXCallWebServiceUtil.xmlToBean(responsStr, CheckResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
