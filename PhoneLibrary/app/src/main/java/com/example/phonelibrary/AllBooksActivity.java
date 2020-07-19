package com.example.phonelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import static com.example.phonelibrary.RecycleViewAdapter.DELETE_ALL_BOOKS;

public class AllBooksActivity extends AppCompatActivity {


    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        //here we use this incase we want to add animation to only one activity

        //overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new RecycleViewAdapter(this,DELETE_ALL_BOOKS);

        recyclerView = findViewById(R.id.recycleView);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter.setBooks(Utils.getInstance(this).getAllBooks());
    }

    //this  method helps on action bar items or menu  items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //we override this finish method in case we want to add animation on the back button also

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_out,R.anim.slide_in);
//    }
}
