package com.example.phonelibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private static final String TAG = "RecycleViewAdapter";
    public static final String BOOK_ID_KEY = "bookId";


    ArrayList<Books> books = new ArrayList<>();
    private Context context;

    private String parentActivity;

    public static final String DELETE_ALL_BOOKS = "allbooks";
    public static final String DELETE_FAVOURITE_BOOKS = "favouriteBooks";
    public static final String DELETE_ALREADY_READ_BOOKS = "alreadyRead";
    public static final String DELETE_CURRENT_READING_BOOKS = "currentReading";
    public static final String DELETE_WANT_TO_READ_BOOKS = "wantToRead";

    public RecycleViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.d(TAG, "onBindViewHolder: called");

        holder.textBookName.setText(books.get(position).getName());

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.imageBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,BookActivity.class);
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.shortDesc.setText(books.get(position).getShortDesc());
        holder.authorName.setText(books.get(position).getAuthor());
        holder.authorText.setText(books.get(position).getName());

        if (books.get(position).isCollapse()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.collapseRelative.setVisibility(View.VISIBLE);
            holder.downImage.setVisibility(View.GONE);

            if (parentActivity.equals(DELETE_ALL_BOOKS)){
                holder.deleteBtn.setVisibility(View.GONE);


            }else if (parentActivity.equals(DELETE_ALREADY_READ_BOOKS)){
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //this Alert dialog helps in case a user has pressed on the delete btn mistakenly
                        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete "+books.get(position).getName());
                        builder.setPositiveButton(" Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //check if a book is removed successfully and show a text message
                                if( Utils.getInstance().removeAlreadyReadBook(books.get(position))) {
                                    Toast.makeText(context, "Book removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });


            }else if(parentActivity.equals(DELETE_CURRENT_READING_BOOKS)){
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //this Alert dialog helps in case a user has pressed on the delete btn mistakenly
                        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete "+books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //check if a book is removed successfully and show a text message
                                if( Utils.getInstance().removeCurrentReadingBooks(books.get(position))) {
                                    Toast.makeText(context, "Book removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });


            }else if ((parentActivity.equals(DELETE_FAVOURITE_BOOKS))){
                holder.deleteBtn.setVisibility(View.VISIBLE);
                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //this Alert dialog helps in case a user has pressed on the delete btn mistakenly
                        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete "+books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //check if a book is removed successfully and show a text message
                                if( Utils.getInstance().removeFavouritesBooks(books.get(position))) {
                                    Toast.makeText(context, "Book removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });


            }else if (parentActivity.equals(DELETE_WANT_TO_READ_BOOKS)){
                holder.deleteBtn.setVisibility(View.VISIBLE);

                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //this Alert dialog helps in case a user has pressed on the delete btn mistakenly
                        AlertDialog.Builder  builder = new AlertDialog.Builder(context);
                        builder.setTitle("Want To Read Books");
                        builder.setMessage("Are you sure you want to delete "+books.get(position).getName());
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //check if a book is removed successfully and show a text message
                                if( Utils.getInstance().removeWantToReadBooks(books.get(position))) {
                                    Toast.makeText(context, "Book removed.", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.create().show();
                    }
                });


            }
        }else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.collapseRelative.setVisibility(View.GONE);
            holder.downImage.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Books> books){
        this.books = books;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private ImageView imageBook;
        private TextView textBookName;
        private ImageView upImage,downImage;
        private RelativeLayout collapseRelative;
        private TextView authorName,authorText,shortDesc;
        private TextView deleteBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imageBook = itemView.findViewById(R.id.imageBook);
            textBookName = itemView.findViewById(R.id.textName);
            upImage = itemView.findViewById(R.id.up_collapse);
            downImage = itemView.findViewById(R.id.down_collapse);
            collapseRelative = itemView.findViewById(R.id.collapseRelLayout);
            authorName = itemView.findViewById(R.id.authorText);
            authorText = itemView.findViewById(R.id.textAuthor);
            shortDesc = itemView.findViewById(R.id.shortDesc);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

            upImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Books book = books.get(getAdapterPosition());
                    book.setCollapse(!book.isCollapse());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            downImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Books book = books.get(getAdapterPosition());
                    book.setCollapse(!book.isCollapse());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }

    }
}
