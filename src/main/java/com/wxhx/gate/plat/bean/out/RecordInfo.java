package com.wxhx.gate.plat.bean.out;

/**
 *人脸机识别记录
 * @author coyi
 *
 */
public class RecordInfo {
	private String id;	//UuidID
	private String time;	//比对时间
	private String name;	//姓名
	private String sex;	//性别
	private String nation;	//民族
	private String idNum;	//身份证号码
	private String birthday;	//生日
	private String address;	//地址
	private String depart;	//签发机关
	private String validStart;	//有效期开始
	private String validEnd;	//有效期结束
	private String mac;	//设备MAC地址
	private int inout;	//进出类型
	private int openType;	//开门方式
	private int cardResultStatus;	//认证识别结果（0失败，1成功）
	private int faceResultStatus;	//白名单识别结果
	private String scenePhoto;	//现场抓拍图片 （Base64格式）
	private String userPhoto;	//白名单图片（Base64格式）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public int getInout() {
		return inout;
	}
	public void setInout(int inout) {
		this.inout = inout;
	}
	public int getOpenType() {
		return openType;
	}
	public void setOpenType(int openType) {
		this.openType = openType;
	}
	public int getCardResultStatus() {
		return cardResultStatus;
	}
	public void setCardResultStatus(int cardResultStatus) {
		this.cardResultStatus = cardResultStatus;
	}
	public int getFaceResultStatus() {
		return faceResultStatus;
	}
	public void setFaceResultStatus(int faceResultStatus) {
		this.faceResultStatus = faceResultStatus;
	}
	public String getScenePhoto() {
		return scenePhoto;
	}
	public void setScenePhoto(String scenePhoto) {
		this.scenePhoto = scenePhoto;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
}
