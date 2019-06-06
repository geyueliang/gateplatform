package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.controller.vo.FaceInfoDelVo;
import com.wxhx.gate.plat.controller.vo.FaceMacDevVO;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;

/**
 * 人脸机
 * @author coyi
 *
 */
public interface IDongwoPlatService {
	
	/**
	 * 上传白名单信息
	 * @param whiteListVO
	 * @return
	 */
	FaceResponse insertWhiteList(WhiteListVO whiteListVO);
	
	
	
	
	/**
	 * 删除白名单
	 * @param newFaceMacDevVO
	 * @return
	 */
	FaceResponse deleteWhiteList(FaceInfoDelVo faceInfoDelVo);
	
	/**
	 * 一键开闸
	 * @param openGateVO
	 * @return
	 */
	FaceResponse openGate();
	
	
	/**
	 * 
	 * 修改人脸机上传地址
	 * @param iploadUrl
	 * @return
	 */
	FaceResponse updateUploadUrl(String iploadUrl);
}
