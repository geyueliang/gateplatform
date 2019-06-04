package com.wxhx.gate.plat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.FaceMacDevVO;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;
import com.wxhx.gate.plat.service.IExamStartService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;
import com.wxhx.gate.plat.util.PersonFaceMachineInfo;

/**
 * 
 * @author coyi
 *
 */
@Service
public class ExamStartServiceImpl implements IExamStartService{

	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Autowired
	private IManagerPlatService iManagerPlatService;
	
	private static String kskm = "2";
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public HXRespons<FaceResponse> examing(RecordInfo recordInfo) {
		HXRespons<FaceResponse> finalResult = new HXRespons<FaceResponse>("ERROR","操作失败",null);
		
		WhiteListVO whiteListVO = new WhiteListVO();
		ExamineeInfoVO examineeInfoVO = new ExamineeInfoVO();
		
		//判断比对结果
		int cardResultStatus = recordInfo.getCardResultStatus();	//人证比对
		int faceResultStatus = recordInfo.getFaceResultStatus();	//白名单比对
		
		if(cardResultStatus != 1) {
			if(faceResultStatus != 1) {
				return finalResult;
			}
			return finalResult;
		}
		//比对成功，上传采集照片
		examineeInfoVO.setKsdd(PersonFaceMachineInfo.KSDD);
		examineeInfoVO.setKskm(kskm);
		examineeInfoVO.setSfzmhm(recordInfo.getIdNum());
		examineeInfoVO.setMjzp(recordInfo.getScenePhoto());
		try {
			examineeInfoVO.setKsrq(HXCoreUtil.getNowDataStr(df.parse(recordInfo.getTime()), "yyyy-mm-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RegisterResponse photoResponse = iManagerPlatService.uploadFacePhoto(examineeInfoVO);
		
		//删除白名单信息
		whiteListVO.setPersonnelIDCard(recordInfo.getIdNum());
		FaceResponse delWhitelistResponse = iDongwoPlatService.deleteWhiteList(whiteListVO);
		
		if(photoResponse != null && delWhitelistResponse != null) {
			//开闸
			FaceMacDevVO faceMacDevVO = new FaceMacDevVO();
			faceMacDevVO.setDeviceAppID(PersonFaceMachineInfo.APPID);
			faceMacDevVO.setDeviceNo(PersonFaceMachineInfo.DEVICENO);
			FaceResponse openGateResponse = iDongwoPlatService.openGate(faceMacDevVO);
			if(openGateResponse != null) {
				finalResult = new HXRespons<FaceResponse>("SUCCESS","操作成功",openGateResponse);
			}
		}
		
		return finalResult;
	}
	
}
