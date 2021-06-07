package com.example.phonelibrary;

import java.util.ArrayList;

//in a real world application this is a database class
public class Utils {

    private static ArrayList<Books> allBooks;
    private static ArrayList<Books> alreadyReadyBooks;
    private static ArrayList<Books> wantToReadBooks;
    private static ArrayList<Books> currentlyReadingBooks;
    private static ArrayList<Books> favouriteBooks;

    private static Utils instance;

    private Utils(){
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }

        if (null == alreadyReadyBooks){
            alreadyReadyBooks = new ArrayList<>();
        }

        if (null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }

        if (null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }

        if (null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        //TODO: initialize data here

        allBooks.add(new Books(1,"1Q84","Haruki",13093,
                "https://i.pinimg.com/originals/28/c6/d1/28c6d1b0e6163232ed4bd9e488f8d54c.png",
                "Sart from here","End here."));

        allBooks.add(new Books(2,"Color Me Yellow","Tabu Taia",8768,
                "https://i.imgur.com/gUNI6Rq.jpg",
                "This is a test book also","This book is based on a long story of a boy who left his parents to go " +
                "and work for his life to be better, this helped him in all his touches hence developing the idea of man hood where he " +
                "got the idea of withing this book."));

        allBooks.add(new Books(3,"Long Live","Fay Wolden",1837,
                "https://m.media-amazon.com/images/I/41sg9IMaNIL.SX316.SY316.jpg",
                "Its a magical book about the best king","This book was written by Fay Wolden in the year 2015" +
                ". This was inspired to him by the king of Buganda who wsa one of the best kings in Uganda for the Buganda Kingdom" +
                ".This Kingdom was located in the center of Uganda and was one of the richest kingdoms on earth.."));

    }

    public static Utils getInstance(){
        if(null == instance){
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Books> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Books> getAlreadyReadyBooks() {
        return alreadyReadyBooks;
    }

    public static ArrayList<Books> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Books> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Books> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Books getBookId(int id){
        for (Books b : allBooks) {
            if (b.getId() == id) {
                return b;
            }
        }

        return null;
    }

    //this method helps to add books that are already read to the already read arrayList
    public boolean addAlreadyReadBooks(Books books){
        return alreadyReadyBooks.add(books);
    }

    public boolean addFavouriteBooks(Books books){
        return favouriteBooks.add(books);
    }

    public boolean addCurrentReading(Books books){
        return currentlyReadingBooks.add(books);
    }

    public boolean addWantToReadBooks(Books books ){
        return wantToReadBooks.add(books);
    }

    public boolean removeAlreadyReadBook(Books books){
        return alreadyReadyBooks.remove(books);
    }

    public boolean removeFavouritesBooks(Books books){
        return favouriteBooks.remove(books);
    }

    public boolean removeCurrentReadingBooks(Books books){
        return currentlyReadingBooks.remove(books);
    }

    public boolean removeWantToReadBooks(Books books){
        return wantToReadBooks.remove(books);
    }
}
