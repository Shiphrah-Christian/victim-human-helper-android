package com.e.victimhumanhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class TrafficRequestadapter extends RecyclerView.Adapter<TrafficRequestadapter.trafficrequestholder> {
    Context context;
    ArrayList<TrafficRequestlist> list;
    public TrafficRequestadapter(FragmentActivity activity, ArrayList<TrafficRequestlist> arraychatlist) {
        this.context = activity;
        this.list = arraychatlist;
    }

    @NonNull
    @Override
    public TrafficRequestadapter.trafficrequestholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_trafficrequest,parent,false);
        return new TrafficRequestadapter.trafficrequestholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficRequestadapter.trafficrequestholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class trafficrequestholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView address;
        public trafficrequestholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_traffic_name);
            contact = itemView.findViewById(R.id.custom_traffic_contact);
            address = itemView.findViewById(R.id.custom_traffic_address);
        }

    }

}
