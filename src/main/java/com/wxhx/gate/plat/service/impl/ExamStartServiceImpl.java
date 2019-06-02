package com.wxhx.gate.plat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	WhiteListVO whiteListVO = new WhiteListVO();
	FaceMacDevVO faceMacDevVO = new FaceMacDevVO();
	ExamineeInfoVO examineeInfoVO = new ExamineeInfoVO();
	
	private static String ksdd = "";
	private static String kskm = "2";
	private static String deviceAppID = "";
	private static String deviceNo = "";
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public HXRespons<FaceResponse> examing(RecordInfo recordInfo) {
		HXRespons<RegisterResponse> finalResult = new HXRespons<RegisterResponse>("ERROR","操作失败",null);
		
		//判断比对结果
		int cardResultStatus = recordInfo.getCardResultStatus();	//人证比对
		int faceResultStatus = recordInfo.getFaceResultStatus();	//白名单比对
		
		if(cardResultStatus != 1) {
			if(faceResultStatus != 1) {
				return null;
			}
			return null;
		}
		//比对成功，上传采集照片
		examineeInfoVO.setKsdd(ksdd);
		examineeInfoVO.setKskm(kskm);
		examineeInfoVO.setSfzmhm(recordInfo.getIdNum());
		examineeInfoVO.setMjzp(recordInfo.getScenePhoto());
		try {
			examineeInfoVO.setKsrq(HXCoreUtil.getNowDataStr(df.parse(recordInfo.getTime()), "yyyy-mm-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RegisterResponse result = iManagerPlatService.uploadFacePhoto(examineeInfoVO);
		
		//删除白名单信息
		whiteListVO.setPersonnelNo(recordInfo.getIdNum());
		FaceResponse whitelistResponse = iDongwoPlatService.deleteWhiteList(whiteListVO);
		
		//开闸
		faceMacDevVO.setDeviceAppID(deviceAppID);
		faceMacDevVO.setDeviceNo(deviceNo);
		FaceResponse openGateResponse = iDongwoPlatService.openGate(faceMacDevVO);
		
		
		return null;
	}
	
}
