package com.e.victimhumanhelper;


import android.app.DownloadManager;
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
public class Trafficpolice_request extends Fragment {


    RecyclerView recyclerView;
    ArrayList<TrafficRequestlist> arraychatlist;
    String [] name = {"abcd","dfgf"};
    String [] contact = {"9558790401","4547654546"};
    String [] address = {"artdfghgh","dfdfdfdg"};
    public Trafficpolice_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trafficpolice_request, container, false);
        recyclerView = view.findViewById(R.id.traffic_request_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arraychatlist = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            TrafficRequestlist chatlist = new TrafficRequestlist();
            chatlist.setName(name[i]);
            chatlist.setContact(contact[i]);
            chatlist.setAddress(address[i]);
            arraychatlist.add(chatlist);
        }
        TrafficRequestadapter adapter = new TrafficRequestadapter(getActivity(),arraychatlist);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
