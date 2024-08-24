package com.blackghost.nephilos.Fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blackghost.nephilos.R;

import java.util.List;

public class WifiScannerFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private WifiManager wifiManager;
    private TextView infoTextView;

    public WifiScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wifiManager = (WifiManager) requireActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wifi_scanner, container, false);


        infoTextView = view.findViewById(R.id.infoTextView);

        Button scanButton = view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifi();
            }
        });

        return view;
    }

    private void scanWifi() { // mb add C scan_Wifi ?
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            requireContext().registerReceiver(new WifiScanReceiver(), new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
            wifiManager.startScan();
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    private class WifiScanReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                requireContext().unregisterReceiver(this);

                StringBuilder wifiInfoBuilder = new StringBuilder();

                for (ScanResult result : scanResults) {
                    wifiInfoBuilder.append("SSID: ").append(result.SSID).append("\n");
                    wifiInfoBuilder.append("BSSID: ").append(result.BSSID).append("\n");
                    wifiInfoBuilder.append("Signal Strength: ").append(result.level).append(" dBm\n");
                    wifiInfoBuilder.append("Encryption: ").append(result.capabilities).append("\n");
                    wifiInfoBuilder.append("Frequency: ").append(result.frequency).append(" MHz\n");
                    wifiInfoBuilder.append("\n");
                }

                String wifiInfo = wifiInfoBuilder.toString();
                infoTextView.setText(wifiInfo);

            } catch (SecurityException e) {

            }
        }
    }
}
