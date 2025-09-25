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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrowesNoteActivity extends AppCompatActivity {
    TextView showNote,showNoteFromAPI;
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


        showNote = findViewById(R.id.textView8);
        showNoteFromAPI = findViewById(R.id.textView7);
        Executors.newSingleThreadExecutor().execute(() ->{
            List<NoteEntity> entities = AppDatabase.getInstance(getApplicationContext()).noteDao().getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity e :  entities){
                notes.add(NoteMapper.fromEntity(e));

            }
            runOnUiThread(() ->{
                StringBuffer stringBuffer = new StringBuffer();
                for ( Note n : notes){
                    stringBuffer.append(n.getSummary()).append("\n");
                }
                showNote.setText(stringBuffer.toString());
            });

        });

        //load from API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<TextNote>> call = apiService.getTextNotes();

        call.enqueue(new Callback<List<TextNote>>() {
            @Override
            public void onResponse(Call<List<TextNote>> call, Response<List<TextNote>> response) {
                if (!response.isSuccessful()) {
                    showNoteFromAPI.setText("Error Code: " + response.code());
                    return;
                }

                List<TextNote> notes = response.body();
                StringBuilder builder = new StringBuilder();
                for (TextNote n : notes) {
                    builder.append("Title: ").append(n.getTitle()).append("\n")
                            .append("Body: ").append(n.getTextContent()).append("\n\n");
                }
                showNoteFromAPI.setText(builder.toString());
            }

            @Override
            public void onFailure(Call<List<TextNote>> call, Throwable t) {
                showNoteFromAPI.setText("Failed: " + t.getMessage());
            }
        });




        text=findViewById(R.id.editTextText5);
        progressBar2=findViewById(R.id.progressBar);
        progressBar2.setVisibility(View.GONE);
        display2=findViewById(R.id.textView5);
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