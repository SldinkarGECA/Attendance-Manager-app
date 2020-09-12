package com.sldinkar.attendance;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


public class ViewAttendance extends Fragment {

    public ViewAttendance() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;

    DatabaseHelper myDB;
    ArrayList<String> roll, name, present, absent, className;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_attendance, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        myDB = new DatabaseHelper(this.getActivity());
        roll = new ArrayList<>();
        name = new ArrayList<>();
        present = new ArrayList<>();
        absent = new ArrayList<>();
        className = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(this.getActivity(), this.getContext(), roll, name, present, absent, className);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toasty.error(this.getContext(), "NO DATA FOUND\nPlease Add Students First!",
                    Toast.LENGTH_SHORT, true).show();
        } else {
            while (cursor.moveToNext()) {
                roll.add(cursor.getString(0));
                name.add(cursor.getString(1));
                present.add(cursor.getString(2));
                absent.add(cursor.getString(3));
                className.add(cursor.getString(4));
            }
        }
    }
}
