package com.blackghost.nephilos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.blackghost.nephilos.Fragments.ArpSpoofFragment;
import com.blackghost.nephilos.Fragments.MacScannerFragment;
import com.blackghost.nephilos.Fragments.MainFragment;
import com.blackghost.nephilos.Fragments.PortScannerFragment;
import com.blackghost.nephilos.Fragments.RequestFragment;
import com.blackghost.nephilos.Fragments.SettingsFragment;
import com.blackghost.nephilos.Fragments.WifiScannerFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // need add checking if there are libs so if empty use ApkManager && finding internet interfaces and add to memory && add checking ROOT access

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));

        fragmentR(new MainFragment());

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
}