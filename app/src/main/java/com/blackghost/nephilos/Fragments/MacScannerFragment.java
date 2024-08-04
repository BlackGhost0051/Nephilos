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

        macScannerTask = new MacScannerTask();

        scanMacBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String arpTable = macScannerTask.getArpTable();
                infoTextView.setText(arpTable);

                infoTextView.setText(String.valueOf(macScannerTask.pingIp("192.168.0.1")));
                /*try {
                    Process p = Runtime.getRuntime().exec("su -c ip n show");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    StringBuilder output = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }
                    reader.close();
                    p.waitFor();

                    String result = output.toString();
                    p.destroy();
                    infoTextView.setText(result);
                    //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });


        return view;
    }

}



/*public class MacScannerFragment extends Fragment {
    Button scanMacBTN;

    public MacScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mac_scanner, container, false);

        scanMacBTN = view.findViewById(R.id.scanButton);

        scanMacBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PingTask().execute();
            }
        });

        return view;
    }

    private class PingTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder output = new StringBuilder();
            try {
                // Ping sweep all IP addresses
                for (int i = 1; i <= 255; i++) {
                    String ipAddress = "192.168.1." + i;
                    Process pingProcess = Runtime.getRuntime().exec("ping -c 1 " + ipAddress);
                    int exitValue = pingProcess.waitFor();
                    if (exitValue == 0) {
                        output.append("Ping successful for IP: ").append(ipAddress).append("\n");
                    }
                }

                // After ping sweep, check ARP table
                Process arpProcess = Runtime.getRuntime().exec("su -c ip n show");
                BufferedReader reader = new BufferedReader(new InputStreamReader(arpProcess.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                reader.close();
                arpProcess.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return "Error executing commands";
            }
            return output.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
        }
    }
}
*/