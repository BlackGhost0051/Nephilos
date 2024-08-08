package com.blackghost.nephilos.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class MacScannerTask {
    public String getArpTable(){

        // make ICMP send from HOST ( make c code send_icmp_to_all("192.168.0.") )

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
    public boolean pingAllIp(String host){
        return false;
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
