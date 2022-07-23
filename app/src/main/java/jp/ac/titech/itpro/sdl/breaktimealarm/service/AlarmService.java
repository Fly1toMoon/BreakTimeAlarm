package jp.ac.titech.itpro.sdl.breaktimealarm.service;

import static jp.ac.titech.itpro.sdl.breaktimealarm.application.App.CHANNEL_ID;
import static jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm.ALARM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.room.Room;

import java.util.List;

import jp.ac.titech.itpro.sdl.breaktimealarm.R;
import jp.ac.titech.itpro.sdl.breaktimealarm.RingActivity;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.AlarmDao;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.AlarmDatabase;


public class AlarmService extends Service {
    private static final String TAG = "AlarmService";

    private List<Alarm> alarmList;
    AlarmDatabase db;
    AlarmDao alarmDao;

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(), AlarmDatabase.class, "database-alarm").allowMainThreadQueries().build();
        alarmDao = db.alarmDao();
        alarmList = alarmDao.getAlarms();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        Intent notificationIntent = new Intent(this, RingActivity.class);
        String[] alarm = intent.getStringArrayExtra(ALARM);
        notificationIntent.putExtra(ALARM, alarm);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "breaknotify", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(alarm[3])
                    .setContentText("Take a break and stretch your body!")
                    .setSmallIcon(R.drawable.ic_icon)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            manager.notify(123, notification);
            startForeground(Integer.parseInt(alarm[4]), notification);
        } else {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(alarm[3])
                    .setContentText("Take a break and stretch your body!")
                    .setSmallIcon(R.drawable.ic_icon)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            manager.notify(123, notification);
            startForeground(Integer.parseInt(alarm[4]), notification);
        }

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(notificationIntent);

        int label = Integer.parseInt(alarm[4]);
        for (int i = 0; i < alarmList.size(); i++) {
            Alarm realarm = alarmList.get(i);
            if (realarm.getAlarmId() == label) {
                realarm.schedule(this);
                break;
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
