package com.wxhx.gate.plat.dao.entity;

import java.util.Date;

public class Ksgc {
    private long id;

    private String lsh;

    private String sfzmhm;

    private String jkbz;

    private int bckscs;

    private Date sj;

    private String cs1;

    private String cs2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh == null ? null : lsh.trim();
    }

    public String getSfzmhm() {
        return sfzmhm;
    }

    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm == null ? null : sfzmhm.trim();
    }

    public String getJkbz() {
        return jkbz;
    }

    public void setJkbz(String jkbz) {
        this.jkbz = jkbz == null ? null : jkbz.trim();
    }

    public int getBckscs() {
        return bckscs;
    }

    public void setBckscs(int bckscs) {
        this.bckscs = bckscs;
    }

    public Date getSj() {
        return sj;
    }

    public void setSj(Date sj) {
        this.sj = sj;
    }

    public String getCs1() {
        return cs1;
    }

    public void setCs1(String cs1) {
        this.cs1 = cs1 == null ? null : cs1.trim();
    }

    public String getCs2() {
        return cs2;
    }

    public void setCs2(String cs2) {
        this.cs2 = cs2 == null ? null : cs2.trim();
    }
}