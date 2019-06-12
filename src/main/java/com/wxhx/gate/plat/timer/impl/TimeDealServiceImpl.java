package com.wxhx.gate.plat.timer.impl;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;
import com.wxhx.gate.plat.dao.FunctionMapper;
import com.wxhx.gate.plat.timer.ITimeDealService;

@Service
public class TimeDealServiceImpl implements ITimeDealService{

	@Value("${wxhx.gate.plate.ntp.address:default}")
	private String ntpAddress;
	
	@Autowired
	private FunctionMapper functionMapper;
	
	public String getServerTime() {
		String result = "";
		/**
		 * 判断获取时间方式
		 */
		//NTP服务获取
		if(!HXCoreUtil.isEquals("default", ntpAddress)) {
			NTPUDPClient timeClient = new NTPUDPClient();
	        InetAddress timeServerAddress;
			try {
				timeServerAddress = InetAddress.getByName(ntpAddress);
		        TimeInfo timeInfo = timeClient.getTime(timeServerAddress);
		        TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
		        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		        result = dateFormat.format(timeStamp.getDate());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//数据库中获取
		else {
			result = functionMapper.getDbDate();
		}
		return result;
	}

	public boolean setSysTime(String timeStr) {
		boolean result = false;
		//分隔日期和时间
		String[] timeArray = timeStr.split(" ");
		if(timeArray.length==2) {
			try {
				String cmd = " cmd /c time "+timeArray[1];
				Runtime.getRuntime().exec(cmd); // 修改时间
				cmd = " cmd /c date "+timeArray[0];
				Runtime.getRuntime().exec(cmd); // 修改日期
				result = true;
			} catch (Exception e) {
				HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"设置系统时间异常{0}",e.getStackTrace());
			}

		}
		return result;
	}

}
