package net.mutil.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;

public class MyLayoutUtil {
    
    
    public static void setMargin(View view,int left,int top,int right,int bottom){
        if(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){//任何实例instanceof都为true
            MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
            mlp.setMargins(left, top, right, bottom);
            view.requestLayout();//重新onMeasure onLayout
//            view.invalidate();//重画
        }
    }
}
