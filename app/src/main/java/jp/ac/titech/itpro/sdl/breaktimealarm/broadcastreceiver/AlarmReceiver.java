package jp.ac.titech.itpro.sdl.breaktimealarm.broadcastreceiver;

import static jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm.ALARM;
import static jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm.REPEAT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

import jp.ac.titech.itpro.sdl.breaktimealarm.service.AlarmService;
import jp.ac.titech.itpro.sdl.breaktimealarm.service.RescheduleAlarmsService;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            startRescheduleAlarmsService(context);
        } else {
            if (alarmIsToday(intent)) {
                startAlarmService(context, intent);
            }
        }
    }

    private boolean alarmIsToday(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        boolean[] repeat = intent.getBooleanArrayExtra(REPEAT);

        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.MONDAY:
                return (repeat[0]);
            case Calendar.TUESDAY:
                return (repeat[1]);
            case Calendar.WEDNESDAY:
                return (repeat[2]);
            case Calendar.THURSDAY:
                return (repeat[3]);
            case Calendar.FRIDAY:
                return (repeat[4]);
            case Calendar.SATURDAY:
                return (repeat[5]);
            case Calendar.SUNDAY:
                return (repeat[6]);
        }
        return false;
    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmService.class);
        String[] alarm = intent.getStringArrayExtra(ALARM);
        intentService.putExtra(ALARM, alarm);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }

    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }
}

