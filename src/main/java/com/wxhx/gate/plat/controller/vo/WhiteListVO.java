package com.wxhx.gate.plat.controller.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 人脸机白名单
 * @author coyi
 *
 */
public class WhiteListVO{
	@JSONField(name = "name")
	private String personnelName;	//姓名
	@JSONField(name = "Personnel_No")
	private String personnelNo;	//人员编号
	@JSONField(name = "idnum")
	private String personnelIDCard;	//身份证号
	@JSONField(name = "Personnel_Status")
	private int personnelStatus = 2;		//审核状态
	@JSONField(name = "nation")
	private String personnelNation;	//民族
	@JSONField(name = "Personnel_StartDate")
	private Date personnelStartDate;	//有效期起
	@JSONField(name = "Personnel_Type")
	private int personnelType = 1;	//人员类型
	@JSONField(name = "gender")
	private int personnelSex;	//性别
	@JSONField(name = "Personnel_EffDate")
	private Date personnelEffDate;	//有效期止
	@JSONField(name = "Personnel_Enable")
	private int personnelEnable;	//启用状态
	@JSONField(name = "photo")
	private String personnelPhoto;	//人员照片
	@JSONField(name = "Personnel_CardNo")
	private String personnelCardNo;	//卡号
	@JSONField(name = "Personnel_GroupName")
	private String personnelGroupName;	//分组名称
	@JSONField(name = "Personnel_Phone")
	private String personnelPhone;	//手机号码
	@JSONField(name = "address")
	private String personnelIDCardAdd;	//身份证地址
	@JSONField(name = "valid_end")
	private String personnelIDCardExp;	//身份证有效期止
	@JSONField(name = "valid_start")
	private String personnelIDCardEff;	//身份证有效期起
	@JSONField(name = "Personnel_IDCardIsSue")
	private String personnelIDCardIsSue;	//身份证签发机关
	@JSONField(name = "Personnel_UpdateTime")
	private Date personnelUpdateTime;	//最后修改时间
	@JSONField(name = "Personnel_AddTime")
	private Date personnelAddTime;	//创建时间
	
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getPersonnelNo() {
		return personnelNo;
	}
	public void setPersonnelNo(String personnelNo) {
		this.personnelNo = personnelNo;
	}
	public String getPersonnelIDCard() {
		return personnelIDCard;
	}
	public void setPersonnelIDCard(String personnelIDCard) {
		this.personnelIDCard = personnelIDCard;
	}
	public int getPersonnelStatus() {
		return personnelStatus;
	}
	public void setPersonnelStatus(int personnelStatus) {
		this.personnelStatus = personnelStatus;
	}
	public String getPersonnelNation() {
		return personnelNation;
	}
	public void setPersonnelNation(String personnelNation) {
		this.personnelNation = personnelNation;
	}
	public Date getPersonnelStartDate() {
		return personnelStartDate;
	}
	public void setPersonnelStartDate(Date personnelStartDate) {
		this.personnelStartDate = personnelStartDate;
	}
	public int getPersonnelType() {
		return personnelType;
	}
	public void setPersonnelType(int personnelType) {
		this.personnelType = personnelType;
	}
	public int getPersonnelSex() {
		return personnelSex;
	}
	public void setPersonnelSex(int personnelSex) {
		this.personnelSex = personnelSex;
	}
	public Date getPersonnelEffDate() {
		return personnelEffDate;
	}
	public void setPersonnelEffDate(Date personnelEffDate) {
		this.personnelEffDate = personnelEffDate;
	}
	public int getPersonnelEnable() {
		return personnelEnable;
	}
	public void setPersonnelEnable(int personnelEnable) {
		this.personnelEnable = personnelEnable;
	}
	public String getPersonnelPhoto() {
		return personnelPhoto;
	}
	public void setPersonnelPhoto(String personnelPhoto) {
		this.personnelPhoto = personnelPhoto;
	}
	public String getPersonnelCardNo() {
		return personnelCardNo;
	}
	public void setPersonnelCardNo(String personnelCardNo) {
		this.personnelCardNo = personnelCardNo;
	}
	public String getPersonnelGroupName() {
		return personnelGroupName;
	}
	public void setPersonnelGroupName(String personnelGroupName) {
		this.personnelGroupName = personnelGroupName;
	}
	public String getPersonnelPhone() {
		return personnelPhone;
	}
	public void setPersonnelPhone(String personnelPhone) {
		this.personnelPhone = personnelPhone;
	}
	public String getPersonnelIDCardAdd() {
		return personnelIDCardAdd;
	}
	public void setPersonnelIDCardAdd(String personnelIDCardAdd) {
		this.personnelIDCardAdd = personnelIDCardAdd;
	}
	public String getPersonnelIDCardExp() {
		return personnelIDCardExp;
	}
	public void setPersonnelIDCardExp(String personnelIDCardExp) {
		this.personnelIDCardExp = personnelIDCardExp;
	}
	public String getPersonnelIDCardEff() {
		return personnelIDCardEff;
	}
	public void setPersonnelIDCardEff(String personnelIDCardEff) {
		this.personnelIDCardEff = personnelIDCardEff;
	}
	public String getPersonnelIDCardIsSue() {
		return personnelIDCardIsSue;
	}
	public void setPersonnelIDCardIsSue(String personnelIDCardIsSue) {
		this.personnelIDCardIsSue = personnelIDCardIsSue;
	}
	public Date getPersonnelUpdateTime() {
		return personnelUpdateTime;
	}
	public void setPersonnelUpdateTime(Date personnelUpdateTime) {
		this.personnelUpdateTime = personnelUpdateTime;
	}
	public Date getPersonnelAddTime() {
		return personnelAddTime;
	}
	public void setPersonnelAddTime(Date personnelAddTime) {
		this.personnelAddTime = personnelAddTime;
	}
	
}
