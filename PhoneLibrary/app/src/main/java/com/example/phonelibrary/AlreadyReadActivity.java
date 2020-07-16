package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AlreadyReadActivity extends AppCompatActivity {

    private  RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);

        recyclerView = findViewById(R.id.recBooksRead);

        recycleViewAdapter = new RecycleViewAdapter(this);

        recyclerView.setAdapter(recycleViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleViewAdapter.setBooks(Utils.getAlreadyReadyBooks());


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
