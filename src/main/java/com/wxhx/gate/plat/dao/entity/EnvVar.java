package com.wxhx.gate.plat.dao.entity;

public class EnvVar {
    private Short id;

    private String envName;

    private String envValue;

    private String envDesc;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName == null ? null : envName.trim();
    }

    public String getEnvValue() {
        return envValue;
    }

    public void setEnvValue(String envValue) {
        this.envValue = envValue == null ? null : envValue.trim();
    }

    public String getEnvDesc() {
        return envDesc;
    }

    public void setEnvDesc(String envDesc) {
        this.envDesc = envDesc == null ? null : envDesc.trim();
    }
}