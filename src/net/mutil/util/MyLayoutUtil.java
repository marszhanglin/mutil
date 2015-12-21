package net.mutil.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;

public class MyLayoutUtil {
    
    
    public static void setMargin(View view,int left,int top,int right,int bottom){
        if(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){//�κ�ʵ��instanceof��Ϊtrue
            MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
            mlp.setMargins(left, top, right, bottom);
            view.requestLayout();//����onMeasure onLayout
//            view.invalidate();//�ػ�
        }
    }
}
