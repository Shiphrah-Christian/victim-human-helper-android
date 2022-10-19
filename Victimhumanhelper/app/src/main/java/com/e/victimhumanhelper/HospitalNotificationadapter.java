package com.e.victimhumanhelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HospitalNotificationadapter extends RecyclerView.Adapter<HospitalNotificationadapter.hospitalNotificationholder> {
    Context context;
    ArrayList<Hospital_notification_list> list;

    public HospitalNotificationadapter(Hospital_list hospital_list, ArrayList<Hospital_notification_list> arraychatlist) {
        this.context = hospital_list;
        this.list = arraychatlist;
    }

    @NonNull
    @Override
    public HospitalNotificationadapter.hospitalNotificationholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hospitalnotification,parent,false);
        return new HospitalNotificationadapter.hospitalNotificationholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalNotificationadapter.hospitalNotificationholder holder, int position) {
        holder.Message.setText(list.get(position).getMessage());
        holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hospitalNotificationholder extends RecyclerView.ViewHolder {
        TextView Message;
        TextView time;
        public hospitalNotificationholder(@NonNull View itemView) {
            super(itemView);
            Message = itemView.findViewById(R.id.custom_hospital_message);
            time= itemView.findViewById(R.id.custom_hospital_time);
        }
    }
}
