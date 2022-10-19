package com.e.victimhumanhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button ambulance, trafficpolice, hospital;
    Context context = this;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ambulance = findViewById(R.id.ambulance);
        trafficpolice = findViewById(R.id.trafficpolice);
        hospital = findViewById(R.id.hospital);

        sp = getSharedPreferences(ConstantURL.PREFERENCE,MODE_PRIVATE);

        if(sp.getString(ConstantURL.TYPE,"").equals("Ambulance")){
            startActivity(new Intent(MainActivity.this,Ambulance_main.class));
            finish();
        }
        else if(sp.getString(ConstantURL.TYPE,"").equals("Hospital")){
            startActivity(new Intent(MainActivity.this,Hospital_main.class));
            finish();
        }
        else if(sp.getString(ConstantURL.TYPE,"").equals("Police")){
            startActivity(new Intent(MainActivity.this,TrafficPolice_main.class));
            finish();
        }
        else{

        }

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                }
            }
        };

        displayFirebaseRegId();

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Ambulance_Login.class);
                startActivity(intent);
            }
        });

        trafficpolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrafficPoliceLogin.class);
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HospitalLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    public class Message {
        public void displayMessage(int resultCode, Bundle resultData) {
            String message = resultData.getString("message");
            //Toast.makeText(DashboardActivity.this, resultCode+""+message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }


    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
        //Log.d("RESPONSE",regId);
        /*if (!TextUtils.isEmpty(regId)) {
            Call<GetAddFCMData> call = apiService.getAddFCMData("addFcmId", regId);
            call.enqueue(new Callback<GetAddFCMData>() {
                @Override
                public void onResponse(Call<GetAddFCMData> call, Response<GetAddFCMData> response) {
                    if (response.body().status.equalsIgnoreCase("True")) {

                    } else {
                    }
                }

                @Override
                public void onFailure(Call<GetAddFCMData> call, Throwable t) {
                    //Toast.makeText(AdminDashboardActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            //txtRegId.setText("Firebase Reg Id is not received yet!");
        }*/
    }

}
