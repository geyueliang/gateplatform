package com.wxhx.gate.plat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.bean.out.FaceResponse;
import com.wxhx.gate.plat.controller.vo.WhiteListVO;
import com.wxhx.gate.plat.dao.UserWhiteListMapper;
import com.wxhx.gate.plat.dao.entity.UserWhiteList;
import com.wxhx.gate.plat.init.WhiteListInit;
import com.wxhx.gate.plat.service.IUserWhiteListService;
import com.wxhx.gate.plat.service.out.IDongwoPlatService;
import com.wxhx.gate.plat.util.GatePlatUtil;

import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;

@Service
public class UserWhiteListServiceImpl implements IUserWhiteListService{

	@Autowired
	private UserWhiteListMapper userWhiteListMapper;
	
	
	@Autowired
	private IDongwoPlatService iDongwoPlatService;
	
	public boolean addUserWhiteInfo(UserWhiteList userWhite) {
		boolean result = false;
		//先删除白名单
		int delRes = userWhiteListMapper.deleteWhiteList(userWhite.getCarId());
		if(delRes>=0) {
			//新增白名单数据到数据库
			int i= userWhiteListMapper.insertSelective(userWhite);
			if(i>0) {
				//将白名单发送到人脸机器
				WhiteListVO whiteListVO = new WhiteListVO();
				whiteListVO.setIdnum(userWhite.getCarId());
				whiteListVO.setName(userWhite.getUserName());
				whiteListVO.setPhoto(userWhite.getUserPhoto());
				whiteListVO.setValidStart(GatePlatUtil.getFormatDate("yyyy.MM.dd", new Date()));
				whiteListVO.setValidEnd("2099.12.31");
				FaceResponse res = iDongwoPlatService.insertWhiteList(whiteListVO);
				if(res.getCode()==0) {
					WhiteListInit.WHITE_LIST.add(userWhite.getCarId());
					result = true;
				}
			}
		}
		return result;
	}

}
