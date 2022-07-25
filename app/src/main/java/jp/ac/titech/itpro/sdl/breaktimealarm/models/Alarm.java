package jp.ac.titech.itpro.sdl.breaktimealarm.models;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

import jp.ac.titech.itpro.sdl.breaktimealarm.broadcastreceiver.AlarmReceiver;


@Entity(tableName = "alarm_table")
public class Alarm implements Serializable {
    public static final String ALARM = "ALARM";
    public static final String REPEAT = "REPEAT";
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int alarmId;

    public int start_hour, start_minute, end_hour, end_minute, interval_hour, interval_minute;
    public String title;
    public boolean isActive, Mon, Tue, Wed, Thu, Fri, Sat, Sun;

    public Alarm(int alarmId, int start_hour, int start_minute, int end_hour, int end_minute, int interval_hour, int interval_minute, String title, int volume, boolean isVibration, boolean isActive, boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun) {
        this.alarmId = alarmId;
        this.start_hour = start_hour;
        this.start_minute = start_minute;
        this.end_hour = end_hour;
        this.end_minute = end_minute;
        this.interval_hour = interval_hour;
        this.interval_minute = interval_minute;
        this.title = title;
        this.isActive = isActive;
        Mon = mon;
        Tue = tue;
        Wed = wed;
        Thu = thu;
        Fri = fri;
        Sat = sat;
        Sun = sun;
    }

