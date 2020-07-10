package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {


    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new RecycleViewAdapter(this);

        recyclerView = findViewById(R.id.recycleView);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<Books> books = new ArrayList<>();

        books.add(new Books(1,"1Q84","Haruki",13093,"https://i.pinimg.com/originals/28/c6/d1/28c6d1b0e6163232ed4bd9e488f8d54c.png","Sart from here","End here."));


        adapter.setBooks(books);
    }
}
