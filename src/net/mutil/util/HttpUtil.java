/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 2014-5-28 上午9:05:27 HttpUtil工具类
 * 
 * @author Mars zhang
 * 
 */
public class HttpUtil {

    /** BASE_PC_URL ForWebView */
    public static final String BASE_PC_URL = "http://192.168.3.100:8080/gssms/";
    /** UPDATE_VERSION_XML */
    public static final String UPDATE_VERSION_XML = "gssms_update_android_version.xml";
    /** 分隔符 */
    public static String DELIMITER = "@_2_";

    /**
     * 获取列表长度 默认为15
     * 
     * @param context
     * @return
     */
    public static String getPageSize(Context context) {
        if (null == context) {
            return "15";
        }
        SharedPreferences sp = context.getSharedPreferences("PageSize", context.MODE_PRIVATE);
        return sp.getString("pagesize", "15");
    }

    /**
     * 获取webip路径
     * 
     * @return
     */
    public static String getPCURL() {
        HttpInfo httpInfo = HttpInfo.getInstance();
        ;
        if (null != httpInfo) {
            if (httpInfo.getPort() == 80) { // 端口号为80就是没有端口号
                return "http://" + httpInfo.getIp().trim() + "/" + httpInfo.getProjectName() + "/";
            } else {
                return "http://" + httpInfo.getIp().trim() + ":" + httpInfo.getPort() + "/" + httpInfo.getProjectName()
                        + "/";
            }
        } else {
            return "";
        }

    }

    /**
     * 获取webip路径
     * 
     * @return
     */
    public static String getVersionXML() {
        HttpInfo httpInfo = HttpInfo.getInstance();
        ;
        if (null != httpInfo) {
            return httpInfo.getVersionxml();
        } else {
            return "";
        }

    }

    /**
     * 获取ip路径
     * 
     * @return
     */
    public static String getBaseURL() {

        return "";
    }

    public static  String connServerForResultPost(String strUrl, HashMap<String, String> entityMap)
            throws ClientProtocolException, IOException {
        String strResult = "";
        URL url = new URL(HttpUtil.getPCURL() + strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        StringBuilder entitySb = new StringBuilder("");
 
        Object[] entityKeys = entityMap.keySet().toArray();
        for (int i = 0; i < entityKeys.length; i++) {
            String key = (String) entityKeys[i];
            if (i == 0) {
                entitySb.append(key + "=" + entityMap.get(key));
            } else {
                entitySb.append("&" + key + "=" + entityMap.get(key));
            }
        }
        byte[] entity = entitySb.toString().getBytes("UTF-8");
        System.out.println(url.toString() + entitySb.toString());
        conn.setConnectTimeout(5000);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
        conn.getOutputStream().write(entity);
        if (conn.getResponseCode() == 200) {
            InputStream inputstream = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = inputstream.read(b)) != -1;) {
                buffer.append(new String(b, 0, n));
            }
            strResult = buffer.toString();
        }
        return strResult;
    }
    
    
    
    /**
     * 解析 json数组据 成baseModel
     * 
     */
   public static List<BaseModel> getObjsInfo(String jsonString) throws JSONException {
        List<BaseModel> list = new ArrayList<BaseModel>();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            BaseModel baseModel = new BaseModel();
            Iterator<String> iterators = jsonObject.keys();
            for (int j = 0; iterators.hasNext(); j++) {
                String key = iterators.next();
                baseModel.set(key, jsonObject.get(key));
            }
            list.add(baseModel);
        }
        return list;
    }

}
