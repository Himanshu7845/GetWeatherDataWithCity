package com.liveline.assignmentweather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.liveline.assignmentweather.Model.MainModel;
import com.liveline.assignmentweather.Adapter.MyHomeAdapter;
import com.liveline.assignmentweather.R;
import com.liveline.assignmentweather.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<MainModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        countryApi();


    }

    private void countryApi() {
        String url = "https://restcountries.com/v3.1/all";
        binding.load.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response)
                    {
                        binding.load.setVisibility(View.GONE);
                        arrayList = new ArrayList<>();
                        Log.d("countryApi", "onResponse: " + response);
                        for (int i = 0; i < response.length(); i++)
                        {
                            JSONObject name = response.optJSONObject(i).optJSONObject("name");
                            String official = name.optString("official");
                            String common = name.optString("common");

                            // Log.d("official", "onResponse: "+official);
                            JSONArray latLang = response.optJSONObject(i).optJSONArray("latlng");
                            String lat = latLang.optString(0);
                            String lang = latLang.optString(1);

                            arrayList.add(new MainModel(official,common, lat, lang));

                            binding.rec.setAdapter(new MyHomeAdapter(getApplicationContext(), arrayList));
                            binding.rec.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            Log.d("latLangs", "onResponse: " + lat + " " + lang);

//                            for(int j=0;j<latLang.length();j++)
//                            {
//                                Log.d("official", "onResponse: "+official+" "+latLang.opt(j));
//                                Log.d("length", "onResponse: "+latLang.length());
//
//                                Log.d("latLangs", "onResponse: "+lat+" "+lang);
//
//
//                            }

                        }




                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

    }

}
