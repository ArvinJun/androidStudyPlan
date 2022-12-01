package com.arviv.skinTest;

public class SkinAttr {

    private String attrName;

    private String attrType;

    private int resId;

    private String resName;

    public SkinAttr(String attrName, String attrType, String resName, int resId) {
        this.attrName = attrName;
        this.attrType = attrType;
        this.resId = resId;
        this.resName = resName;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

}
 