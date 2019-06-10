package com.wxhx.gate.plat.bean.exam.process;

/**
 * 当前考车使用信息
 * @author geliang
 *
 */
public class CarUsedInfo {
	
	//车牌号码
	private String kchp;

	//当前使用次数
	private int times;
	
	//考车编号
	private String kcbh;
	
	
	//考试路线
	private String kslx;


	public int getTimes() {
		return times;
	}


	public void setTimes(int times) {
		this.times = times;
	}


	public String getKcbh() {
		return kcbh;
	}


	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}


	public String getKslx() {
		return kslx;
	}


	public void setKslx(String kslx) {
		this.kslx = kslx;
	}


	public String getKchp() {
		return kchp;
	}


	public void setKchp(String kchp) {
		this.kchp = kchp;
	}
	
	
	
}
