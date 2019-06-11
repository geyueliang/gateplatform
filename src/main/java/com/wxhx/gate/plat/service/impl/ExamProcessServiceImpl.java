package com.wxhx.gate.plat.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.basic_client.common.HXLogUtil;
import com.wxhx.basic_client.config.log.HXLogerFactory;
import com.wxhx.gate.plat.bean.exam.process.ExamEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamItemEnd;
import com.wxhx.gate.plat.bean.exam.process.ExamMark;
import com.wxhx.gate.plat.bean.exam.process.IdentityComparison;
import com.wxhx.gate.plat.bean.exam.process.ProcessBase;
import com.wxhx.gate.plat.bean.exam.process.ProcessImage;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.FunctionMapper;
import com.wxhx.gate.plat.dao.KsgcMapper;
import com.wxhx.gate.plat.dao.KsyxxMapper;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.KszpMapper;
import com.wxhx.gate.plat.dao.entity.Ksgc;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.dao.entity.Kszp;
import com.wxhx.gate.plat.init.InitKSKFDM;
import com.wxhx.gate.plat.service.IExamProcessService;
import com.wxhx.gate.plat.service.bean.WebServiceResult;
import com.wxhx.gate.plat.service.bean.WebServiceResultHead;
import com.wxhx.gate.plat.util.GatePlatUtil;
import com.wxhx.gate.plat.util.HXCallWebServiceUtil;

@Service
public class ExamProcessServiceImpl implements IExamProcessService{

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
	
	public String idCheck(IdentityComparison comparison) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(comparison);
		String jkid = "17C51"; //身份比對
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.writeWebService(jkid, writeXml);
	}

	public String examMarkHappen(ExamMark examMark) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(examMark);
		String jkid = "17C53"; //考试扣分
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.writeWebService(jkid, writeXml);
	}

	public String uploadImage(ProcessImage processImage) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(processImage);
		String jkid = "17C54"; //图片上传
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.writeWebService(jkid, writeXml);
	}

	public String examItemEnd(ExamItemEnd examItemEnd) throws Exception {
		String result = "";
		//是最后的项目 调用科目考试结束
		if(HXCoreUtil.isEquals("ckm2zd01", examItemEnd.getKsxm())) {
			//调用科目考试结束
			ExamEnd examEnd = this.createExamEnd(examItemEnd);
			result = this.examEnd(examEnd);
		}
		else {
			String writeXml = HXCallWebServiceUtil.beanToXml(examItemEnd);
			String jkid = "17C55"; //项目结束
			HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"调用{0},入参{1}",jkid,writeXml);
			result =  HXCallWebServiceUtil.writeWebService(jkid, writeXml);
		}
		return result;
	}

	public String examEnd(ExamEnd examEnd) throws Exception {
		String writeXml = HXCallWebServiceUtil.beanToXml(examEnd);
		String jkid = "17C56"; //科目结束
		HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"调用{0},入参{1}",jkid,writeXml);
		return HXCallWebServiceUtil.writeWebService(jkid, writeXml);
	}

	/**
	 * 统一处理过程处理
	 * @throws Exception 
	 */
	public String doProcess(String content) throws Exception {
		String result = "";
		String[] processArray = null;
		//包含照片的 不需要解码
		if(content.contains(",2,")) {
			processArray = content.split(",");
		}
		else {
			//获取处理参数
			processArray = this.decodeInfo(content);
		}
		if(processArray==null) {
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
			break;
		//考试扣分
		case 1:
			result = this.examMarkHappen((ExamMark) getCallBeanFromArray(processArray,typeId));
			break;
		//图片上传
		case 2:
			result = this.uploadImage((ProcessImage) getCallBeanFromArray(processArray,typeId));
			break;
		//项目结束
		case 3:
			result = this.examItemEnd((ExamItemEnd) getCallBeanFromArray(processArray,typeId));
			break;
		default:
			break;
		}
		//解析返回对象
		WebServiceResult serviceResult =  HXCallWebServiceUtil.xmlToBean(result, WebServiceResult.class);
		if(serviceResult!=null&&serviceResult.getHead()!=null) {
			HXLogUtil.info(HXLogerFactory.getLogger("gate_plate"),"返回内容",serviceResult);
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
				identityComparison.setKssj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
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
			examMark.setKfsj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
			t = (T) examMark;
			break;
		//图片上传
		case 2:
			ProcessImage processImage = new ProcessImage();
			//照片时间
			processImage.setZpsj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
			//照片
			processImage.setZp(array[9]);
			//车速
			processImage.setCs(array[8]);
			//考试项目
			processImage.setKsxm(array[6]);
			t = (T) processImage;
			break;
		//项目结束
		case 3:
			ExamItemEnd examItemEnd = new ExamItemEnd();
			//设备序列号
			examItemEnd.setSbxh(array[7]);
			//结束时间
			examItemEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
			//操作类型
			String czlx = HXCoreUtil.isEquals("true",array[8])?"1":"0"; 
			examItemEnd.setCzlx(czlx);
			//考试项目
			examItemEnd.setKsxm(array[6]);
			t = (T) examItemEnd;
			break;
		default:
			break;
		}
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
		return t;
	}
	
	
	/**
	 * 根据项目结束 创建科目结束参数
	 * @param examItemEnd
	 * @return
	 */
	private ExamEnd createExamEnd(ExamItemEnd examItemEnd) {
		ExamEnd examEnd = new ExamEnd();
		try {
			//复制基本属性
			BeanUtils.copyProperties(examEnd, examItemEnd);
			//照片
			Kszp kszp = kszpMapper.getKszpByCarNo(examItemEnd.getSfzmhm(), "BD");
			examEnd.setZp(kszp.getZp());
			//结束时间
			examEnd.setJssj(GatePlatUtil.getFormatDate("yyyy-MM-dd hh:mm:ss", new Date()));
			/**
			 * 考试成绩
			 */
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
			int kfhj = 0;
			if(kfxms!=null&&kfxms.size()>0) {
				for(Ksgc kfxm:kfxms) {
					kfhj = kfhj+InitKSKFDM.getKf(kfxm.getCs2());
				}
			}
			//考试成绩
			examEnd.setKscj((100-kfhj)+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
