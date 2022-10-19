package com.e.victimhumanhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Ambulance_main extends AppCompatActivity {

    ImageButton profile;
    Button submit,submitHospital;
    Spinner stateSpinner, citySpinner, areaSpinner,hospitalSpinner;
    ArrayList<String> arrayStateName;
    ArrayList<String> arrayStateId;
    ArrayList<String> arrayCityName;
    ArrayList<String> arrayCityId;
    ArrayList<String> arrayAreaName;
    ArrayList<String> arrayAreaId;

    ArrayList<String> arrayHospitalName;
    ArrayList<String> arrayHospitalId;

    String sStateName,sStateId,sCityName,sCityId,sAreaName,sAreaId,sHospitalName,sHospitalId;
    SharedPreferences sp;

    TextView hospitalName;

    LinearLayout hospitalLayout,cityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_main);
        getSupportActionBar().hide();
        sp = getSharedPreferences(ConstantURL.PREFERENCE,MODE_PRIVATE);

        hospitalLayout = findViewById(R.id.ambulance_hospital);
        cityLayout = findViewById(R.id.ambulance_city_layout);


        hospitalLayout.setVisibility(View.VISIBLE);
        cityLayout.setVisibility(View.GONE);

        profile = findViewById(R.id.profile_ambulance);
        hospitalName = findViewById(R.id.ambulance_hospital_name);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ambulance_main.this, Ambulance_profile.class);
                startActivity(intent);
            }
        });

        submitHospital = findViewById(R.id.ambulance_hospital_submit);
        hospitalSpinner = findViewById(R.id.ambulance_hospital_sp);

        submit = findViewById(R.id.ambulance_submit);
        stateSpinner = findViewById(R.id.ambulance_state);
        citySpinner = findViewById(R.id.ambulance_city);
        areaSpinner = findViewById(R.id.ambulance_area);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new ConnectionDetector(Ambulance_main.this).isConnectingToInternet()){
                    new addNotification().execute();
                }
                else{
                    new ConnectionDetector(Ambulance_main.this).connectiondetect();
                }
            }
        });

        if(new ConnectionDetector(Ambulance_main.this).isConnectingToInternet()){
            new getState().execute();
            new getHospital().execute();
        }
        else{
            new ConnectionDetector(Ambulance_main.this).connectiondetect();
        }



        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sStateName = arrayStateName.get(i);
                sStateId = arrayStateId.get(i);

                new getCity().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sCityName = arrayCityName.get(i);
                sCityId = arrayCityId.get(i);
                new getArea().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sAreaName = arrayAreaName.get(i);
                sAreaId = arrayAreaId.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hospitalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sHospitalName = arrayHospitalName.get(i);
                sHospitalId = arrayHospitalId.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new ConnectionDetector(Ambulance_main.this).isConnectingToInternet()){
                    new addHospitalNotification().execute();
                }
                else{
                    new ConnectionDetector(Ambulance_main.this).connectiondetect();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private class getHospital extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"getHospital.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    JSONArray array = object.getJSONArray("response");
                    arrayHospitalName = new ArrayList<>();
                    arrayHospitalId = new ArrayList<>();
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        arrayHospitalId.add(jsonObject.getString("id"));
                        arrayHospitalName.add(jsonObject.getString("name"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Ambulance_main.this,android.R.layout.simple_list_item_1,arrayHospitalName);
                    hospitalSpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getState extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"getState.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    JSONArray array = object.getJSONArray("response");
                    arrayStateName = new ArrayList<>();
                    arrayStateId = new ArrayList<>();
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        arrayStateId.add(jsonObject.getString("id"));
                        arrayStateName.add(jsonObject.getString("name"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Ambulance_main.this,android.R.layout.simple_list_item_1,arrayStateName);
                    stateSpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getCity extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("stateId",sStateId);
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"getCity.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    JSONArray array = object.getJSONArray("response");
                    arrayCityName = new ArrayList<>();
                    arrayCityId = new ArrayList<>();
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        arrayCityId.add(jsonObject.getString("id"));
                        arrayCityName.add(jsonObject.getString("name"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Ambulance_main.this,android.R.layout.simple_list_item_1,arrayCityName);
                    citySpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getArea extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("cityId",sCityId);
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"getArea.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    JSONArray array = object.getJSONArray("response");
                    arrayAreaName = new ArrayList<>();
                    arrayAreaId = new ArrayList<>();
                    for(int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        arrayAreaId.add(jsonObject.getString("id"));
                        arrayAreaName.add(jsonObject.getString("name"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(Ambulance_main.this,android.R.layout.simple_list_item_1,arrayAreaName);
                    areaSpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class addNotification extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("areaId",sAreaId);
            hashMap.put("areaName",sAreaName);
            hashMap.put("userId",sp.getString(ConstantURL.ID,""));
            hashMap.put("message","Please Clear Traffics For Emergency Vehicle");
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"addTrafficNotification.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class addHospitalNotification extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_main.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("hospitalId",sHospitalId);
            hashMap.put("hospitalName",sHospitalName);
            hashMap.put("userId",sp.getString(ConstantURL.ID,""));
            hashMap.put("message","Emergency Vehicle Coming With Patient. Please Preapare For Treatment");
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"addHospitalNotification.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    hospitalLayout.setVisibility(View.GONE);
                    cityLayout.setVisibility(View.VISIBLE);
                    hospitalName.setText("Hospital Name : "+sHospitalName);
                }
                else{
                    Toast.makeText(Ambulance_main.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
