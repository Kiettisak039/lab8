package com.example.lab6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.concurrent.Executors;

public class AddNoteActivity extends AppCompatActivity {
    Button addBack;

    Button addButton;

    EditText title,textContent,name,id;

    TextView display;

    TextView display1;

    TextView display2;

    Button addcheck;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addBack = findViewById(R.id.button6);//event aource
        addBack.setOnClickListener(new View.OnClickListener() {//event listener
            @Override
            public void onClick(View view) {//event handler
                System.out.println("Click!!");
                Intent addBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(addBack);
            }

        });
        addButton = findViewById(R.id.button4);
        title = findViewById(R.id.editTextText5);
        textContent = findViewById(R.id.editTextText6);
        display = findViewById(R.id.textView3);
        display1 = findViewById(R.id.textView);
        display2 = findViewById(R.id.textView2);
        addcheck = findViewById(R.id.button5);
        name = findViewById(R.id.editTextText2);
        id = findViewById(R.id.editTextText4);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from user (EditText)
                String strOfTitle = title.getText().toString();
                String strOfContent = textContent.getText().toString();

                Date strOfDate = new Date();
                String strOfName = name.getText().toString();
                String strOfId = id.getText().toString();

                //set data to TextNote class
                TextNote note1 = new TextNote();
                note1.setTitle(strOfTitle);

                note1.setTextContent(strOfContent);

                note1.createdDate = strOfDate;
                TextUser user1 = new TextUser();
                user1.setName(strOfName);
                user1.setId(strOfId);
                //show note on TextView
                display.setText(note1.getSummary());
                display2.setText(user1.getSummary());
            }
        });
        addcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOfTitle = title.getText().toString();
                Date strOfDate = new Date();

                ChecklistNote note1 = new ChecklistNote();
                note1.setTitle(strOfTitle);

                note1.createdDate = strOfDate;

                display1.setText(note1.getSummary());
                NoteEntity enity = NoteMapper.toEntity(note1);

                Context context = view.getContext();
                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase.getInstance(context).noteDao().insert(enity);
                });
            }
        });
    }
}
