package com.wxhx.gate.plat.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.EnvVarMapper;
import com.wxhx.gate.plat.dao.entity.EnvVar;

/**
 * 系統初始化工作
 * @author geliang
 *
 */
@Component
public class HXSystemInfo {

	@Autowired
	private EnvVarMapper envVarMapper;
	
	/**
	 * 系統初始化相关工作
	 */
	@PostConstruct
	private void systemInit() {
		//查询所有的环境信息
		List<EnvVar> envVars = envVarMapper.selectAll();
		//将数据库信息放入缓存中
		Map<String, String> cacheMap = new HashMap<String, String>();
		if(envVars!=null&&envVars.size()>0) {
			for(EnvVar envVar:envVars) {
				cacheMap.put(envVar.getEnvName(), envVar.getEnvValue());
			}
		}
		EvnVarConstentInfo.setSystemInfoMap(cacheMap);
	}
	
}
