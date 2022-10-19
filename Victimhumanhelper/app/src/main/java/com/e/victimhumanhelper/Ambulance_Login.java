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

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Ambulance_Login extends AppCompatActivity {

    EditText email, password;
    ImageButton signup;
    Button login;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance__login);
        getSupportActionBar().hide();

        sp = getSharedPreferences(ConstantURL.PREFERENCE, MODE_PRIVATE);
        signup = findViewById(R.id.ambulance_Signup);
        login = findViewById(R.id.login_ambulance);
        email = findViewById(R.id.ambu_login_email);
        password = findViewById(R.id.ambu_login_password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ambulance_Login.this, AmbulancesSignup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().trim().equals("") && password.getText().toString().equals("")) {
                    email.setError("Email ID required");
                    password.setError("Password required");
                } else if (email.getText().toString().trim().equals("")) {
                    email.setError("Email ID required");
                } else if (password.getText().toString().equals("")) {
                    password.setError("Password required");
                } else {
                    if (email.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("admin")) {
                        sp.edit().putString(ConstantURL.TYPE, "Admin").commit();
                        sp.edit().putString(ConstantURL.ID, "0").commit();
                        sp.edit().putString(ConstantURL.NAME, "Admin").commit();
                        sp.edit().putString(ConstantURL.CONTACT, "9876543210").commit();
                        sp.edit().putString(ConstantURL.PASSWORD, "admin").commit();
                        sp.edit().putString(ConstantURL.AREA, "Ashramroad").commit();
                        sp.edit().putString(ConstantURL.CITY, "Ahmedabad").commit();
                        sp.edit().putString(ConstantURL.STATE, "Gujarat").commit();
                        sp.edit().putString(ConstantURL.EMAIL, "admin@gmail.com").commit();
                        startActivity(new Intent(Ambulance_Login.this, Admin_dashboard.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else {
                        if (new ConnectionDetector(Ambulance_Login.this).isConnectingToInternet()) {
                            new loginData().execute();
                        } else {
                            new ConnectionDetector(Ambulance_Login.this).connectiondetect();
                        }
                    }
                }

            }
        });
    }

    private class loginData extends AsyncTask<String, String, String> {


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Ambulance_Login.this);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("email", email.getText().toString());
            hashMap.put("password", password.getText().toString());
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "Ambulance_login.php", MakeServiceCall.POST, hashMap);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(Ambulance_Login.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    JSONArray array = object.getJSONArray("response");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        sp.edit().putString(ConstantURL.TYPE, "Ambulance").commit();
                        sp.edit().putString(ConstantURL.ID, jsonObject.getString("id")).commit();
                        sp.edit().putString(ConstantURL.NAME, jsonObject.getString("name")).commit();
                        sp.edit().putString(ConstantURL.CONTACT, jsonObject.getString("contact")).commit();
                        sp.edit().putString(ConstantURL.PASSWORD, jsonObject.getString("password")).commit();
                        sp.edit().putString(ConstantURL.AREA, jsonObject.getString("area")).commit();
                        sp.edit().putString(ConstantURL.CITY, jsonObject.getString("city")).commit();
                        sp.edit().putString(ConstantURL.STATE, jsonObject.getString("state")).commit();
                        sp.edit().putString(ConstantURL.EMAIL, jsonObject.getString("email")).commit();

                        Intent intent = new Intent(Ambulance_Login.this, Ambulance_main.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Ambulance_Login.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
