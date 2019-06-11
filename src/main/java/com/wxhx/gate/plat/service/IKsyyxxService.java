package com.wxhx.gate.plat.service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;

/**
 * 考试预约信息操作服务
 * @author geliang
 *
 */
public interface IKsyyxxService {

	/**
	 * 更新考试预约信息
	 * @param examinationInfo
	 * @return
	 */
	public boolean updateKsyyxx(ExaminationInfo examinationInfo);
	
}
