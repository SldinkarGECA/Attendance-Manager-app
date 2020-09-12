package com.sldinkar.attendance;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity {


    int SPLASH_TIME = 4000;
    static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toasty.info(this, "Designed and Developed by SD",
                Toast.LENGTH_LONG, true).show();
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                fragmentManager = getSupportFragmentManager();
                if (findViewById(R.id.Container) != null) {
                    fragmentManager.beginTransaction().add(R.id.Container, new Home(), null).commit();

                }

            }
        }, SPLASH_TIME);


    }
}
