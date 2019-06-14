package com.wxhx.gate.plat.init;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.dao.KcsbMapper;
import com.wxhx.gate.plat.dao.LxpzMapper;
import com.wxhx.gate.plat.dao.entity.Kcsb;
import com.wxhx.gate.plat.dao.entity.Lxpz;

/**
 * 	考车设备缓存初始化
 * @author geliang
 *
 */
@Component
public class KcsbInit {

	@Autowired
	private KcsbMapper kcsbMapper;
	
	@Autowired
	private LxpzMapper lxpzMapper;
	
	//根据设备序号 缓存设备信息
	private static Map<String, Kcsb> KcsbMap = new HashMap<String, Kcsb>();
	
	//项目编号和设备编号对应map
	private static Map<String, String> itemMap = new HashMap<String, String>();
	
	//考试项目路线序号
	private static Map<String, List<String>> ksxmlx = new HashMap<String, List<String>>();

	
	@PostConstruct
	public void initKcsb() {
		//获取所有的考场设备信息
		List<Kcsb> kcsbs = kcsbMapper.selectAll();
		if(kcsbs!=null&&kcsbs.size()>0) {
			for(Kcsb kcsb:kcsbs) {
				KcsbMap.put(kcsb.getSbxh(), kcsb);
				itemMap.put(kcsb.getSbxm(), kcsb.getSbbh());
			}
		}
		
		//路线配置信息
		List<Lxpz> lxpzs = lxpzMapper.selectAll();
		if(lxpzs!=null&& lxpzs.size()>0) {
			for(Lxpz lxpz:lxpzs) {
				ksxmlx.put(lxpz.getLxxh(), Arrays.asList(lxpz.getSbxms().split(",")));
			}
		}
		
		
	}
	
	/**
	 * 根据车上的设备序号 获取设备信息
	 * @param sbxh
	 * @return
	 */
	public static Kcsb getKcsb(String sbxh) {
		return KcsbMap.get(sbxh);
	}
	
	/**
	 * 根据当前的考试项目获取项目设备对应的备案编号
	 * @param sbxm
	 * @return
	 */
	public static String getSBBH(String sbxm) {
		return itemMap.get(sbxm);
	}
	
	/**
	 * 根据当前路线序号和结束项目 获取下一个开始项目
	 * @param lxxh
	 * @param endKsxm
	 * @return
	 */
	public static String getNextBeginItem(String lxxh,String endKsxm) {
		String result = "";
		List<String> sbxms = ksxmlx.get(lxxh);
		if(sbxms!=null) {
			if(endKsxm==null) {
				result = sbxms.get(0);
				return result;
			}
			int index = sbxms.indexOf(endKsxm);
			if((index+1)<sbxms.size()) {
				result = sbxms.get(index+1);
			}
		}
		return result;
	}
}
