package jp.ac.titech.itpro.sdl.breaktimealarm.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jp.ac.titech.itpro.sdl.breaktimealarm.R;
import jp.ac.titech.itpro.sdl.breaktimealarm.models.Alarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmHolder> {

    private final List<Alarm> alarmList;
    private final IAlarmClick iAlarmClick;

    public AlarmAdapter(List<Alarm> alarmList, IAlarmClick iAlarmClick) {
        this.alarmList = alarmList;
        this.iAlarmClick = iAlarmClick;
    }

    @NonNull
    @Override
    public AlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarms, parent, false);
        return new AlarmHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AlarmHolder holder, @SuppressLint("RecyclerView") int position) {
        Alarm alarm = alarmList.get(position);
        holder.tvAlarmItemTimeStart.setText(alarm.getStringStartHour() + ":" + alarm.getStringStartMinute());
        holder.tvAlarmItemTimeEnd.setText(alarm.getStringEndHour() + ":" + alarm.getStringEndMinute());
        holder.tvAlarmItemTimeInterval.setText(alarm.getStringIntervalHour() + ":" + alarm.getStringIntervalMinute());
        holder.tvAlarmItemRepeat.setText(alarm.getRepeatToString());
        holder.tvAlarmItemTitle.setText(alarm.getTitle());
        holder.swAlarmItemActive.setChecked(alarm.isActive());

        holder.itemView.setOnClickListener(view -> {
            iAlarmClick.onItemClick(position);
        });
        holder.itemView.setOnLongClickListener(view -> {
            iAlarmClick.onItemLongClick(position);
            return false;
        });

        holder.swAlarmItemActive.setOnClickListener(view -> {
            iAlarmClick.onActiveClick(position, holder.swAlarmItemActive.isChecked());
        });

    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public static class AlarmHolder extends RecyclerView.ViewHolder {
        TextView tvAlarmItemRepeat;
        TextView tvAlarmItemTimeStart;
        TextView tvAlarmItemTimeEnd;
        TextView tvAlarmItemTimeInterval;
        TextView tvAlarmItemTitle;
        Switch swAlarmItemActive;

        public AlarmHolder(@NonNull View itemView) {
            super(itemView);
            tvAlarmItemTitle = itemView.findViewById(R.id.tv_alarm_title);
            tvAlarmItemRepeat = itemView.findViewById(R.id.tv_alarm_repeat);
            tvAlarmItemTimeStart = itemView.findViewById(R.id.tv_alarm_time_start);
            tvAlarmItemTimeEnd = itemView.findViewById(R.id.tv_alarm_time_end);
            tvAlarmItemTimeInterval = itemView.findViewById(R.id.tv_alarm_time_interval);
            swAlarmItemActive = itemView.findViewById(R.id.sw_alarm_item_active);
        }
    }
}
