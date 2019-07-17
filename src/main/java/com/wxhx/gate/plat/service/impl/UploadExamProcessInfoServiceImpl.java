package com.wxhx.gate.plat.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.gate.plat.aop.annotation.ExamProcessLogSaveAnnotation;
import com.wxhx.gate.plat.bean.exam.process.AuthenticationResult;
import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.ExamStop;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessBase;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.KsyxxMapper;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.KszpMapper;
import com.wxhx.gate.plat.dao.entity.Kcsb;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.dao.entity.Kszp;
import com.wxhx.gate.plat.init.InitKSKFDM;
import com.wxhx.gate.plat.init.KcsbInit;
import com.wxhx.gate.plat.service.IUploadExamProcessInfoService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.bean.WebServiceResultHead;
import com.wxhx.gate.plat.service.impl.cache.ExamineeInfoCacheManager;
import com.wxhx.gate.plat.util.GatePlatUtil;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;



@Service
public class UploadExamProcessInfoServiceImpl implements  IUploadExamProcessInfoService{

	private static Logger logger = LoggerFactory.getLogger("examLog");
	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	@Autowired
	private KsyxxMapper ksyxxMappper;
	
	
	@Autowired
	private KszpMapper kszpMapper;
	
	@Autowired
	private ExamineeInfoCacheManager examineeInfoCacheManager;
	
	//所有的考试项目
	private List<String> KSXMS = Arrays.asList("20100","20300","20400","20600","20700","23100","23200");


	
	/**
	 * 统一入口
	 */
	public int unifiedEntrance(String content) throws Exception {
		//替換坡道起步的考試項目
		if(content.contains(",23000,")) {
			content.replace(",23000,", ",20300,");
		}
		WebServiceResult webServiceResult = null;
		int result = 0;
		//解析车上上传的数据信息
		String[] infoArray = content.split(",");
		HXLogUtil.info(logger,"===========车载调用交互中心的入参:{0}==============",Arrays.asList(infoArray));
		//异常信息直接返回正确
		if(infoArray.length<2) {
			return 1;
		}
		//根据上传第二位 得到具体的操作类型
		int typeId = Integer.parseInt(infoArray[1]);
		switch (typeId) {
		//身份认证（科目开始 或者项目开始）
		case 0:
			IdentityComparison identityComparison = this.getCallBeanFromCarContentArray(infoArray, typeId);
			if(identityComparison!=null) {
				webServiceResult = this.idCheck(identityComparison);
			}
			break;
			
		// 考试扣分
		case 1:
			ExamMark examMark = this.getCallBeanFromCarContentArray(infoArray, typeId);
			if (examMark != null) {
				webServiceResult = this.examMarkHappen(examMark);
			}
			break;
			
		//图片上传
		case 2:
			ProcessImage processImage = this.getCallBeanFromCarContentArray(infoArray, typeId);
			if (processImage != null) {
				webServiceResult = this.uploadImage(processImage);
			}
			break;

		// 项目开始 / 结束
		case 3:
			ExamItemEnd examItemEnd = this.getCallBeanFromCarContentArray(infoArray, typeId);
			// 根据结束入参 来开始下一个项目
			if (HXCoreUtil.isEquals("xmks", examItemEnd.getAddressType())) {
				ItemBegin itemBegin = this.createNextBeginItem(examItemEnd);
				HXLogUtil.info(logger, "当前{0}项目结束，下一个项目{1}开始", examItemEnd.getKsxm(), itemBegin.getKsxm());
				webServiceResult = this.itemBegin(itemBegin);
			}
			// 项目结束
			if ((HXCoreUtil.isEquals("xmjs", examItemEnd.getAddressType()))) {
				webServiceResult = this.examItemEnd(examItemEnd);
			}
			break;
			
		//发起视频认证
		case 5:
			WirteVideo wirteVideo = this.getCallBeanFromCarContentArray(infoArray, typeId);
			if(wirteVideo !=null) {
				webServiceResult = this.writeVideo(wirteVideo);
			}
			break;
		//视频认证结果
		case 6:
			ReadVideo redReadVideo = this.getCallBeanFromCarContentArray(infoArray, typeId);
			if(redReadVideo !=null) {
				webServiceResult = this.readVideo(redReadVideo);
			}
			break;
		default:
			break;
		}
		HXLogUtil.info(logger,"===========车载调用交互中心的出参:{0}==============",webServiceResult);
		if(webServiceResult==null || webServiceResult.getHead()==null) {
			result = 0;
		}
		else {
			result = Integer.parseInt(HXCoreUtil.isEmpty(webServiceResult.getHead().getCode())?webServiceResult.getHead().getCode():"0");
		}
		return result;
	}
	
	
	
	
	
