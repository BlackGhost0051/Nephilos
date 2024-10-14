package com.blackghost.nephilos.Managers;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager {
    public RequestManager(){

    }

    public void send_GET(String urlString){
        Thread thread = new Thread(() -> {
           try{
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responceCode = connection.getResponseCode();

                if (responceCode == HttpURLConnection.HTTP_OK){
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    in.close();
                    connection.disconnect();

                    Log.d("Content", content.toString());
                } else {
                    // failed
                }
           } catch (Exception e){
                e.printStackTrace();
           }
        });

        thread.start();
    }

    public void send_POST(){

    }
}
