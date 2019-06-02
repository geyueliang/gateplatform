package com.wxhx.gate.plat.controller.vo;

import java.util.Date;

/**
 * 人脸机白名单
 * @author coyi
 *
 */
public class WhiteListVO {
	private String appid;	//开发者应用ID
	private String timestemp;	//时间戳
	private String sign;	//签名字符串
	private String data;	//人员参数
	private String PersonnelName;	//姓名
	private String PersonnelNo;	//人员编号
	private String PersonnelIDCard;	//身份证号
	private int PersonnelStatus = 2;		//审核状态
	private String PersonnelNation;	//民族
	private Date PersonnelStartDate;	//有效期起
	private int PersonnelType = 1;	//人员类型
	private int PersonnelSex;	//性别
	private Date PersonnelEffDate;	//有效期止
	private int PersonnelEnable;	//启用状态
	private byte[] PersonnelPhoto;	//人员照片
	private String PersonnelCardNo;	//卡号
	private String PersonnelGroupName;	//分组名称
	private String PersonnelPhone;	//手机号码
	private String PersonnelIDCardAdd;	//身份证地址
	private Date PersonnelIDCardExp;	//身份证有效期止
	private Date PersonnelIDCardEff;	//身份证有效期起
	private String PersonnelIDCardIsSue;	//身份证签发机关
	private Date PersonnelUpdateTime;	//最后修改时间
	private Date PersonnelAddTime;	//创建时间
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getTimestemp() {
		return timestemp;
	}
	public void setTimestemp(String timestemp) {
		this.timestemp = timestemp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPersonnelName() {
		return PersonnelName;
	}
	public void setPersonnelName(String personnelName) {
		PersonnelName = personnelName;
	}
	public String getPersonnelNo() {
		return PersonnelNo;
	}
	public void setPersonnelNo(String personnelNo) {
		PersonnelNo = personnelNo;
	}
	public String getPersonnelIDCard() {
		return PersonnelIDCard;
	}
	public void setPersonnelIDCard(String personnelIDCard) {
		PersonnelIDCard = personnelIDCard;
	}
	public int getPersonnelStatus() {
		return PersonnelStatus;
	}
	public void setPersonnelStatus(int personnelStatus) {
		PersonnelStatus = personnelStatus;
	}
	public String getPersonnelNation() {
		return PersonnelNation;
	}
	public void setPersonnelNation(String personnelNation) {
		PersonnelNation = personnelNation;
	}
	public Date getPersonnelStartDate() {
		return PersonnelStartDate;
	}
	public void setPersonnelStartDate(Date personnelStartDate) {
		PersonnelStartDate = personnelStartDate;
	}
	public int getPersonnelType() {
		return PersonnelType;
	}
	public void setPersonnelType(int personnelType) {
		PersonnelType = personnelType;
	}
	public int getPersonnelSex() {
		return PersonnelSex;
	}
	public void setPersonnelSex(int personnelSex) {
		PersonnelSex = personnelSex;
	}
	public Date getPersonnelEffDate() {
		return PersonnelEffDate;
	}
	public void setPersonnelEffDate(Date personnelEffDate) {
		PersonnelEffDate = personnelEffDate;
	}
	public int getPersonnelEnable() {
		return PersonnelEnable;
	}
	public void setPersonnelEnable(int personnelEnable) {
		PersonnelEnable = personnelEnable;
	}
	public byte[] getPersonnelPhoto() {
		return PersonnelPhoto;
	}
	public void setPersonnelPhoto(byte[] personnelPhoto) {
		PersonnelPhoto = personnelPhoto;
	}
	public String getPersonnelCardNo() {
		return PersonnelCardNo;
	}
	public void setPersonnelCardNo(String personnelCardNo) {
		PersonnelCardNo = personnelCardNo;
	}
	public String getPersonnelGroupName() {
		return PersonnelGroupName;
	}
	public void setPersonnelGroupName(String personnelGroupName) {
		PersonnelGroupName = personnelGroupName;
	}
	public String getPersonnelPhone() {
		return PersonnelPhone;
	}
	public void setPersonnelPhone(String personnelPhone) {
		PersonnelPhone = personnelPhone;
	}
	public String getPersonnelIDCardAdd() {
		return PersonnelIDCardAdd;
	}
	public void setPersonnelIDCardAdd(String personnelIDCardAdd) {
		PersonnelIDCardAdd = personnelIDCardAdd;
	}
	public Date getPersonnelIDCardExp() {
		return PersonnelIDCardExp;
	}
	public void setPersonnelIDCardExp(Date personnelIDCardExp) {
		PersonnelIDCardExp = personnelIDCardExp;
	}
	public Date getPersonnelIDCardEff() {
		return PersonnelIDCardEff;
	}
	public void setPersonnelIDCardEff(Date personnelIDCardEff) {
		PersonnelIDCardEff = personnelIDCardEff;
	}
	public String getPersonnelIDCardIsSue() {
		return PersonnelIDCardIsSue;
	}
	public void setPersonnelIDCardIsSue(String personnelIDCardIsSue) {
		PersonnelIDCardIsSue = personnelIDCardIsSue;
	}
	public Date getPersonnelUpdateTime() {
		return PersonnelUpdateTime;
	}
	public void setPersonnelUpdateTime(Date personnelUpdateTime) {
		PersonnelUpdateTime = personnelUpdateTime;
	}
	public Date getPersonnelAddTime() {
		return PersonnelAddTime;
	}
	public void setPersonnelAddTime(Date personnelAddTime) {
		PersonnelAddTime = personnelAddTime;
	}
	
	
}