	/**
	 * 根据车载上传的数据 获取当前需要调用的请求信息
	 * @param <T>
	 * @param arrayInfo
	 * @param type
	 * @return
	 */
	private <T extends ProcessBase> T getCallBeanFromCarContentArray(String[] arrayInfo,int typeId) {
		String sfzmhm =arrayInfo[3];	//身份证号码
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(sfzmhm);	//考试预约信息
		T t = null;
		switch (typeId) {
		//身份认证(科目开始)(第一个项目开始)
		case 0:
			IdentityComparison	identityComparison = new IdentityComparison();
			//当前标记
			String startTag = arrayInfo[arrayInfo.length-1];
			identityComparison.setAddressType(startTag);
			//考试系统编号
			identityComparison.setKsxtbh(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSXTBH));
			//开始时间
			identityComparison.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			if(ksyyxx!=null && HXCoreUtil.isEquals("kmks", startTag)) {
				String ksysfzmhm = ksyxxMappper.getKsySfz(ksyyxx.getKsy1());
				//考试员身份证号码
				if(!HXCoreUtil.isEmpty(ksysfzmhm)) {
					identityComparison.setKsysfzmhm(ksysfzmhm);
				}
				//照片
				Kszp kszp = kszpMapper.getKszpByCarNo(ksyyxx.getSfzmhm(), "BD");
				if(kszp!=null) {
					identityComparison.setZp(kszp.getZp());
				}
				t = (T) identityComparison;
			}
			break;
			
		//考试扣分
		case 1:
			ExamMark examMark = new ExamMark();
			String ksxm = arrayInfo[6];
			//考试项目
			examMark.setKsxm(ksxm);
			//扣分项目
			examMark.setKfxm(arrayInfo[8]);
			//扣分时间
			examMark.setKfsj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			//考车设备
			examMark.setKcsb(arrayInfo[7]);
			t = (T) examMark;
			break;
					
		//图片上传
		case 2:
			ProcessImage processImage = new ProcessImage();
			//照片时间
			processImage.setZpsj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			//照片
			try {
				processImage.setZp(URLEncoder.encode(arrayInfo[9], "utf-8"));
			} catch (UnsupportedEncodingException e) {
				processImage.setZp(arrayInfo[9]);
			}
			//车速
			processImage.setCs(arrayInfo[8]);
			//考试项目
			processImage.setKsxm(arrayInfo[6]);
			t = (T) processImage;
			break;

		//项目结束
		case 3:
			ExamItemEnd examItemEnd = new ExamItemEnd();
			//地点标记
			examItemEnd.setAddressType(arrayInfo[arrayInfo.length-1]);
			//设备序列号
			Kcsb kcsb = KcsbInit.getKcsb(arrayInfo[7]);
			examItemEnd.setSbxh(kcsb!=null?kcsb.getSbbh():arrayInfo[7]);  //设备序列号
			//结束时间
			examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			//操作类型
			examItemEnd.setCzlx("1");
			//考试项目
			examItemEnd.setKsxm(arrayInfo[6]);
			t = (T) examItemEnd;
			break;
		
		// 开始视频认证
		case 5:
			WirteVideo wirteVideo = new WirteVideo();
			wirteVideo.setKchp(EvnVarConstentInfo.getCarInfo(arrayInfo[4]));
			wirteVideo.setKsxtbh(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSXTBH));
			t = (T) wirteVideo;
			break;
		// 读取视频认证结果
		case 6:
			ReadVideo readVideo = new ReadVideo();
			readVideo.setKchp(EvnVarConstentInfo.getCarInfo(arrayInfo[4]));
			t = (T) readVideo;
			break;
			
