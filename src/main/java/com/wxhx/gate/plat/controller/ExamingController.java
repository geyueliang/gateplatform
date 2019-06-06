package com.wxhx.gate.plat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.config.thread.HXThreadManager;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.bean.out.RegisterResponse;
import com.wxhx.gate.plat.init.WhiteListInit;
import com.wxhx.gate.plat.service.IExamStartService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;

/**
 * 开始考试
 * @author coyi
 *
 */
@RestController
@RequestMapping("/examStart")
public class ExamingController {
	
	@Autowired
	private IExamStartService iExamStartService;
	
	@Autowired
	private HXThreadManager hxThreadManager;
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	/**
	 * 开始考试
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String examStart(@RequestBody RecordInfo recordInfo){
		Map<String, Object> res = new HashMap<String, Object>();
		if(WhiteListInit.WHITE_LIST.contains(recordInfo.getIdNum())) {
			res.put("code", 1);
			res.put("msg", "管理员开门");
			hxThreadManager.execThread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2*1000);
						iDongwoPlatService.openGate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			return HXCoreUtil.getJsonString(res);
		}
//		HXCoreUtil.createImageFromBase64(recordInfo.getScenePhoto(), "D://11.jpg");
		HXRespons<FaceResponse> result= iExamStartService.examing(recordInfo);
		if(HXCoreUtil.isEquals("SUCCESS", result.getResCode())) {
			res.put("code", 0);
			res.put("msg", "success");
			hxThreadManager.execThread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(2*1000);
						iDongwoPlatService.openGate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		else {
			res.put("code",1);
			res.put("msg", "error");
		}
		return HXCoreUtil.getJsonString(res);
		
	}
}
