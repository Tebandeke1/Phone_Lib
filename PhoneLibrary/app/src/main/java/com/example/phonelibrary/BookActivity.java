package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private Button currentlyReading, wantToRead,AlreadyRead,Favouraties;
    private TextView  bookName,author,pages,longDesc;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        String longDec = "A design patterns are well-proved solution for solving the specific problem/task.\n" +
                "\n" +
                "Now, a question will be arising in your mind what kind of specific problem? Let me explain by taking an example.\n" +
                "\n" +
                "Problem Given:\n" +
                "Suppose you want to create a class for which only a single instance (or object) should be created and that single object can be used by all other classes.\n" +
                "\n" +
                "Solution:\n" +
                "Singleton design pattern is the best solution of above specific problem. So, every design pattern has some specification or set of rules for solving the problems. What are those specifications, you will see later in the types of design patterns.\n" +
                "\n" +
                "But remember one-thing, design patterns are programming language independent strategies for solving the common object-oriented design problems. That means, a design pattern represents an idea, not a particular implementation.\n" +
                "\n" +
                "By using the design patterns you can make your code more flexible, reusable and maintainable. It is the most important part because java internally follows design patterns.\n" +
                "\n" +
                "To become a professional software developer, you must know at least some popular solutions (i.e. design patterns) to the coding problems.";

        initViews();

        Books book = new Books(1,"1Q84","Haruki",13093,
                "https://i.pinimg.com/originals/28/c6/d1/28c6d1b0e6163232ed4bd9e488f8d54c.png",
                "Sart from here",longDec);


        setData(book);
    }

    public void setData(Books books){
        bookName.setText(books.getName());
        author.setText(books.getAuthor());
        pages.setText(String.valueOf(books.getPages()));
        longDesc.setText(books.getLongDesc());

        Glide.with(this)
                .asBitmap().load(books.getImageUrl())
                .into(imageView);
    }

    private void initViews() {
        currentlyReading = findViewById(R.id.currentReading);
        wantToRead = findViewById(R.id.wantToRead);
        AlreadyRead = findViewById(R.id.already_read);
        Favouraties = findViewById(R.id.favouritesbtn);
        imageView = findViewById(R.id.bookImage);

        bookName = findViewById(R.id.bookName);
        author = findViewById(R.id.author);
        pages  = findViewById(R.id.pages);
        longDesc = findViewById(R.id.desc);
    }


}
