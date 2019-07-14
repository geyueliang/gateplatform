package com.wxhx.gate.plat.service.impl;

import java.io.UnsupportedEncodingException;
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
import com.wxhx.basic_client.config.thread.HXThreadManager;
import com.wxhx.gate.plat.aop.annotation.ExamProcessLogSaveAnnotation;
import com.wxhx.gate.plat.bean.exam.process.AuthenticationResult;
import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ItemBegin;
import com.wxhx.gate.plat.bean.exam.process.ProcessBase;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.bean.exam.process.ReadVideo;
import com.wxhx.gate.plat.bean.exam.process.WirteVideo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.FunctionMapper;
import com.wxhx.gate.plat.dao.KsgcMapper;
import com.wxhx.gate.plat.dao.KsyxxMapper;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.KszpMapper;
import com.wxhx.gate.plat.dao.entity.Kcsb;
import com.wxhx.gate.plat.dao.entity.Ksgc;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.dao.entity.Kszp;
import com.wxhx.gate.plat.init.InitKSKFDM;
import com.wxhx.gate.plat.init.KcsbInit;
import com.wxhx.gate.plat.service.IExamProcessService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.bean.WebServiceResultHead;
import com.wxhx.gate.plat.service.impl.thread.DealEndThread;
import com.wxhx.gate.plat.util.GatePlatUtil;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

@Service
public class ExamProcessServiceImpl implements IExamProcessService{
	
	private static Logger logger = LoggerFactory.getLogger("examLog");

	
	@Autowired
	private HXThreadManager hxThreadManager;

	@Autowired
	private FunctionMapper functionMapper;
	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	@Autowired
	private KsyxxMapper ksyxxMappper;
	
	@Autowired
	private KsgcMapper ksgcMapper;
	
	@Autowired
	private KszpMapper kszpMapper;
	
	/**
	 * 身份验证
	 */
	@ExamProcessLogSaveAnnotation
	public String idCheck(IdentityComparison comparison) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(comparison);
		String jkid = "17C51"; //身份比對
		HXLogUtil.info(logger,"身份对比调用{0},入参{1}",jkid,HXCallWebServiceUtil.enCodeStr(writeXml));
//		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		/**
		 * 身份认证 默认成功 不用调用精英的开始 直接项目开始
		 */
		String result = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><head><code>1</code><message>默认成功</message><rownum>1</rownum></head></root>";
		HXLogUtil.info(logger,"身份对比返回结果{0}",result);
		//验证成功开始第一个项目
		if(HXCoreUtil.isEquals("xmks", comparison.getAddressType())) {
			ItemBegin itemBegin = this.getItemBegin(comparison.getSfzmhm(), null, comparison);
			this.itemBegin(itemBegin);
		}
		return result;
	}

	
	/**
	 * 项目开始
	 */
	@ExamProcessLogSaveAnnotation
	public String itemBegin(ItemBegin itemBegin) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(itemBegin);
		String jkid = "17C52";	//项目开始接口
		HXLogUtil.info(logger,"项目开始调用{0},入参{1}",jkid,writeXml);
		String result =  HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"项目开始调用返回{0}",result);
		return result;
	}
	
	/**
	 * 发生扣分
	 */
	@ExamProcessLogSaveAnnotation
	public String examMarkHappen(ExamMark examMark,ExamItemEnd examItemEnd) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(examMark);
		String jkid = "17C53"; //考试扣分
		HXLogUtil.info(logger,"发生扣分调用{0},入参{1}",jkid,writeXml);
		//判断当前的分数加上已经扣的分数 是否达到结束考试要求
//		int nowKf = InitKSKFDM.getKf(examMark.getKfxm());
		//获取当前已经扣除分数
//		int currentSum = this.getKskf(examMark.getSfzmhm());
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		/**
		 *  超过20分 考试不及格 调用项目结束和 科目结束进行处理
		 */
//		if((nowKf+currentSum)>20) {
//			HXLogUtil.info(logger,"=============扣分大于20分执行 结束逻辑============");
			//复制基本属性
