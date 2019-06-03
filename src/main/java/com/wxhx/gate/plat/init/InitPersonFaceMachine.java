package com.wxhx.gate.plat.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.controller.vo.FaceMacDevVO;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.util.PersonFaceMachineInfo;

/**
 * 初始化人脸机器相关信息
 * @author geliang
 *
 */
@Component
public class InitPersonFaceMachine {
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	@Value("${wxhx.person.face.deviceno:5fc7540d5efdf555}")
	private String deviceno;
	
	@Value("${wxhx.person.face.appId:mj4bfdecbef41e2faf}")
	private String appId;
	
	@Value("${wxhx.kcxi.ddxx.ksdd:520300208}")
	private String ksdd;
	
	/**
	 * 初始化人脸机相关信息
	 */
	@PostConstruct
	public void init() {
		//设定全局变量
		PersonFaceMachineInfo.APPID = appId;	//开发者应用ID
		//查询设备信息
		/*FaceMacDevVO faceMacDevVO = new FaceMacDevVO();
		faceMacDevVO.setDeviceAppID(appId);
		faceMacDevVO.setDeviceNo(deviceno);
		FaceResponse faceResponse = iDongwoPlatService.selectDevice(faceMacDevVO);
		if(!("1".equals(faceResponse.getResultcode()))) {
			//新增设备
			faceMacDevVO.setDeviceType(1);
			faceMacDevVO.setDeviceEnable(1);
			faceMacDevVO.setDeviceOutInType(0);
			faceMacDevVO.setDeviceBlacklist(1);
			faceMacDevVO.setDevicePictureRatio(80);
			faceMacDevVO.setDeviceVolume(80);
			faceMacDevVO.setDeviceLightBright(100);
			faceMacDevVO.setDeviceLightMode(0);
			faceMacDevVO.setDeviceRecogThreshold(80);
			faceMacDevVO.setDeviceWhiteThreshold(80);
			faceMacDevVO.setDeviceOpenBroadcast(1);
			faceMacDevVO.setDeviceRecogInterval(5);
			faceMacDevVO.setDeviceRecogSpace(400);
			faceMacDevVO.setDeviceDoorMode(0);
			faceMacDevVO.setDeviceDoorInterval(5);
			faceMacDevVO.setDeviceDoorWiegandType(1);
			faceMacDevVO.setDeviceDoorWiegand(26);
			faceMacDevVO.setDeviceOpenAdvert(0);
			faceMacDevVO.setDeviceAutoRecord(1);
			faceMacDevVO.setDeviceAutoRecordUrl("");
			faceMacDevVO.setDeviceIsLiving(0);
			faceMacDevVO.setDeviceLivingThreshold(0);
			faceMacDevVO.setDeviceAdvertTime(5);
			faceMacDevVO.setDeviceAutoOpenDoor(0);
			faceMacDevVO.setDeviceResatrtDay(7);
			faceMacDevVO.setDeviceShowText("");
			faceMacDevVO.setDeviceBroadcastText("");
			faceMacDevVO.setDeviceResatrtTime("05:00:00");
			faceMacDevVO.setDeviceDelayAlamValue("60");
			iDongwoPlatService.insertDevice(faceMacDevVO);
			PersonFaceMachineInfo.DEVICENO = deviceno;	//设备编号
			PersonFaceMachineInfo.KSDD = ksdd;	//考试地点
		
		}
*/	
	}
	
}
