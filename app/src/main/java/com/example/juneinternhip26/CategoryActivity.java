package com.example.juneinternhip26;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    int[] idArray = {1, 2, 3, 4, 5};
    String[] nameArray = {"Electronics", "Books", "Clothes", "Shoes", "Bags"};
    int[] imageArray = {R.drawable.electronics, R.drawable.books, R.drawable.clothes, R.drawable.shoes, R.drawable.bags};

    SQLiteDatabase db;

    ArrayList<CategoryList> arraylist;


    RecyclerView category_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        db = openOrCreateDatabase(ConstantSp.pref, MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(categoryTable);

        category_recycler = findViewById(R.id.category_recycler);


        for(int i = 0; i < nameArray.length; i++){
            String checkCategory = "SELECT * FROM category WHERE name = '"+nameArray[i]+"'";
            Cursor cursor = db.rawQuery(checkCategory, null);
            if(cursor.getCount() == 0){
                String insertCategory = "INSERT INTO category VALUES(NULL, '"+nameArray[i]+"', '"+imageArray[i]+"')";
                db.execSQL(insertCategory);
            }

        }


        String fetchCategory = "SELECT * FROM category";
        Cursor categroyCursor = db.rawQuery(fetchCategory, null);

        arraylist = new ArrayList<>();

        if(categroyCursor.getCount()>0){
            while(categroyCursor.moveToNext()){
                CategoryList list = new CategoryList();
                list.setId(categroyCursor.getInt(0));
                list.setName(categroyCursor.getString(1));
                list.setImage(categroyCursor.getInt(2));
                arraylist.add(list);
            }
        }

//        category_recycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        category_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        category_recycler.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

//        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArray, imageArray);
        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, arraylist);
        category_recycler.setAdapter(adapter);




    }
}