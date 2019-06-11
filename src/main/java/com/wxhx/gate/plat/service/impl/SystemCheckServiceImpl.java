package com.wxhx.gate.plat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.exam.process.CarUsedInfo;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.controller.vo.CheckCar;
import com.wxhx.gate.plat.controller.vo.CheckItem;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.init.InitCarInfo;
import com.wxhx.gate.plat.service.ISystemCheckService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@Service
public class SystemCheckServiceImpl implements ISystemCheckService{
	
	@Autowired
	private IManagerPlatService iManagerPlatService;
	
	/**
	 * 开始检测
	 */
	public HXRespons<CheckResponse> startCheck(ExamineeInfoQueryVO examineeInfoQueryVO) {
		HXRespons<CheckResponse> finalResult = new HXRespons<CheckResponse>("ERROR", "操作失败", null);
		List<SystemCheckInfo> checkInfo = null;
		CheckresultVO checkresultVO = null;
		
		//获取检测项
		checkInfo = (List<SystemCheckInfo>) iManagerPlatService.getSystemTests(examineeInfoQueryVO).getBodyContent().getContent();
		
		//写入检测结果
		List<CarUsedInfo> canUseCars = InitCarInfo.CANUSECARS;
		
		
		for(CarUsedInfo cars : canUseCars) {
			checkresultVO = new CheckresultVO();
			checkresultVO.setKsdd(examineeInfoQueryVO.getKsdd());
			checkresultVO.setKskm(examineeInfoQueryVO.getKskm());
			checkresultVO.setCx(examineeInfoQueryVO.getCx());
			
			CheckCar checkCar = new CheckCar();
			checkCar.setKchp(cars.getKchp());
			checkCar.setCheckdate(HXCoreUtil.getNowDataStr(new Date(), "yyyy-MM-dd"));
			List<CheckItem> checkitemList = new ArrayList<CheckItem>();
			for(SystemCheckInfo checkContents : checkInfo) {
				CheckItem checkItem = new CheckItem();
				checkItem.setCheckname(checkContents.getCheckname());
				checkItem.setCheckkey(checkContents.getCheckkey());
				checkItem.setCheckresult("1");
				checkitemList.add(checkItem);
			}
			checkCar.setCheckitem(checkitemList);
			checkresultVO.setCheckcar(checkCar);
			WebServiceResult<CheckResponse> webResult = iManagerPlatService.writeCheckResult(checkresultVO);
			if(webResult.getHead()!=null && HXCoreUtil.isEquals(webResult.getHead().getCode(), "1")) {
				finalResult = new HXRespons<CheckResponse>("SUCCESS", "操作成功", null);
			}else {
				finalResult = new HXRespons<CheckResponse>("ERROR", cars.getKcbh()+":写入检测失败", null);
				return finalResult;
			}
		}
		
		
		return finalResult;
	}

}
