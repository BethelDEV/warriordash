package funs.gamez;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: WarriorDash
 * @Package: funs.gamez
 * @ClassName: Utils
 * @Description: 工具类
 */
public class Utils {
    /**
     * 获取屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        //从系统服务中获取窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        //从默认显示显示器中获取显示参数保存到dm对象中
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;//返回屏幕的宽度数值
    }

    /**
     * 获取屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        //从系统服务中获取窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        //从默认显示显示器中获取显示参数保存到dm对象中
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;//返回屏幕的高度数值
    }

    /**
     * 获取屏幕的像素密度
     */
    public static float getScreenDensity(Context context) {
        //从系统服务中获取窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        //从默认显示显示器中获取显示参数保存到dm对象中
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;//返回屏幕的像素密度数值
    }

    public static String getTimeString(long time){
        Date date = new Date(time);
        final String strDateFormat = "MM-dd HH:mm:ss";
//        final String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }
}
