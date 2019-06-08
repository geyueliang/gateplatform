package com.wxhx.gate.plat.dao.entity;

public class Kskfdm {
    private String dm;

    private String kfms;

    private int kf;

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm == null ? null : dm.trim();
    }

    public String getKfms() {
        return kfms;
    }

    public void setKfms(String kfms) {
        this.kfms = kfms == null ? null : kfms.trim();
    }

    public int getKf() {
        return kf;
    }

    public void setKf(int kf) {
        this.kf = kf;
    }
}