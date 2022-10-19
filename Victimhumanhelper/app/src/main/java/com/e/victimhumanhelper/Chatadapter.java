package com.e.victimhumanhelper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

class Chatadapter extends RecyclerView.Adapter<Chatadapter.MyHolder> {

    Context context;
    ArrayList<Chatlist> list;

    public Chatadapter(FragmentActivity activity, ArrayList<Chatlist> arraychatlist) {
        this.context = activity;
        this.list = arraychatlist;
    }


    @NonNull
    @Override
    public Chatadapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_chat,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chatadapter.MyHolder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.iv.setImageResource(list.get(position).getImage());
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0)
                {
                    Intent intent = new Intent(context,Admin_Ambulance.class);
                    context.startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(context,Admin_TrafficPolice.class);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(context,Admin_Hospital.class);
                    context.startActivity(intent);
                }
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0)
                {
                    Intent intent = new Intent(context,Admin_Ambulance.class);
                    context.startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(context,Admin_TrafficPolice.class);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(context,Admin_Hospital.class);
                    context.startActivity(intent);
                }
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0)
                {
                    Intent intent = new Intent(context,Admin_Ambulance.class);
                    context.startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent = new Intent(context,Admin_TrafficPolice.class);
                    context.startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(context,Admin_Hospital.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
         return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView name;
        LinearLayout linearLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.custom_images);
            name = itemView.findViewById(R.id.custom_chat_names);
            linearLayout = itemView.findViewById(R.id.custom_chat_layout);
        }
    }

}
