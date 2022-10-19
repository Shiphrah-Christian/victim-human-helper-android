package com.e.victimhumanhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class TrafficNotificationadapter extends RecyclerView.Adapter<TrafficNotificationadapter.trafficNotificationholder> {
    Context context;
    ArrayList<Traffic_notification_list> list;

    public TrafficNotificationadapter(Traffic_police_list traffic_police_list, ArrayList<Traffic_notification_list> arraychatlist) {
        this.context = traffic_police_list;
        this.list = arraychatlist;
    }

    @NonNull
    @Override
    public TrafficNotificationadapter.trafficNotificationholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_trafficnotification,parent,false);
        return new TrafficNotificationadapter.trafficNotificationholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficNotificationadapter.trafficNotificationholder holder, int position) {
        holder.Message.setText(list.get(position).getMessage());
        holder.time.setText(list.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class trafficNotificationholder extends RecyclerView.ViewHolder {
        TextView Message;
        TextView time;
        public trafficNotificationholder(@NonNull View itemView) {
            super(itemView);
            Message = itemView.findViewById(R.id.custom_traffic_message);
            time= itemView.findViewById(R.id.custom_traffic_time);
        }
    }
}
