package com.e.victimhumanhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Traffichistoryadapter extends RecyclerView.Adapter<Traffichistoryadapter.traffichistoryholder>{
    Context context;
    ArrayList<Traffichistorylist> list;

    public Traffichistoryadapter(FragmentActivity activity, ArrayList<Traffichistorylist> arrayhistorylist) {
        this.context = activity;
        this.list = arrayhistorylist;
        }

    @NonNull
    @Override
    public Traffichistoryadapter.traffichistoryholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_traffichistory,parent,false);
        return new traffichistoryholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Traffichistoryadapter.traffichistoryholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.address.setText(list.get(position).getAddress());
        holder.history.setText(list.get(position).getHistory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class traffichistoryholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView address;
        TextView history;
        public traffichistoryholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_traffic_history_name);
            contact = itemView.findViewById(R.id.custom_traffic_history_contact);
            address = itemView.findViewById(R.id.custom_traffic_history_address);
            history = itemView.findViewById(R.id.custom_traffic_history);
        }
    }

}
