package com.example.lab6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BrowesNoteActivity extends AppCompatActivity {

    Button addSearch ;
    ProgressBar progressBar2;
    EditText text;
    TextView display2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_browes_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        text=findViewById(R.id.editTextText5);
        progressBar2=findViewById(R.id.progressBar);
        progressBar2.setVisibility(View.GONE);
        display2=findViewById(R.id.textView4);
        addSearch = findViewById(R.id.button3);
        addSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.show progrogress bar
                progressBar2.setVisibility(View.VISIBLE);
                //2.create htread
                new Thread(() ->{
                    //2.1delay x seconds
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //2.2load data form DB

                    //2.3back to main thread
                    runOnUiThread(() ->{
                        progressBar2.setVisibility(View.GONE);
                        display2.setText("ไม่พบข้อมูล");
                        //  finish();
                    });

                }).start();
            }
        });
    }
}