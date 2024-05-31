package com.example.nephilos.Fragments;

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

import com.example.nephilos.R;

import java.io.DataOutputStream;
import java.io.IOException;


public class ArpSpoofFragment extends Fragment {


    public ArpSpoofFragment() {
        // Required empty public constructor
    }


    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arp_spoof, container, false);

        edit1 = view.findViewById(R.id.editText);
        edit2 = view.findViewById(R.id.editText2);
        edit3 = view.findViewById(R.id.editText3);
        edit4 = view.findViewById(R.id.editText4);

        button = view.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_native_app();
            }
        });

        return view;


    }

    private void start_native_app()
    {
        new Thread(new Runnable() {
            public void run(){
                Process suProcess=null;
                try {
                    suProcess = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

                    String command = requireContext().getFilesDir().getParent() + "/lib/libarpspoof.so";
                    Log.d("DIR", command);

                    command+= " " + edit1.getText().toString()
                            + " " + edit2.getText().toString()
                            + " " + edit3.getText().toString()
                            + " " + edit4.getText().toString();

                    os.writeBytes(command + "\n");
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}