//			BeanUtils.copyProperties(examItemEnd, examMark);
//			ExamEnd examEnd = this.createExamEnd(examItemEnd,(nowKf+currentSum));
//			DealEndThread dealEndThread = new DealEndThread(this, examItemEnd, examEnd);
//			hxThreadManager.execThread(dealEndThread);
//		}
		return result;
	}

	@ExamProcessLogSaveAnnotation
	public String uploadImage(ProcessImage processImage) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(processImage);
		String jkid = "17C54"; //图片上传
		HXLogUtil.info(logger,"上传图片调用{0},入参{1}",jkid,writeXml);
		String result =  HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"上传图片调用返回结果{0}",result);
		return result;
	}

	/**
	 * 项目结束
	 */
	@ExamProcessLogSaveAnnotation
	public String examItemEnd(ExamItemEnd examItemEnd,boolean isNormal) throws Exception {
		String result = "";
		//项目结束地点的结束
		if (HXCoreUtil.isEquals("xmjs", examItemEnd.getAddressType()) || isNormal) {
			// 是最后的项目 调用科目考试结束
			if (HXCoreUtil.isEquals("20500", examItemEnd.getKsxm())) {
				HXLogUtil.info(logger, "最后一个项目结束 直接科目结束");
				// 调用科目考试结束
				ExamEnd examEnd = this.createExamEnd(examItemEnd, 0);
//				HXLogUtil.info(logger,"开始科目结束,项目入参{0},科目入参{1}",examItemEnd,examEnd);
				result = this.examEnd(examEnd);
			} else {
				examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
				String writeXml = HXCallWebServiceUtil.beanToXml(examItemEnd);
				String jkid = "17C55"; // 项目结束
				HXLogUtil.info(logger, "项目结束调用{0},入参{1}", jkid, writeXml);
				result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
				HXLogUtil.info(logger, "项目结束结果{0}", result);
				// 判断当前扣分是否超过20 超过20分 结束考试
				int kfhj = this.getKskf(examItemEnd.getSfzmhm());
				if (kfhj > 20) {
					HXLogUtil.info(logger, "项目结束后分数超过 20 分 科目结束==============");
					ExamEnd examEnd = this.createExamEnd(examItemEnd, kfhj);
					String endStr = this.examEnd(examEnd);
					HXLogUtil.info(logger, "项目结束中进行科目结束 结果{0}", endStr);
					return result;
				}
			}
		}
		//项目结束调用下一个项目的开始
		if(HXCoreUtil.isEquals("xmks", examItemEnd.getAddressType())) {
			ItemBegin itemBegin = this.getItemBegin(examItemEnd.getSfzmhm(), examItemEnd, null);
			HXLogUtil.info(logger,"#############当前{0}项目结束，下一个项目{1}开始##################",examItemEnd.getKsxm(),itemBegin.getKsxm());
			if(itemBegin!=null) {
				this.itemBegin(itemBegin);
			}
		}
		return result;
	}
	
	@ExamProcessLogSaveAnnotation
	public String examEnd(ExamEnd examEnd) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(examEnd);
		String jkid = "17C56"; //科目结束
		HXLogUtil.info(logger,"科目结束调用{0},入参{1}",jkid,writeXml);
		String result = HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		HXLogUtil.info(logger,"科目结束调用返回{0}",result);
		return result;
	}
	
	@ExamProcessLogSaveAnnotation
	public String writeVideo(WirteVideo wirteVideo) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(wirteVideo);
		String jkid = "17E14"; //视频认证发启（写入）
		HXLogUtil.info(logger,"发起视频认证调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.writeWebService(jkid, writeXml);
	} 
	
	@ExamProcessLogSaveAnnotation
	public String readVideo(ReadVideo readVideo) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(readVideo);
		String jkid = "17E15"; //读取视频认证结果
		HXLogUtil.info(logger,"读取视频认证结果调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.queryWebService(jkid, writeXml);
	} 
	
	/**
	 * 统一处理过程处理
	 * @throws Exception 
	 */
	public String doProcess(String content) throws Exception {
		String result = "";
		String[] processArray = content.split(",");
		/**
		 * 
			去除加密
		if(content.contains(",2,")||content.contains(",5,")||content.contains(",6,")) {
			processArray = content.split(",");
		}
		else {
			//获取处理参数
			processArray = this.decodeInfo(content);
		}
		 */
		HXLogUtil.info(logger,"===========车载调用交互中心的入参:{0}==============",Arrays.asList(processArray));
		if(processArray==null || processArray.length<2) {
			WebServiceResultHead head = new WebServiceResultHead();
			head.setCode("1");
			head.setMessage("success");
			result = HXCoreUtil.getJsonString(head);
			return result;
		}
		int typeId = Integer.parseInt(processArray[1]);
		switch (typeId) {
		//身份验证
		case 0:
			result = this.idCheck((IdentityComparison) getCallBeanFromArray(processArray,typeId));
			HXLogUtil.debug(logger,"身份验证返回{0}",result);
			/*
			 * WebServiceResultHead head = new WebServiceResultHead(); head.setCode("1");
			 * head.setMessage("success"); result = HXCoreUtil.getJsonString(head); return
			 * result;
			 */
			break;
		//考试扣分
		case 1:
			//扣分内容
			ExamMark examMark = (ExamMark) getCallBeanFromArray(processArray,typeId);
			/**
			 * 项目内容
			 */
			ExamItemEnd examItemEnd = new ExamItemEnd();
			Kcsb kcsb = KcsbInit.getKcsb(processArray[7]);
			examItemEnd.setSbxh(kcsb!=null?kcsb.getSbbh():processArray[7]);  //设备序列号
			examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date())); //结束时间
