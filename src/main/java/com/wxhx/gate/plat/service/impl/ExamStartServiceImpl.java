package com.wxhx.gate.plat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.OpenGateVO;
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
	
	
	public HXRespons<FaceResponse> examing(RecordInfo recordInfo) {
		
		WhiteListVO whiteListVO = new WhiteListVO();
		OpenGateVO openGateVO = new OpenGateVO();
		ExamineeInfoVO examineeInfoVO = new ExamineeInfoVO();
		FaceResponse whitelistResponse = new FaceResponse();
		FaceResponse openGateResponse = new FaceResponse();
		RegisterResponse result = new RegisterResponse();

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
		result = iManagerPlatService.uploadFacePhoto(examineeInfoVO);
		
		//删除白名单信息
		whiteListVO.setPersonnelCardNo(recordInfo.getIdNum());
		
		whitelistResponse = iDongwoPlatService.deleteWhiteList(whiteListVO);
		
		//开闸
		openGateVO.setAppid(recordInfo.getId());
		
		openGateResponse = iDongwoPlatService.openGate(openGateVO);
		
		
		
		return null;
	}
	
}