		default:
			break;
		}
		/**
		 * 通用信息设置
		 */
		if(t!=null) {
			//流水号
			t.setLsh(ksyyxx.getLsh());
			//考试地点
			t.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
			//考试科目
			t.setKskm("2");
			//单位机构代码
			t.setDwjgdm(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.XZGLBM));
			//终端标识
			t.setZdbs(GatePlatUtil.getLocalhostIp());
			//身份证号码
			t.setSfzmhm(sfzmhm);
		}
		return t;
	}
	
	
	/**
	 * 发起视频认证
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult writeVideo(WirteVideo wirteVideo) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(wirteVideo);
		String jkid = "17E14"; //视频认证发启（写入）
		HXLogUtil.info(logger,"发起视频认证调用{0},入参{1}",jkid,writeXml);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"发起视频认证返回{0}",URLDecoder.decode(result, "utf-8"));
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}

	/**
	 * 获取视频认证返回结果
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult readVideo(ReadVideo readVideo) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(readVideo);
		String jkid = "17E15"; //读取视频认证结果
		HXLogUtil.info(logger,"读取视频认证结果调用{0},入参{1}",jkid,writeXml);
		String result =  HXCallWebServiceUtil.queryWebService(jkid, writeXml);
		HXLogUtil.info(logger,"读取视频认证结果调用返回{0}",URLDecoder.decode(result, "utf-8"));
		WebServiceResult<AuthenticationResult> authenResult = HXCallWebServiceUtil.xmlToBean(result, AuthenticationResult.class);
		//视频认证结果有返回
		if(authenResult!=null && authenResult.getHead() !=null && authenResult.getBodyContent().getContent()!=null) {
			AuthenticationResult  authenticationResult = (AuthenticationResult) authenResult.getBodyContent().getContent().get(0);
			int rzjg = Integer.parseInt(authenticationResult.getRzjg()!=null?authenticationResult.getRzjg():"0");
			String setCode = "0";
			switch (rzjg) {
			case 1:
				setCode = "1";
				break;
			case 3:
				setCode = "1";
				break;
			case 0:
				setCode = "2";
				break;
			default:
				break;
			}
			authenResult.getHead().setCode(setCode);
		}
		return authenResult;
	}

	/**
	 * 身份认证  或者第一项目开始
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult idCheck(IdentityComparison comparison) throws Exception {
		WebServiceResult result = null;
		//身份认证
		if(HXCoreUtil.isEquals("kmks", comparison.getAddressType())) {
			//初始化当前考试在系统的的考试缓存信息
			examineeInfoCacheManager.initExamCache(comparison.getSfzmhm());
			String writeXml = HXCallWebServiceUtil.beanToXml(comparison);
			String jkid = "17C51"; //身份认证
			HXLogUtil.info(logger,"身份对比调用{0},入参{1}",jkid,HXCallWebServiceUtil.enCodeStr(writeXml));
			String resultStr = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
			HXLogUtil.info(logger,"身份对比调用返回{0}",URLDecoder.decode(resultStr, "utf-8"));
			result = HXCallWebServiceUtil.xmlToBean(resultStr, WebServiceResult.class);
		}
		//第一个项目开始
		if(HXCoreUtil.isEquals("xmks", comparison.getAddressType())) {
			//开始倒车入库
			result = this.itemBegin(createFirstBeginItem(comparison));
		}
		return result;
	}

	/**
	 * 创建第一个开始项目
	 * @param comparison
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	private ItemBegin createFirstBeginItem(IdentityComparison comparison) throws Exception {
		ItemBegin itemBegin = null;
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(comparison.getSfzmhm());
		if(ksyyxx!=null&&!HXCoreUtil.isEmpty(ksyyxx.getKsxm())) {
			//当前路线序号
			String lxxh = ksyyxx.getLxxh();
			itemBegin = new ItemBegin();
			//复制基本属性
			BeanUtils.copyProperties(itemBegin, comparison);
			String ksxm = KcsbInit.getNextBeginItem(lxxh, null);
			itemBegin.setKsxm(ksxm);	//考试项目
			String sbxh = KcsbInit.getNextSbxh(lxxh, null); //设备序号
			String sbbh = KcsbInit.getSbbhBySbxh(sbxh); 
			itemBegin.setSbxh(sbbh);
			itemBegin.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
		}
		return itemBegin;
	}
	
	
	/**
	 * 根据结束项目创建下一个项目的开始
	 * @param examItem
	 * @return
	 */
	private ItemBegin createNextBeginItem(ExamItemEnd examItem) throws Exception{
		ItemBegin itemBegin = null;
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(examItem.getSfzmhm());
		if(ksyyxx!=null&&!HXCoreUtil.isEmpty(ksyyxx.getKsxm())) {
			//当前路线序号
			String lxxh = ksyyxx.getLxxh();
			String ksxm = KcsbInit.getNextBeginItem(lxxh, examItem.getKsxm());
			if(HXCoreUtil.isEmpty(ksxm)) {
				return null;
			}
			itemBegin = new ItemBegin();
			//复制基本属性
			BeanUtils.copyProperties(examItem, itemBegin);
			itemBegin.setKsxm(ksxm);	//考试项目
			String sbxh = KcsbInit.getNextSbxh(lxxh, ksxm); //设备序号
			String sbbh = KcsbInit.getSbbhBySbxh(sbxh); 
			itemBegin.setSbxh(sbbh);
			itemBegin.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
		}
		return itemBegin;
	}
	
	/**
	 * 项目开始
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult itemBegin(ItemBegin itemBegin) throws Exception {
		examineeInfoCacheManager.setKsxm(itemBegin.getSfzmhm(), itemBegin.getKsxm());
		if(HXCoreUtil.isEquals("20500", itemBegin.getKsxm())) {
			HXLogUtil.info(logger,"当前开始项目是20500,直接返回成功");
			return this.createSuccess();
		}
		String writeXml = HXCallWebServiceUtil.beanToXml(itemBegin);
		String jkid = "17C52";	//项目开始接口
		HXLogUtil.info(logger,"项目开始调用{0},入参{1}",jkid,writeXml);
		String result =  HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"项目开始调用返回{0}",URLDecoder.decode(result,"utf-8"));
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}

	/**
	 * 上传图片信息
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult uploadImage(ProcessImage processImage) throws Exception {
		//设置当前图片缓存
		examineeInfoCacheManager.setNowZp(processImage.getSfzmhm(), processImage.getZp());
		//获取当前考试所在项目
		String nowKsxm = examineeInfoCacheManager.getKsxm(processImage.getSfzmhm());
		//比较当前考试的项目是否和图片上的项目一直
		if(HXCoreUtil.isEquals(nowKsxm, processImage.getKsxm())) {
			//一致的话判断当前项目是否在考试外
			if(!KSXMS.contains(processImage.getKsxm())) {
				processImage.setKsxm("10000");
			}
		}
		else {
			return createSuccess();
		}
		String writeXml = HXCallWebServiceUtil.beanToXml(processImage);
		String jkid = "17C54"; //图片上传
		String logStr = getParamNoImage(writeXml);
		HXLogUtil.info(logger,"上传图片调用{0},入参{1}",jkid,logStr);
		String result =  HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"上传图片调用返回结果{0}",result);
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}
	
	/**
	 * 发生扣分
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult examMarkHappen(ExamMark examMark) throws Exception {
		//获取当前考试所在项目
		String nowKsxm = examineeInfoCacheManager.getKsxm(examMark.getSfzmhm());
		//比较当前考试的项目是否和图片上的项目一直
		if(HXCoreUtil.isEquals(nowKsxm, examMark.getKsxm())) {
			//一致的话判断当前项目是否在考试外
			if(!KSXMS.contains(examMark.getKsxm())) {
				examMark.setKsxm("10000");
			}
		}
		else {
			examMark.setKskm(nowKsxm);
		}
		
		//获取当前考生的扣分
		int nowKsck = examineeInfoCacheManager.getNowKf(examMark.getSfzmhm());
		int bckf = InitKSKFDM.getKf(examMark.getKfxm());
		//更新累计扣分
		examineeInfoCacheManager.updateKf(examMark.getKfxm(), bckf);
		String writeXml = HXCallWebServiceUtil.beanToXml(examMark);
		String jkid = "17C53"; //考试扣分
		HXLogUtil.info(logger,"发生扣分调用{0},入参{1}",jkid,writeXml);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"发生扣分调用返回{0}",result);
		//累计扣分超过20分
		if((nowKsck+bckf)>20) {
			HXLogUtil.info(logger,"当前考生{0}扣分{1}超过20分,进行项目结束",examMark.getKfxm(),(nowKsck+bckf));
			//创建结束项目参数
			ExamItemEnd examItemEnd = createItemEndByMark(examMark);
			this.examItemEnd(examItemEnd);
		}
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}
	
	
	/**
	 * 根据考试扣分 创建项目结束入参
	 * @param examMark
	 * @return
	 * @throws Exception 
	 * @throws  
	 */
	private ExamItemEnd createItemEndByMark(ExamMark examMark) throws  Exception {
		ExamItemEnd examItemEnd = new ExamItemEnd();
		Kcsb kcsb = KcsbInit.getKcsb(examMark.getKcsb());
		examItemEnd.setSbxh(kcsb!=null?kcsb.getSbbh():examMark.getKcsb());  //设备序列号
		examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date())); //结束时间
		examItemEnd.setCzlx("1");
		examItemEnd.setKsxm(examMark.getKsxm()); //考试项目
		BeanUtils.copyProperties(examItemEnd, examMark);
		return examItemEnd;
	}

	/**
	 * 项目结束
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult examItemEnd(ExamItemEnd examItemEnd) throws Exception {
		//考试结束 调用科目结束
		if(HXCoreUtil.isEquals("20500", examItemEnd.getKsxm())) {
			//根据当前结束项目  创建科目结束入参
			HXLogUtil.info(logger, "当前项目是{0}直接科目结束", examItemEnd.getKsxm());
			ExamEnd examEnd = this.createExamEndParam(examItemEnd);
			return this.examEnd(examEnd);
		}
		
		String writeXml = HXCallWebServiceUtil.beanToXml(examItemEnd);
		String jkid = "17C55"; // 项目结束
		HXLogUtil.info(logger, "项目结束调用{0},入参{1}", jkid, writeXml);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger, "项目结束结果{0}", result);
		int nowKf = examineeInfoCacheManager.getNowKf(examItemEnd.getSfzmhm());
		//当前项目扣分超过20  科目结束
		if(nowKf > 20) {
			HXLogUtil.info(logger, "当前项目是{0},当前扣分{1}超过20分,进行科目结束", examItemEnd.getKsxm(),nowKf);
			ExamEnd examEnd = this.createExamEndParam(examItemEnd);
			return this.examEnd(examEnd);
		}
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}

	
	/**
	 * 根据项目结束创建科目结束入参
	 * @param examItemEnd
	 */
	private ExamEnd createExamEndParam(ExamItemEnd examItemEnd) throws Exception{
		ExamEnd examEnd = new ExamEnd();
		//复制基本属性
		BeanUtils.copyProperties(examEnd, examItemEnd);
		examEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
		//照片
		String nowZp = examineeInfoCacheManager.getNowZp(examItemEnd.getSfzmhm());
		if(HXCoreUtil.isEmpty(nowZp)) {
			HXLogUtil.info(logger,"当前缓存中无此人照片{1}",examItemEnd.getSfzmhm());
			return null;
		}
		else {
			examEnd.setZp(nowZp);
		}
		int nowKf = examineeInfoCacheManager.getNowKf(examItemEnd.getSfzmhm());
		examEnd.setKscj((100-nowKf)+"");
		return examEnd;
	}
	
	
	/**
	 * 科目结束
	 */
	@ExamProcessLogSaveAnnotation
	public WebServiceResult examEnd(ExamEnd examEnd) throws Exception {
		if(Integer.parseInt(examEnd.getKscj())<0) {
			examEnd.setKscj("0");
		}
		String writeXml = HXCallWebServiceUtil.beanToXml(examEnd);
		String jkid = "17C56"; //科目结束
		String paramInfo = this.getParamNoImage(writeXml);
		HXLogUtil.info(logger,"科目结束调用{0},入参{1}",jkid,paramInfo);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"科目结束调用返回{0}",result);
		//清除当前缓存信息
		examineeInfoCacheManager.removeCache(examEnd.getSfzmhm());
		return HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
	}

	
	/**
	 * 去除参数中的照片
	 * @param param
	 * @return
	 */
	private String getParamNoImage(String param) {
		String result = param;
		if(param.contains("<zp>")&&param.contains("</zp>")) {
			int zpStart = param.indexOf("<zp>");
			int zpEnd = param.indexOf("</zp>");
			String firstStr = param.substring(0,zpStart);
			String endStr = param.substring(zpEnd+5,param.length());
			result = firstStr+"<zp></zp>"+endStr;
		}
		return result;
	}
	
	/**
	 * 模拟创建成功
	 * @return
	 */
	private WebServiceResult createSuccess() {
		WebServiceResult result = new WebServiceResult();
		WebServiceResultHead head = new WebServiceResultHead();
		head.setCode("1");
		head.setMessage("交互中心返回");
		result.setHead(head);
		return result;
	}


	/**
	 * 暂停考试
	 */
	public WebServiceResult stopExam(ExamStop examStop) throws Exception {
		//设置基本信息
		examStop.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		//考场序号
		examStop.setKcxh(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.XZKCXH));
		//考试科目
		examStop.setKskm("2");
		//考试日期
		examStop.setKsrq(HXCoreUtil.getNowDataStr(new Date(), "yyyy-MM-dd"));
		String writeXml = HXCallWebServiceUtil.beanToXml(examStop);
		String jkid = "17C60"; //科目结束
		String paramInfo = this.getParamNoImage(writeXml);
		HXLogUtil.info(logger,"考试暂停{0},入参{1}",jkid,paramInfo);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"考试暂停返回{0}",result);
		return null;
	}

}
