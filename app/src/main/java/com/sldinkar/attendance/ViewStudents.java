package com.sldinkar.attendance;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ViewStudents extends Fragment {

    public ViewStudents() {

    }

    RecyclerView recyclerView;
    public static String classNameReceived;
    DatabaseHelper myDB;
    ArrayList<String> roll, name, classes;
    StudentsAdapter StudentsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_students, container, false);

        recyclerView = view.findViewById(R.id.recyclerView1);
        myDB = new DatabaseHelper(this.getActivity());
        roll = new ArrayList<>();
        name = new ArrayList<>();
        classes = new ArrayList<>();
//        Bundle bundle=getArguments();
//        String ClassNameReceived=bundle.getString("ClassNamePassed");
//        storeStudentsInArrays(ClassNameReceived);
        ClassesAdapter classesAdapter = new ClassesAdapter(this.getActivity(), this.getContext(), classes);
        String clsNAme = classesAdapter.classNamePaased;
        classNameReceived = clsNAme;
        Log.d("class", "onCreateView: " + clsNAme);
        storeStudentsInArrays(clsNAme);

        StudentsAdapter = new StudentsAdapter(this.getContext(), this.getActivity(), roll, name, classes);
        recyclerView.setAdapter(StudentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    void storeStudentsInArrays(String ClassName) {
        String cls = ClassName;
        Cursor cursor = myDB.readStudentNames(cls);
        if (cursor.getCount() == 0) {
            Toasty.error(this.getContext(), "NO DATA FOUND\nPlease Add Students First!",
                    Toast.LENGTH_SHORT, true).show();
            //Thread.sleep(Toast.LENGTH_SHORT);
        } else {
            while (cursor.moveToNext()) {
                roll.add(cursor.getString(0));
                name.add(cursor.getString(1));
            }
        }
    }

}
