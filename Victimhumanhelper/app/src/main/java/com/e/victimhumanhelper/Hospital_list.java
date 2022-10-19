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

public class Hospital_list extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Hospital_notification_list> arraychatlist;
    String [] Message = {"abcd"};
    String [] time = {"30 jan 2020  10:00 pm"};
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantURL.PREFERENCE,MODE_PRIVATE);
        recyclerView = findViewById(R.id.hospital_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(Hospital_list.this));

        if (new ConnectionDetector(Hospital_list.this).isConnectingToInternet()) {
            new getData().execute();
        } else {
            new ConnectionDetector(Hospital_list.this).connectiondetect();
        }

    }

    private class getData extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Hospital_list.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("hospitalId",sp.getString(ConstantURL.ID,""));
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"hospitalNotification.php",MakeServiceCall.POST,hashMap);

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
                        Hospital_notification_list chatlist = new Hospital_notification_list();
                        chatlist.setMessage(jsonObject.getString("message"));
                        chatlist.setTime(jsonObject.getString("created_date"));
                        arraychatlist.add(chatlist);
                    }
                    HospitalNotificationadapter adapter = new HospitalNotificationadapter(Hospital_list.this,arraychatlist);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(Hospital_list.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
