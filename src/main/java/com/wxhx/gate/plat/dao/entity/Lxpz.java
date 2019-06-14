package com.wxhx.gate.plat.dao.entity;

public class Lxpz {
    private String lxxh;

    private String sbxhs;

    private String sbxms;

    private String czxms;

    public String getLxxh() {
        return lxxh;
    }

    public void setLxxh(String lxxh) {
        this.lxxh = lxxh == null ? null : lxxh.trim();
    }

    public String getSbxhs() {
        return sbxhs;
    }

    public void setSbxhs(String sbxhs) {
        this.sbxhs = sbxhs == null ? null : sbxhs.trim();
    }

    public String getSbxms() {
        return sbxms;
    }

    public void setSbxms(String sbxms) {
        this.sbxms = sbxms == null ? null : sbxms.trim();
    }

    public String getCzxms() {
        return czxms;
    }

    public void setCzxms(String czxms) {
        this.czxms = czxms == null ? null : czxms.trim();
    }
}