package com.e.victimhumanhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TrafficPolice_profile extends AppCompatActivity {

    EditText name,contact,password;
    Button edit,delete,logout,update;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_police_profile);
        getSupportActionBar().hide();

        sp = getSharedPreferences(ConstantURL.PREFERENCE, MODE_PRIVATE);
        name = findViewById(R.id.police_profile_name);
        contact = findViewById(R.id.police_profile_contact);
        password = findViewById(R.id.police_profile_password);
        edit = findViewById(R.id.police_profile_edit);
        delete = findViewById(R.id.police_profile_delete);
        logout = findViewById(R.id.police_profile_logout);
        update = findViewById(R.id.police_profile_update);


        name.setText(sp.getString(ConstantURL.NAME, ""));
        contact.setText(sp.getString(ConstantURL.CONTACT, ""));
        password.setText(sp.getString(ConstantURL.PASSWORD, ""));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                password.setEnabled(true);
                contact.setEnabled(true);
                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new ConnectionDetector(TrafficPolice_profile.this).isConnectingToInternet()) {
                    new updateData().execute();
                } else {
                    new ConnectionDetector(TrafficPolice_profile.this).connectiondetect();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new ConnectionDetector(TrafficPolice_profile.this).isConnectingToInternet()) {
                    new deleteprofile().execute();
                } else {
                    new ConnectionDetector(TrafficPolice_profile.this).connectiondetect();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(TrafficPolice_profile.this, TrafficPoliceLogin.class));

            }
        });
    }

    private class updateData  extends AsyncTask<String, String, String> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(TrafficPolice_profile.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", sp.getString(ConstantURL.ID, ""));
            hashMap.put("name", name.getText().toString());
            hashMap.put("contact", contact.getText().toString());
            hashMap.put("password", password.getText().toString());
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "police_update.php", MakeServiceCall.POST, hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(TrafficPolice_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    sp.edit().putString(ConstantURL.NAME, name.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.CONTACT, contact.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.PASSWORD, password.getText().toString()).commit();

                    name.setEnabled(false);
                    password.setEnabled(false);
                    contact.setEnabled(false);
                    edit.setVisibility(View.VISIBLE);
                    update.setVisibility(View.GONE);
                } else {
                    Toast.makeText(TrafficPolice_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class deleteprofile  extends AsyncTask<String, String, String> {


        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(TrafficPolice_profile.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", sp.getString(ConstantURL.ID, ""));
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "police_delete.php", MakeServiceCall.POST, hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(TrafficPolice_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    sp.edit().clear().commit();
                    startActivity(new Intent(TrafficPolice_profile.this, TrafficPoliceLogin.class));

                } else {
                    Toast.makeText(TrafficPolice_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
