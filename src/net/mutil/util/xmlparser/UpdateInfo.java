/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util.xmlparser;

import java.io.Serializable;

/**
 * 
 * 2014-7-22����4:44:56 ��UpdateInfo app�汾��Ϣ
 * 
 * @author Mars zhang
 * 
 */
public class UpdateInfo implements Serializable{
    /** MemberVariables */
    private String version;
    /** MemberVariables */
    private String description;
    /** MemberVariables */
    private String apkurl;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApkurl() {
        return apkurl;
    }

    public void setApkurl(String apkurl) {
        this.apkurl = apkurl;
    }

    @Override
    public String toString() {

        return version + description + apkurl;
    }

}
