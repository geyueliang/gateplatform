package com.wxhx.gate.plat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.service.IKsyyxxService;

@Service
public class KsyyxxServiceImpl implements IKsyyxxService{

	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	public boolean updateKsyyxx(ExaminationInfo examinationInfo) {
		/**
		 * 更新考试预约信息  更新条件是身份证信息和当前日期
		 */
		Ksyyxx ksyyxx = new Ksyyxx();
		ksyyxx.setSfzmhm(examinationInfo.getSfzmhm());
		ksyyxx.setLsh(examinationInfo.getLsh());
		ksyyxx.setZkzmbh(examinationInfo.getZkzmbh());
		ksyyxx.setKsyy(examinationInfo.getKsyy());
		ksyyxx.setKscx(examinationInfo.getKscx());
		ksyyxx.setKscc(examinationInfo.getYycs()+"");
		ksyyxx.setDlr(examinationInfo.getDlr());
		ksyyxx.setKsy1(examinationInfo.getKsy1());
		ksyyxx.setKsy2(examinationInfo.getKsy2());
		ksyyxx.setKsxm(examinationInfo.getKsxm());
		ksyyxx.setLxxh(examinationInfo.getLxxh());
		ksyyxx.setCtlbit1(examinationInfo.getKcbh());
		ksyyxx.setCtlbit3(examinationInfo.getKssxxh());
		int res = ksyyxxMapper.updateByCardId(ksyyxx);
		if(res == 1) {
			return true;
		}
		return false;
	}

}
