package com.wxhx.gate.plat.service.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.dao.ExamineeSortInfoMapper;

/**
 * 
 * @author coyi
 *
 */
@Service
public class ControlCenterServiceImpl implements IControlCenterService{
	
	@Autowired
	private ExamineeSortInfoMapper examineeSortInfoMapper;

	public int insertSortInfo(ExaminationInfo examinationInfo) {
		
		
		examineeSortInfoMapper.insert(examinationInfo);
		return examineeSortInfoMapper.insert(examinationInfo);
	}
	
}
