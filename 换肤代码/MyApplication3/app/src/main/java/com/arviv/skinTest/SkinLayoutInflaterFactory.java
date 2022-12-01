package com.arviv.skinTest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterFactory;

public class SkinLayoutInflaterFactory implements LayoutInflater.Factory2 {


    private List<SkinView> skinViews = new ArrayList<>();//需要换肤的所有view的集合
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.view.",
            "android.webkit."
    };
    @Nullable
    @Override
    public View onCreateView(@Nullable View view, @NonNull String name, @NonNull Context context, @NonNull AttributeSet set) {
        Log.d("jun","------>"+name);


        View realView= null;

        if (name.contains(".")) {//表示不是常用的TextView这些控件，不包括自定义，
            // 第三方。v7 ，v4，androidx等库的控件，这些需要单独去适配这里只是说原理，其他的都是差不多的
            realView = createView(name, context, set);
        } else {//系统控件
            for (int i = 0; i < sClassPrefixList.length; i++) {
                realView = createView(sClassPrefixList[i] + name, context, set);
                if (realView != null) {
                    break;
                }
            }
        }
        List<SkinAttr> skinAttrsList = new ArrayList<>();
        for (int i=0;i<set.getAttributeCount();i++){
            String attributeName = set.getAttributeName(i);//属性的名字background
            String attributeValue = set.getAttributeValue(i);//属性的值

            //在这里收集的属性主要是皮肤换肤需要的一些属性，例如background，textColor，src等
            if(isSupportSkinAttr(attributeName)){
                //资源的id,实际就是R文件的id
                int resId = Integer.parseInt(attributeValue.substring(1));//截取@2131361811 ，拿到实际的在R文件中的值
                String resName = context.getResources().getResourceName(resId);//这个是完整的路径
                String res = context.getResources().getResourceEntryName(resId);//资源的名字
                String attrType = context.getResources().getResourceTypeName(resId);
                Log.i("jun","res"+res+"name:"+resName+"----attrType"+attrType+"---rId");
                SkinAttr attr = new SkinAttr(attributeName,attrType,res,resId);
                skinAttrsList.add(attr);
            }
        }
        SkinView skinView = new SkinView(realView,skinAttrsList);
        skinViews.add(skinView);
        skinView.skinApply();
        return realView;
    }

   public void applySkinAll(){
       for (SkinView skinView :skinViews){

           skinView.skinApply();
       }
       Log.i("jun","----->"+skinViews.size());
   }

    private boolean isSupportSkinAttr(String name){
        if(name.equalsIgnoreCase("background")
                ||name.equalsIgnoreCase("src")
                ||name.equalsIgnoreCase("textColor") ){
            return  true;
        }
        return  false;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull String s, @NonNull Context context, @NonNull AttributeSet set) {

        return null;
    }

    private View createView(String name, Context context, AttributeSet attrs) {

        try {
            Class<? extends View> aClass = (Class<? extends View>) context.getClassLoader().loadClass(name);
            Constructor<? extends View> constructor = aClass.getConstructor(new Class[]{Context.class, AttributeSet.class});
            return constructor.newInstance(context, attrs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
