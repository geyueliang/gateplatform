package com.wxhx.gate.plat.dao.entity;

import java.util.Date;

public class Kszp {
	private String id;	//自动增长
	private String rid;	//考试生成的一个ID
	private String lsh;	//
	private String bckscs;
	private String sfzmhm;
	private String zplx;
	private String zp;
	private Date gxsj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getBckscs() {
		return bckscs;
	}
	public void setBckscs(String bckscs) {
		this.bckscs = bckscs;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getZplx() {
		return zplx;
	}
	public void setZplx(String zplx) {
		this.zplx = zplx;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	public Date getGxsj() {
		return gxsj;
	}
	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}
}
