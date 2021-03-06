package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import static com.example.phonelibrary.RecycleViewAdapter.DELETE_ALL_BOOKS;

public class AllBooksActivity extends AppCompatActivity {


    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new RecycleViewAdapter(this,DELETE_ALL_BOOKS);

        recyclerView = findViewById(R.id.recycleView);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter.setBooks(Utils.getInstance().getAllBooks());
    }
}
