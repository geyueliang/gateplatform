package com.wxhx.gate.plat.service.out;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RecordInfo;

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
	
	/**
	 * 获取考生预约信息
	 */
	ExaminationInfo getExaminationInfo(ExaminationInfo examinationInfo);
	
	
	/**
	 * 插入证件照片
	 * @param examinationInfo
	 * @return
	 */
	int insertPhotoInfo(ExaminationInfo examinationInfo);
	
	/**
	 * 更新采集照片
	 * @param examinationInfo
	 * @return
	 */
	int updatePhotoInfo(RecordInfo recordInfo);
	
	/**
	 * 删除报道照片
	 * @param sfzmhm
	 * @return
	 */
	public int deletePhotoInfo(String sfzmhm);
}
