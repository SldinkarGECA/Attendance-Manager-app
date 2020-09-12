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


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewClasses extends Fragment {

    public ViewClasses() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;

    DatabaseHelper myDB;
    ArrayList<String> className;
    ClassesAdapter classesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_classes, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        myDB = new DatabaseHelper(this.getActivity());
        className = new ArrayList<>();
        storeDataInArrays();

        classesAdapter = new ClassesAdapter(this.getActivity(), this.getContext(), className);
        recyclerView.setAdapter(classesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        return view;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.getClasses();
        if (cursor.getCount() == 0) {
            Toasty.error(this.getContext(), "NO DATA FOUND\nPlease Add Students First!",
                    Toast.LENGTH_SHORT, true).show();
        } else {
            while (cursor.moveToNext()) {
                className.add(cursor.getString(0));
            }
        }
    }
}
