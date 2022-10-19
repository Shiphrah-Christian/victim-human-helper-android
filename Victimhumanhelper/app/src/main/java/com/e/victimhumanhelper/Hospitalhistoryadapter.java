package com.e.victimhumanhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Hospitalhistoryadapter extends RecyclerView.Adapter<Hospitalhistoryadapter.hospitalhistoryholder> {
    Context context;
    ArrayList<Hospitalhistorylist> list;

    public Hospitalhistoryadapter(FragmentActivity activity, ArrayList<Hospitalhistorylist> arrayhistorylist) {
        this.context = activity;
        this.list = arrayhistorylist;
    }

    @NonNull
    @Override
    public Hospitalhistoryadapter.hospitalhistoryholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hospitalhistory,parent,false);
        return new Hospitalhistoryadapter.hospitalhistoryholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Hospitalhistoryadapter.hospitalhistoryholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.address.setText(list.get(position).getAddress());
        holder.history.setText(list.get(position).getHistory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hospitalhistoryholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView address;
        TextView history;
        public hospitalhistoryholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_hospital_history_name);
            contact = itemView.findViewById(R.id.custom_hospital_history_contact);
            address = itemView.findViewById(R.id.custom_hospital_history_address);
            history = itemView.findViewById(R.id.custom_hospital_history);
        }
    }
}
