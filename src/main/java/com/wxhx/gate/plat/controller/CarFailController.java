package com.wxhx.gate.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;
import com.wxhx.basic_client.config.thread.HXThreadManager;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.CheckResponse;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.SystemCheckInfo;
import com.wxhx.gate.plat.constent.CommonTestConstent;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.CheckCar;
import com.wxhx.gate.plat.controller.vo.CheckItem;
import com.wxhx.gate.plat.controller.vo.CheckresultVO;
import com.wxhx.gate.plat.controller.vo.FaceInfoDelVo;
import com.wxhx.gate.plat.dao.entity.Kscl;
import com.wxhx.gate.plat.init.WhiteListInit;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.service.out.IManagerPlatService;

@RestController
@RequestMapping("/carFail")
public class CarFailController {
	@Autowired
	private IManagerPlatService iManagerPlatService;
	
	@Autowired
	private HXThreadManager hxThreadManager;
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	/**
	 * 暂停车辆
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public HXRespons<String> carFail(@RequestBody Kscl kscl){
		HXRespons<String> result = new HXRespons<String>();
		
		CheckresultVO checkresultVO = new CheckresultVO();
		checkresultVO.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		checkresultVO.setKskm("2");
		checkresultVO.setCx(kscl.getKscx());
		
		CheckCar checkCar = new CheckCar();
		checkCar.setKchp(kscl.getKchp());
		checkCar.setCheckdate(HXCoreUtil.getNowDataStr(new Date(), "yyyy-MM-dd"));
		
		List<CheckItem> checkitemList = new ArrayList<CheckItem>();
		CheckItem checkItem = new CheckItem();
		checkItem.setCheckname("安全带");
		checkItem.setCheckkey("AQD");
		checkItem.setCheckresult(kscl.getKczt()+"");	//设置考车状态对应检测结果
		
		checkitemList.add(checkItem);
		checkCar.setCheckitem(checkitemList);
		checkresultVO.setCheckcar(checkCar);
		
		WebServiceResult<CheckResponse> carFailResult = iManagerPlatService.writeCheckResult(checkresultVO);
		if("1".equals(carFailResult.getHead().getCode())) {
			result.setResCode("1");
		}
		else {
			result.setResCode("0");
		}
		
		
		return result;
		
	}
}
