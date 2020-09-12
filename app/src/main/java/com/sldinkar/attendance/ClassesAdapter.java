package com.sldinkar.attendance;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.MyViewHolder> {

    public static String classNamePaased;
    private Context context;
    private Activity activity;
    private ArrayList className;


    ClassesAdapter(Activity activity, Context context, ArrayList className) {
        this.activity = activity;
        this.context = context;
        this.className = className;

    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_classes, parent, false);
        return new MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.class_txt.setText(String.valueOf(className.get(position)));
        holder.class_Students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                classNamePaased = String.valueOf(className.get(position));
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new ViewStudents(), null)
                        .addToBackStack(null).commit();

            }
        });

    }


    public int getItemCount() {
        return className.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView class_txt;
        LinearLayout mainLayout;
        Button class_Students;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            class_txt = itemView.findViewById(R.id.class_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout1);
            class_Students = itemView.findViewById(R.id.class_students);

        }


    }


}
