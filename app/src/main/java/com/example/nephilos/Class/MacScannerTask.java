package com.example.nephilos.Class;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacScannerTask {
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
    public boolean pingAllIp(String host){
        return false;
    }
}
