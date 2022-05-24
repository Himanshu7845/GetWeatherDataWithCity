package com.liveline.assignmentweather.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.liveline.assignmentweather.Activities.WeatherData;
import com.liveline.assignmentweather.Model.MainModel;
import com.liveline.assignmentweather.R;

import java.util.ArrayList;

public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.myViewHolder>
{
    Context context;
    ArrayList<MainModel> arrayList;

    public MyHomeAdapter(Context context, ArrayList<MainModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.singlerow,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
       holder.country.setText(arrayList.get(position).getOfficial());
       holder.common.setText("Common Name:-"+arrayList.get(position).getCommon());
       String lat=arrayList.get(position).getLat();
       String lang=arrayList.get(position).getLang();
        Log.d("latLangss", "onBindViewHolder: "+lat+" "+lang);
        holder.latLang.setText("Lat:-"+lat+" "+"Lang:-"+lang);

        holder.arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expandableView.getVisibility()== View.GONE){
                    TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    //  TransitionManager.beginDelayedTransition(binding.cardView, new A);
                    holder.expandableView.setVisibility(View.GONE);
                    holder.arrowBtn.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), WeatherData.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("lat",arrayList.get(position).getLat());
                intent.putExtra("lang",arrayList.get(position).getLang());
                intent.putExtra("common",arrayList.get(position).getCommon());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView country,latLang,common;
        Button arrowBtn;
        ConstraintLayout expandableView;
        CardView cardView;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            country=itemView.findViewById(R.id.country);
            latLang=itemView.findViewById(R.id.latLang);
            common=itemView.findViewById(R.id.common);
            arrowBtn=itemView.findViewById(R.id.arrowBtn);
            expandableView=itemView.findViewById(R.id.expandableView);
            cardView=itemView.findViewById(R.id.cardView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(itemView.getContext(), WeatherData.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("",);
//                    itemView.getContext().startActivity(intent);
//                }
//            });
        }
    }
}
