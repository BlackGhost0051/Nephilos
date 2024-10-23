package com.blackghost.nephilos.Class;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class MacScannerTask {
    private Context context;

    public MacScannerTask(Context context){
        this.context = context;
    }

    public String getArpTable(){
        try {
            Process p = Runtime.getRuntime().exec("su -c ip n show");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            reader.close();
            p.waitFor();

            String result = output.toString();
            p.destroy();
            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error executing command";
        }
    }
    public void pingAllIp(){
        try{
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
            String inetAddress = Formatter.formatIpAddress(ipAddress);
            Log.d("HOST", inetAddress);


        } catch (Exception e){

        }
    }

    public boolean pingIp(String ip){
        try{
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.isReachable(500);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
