package com.wxhx.gate.plat.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.controller.ExamingController;
import com.wxhx.gate.plat.dao.EnvVarMapper;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.entity.EnvVar;
import com.wxhx.gate.plat.dao.entity.Kscl;
import com.wxhx.gate.plat.service.ISystemCheckService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;

/**
 * 系統初始化工作
 * 
 * @author geliang
 *
 */
@Component
public class HXSystemInfo {
	
	private static Logger logger = LoggerFactory.getLogger(HXSystemInfo.class);

	
	@Value("${wxhx.gate.plate.model.dev:n}")
	private String devModel;

	@Autowired
	private EnvVarMapper envVarMapper;

	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Autowired
	private ISystemCheckService iSystemCheckServier;
	

	@Autowired
	private KsclMapper ksclMapper;
	
	/**
	 * 系統初始化相关工作
	 */
	@PostConstruct
	private void systemInit() {
		// 查询所有的环境信息
		List<EnvVar> envVars = envVarMapper.selectAll();
		// 将数据库信息放入缓存中
		Map<String, String> cacheMap = new HashMap<String, String>();
		String localUrl = "";
		if (envVars != null && envVars.size() > 0) {
			for (EnvVar envVar : envVars) {
				cacheMap.put(envVar.getEnvName(), envVar.getEnvValue());
				// 设定人脸机返回信息地址
				if (HXCoreUtil.isEquals(envVar.getEnvName(), "localUrl")) {
					localUrl = envVar.getEnvValue();
				}
			}
		}
		EvnVarConstentInfo.setSystemInfoMap(cacheMap);
		//开发模式下 不进行车辆注册
		if(!HXCoreUtil.isEquals("y", devModel)) {
			if (!HXCoreUtil.isEmpty(localUrl)) {
				iDongwoPlatService.updateUploadUrl(localUrl);
			}
			HXLogUtil.debug(logger,"开始车辆检测");
			List<Kscl> kscls = ksclMapper.selectAll();
			//iSystemCheckServier.startCheck(kscls);
			HXLogUtil.debug(logger,"车辆检测结束");
		}
	}
}
