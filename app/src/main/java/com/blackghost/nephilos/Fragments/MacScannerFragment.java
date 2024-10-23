package com.blackghost.nephilos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blackghost.nephilos.Class.MacScannerTask;
import com.blackghost.nephilos.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MacScannerFragment extends Fragment {
    Button scanMacBTN;
    TextView infoTextView;
    private MacScannerTask macScannerTask;
    public MacScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mac_scanner, container, false);

        scanMacBTN = view.findViewById(R.id.scanButton);
        infoTextView = view.findViewById(R.id.infoTextView);

        macScannerTask = new MacScannerTask(getContext());

        scanMacBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                macScannerTask.pingAllIp();
                infoTextView.setText(macScannerTask.getArpTable());
            }
        });


        return view;
    }
}