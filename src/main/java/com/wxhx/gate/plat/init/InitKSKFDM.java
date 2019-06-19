package com.wxhx.gate.plat.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.dao.KskfdmMapper;
import com.wxhx.gate.plat.dao.entity.Kskfdm;

/**
 * 初始化考试扣分代码
 * @author geliang
 *
 */
@Component
public class InitKSKFDM {

	public static Map<String, Integer> KSKFMAPPING = new HashMap<String, Integer>();
	
	@Autowired
	private KskfdmMapper kskfdmMapper;
	
	@PostConstruct
	public void initKfDM() {
		List<Kskfdm> kskfdms = kskfdmMapper.selectAll();
		if(kskfdms!=null && kskfdms.size()>0) {
			for(Kskfdm dm:kskfdms) {
				InitKSKFDM.KSKFMAPPING.put(dm.getDm(), dm.getKf());
			}
		}
	}
	
	/**
	 * 获取指定的扣分代码对应的扣分值
	 * @param ksdm
	 * @return
	 */
	public static int getKf(String ksdm) {
		return InitKSKFDM.KSKFMAPPING.get(ksdm);
	}
	
}
