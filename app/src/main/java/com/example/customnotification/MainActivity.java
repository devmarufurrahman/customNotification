package com.example.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private  static final  String CHANNEL_ID = "My Channel";
    private  static final  int NOTIFICATION_ID = 100;
    private static final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Drawable drawableImgBig = ResourcesCompat.getDrawable(getResources(),R.drawable.notification, null);
        BitmapDrawable bitmapDrawableImg =  (BitmapDrawable) drawableImgBig;
        Bitmap largeIcon = bitmapDrawableImg.getBitmap();

        Notification notification;
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pi = PendingIntent.getActivity(this,REQ_CODE,iNotify,PendingIntent.FLAG_UPDATE_CURRENT);

        // Big picture Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable)(ResourcesCompat.getDrawable(getResources(),R.drawable.notification, null))).getBitmap())
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Image sent by maruf")
                .setSummaryText("Image Message");

//        Inbox style
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("A")
                .addLine("b")
                .addLine("c")
                .addLine("d")
                .addLine("e")
                .addLine("f")
                .addLine("g")
                .setBigContentTitle("Full Message")
                .setSummaryText("Message from Maruf");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification)
                    .setContentText("New Notification")
                    .setSubText("Your new notification is arrival")
                    .setContentIntent(pi)
                    .setStyle(bigPictureStyle)
                    .setChannelId(CHANNEL_ID)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notification)
                    .setContentText("New Notification")
                    .setSubText("Your new notification is arrival")
                    .setStyle(bigPictureStyle)
                    .setContentIntent(pi)
                    .build();
        }

        nm.notify(NOTIFICATION_ID,notification);

    }
}