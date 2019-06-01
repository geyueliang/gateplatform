package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.controller.vo.WhiteListVO;

/**
 * 人脸机
 * @author coyi
 *
 */
public interface IDongwoPlatService {
	
	boolean insertWhiteList(WhiteListVO whiteListVO);
	
}
