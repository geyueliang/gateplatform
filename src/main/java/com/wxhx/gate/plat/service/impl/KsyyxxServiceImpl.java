package com.wxhx.gate.plat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.service.IKsyyxxService;

@Service
public class KsyyxxServiceImpl implements IKsyyxxService{

	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	public boolean updateKsyyxx(ExaminationInfo examinationInfo) {
		/**
		 * 更新考试预约信息  更新条件是身份证信息和当前日期
		 */
		
		return false;
	}

}
