package com.example.phonelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import static com.example.phonelibrary.RecycleViewAdapter.DELETE_ALREADY_READ_BOOKS;

public class AlreadyReadActivity extends AppCompatActivity {

    private  RecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recBooksRead);

        recycleViewAdapter = new RecycleViewAdapter(this,DELETE_ALREADY_READ_BOOKS);

        recyclerView.setAdapter(recycleViewAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleViewAdapter.setBooks(Utils.getInstance(this).getAlreadyReadyBooks());


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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
