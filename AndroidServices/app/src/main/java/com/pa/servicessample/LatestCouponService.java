package com.pa.servicessample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Objects;

public class LatestCouponService extends Service {

    private int serviceRegNum = 0;
    private Object obj = new Object();
    private int notificationId = 22;

    @Override
    public void onCreate() {
        Toast.makeText(this, "service request "+serviceRegNum, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        synchronized (obj){
            serviceRegNum++;
        }
        Toast.makeText(this, "service request "+serviceRegNum+" "+startId, Toast.LENGTH_SHORT).show();

        boolean fg = Objects.requireNonNull(intent.getExtras()).getBoolean("foreground");
        if(fg){
            makeServiceForeground();
        }
        return START_NOT_STICKY;
    }

    private void makeServiceForeground(){
        Intent pi = new Intent(this, ServicesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, pi, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Coupons Service")
                .setContentText("Trying to get latest coupons")
                .setContentIntent(pendingIntent)
                .setTicker("Latest coupons")
                .build();
        startForeground(notificationId, notification);
        Toast.makeText(this, "foreground started ", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service destroyed "+serviceRegNum, Toast.LENGTH_SHORT).show();
    }
}
