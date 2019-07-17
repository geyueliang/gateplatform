package com.wxhx.gate.plat.service.impl.cache;
/**
 * 考试信息缓存管理类
 * @author geliang
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;

@Component
public class ExamineeInfoCacheManager {

	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	private Map<String, ExamineeInfoCache> cachePoool = new HashMap<String, ExamineeInfoCache>();
	
	private static Logger logger = LoggerFactory.getLogger("examLog");

	
	/**
	 * 科目开始时候 初始化操作
	 * @param sfzmhm
	 * @return
	 */
	public boolean initExamCache(String sfzmhm) {
		//先清除缓存
		cachePoool.remove(sfzmhm);
		//开始科目，设置当前人员的缓存信息
		ExamineeInfoCache examineCache = new ExamineeInfoCache();
		examineCache.setSfzmhm(sfzmhm);
		examineCache.setNowKsxm("10000");
		examineCache.setNowKscs(1);
		//获取本次考试次数
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(sfzmhm);
		if(ksyyxx!=null) {
			if(Integer.parseInt(ksyyxx.getBcyykscs())>1) {
				examineCache.setNowKscs(2);
			}
		}
		cachePoool.put(sfzmhm, examineCache);
		HXLogUtil.info(logger, "操作缓存初始化，当前缓存信息{0}", examineCache);
		return true;
	}
	
	/**
	 * 清除缓存信息
	 * @param sfzmhm
	 */
	public void removeCache(String sfzmhm) {
		cachePoool.remove(sfzmhm);
	}
	
	
	/**
	 * 扣分时候 修改当前扣分
	 * @param sfzmhm
	 * @param bckf
	 * @return
	 */
	public boolean updateKf(String sfzmhm,int bckf) {
		boolean result = true;
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache==null) {
			result = false;
		}
		else {
			examineCache.setNowKscs(examineCache.getNowKscs()+bckf);
		}
		return result;
	}
	
	/**
	 * 得到当前扣分
	 * @param sfzmhm
	 * @return
	 */
	public int getNowKf(String sfzmhm) {
		int nowKscf = 0;
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache!=null) {
			nowKscf = examineCache.getNowKscs();
		}
		return nowKscf;
	}
	
	/**
	 * 返回当前考试项目
	 * @param sfzmhm
	 * @return
	 */
	public String getKsxm(String sfzmhm) {
		String nowKsxm = "";
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache!=null) {
			nowKsxm = examineCache.getNowKsxm();
		}
		return nowKsxm;
	}
	
	
	/**
	 * 设置考试项目
	 * @param sfzmhm
	 * @param ksxm
	 * @return
	 */
	public boolean setKsxm(String sfzmhm,String ksxm) {
		boolean result = false;
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache!=null) {
			examineCache.setNowKsxm(ksxm);
			result = true;
		}
		return result;
	}
	
	/**
	 * 返回当前照片
	 * @param sfzmhm
	 * @return
	 */
	public String getNowZp(String sfzmhm) {
		String result = "";
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache!=null) {
			result = examineCache.getNowZp();
		}
		return result;
	}
	
	/**
	 * 设置当前照片
	 * @param sfzmhm
	 * @return
	 */
	public boolean setNowZp(String sfzmhm,String zp) {
		boolean res = false;
		ExamineeInfoCache examineCache = cachePoool.get(sfzmhm);
		if(examineCache!=null) {
			examineCache.setNowZp(zp);
			res = true;
		}
		return res;
	}
}