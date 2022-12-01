package com.arviv.skinTest;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SkinView {


    private View view;

    private List<SkinAttr> mSkinAttrs = new ArrayList<>();

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        mSkinAttrs = skinAttrs;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<SkinAttr> getSkinAttrs() {
        return mSkinAttrs;
    }

    public void setSkinAttrs(List<SkinAttr> skinAttrs) {
        mSkinAttrs = skinAttrs;
    }

    public void  skinApply(){
       for (SkinAttr skinAttr :mSkinAttrs){
           String attrName = skinAttr.getAttrName();
           String attrtype = skinAttr.getAttrType();
           int resId = skinAttr.getResId();
           String resName = skinAttr.getResName();
           if("background".equalsIgnoreCase(attrName)){
               if(attrtype.equalsIgnoreCase("color")){
                  view.setBackgroundColor(SkinManager.getInstance(SkinApplication.getApp()).getSkinColor(resId,resName));
               }else if(attrtype.equalsIgnoreCase("drawble")){
                   view.setBackground(SkinManager.getInstance(SkinApplication.getApp()).getSkinDrawable(resId,resName));
               }else if(attrtype.equalsIgnoreCase("mipmap")){
                   view.setBackground(SkinManager.getInstance(SkinApplication.getApp()).getMipmap(resId,resName));
               }

           }
           if("src".equalsIgnoreCase(attrName)&& view instanceof  ImageView){
               if(attrtype.equalsIgnoreCase("drawble")){
                   ((ImageView)view).setImageDrawable(SkinManager.getInstance(SkinApplication.getApp()).getSkinDrawable(resId,resName));
               }else if(attrtype.equalsIgnoreCase("mipmap")){
                   ((ImageView)view).setImageDrawable(SkinManager.getInstance(SkinApplication.getApp()).getMipmap(resId,resName));
               }

           }
           if("textColor".equalsIgnoreCase(attrName)){
               if (view instanceof TextView && "color".equals(attrtype)) {
                   ((TextView) view).setTextColor(SkinManager.getInstance(SkinApplication.getApp()).getSkinColor(resId,resName));
               }
           }

       }

    }
}
