package com.wxhx.gate.plat.service.out;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXHttpClient;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.controller.vo.DeleteExamineeVO;
import com.wxhx.gate.plat.controller.vo.FaceGateVO;
import com.wxhx.gate.plat.controller.vo.FaceMacDevVO;
import com.wxhx.gate.plat.controller.vo.NewDeviceVO;
import com.wxhx.gate.plat.controller.vo.NewExamineeVO;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;

/**
 * 
 * @author coyi
 *
 */
@Service
public class DongwoPlatServiceImpl implements IDongwoPlatService{
	private static String url = "";
	
	private static String appid = ""; 	//开发者应用ID
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	
	//签名算法
	public String getSign() {
		return null;
	}
    

	/**
	 * 上传白名单信息
	 */
	public FaceResponse insertWhiteList(WhiteListVO whiteListVO) {
		NewExamineeVO newExaminee = new NewExamineeVO();
		newExaminee.setAppid(appid);
		newExaminee.setTimestemp(df.format(new Date()));
		newExaminee.setSign(getSign());
		newExaminee.setData(whiteListVO);
		String jsonParam = HXCoreUtil.getJsonString(whiteListVO);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(url, jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}
	
	/**
	 * 新增设备
	 */
	public FaceResponse insertDevice(FaceMacDevVO newFaceMacDevVO) {
		NewDeviceVO newDeviceVO = new NewDeviceVO();
		newDeviceVO.setAppid(appid);
		newDeviceVO.setTimestemp(df.format(new Date()));
		newDeviceVO.setSign(getSign());
		newDeviceVO.setData(newFaceMacDevVO);
		String jsonParam = HXCoreUtil.getJsonString(newDeviceVO);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(url, jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}

	/**
	 * 删除白名单
	 */
	public FaceResponse deleteWhiteList(WhiteListVO whiteListVO) {
		DeleteExamineeVO deleteExamineeVO = new DeleteExamineeVO();
		deleteExamineeVO.setAppid(appid);
		deleteExamineeVO.setTimestemp(df.format(new Date()));
		deleteExamineeVO.setSign(getSign());
		deleteExamineeVO.setPersonnelno(whiteListVO.getPersonnelNo());//人员编号
		String jsonParam = HXCoreUtil.getJsonString(deleteExamineeVO);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(url, jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}
	
	/**
	 *一键开闸 
	 */
	public FaceResponse openGate(FaceMacDevVO faceMacDevVO) {
		FaceGateVO faceGateVO = new FaceGateVO();
		faceGateVO.setAppid(appid);
		faceGateVO.setTimestemp(df.format(new Date()));
		faceGateVO.setSign(getSign());
		faceGateVO.setData(faceMacDevVO);
		String jsonParam = HXCoreUtil.getJsonString(faceGateVO);
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(url, jsonParam));
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}
	
}
