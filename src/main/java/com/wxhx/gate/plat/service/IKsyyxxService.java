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
	
	/**
	 * 
	 */
	public boolean updateKszpLsh(ExaminationInfo examinationInfo);
	
	/**
	 * 根据身份证号码查询是否存在预约信息
	 * @param sfzmhm
	 * @return
	 */
	public boolean selectByIdNum(String sfzmhm);
	
	/**
	 * 插入预约信息
	 * @param examinationInfo
	 * @return
	 */
	public int insertKsyyxx(ExaminationInfo examinationInfo);
	
}
