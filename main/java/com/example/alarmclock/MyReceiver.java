package com.example.alarmclock;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.codsoftalarmclock.R;

public class MyReceiver extends BroadcastReceiver {
    static final int ALARM_REQ_CODE = 0;
    @Override
    public void onReceive(Context context, Intent intent){
        Vibrator vib = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(2000);

        Intent iBroadCast = new Intent(context, SetAlarm.class);
        iBroadCast.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pi = PendingIntent.getBroadcast(context, ALARM_REQ_CODE, iBroadCast, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Notify")
                .setSmallIcon(R.drawable.ic_baseline_access_alarm)
                .setContentTitle("Alarm Clock")  // Fixed the method name to setContentTitle
                .setContentText("Reminder...Wake Up!!!!")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pi);

        NotificationManagerCompat nmCompat = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        nmCompat.notify(200, builder.build());

        Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/"  + R.raw.alarm_tone);

        Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
        ringtone.play();
    }
}
