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
import java.util.prefs.BackingStoreException;

class HospitalRequestadapter extends RecyclerView.Adapter<HospitalRequestadapter.hospitalrequestholder>  {
    Context context;
    ArrayList<HospitalRequestlist> list;

    public HospitalRequestadapter(FragmentActivity activity, ArrayList<HospitalRequestlist> arraychatlist) {
        this.context = activity;
        this.list = arraychatlist;
    }

    @NonNull
    @Override
    public HospitalRequestadapter.hospitalrequestholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hospitalrequest,parent,false);
        return new HospitalRequestadapter.hospitalrequestholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalRequestadapter.hospitalrequestholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class hospitalrequestholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView address;
        public hospitalrequestholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_hospital_name);
            contact = itemView.findViewById(R.id.custom_hospital_contact);
            address = itemView.findViewById(R.id.custom_hospital_address);
        }
    }
}
