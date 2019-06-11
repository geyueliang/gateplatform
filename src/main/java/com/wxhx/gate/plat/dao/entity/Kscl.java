package com.wxhx.gate.plat.dao.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "KSCL")
public class Kscl {
    private String kcbh;

    private String kchp;

    @Column( name = "KCZT")
    private int kczt;

    private Short sptd;

    private String kscx;

    private String kcip;

    private String lxxh;

    public String getKcbh() {
        return kcbh;
    }

    public void setKcbh(String kcbh) {
        this.kcbh = kcbh == null ? null : kcbh.trim();
    }

    public String getKchp() {
        return kchp;
    }

    public void setKchp(String kchp) {
        this.kchp = kchp == null ? null : kchp.trim();
    }

    public int getKczt() {
        return kczt;
    }

    public void setKczt(int kczt) {
        this.kczt = kczt;
    }

    public Short getSptd() {
        return sptd;
    }

    public void setSptd(Short sptd) {
        this.sptd = sptd;
    }

    public String getKscx() {
        return kscx;
    }

    public void setKscx(String kscx) {
        this.kscx = kscx == null ? null : kscx.trim();
    }

    public String getKcip() {
        return kcip;
    }

    public void setKcip(String kcip) {
        this.kcip = kcip == null ? null : kcip.trim();
    }

    public String getLxxh() {
        return lxxh;
    }

    public void setLxxh(String lxxh) {
        this.lxxh = lxxh == null ? null : lxxh.trim();
    }
}