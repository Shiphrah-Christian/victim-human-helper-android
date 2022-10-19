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
public class Hospital_history extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Hospitalhistorylist> arrayhistorylist;
    String[] name = {"abcd","gfg"};
    String[] contact = {"9558790401","67867686"};
    String[] address = {"artdfghgh","ghjvhvgvgvg"};
    String[] history = {"accept","hhjh"};


    public Hospital_history() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital_history, container, false);
        recyclerView = view.findViewById(R.id.hospital_history_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayhistorylist = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {

            Hospitalhistorylist chatlist = new Hospitalhistorylist();
            chatlist.setName(name[i]);
            chatlist.setContact(contact[i]);
            chatlist.setAddress(address[i]);
            chatlist.setHistory(history[i]);
            arrayhistorylist.add(chatlist);
        }
        Hospitalhistoryadapter adapter = new Hospitalhistoryadapter(getActivity(),arrayhistorylist);
        recyclerView.setAdapter(adapter);
        return view;

    }


}
