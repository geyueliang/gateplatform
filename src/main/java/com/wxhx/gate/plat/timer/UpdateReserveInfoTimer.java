package com.wxhx.gate.plat.timer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.entity.Kscl;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

/**
 * 定时获取上车人员信息并更新预约信息表
 * @author geliang
 *
 */
@Component
public class UpdateReserveInfoTimer {

	@Autowired
	private KsclMapper ksclMapper;
	
	@Autowired
	private IManagerPlatService iManagerPlatServer;
	
	@Scheduled(fixedRateString = "${wxhx.gate.plate.update.reserveInfo.rate}",initialDelayString = "${wxhx.gate.plate.update.delay.timme}")
	public void updateInfo() {
		//获取当前可用循环的车辆信息
		List<Kscl> kscls = ksclMapper.selectAll();
		int successSum = 0;
		//循环车辆
		for(Kscl kscl:kscls) {
			if(iManagerPlatServer.getExaminSortInfo(kscl.getKchp())!=null) {
				successSum = successSum+1;
			}
		}
		//都成功的情况下考试顺序（排考）信息
		if(successSum>0) {
			ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
			//考试地点
			examineeInfoQueryVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
			//考试科目
			examineeInfoQueryVO.setKskm("2");
			//返回记录数量
			examineeInfoQueryVO.setFhjls("50");
			WebServiceResult<ExaminationInfo> webServiceRes = iManagerPlatServer.getSortInfo(examineeInfoQueryVO);
			if(webServiceRes.getBodyContent().getContent()!=null) {
				List<ExaminationInfo> sortExams = webServiceRes.getBodyContent().getContent();
				//更新预约信息表中的数据
				for(ExaminationInfo examSortInfo:sortExams) {
					
				}
			}
			
		}
	}
}
