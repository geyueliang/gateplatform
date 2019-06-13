package com.wxhx.gate.plat.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;
import com.wxhx.basic_client.config.thread.HXThreadManager;
import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.constent.CommonTestConstent;
import com.wxhx.gate.plat.controller.vo.FaceInfoDelVo;
import com.wxhx.gate.plat.init.WhiteListInit;
import com.wxhx.gate.plat.service.IExamStartService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;

/**
 * 开始考试
 * @author coyi
 *
 */
@RestController
public class ExamingController {
	
	@Autowired
	private IExamStartService iExamStartService;
	
	@Autowired
	private HXThreadManager hxThreadManager;
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Value("${wxhx.gate.test:false}")
	private boolean isTest;
	
	/**
	 * 开始考试
	 * @return
	 */
	@RequestMapping(value = "/examStart", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public String examStart(@RequestBody RecordInfo recordInfo){
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"人脸机上传信息成功{0}",recordInfo);

		Map<String, Object> res = new HashMap<String, Object>();
		/**
		 * 测试
		 */
		if(true) {
			res.put("code", 0);
			res.put("msg", "success");
			return HXCoreUtil.getJsonString(res);
		}
		final String idNum = recordInfo.getIdNum();
//		HXCoreUtil.createImageFromBase64(recordInfo.getScenePhoto(), "D://11.jpg");
		//如果是测试环境根据将身份证编号换成测试编号
		if(isTest) {
			if(!HXCoreUtil.isEmpty(CommonTestConstent.replaceMap.get(recordInfo.getIdNum())+"")){
				recordInfo.setIdNum(CommonTestConstent.replaceMap.get(recordInfo.getIdNum())+"");
			} 
		}
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
		
		HXRespons<FaceResponse> result= iExamStartService.examing(recordInfo);
		
		if(HXCoreUtil.isEquals("SUCCESS", result.getResCode())) {
			res.put("code", 0);
			res.put("msg", "success");
			hxThreadManager.execThread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1*1000);
						iDongwoPlatService.openGate();
						
						//删除白名单信息
						List<String> idNumList = new ArrayList<String>();
						idNumList.add(idNum);
						FaceInfoDelVo faceInfoDelVo = new FaceInfoDelVo();
						faceInfoDelVo.setIdnum(idNumList);
						iDongwoPlatService.deleteWhiteList(faceInfoDelVo);
					} catch (InterruptedException e) {
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
	
	/**
	 * 手动开始考试
	 * @param file
	 * @param userWhiteList
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "/examStartByHand", method= RequestMethod.POST,produces="application/json;charset=UTF-8")
	public HXRespons<String> examStartByHand(@RequestParam("imageFile") MultipartFile file, RecordInfo recordInfo) throws Exception{
		HXRespons<String> result = new HXRespons<String>();
		Base64 base64 = new Base64();
		byte[] data = new byte[file.getInputStream().available()];
		file.getInputStream().read(data);
		String imageBase64 =  new String(base64.encode(data));
		recordInfo.setScenePhoto(imageBase64);
		
		final String idNum = recordInfo.getIdNum();
		
		recordInfo.setTime(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd HH:mm:ss"));
		recordInfo.setValidStart(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd"));
		recordInfo.setValidEnd(HXCoreUtil.getNowDataStr(new Date(), "yyyy.MM.dd"));
		recordInfo.setCardResultStatus(0);
		recordInfo.setFaceResultStatus(0);
		
		HXRespons<FaceResponse> faceResponse= iExamStartService.examing(recordInfo);
		
		if(HXCoreUtil.isEquals("SUCCESS", faceResponse.getResCode())) {
			result.setResCode("1");
			hxThreadManager.execThread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1*1000);
						iDongwoPlatService.openGate();
						
						//删除白名单信息
						List<String> idNumList = new ArrayList<String>();
						idNumList.add(idNum);
						FaceInfoDelVo faceInfoDelVo = new FaceInfoDelVo();
						faceInfoDelVo.setIdnum(idNumList);
						iDongwoPlatService.deleteWhiteList(faceInfoDelVo);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		else {
			result.setResCode("0");
		}
		return result;
	}
}
