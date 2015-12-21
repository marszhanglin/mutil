/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.TrafficStats;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * 2014-5-28 ����9:05:27 ShareUtil������
 * 
 * @author Mars zhang
 * 
 */
public class PhoneUtil {
    /** ���� */
    private static PhoneUtil me;

    /**
     * 
     * ����
     * 
     * @author Mars zhang
     * @created 2015-11-25 ����2:24:09
     * @return
     */
    public static PhoneUtil getInstance() {
        if (null == me) {
            me = new PhoneUtil();
        }
        return me;
    }

    /**
     * ����ĳ��uidʹ������
     * 
     * @param context
     *            getApplicationContext()
     * @param UID
     *            android.os.Process.myUid()
     * @return
     */
    public String getFlowByUid(Context context, int UID) {
        long LJWG_Rx = (TrafficStats.getUidRxBytes(android.os.Process.myUid()) == TrafficStats.UNSUPPORTED ? 0
                : (TrafficStats.getUidRxBytes(UID) / 1024));
        long LJWG_Tx = (TrafficStats.getUidTxBytes(android.os.Process.myUid()) == TrafficStats.UNSUPPORTED ? 0
                : (TrafficStats.getUidTxBytes(UID) / 1024));
        long all = LJWG_Rx + LJWG_Tx + ShareUtil.getLong(context, "FLOW", "LJWG", 0L);
        String s = "" + all / 1024.00;
        // flow_size.setText(s.substring(0, s.indexOf(".")+4)+"M");
        return s.substring(0, s.indexOf(".") + 4) + "M";
    }

    /**
     * ���±���ʹ������
     * 
     * @param context
     * @return
     */
    public String getAllFlow(Context context) {
        long ALL_Rx = (TrafficStats.getTotalRxBytes() == TrafficStats.UNSUPPORTED ? 0
                : (TrafficStats.getTotalRxBytes() / 1024));
        long ALL_Tx = (TrafficStats.getTotalTxBytes() == TrafficStats.UNSUPPORTED ? 0
                : (TrafficStats.getTotalTxBytes() / 1024));
        long all_android = ALL_Rx + ALL_Tx + ShareUtil.getLong(context, "FLOW", "ALL_WIFI_GPRS", 0L);
        String s2 = "" + all_android / 1024.00;
        return s2.substring(0, s2.indexOf(".") + 4) + "M";
    }

    /**
     * ��ȡ�豸��
     * 
     * @param context
     * @return
     */
    public String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimSerialNumber();
    }

    /**
     * ��ȡSIM��
     * 
     * @param context
     * @return
     */
    public String getImsi(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }

    /**
     * jt
     */
    public void getandSaveCurrentImage(Activity activity) {
        // ����Bitmap
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int w = display.getWidth();
        int h = display.getHeight();
        Bitmap Bmp = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        // ��ȡ��Ļ
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        Bmp = decorview.getDrawingCache();
        // ͼƬ�洢·��
        String SavePath = getSDCardPath() + "/LJWG/ScreenImages";
        // ����Bitmap
        try {
            File path = new File(SavePath);
            // �ļ�
            String filepath = SavePath + "/Screen_" + System.currentTimeMillis() + ".png";
            File file = new File(filepath);
            if (!path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            if (null != fos) {
                Bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                Toast.makeText(activity, "�����ļ��ѱ�����" + path + "Ŀ¼��", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡSDCard��Ŀ¼·������
     * 
     * @return
     */
    public String getSDCardPath() {
        File sdcardDir = null;
        // �ж�SDCard�Ƿ����
        boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
}