    public Alarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.MONDAY:
                setMon(true);
                break;
            case Calendar.TUESDAY:
                setTue(true);
                break;
            case Calendar.WEDNESDAY:
                setWed(true);
                break;
            case Calendar.THURSDAY:
                setThu(true);
                break;
            case Calendar.FRIDAY:
                setFri(true);
                break;
            case Calendar.SATURDAY:
                setSat(true);
                break;
            case Calendar.SUNDAY:
                setSun(true);
                break;
        }
        this.isActive = true;
    }

    public Alarm(int start_hour, int start_minute, int end_hour, int end_minute, int interval_hour, int interval_minute, String title) {
        this.start_hour = start_hour;
        this.start_minute = start_minute;
        this.end_hour = end_hour;
        this.end_minute = end_minute;
        this.interval_hour = interval_hour;
        this.interval_minute = interval_minute;
        this.title = title;
    }

    public boolean isMon() {
        return Mon;
    }

    public void setMon(boolean mon) {
        Mon = mon;
    }

    public boolean isTue() {
        return Tue;
    }

    public void setTue(boolean tue) {
        Tue = tue;
    }

    public boolean isWed() {
        return Wed;
    }

    public void setWed(boolean wed) {
        Wed = wed;
    }

    public boolean isThu() {
        return Thu;
    }

    public void setThu(boolean thu) {
        Thu = thu;
    }

    public boolean isFri() {
        return Fri;
    }

    public void setFri(boolean fri) {
        Fri = fri;
    }

    public boolean isSat() {
        return Sat;
    }

    public void setSat(boolean sat) {
        Sat = sat;
    }

    public boolean isSun() {
        return Sun;
    }

    public void setSun(boolean sun) {
        Sun = sun;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public int getStartHour() {
        return start_hour;
    }

    public String getStringStartHour() {
        if (start_hour < 10)
            return "0" + start_hour;
        return String.valueOf(start_hour);
    }

    public void setStartHour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getStartMinute() {
        return start_minute;
    }

    public String getStringStartMinute() {
        if (start_minute < 10)
            return "0" + start_minute;
        return String.valueOf(start_minute);
    }

    public void setStartMinute(int start_minute) {
        this.start_minute = start_minute;
    }

    public int getEndtHour() {
        return end_hour;
    }

    public String getStringEndHour() {
        if (end_hour < 10)
            return "0" + end_hour;
        return String.valueOf(end_hour);
    }

    public void setEndHour(int end_hour) {
        this.end_hour = end_hour;
    }

    public int getEndMinute() {
        return end_minute;
    }

    public String getStringEndMinute() {
        if (end_minute < 10)
            return "0" + end_minute;
        return String.valueOf(end_minute);
    }

    public void setEndMinute(int end_minute) {
        this.end_minute = end_minute;
    }

    public int getIntervaltHour() {
        return interval_hour;
    }

    public String getStringIntervalHour() {
        if (interval_hour == 0 && interval_minute == 0)
            interval_hour = 1;
        return String.valueOf(interval_hour);
    }

    public void setIntervalHour(int interval_hour) {
        this.interval_hour = interval_hour;
    }

    public int getIntervalMinute() {
        return interval_minute;
    }

    public String getStringIntervalMinute() {
        if (interval_minute < 10)
            return "0" + interval_minute;
        return String.valueOf(interval_minute);
    }

    public void setIntervalMinute(int interval_minute) {
        this.interval_minute = interval_minute;
    }

    public String getTitle() {
        if (title == null)
            return "Title";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRepeatToString() {
        String s = "";
        int count = 0;
        if (Mon) {
            if (count > 0) {
                s += ",Mon";
            } else {
                s += "Mon";
            }
            count++;
        }

        if (Tue) {
            if (count > 0) {
                s += ",Tue";
            } else {
                s += "Tue";
            }
            count++;
        }

        if (Wed) {
            if (count > 0) {
                s += ",Wed";
            } else {
                s += "Wed";
            }
            count++;
        }

        if (Thu) {
            if (count > 0) {
                s += ",Thu";
            } else {
                s += "Thu";
            }
            count++;
        }

        if (Fri) {
            if (count > 0) {
                s += ",Fri";
            } else {
                s += "Fri";
            }
            count++;
        }

        if (Sat) {
            if (count > 0) {
                s += ",Sat";
            } else {
                s += "Sat";
            }
            count++;

        }

        if (Sun) {
            if (count > 0) {
                s += ",Sun";
            } else {
                s += "Sun";
            }
            count++;
        }


        if (count == 7) {
            s = "Weekdays";
        }

        return s;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarmId=" + alarmId +
                ", start_hour=" + start_hour +
                ", start_minute=" + start_minute +
                ", end_hour=" + end_hour +
                ", end_minute=" + end_minute +
                ", interval_hour=" + interval_hour +
                ", interval_minute=" + interval_minute +
                ", title='" + title + '\'' +
                ", isActive=" + isActive +
                ", Mon=" + Mon +
                ", Tue=" + Tue +
                ", Wed=" + Wed +
                ", Thu=" + Thu +
                ", Fri=" + Fri +
                ", Sat=" + Sat +
                ", Sun=" + Sun +
                '}';
    }

    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.i("Schedule", "Create schedule");

        Intent intent = new Intent(context, AlarmReceiver.class);
        String[] alarm = new String[]{getStringStartHour() + ":" + getStringStartMinute(), getStringEndHour() + ":" + getStringEndMinute(), getStringIntervalHour() + ":" + getStringIntervalMinute(), title, String.valueOf(alarmId)};

        boolean[] repeat = new boolean[]{isMon(), isTue(), isWed(), isThu(), isFri(), isSat(), isSun()};
        intent.putExtra(ALARM, alarm);
        intent.putExtra(REPEAT, repeat);

        int pendingFlags;
        if (Build.VERSION.SDK_INT >= 23) {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, alarmId, intent, pendingFlags
        );

        int interval = getIntervaltHour() * 60 + getIntervalMinute();
        int hour_mem = getStartHour(), minute_mem = getStartMinute();
        int difference = (getEndtHour() - getStartHour()) * 60 + (getEndMinute() - getStartMinute());
        for (int i = 1; i <= difference / interval; i++) {
            hour_mem += (minute_mem + interval) / 60;
            minute_mem = (minute_mem + interval) % 60;

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour_mem);
            calendar.set(Calendar.MINUTE, minute_mem);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        pendingIntent
                );
                break;
            }
        }

    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        int pendingFlags;
        if (Build.VERSION.SDK_INT >= 23) {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE;
        } else {
            pendingFlags = PendingIntent.FLAG_UPDATE_CURRENT;
        }
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, pendingFlags);
        alarmManager.cancel(alarmPendingIntent);
        this.setActive(false);
        String toastText = String.format("Alarm cancelled.");
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        Log.i("cancel", toastText);

    }


}
