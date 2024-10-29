package com.blackghost.nephilos.Fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blackghost.nephilos.Managers.LibraryManager;
import com.blackghost.nephilos.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class ArpSpoofFragment extends Fragment {
    // Add icmp_send host 1 -> 255

    public ArpSpoofFragment() {}

    private ProgressBar progressBar;

    private String nativeLibraryDir;
    private TextView info_view;

    private EditText source_ip_input;
    private EditText source_mac_input;
    private EditText target_ip_input;
    private EditText target_mac_input;

    Button start_spoof_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nativeLibraryDir = LibraryManager.getLibraryDir(getActivity());
        Log.d("DIR T", nativeLibraryDir);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arp_spoof, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        progressBar = view.findViewById(R.id.progress_bar);

        start_spoof_btn = view.findViewById(R.id.start_spoof_btn);
        info_view = view.findViewById(R.id.info_view);

        source_ip_input = view.findViewById(R.id.source_ip_input);
        source_mac_input = view.findViewById(R.id.source_mac_input);
        target_ip_input = view.findViewById(R.id.target_ip_input);
        target_mac_input = view.findViewById(R.id.target_mac_input);


        start_spoof_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String interfaceName = sharedPreferences.getString("interface_name", "wlan0");
                progressBar.setVisibility(View.VISIBLE);
                start_arp_spoof(interfaceName, String.valueOf(source_ip_input.getText()), String.valueOf(source_mac_input.getText()),String.valueOf(target_ip_input.getText()),String.valueOf(target_mac_input.getText()));
            }
        });

        return view;
    }

    private void start_arp_spoof(String use_interface, String source_ip, String source_mac, String target_ip, String target_mac) {
        new Thread(new Runnable() {
            public void run(){
                Process suProcess = null;
                try {
                    suProcess = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

                    String command = nativeLibraryDir + "/libarp_spoof.so";
                    Log.d("DIR", command);

                    command += " " + use_interface + " " + source_ip + " " + source_mac + " " + target_ip + " " + target_mac;

                    os.writeBytes(command + "\n");
                    os.flush();
                    os.close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
                    String line;
                    StringBuilder output = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                info_view.setText(output.toString());
                            }
                        });
                    }

                    reader.close();

                    suProcess.waitFor();

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}