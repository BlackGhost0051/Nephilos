package com.blackghost.nephilos.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.blackghost.nephilos.R;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PortScannerFragment extends Fragment {

    private EditText ipEditText;
    private EditText portsEditText;
    private Button scanButton;
    private TextView resultTextView;

    public PortScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_port_scanner, container, false);

        ipEditText = view.findViewById(R.id.ipEditText);
        portsEditText = view.findViewById(R.id.portsEditText);
        scanButton = view.findViewById(R.id.scanButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        resultTextView.setMovementMethod(new ScrollingMovementMethod());

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("");
                
                String ipAddress = ipEditText.getText().toString();
                String portsString = portsEditText.getText().toString();
                List<Integer> ports = parsePorts(portsString);

                PortScannerTask scannerTask = new PortScannerTask();
                scannerTask.execute(ipAddress, ports);
            }
        });

        return view;
    }

    private List<Integer> parsePorts(String portsString) {
        List<Integer> ports = new ArrayList<>();

        String[] portTokens = portsString.split(",");
        for (String portToken : portTokens) {
            if (portToken.contains("-")) {
                String[] range = portToken.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                for (int port = start; port <= end; port++) {
                    ports.add(port);
                }
            } else {
                int port = Integer.parseInt(portToken);
                ports.add(port);
            }
        }

        return ports;
    }

    private class PortScannerTask extends AsyncTask<Object, String, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            String ipAddress = (String) params[0];
            List<Integer> ports = (List<Integer>) params[1];

            publishProgress("Start scan");
            for (int port : ports) {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ipAddress, port), 1000);
                    socket.close();
                    publishProgress(port + "/tcp   open");
                } catch (IOException e) {
                    publishProgress(port + "/tcp   closed");
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            for (String value : values) {
                resultTextView.append(value + "\n");
            }
        }
    }
}
