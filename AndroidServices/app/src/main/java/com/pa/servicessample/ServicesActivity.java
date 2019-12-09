package com.pa.servicessample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServicesActivity extends AppCompatActivity {

    private CouponsBoundService boundService = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);

        Intent i = new Intent();
        i.setClass(this, CouponsBoundService.class);
        bindService(i, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CouponsBoundService.CouponServiceBinder binder = (CouponsBoundService.CouponServiceBinder) service;
            boundService = binder.getService();

            if(boundService != null){
                Log.d("service-bind", "Service is bonded successfully!");

                //do whatever you want to do after successful binding
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            boundService = null;
        }
    };

    public void createStartedService(View view){
        Intent i =  new Intent();
        i.setClass(this, LatestCouponService.class);
        i.putExtra("foreground", true);
        startService(i);
    }

    public void stopService(View view){
        Intent i =  new Intent();
        i.setClass(this, LatestCouponService.class);
        stopService(i);
    }
    public void startForegroundService(View view){
        Intent i =  new Intent();
        i.setClass(this, LatestCouponService.class);
        i.putExtra("foreground", true);
        startService(i);
    }

    public void useBoundService(View view){
        if(boundService != null) {
            String latestCoupon = boundService.getCoupon();
            Toast.makeText(this, latestCoupon, Toast.LENGTH_SHORT).show();
        }
    }
}
