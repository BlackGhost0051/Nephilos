package com.blackghost.nephilos.Class;

import android.content.Context;
import android.net.wifi.WifiManager;

public class WifiScannerTask {
    private WifiManager wifiManager;
    private Context context;

    public WifiScannerTask(Context context){
        this.context = context;
        wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    public void scanWifi(){

    }
}
