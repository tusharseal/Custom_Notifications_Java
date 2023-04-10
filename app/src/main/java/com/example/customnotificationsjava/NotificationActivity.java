package com.example.customnotificationsjava;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 100;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), FLAG_ACTIVITY_CLEAR_TASK);

        btn1 = findViewById(R.id.btn1);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.notifications, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;


        //Inbox Style

        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("Hello")
                .addLine("How Are You")
                .addLine("Fine")
                .addLine("Thanks")
                .addLine("Where are you?")
                .addLine("Congratulations")
                .addLine("Come back")
                .addLine("Goodbye")
                .setBigContentTitle("Inbox Style")
                .setSummaryText("Message Can't Be More Than 7 Lines");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notifications)
                    .setContentText("New Message")
                    .setSubText("Message From Tushar")
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .build();
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        } else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.notifications)
                    .setContentText("New Message")
                    .setSubText("Message From Tushar")
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true)
                    .setOngoing(false)
                    .build();
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm.notify(NOTIFICATION_ID, notification);
            }
        });
    }
}