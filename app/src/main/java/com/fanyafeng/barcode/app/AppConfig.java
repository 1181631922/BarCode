package com.fanyafeng.barcode.app;

import android.app.Application;
import android.util.DisplayMetrics;

import com.fanyafeng.barcode.zxing.DisplayUtil;

/**
 * Author： fanyafeng
 * Data： 16/12/1 15:36
 * Email: fanyafeng@live.cn
 */
public class AppConfig extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化尺寸工具类
         */
        initDisplayOpinion();
    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }
}
