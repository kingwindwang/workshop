package com.time.workshop;

import com.time.workshop.util.ScreenSizeUtil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;




/**
 * 
 * @author wangfeng
 *
 */
public class App extends Application {
	
    private final static String TAG = "com.time.workshop";
    public final static boolean DEBUG = true;
    public final static double imgrote = 0.40;
    public boolean isReceiveMSG = false;
    public static Context applicationContext;
	private static App INSTANCE;
	
	private ScreenSizeUtil sizeUtil;
	    
    public ScreenSizeUtil getSizeUtil() {
        return sizeUtil;
    }
    
    public void setSizeUtil(ScreenSizeUtil sizeUtil) {
        this.sizeUtil = sizeUtil;
    }
    
    public int getScreenWidth() {
        return sizeUtil.getScreenWidth();
    }
    

    @Override
    public void onCreate() {
    	super.onCreate();
    	INSTANCE = this;
    	sizeUtil = new ScreenSizeUtil(this);
    }

    
    
    public static App getInstance() {
        return INSTANCE;
    }

    public static void showToast(Object msg) {
        Toast.makeText(getInstance(), msg + "", Toast.LENGTH_SHORT).show();
    }
    
    public static void showLog(Object msg) {
        Log.i(TAG, msg + "");
    }

    public static void SystemOut(String str) {
        System.out.println(str);
    }
}
