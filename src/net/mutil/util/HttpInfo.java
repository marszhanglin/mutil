/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

/**
 * 
 * ����
 * 
 * @author Mars zhang
 * @created 2015-11-25 ����11:24:53
 */
public class HttpInfo {
    /** ��������IP��ַ */
    private String ip;
    /** �������˶˿� */
    private int port = 0;
    /** ����������Ŀ���� */
    private String projectName;
    /** ���ڷ����������°汾��Ϣxml�ļ��� */
    private String versionxml;
    /** �Լ����� */
    private static HttpInfo me;

    public String getVersionxml() {
        return versionxml;
    }

    public void setVersionxml(String versionxml) {
        this.versionxml = versionxml;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * ��ȡ��������HttpInfo
     * 
     * @return
     */
    public static HttpInfo getInstance() {
        if (null == me) {
            me = new HttpInfo();
        }
        return me;
    }

    /**
     * ���캯�� ����Ա�����и�ֵ
     */
    public HttpInfo() {
        Properties properties = new Properties();
        InputStream is = HttpUtil.class.getClassLoader().getResourceAsStream("HttpInfo.properties");
		if(null==is){
            is = HttpUtil.class.getClassLoader().getResourceAsStream("assets/HttpInfo.properties");
            
        }
        try {
            properties.load(is);
            setIp(properties.getProperty("IP"));
            setPort(new Integer(properties.getProperty("PORT")));
            setProjectName(properties.getProperty("PROJECT"));
            setVersionxml(properties.getProperty("VERSIONXML"));
            Log.v("mars", "ִ��HttpInfo�������캯��");
        } catch (IOException e) {
            Log.e("mars", "��ȡHttpInfo.properties����" + e.getMessage());
        }
    }

}
