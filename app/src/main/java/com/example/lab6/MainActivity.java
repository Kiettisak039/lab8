package com.example.lab6;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    ImageView logo;
    Button browse;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.button2);//event aource
        logo = findViewById(R.id.imageView);
        logo.setImageResource(R.drawable.t1);

        addButton.setOnClickListener(new View.OnClickListener() {//event listener
            @Override
            public void onClick(View view) {//event handler
                System.out.println("Click!!");
                Intent addNoteAct = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(addNoteAct);
            }
        });
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        browse = findViewById(R.id.button);
        //load data from DB
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.show progrogress bar
                progressBar.setVisibility(View.VISIBLE);
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
                        progressBar.setVisibility(View.GONE);
                        //2.4go to BrowesNoteActivity
                        Intent BrowseNoteAct = new Intent(getApplicationContext(),BrowesNoteActivity.class);
                        startActivity(BrowseNoteAct);
                        finish();
                    });

                }).start();

            }
        });

    }

   /* public static void main(String[] args) {
Note noteA = new Note();
noteA.title="OOP class";
//noteA.createdDate=17768;
//noteA.content="Coding OOP class and object";
//noteA.getSummary();

User user1 = new TextUser();
user1.name = "it";
user1.id = 1511 ;
//user1.take_note();
user1.getSummary();

TextNote textNote1 = new TextNote();
textNote1.title = "";
//textNote1.textContent="Coding OOP class and object";
textNote1.setTextContent("Coding OOP class and object");
textNote1.createdDate = "02" ;






    }*/
}