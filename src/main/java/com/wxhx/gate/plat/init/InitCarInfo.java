package com.wxhx.gate.plat.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	 * 	随机获取可用车辆
	 * @return
	 */
	public static CarUsedInfo getCanUseCar() {
		Random random = new Random();
		int i = random.nextInt(CANUSECARS.size());
		return CANUSECARS.get(i);
	}

	/**
	 * 尝试获取当前可用车辆
	 * @param tryTime 尝试次数
	 * @return
	 */
	public static CarUsedInfo getTryCanUseCar(List<String> carIds) {
		List<CarUsedInfo> cars = new ArrayList<CarUsedInfo>();
		for(CarUsedInfo car:CANUSECARS) {
			if(!carIds.contains(car.getKchp())) {
				cars.add(car);
			}
		}
		Random random = new Random();
		int i = random.nextInt(cars.size());
		return cars.get(i);
	}
}
