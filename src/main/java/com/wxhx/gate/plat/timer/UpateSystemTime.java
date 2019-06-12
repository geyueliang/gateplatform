package com.wxhx.gate.plat.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;

/**
 * 更新系统时间定时任务
 * @author geliang
 *
 */
@Component
public class UpateSystemTime {

	@Autowired
	private ITimeDealService iTimeDealService;
	
	
	/**
	 * 每半小时执行一次系统时间的比对更新
	 */
	@Scheduled(cron = "0 0/30 * * * ? ")
//	@Scheduled(cron = "0/2 * * * * ? ")
	public void updateTimeTimer() {
		String timeStr = iTimeDealService.getServerTime();
		boolean res = iTimeDealService.setSysTime(timeStr);
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"开始设置校验时间,服务器时间{0},校验结果{1}",timeStr,res);

		
	}
	
}
