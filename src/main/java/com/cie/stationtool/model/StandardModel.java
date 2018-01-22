package com.cie.stationtool.model;

public class StandardModel {

    private String deviceType;
    private String cateId;
    private String cateName;
    private String Requirement;
    private String cateDesc;
    private String pointClass;
    private String pointUnit;
    private String displayGroup1;
    private String displayGroup2;

    public String getdeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        deviceType = deviceType;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getRequirement() {
        return Requirement;
    }

    public void setRequirement(String requirement) {
        Requirement = requirement;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
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
