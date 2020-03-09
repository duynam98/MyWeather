package com.duynam.myweather.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;

import com.duynam.myweather.R;
import com.duynam.myweather.view.errornetwork.ErrorNetWorkFragment;
import com.duynam.myweather.view.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private ErrorNetWorkFragment fragmentErrorNetWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checknetwork();
    }

    private void checknetwork() {
        fragmentErrorNetWork = new ErrorNetWorkFragment();
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (capabilities != null) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }else {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, fragmentErrorNetWork);
                fragmentTransaction.commit();
            }
        }else {
            if (cm != null){
                if (cm.getActiveNetworkInfo() != null){
                    startActivity(new Intent(this, HomeActivity.class));
                    finish();
                }
            }else {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, fragmentErrorNetWork);
                fragmentTransaction.commit();
            }
        }
    }

}
