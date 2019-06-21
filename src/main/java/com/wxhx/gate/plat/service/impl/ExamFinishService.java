package com.wxhx.gate.plat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.out.ExamFinishResponse;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.KszpMapper;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.dao.entity.Kszp;
import com.wxhx.gate.plat.service.IExamFinishService;
import com.wxhx.gate.plat.service.IExamProcessService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.util.GatePlatUtil;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;


/**
 * 
 * @author coyi
 *
 */
@Service
public class ExamFinishService implements IExamFinishService{
	@Autowired
	private KszpMapper kszpMapper;
	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	@Autowired
	private IExamProcessService iExamProcessService;

	public HXRespons<ExamFinishResponse> examFinish(String sfzmhm, String score) {
		HXRespons<ExamFinishResponse> finalResult = new HXRespons<ExamFinishResponse>("0", "操作失败", null);
		WebServiceResult<ExamFinishResponse> result = null;
		
		//获取预约信息
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(sfzmhm);
		if(ksyyxx != null) {
			
			//获取预约照片
			Kszp kszp = kszpMapper.getKszpByCarNo(sfzmhm, "BD");
			if(kszp != null) {
				ExamEnd examEnd = new ExamEnd();
				examEnd.setLsh(ksyyxx.getLsh());
				examEnd.setKskm("2");
				examEnd.setSfzmhm(ksyyxx.getSfzmhm());
				examEnd.setZp(kszp.getZp());
				examEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
				examEnd.setKscj(score);
				examEnd.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
				examEnd.setDwjgdm(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.XZGLBM));
				examEnd.setZdbs(GatePlatUtil.getLocalhostIp());
				
				try {
					result = HXCallWebServiceUtil.xmlToBean(iExamProcessService.examEnd(examEnd), ExamFinishResponse.class);
					if(result != null) {
						finalResult = new HXRespons<ExamFinishResponse>(result.getHead().getCode(), result.getHead().getMessage(), null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return finalResult;
	}

}
