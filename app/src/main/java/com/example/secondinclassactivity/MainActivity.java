package com.example.secondinclassactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText bookTitle;
    private EditText author;
    private EditText pages;
    private Switch available;
    private Button btnAdd;
    private Button btnSave;
    SharedPreferences sp;
    public static final String BOOKS = "BOOKS";
    public static List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookTitle = findViewById(R.id.bookTitle);
        author = findViewById(R.id.author);
        pages = findViewById(R.id.pages);
        available = findViewById(R.id.available);
        btnAdd = findViewById(R.id.btnAdd);
        btnSave = findViewById(R.id.btnSave);
        sp = getSharedPreferences("myShared", Context.MODE_PRIVATE);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = bookTitle.getText().toString();
                String authorText = author.getText().toString();
                String pagesNumber = pages.getText().toString();
               books.add(new Book(title,authorText,pagesNumber));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sp.edit();
                Gson gson = new Gson();
                String json = gson.toJson(books);
                editor.putString("BOOKS", json);
                editor.apply();
                editor.commit();
                Toast.makeText(MainActivity.this, "Added Done" , Toast.LENGTH_LONG).show();

            }
        });
    }

    public static class Book {
        String title;
        String AuthorName;
        String Pages;

        public Book(String title, String authorName, String pages) {
            this.title = title;
            AuthorName = authorName;
            Pages = pages;
        }

        public Book() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthorName() {
            return AuthorName;
        }

        public void setAuthorName(String authorName) {
            AuthorName = authorName;
        }

        public String getPages() {
            return Pages;
        }

        public void setPages(String pages) {
            Pages = pages;
        }
    }
}