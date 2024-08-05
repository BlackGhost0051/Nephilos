package com.blackghost.nephilos.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blackghost.nephilos.R;

import java.io.DataOutputStream;
import java.io.IOException;


public class ArpSpoofFragment extends Fragment {
    // Add icmp_send host 1 -> 255

    public ArpSpoofFragment() {}

    private String nativeLibraryDir;
    private TextView info_view;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nativeLibraryDir = requireContext().getApplicationInfo().nativeLibraryDir;

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arp_spoof, container, false);

        button = view.findViewById(R.id.button);

        String command = nativeLibraryDir + "libarpspoof.so";
        Log.d("DIR", command);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_arp_spoof("wlan0","192.168.0.2","ff:ff:ff:ff:ff:ff","192.168.0.1","ff:ff:ff:ff:ff:ff");
            }
        });

        return view;


    }

    private void start_arp_spoof(String use_interface, String source_ip, String source_mac, String target_ip, String target_mac) {
        new Thread(new Runnable() {
            public void run(){
                Process suProcess = null;   // Test het info from C code ( printf )
                try {
                    suProcess = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

                    String command = nativeLibraryDir + "libarpspoof.so";
                    Log.d("DIR", command);

                    command+= " " + use_interface;

                    os.writeBytes(command + "\n");
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}