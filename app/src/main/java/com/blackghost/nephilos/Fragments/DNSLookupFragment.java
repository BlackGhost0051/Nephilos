package com.blackghost.nephilos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blackghost.nephilos.R;


public class DNSLookupFragment extends Fragment {

    private EditText dnsInput;
    private TextView dnsInfo;
    private Button sendButton;

    public DNSLookupFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_dns_lookup, container, false);

        dnsInfo = view.findViewById(R.id.dns_info);
        dnsInput = view.findViewById(R.id.dns_input);
        sendButton = view.findViewById(R.id.send_btn);
        return view;
    }
}