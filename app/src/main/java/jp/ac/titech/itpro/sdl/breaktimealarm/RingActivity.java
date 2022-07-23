package jp.ac.titech.itpro.sdl.breaktimealarm;

import static jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm.ALARM;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import jp.ac.titech.itpro.sdl.breaktimealarm.service.AlarmService;

public class RingActivity extends AppCompatActivity {

    TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        Intent intent = getIntent();
        txtLabel = findViewById(R.id.labelRing);

        String[] alarm = intent.getStringArrayExtra(ALARM);

        txtLabel.setText(alarm[3]);

        findViewById(R.id.floatBtnClose).setOnClickListener(view -> {
            Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
            getApplicationContext().stopService(intentService);
            finish();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
        getApplicationContext().stopService(intentService);
    }


}
