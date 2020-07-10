package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button booksAvailable,currentBooks,alreadyRead,wishList,favourite,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeResources();

        booksAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });
    }

    private void intializeResources() {

        booksAvailable = findViewById(R.id.see_all);
        currentBooks = findViewById(R.id.current);
        alreadyRead = findViewById(R.id.readBooks);
        wishList = findViewById(R.id.wishList);
        favourite = findViewById(R.id.favourite);
        about = findViewById(R.id.about);
    }

}
