package com.wxhx.gate.plat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoVO;
import com.wxhx.gate.plat.controller.vo.FaceInfoDelVo;
import com.wxhx.gate.plat.service.IExamStartService;
import com.wxhx.gate.plat.service.out.IControlCenterService;
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
	private IManagerPlatService iManagerPlatService;
	
	@Autowired
	private IControlCenterService iControlCenterService;	//控制中心
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	private static String kskm = "2";
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	public HXRespons<FaceResponse> examing(RecordInfo recordInfo) {
		HXRespons<FaceResponse> finalResult = new HXRespons<FaceResponse>("ERROR","操作失败",null);
		
		ExamineeInfoVO examineeInfoVO = new ExamineeInfoVO();
		
		//判断比对结果
		int cardResultStatus = recordInfo.getCardResultStatus();	//人证比对
		int faceResultStatus = recordInfo.getFaceResultStatus();	//白名单比对
		
		if(cardResultStatus != 0) {
			if(faceResultStatus != 0) {
				return finalResult;
			}
			return finalResult;
		}
		
		//比对成功，上传采集照片
		examineeInfoVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		examineeInfoVO.setKskm(kskm);
		examineeInfoVO.setSfzmhm(recordInfo.getIdNum());
		examineeInfoVO.setMjzp(recordInfo.getScenePhoto());
		try {
			examineeInfoVO.setKsrq(HXCoreUtil.getNowDataStr(df.parse(recordInfo.getTime()), "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		RegisterResponse photoResponse = iManagerPlatService.uploadFacePhoto(examineeInfoVO);
		if(photoResponse != null) {
			//更新门禁照片
			int updateRes = iControlCenterService.updatePhotoInfo(recordInfo);
			if(updateRes > 0) {	
				finalResult = new HXRespons<FaceResponse>("SUCCESS","操作成功",null);
			}
		}
		
		return finalResult;
	}
	
}
