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

public class Ambulance_profile extends AppCompatActivity {

    EditText name, contact, password, area, city, state, email;
    Button edit, delete, logout, update;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_profile);
        getSupportActionBar().hide();

        sp = getSharedPreferences(ConstantURL.PREFERENCE, MODE_PRIVATE);
        name = findViewById(R.id.ambu_profile_name);
        contact = findViewById(R.id.ambu_profile_contact);
        password = findViewById(R.id.ambu_profile_password);
        area = findViewById(R.id.ambu_profile_area);
        city = findViewById(R.id.ambu_profile_city);
        state = findViewById(R.id.ambu_profile_state);
        email = findViewById(R.id.ambu_profile_email);
        edit = findViewById(R.id.ambu_profile_edit);
        delete = findViewById(R.id.ambu_profile_delete);
        logout = findViewById(R.id.ambu_profile_logout);
        update = findViewById(R.id.ambu_profile_update);

        name.setText(sp.getString(ConstantURL.NAME, ""));
        contact.setText(sp.getString(ConstantURL.CONTACT, ""));
        password.setText(sp.getString(ConstantURL.PASSWORD, ""));
        area.setText(sp.getString(ConstantURL.AREA, ""));
        city.setText(sp.getString(ConstantURL.CITY, ""));
        state.setText(sp.getString(ConstantURL.STATE, ""));
        email.setText(sp.getString(ConstantURL.EMAIL, ""));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
                password.setEnabled(true);
                contact.setEnabled(true);
                area.setEnabled(true);
                city.setEnabled(true);
                state.setEnabled(true);
                email.setEnabled(true);
                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new ConnectionDetector(Ambulance_profile.this).isConnectingToInternet()) {
                    new updateData().execute();
                } else {
                    new ConnectionDetector(Ambulance_profile.this).connectiondetect();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (new ConnectionDetector(Ambulance_profile.this).isConnectingToInternet()) {
                    new deleteprofile().execute();
                } else {
                    new ConnectionDetector(Ambulance_profile.this).connectiondetect();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().clear().commit();
                startActivity(new Intent(Ambulance_profile.this, Ambulance_Login.class));

            }
        });

    }

    private class updateData extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_profile.this);
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
            hashMap.put("email", email.getText().toString());
            hashMap.put("area", area.getText().toString());
            hashMap.put("city", city.getText().toString());
            hashMap.put("state", state.getText().toString());
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "ambulance_update.php", MakeServiceCall.POST, hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(Ambulance_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    sp.edit().putString(ConstantURL.NAME, name.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.CONTACT, contact.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.PASSWORD, password.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.EMAIL, email.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.AREA, area.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.CITY, city.getText().toString()).commit();
                    sp.edit().putString(ConstantURL.STATE, state.getText().toString()).commit();

                    name.setEnabled(false);
                    password.setEnabled(false);
                    contact.setEnabled(false);
                    email.setEnabled(false);
                    area.setEnabled(false);
                    city.setEnabled(false);
                    state.setEnabled(false);
                    edit.setVisibility(View.VISIBLE);
                    update.setVisibility(View.GONE);
                } else {
                    Toast.makeText(Ambulance_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class deleteprofile extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_profile.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", sp.getString(ConstantURL.ID, ""));
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "ambulance_delete.php", MakeServiceCall.POST, hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(Ambulance_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    sp.edit().clear().commit();
                    startActivity(new Intent(Ambulance_profile.this, Ambulance_Login.class));

                } else {
                    Toast.makeText(Ambulance_profile.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
