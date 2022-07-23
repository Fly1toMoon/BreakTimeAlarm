package jp.ac.titech.itpro.sdl.breaktimealarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm;

public class AlarmEditActivity extends AppCompatActivity {
    static String TAG = "AlarmEditActivity";
    Alarm newAlarm;
    String type;
    TimePicker timePickerStart, timePickerEnd;
    ToggleButton btnMon, btnTue, btnWed, btnThu, btnFri, btnSat, btnSun;
    EditText edtTitle, intervalHour, intervalMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);
        mapping();
        Intent intent = getIntent();
        newAlarm = (Alarm) intent.getSerializableExtra("alarm");
        Log.i(TAG, newAlarm.toString());
        type = intent.getStringExtra("type");
        setDataToView();
    }


    public void mapping() {
        timePickerStart = findViewById(R.id.timePicker_start);
        timePickerEnd = findViewById(R.id.timePicker_end);
        timePickerStart.setIs24HourView(true);
        timePickerEnd.setIs24HourView(true);

        btnMon = findViewById(R.id.btn_Mon);
        btnTue = findViewById(R.id.btn_Tue);
        btnWed = findViewById(R.id.btn_Web);
        btnThu = findViewById(R.id.btn_Thu);
        btnFri = findViewById(R.id.btn_Fri);
        btnSat = findViewById(R.id.btn_Sat);
        btnSun = findViewById(R.id.btn_Sun);

        edtTitle = findViewById(R.id.edt_alarm_title);
        intervalHour = findViewById(R.id.alarm_interval_hour);
        intervalMinute = findViewById(R.id.alarm_interval_minute);

    }

    @SuppressLint("ResourceAsColor")
    public void setDataToView() {
        Log.i(TAG, newAlarm.toString());
        timePickerStart.setCurrentHour(newAlarm.getStartHour());
        timePickerStart.setCurrentMinute(newAlarm.getStartMinute());

        timePickerEnd.setCurrentHour(newAlarm.getEndtHour());
        timePickerEnd.setCurrentMinute(newAlarm.getEndMinute());

        edtTitle.setText(newAlarm.getTitle());
        intervalHour.setText(newAlarm.getStringIntervalHour());
        intervalMinute.setText(newAlarm.getStringIntervalMinute());

        btnMon.setChecked(newAlarm.isMon());
        btnTue.setChecked(newAlarm.isTue());
        btnWed.setChecked(newAlarm.isWed());
        btnThu.setChecked(newAlarm.isThu());
        btnFri.setChecked(newAlarm.isFri());
        btnSat.setChecked(newAlarm.isSat());
        btnSun.setChecked(newAlarm.isSun());

    }


    public void handleExit(View view) {
        AlarmEditActivity.super.onBackPressed();
    }

    public void handleSave(View view) {
        Log.i(TAG, "handle save");

        newAlarm.setMon(btnMon.isChecked());
        newAlarm.setTue(btnTue.isChecked());
        newAlarm.setWed(btnWed.isChecked());
        newAlarm.setThu(btnThu.isChecked());
        newAlarm.setFri(btnFri.isChecked());
        newAlarm.setSat(btnSat.isChecked());
        newAlarm.setSun(btnSun.isChecked());

        newAlarm.setTitle(edtTitle.getText().toString());
        newAlarm.setIntervalHour(Integer.parseInt(intervalHour.getText().toString()));
        newAlarm.setIntervalMinute(Integer.parseInt(intervalMinute.getText().toString()));

        newAlarm.setStartHour(timePickerStart.getCurrentHour());
        newAlarm.setStartMinute(timePickerStart.getCurrentMinute());
        newAlarm.setEndHour(timePickerEnd.getCurrentHour());
        newAlarm.setEndMinute(timePickerEnd.getCurrentMinute());

        if (checkAlarmOk(newAlarm)) {
            Intent intent = new Intent(AlarmEditActivity.this, MainActivity.class);
            intent.putExtra("newAlarm", newAlarm);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Please set the correct time.");
            b.setPositiveButton("Ok", (dialog, id) -> {
                dialog.cancel();
            });
            AlertDialog al = b.create();
            al.show();
        }
    }

    public boolean checkAlarmOk(Alarm alarm) {
        boolean dayOk = false;
        boolean timeOK = false;
        if (alarm.isMon() || alarm.isTue() || alarm.isWed() || alarm.isThu() || alarm.isFri() || alarm.isSat() || alarm.isSun()) {
            dayOk = true;
        }
        if (alarm.getStartHour() < alarm.getEndtHour() || (alarm.getStartHour() == alarm.getEndtHour() && alarm.getStartMinute() < alarm.getEndMinute())) {
            timeOK = true;
        }
        if (alarm.getIntervaltHour() == 0 && alarm.getIntervalMinute() == 0) {
            timeOK = false;
        }

        return (dayOk && timeOK);
    }

}
