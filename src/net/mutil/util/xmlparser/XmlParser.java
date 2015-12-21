/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util.xmlparser;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * 
 * ����
 * 
 * @author Mars zhang
 * @created 2015-11-25 ����11:25:18
 */
public class XmlParser {
    /**
     * �������������ص���������ȡxml�ļ���İ汾����
     * 
     * @throws XmlPullParserException
     * @throws IOException
     * */
    public static UpdateInfo getUpdateInfo(InputStream is) throws XmlPullParserException, IOException {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        int type = parser.getEventType(); // �õ��¼�����
        UpdateInfo updateInfo = new UpdateInfo();
        while (type != XmlPullParser.END_DOCUMENT) {
            type = parser.next();
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("version".equals(parser.getName())) {
                        updateInfo.setVersion(parser.nextText());
                    } else if ("description".equals(parser.getName())) {
                        updateInfo.setDescription(parser.nextText());
                    } else if ("apkurl".equals(parser.getName())) {
                        updateInfo.setApkurl(parser.nextText());
                    }
                    break;
                default:
                    break;
            }

        }
        is.close();
        return updateInfo;
    }
}
