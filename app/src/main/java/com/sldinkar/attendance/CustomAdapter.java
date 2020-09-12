package com.sldinkar.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    DatabaseHelper myDB;
    private ArrayList roll, name, present, absent, className;


    CustomAdapter(Activity activity, Context context, ArrayList roll, ArrayList name, ArrayList present,
                  ArrayList absent, ArrayList className) {
        this.activity = activity;
        this.context = context;
        this.roll = roll;
        this.name = name;
        this.present = present;
        this.absent = absent;
        this.className = className;

    }


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.roll_txt.setText(String.valueOf(roll.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.present_txt.setText("PRESENT: " + String.valueOf(present.get(position)));
        holder.absent_txt.setText("ABSENT: " + String.valueOf(absent.get(position)));
        holder.class_txt.setText(String.valueOf(className.get(position)));
        final int Roll = Integer.parseInt(String.valueOf(roll.get(position)));
        final String Class = String.valueOf(className.get(position));
//        holder.student.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                myDB=new DatabaseHelper(v.getContext());
//                double perAtt=myDB.getAttendancePercentage(Class,Roll)*100;
//                 if(perAtt<75)
//                 {
//                     holder.student.setBackgroundColor(Color.RED);
//                     holder.l3.setBackgroundColor(Color.RED);
//                 }
//
//
//                return false;
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return roll.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roll_txt, name_txt, present_txt, absent_txt, class_txt;
        LinearLayout student, l3;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roll_txt = itemView.findViewById(R.id.roll_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            present_txt = itemView.findViewById(R.id.present_txt);
            absent_txt = itemView.findViewById(R.id.absent_txt);
            class_txt = itemView.findViewById(R.id.class_txt);
            student = itemView.findViewById(R.id.studentBackground);
            l3 = itemView.findViewById(R.id.linearLayout3);
        }

    }

}
