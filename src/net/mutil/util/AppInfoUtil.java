/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 
 * 描述
 * @author Mars zhang
 * @created 2015-11-25 上午11:50:34
 */
public class AppInfoUtil {
    /** 定义一个包管理者 */
    private PackageManager pm;
    /** 应用程序版本 */
    private String version;

    /**
     * 获取应用程序的版本信息
     */
    public String getAppversion(Context context) {
        pm = context.getPackageManager();
        try {
            // 获取包路径 packageInfo保存了清单文件的信息
            // pm.getPackageInfo("com.example.mobilesafemanager", 0);
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName; // 获取版本号\
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            // cant't reach
        }

        return version;

    }
}
