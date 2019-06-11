package com.wxhx.gate.plat.controller.vo;

import java.util.List;

public class CheckCar {

	private String kchp;
	
	private String checkdate;
	
	private List<CheckItem> checkitem;

	public String getKchp() {
		return kchp;
	}

	public void setKchp(String kchp) {
		this.kchp = kchp;
	}

	public String getCheckdate() {
		return checkdate;
	}

	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}

	public List<CheckItem> getCheckitem() {
		return checkitem;
	}

	public void setCheckitem(List<CheckItem> checkitem) {
		this.checkitem = checkitem;
	}

	
	
}
