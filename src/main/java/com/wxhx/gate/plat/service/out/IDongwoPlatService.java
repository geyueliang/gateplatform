package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.controller.vo.FaceMacDevVO;
import com.wxhx.gate.plat.controller.vo.NewDeviceVO;
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
	 * 新增设备
	 * @param newFaceMacDevVO
	 * @return
	 */
	FaceResponse insertDevice(FaceMacDevVO newFaceMacDevVO);
	
	
	/**
	 * 删除白名单
	 * @param newFaceMacDevVO
	 * @return
	 */
	FaceResponse deleteWhiteList(WhiteListVO whiteListVO);
	
	/**
	 * 一键开闸
	 * @param openGateVO
	 * @return
	 */
	FaceResponse openGate(FaceMacDevVO faceMacDevVO);
	
	
	/**
	 * 查询设备
	 * @param newFaceMacDevVO
	 * @return
	 */
	FaceResponse selectDevice(FaceMacDevVO newFaceMacDevVO);
}
