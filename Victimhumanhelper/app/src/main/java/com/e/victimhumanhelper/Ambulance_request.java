package com.e.victimhumanhelper;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ambulance_request extends Fragment {

    RecyclerView recyclerView;
    ArrayList<AmbulanceRequestlist> arraychatlist;
    String [] area = {"abcd","cchhc"};
    String [] city = {"ahmedabad","fgfgh"};
    String [] state = {"gujarat","fgfgh"};
    String [] name = {"abcd","fgfhg"};
    String [] contact = {"9558790401","67686867686"};
    String [] email = {"artdfghgh@gmail.com","ytryry"};

    public Ambulance_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ambulance_request, container, false);
        recyclerView = view.findViewById(R.id.ambu_request_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()){
            new getData().execute();
        }
        else{
            new ConnectionDetector(getActivity()).connectiondetect();
        }

        return view;
    }

    private class getData extends AsyncTask<String, String, String> {


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("status", "Pending");
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "getAmbulanceUser.php", MakeServiceCall.POST, hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    JSONArray array = object.getJSONArray("response");
                    arraychatlist = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        AmbulanceRequestlist chatlist = new AmbulanceRequestlist();
                        chatlist.setId(jsonObject.getString("id"));
                        chatlist.setName(jsonObject.getString("name"));
                        chatlist.setContact(jsonObject.getString("contact"));
                        chatlist.setArea(jsonObject.getString("area"));
                        chatlist.setCity(jsonObject.getString("city"));
                        chatlist.setEmail(jsonObject.getString("email"));
                        chatlist.setState(jsonObject.getString("state"));
                        arraychatlist.add(chatlist);
                    }
                    AmbulanceRequestadapter adapter = new AmbulanceRequestadapter(getActivity(),arraychatlist);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
