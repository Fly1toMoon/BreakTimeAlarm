package jp.ac.titech.itpro.sdl.breaktimealarm.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;
import androidx.room.Room;

import java.util.List;

import jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.AlarmDao;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.AlarmDatabase;

public class RescheduleAlarmsService extends LifecycleService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        AlarmDatabase db = Room.databaseBuilder(getApplicationContext(), AlarmDatabase.class, "database-alarm").allowMainThreadQueries().build();
        AlarmDao alarmDao = db.alarmDao();
        List<Alarm> alarmList = alarmDao.getAlarms();
        for (Alarm a : alarmList) {
            if (a.isActive()) {
                a.schedule(getApplicationContext());
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
        super.onBind(intent);
        return null;
    }
}