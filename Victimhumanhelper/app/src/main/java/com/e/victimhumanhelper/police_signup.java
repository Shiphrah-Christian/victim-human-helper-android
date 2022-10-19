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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class police_signup extends AppCompatActivity {

    EditText name,contact,password;
    Button submit;
    String fcmId;
    Spinner stateSpinner, citySpinner, areaSpinner;
    ArrayList<String> arrayStateName;
    ArrayList<String> arrayStateId;
    ArrayList<String> arrayCityName;
    ArrayList<String> arrayCityId;
    ArrayList<String> arrayAreaName;
    ArrayList<String> arrayAreaId;

    String sStateName,sStateId,sCityName,sCityId,sAreaName,sAreaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_signup);
        getSupportActionBar().hide();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        fcmId = pref.getString("regId", null);
        name = findViewById(R.id.police_signup_name);
        contact = findViewById(R.id.police_signup_contact);
        password = findViewById(R.id.police_signup_password);
        submit = findViewById(R.id.police_signup_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().equals("")&& contact.getText().toString().trim().equals("")&&password.getText().toString().trim().equals("") )
                {
                    name.setError("Name required");
                    contact.setError("Contact required");
                    password.setError("Password required");

                }

                else if (name.getText().toString().equals(""))
                {
                    name.setError("Name required");
                }
                else if (contact.getText().toString().equals(""))
                {
                    contact.setError("Contact required");
                }
                else if (password.getText().toString().equals(""))
                {
                    password.setError("Password required");
                }
                else
                {
                    if(new ConnectionDetector(police_signup.this).isConnectingToInternet()){
                        new addData().execute();
                    }
                    else{
                        new ConnectionDetector(police_signup.this).connectiondetect();
                    }
                }
            }
        });

        stateSpinner = findViewById(R.id.police_signup_state);
        citySpinner = findViewById(R.id.police_signup_city);
        areaSpinner = findViewById(R.id.police_signup_area);

        if(new ConnectionDetector(police_signup.this).isConnectingToInternet()){
            new getState().execute();
        }
        else{
            new ConnectionDetector(police_signup.this).connectiondetect();
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
        
    }

    private class getState extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(police_signup.this);
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
                    ArrayAdapter adapter = new ArrayAdapter(police_signup.this,android.R.layout.simple_list_item_1,arrayStateName);
                    stateSpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(police_signup.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
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
            pd = new ProgressDialog(police_signup.this);
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
                    ArrayAdapter adapter = new ArrayAdapter(police_signup.this,android.R.layout.simple_list_item_1,arrayCityName);
                    citySpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(police_signup.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
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
            pd = new ProgressDialog(police_signup.this);
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
                    ArrayAdapter adapter = new ArrayAdapter(police_signup.this,android.R.layout.simple_list_item_1,arrayAreaName);
                    areaSpinner.setAdapter(adapter);
                }
                else{
                    Toast.makeText(police_signup.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    
    public class addData extends AsyncTask<String,String,String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(police_signup.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("name",name.getText().toString());
            hashMap.put("contact",contact.getText().toString());
            hashMap.put("password",password.getText().toString());
            hashMap.put("area",sAreaName);
            hashMap.put("city",sCityName);
            hashMap.put("state",sStateName);
            hashMap.put("fcmId",fcmId);
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL+"police_user_signup.php",MakeServiceCall.POST,hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if(object.getString("Status").equals("True")){
                    Toast.makeText(police_signup.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(police_signup.this,TrafficPoliceLogin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
                else{
                    Toast.makeText(police_signup.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
