package com.wxhx.gate.plat.service;

import com.wxhx.gate.plat.dao.entity.UserWhiteList;

/**
 * 管理员白名单接口
 * @author geliang
 *
 */
public interface IUserWhiteListService {

	/**
	 *     新增白名单
	 * @param userWhite
	 * @return
	 */
	boolean addUserWhiteInfo(UserWhiteList userWhite);
	
}
