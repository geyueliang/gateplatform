package com.wxhx.gate.plat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.CheckCar;
import com.wxhx.gate.plat.controller.vo.CheckItem;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.entity.Kscl;
import com.wxhx.gate.plat.service.IDealCarInfoService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@Service
public class DealCarInfoServiceImpl implements IDealCarInfoService{

	
	@Autowired
	private IManagerPlatService iManagerPlatService;
	
	@Autowired
	private KsclMapper ksMapper;
	
	public boolean reportExceptionCar(String carNum) {
		boolean result = false;
		//获取考车信息
		Kscl kscl = ksMapper.selectKcInfo(carNum);
		//查询上报信息内容
		ExamineeInfoQueryVO examineeInfoQueryVO = new ExamineeInfoQueryVO();
		examineeInfoQueryVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		examineeInfoQueryVO.setCx(kscl.getKscx());
		examineeInfoQueryVO.setKskm("2");
		WebServiceResult<SystemCheckInfo> checkItemsRoot = iManagerPlatService.getSystemTests(examineeInfoQueryVO);
		if(checkItemsRoot!=null&&checkItemsRoot.getBodyContent()!=null) {
			List<SystemCheckInfo> checkInfo = checkItemsRoot.getBodyContent().getContent();

			CheckresultVO checkresultVO = new CheckresultVO();
			checkresultVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
			checkresultVO.setKskm("2");
			checkresultVO.setCx(kscl.getKscx());
			
			CheckCar checkCar = new CheckCar();
			checkCar.setKchp(kscl.getKchp());
			checkCar.setCheckdate(HXCoreUtil.getNowDataStr(new Date(), "yyyy-MM-dd"));
			List<CheckItem> checkitemList = new ArrayList<CheckItem>();
			for(SystemCheckInfo checkContents : checkInfo) {
				CheckItem checkItem = new CheckItem();
				checkItem.setCheckname(checkContents.getCheckname());
				checkItem.setCheckkey(checkContents.getCheckkey());
				checkItem.setCheckresult("0");	//设置考车状态对应检测结果
				checkitemList.add(checkItem);
			}
			checkCar.setCheckitem(checkitemList);
			checkresultVO.setCheckcar(checkCar);
			WebServiceResult<CheckResponse> webResult = iManagerPlatService.writeCheckResult(checkresultVO);
			if(webResult.getHead()!=null && HXCoreUtil.isEquals(webResult.getHead().getCode(), "1")) {
				result = true;
			}
		}
		return result;
	}

}
