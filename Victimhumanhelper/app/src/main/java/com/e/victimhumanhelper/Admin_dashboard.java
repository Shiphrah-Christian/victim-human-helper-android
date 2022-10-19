package com.e.victimhumanhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Admin_dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    String [] users = {"Ambulance Driver","Traffic Police","Hospital"};
    int [] images = {R.drawable.ambulance,R.drawable.trafficpolice,R.drawable.hospital};
    ArrayList<Chatlist> arraychatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.admin_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(Admin_dashboard.this));
        arraychatlist = new ArrayList<>();

        for (int i = 0; i < users.length; i++) {
            Chatlist chatlist = new Chatlist();
            chatlist.setName(users[i]);
            chatlist.setImage(images[i]);
            arraychatlist.add(chatlist);
        }
        Chatadapter adapter = new Chatadapter(Admin_dashboard.this,arraychatlist);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
