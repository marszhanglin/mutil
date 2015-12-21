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
 * ����
 * @author Mars zhang
 * @created 2015-11-25 ����11:50:34
 */
public class AppInfoUtil {
    /** ����һ���������� */
    private PackageManager pm;
    /** Ӧ�ó���汾 */
    private String version;

    /**
     * ��ȡӦ�ó���İ汾��Ϣ
     */
    public String getAppversion(Context context) {
        pm = context.getPackageManager();
        try {
            // ��ȡ��·�� packageInfo�������嵥�ļ�����Ϣ
            // pm.getPackageInfo("com.example.mobilesafemanager", 0);
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionName; // ��ȡ�汾��\
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            // cant't reach
        }

        return version;

    }
}
