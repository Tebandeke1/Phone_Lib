package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.example.phonelibrary.RecycleViewAdapter.DELETE_WANT_TO_READ_BOOKS;

public class WantToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

        RecycleViewAdapter adapter = new RecycleViewAdapter(this,DELETE_WANT_TO_READ_BOOKS);

        RecyclerView recyclerView = findViewById(R.id.wantRecBooks);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
