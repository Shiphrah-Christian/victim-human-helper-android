package com.e.victimhumanhelper;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Trafficpolice_history extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Traffichistorylist> arrayhistorylist;
    String [] name = {"abcd","asbnbsa"};
    String [] contact = {"9558790401","452654625624"};
    String [] address = {"artdfghgh","64768731643764"};
    String [] history = {"accept","decline"};


    public Trafficpolice_history() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trafficpolice_history, container, false);
        recyclerView = view.findViewById(R.id.traffic_history_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayhistorylist = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            Traffichistorylist chatlist = new Traffichistorylist();
            chatlist.setName(name[i]);
            chatlist.setContact(contact[i]);
            chatlist.setAddress(address[i]);
            chatlist.setHistory(history[i]);
            arrayhistorylist.add(chatlist);
        }
        Traffichistoryadapter adapter = new Traffichistoryadapter(getActivity(),arrayhistorylist);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
