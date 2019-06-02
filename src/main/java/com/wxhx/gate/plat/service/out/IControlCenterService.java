package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;

/**
 * 
 * @author coyi
 *
 */
public interface IControlCenterService {
	
	/**
	 * 插入排考信息至控制中心
	 * @param examinationInfo
	 * @return
	 */
	int insertSortInfo(ExaminationInfo examinationInfo);
}
