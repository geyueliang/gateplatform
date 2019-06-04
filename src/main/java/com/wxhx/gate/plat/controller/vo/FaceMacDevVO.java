package com.wxhx.gate.plat.controller.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @ClassName:  FaceMachineVO   
 * @Description:设备信息
 * @author: coyi
 * @date:   2019年5月30日 下午3:52:02  
 * @Copyright: 2019 www.wxhx.com Inc. All rights reserved. 
 * 注意：本内容仅限无锡华讯科技公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class FaceMacDevVO{
	@JSONField(name = "Device_AppID")
	private String deviceAppID;	//开发者应用ID
	@JSONField(name = "Device_No")
	private String deviceNo;	//设备编号
	@JSONField(name = "Device_Model")
	private String deviceModel;	//设备型号
	@JSONField(name = "Device_Name")
	private String deviceName;		//设备名称
	@JSONField(name = "Device_Area")
	private String deviceArea;	//安装位置
	@JSONField(name = "Device_Type")
	private int deviceType;	//设备类型
	@JSONField(name = "Device_Enable")
	private int deviceEnable;	//启用状态
	@JSONField(name = "Device_OutInType")
	private int deviceOutInType;	//	出入类型
	@JSONField(name = "Device_Blacklist")
	private int deviceBlacklist;	//是否启用黑名单
	@JSONField(name = "Device_PictureRatio")
	private int devicePictureRatio;	//图片压缩比例
	@JSONField(name = "Device_Pwd")
	private int devicePwd;	//设备密码
	@JSONField(name = "Device_Volume")
	private int deviceVolume;	//设备音量
	@JSONField(name = "Device_LightBright")
	private int deviceLightBright;	//设备补光灯亮度
	@JSONField(name = "Device_LightMode")
	private int deviceLightMode;	//设备补光灯控制方式
	@JSONField(name = "Device_LightOpenTime")
	private String deviceLightOpenTime;	//设备补光灯开启时间
	@JSONField(name = "Device_LightCloseTime")
	private String deviceLightCloseTime;	//设备补光灯关闭时间
	@JSONField(name = "Device_RecogMode")
	private String deviceRecogMode;	//备识别方式
	@JSONField(name = "Device_RecogThreshold")
	private int deviceRecogThreshold;	//设备识别通用阈值
	@JSONField(name = "Device_WhiteThreshold")
	private int deviceWhiteThreshold;	//设备识别白名单阈值
	@JSONField(name = "Device_OpenBroadcast")
	private int deviceOpenBroadcast;	//设备是否开启语音播报
	@JSONField(name = "Device_RecogInterval")
	private int deviceRecogInterval;	//设备识别间隔
	@JSONField(name = "Device_RecogSpace")
	private int deviceRecogSpace;	//设备识别距离
	@JSONField(name = "Device_DoorMode")
	private int deviceDoorMode;	//门禁控制方式
	@JSONField(name = "Device_DoorInterval")
	private int deviceDoorInterval;	//门禁开门延时
	@JSONField(name = "Device_DoorWiegandType")
	private int deviceDoorWiegandType;	//韦根输出
	@JSONField(name = "Device_DoorWiegand")
	private int deviceDoorWiegand;	//韦根类型
	@JSONField(name = "Device_OpenAdvert")
	private int deviceOpenAdvert;	//是否启用广告显示
	@JSONField(name = "Device_AutoRecord")
	private int deviceAutoRecord;	//识别记录自动上传
	@JSONField(name = "url")
	private String deviceAutoRecordUrl;	//识别记录上传地址
	@JSONField(name = "Device_IsLiving")
	private int deviceIsLiving;	//活体识别
	@JSONField(name = "Device_LivingThreshold")
	private int deviceLivingThreshold;	//活体识别阈值
	@JSONField(name = "Device_AdvertTime")
	private int deviceAdvertTime;	//广告切换时间
	@JSONField(name = "Device_AdvertTitle")
	private String deviceAdvertTitle;	//广告标语
	@JSONField(name = "Device_AutoOpenDoor")
	private int deviceAutoOpenDoor;	//识别后是否自动开闸
	@JSONField(name = "Device_ResatrtDay")
	private int deviceResatrtDay;	//设备重启间隔
	@JSONField(name = "Device_ShowText")
	private String deviceShowText;	//自定义识别结果显示
	@JSONField(name = "Device_BroadcastText")
	private String deviceBroadcastText;	//自定义语音内容
	@JSONField(name = "Device_ResatrtTime")
	private String deviceResatrtTime;	//设备重启时间点
	@JSONField(name = "Device_DelayAlamValue")
	private String deviceDelayAlamValue;	//门磁报警延时
	public String getDeviceAppID() {
		return deviceAppID;
	}
	public void setDeviceAppID(String deviceAppID) {
		this.deviceAppID = deviceAppID;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceArea() {
		return deviceArea;
	}
	public void setDeviceArea(String deviceArea) {
		this.deviceArea = deviceArea;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public int getDeviceEnable() {
		return deviceEnable;
	}
	public void setDeviceEnable(int deviceEnable) {
		this.deviceEnable = deviceEnable;
	}
	public int getDeviceOutInType() {
		return deviceOutInType;
	}
	public void setDeviceOutInType(int deviceOutInType) {
		this.deviceOutInType = deviceOutInType;
	}
	public int getDeviceBlacklist() {
		return deviceBlacklist;
	}
	public void setDeviceBlacklist(int deviceBlacklist) {
		this.deviceBlacklist = deviceBlacklist;
	}
	public int getDevicePictureRatio() {
		return devicePictureRatio;
	}
	public void setDevicePictureRatio(int devicePictureRatio) {
		this.devicePictureRatio = devicePictureRatio;
	}
	public int getDevicePwd() {
		return devicePwd;
	}
	public void setDevicePwd(int devicePwd) {
		this.devicePwd = devicePwd;
	}
	public int getDeviceVolume() {
		return deviceVolume;
	}
	public void setDeviceVolume(int deviceVolume) {
		this.deviceVolume = deviceVolume;
	}
	public int getDeviceLightBright() {
		return deviceLightBright;
	}
	public void setDeviceLightBright(int deviceLightBright) {
		this.deviceLightBright = deviceLightBright;
	}
	public int getDeviceLightMode() {
		return deviceLightMode;
	}
	public void setDeviceLightMode(int deviceLightMode) {
		this.deviceLightMode = deviceLightMode;
	}
	public String getDeviceLightOpenTime() {
		return deviceLightOpenTime;
	}
	public void setDeviceLightOpenTime(String deviceLightOpenTime) {
		this.deviceLightOpenTime = deviceLightOpenTime;
	}
	public String getDeviceLightCloseTime() {
		return deviceLightCloseTime;
	}
	public void setDeviceLightCloseTime(String deviceLightCloseTime) {
		this.deviceLightCloseTime = deviceLightCloseTime;
	}
	public String getDeviceRecogMode() {
		return deviceRecogMode;
	}
	public void setDeviceRecogMode(String deviceRecogMode) {
		this.deviceRecogMode = deviceRecogMode;
	}
	public int getDeviceRecogThreshold() {
		return deviceRecogThreshold;
	}
	public void setDeviceRecogThreshold(int deviceRecogThreshold) {
		this.deviceRecogThreshold = deviceRecogThreshold;
	}
	public int getDeviceWhiteThreshold() {
		return deviceWhiteThreshold;
	}
	public void setDeviceWhiteThreshold(int deviceWhiteThreshold) {
		this.deviceWhiteThreshold = deviceWhiteThreshold;
	}
	public int getDeviceOpenBroadcast() {
		return deviceOpenBroadcast;
	}
	public void setDeviceOpenBroadcast(int deviceOpenBroadcast) {
		this.deviceOpenBroadcast = deviceOpenBroadcast;
	}
	public int getDeviceRecogInterval() {
		return deviceRecogInterval;
	}
	public void setDeviceRecogInterval(int deviceRecogInterval) {
		this.deviceRecogInterval = deviceRecogInterval;
	}
	public int getDeviceRecogSpace() {
		return deviceRecogSpace;
	}
	public void setDeviceRecogSpace(int deviceRecogSpace) {
		this.deviceRecogSpace = deviceRecogSpace;
	}
	public int getDeviceDoorMode() {
		return deviceDoorMode;
	}
	public void setDeviceDoorMode(int deviceDoorMode) {
		this.deviceDoorMode = deviceDoorMode;
	}
	public int getDeviceDoorInterval() {
		return deviceDoorInterval;
	}
	public void setDeviceDoorInterval(int deviceDoorInterval) {
		this.deviceDoorInterval = deviceDoorInterval;
	}
	public int getDeviceDoorWiegandType() {
		return deviceDoorWiegandType;
	}
	public void setDeviceDoorWiegandType(int deviceDoorWiegandType) {
		this.deviceDoorWiegandType = deviceDoorWiegandType;
	}
	public int getDeviceDoorWiegand() {
		return deviceDoorWiegand;
	}
	public void setDeviceDoorWiegand(int deviceDoorWiegand) {
		this.deviceDoorWiegand = deviceDoorWiegand;
	}
	public int getDeviceOpenAdvert() {
		return deviceOpenAdvert;
	}
	public void setDeviceOpenAdvert(int deviceOpenAdvert) {
		this.deviceOpenAdvert = deviceOpenAdvert;
	}
	public int getDeviceAutoRecord() {
		return deviceAutoRecord;
	}
	public void setDeviceAutoRecord(int deviceAutoRecord) {
		this.deviceAutoRecord = deviceAutoRecord;
	}
	public String getDeviceAutoRecordUrl() {
		return deviceAutoRecordUrl;
	}
	public void setDeviceAutoRecordUrl(String deviceAutoRecordUrl) {
		this.deviceAutoRecordUrl = deviceAutoRecordUrl;
	}
	public int getDeviceIsLiving() {
		return deviceIsLiving;
	}
	public void setDeviceIsLiving(int deviceIsLiving) {
		this.deviceIsLiving = deviceIsLiving;
	}
	public int getDeviceLivingThreshold() {
		return deviceLivingThreshold;
	}
	public void setDeviceLivingThreshold(int deviceLivingThreshold) {
		this.deviceLivingThreshold = deviceLivingThreshold;
	}
	public int getDeviceAdvertTime() {
		return deviceAdvertTime;
	}
	public void setDeviceAdvertTime(int deviceAdvertTime) {
		this.deviceAdvertTime = deviceAdvertTime;
	}
	public String getDeviceAdvertTitle() {
		return deviceAdvertTitle;
	}
	public void setDeviceAdvertTitle(String deviceAdvertTitle) {
		this.deviceAdvertTitle = deviceAdvertTitle;
	}
	public int getDeviceAutoOpenDoor() {
		return deviceAutoOpenDoor;
	}
	public void setDeviceAutoOpenDoor(int deviceAutoOpenDoor) {
		this.deviceAutoOpenDoor = deviceAutoOpenDoor;
	}
	public int getDeviceResatrtDay() {
		return deviceResatrtDay;
	}
	public void setDeviceResatrtDay(int deviceResatrtDay) {
		this.deviceResatrtDay = deviceResatrtDay;
	}
	public String getDeviceShowText() {
		return deviceShowText;
	}
	public void setDeviceShowText(String deviceShowText) {
		this.deviceShowText = deviceShowText;
	}
	public String getDeviceBroadcastText() {
		return deviceBroadcastText;
	}
	public void setDeviceBroadcastText(String deviceBroadcastText) {
		this.deviceBroadcastText = deviceBroadcastText;
	}
	public String getDeviceResatrtTime() {
		return deviceResatrtTime;
	}
	public void setDeviceResatrtTime(String deviceResatrtTime) {
		this.deviceResatrtTime = deviceResatrtTime;
	}
	public String getDeviceDelayAlamValue() {
		return deviceDelayAlamValue;
	}
	public void setDeviceDelayAlamValue(String deviceDelayAlamValue) {
		this.deviceDelayAlamValue = deviceDelayAlamValue;
	}
	
}
