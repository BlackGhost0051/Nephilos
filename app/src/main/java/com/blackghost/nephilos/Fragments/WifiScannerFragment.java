package com.blackghost.nephilos.Fragments;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blackghost.nephilos.Class.WifiScannerTask;
import com.blackghost.nephilos.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class WifiScannerFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 1001;

    private WifiManager wifiManager;
    private TextView infoTextView;

    private ProgressBar progressBar;

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
        progressBar = view.findViewById(R.id.progress_bar);

        Button scanButton = view.findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*WifiScannerTask wifiScannerTask = new WifiScannerTask(getContext());
                List<ScanResult> results = wifiScannerTask.scanWifi();

                if (results != null) {
                    for (ScanResult result : results) {
                        Log.d("WifiScannerTask", "SSID: " + result.SSID + ", BSSID: " + result.BSSID);
                    }
                } else {
                    Log.e("WifiScannerTask", "Wi-Fi scan failed or permissions not granted.");
                }*/
                checkLocationAndScanWifi();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void checkLocationAndScanWifi() {
        LocationManager locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);
        boolean isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isLocationEnabled) {
            promptEnableLocation();
        } else {
            scanWifi();
        }
    }

    private void promptEnableLocation() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(requireActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                scanWifi();
                Toast.makeText(requireContext(), "YES", Toast.LENGTH_SHORT).show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(requireActivity(), REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        //
                    }
                }
            }
        });
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
                String wifiInfo = "";
                // NEED ADD CHANNEL
                for (ScanResult result : scanResults) {
                    wifiInfo =  "SSID: " + result.SSID + "\n" +
                                "BSSID: " + result.BSSID + "\n" +
                                "Signal Strength: " + result.level + " dBm\n" +
                                "Encryption: " + result.capabilities + "\n" +
                                "Channel: " + getChannelFromFrequency(result.frequency) + "\n" +
                                "Frequency: " + result.frequency + "\n" +
                                "\n";
                }

                progressBar.setVisibility(View.GONE);
                infoTextView.setText(wifiInfo);

            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    private int getChannelFromFrequency(int frequency){
        if(frequency >= 2412 && frequency <= 2484){
            return (frequency - 2412) / 5 + 1;
        } else if(frequency >= 5170 && frequency <= 5825){
            return (frequency - 5170) / 5 + 34;
        } else {
            return -1;
        }
    }
}
