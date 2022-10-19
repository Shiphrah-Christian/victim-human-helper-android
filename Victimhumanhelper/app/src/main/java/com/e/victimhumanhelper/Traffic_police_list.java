package com.e.victimhumanhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Traffic_police_list extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Traffic_notification_list> arraychatlist;
    String [] Message = {"abcd"};
    String [] time = {"30 jan 2020  10:00 pm"};
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_police_list);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantURL.PREFERENCE,MODE_PRIVATE);
        recyclerView = findViewById(R.id.traffic_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(Traffic_police_list.this));

        if (new ConnectionDetector(Traffic_police_list.this).isConnectingToInternet()) {
            new getData().execute();
        } else {
            new ConnectionDetector(Traffic_police_list.this).connectiondetect();
        }
        arraychatlist = new ArrayList<>();
        for (int i = 0; i < Message.length; i++) {
            Traffic_notification_list chatlist = new Traffic_notification_list();
            chatlist.setMessage(Message[i]);
            chatlist.setTime(time[i]);
            arraychatlist.add(chatlist);
        }
        TrafficNotificationadapter adapter = new TrafficNotificationadapter(Traffic_police_list.this,arraychatlist);
        recyclerView.setAdapter(adapter);

    }

    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Traffic_police_list.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("trafficId",sp.getString(ConstantURL.ID,""));
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"trafficNotification.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    arraychatlist = new ArrayList<>();
                    JSONArray array=object.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        Traffic_notification_list chatlist = new Traffic_notification_list();
                        chatlist.setMessage(jsonObject.getString("message"));
                        chatlist.setTime(jsonObject.getString("created_date"));
                        arraychatlist.add(chatlist);
                    }
                    TrafficNotificationadapter adapter = new TrafficNotificationadapter(Traffic_police_list.this,arraychatlist);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(Traffic_police_list.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
