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
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class AddStudents extends Fragment {

    public AddStudents() {
        // Required empty public constructor
    }

    DatabaseHelper myDb;
    EditText Roll, Name;
    Button Add, Delete, Done, DeleteAll;
    Button Create;
    EditText Class, Branch, Subject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_students, container, false);
        Roll = view.findViewById(R.id.Roll);
        Name = view.findViewById(R.id.Name);
        Add = view.findViewById(R.id.Add);
        Delete = view.findViewById(R.id.Delete);
        DeleteAll = view.findViewById(R.id.DeleteAll);
        Done = view.findViewById(R.id.Done);

        Class = view.findViewById(R.id.Class);
        Branch = view.findViewById(R.id.Branch);
        Subject = view.findViewById(R.id.Subject);
        myDb = new DatabaseHelper(getContext());

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classValue = "", branchValue = "", subjectValue = "", className = "";

                int roll = 0;
                String name = null;
                try {
                    roll = Integer.parseInt(Roll.getText().toString());
                    name = Name.getText().toString();
                    classValue = Class.getText().toString();
                    branchValue = Branch.getText().toString();
                    subjectValue = Subject.getText().toString();
                } catch (NumberFormatException e) {
                    roll = 0;
                    name = "";
                    classValue = "";
                    branchValue = "";
                    subjectValue = "";
                }
                className = classValue + branchValue + subjectValue;


                if (roll == 0 ||
                        Name.getText().toString() == "" ||
                        Class.getText().toString() == "" ||
                        Branch.getText().toString() == "" ||
                        Subject.getText().toString() == "") {
                    Toasty.error(getContext(), "Please Fill the all fields", Toast.LENGTH_SHORT,
                            true).show();
//                    //MainActivity.fragmentManager.beginTransaction().replace
//                            (R.id.Container,new AddStudents(),null).addToBackStack(null).commit();

                } else if (myDb.CheckStudent(roll, name, className) == true) {
                    Toasty.error(getContext(), "Student Already Exists!", Toast.LENGTH_SHORT,
                            true).show();
                } else {
                    boolean isInserted = false;
                    try {
                        isInserted = myDb.insertData(roll, name, className);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (isInserted == true) {
                        Toasty.success(getContext(), "Student Added Successfully!",
                                Toast.LENGTH_SHORT, true).show();
                        Roll.setText("");
                        Name.setText("");
                    } else {
                        Toasty.error(getContext(), "Something Went Wrong!", Toast.LENGTH_SHORT,
                                true).show();
                    }
                    Log.d("TAG", "" + roll + name);
                }


            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getContext(), "You are on Homepage Now!!",
                        Toast.LENGTH_SHORT, true).show();
                MainActivity.fragmentManager.beginTransaction().replace
                        (R.id.Container, new Home(), null).addToBackStack(null).commit();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.create();
                builder.setCancelable(true);
                builder.setTitle("Warning!!");
                builder.setMessage("Are you Sure to delete Student Data?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.success(getContext(), "Cancelled!!",
                                Toast.LENGTH_SHORT, true).show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String classValue = "", branchValue = "", subjectValue = "", className = "";

                        int roll = 0;
                        String name = null;
                        try {
                            roll = Integer.parseInt(Roll.getText().toString());
                            name = Name.getText().toString();
                            classValue = Class.getText().toString();
                            branchValue = Branch.getText().toString();
                            subjectValue = Subject.getText().toString();
                        } catch (NumberFormatException e) {
                            roll = 0;
                            name = "";
                            classValue = "";
                            branchValue = "";
                            subjectValue = "";
                        }
                        className = classValue + branchValue + subjectValue;

                        if (roll == 0 || Name.getText().toString() == "" || Class.getText().toString() == ""
                                || Branch.getText().toString() == "" || Subject.getText().toString() == "") {
                            Toasty.error(getContext(), "Please Fill the all fields", Toast.LENGTH_SHORT,
                                    true).show();
                        } else if (myDb.CheckStudent(roll, name, className) == false) {
                            Toasty.error(getContext(), "Student Doesn't Exists!", Toast.LENGTH_SHORT,
                                    true).show();
                        } else {
                            Toasty.warning(getContext(), "Student Deleted Successfully",
                                    Toast.LENGTH_SHORT, true).show();
                            myDb.DeleteStudent(roll, name, className);
                            Log.d("error", "onClick: ");
                            Roll.setText("");
                            Name.setText("");
                        }
                    }
                });
                builder.show();
            }


        });

        DeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.create();
                builder.setCancelable(true);
                builder.setTitle("Warning!!");
                builder.setMessage("Are you Sure to delete Student Data?");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.success(getContext(), "Cancelled!!",
                                Toast.LENGTH_SHORT, true).show();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toasty.warning(getContext(), "All Students Deleted Successfully",
                                Toast.LENGTH_SHORT, true).show();
                        myDb.Delete();
                    }
                });
                builder.show();

            }
        });
        return view;
    }

}
