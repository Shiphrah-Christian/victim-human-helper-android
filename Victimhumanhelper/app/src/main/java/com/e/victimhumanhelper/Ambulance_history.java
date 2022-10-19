package com.e.victimhumanhelper;


import android.app.ProgressDialog;
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
public class Ambulance_history extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Ambulancehistorylist> arrayhistorylist;
    String[] name = {"abcd","tyutyu"};
    String[] contact = {"9558790401","67686768"};
    String[] email = {"vihang@gmail.com","tgttuuytuy"};
    String[] area = {"fbsbfd","ytyutyut"};
    String[] city = {"fsjjjsf","yuyuiyiy"};
    String[] state = {"afffthjrt","yuiyiuu"};
    String[] history = {"accept","uyuyu"};


    public Ambulance_history() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ambulance_history, container, false);
        recyclerView = view.findViewById(R.id.ambu_history_lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(new ConnectionDetector(getActivity()).isConnectingToInternet()){
            new getData().execute();
        }
        else{
            new ConnectionDetector(getActivity()).connectiondetect();
        }

        /*arrayhistorylist = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            Ambulancehistorylist chatlist = new Ambulancehistorylist() ;
            chatlist.setName(name[i]);
            chatlist.setContact(contact[i]);
            chatlist.setEmail(email[i]);
            chatlist.setCity(city[i]);
            chatlist.setState(state[i]);
            chatlist.setArea(area[i]);
            chatlist.setHistory(history[i]);
            arrayhistorylist.add(chatlist);
        }
        Ambulancehistoryadapter adapter = new Ambulancehistoryadapter(getActivity(),arrayhistorylist);
        recyclerView.setAdapter(adapter);*/
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
            hashMap.put("status", "Accept");
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
                    arrayhistorylist = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Ambulancehistorylist chatlist = new Ambulancehistorylist();
                        chatlist.setId(jsonObject.getString("id"));
                        chatlist.setName(jsonObject.getString("name"));
                        chatlist.setContact(jsonObject.getString("contact"));
                        chatlist.setArea(jsonObject.getString("area"));
                        chatlist.setCity(jsonObject.getString("city"));
                        chatlist.setEmail(jsonObject.getString("email"));
                        chatlist.setState(jsonObject.getString("state"));
                        chatlist.setStatus(jsonObject.getString("status"));
                        arrayhistorylist.add(chatlist);
                    }
                    Ambulancehistoryadapter adapter = new Ambulancehistoryadapter(getActivity(),arrayhistorylist);
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
