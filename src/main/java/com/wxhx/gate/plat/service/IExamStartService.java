package com.wxhx.gate.plat.service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;

/**
 * 开始考试，刷人脸机
 * @author coyi
 *
 */
public interface IExamStartService {
	
	/**
	 * 开始考试
	 * @param whiteListVO
	 * @return
	 */
	HXRespons<FaceResponse> examing(RecordInfo recordInfo);
}
