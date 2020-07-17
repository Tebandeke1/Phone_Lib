package com.example.phonelibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import static com.example.phonelibrary.RecycleViewAdapter.DELETE_WANT_TO_READ_BOOKS;

public class WantToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecycleViewAdapter adapter = new RecycleViewAdapter(this,DELETE_WANT_TO_READ_BOOKS);

        RecyclerView recyclerView = findViewById(R.id.wantRecBooks);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}
