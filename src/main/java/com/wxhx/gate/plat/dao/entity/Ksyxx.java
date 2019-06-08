package com.wxhx.gate.plat.dao.entity;

public class Ksyxx {
    private String ksysfzmhm;

    private String ksyxm;

    public String getKsysfzmhm() {
        return ksysfzmhm;
    }

    public void setKsysfzmhm(String ksysfzmhm) {
        this.ksysfzmhm = ksysfzmhm == null ? null : ksysfzmhm.trim();
    }

    public String getKsyxm() {
        return ksyxm;
    }

    public void setKsyxm(String ksyxm) {
        this.ksyxm = ksyxm == null ? null : ksyxm.trim();
    }
}