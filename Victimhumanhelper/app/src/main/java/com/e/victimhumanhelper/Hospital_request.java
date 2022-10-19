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
public class Hospital_request extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HospitalRequestlist> arrayhosreqlist;
    String [] name = {"abcd","jbjk"};
    String [] contact = {"9558790401","huhyg"};
    String [] address = {"artdfghgh","656577t"};

    public Hospital_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital_request, container, false);
        recyclerView = view.findViewById(R.id.hospital_request_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayhosreqlist = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            HospitalRequestlist hosreqchatlist = new HospitalRequestlist();
            hosreqchatlist.setName(name[i]);
            hosreqchatlist.setContact(contact[i]);
            hosreqchatlist.setAddress(address[i]);
            arrayhosreqlist.add(hosreqchatlist);
        }
        HospitalRequestadapter adapter = new HospitalRequestadapter(getActivity(),arrayhosreqlist);
        recyclerView.setAdapter(adapter);
        return  view;
    }

}
