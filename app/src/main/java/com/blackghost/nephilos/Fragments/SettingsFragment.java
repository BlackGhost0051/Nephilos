package com.blackghost.nephilos.Fragments;

import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.blackghost.nephilos.Managers.LibraryManager;
import com.blackghost.nephilos.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class SettingsFragment  extends PreferenceFragmentCompat{

    String nativeLibraryDir;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        getActivity().setTheme(R.style.preferences_style);

        nativeLibraryDir = LibraryManager.getLibraryDir(getActivity());



        get_interfaces();
    }

    private void get_interfaces() {
        new Thread(new Runnable() {
            public void run(){
                Process suProcess = null;
                try {
                    suProcess = Runtime.getRuntime().exec("su");

                    DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());

                    String command = nativeLibraryDir + "/libget_interfaces.so";
                    Log.d("DIR", command);

                    os.writeBytes(command + "\n");
                    os.flush();
                    os.close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
                    String line;
                    StringBuilder output = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }

                    reader.close();

                    suProcess.waitFor();

                    final String[] interfacesNames = output.toString().trim().split("\n");

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Interfaces",output.toString());

                            ListPreference listPreference = findPreference("interface_name");

                            if (listPreference != null) {

                                CharSequence[] newEntries = interfacesNames;
                                CharSequence[] newEntryValues = interfacesNames;

                                listPreference.setEntries(newEntries);
                                listPreference.setEntryValues(newEntryValues);
                            }
                        }
                    });

                } catch (IOException | InterruptedException e) {
                    String message = "Error = " + e.toString() + "\n\n NEED ROOT to get the name of the interfaces!!!";

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("SettingsFragment", message);
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
