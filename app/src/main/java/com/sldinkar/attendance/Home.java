package com.sldinkar.attendance;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class Home extends Fragment implements View.OnClickListener {

    public Home() {
        // Required empty public constructor
    }

    DatabaseHelper myDb;
    private Button create_class, take_attendance, view_attendance, about_app;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        create_class = view.findViewById(R.id.create_class);
        take_attendance = view.findViewById(R.id.take_attendance);
        view_attendance = view.findViewById(R.id.view_attendance);
        about_app = view.findViewById(R.id.about_app);
        create_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new AddStudents(), null)
                        .addToBackStack(null).commit();
            }
        });
        myDb = new DatabaseHelper(getContext());

        take_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new ViewClasses(), null)
                        .addToBackStack(null).commit();
            }
        });

        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new ViewAttendance(), null)
                        .addToBackStack(null).commit();
            }
        });

//        about_app.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showMessage("About App","This is a simple user-friendly\n" +
//                        "app which can be used by teachers\n" +
//                        "to track student attendance.\n" +
//                        "This is a lightweight app which\n" +
//                        "saves device memory and you\n" +
//                        "can use it without the need\n" +
//                        "for any internet connectivity.\n" +
//                        "\n\n\nAll Rights Reserved @SD\n" +
//                        "Thanks for installing the app\n" +
//                        "Stay tuned for new Updates :)");
//            }
//        });
        about_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("About App", "This is a simple user-friendly" +
                        "app which can be used by teachers" +
                        "to track student attendance.\n" +
                        "This is a lightweight app which" +
                        " saves device memory and you" +
                        " can use it without the need" +
                        " of any internet connectivity.\n" +
                        "\n\nAll Rights Reserved.\n" +
                        "Thanks for installing the app.\n" +
                        "Stay tuned for new Updates :)");
            }
        });


        return view;
    }


    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toasty.info(getContext(), "Thank You!!",
                        Toast.LENGTH_SHORT, true).show();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.Container, new Home(), null)
                        .addToBackStack(null).commit();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View v) {

    }
}
