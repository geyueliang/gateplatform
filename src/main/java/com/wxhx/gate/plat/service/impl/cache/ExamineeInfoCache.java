package com.wxhx.gate.plat.service.impl.cache;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 考生当前信息缓存
 * @author geliang
 *
 */
public class ExamineeInfoCache {

	//身份证号
	private String sfzmhm;
	
	//当前考试次数
	private int nowKscs;
	
	//当前考试项目
	private String nowKsxm;
	
	//当前扣分
	private int nowMarks;
	
	//当前照片
	private String nowZp;

	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public int getNowKscs() {
		return nowKscs;
	}

	public void setNowKscs(int nowKscs) {
		this.nowKscs = nowKscs;
	}

	public String getNowKsxm() {
		return nowKsxm;
	}

	public void setNowKsxm(String nowKsxm) {
		this.nowKsxm = nowKsxm;
	}

	public int getNowMarks() {
		return nowMarks;
	}

	public void setNowMarks(int nowMarks) {
		this.nowMarks = nowMarks;
	}

	public String getNowZp() {
		return nowZp;
	}

	public void setNowZp(String nowZp) {
		this.nowZp = nowZp;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
	
}
