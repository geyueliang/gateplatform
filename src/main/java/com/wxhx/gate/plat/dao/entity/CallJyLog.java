package com.wxhx.gate.plat.dao.entity;

import java.util.Date;

import com.wxhx.basic_client.common.HXCoreUtil;

public class CallJyLog {
    private String id;

    private String sfzmhm;

    private String jkid;

    private String jkms;

    private String dyjg;

    private String jgnr;

    private String dyrq;

    private Date dysj;
    
    private String logType;
    

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm == null ? null : sfzmhm.trim();
    }

    public String getJkid() {
        return jkid;
    }

    public void setJkid(String jkid) {
        this.jkid = jkid == null ? null : jkid.trim();
    }

    public String getJkms() {
        return jkms;
    }

    public void setJkms(String jkms) {
        this.jkms = jkms == null ? null : jkms.trim();
    }

    public String getDyjg() {
        return dyjg;
    }

    public void setDyjg(String dyjg) {
        this.dyjg = dyjg == null ? null : dyjg.trim();
    }

    public String getJgnr() {
        return jgnr;
    }

    public void setJgnr(String jgnr) {
        this.jgnr = jgnr == null ? null : jgnr.trim();
    }

    public String getDyrq() {
        return dyrq;
    }

    public void setDyrq(String dyrq) {
        this.dyrq = dyrq == null ? null : dyrq.trim();
    }

    public Date getDysj() {
        return dysj;
    }

    public void setDysj(Date dysj) {
        this.dysj = dysj;
    }

    
    
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	@Override
	public String toString() {
		return HXCoreUtil.getJsonString(this);
	}
    
}