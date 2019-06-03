package com.wxhx.gate.plat.util;
/**
 * 人脸平台相关工具类
 * @author geliang
 *
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wxhx.basic_client.common.HXCoreUtil;

@Component
public class PersonFacePlatUtil {
	
	@Value("${wxhx.person.face.AppSecret:2bfcf060140643b0bee193b547ee83b0}")
	private String appSecret;
	
	/**
	 * 计算签名算法
	 * @param <T>
	 * @param t
	 * @return
	 */
	public <T> String getSign(T t) {
		//将对象转换成map
		Map<String, Object> map = JSONObject.parseObject(HXCoreUtil.getJsonString(t), Map.class);
		List<String> keys = new ArrayList<String>(map.keySet());
		//排序
		Collections.sort(keys);
		//创建加密初始值
		StringBuffer stringA = new StringBuffer();
		for(String key:keys) {
			stringA.append(key+"="+map.get(keys)+"&");
		}
		stringA.append(appSecret);
		String result = HXCoreUtil.md5(stringA.toString());
		return result.toUpperCase();
	}
}
