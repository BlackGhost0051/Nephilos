package com.example.nephilos;

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
import android.widget.Toast;

import com.example.nephilos.Fragments.ArpSpoofFragment;
import com.example.nephilos.Fragments.MacScannerFragment;
import com.example.nephilos.Fragments.MainFragment;
import com.example.nephilos.Fragments.PortScannerFragment;
import com.example.nephilos.Fragments.WifiScannerFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        //Toast.makeText(MainActivity.this, "Wifi Scanner",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Wifi Scanner");
                        fragmentR(new WifiScannerFragment());

                        break;

                    case R.id.port_scanner:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        //Toast.makeText(MainActivity.this, "Wifi Scanner",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Port Scanner");
                        fragmentR(new PortScannerFragment());

                        break;

                    case R.id.mac_scanner:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        //Toast.makeText(MainActivity.this, "Message",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Mac Scanner");
                        fragmentR(new MacScannerFragment());

                        break;

                    case R.id.arp_spoof:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        //Toast.makeText(MainActivity.this, "explore",Toast.LENGTH_SHORT).show();
                        toolbar.setTitle("Arp Spoof");
                        fragmentR(new ArpSpoofFragment());

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