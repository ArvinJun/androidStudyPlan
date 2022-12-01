package com.arviv.skinTest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SkinManager {

    private  static  volatile SkinManager skinManager;
    private  static Context mContext;
    private  Resources mChooseResources;
    private String skinPackageName;

    private SkinManager(){


    }

    public static   SkinManager  getInstance(Context context){
        if(skinManager == null){
            synchronized (SkinManager.class){
                if(skinManager == null){
                    mContext = context.getApplicationContext();
                    skinManager = new SkinManager();
                }

            }
        }
        return skinManager;
    }

    public boolean loadSkin(String skinPath) {
        //------------拿到skinPackageName----------
        boolean isSuccess =false;
        PackageInfo packageArchiveInfo = mContext.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES);
        if (packageArchiveInfo == null) {
        } else {
            //----------拿到skin中的Resource对象----------
            AssetManager assets = null;
            skinPackageName = packageArchiveInfo.packageName;
            try {
                assets = AssetManager.class.newInstance();
                Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assets, skinPath);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            mChooseResources = new Resources(assets, mContext.getResources().getDisplayMetrics(), mContext.getResources().getConfiguration());
            isSuccess =true;

        }
        return isSuccess;
    }


    public int getSkinColor(int resId,String resname) {
        int defaultColor = mContext.getResources().getColor(resId);
        if (mChooseResources == null) {
            return defaultColor;
        }
        int skinResId = mChooseResources.getIdentifier(resname, "color", skinPackageName);
        return mChooseResources.getColor(skinResId);
    }

    public Drawable getSkinDrawable(int resId,String resname) {
        Drawable drawable = mContext.getResources().getDrawable(resId);
        if (mChooseResources == null) {
            return drawable;
        }
        int skinResId = mChooseResources.getIdentifier(resname, "drawble", skinPackageName);
        return mChooseResources.getDrawable(skinResId);
    }

    public Drawable getMipmap(int resId,String resname) {
        Drawable drawable = mContext.getResources().getDrawable(resId);
        if (mChooseResources == null) {
            return drawable;
        }
        int skinResId = mChooseResources.getIdentifier(resname, "mipmap" ,skinPackageName);
        return mChooseResources.getDrawable(skinResId);
    }
}
