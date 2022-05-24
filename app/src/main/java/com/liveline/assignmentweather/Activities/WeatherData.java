package com.liveline.assignmentweather.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liveline.assignmentweather.R;
import com.liveline.assignmentweather.databinding.ActivityWeatherdataBinding;

import org.json.JSONObject;

public class WeatherData extends AppCompatActivity {

    ActivityWeatherdataBinding binding;
    String lat,lang,commonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_weatherdata);
        Intent i=getIntent();
        if(i!=null)
        {
             lat=i.getStringExtra("lat");
             lang=i.getStringExtra("lang");
             commonName=i.getStringExtra("common");
            binding.tv1.setText("Lat:-"+lat);
            binding.tv2.setText("Lang:-"+lang);
            binding.tv3.setText("CommonName:-"+commonName);
        }
        Api(lat,lang);
    }

    private void Api(String lat,String lang)
    {
        String url="https://api.open-meteo.com/v1/forecast?latitude="+lat+"&longitude="+lang+"&current_weather=true";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        Log.d("resp", "onResponse: "+response);
                        JSONObject temp=  response.optJSONObject("current_weather");
                        String te=temp.opt("temperature").toString();
                        Log.d("temp", "onResponse: "+te);
                        binding.tv.setText("Temperature is "+te+" degree");

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherData.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
