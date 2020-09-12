package com.sldinkar.attendance;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList roll, name, className;
    DatabaseHelper myDB;

    public StudentsAdapter(Context context, Activity activity, ArrayList roll, ArrayList name, ArrayList className) {
        this.context = context;
        this.activity = activity;
        this.roll = roll;
        this.name = name;
        this.className = className;
    }

    public StudentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_students, parent, false);
        return new StudentsAdapter.MyViewHolder(view);
    }

    public int flagP = 0, flagA = 0;

    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.roll_txt1.setText(String.valueOf(roll.get(position)));
        holder.name_txt1.setText(String.valueOf(name.get(position)));
        final int Roll = Integer.parseInt(String.valueOf(roll.get(position)));

        holder.present_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagA == 1) {
                    myDB = new DatabaseHelper(v.getContext());
                    myDB.markAbsentUndo(ViewStudents.classNameReceived, Roll);
                    myDB.markPresent(ViewStudents.classNameReceived, Roll);
                    flagP = 1;
                    Toasty.success(v.getContext(), "MARKED PRESENT", Toast.LENGTH_SHORT, true).show();
                    holder.present_txt1.setEnabled(false);
                    holder.absent_txt1.setEnabled(true);
                } else {
                    myDB = new DatabaseHelper(v.getContext());
                    myDB.markPresent(ViewStudents.classNameReceived, Roll);
                    Toasty.success(v.getContext(), "MARKED PRESENT", Toast.LENGTH_SHORT, true).show();
                    flagP = 1;
                    holder.present_txt1.setEnabled(false);
                    holder.absent_txt1.setEnabled(true);
                }


            }
        });
        holder.absent_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flagP == 1) {
                    myDB = new DatabaseHelper(v.getContext());
                    myDB.markPresentUndo(ViewStudents.classNameReceived, Roll);
                    myDB.markAbsent(ViewStudents.classNameReceived, Roll);
                    flagA = 1;
                    Toasty.success(v.getContext(), "MARKED ABSENT", Toast.LENGTH_SHORT, true).show();
                    holder.absent_txt1.setEnabled(false);
                    holder.present_txt1.setEnabled(true);
                } else {
                    myDB = new DatabaseHelper(v.getContext());
                    myDB.markAbsent(ViewStudents.classNameReceived, Roll);
                    Toasty.success(v.getContext(), "MARKED ABSENT", Toast.LENGTH_SHORT, true).show();
                    flagA = 1;
                    holder.absent_txt1.setEnabled(false);
                    holder.present_txt1.setEnabled(true);
                }

            }
        });


    }


    public int getItemCount() {
        return roll.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView roll_txt1, name_txt1;
        LinearLayout mainLayout;
        Button present_txt1, absent_txt1;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roll_txt1 = itemView.findViewById(R.id.roll_txt1);
            name_txt1 = itemView.findViewById(R.id.name_txt1);
            present_txt1 = itemView.findViewById(R.id.present_txt1);
            absent_txt1 = itemView.findViewById(R.id.absent_txt1);

            mainLayout = itemView.findViewById(R.id.mainLayout1);
        }

    }
}
