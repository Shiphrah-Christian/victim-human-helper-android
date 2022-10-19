package com.e.victimhumanhelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class AmbulanceRequestadapter extends RecyclerView.Adapter<AmbulanceRequestadapter.ambulancerequestholder> {
    Context context;
    ArrayList<AmbulanceRequestlist> list;
    String sId, sStatus;

    public AmbulanceRequestadapter(FragmentActivity activity, ArrayList<AmbulanceRequestlist> arraychatlist) {
        this.context = activity;
        this.list = arraychatlist;
    }

    @NonNull
    @Override
    public AmbulanceRequestadapter.ambulancerequestholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_ambulancerequest, parent, false);
        return new AmbulanceRequestadapter.ambulancerequestholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanceRequestadapter.ambulancerequestholder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.contact.setText(list.get(position).getContact());
        holder.email.setText(list.get(position).getEmail());
        holder.area.setText(list.get(position).getArea());
        holder.city.setText(list.get(position).getCity());
        holder.state.setText(list.get(position).getState());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = list.get(position).getId();
                sStatus = "Accepted";
                if (new ConnectionDetector(context).isConnectingToInternet()) {
                    new updateStatus().execute();
                } else {
                    new ConnectionDetector(context).connectiondetect();
                }
            }
        });

        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sId = list.get(position).getId();
                sStatus = "Cancelled";
                if (new ConnectionDetector(context).isConnectingToInternet()) {
                    new updateStatus().execute();
                } else {
                    new ConnectionDetector(context).connectiondetect();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ambulancerequestholder extends RecyclerView.ViewHolder {
        TextView name;
        TextView contact;
        TextView email;
        TextView area;
        TextView city;
        TextView state;
        Button accept, decline;

        public ambulancerequestholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.custom_ambulance_name);
            contact = itemView.findViewById(R.id.custom_ambulance_contact);
            email = itemView.findViewById(R.id.custom_ambulance_email);
            area = itemView.findViewById(R.id.custom_ambulance_area);
            city = itemView.findViewById(R.id.custom_ambulance_city);
            state = itemView.findViewById(R.id.custom_ambulance_state);
            accept = itemView.findViewById(R.id.custom_ambulance_accept);
            decline = itemView.findViewById(R.id.custom_ambulance_decline);
        }
    }

    private class updateStatus extends AsyncTask<String, String, String> {


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Please Wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", sId);
            hashMap.put("status", sStatus);
            return new MakeServiceCall().MakeServiceCall(ConstantURL.URL + "updateAmbulanceUser.php", MakeServiceCall.POST, hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();

            try {
                JSONObject object = new JSONObject(s);
                if (object.getString("Status").equals("True")) {
                    Toast.makeText(context, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, Admin_Ambulance.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                } else {
                    Toast.makeText(context, object.getString("Message"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
