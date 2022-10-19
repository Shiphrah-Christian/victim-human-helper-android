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

class Ambulancehistoryadapter extends RecyclerView.Adapter<Ambulancehistoryadapter.ambulancehistoryholder>{
    Context context;
    ArrayList<Ambulancehistorylist> list;

    public Ambulancehistoryadapter(FragmentActivity activity, ArrayList<Ambulancehistorylist> arrayhistorylist) {
        this.context = activity;
        this.list = arrayhistorylist;
    }

    @NonNull
    @Override
    public Ambulancehistoryadapter.ambulancehistoryholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ambulancehistory,parent,false);
        return new Ambulancehistoryadapter.ambulancehistoryholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ambulancehistoryadapter.ambulancehistoryholder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.email.setText(list.get(position).getEmail());
        holder.history.setText(list.get(position).getStatus());
        holder.area.setText(list.get(position).getArea());
        holder.city.setText(list.get(position).getCity());
        holder.state.setText(list.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ambulancehistoryholder extends RecyclerView.ViewHolder {
        TextView name ;
        TextView contact;
        TextView email;
        TextView history ;
        TextView area ;
        TextView city ;
        TextView state ;

        public ambulancehistoryholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_ambulance_history_name);
            contact = itemView.findViewById(R.id.custom_ambulance_history_contact);
            email = itemView.findViewById(R.id.custom_ambulance_history_email);
            history = itemView.findViewById(R.id.custom_ambulance_history);
            area = itemView.findViewById(R.id.custom_ambulance_history_area);
            city = itemView.findViewById(R.id.custom_ambulance_history_city);
            state = itemView.findViewById(R.id.custom_ambulance_history_state);
        }
    }
}