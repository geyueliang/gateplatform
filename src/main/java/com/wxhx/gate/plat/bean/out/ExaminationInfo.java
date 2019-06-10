package com.wxhx.gate.plat.bean.out;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wxhx.basic_client.common.HXCoreUtil;

/**
 * 考生信息
 * @author coyi
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="drvexam")
public class ExaminationInfo{
	@XmlElement(name="lsh")
	private String lsh;	//流水号
	
	@XmlElement(name="kskm")
	private String kskm;	//考试科目
	
	@XmlElement(name="zkzmbh")
	private String zkzmbh;	//准考证明编号
	
	@XmlElement(name="sfzmhm")
	private String sfzmhm;	//身份证明号码
	
	@XmlElement(name="xm")
	private String xm;	//姓名
	
	@XmlElement(name="xb")
	private String xb;	//性别
	
	@XmlElement(name="zp")
	private String zp;	//照片
	
	@XmlElement(name="ywlx")
	private String ywlx;	//业务类型**
	
	@XmlElement(name="kssxh")
	private int kssxh;	//考试顺序号
	
	@XmlElement(name="ksyy")
	private String ksyy;	//考试原因
	
	@XmlElement(name="kscx")
	private String kscx;	//考试车型
	
	@XmlElement(name="kscx")
	private String ksdd;	//考试地点
	
	@XmlElement(name="bkcs")
	private String bkcs;	//报考次数
	
	@XmlElement(name="dlr")
	private String dlr;	//代理人（驾校）
	
	@XmlElement(name="yycs")
	private int yycs;	//预约次数
	
	@XmlElement(name="ksy1")
	private String ksy1;	//考试员1
	
	@XmlElement(name="ksy2")
	private String ksy2;	//考试员2
	
	@XmlElement(name="ksxm")
	private String ksxm;	//考试项目
	
	@XmlElement(name="ykxm")
	private String ykxm;	//已考项目
	
	@XmlElement(name="kssxxh")
	private String kssxxh;	//考试顺序号
	
	@XmlElement(name="ykms")
	private String ykms;	//夜考模式
	
	@XmlElement(name="qjcj")
	private String qjcj;	//开始成绩
	
	@XmlElement(name="kskssj")
	private String kskssj;	//考试开始时间
	
	@XmlElement(name="skysfzmhm")
	private String skysfzmhm;	//考试员身份证明编码
	
	@XmlElement(name="Ksy2sfzmhm")
	private String Ksy2sfzmhm;	//考试员身份证明编码2
	
	@XmlElement(name="dqxm")
	private String dqxm;	//当前项目
	
	@XmlElement(name="mkxm")
	private String mkxm;	//免考项目
	
	@XmlElement(name="kchp")
	private String kchp;	//考车号牌
	
	@XmlElement(name="kcbh")
	private String kcbh;	//考车编号
	
	
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getKskm() {
		return kskm;
	}
	public void setKskm(String kskm) {
		this.kskm = kskm;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public int getKssxh() {
		return kssxh;
	}
	public void setKssxh(int kssxh) {
		this.kssxh = kssxh;
	}
	public String getKsyy() {
		return ksyy;
	}
	public void setKsyy(String ksyy) {
		this.ksyy = ksyy;
	}
	public String getKscx() {
		return kscx;
	}
	public void setKscx(String kscx) {
		this.kscx = kscx;
	}
	public String getDlr() {
		return dlr;
	}
	public void setDlr(String dlr) {
		this.dlr = dlr;
	}
	public int getYycs() {
		return yycs;
	}
	public void setYycs(int yycs) {
		this.yycs = yycs;
	}
	public String getKsy1() {
		return ksy1;
	}
	public void setKsy1(String ksy1) {
		this.ksy1 = ksy1;
	}
	public String getKsy2() {
		return ksy2;
	}
	public void setKsy2(String ksy2) {
		this.ksy2 = ksy2;
	}
	public String getKsxm() {
		return ksxm;
	}
	public void setKsxm(String ksxm) {
		this.ksxm = ksxm;
	}
	public String getKchp() {
		return kchp;
	}
	public void setKchp(String kchp) {
		this.kchp = kchp;
	}
	public String getKcbh() {
		return kcbh;
	}
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	public String getZkzmbh() {
		return zkzmbh;
	}
	public void setZkzmbh(String zkzmbh) {
		this.zkzmbh = zkzmbh;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	
	public String getKsdd() {
		return ksdd;
	}
	public void setKsdd(String ksdd) {
		this.ksdd = ksdd;
	}
	public String getBkcs() {
		return bkcs;
	}
	public void setBkcs(String bkcs) {
		this.bkcs = bkcs;
	}
	public String getYkxm() {
		return ykxm;
	}
	public void setYkxm(String ykxm) {
		this.ykxm = ykxm;
	}
	public String getKssxxh() {
		return kssxxh;
	}
	public void setKssxxh(String kssxxh) {
		this.kssxxh = kssxxh;
	}
	public String getYkms() {
		return ykms;
	}
	public void setYkms(String ykms) {
		this.ykms = ykms;
	}
	public String getQjcj() {
		return qjcj;
	}
	public void setQjcj(String qjcj) {
		this.qjcj = qjcj;
	}
	public String getKskssj() {
		return kskssj;
	}
	public void setKskssj(String kskssj) {
		this.kskssj = kskssj;
	}
	public String getSkysfzmhm() {
		return skysfzmhm;
	}
	public void setSkysfzmhm(String skysfzmhm) {
		this.skysfzmhm = skysfzmhm;
	}
	public String getKsy2sfzmhm() {
		return Ksy2sfzmhm;
	}
	public void setKsy2sfzmhm(String ksy2sfzmhm) {
		Ksy2sfzmhm = ksy2sfzmhm;
	}
	public String getDqxm() {
		return dqxm;
	}
	public void setDqxm(String dqxm) {
		this.dqxm = dqxm;
	}
	public String getMkxm() {
		return mkxm;
	}
	public void setMkxm(String mkxm) {
		this.mkxm = mkxm;
	}
	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
	
}
