/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package net.mutil.util;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 
 * ����  ����������
 * @author Mars zhang
 * @created 2015-12-9 ����10:45:26
 */
public class AnimationUtil {
    
    
    
    /**
     * �����С�Ŵ�Ч��
     * 
     * @param view
     */
    public static void AniZoomIn(View view) {
        float[] vaules = new float[] { 0.9f, 0.92f, 0.94f, 0.96f, 0.98f, 1.0f, 1.1f, 1.2f, 1.3f, 1.25f, 1.2f, 1.15f, 1.1f,
                1.0f };
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(view, "scaleX", vaules), ObjectAnimator.ofFloat(view, "scaleY", vaules));
        set.setDuration(20);
        set.start();
    }
}
