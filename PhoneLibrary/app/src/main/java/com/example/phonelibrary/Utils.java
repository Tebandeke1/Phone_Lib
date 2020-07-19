package com.example.phonelibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

//in a real world application this is a database class
public class Utils {

    private SharedPreferences sharedPreferences;
//    private static ArrayList<Books> allBooks;
//    private static ArrayList<Books> alreadyReadyBooks;
//    private static ArrayList<Books> wantToReadBooks;
//    private static ArrayList<Books> currentlyReadingBooks;
//    private static ArrayList<Books> favouriteBooks;

    private static final String ALL_BOOKS = "allBooks";
    private static final String ALL_READY_READ_BOOKS = "all_ready_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENT_READING_BOOKS = "current_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";

    private static Utils instance;

    private Utils(Context context){

        //we have used shared preferences here so as to initialize it
        sharedPreferences = context.getSharedPreferences("alternate_db",MODE_PRIVATE);
        if (null == getAllBooks()){
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();


        if (null == getAlreadyReadyBooks()){
            editor.putString(ALL_READY_READ_BOOKS,gson.toJson(new ArrayList<Books>()));
            //after we have to commit the changes or apply
            editor.apply();
        }

        if (null == getWantToReadBooks()){
           editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Books>()));
           editor.apply();
        }

        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENT_READING_BOOKS,gson.toJson(new ArrayList<Books>()));
            editor.apply();
        }

        if (null == getFavouriteBooks()){
            editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Books>()));
            editor.apply();
        }
    }

    private void initData() {
        //TODO: initialize data here

        //Here am to use Shared Preferences to store data to the phone storage
        /**
         * This will help in a way that it helps to store the saved data so that it does not get lost on phone restart
         * its a very good practice to do
         */

        ArrayList<Books> books = new ArrayList<>();

        books.add(new Books(1,"1Q84","Haruki",13093,
                "https://i.pinimg.com/originals/28/c6/d1/28c6d1b0e6163232ed4bd9e488f8d54c.png",
                "Sart from here","End here."));

        books.add(new Books(2,"Color Me Yellow","Tabu Taia",8768,
                "https://i.imgur.com/gUNI6Rq.jpg",
                "This is a test book also","This book is based on a long story of a boy who left his parents to go " +
                "and work for his life to be better, this helped him in all his touches hence developing the idea of man hood where he " +
                "got the idea of withing this book."));

        books.add(new Books(3,"Long Live","Fay Wolden",1837,
                "https://m.media-amazon.com/images/I/41sg9IMaNIL.SX316.SY316.jpg",
                "Its a magical book about the best king","This book was written by Fay Wolden in the year 2015" +
                ". This was inspired to him by the king of Buganda who wsa one of the best kings in Uganda for the Buganda Kingdom" +
                ".This Kingdom was located in the center of Uganda and was one of the richest kingdoms on earth.."));

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
         editor.putString(ALL_BOOKS,gson.toJson(books));
         //apply() is better because it does not block user Interface
         editor.apply();


    }

    public static Utils getInstance(Context context){
        if (null != instance){
            return instance;
        }else {
            instance = new Utils(context);
            return instance;
        }
    }

    public  ArrayList<Books> getAllBooks() {
        //we have used this GSON object to get data from the shared preferences to our list
        Gson gson = new Gson();

        //this helps to tell the shared preferences that our data we would want it in form of an ArrayList
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        //here were getting the data and storing it in the array List
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS,null),type);

        return books;
    }

    public  ArrayList<Books> getAlreadyReadyBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(ALL_READY_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Books> getWantToReadBooks() {

        //just the way we have done in the get all books method
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Books> getCurrentlyReadingBooks() {
        //just the way we have done in the get all books method
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(CURRENT_READING_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Books> getFavouriteBooks() {
        //just the way we have done in the get all books method
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null),type);
        return books;
    }

    public Books getBookId(int id){
        ArrayList<Books> books = getAllBooks();

        if (books != null){
            for (Books b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }

        return null;
    }

    //this method helps to add books that are already read to the already read arrayList
    public boolean addAlreadyReadBooks(Books books){

        //adding new book to the  books shared preference
        ArrayList<Books> books1 = getAlreadyReadyBooks();
        if (null != books1){
            if (books1.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_READY_READ_BOOKS);
                editor.putString(ALL_READY_READ_BOOKS,gson.toJson(books1));
                editor.apply();
                // if every thing works then make sure you return true;
                return true;
            }
        }

        //if adding a book fails , return false
        return false;
    }

    public boolean addFavouriteBooks(Books books){
        //adding new book to the  books shared preference
        ArrayList<Books> books1 = getAlreadyReadyBooks();
        if (books1 != null){
            if (books1.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS,gson.toJson(books1));
                editor.apply();
                // if every thing works then make sure you return true;
                return true;
            }
        }

        //if adding a book fails , return false
        return false;
    }

    public boolean addCurrentReading(Books books){
        //adding new book to the  books shared preference
        ArrayList<Books> books1 = getAlreadyReadyBooks();
        if (books1 != null){
            if (books1.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENT_READING_BOOKS);
                editor.putString(CURRENT_READING_BOOKS,gson.toJson(books1));
                editor.apply();
                // if every thing works then make sure you return true;
                return true;
            }
        }

        //if adding a book fails , return false
        return false;
    }

    public boolean addWantToReadBooks(Books books ){
        //adding new book to the  books shared preference
        ArrayList<Books> books1 = getAlreadyReadyBooks();
        if (books1 != null){
            if (books1.add(books)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books1));
                editor.apply();
                // if every thing works then make sure you return true;
                return true;
            }
        }

        //if adding a book fails , return false
        return false;
    }

    public boolean removeAlreadyReadBook(Books books){
        //removing new book to the  books shared preference

        ArrayList<Books> books1 = getAlreadyReadyBooks();

        if (books1 != null){
            //creating a loop to loop through all the books
            for (Books b: books1) {
                //we use getId method to get the id of the book we would love to delete
                //if we just delete book from the array list ,we may delete a book we wouldn't want to delete
                if (b.getId() == books.getId()){

                    if (books1.remove(books)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALL_READY_READ_BOOKS);
                        editor.putString(ALL_READY_READ_BOOKS,gson.toJson(books1));
                        editor.apply();
                        //after return true
                        return true;
                    }

                }
            }

        }
        //if it fails the return false
        return false;

    }

    public boolean removeFavouritesBooks(Books books){
        //removing new book to the  books shared preference

        ArrayList<Books> books1 = getAlreadyReadyBooks();

        if (books1 != null){
            //creating a loop to loop through all the books
            for (Books b: books1) {
                //we use getId method to get the id of the book we would love to delete
                //if we just delete book from the array list ,we may delete a book we wouldn't want to delete
                if (b.getId() == books.getId()){

                    if (books1.remove(books)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS,gson.toJson(books1));
                        editor.apply();
                        //after return true
                        return true;
                    }

                }
            }

        }
        //if it fails the return false
        return false;
    }

    public boolean removeCurrentReadingBooks(Books books){
        //removing new book to the  books shared preference

        ArrayList<Books> books1 = getAlreadyReadyBooks();

        if (books1 != null){
            //creating a loop to loop through all the books
            for (Books b: books1) {
                //we use getId method to get the id of the book we would love to delete
                //if we just delete book from the array list ,we may delete a book we wouldn't want to delete
                if (b.getId() == books.getId()){

                    if (books1.remove(books)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENT_READING_BOOKS);
                        editor.putString(CURRENT_READING_BOOKS,gson.toJson(books1));
                        editor.apply();
                        //after return true
                        return true;
                    }

                }
            }

        }
        //if it fails the return false
        return false;
    }

    public boolean removeWantToReadBooks(Books books){
        //removing new book to the  books shared preference

        ArrayList<Books> books1 = getAlreadyReadyBooks();

        if (books1 != null){
            //creating a loop to loop through all the books
            for (Books b: books1) {
                //we use getId method to get the id of the book we would love to delete
                //if we just delete book from the array list ,we may delete a book we wouldn't want to delete
                if (b.getId() == books.getId()){

                    if (books1.remove(books)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books1));
                        editor.apply();
                        //after return true
                        return true;
                    }

                }
            }

        }
        //if it fails the return false
        return false;
    }
}
