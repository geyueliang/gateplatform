package com.wxhx.gate.plat.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.service.out.IDongwoPlatService;

/**
 * 初始化人脸机器相关信息
 * @author geliang
 *
 */
@Component
public class InitPersonFaceMachine {
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Value("${wxhx.person.face.deviceno:5fc7540d5efdf555}")
	private String deviceno;
	
	/**
	 * 初始化人脸机相关信息
	 */
	@PostConstruct
	public void init() {
		//查询设备信息
		
	}
	
}
