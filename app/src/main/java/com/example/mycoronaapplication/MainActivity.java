package com.example.mycoronaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    String url = "https://w3qa5ydb4l.execute-api.eu-west-1.amazonaws.com/prod/finnishCoronaData/v2";
    private ArrayAdapter mAdapter;
    ListView listView;
    int pp, kp, lp, lappi, kainuu, ps, pk, vaasa, ep, ks, pm, es, sk, is, kh, ph, vs, hus, kl, ek, am, unknow;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.simpleList);
        mAdapter = new ArrayAdapter(this, R.layout.listviewitem, list){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                View v = convertView;
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.listviewitem, null);
                TextView text = v.findViewById(R.id.textView);
                text.setText(list.get(position));
                return v;

            }

        };

        listView.setAdapter(mAdapter);
        requestQueue = Volley.newRequestQueue(this);
        requestJson();
    }

    public void requestJson() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("respones", "saatu");
                JSONArray array = new JSONArray();
                try {
                    array = response.getJSONArray("confirmed");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject confirmed = array.getJSONObject(i);
                        String healtCareDistrict = confirmed.getString("healthCareDistrict");
                        if (healtCareDistrict.contains("HUS"))
                            hus++;
                        if (healtCareDistrict.contains("Pohjois-Pohjanmaa"))
                            pp++;
                        if (healtCareDistrict.contains("Keski-Pohjanmaa"))
                            kp++;
                        if (healtCareDistrict.contains("Länsi-Pohja"))
                            lp++;
                        if (healtCareDistrict.contains("Lappi"))
                            lappi++;
                        if (healtCareDistrict.contains("Kainuu"))
                            kainuu++;
                        if (healtCareDistrict.contains("Pohjois-Savo"))
                            ps++;
                        if (healtCareDistrict.contains("Pohjois-Karjala"))
                            pk++;
                        if (healtCareDistrict.contains("Vaasa"))
                            vaasa++;
                        if (healtCareDistrict.contains("Etelä-Pohjanmaa"))
                            ep++;
                        if (healtCareDistrict.contains("Keski-Suomi"))
                            ks++;
                        if (healtCareDistrict.contains("Päijät-Häme"))
                            ph++;
                        if (healtCareDistrict.contains("Etelä-Savo"))
                            es++;
                        if (healtCareDistrict.contains("Satakunta"))
                            sk++;
                        if (healtCareDistrict.contains("Itä-Savo"))
                            is++;
                        if (healtCareDistrict.contains("Kanta-Häme"))
                            kh++;
                        if (healtCareDistrict.contains("Pirkanmaa"))
                            pm++;
                        if (healtCareDistrict.contains("Varsinais-Suomi"))
                            vs++;
                        if (healtCareDistrict.contains("Kymenlaakso"))
                            kl++;
                        if (healtCareDistrict.contains("Etelä-Karjala"))
                            ek++;
                        if (healtCareDistrict.contains("Ahvenanmaa"))
                            am++;
                        if(healtCareDistrict.contains("null"))
                            unknow++;

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.add("HUS: "+String.valueOf(hus));
                mAdapter.add("Pohjois-Pohjanmaa: "+String.valueOf(pp));
                mAdapter.add("Keski-Pohjanmaa: "+String.valueOf(kp));
                mAdapter.add("Keski-Suomi: "+String.valueOf(ks));
                mAdapter.add("Etelä-Pohjanmaa: "+String.valueOf(ep));
                mAdapter.add("Etelä-Karjala: "+String.valueOf(ek));
                mAdapter.add("Pohjois-Karjala: "+String.valueOf(pk));
                mAdapter.add("Länsi-Pohja: "+String.valueOf(lp));
                mAdapter.add("Lappi: "+String.valueOf(lappi));
                mAdapter.add("Kainuu: "+String.valueOf(kainuu));
                mAdapter.add("Pohjois-Savo: "+String.valueOf(ps));
                mAdapter.add("Vaasa: "+String.valueOf(vaasa));
                mAdapter.add("Pirkanmaa: "+String.valueOf(pm));
                mAdapter.add("Etelä-Savo: "+String.valueOf(es));
                mAdapter.add("Satakunta: "+String.valueOf(sk));
                mAdapter.add("Itä-Savo: "+String.valueOf(is));
                mAdapter.add("Kanta-Häme: "+String.valueOf(kh));
                mAdapter.add("Päijät-Häme: "+String.valueOf(ph));
                mAdapter.add("Varsinais-Suomi: "+String.valueOf(vs));
                mAdapter.add("Kymenlaakso: "+String.valueOf(kl));
                mAdapter.add("Ahvenanmaa: "+String.valueOf(am));
                mAdapter.add("Tuntematon: "+String.valueOf(unknow));










            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
