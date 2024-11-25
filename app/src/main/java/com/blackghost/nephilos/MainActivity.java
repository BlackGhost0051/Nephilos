package com.blackghost.nephilos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blackghost.nephilos.Fragments.ArpSpoofFragment;
import com.blackghost.nephilos.Fragments.DNSLookupFragment;
import com.blackghost.nephilos.Fragments.MacScannerFragment;
import com.blackghost.nephilos.Fragments.MainFragment;
import com.blackghost.nephilos.Fragments.PortScannerFragment;
import com.blackghost.nephilos.Fragments.RequestFragment;
import com.blackghost.nephilos.Fragments.SettingsFragment;
import com.blackghost.nephilos.Fragments.WifiScannerFragment;
import com.blackghost.nephilos.Managers.LibraryManager;
import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    String nativeLibraryDir;
    TextView app_version_TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nativeLibraryDir = LibraryManager.getLibraryDir(this);
        //get_interfaces();

        // need add checking if there are libs so if empty use LibraryManager && finding internet interfaces and add to memory && add checking ROOT access


/*      GET INTERFACE VALUE
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String selectedInterface = sharedPreferences.getString("interface_name", "wlan0");
        Log.d("Selected Interface: ",selectedInterface);
*/




        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

        fragmentR(new MainFragment());

        View header = navigationView.getHeaderView(0);
        app_version_TextView = header.findViewById(R.id.app_version);
        app_version_TextView.setText(BuildConfig.VERSION_NAME);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.wifi_scanner:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_wifi_scanner);
                        fragmentR(new WifiScannerFragment());
                        break;

                    case R.id.port_scanner:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_port_scanner);
                        fragmentR(new PortScannerFragment());
                        break;

                    case R.id.mac_scanner:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_mac_scanner);
                        fragmentR(new MacScannerFragment());
                        break;
                    case R.id.request :
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_request);
                        fragmentR(new RequestFragment());
                        break;

                    case R.id.arp_spoof:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_arp_spoof);
                        fragmentR(new ArpSpoofFragment());
                        break;
                    case R.id.dns_lookup:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_dns_lookup);
                        fragmentR(new DNSLookupFragment());
                        break;

                    case R.id.settings:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        toolbar.setTitle(R.string.toolbar_settings);
                        fragmentR(new SettingsFragment());
                        break;
                }

                return true;
            }
        });
    }

    private void fragmentR(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Interfaces",output.toString());
                        }
                    });

                } catch (IOException | InterruptedException e) {
                    String message = "Error = " + e.toString() + "\n\n NEED ROOT to get the name of the interfaces!!!";

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showAlertMessage("Root Access Required", message);
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showAlertMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}