//			String czlx = HXCoreUtil.isEquals("true",processArray[8])?"1":"0";  //操作类型
			String czlx = "1";
			examItemEnd.setCzlx(czlx); 
			examItemEnd.setKsxm(processArray[6]); //考试项目
			//开始调用扣分
			result = this.examMarkHappen(examMark,examItemEnd);
			HXLogUtil.debug(logger,"考试扣分返回{0}",result);
			break;
		//图片上传
		case 2:
			result = this.uploadImage((ProcessImage) getCallBeanFromArray(processArray,typeId));
			HXLogUtil.debug(logger,"图片上传返回{0}",result);
			break;
		//项目结束
		case 3:
			result = this.examItemEnd((ExamItemEnd) getCallBeanFromArray(processArray,typeId),false);
			HXLogUtil.debug(logger,"项目结束返回{0}",result);
			break;
		//视频认证发启（写入）	
		case 5:
			result = this.writeVideo((WirteVideo) getCallBeanFromArray(processArray,typeId));
			HXLogUtil.debug(logger,"视频认证发启{0}",result);
			break;
		//读取视频认证结果
		case 6:
			result = this.readVideo((ReadVideo) getCallBeanFromArray(processArray,typeId));
			HXLogUtil.debug(logger,"读取视频认证结果{0}",result);
			break;
		default:
			break;
		}
		//解析返回对象
		WebServiceResult serviceResult =  HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
		HXLogUtil.debug(logger,"process处理 typeId{0}返回的json{1}",typeId,serviceResult);
		if(serviceResult!=null&&serviceResult.getHead()!=null) {
			//视频认证结果返回
			WebServiceResultHead head = serviceResult.getHead();
			int resultCode = Integer.parseInt(head.getCode());
			if(typeId==6) {
				WebServiceResult<AuthenticationResult> authenResult = HXCallWebServiceUtil.xmlToBean(result, AuthenticationResult.class);
				//读取认证结果
				if(authenResult.getHead()!=null && Integer.parseInt(authenResult.getHead().getCode())==1) {
					if(authenResult.getBodyContent().getContent()!=null && authenResult.getBodyContent().getContent().size()>0) {
						AuthenticationResult  authenticationResult = (AuthenticationResult) authenResult.getBodyContent().getContent().get(0);
						int rzjg = Integer.parseInt(authenticationResult.getRzjg()!=null?authenticationResult.getRzjg():"0");
						if(rzjg==1 || rzjg ==3) {
							head.setCode("1"); //成功
						}
						else if(rzjg==0) {
							head.setCode("2"); //等待
						}
						else {
							head.setCode("0"); //失败
						}
								
					}
				}
			}
			else {
				if(resultCode==1) {
					head.setCode("1"); //成功
				}
				else {
					head.setCode("0"); //成功
				}
			}
			result = HXCoreUtil.getJsonString(serviceResult.getHead());
		}
		else {
			WebServiceResultHead head = new WebServiceResultHead();
			head.setCode("0");
			head.setMessage("error");
			result = HXCoreUtil.getJsonString(serviceResult.getHead());
		}
		return result;
	}
	
	
	/**
	 * 调用数据库中的函数解析加密数据
	 * @param content
	 * @return
	 */
	private String[] decodeInfo(String content) {
		String[] result = null;
		try {
			content = content.substring(1, content.length());
			String decodeStr = functionMapper.decodeStr(content);
			if(!HXCoreUtil.isEmpty(decodeStr)) {
				result = decodeStr.split(",");
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}
	
	
	/**
	 *  根据当前类型 获取相应调用的数据
	 * @param <T>
	 * @param array
	 * @return
	 */
	private <T extends ProcessBase> T getCallBeanFromArray(String[] array,int tyepId) {
		//考生身份证号码
		String sfzmhm = array[3];
		//根据查询当前考试员的身份证信息
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(sfzmhm);
		//根据类型创建不同bean
		T t = null;
		switch (tyepId) {
		//身份验证
		case 0:
			IdentityComparison	identityComparison = new IdentityComparison();
			//结束地点
			identityComparison.setAddressType(array[array.length-1]);
			//考试系统编号
			identityComparison.setKsxtbh(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSXTBH));
			if(ksyyxx!=null) {
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
				//开始时间
				identityComparison.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
				t = (T) identityComparison;
			}
			break;
		//考试扣分
		case 1:
			ExamMark examMark = new ExamMark();
			//考试项目
			examMark.setKsxm(array[6]);
			//扣分项目
			examMark.setKfxm(array[8]);
			//扣分时间
			examMark.setKfsj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			t = (T) examMark;
			break;
		//图片上传
		case 2:
			ProcessImage processImage = new ProcessImage();
			//照片时间
			processImage.setZpsj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			//照片
			try {
				processImage.setZp(URLEncoder.encode(array[9], "utf-8"));
			} catch (UnsupportedEncodingException e) {
				processImage.setZp(array[9]);
			}
			//车速
			processImage.setCs(array[8]);
			//考试项目
			processImage.setKsxm(array[6]);
			t = (T) processImage;
			break;
		//项目结束
		case 3:
			ExamItemEnd examItemEnd = new ExamItemEnd();
			//结束地点
			examItemEnd.setAddressType(array[array.length-1]);
			//设备序列号
			Kcsb kcsb = KcsbInit.getKcsb(array[7]);
			examItemEnd.setSbxh(kcsb!=null?kcsb.getSbbh():array[7]);  //设备序列号
			//结束时间
			examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			//操作类型
//			String czlx = HXCoreUtil.isEquals("true",array[8])?"1":"0"; 
			String czlx = "1"; 
			examItemEnd.setCzlx(czlx);
			//考试项目
			examItemEnd.setKsxm(array[6]);
			t = (T) examItemEnd;
			break;
		//视频认证发启（写入）	
		case 5:
			WirteVideo wirteVideo = new WirteVideo();
			wirteVideo.setKchp(EvnVarConstentInfo.getCarInfo(array[4]));
			wirteVideo.setKsxtbh(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSXTBH));
			t = (T) wirteVideo;
			break;
		//读取视频认证结果
		case 6:
			ReadVideo readVideo = new ReadVideo();
			readVideo.setKchp(EvnVarConstentInfo.getCarInfo(array[4]));
			t = (T) readVideo;
			break;
		default:
			break;
		}
		if(t!=null) {
			//通用信息设置
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
	 * 根据项目结束 创建科目结束参数
	 * @param examItemEnd
	 * @return
	 */
	private ExamEnd createExamEnd(ExamItemEnd examItemEnd,int kfhj) {
		ExamEnd examEnd = new ExamEnd();
		try {
			//复制基本属性
			BeanUtils.copyProperties(examEnd, examItemEnd);
			//照片
			Kszp kszp = kszpMapper.getKszpByCarNo(examItemEnd.getSfzmhm(), "CZ");
			if(kszp!=null) {
				examEnd.setZp(kszp.getZp());
			}
			else {
				kszp = kszpMapper.getKszpByCarNo(examItemEnd.getSfzmhm(), "BD");
				if(kszp!=null) {
					examEnd.setZp(kszp.getZp());
				}
			}
			examEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			/**
			 * 考试成绩
			 */
			if(kfhj==0) {
				//判断当前是第几次考试
				int kscs = ksgcMapper.getNowKscs(examItemEnd.getSfzmhm());
				List<Ksgc> kfxms = null;
				//第一次考试
				if(kscs<2) {
					kfxms = ksgcMapper.getKfxm(examItemEnd.getSfzmhm(), 1);
				}
				//第二次考试
				else {
					kfxms = ksgcMapper.getKfxm(examItemEnd.getSfzmhm(), 2);
				}
				//计算扣分
				int kfhjj = 0;
				if(kfxms!=null&&kfxms.size()>0) {
					for(Ksgc kfxm:kfxms) {
						kfhjj = kfhjj+InitKSKFDM.getKf(kfxm.getCs2());
					}
				}
				//考试成绩
				examEnd.setKscj((100-kfhjj)+"");
			}
			else {
				//考试成绩
				examEnd.setKscj((100-kfhj)+"");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examEnd;
	}
	
	
	/**
	 * 得到当前项目结束时候的扣分总数
	 * @param examItemEnd
	 * @return
	 */
	private int getKskf(String sfzmhm) {
		//判断当前是第几次考试
		int kscs = ksgcMapper.getNowKscs(sfzmhm);
		List<Ksgc> kfxms = null;
		//第一次考试
		if(kscs<2) {
			kfxms = ksgcMapper.getKfxm(sfzmhm, 1);
		}
		//第二次考试
		else {
			kfxms = ksgcMapper.getKfxm(sfzmhm, 2);
		}
		//计算扣分
		int kfhj = 0;
		if(kfxms!=null&&kfxms.size()>0) {
			for(Ksgc kfxm:kfxms) {
				kfhj = kfhj+InitKSKFDM.getKf(kfxm.getCs2());
			}
		}
		return kfhj;
	}
	
	
	
	/**
	 * 根据当前考生的身份证信息 和 结束的项目 得到开始项目的入参
	 * @param sfzmhm
	 * @param examItemEnd
	 * @return
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 */
	private ItemBegin getItemBegin(String sfzmhm,ExamItemEnd examItemEnd,IdentityComparison comparison) throws Exception {
		ItemBegin itemBegin = null;
		Ksyyxx ksyyxx = ksyyxxMapper.selectByIdNum(sfzmhm);
		if(ksyyxx!=null&&!HXCoreUtil.isEmpty(ksyyxx.getKsxm())) {
			//当前路线序号
			String lxxh = ksyyxx.getLxxh();
			//结束项目是空 身份验证信息不空	当前为第一项目
			if(examItemEnd==null&&comparison!=null) {
				itemBegin = new ItemBegin();
				//复制基本属性
				BeanUtils.copyProperties(itemBegin, comparison);
				String ksxm = KcsbInit.getNextBeginItem(lxxh, null);
				itemBegin.setKsxm(ksxm);	//考试项目
				String sbxh = KcsbInit.getNextSbxh(lxxh, null); //设备序号
				String sbbh = KcsbInit.getSbbhBySbxh(sbxh); 
				itemBegin.setSbxh(sbbh);
//				itemBegin.setSbxh(sbxh); 		
				itemBegin.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
			}
			
			//考试项目结束
			if(examItemEnd!=null&&comparison==null) {
				//如果是最后一个项目结束 不用操作
				if(HXCoreUtil.isEquals(examItemEnd.getKsxm(), "20500")) {
					return null;
				}
				else {
					String ksxm = KcsbInit.getNextBeginItem(lxxh, examItemEnd.getKsxm());
					if(HXCoreUtil.isEmpty(ksxm)) {
						return null;
					}
					itemBegin = new ItemBegin();
					//复制基本属性
					BeanUtils.copyProperties(itemBegin, examItemEnd);
					itemBegin.setKsxm(ksxm);	//考试项目
					String sbxh = KcsbInit.getNextSbxh(lxxh, null); //设备序号
					String sbbh = KcsbInit.getSbbhBySbxh(sbxh); 
					itemBegin.setSbxh(sbbh);
					itemBegin.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd HH:mm:ss", new Date()));
				}
				
			}
			
		}
		return itemBegin;
	}

}
