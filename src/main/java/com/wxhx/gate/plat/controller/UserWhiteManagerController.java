package com.wxhx.gate.plat.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wxhx.basic_client.web.HXRespons;
import com.wxhx.gate.plat.dao.entity.UserWhiteList;
import com.wxhx.gate.plat.service.IUserWhiteListService;

/**
 * 白名单管理controller
 * @author geliang
 *
 */
@RestController
public class UserWhiteManagerController {

	@Autowired
	private IUserWhiteListService userWhiteListService;
	
	/**
	 * 新增白名单
	 * @param file
	 * @param userWhiteList
	 * @return
	 * @throws Exception 
	 */

	@RequestMapping(value = "/addWhiteUser", method= RequestMethod.POST)
	public HXRespons<String> addWhiteUser(@RequestParam("imageFile") MultipartFile file, UserWhiteList userWhiteList) throws Exception{
		HXRespons<String> result = new HXRespons<String>();
		Base64 base64 = new Base64();
		byte[] data = new byte[file.getInputStream().available()];
		file.getInputStream().read(data);
		String imageBase64 =  new String(base64.encode(data));
		userWhiteList.setUserPhoto(imageBase64);
		if(userWhiteListService.addUserWhiteInfo(userWhiteList)) {
			result.setResCode("1");
		}
		else {
			result.setResCode("0");
		}
		return result;
	}
	
}
