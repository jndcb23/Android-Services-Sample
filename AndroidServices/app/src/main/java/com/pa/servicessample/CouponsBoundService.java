package com.pa.servicessample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;


public class CouponsBoundService extends Service {

    private final IBinder mBinder = new CouponServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class CouponServiceBinder extends Binder {
        CouponsBoundService getService(){
            return CouponsBoundService.this;
        }
    }

    public String getCoupon(){
        return "get 10% off";
    }
}
