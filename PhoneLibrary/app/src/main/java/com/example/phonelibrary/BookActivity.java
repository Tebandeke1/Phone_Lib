package com.example.phonelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.phonelibrary.RecycleViewAdapter.BOOK_ID_KEY;

public class BookActivity extends AppCompatActivity {

    private Button wantToread, wantToRead,AlreadyRead,Favouraties;
    private TextView  bookName,author,pages,longDesc;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //initializing the views
        initViews();

//        String longDec = "A design patterns are well-proved solution for solving the specific problem/task.\n" +
//                "\n" +
//                "Now, a question will be arising in your mind what kind of specific problem? Let me explain by taking an example.\n" +
//                "\n" +
//                "Problem Given:\n" +
//                "Suppose you want to create a class for which only a single instance (or object) should be created and that single object can be used by all other classes.\n" +
//                "\n" +
//                "Solution:\n" +
//                "Singleton design pattern is the best solution of above specific problem. So, every design pattern has some specification or set of rules for solving the problems. What are those specifications, you will see later in the types of design patterns.\n" +
//                "\n" +
//                "But remember one-thing, design patterns are programming language independent strategies for solving the common object-oriented design problems. That means, a design pattern represents an idea, not a particular implementation.\n" +
//                "\n" +
//                "By using the design patterns you can make your code more flexible, reusable and maintainable. It is the most important part because java internally follows design patterns.\n" +
//                "\n" +
//                "To become a professional software developer, you must know at least some popular solutions (i.e. design patterns) to the coding problems.";
//
//        initViews();
//
//        Books book = new Books(1,"1Q84","Haruki",13093,
//                "https://i.pinimg.com/originals/28/c6/d1/28c6d1b0e6163232ed4bd9e488f8d54c.png",
//                "Sart from here",longDec);


        //here were getting the books object using the books id from the Utils class
        Intent intent = getIntent();

        //first make sure the intent is not null  because it may cause some UI problems
        if (intent != null){
            int id = intent.getIntExtra(BOOK_ID_KEY,-1);

            //make sure that also the id is not negative -1
            if (id != -1){
                Books IncomingBook= Utils.getInstance(this).getBookId(id);

                if (null != IncomingBook){

                    setData(IncomingBook);
                    Toast.makeText(this, IncomingBook.getName(), Toast.LENGTH_SHORT).show();
                    handleAlreadyReadBooks(IncomingBook);
                    handleCurrentReadingBooks(IncomingBook);
                    handleAddedToFavouritesBooks(IncomingBook);
                    handleWantToReadBooks(IncomingBook);
                }
            }
        }

    }

    //this method works on handling Current reading books
    // this helps to manage current reading books activity
    private void handleCurrentReadingBooks(final Books incomingBook) {

        ArrayList<Books> current = Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean want  = false;

        for (Books b : current){
            if (b.getId() == incomingBook.getId()){
                want = true;
            }
        }

        if (want){
            wantToread.setEnabled(false);
        }else {
            wantToread.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addCurrentReading(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added to Current Reading books.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,CurrentReadingActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something went wrong try again..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    //this method works on handling favourites books to be read
    //this helps in managing favourites activities
    private void handleAddedToFavouritesBooks(final Books incomingBook) {

        ArrayList<Books> favbooks = Utils.getInstance(this).getFavouriteBooks();

        boolean fav  = false;

        for (Books b : favbooks){
            if (b.getId() == incomingBook.getId()){
                fav = true;
            }
        }

        if (fav){
            Favouraties.setEnabled(false);
        }else {
            Favouraties.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addFavouriteBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added to favourites  books.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,AddToFavouritesActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something went wrong try again..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    //this method is for working on handling want to read books
    // this helps in managing want to read activities
    private void handleWantToReadBooks(final Books incomingBook) {

        ArrayList<Books> current = Utils.getInstance(this).getWantToReadBooks();

        boolean wantoread  = false;

        for (Books b : current){
            if (b.getId() == incomingBook.getId()){
                wantoread = true;
            }
        }

        if (wantoread){
            wantToRead.setEnabled(false);
        }else {
            wantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addWantToReadBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added to Want to read  books.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something went wrong try again..", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    //this method works on Already read btn
    //this helps to manage already read books in the Books activity
    private void handleAlreadyReadBooks(final Books incomingBook) {

        final ArrayList<Books> alreadyReady = Utils.getInstance(this).getAlreadyReadyBooks();
        System.out.println(alreadyReady);


        boolean read = false;
        for (Books b : alreadyReady){
            if (b.getId() == incomingBook.getId()){
                read = true;
            }
        }

        if (read){
            AlreadyRead.setEnabled(false);
        }else {
            AlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.getInstance(BookActivity.this).addAlreadyReadBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added successfully.", Toast.LENGTH_SHORT).show();
                        //TODO open another activity here

                        Intent intent = new Intent(BookActivity.this,AlreadyReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something happened try again.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void setData(Books books){
        bookName.setText(books.getName());
        author.setText(books.getAuthor());
        pages.setText(String.valueOf(books.getPages()));
        longDesc.setText(books.getLongDesc());

        Glide.with(this)
                .asBitmap().load(books.getImageUrl())
                .into(imageView);
    }

    private void initViews() {
        wantToread = findViewById(R.id.currentReading);
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
