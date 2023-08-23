package com.example.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private  static final  String CHANNEL_ID = "My Channel";
    private  static final  int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Drawable drawableImgBig = ResourcesCompat.getDrawable(getResources(),R.drawable.notification, null);
        BitmapDrawable bitmapDrawableImg =  (BitmapDrawable) drawableImgBig;
        Bitmap largeIcon = bitmapDrawableImg.getBitmap();

        Notification notification;
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification)
                    .setContentText("New Notification")
                    .setSubText("Your new notification is arrival")
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification)
                    .setContentText("New Notification")
                    .setSubText("Your new notification is arrival")
                    .build();
        }

        nm.notify(NOTIFICATION_ID,notification);

    }
}