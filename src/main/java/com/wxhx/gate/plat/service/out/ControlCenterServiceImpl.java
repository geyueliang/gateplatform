package com.wxhx.gate.plat.service.out;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxhx.basic_client.common.HXCoreUtil;
import com.wxhx.gate.plat.bean.out.ExaminationInfo;
import com.wxhx.gate.plat.bean.out.RecordInfo;
import com.wxhx.gate.plat.constent.EvnVarConstentInfo;
import com.wxhx.gate.plat.dao.KsclMapper;
import com.wxhx.gate.plat.dao.KsyyxxMapper;
import com.wxhx.gate.plat.dao.KszpMapper;
import com.wxhx.gate.plat.dao.entity.Ksyyxx;
import com.wxhx.gate.plat.dao.entity.Kszp;

/**
 * 
 * @author coyi
 *
 */
@Service
public class ControlCenterServiceImpl implements IControlCenterService{
	
	@Autowired
	private KsyyxxMapper ksyyxxMapper;
	
	@Autowired
	private KszpMapper kszpMapper;
	
	@Autowired
	private KsclMapper ksclMapper;
	
	public int insertSortInfo(ExaminationInfo examinationInfo) {
		if(examinationInfo==null) {
			return 0;
		}
		//将下载下来的预约信息转换成对应的预约信息
		Ksyyxx ksyyxx = new Ksyyxx();
		copyInfo(examinationInfo,ksyyxx);
		//插入排考信息
		return ksyyxxMapper.insertSelective(ksyyxx);
	}
	
	public ExaminationInfo getExaminationInfo(ExaminationInfo examinationInfo) {
		if(examinationInfo==null) {
			return null;
		}
		//将下载下来的预约信息转换成对应的预约信息
		Ksyyxx ksyyxx = new Ksyyxx();
		copyInfo(examinationInfo,ksyyxx);
		Ksyyxx result = ksyyxxMapper.selectByIdNum(ksyyxx.getSfzmhm());
		
		if(result != null) {
			ExaminationInfo ksyyInfo = new ExaminationInfo();
			ksyyInfo.setLsh(result.getLsh());
			ksyyInfo.setKchp(result.getCtlbit1());
			return ksyyInfo;
		}
		return null;
	}
	
	/**
	 * 插入证件照片
	 */
	public int insertPhotoInfo(ExaminationInfo examinationInfo) {
		int result = 0;
		if(examinationInfo==null) {
			return result;
		}
		Kszp kszp = new Kszp();
		kszp.setRid(getRandomCharts());
		kszp.setLsh(examinationInfo.getLsh());
		kszp.setBckscs("0");
		kszp.setZplx("XZ");
		kszp.setSfzmhm(examinationInfo.getSfzmhm());
		kszp.setZp(examinationInfo.getZp());
		kszp.setGxsj(new Date());
		//插入报名照片
		int zjRes = kszpMapper.insertSelective(kszp);
		if(zjRes == 1) {
			//插入采集照片
			kszp.setZplx("BD");
			result = kszpMapper.insertSelective(kszp);
			return result;
		}
		return result;
	}
	
	/**
	 * 更新采集照片
	 */
	public int updatePhotoInfo(RecordInfo recordInfo) {
		if(recordInfo==null) {
			return 0;
		}
		Kszp kszp = new Kszp();
		kszp.setRid(getRandomCharts());
		kszp.setSfzmhm(recordInfo.getIdNum());
		kszp.setZp(recordInfo.getScenePhoto());
		kszp.setZplx("BD");
		return kszpMapper.updatePhoto(kszp);
	}
	
	
	/**
	 * 将从平台下载的预约信息下载到数据库中
	 * @param source
	 * @param target
	 */
	private void copyInfo(ExaminationInfo source,Ksyyxx target) {
		//顺序号
		target.setLsh(source.getLsh());
		//考试科目
		target.setKskm(source.getKskm());
		//准考证编号
		target.setZkzmbh(source.getZkzmbh());
		//身份证编号
		target.setSfzmhm(source.getSfzmhm());
		//身份证名称
		target.setSfzmmc("A");
		//姓名
		target.setXm(source.getXm());
		//考试原因
		target.setKsyy(source.getKsyy());
		//设置预约日期
		target.setYyrq(new Date());
		//设置预考日期
		target.setYkrq(new Date());
		//考试车型
		target.setKscx(source.getKscx());
		//考试地点
		target.setKsdd(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.KSDD));
		//考试次数
		target.setKscc(source.getYycs()+"");
		//经办人
		target.setJbr("互联网");
		//管理部门
		target.setGlbm(EvnVarConstentInfo.getSystemInfo(EvnVarConstentInfo.XZGLBM));
		//代理人
		target.setDlr(source.getDlr());
		//考试员
		target.setKsy1(source.getKsy1());
		target.setKsy2(source.getKsy2());
		//当天考试测试
		target.setBcyykscs(0 + "");
		//考试项目
		target.setKsxm(source.getKsxm());
		//是否夜考
		target.setLxxh(source.getKssxh()+"");
		//考试车辆编号
		target.setCtlbit1(source.getKcbh());
		//考试顺序号
		target.setCtlbit3(source.getKssxh()+"");
		
	}
	
	/**
	 * 考试照片RID
	 * @return
	 */
	public String getRandomCharts() {
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
	            'K', 'L', 'M', 'N', 'O', 'P','Q', 'R', 'S', 'T', 'U', 'V',
	            'W', 'X', 'Y', 'Z','0','1','2','3','4','5','6','7','8','9'};
		
	    StringBuffer chs = new StringBuffer();
	    chs.append(HXCoreUtil.getNowDataStr(new Date(),"yyyyMMddHHmmss"));
	    
	    for (int i = 0; i < 6; i++) {
	        int index;
	        index = (int) (Math.random() * (letters.length));
	        chs = chs.append(letters[index]);
	    }
	    
	    return chs.toString();
	}
	
	
	/**
	 * 根据考车车牌转换编号
	 * @return
	 */
	public String getKchp(String kchp) {
		return ksclMapper.selectByKchp(kchp);
	}
	

}
