package com.wxhx.gate.plat.service.out;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.wxhx.gate.plat.util.PersonFaceMachineInfo;
import com.wxhx.gate.plat.util.PersonFacePlatUtil;

/**
 * 
 * @author coyi
 *
 */
@Service
public class DongwoPlatServiceImpl implements IDongwoPlatService{
	
	@Autowired
	private	PersonFacePlatUtil personFacePlatUtil; 
	
	/**
	 * 上传白名单信息
	 */
	public FaceResponse insertWhiteList(WhiteListVO whiteListVO) {
		NewExamineeVO newExaminee = new NewExamineeVO();
		newExaminee.setAppid(PersonFaceMachineInfo.APPID);
		newExaminee.setTimestemp(PersonFaceMachineInfo.getTimeTemp());
		newExaminee.setSign(personFacePlatUtil.getSign(whiteListVO));
		newExaminee.setData(whiteListVO);
		String jsonParam = HXCoreUtil.getJsonString(newExaminee);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(PersonFaceMachineInfo.url+"/addCard", jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}
	
	/**
	 * 新增设备
	 * update:修改上传地址
	 */
	public FaceResponse insertDevice(FaceMacDevVO newFaceMacDevVO) {
		NewDeviceVO newDeviceVO = new NewDeviceVO();
		newDeviceVO.setUrl(newFaceMacDevVO.getDeviceAutoRecordUrl());
		String jsonParam = HXCoreUtil.getJsonString(newDeviceVO);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(PersonFaceMachineInfo.url+"/setUploadUrl", jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}

	/**
	 * 删除白名单
	 */
	public FaceResponse deleteWhiteList(WhiteListVO whiteListVO) {
		DeleteExamineeVO deleteExamineeVO = new DeleteExamineeVO();
		deleteExamineeVO.setAppid(PersonFaceMachineInfo.APPID);
		deleteExamineeVO.setTimestemp(PersonFaceMachineInfo.getTimeTemp());
		deleteExamineeVO.setSign(personFacePlatUtil.getSign(whiteListVO));
		deleteExamineeVO.setIdnum(whiteListVO.getPersonnelIDCard());//人员编号
		String jsonParam = HXCoreUtil.getJsonString(deleteExamineeVO);
		//上传
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(PersonFaceMachineInfo.url+"/deleteCard", jsonParam));
		
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}
	
	/**
	 *一键开闸 
	 */
	public FaceResponse openGate(FaceMacDevVO faceMacDevVO) {
		FaceGateVO faceGateVO = new FaceGateVO();
		faceGateVO.setAppid(PersonFaceMachineInfo.APPID);
		faceGateVO.setTimestemp(PersonFaceMachineInfo.getTimeTemp());
		faceGateVO.setSign(personFacePlatUtil.getSign(faceMacDevVO));
		faceGateVO.setData(faceMacDevVO);
		String jsonParam = HXCoreUtil.getJsonString(faceGateVO);
		String responsejson = HXCoreUtil.getJsonString(HXHttpClient.httpPost(PersonFaceMachineInfo.url+"/openDoor", jsonParam));
		FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
		return result;
	}

	/**
	 * 查询设备
	 */
	/*
	 * public FaceResponse selectDevice(FaceMacDevVO newFaceMacDevVO) { NewDeviceVO
	 * newDeviceVO = new NewDeviceVO();
	 * newDeviceVO.setAppid(PersonFaceMachineInfo.APPID);
	 * newDeviceVO.setTimestemp(PersonFaceMachineInfo.getTimeTemp());
	 * newDeviceVO.setSign(personFacePlatUtil.getSign(newFaceMacDevVO));
	 * newDeviceVO.setData(newFaceMacDevVO); String jsonParam =
	 * HXCoreUtil.getJsonString(newDeviceVO); //上传 String responsejson =
	 * HXCoreUtil.getJsonString(HXHttpClient.httpPost(url, jsonParam));
	 * 
	 * FaceResponse result =HXCoreUtil.jsonToObj(responsejson,FaceResponse.class) ;
	 * return result; }
	 */
	
	
	
}
