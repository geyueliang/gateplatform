package com.wxhx.gate.plat.service.out;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXHttpClient;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.vo.FaceInfoDelVo;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;

/**
 * 
 * @author coyi
 *
 */
@Service
public class DongwoPlatServiceImpl implements IDongwoPlatService{

	public FaceResponse insertWhiteList(WhiteListVO whiteListVO) {
		String addUrl = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.FACEMACHE_URL)+"addCard";
		JSONObject jsonObject = HXHttpClient.httpPost(addUrl, getJsonParam(whiteListVO));
		return HXCoreUtil.jsonToObj(jsonObject.toJSONString(), FaceResponse.class);
	}

	public FaceResponse deleteWhiteList(FaceInfoDelVo faceInfoDelVo) {
		String delUrl = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.FACEMACHE_URL)+"deleteCard";
		JSONObject jsonObject = HXHttpClient.httpPost(delUrl,getJsonParam(faceInfoDelVo));
		return HXCoreUtil.jsonToObj(jsonObject.toJSONString(), FaceResponse.class);
	}

	public FaceResponse openGate() {
		String openUrl = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.FACEMACHE_URL)+"openDoor";
		JSONObject jsonObject = HXHttpClient.httpPost(openUrl,new JSONObject());
		return HXCoreUtil.jsonToObj(jsonObject.toJSONString(), FaceResponse.class);
	}

	public FaceResponse updateUploadUrl(String iploadUrl) {
		String openUrl = EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.FACEMACHE_URL)+"setUploadUrl";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url", iploadUrl);
		JSONObject res = HXHttpClient.httpPost(openUrl,jsonObject);
		return HXCoreUtil.jsonToObj(res.toJSONString(), FaceResponse.class);
	}
	
	
	private <T> JSONObject getJsonParam(T t) {
		String jsonT = HXCoreUtil.getJsonString(t);
		Map<String, Object> obj = HXCoreUtil.jsonToObj(jsonT, Map.class);
		return new JSONObject(obj);
	}
	
}
