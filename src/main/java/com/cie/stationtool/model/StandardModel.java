package com.cie.stationtool.model;

public class StandardModel {

    private String DeviceType;
    private String cateId;
    private String cate_name;
    private String Requirement;
    private String cate_desc;
    private String pointClass;
    private String pointUnit;
    private String displayGroup1;
    private String displayGroup2;

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getRequirement() {
        return Requirement;
    }

    public void setRequirement(String requirement) {
        Requirement = requirement;
    }

    public String getCate_desc() {
        return cate_desc;
    }

    public void setCate_desc(String cate_desc) {
        this.cate_desc = cate_desc;
    }

    public String getPointClass() {
        return pointClass;
    }

    public void setPointClass(String pointClass) {
        this.pointClass = pointClass;
    }

    public String getPointUnit() {
        return pointUnit;
    }

    public void setPointUnit(String pointUnit) {
        this.pointUnit = pointUnit;
    }

    public String getDisplayGroup1() {
        return displayGroup1;
    }

    public void setDisplayGroup1(String displayGroup1) {
        this.displayGroup1 = displayGroup1;
    }

    public String getDisplayGroup2() {
        return displayGroup2;
    }

    public void setDisplayGroup2(String displayGroup2) {
        this.displayGroup2 = displayGroup2;
    }
}
