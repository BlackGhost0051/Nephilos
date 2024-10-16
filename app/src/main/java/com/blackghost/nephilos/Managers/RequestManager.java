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
                    handler.post(() -> requestInterface.GET_request("Failed!!!"));
                }
           } catch (Exception e){
                handler.post(() -> requestInterface.GET_request("Exception = " + e.toString()));
                e.printStackTrace();
           }
        });

        thread.start();
    }

    public void send_POST(String urlString){
        Thread thread = new Thread(() -> {
            try{
                URL url = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                int responceCode = connection.getResponseCode();

                if(responceCode == HttpURLConnection.HTTP_OK){

                } else {

                }

            } catch (Exception e){
                e.printStackTrace();
            }
        });

        thread.start();
    }
}
