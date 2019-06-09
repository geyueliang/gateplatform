package com.wxhx.gate.plat.constent;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 测试身份证替换信息
 * @author geliang
 *
 */
@Component
public class CommonTestConstent {
	
	@Value("${wxhx.gate.plat.realCarIds}")
	private String realCarIds;
	
	@Value("${wxhx.gate.plat.replaceCarIds}")
	private String replaceCarIds;
	

	public static Map<String, Object> replaceMap = new HashMap<String, Object>();
	
	
	@PostConstruct
	public void init() {
		if(!HXCoreUtil.isEmpty(realCarIds)&&!HXCoreUtil.isEmpty(replaceCarIds)) {
			String[] realArray = replaceCarIds.split(",");
			String[] replaceArray = replaceCarIds.split(",");
			if(realArray.length==replaceArray.length) {
				for(int i = 0;i<realArray.length;i++) {
					replaceMap.put(realArray[i],replaceArray[i]);
				}
			}
			
		}
	}
}
