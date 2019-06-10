package com.wxhx.gate.plat.init;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.bean.exam.process.CarUsedInfo;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.entity.Kscl;

/**
 * 初始化车辆信息 排考使用
 * @author geliang
 *
 */
@Component
public class InitCarInfo {

	@Autowired
	private KsclMapper ksclMapper;
	
	public static List<CarUsedInfo> CANUSECARS = new ArrayList<CarUsedInfo>();
	
	@PostConstruct
	public void init() {
		Kscl kscl = new Kscl();
		kscl.setKczt(1);
		List<Kscl> canUseCar = ksclMapper.select(kscl);
		for(Kscl ks:canUseCar) {
			CarUsedInfo carUsedInfo = new CarUsedInfo();
			carUsedInfo.setKcbh(ks.getKcbh());
			carUsedInfo.setKslx(ks.getLxxh());
			carUsedInfo.setTimes(0);
			carUsedInfo.setKchp(ks.getKchp());
			CANUSECARS.add(carUsedInfo);
		}
	}
	
	/**
	 * 获取需要尝试的次数
	 * @return
	 */
	public static int tryTimes() {
		return CANUSECARS.size();
	}
	
	/**
	 * 获取当前可用车辆
	 * @return
	 */
	public static CarUsedInfo getCanUseCar() {
		CANUSECARS.sort(new Comparator<CarUsedInfo>() {
			public int compare(CarUsedInfo o1, CarUsedInfo o2) {
				return o1.getTimes()-o2.getTimes();
			}
			
		});
		CarUsedInfo canCarUsedInfo = CANUSECARS.get(0);
//		canCarUsedInfo.setTimes(canCarUsedInfo.getTimes()+1);
		return canCarUsedInfo;
	}
	
	
	public static boolean updateCarTime(String carId) {
		boolean result = false;
		for(CarUsedInfo carUsedInfo:CANUSECARS) {
			if(HXCoreUtil.isEquals(carId, carUsedInfo.getKchp())) {
				carUsedInfo.setTimes(carUsedInfo.getTimes()+1);
				result = true;
			}
		}
		return result;
	}
	
	
	/**
	 * 尝试获取当前可用车辆
	 * @param tryTime 尝试次数
	 * @return
	 */
	public static CarUsedInfo getTryCanUseCar(int tryTime) {
		if(tryTime<CANUSECARS.size()) {
			return CANUSECARS.get(tryTime-1);
		}
		return null;
	}
}
