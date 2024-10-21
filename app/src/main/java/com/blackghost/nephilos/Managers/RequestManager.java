package com.blackghost.nephilos.Managers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.blackghost.nephilos.Interfaces.RequestInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager {
    private RequestInterface requestInterface;
    private Handler handler = new Handler(Looper.getMainLooper());


    public RequestManager(RequestInterface requestInterface){
        this.requestInterface = requestInterface;
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
                        content.append(inputLine + "\n");
                    }

                    in.close();
                    connection.disconnect();

                    Log.d("Content", content.toString());
                    handler.post(() -> requestInterface.GET_request(content.toString()));
                } else {
                    // failed
                    handler.post(() -> requestInterface.GET_request("Failed with response code: " + String.valueOf(responceCode)));
                }
           } catch (Exception e){
                handler.post(() -> requestInterface.GET_request("Exception = " + e.toString()));
                e.printStackTrace();
           }
        });

        thread.start();
    }

    public void send_POST(String urlString, String postData, String contentType){
        Thread thread = new Thread(() -> {
            try{
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                connection.setRequestProperty("Content-Type", contentType);
                connection.setRequestProperty("charset", "utf-8");


                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = postData.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responceCode = connection.getResponseCode();

                if(responceCode == HttpURLConnection.HTTP_OK){
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = reader.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        handler.post(() -> requestInterface.POST_request(response.toString()));
                    }
                } else {
                    handler.post(() -> requestInterface.POST_request("Failed with response code: " + String.valueOf(responceCode)));
                }

            } catch (Exception e){
                handler.post(() -> requestInterface.POST_request("Exception = " + e.toString()));
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
