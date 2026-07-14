package com.example.juneinternhip26;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {

    int[] subIdArray = {1,2,3,4,5,6,7,8,9};
    int[] catIdArray = {1,1,1,2,2,2,3,3,3};
    String[] nameArray = {"Mobile", "Headphones", "Earbuds",
            "Horror", "Novel", "Fiction",
            "T-Shirt", "Jeans", "Shirts"};
    int[] imageArray = {R.drawable.mobile, R.drawable.headphone, R.drawable.earbuds,
            R.drawable.horror, R.drawable.novel, R.drawable.fiction,
            R.drawable.thsirt, R.drawable.jeans, R.drawable.shirt};

    RecyclerView subategory_recycler;

    SQLiteDatabase db;

    SharedPreferences sp;

    ArrayList<SubCategoryList> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        db = openOrCreateDatabase(ConstantSp.pref, MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(categoryTable);

        String subCategoryTable = "CREATE TABLE IF NOT EXISTS subcategory(subcategoryid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(subCategoryTable);

        for(int i = 0; i < nameArray.length; i++){
            String checkSubcategory = "SELECT * FROM subcategory WHERE name = '"+nameArray[i]+"' and categoryid = '"+catIdArray[i]+"'";
            Cursor subcategoryCursor = db.rawQuery(checkSubcategory, null);
            if(subcategoryCursor.getCount() == 0){
                String insertSubcategory = "INSERT INTO subcategory VALUES(NULL, '"+catIdArray[i]+"', '"+nameArray[i]+"', '"+imageArray[i]+"')";
                db.execSQL(insertSubcategory);
            }
        }

        String fetchSubcategory = "SELECT * FROM subcategory WHERE categoryid = '"+sp.getString(ConstantSp.categoryid, "")+"'";
        Cursor cursor = db.rawQuery(fetchSubcategory, null);

        arraylist = new ArrayList<>();

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                SubCategoryList list = new SubCategoryList();
                list.setSubId(cursor.getInt(0));
                list.setCatId(cursor.getInt(1));
                list.setSubName(cursor.getString(2));
                list.setSubImage(cursor.getInt(3));
                arraylist.add(list);
            }
        }




        subategory_recycler = findViewById(R.id.subcategory_recycler);

        subategory_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//        SubCategoryAdapter adapter = new SubCategoryAdapter(SubCategoryActivity.this, subIdArray, catIdArray, nameArray, imageArray);
        SubCategoryAdapter adapter = new SubCategoryAdapter(SubCategoryActivity.this, arraylist);
        subategory_recycler.setAdapter(adapter);


    }
}