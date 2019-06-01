package com.wxhx.gate.plat.controller.vo;

/**
 * 
 * @ClassName:  FaceMachineVO   
 * @Description:新增设备信息
 * @author: coyi
 * @date:   2019年5月30日 下午3:52:02  
 * @Copyright: 2019 www.wxhx.com Inc. All rights reserved. 
 * 注意：本内容仅限无锡华讯科技公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class NewFaceMacDevVO {
	private String appid;	//开发者应用ID
	private String deviceno;	//设备编号
	private String timestemp;	//时间戳
	private String sign;	//签名字符串
	private String data;	//设备参数
	private String Device_AppID;	//开发者应用ID
	private String Device_No;	//设备编号
	private String Device_Model;	//设备型号
	private String Device_Name;		//设备名称
	private String Device_Area;	//安装位置
	private int Device_Type;	//设备类型
	private int Device_Enable;	//启用状态
	private int Device_OutInType;	//	出入类型
	private int Device_Blacklist;	//是否启用黑名单
	private int Device_PictureRatio;	//图片压缩比例
	private int Device_Pwd;	//设备密码
	private int Device_Volume;	//设备音量
	private int Device_LightBright;	//设备补光灯亮度
	private int Device_LightMode;	//设备补光灯控制方式
	private String Device_LightOpenTime;	//设备补光灯开启时间
	private String Device_LightCloseTime;	//设备补光灯关闭时间
	private String Device_RecogMode;	//备识别方式
	private int Device_RecogThreshold;	//设备识别通用阈值
	private int Device_WhiteThreshold;	//设备识别白名单阈值
	private int Device_OpenBroadcast;	//设备是否开启语音播报
	private int Device_RecogInterval;	//设备识别间隔
	private int Device_RecogSpace;	//设备识别距离
	private int Device_DoorMode;	//门禁控制方式
	private int Device_DoorInterval;	//门禁开门延时
	private int Device_DoorWiegandType;	//韦根输出
	private int Device_DoorWiegand;	//韦根类型
	private int Device_OpenAdvert;	//是否启用广告显示
	private int Device_AutoRecord;	//识别记录自动上传
	private String Device_AutoRecordUrl;	//识别记录上传地址
	private int Device_IsLiving;	//活体识别
	private int Device_LivingThreshold;	//活体识别阈值
	private int Device_AdvertTime;	//广告切换时间
	private String Device_AdvertTitle;	//广告标语
	private int Device_AutoOpenDoor;	//识别后是否自动开闸
	private int Device_ResatrtDay;	//设备重启间隔
	private String Device_ShowText;	//自定义识别结果显示
	private String Device_BroadcastText;	//自定义语音内容
	private String Device_ResatrtTime;	//设备重启时间点
	private String Device_DelayAlamValue;	//门磁报警延时
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDeviceno() {
		return deviceno;
	}
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
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
	public String getDevice_AppID() {
		return Device_AppID;
	}
	public void setDevice_AppID(String device_AppID) {
		Device_AppID = device_AppID;
	}
	public String getDevice_No() {
		return Device_No;
	}
	public void setDevice_No(String device_No) {
		Device_No = device_No;
	}
	public String getDevice_Model() {
		return Device_Model;
	}
	public void setDevice_Model(String device_Model) {
		Device_Model = device_Model;
	}
	public String getDevice_Name() {
		return Device_Name;
	}
	public void setDevice_Name(String device_Name) {
		Device_Name = device_Name;
	}
	public String getDevice_Area() {
		return Device_Area;
	}
	public void setDevice_Area(String device_Area) {
		Device_Area = device_Area;
	}
	public int getDevice_Type() {
		return Device_Type;
	}
	public void setDevice_Type(int device_Type) {
		Device_Type = device_Type;
	}
	public int getDevice_Enable() {
		return Device_Enable;
	}
	public void setDevice_Enable(int device_Enable) {
		Device_Enable = device_Enable;
	}
	public int getDevice_OutInType() {
		return Device_OutInType;
	}
	public void setDevice_OutInType(int device_OutInType) {
		Device_OutInType = device_OutInType;
	}
	public int getDevice_Blacklist() {
		return Device_Blacklist;
	}
	public void setDevice_Blacklist(int device_Blacklist) {
		Device_Blacklist = device_Blacklist;
	}
	public int getDevice_PictureRatio() {
		return Device_PictureRatio;
	}
	public void setDevice_PictureRatio(int device_PictureRatio) {
		Device_PictureRatio = device_PictureRatio;
	}
	public int getDevice_Pwd() {
		return Device_Pwd;
	}
	public void setDevice_Pwd(int device_Pwd) {
		Device_Pwd = device_Pwd;
	}
	public int getDevice_Volume() {
		return Device_Volume;
	}
	public void setDevice_Volume(int device_Volume) {
		Device_Volume = device_Volume;
	}
	public int getDevice_LightBright() {
		return Device_LightBright;
	}
	public void setDevice_LightBright(int device_LightBright) {
		Device_LightBright = device_LightBright;
	}
	public int getDevice_LightMode() {
		return Device_LightMode;
	}
	public void setDevice_LightMode(int device_LightMode) {
		Device_LightMode = device_LightMode;
	}
	public String getDevice_LightOpenTime() {
		return Device_LightOpenTime;
	}
	public void setDevice_LightOpenTime(String device_LightOpenTime) {
		Device_LightOpenTime = device_LightOpenTime;
	}
	public String getDevice_LightCloseTime() {
		return Device_LightCloseTime;
	}
	public void setDevice_LightCloseTime(String device_LightCloseTime) {
		Device_LightCloseTime = device_LightCloseTime;
	}
	public String getDevice_RecogMode() {
		return Device_RecogMode;
	}
	public void setDevice_RecogMode(String device_RecogMode) {
		Device_RecogMode = device_RecogMode;
	}
	public int getDevice_RecogThreshold() {
		return Device_RecogThreshold;
	}
	public void setDevice_RecogThreshold(int device_RecogThreshold) {
		Device_RecogThreshold = device_RecogThreshold;
	}
	public int getDevice_WhiteThreshold() {
		return Device_WhiteThreshold;
	}
	public void setDevice_WhiteThreshold(int device_WhiteThreshold) {
		Device_WhiteThreshold = device_WhiteThreshold;
	}
	public int getDevice_OpenBroadcast() {
		return Device_OpenBroadcast;
	}
	public void setDevice_OpenBroadcast(int device_OpenBroadcast) {
		Device_OpenBroadcast = device_OpenBroadcast;
	}
	public int getDevice_RecogInterval() {
		return Device_RecogInterval;
	}
	public void setDevice_RecogInterval(int device_RecogInterval) {
		Device_RecogInterval = device_RecogInterval;
	}
	public int getDevice_RecogSpace() {
		return Device_RecogSpace;
	}
	public void setDevice_RecogSpace(int device_RecogSpace) {
		Device_RecogSpace = device_RecogSpace;
	}
	public int getDevice_DoorMode() {
		return Device_DoorMode;
	}
	public void setDevice_DoorMode(int device_DoorMode) {
		Device_DoorMode = device_DoorMode;
	}
	public int getDevice_DoorInterval() {
		return Device_DoorInterval;
	}
	public void setDevice_DoorInterval(int device_DoorInterval) {
		Device_DoorInterval = device_DoorInterval;
	}
	public int getDevice_DoorWiegandType() {
		return Device_DoorWiegandType;
	}
	public void setDevice_DoorWiegandType(int device_DoorWiegandType) {
		Device_DoorWiegandType = device_DoorWiegandType;
	}
	public int getDevice_DoorWiegand() {
		return Device_DoorWiegand;
	}
	public void setDevice_DoorWiegand(int device_DoorWiegand) {
		Device_DoorWiegand = device_DoorWiegand;
	}
	public int getDevice_OpenAdvert() {
		return Device_OpenAdvert;
	}
	public void setDevice_OpenAdvert(int device_OpenAdvert) {
		Device_OpenAdvert = device_OpenAdvert;
	}
	public int getDevice_AutoRecord() {
		return Device_AutoRecord;
	}
	public void setDevice_AutoRecord(int device_AutoRecord) {
		Device_AutoRecord = device_AutoRecord;
	}
	public String getDevice_AutoRecordUrl() {
		return Device_AutoRecordUrl;
	}
	public void setDevice_AutoRecordUrl(String device_AutoRecordUrl) {
		Device_AutoRecordUrl = device_AutoRecordUrl;
	}
	public int getDevice_IsLiving() {
		return Device_IsLiving;
	}
	public void setDevice_IsLiving(int device_IsLiving) {
		Device_IsLiving = device_IsLiving;
	}
	public int getDevice_LivingThreshold() {
		return Device_LivingThreshold;
	}
	public void setDevice_LivingThreshold(int device_LivingThreshold) {
		Device_LivingThreshold = device_LivingThreshold;
	}
	public int getDevice_AdvertTime() {
		return Device_AdvertTime;
	}
	public void setDevice_AdvertTime(int device_AdvertTime) {
		Device_AdvertTime = device_AdvertTime;
	}
	public String getDevice_AdvertTitle() {
		return Device_AdvertTitle;
	}
	public void setDevice_AdvertTitle(String device_AdvertTitle) {
		Device_AdvertTitle = device_AdvertTitle;
	}
	public int getDevice_AutoOpenDoor() {
		return Device_AutoOpenDoor;
	}
	public void setDevice_AutoOpenDoor(int device_AutoOpenDoor) {
		Device_AutoOpenDoor = device_AutoOpenDoor;
	}
	public int getDevice_ResatrtDay() {
		return Device_ResatrtDay;
	}
	public void setDevice_ResatrtDay(int device_ResatrtDay) {
		Device_ResatrtDay = device_ResatrtDay;
	}
	public String getDevice_ShowText() {
		return Device_ShowText;
	}
	public void setDevice_ShowText(String device_ShowText) {
		Device_ShowText = device_ShowText;
	}
	public String getDevice_BroadcastText() {
		return Device_BroadcastText;
	}
	public void setDevice_BroadcastText(String device_BroadcastText) {
		Device_BroadcastText = device_BroadcastText;
	}
	public String getDevice_ResatrtTime() {
		return Device_ResatrtTime;
	}
	public void setDevice_ResatrtTime(String device_ResatrtTime) {
		Device_ResatrtTime = device_ResatrtTime;
	}
	public String getDevice_DelayAlamValue() {
		return Device_DelayAlamValue;
	}
	public void setDevice_DelayAlamValue(String device_DelayAlamValue) {
		Device_DelayAlamValue = device_DelayAlamValue;
	}
}
