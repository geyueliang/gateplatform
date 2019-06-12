package com.wxhx.gate.plat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.CheckCar;
import com.wxhx.gate.plat.controller.vo.CheckItem;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.ExamineeInfoQueryVO;
import com.wxhx.gate.plat.dao.entity.Kscl;
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
	public HXRespons<CheckResponse> startCheck(List<Kscl> kscls) {
		HXRespons<CheckResponse> finalResult = new HXRespons<CheckResponse>("ERROR", "操作失败", null);
		//循环检测每辆车	
		for(Kscl kscl : kscls) {
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
						checkItem.setCheckresult("1");
						checkitemList.add(checkItem);
					}
					checkCar.setCheckitem(checkitemList);
					checkresultVO.setCheckcar(checkCar);
					WebServiceResult<CheckResponse> webResult = iManagerPlatService.writeCheckResult(checkresultVO);
					if(webResult.getHead()!=null && HXCoreUtil.isEquals(webResult.getHead().getCode(), "1")) {
						finalResult = new HXRespons<CheckResponse>("SUCCESS", kscl.getKcbh()+":写入检测成功", null);
						//操作成功后设置缓存
						EvnVarConstentInfo.addCar(kscl);
						HXLogUtil.debug(HXLogerFactory.getLogger("gate_plate"),checkCar.getKchp()+":"+finalResult.getResMsg());
					}else {
						finalResult = new HXRespons<CheckResponse>("ERROR", kscl.getKcbh()+":写入检测失败", null);
						HXLogUtil.debug(HXLogerFactory.getLogger("gate_plate"),checkCar.getKchp()+":"+finalResult.getResMsg());
					}
				}
				else {
					continue;
				}

			}
		return finalResult;
	}

}
