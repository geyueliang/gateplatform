package com.wxhx.gate.plat.controller.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 人脸机白名单
 * @author coyi
 *
 */
public class WhiteListVO{
	
	private String  name;
	
	private String gender;
	
	private String nation;
	
	private String idnum;
	
	private String address;
	
	@JSONField(name = "valid_start")
	private String validStart;
	
	@JSONField(name = "valid_end")
	private String validEnd;
	
	private String company;
	
	private String depart;
	
	private String phone;
	
	private String photo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getValidStart() {
		return validStart;
	}

	public void setValidStart(String validStart) {
		this.validStart = validStart;
	}

	public String getValidEnd() {
		return validEnd;
	}

	public void setValidEnd(String validEnd) {
		this.validEnd = validEnd;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
