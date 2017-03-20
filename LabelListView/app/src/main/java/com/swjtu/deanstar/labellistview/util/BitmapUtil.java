package com.swjtu.deanstar.labellistview.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by yhp5210 on 2017/2/23.
 */

public class BitmapUtil {


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight){
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){

            final int halfHeight = height/2;
            final int halfWidth = width/2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps     both
            // height and width larger than the requested height and width.
            while((halfHeight/inSampleSize) >= reqHeight
                    &&(halfWidth/inSampleSize) >= reqWidth){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight){

        //首先设置inJustDecodeBounds = true来解码用来检查尺寸
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
}
