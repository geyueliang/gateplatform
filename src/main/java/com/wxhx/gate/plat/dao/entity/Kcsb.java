package com.wxhx.gate.plat.dao.entity;

public class Kcsb {
    private String sbbh;

    private String sbmc;

    private String sbxh;

    private String sbxm;

    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc == null ? null : sbmc.trim();
    }

    public String getSbxh() {
        return sbxh;
    }

    public void setSbxh(String sbxh) {
        this.sbxh = sbxh == null ? null : sbxh.trim();
    }

    public String getSbxm() {
        return sbxm;
    }

    public void setSbxm(String sbxm) {
        this.sbxm = sbxm == null ? null : sbxm.trim();
    }
}