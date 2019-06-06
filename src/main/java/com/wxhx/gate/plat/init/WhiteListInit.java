package com.wxhx.gate.plat.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wxhx.gate.plat.dao.UserWhiteListMapper;
import com.wxhx.gate.plat.dao.entity.UserWhiteList;

/**
 * 白名单初始化
 * @author geliang
 *
 */
@Component
public class WhiteListInit {
	
	public static List<String> WHITE_LIST = new ArrayList<String>();
	
	@Autowired
	private UserWhiteListMapper userWhiteListMapper;
	
	/**
	 * 初始化人脸机相关信息
	 */
	@PostConstruct
	public void init() {
		List<UserWhiteList> whiteList = userWhiteListMapper.selectEnableList();
		if (whiteList != null && whiteList.size() > 0) {
			for (UserWhiteList userWhite : whiteList) {
				WHITE_LIST.add(userWhite.getCarId());
			}
		}
	}
}
