package com.example.juneinternhip26;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class CategoryActivity extends AppCompatActivity {

    int[] idArray = {1,2,3,4,5};
    String[] nameArray = {"Electronics", "Books", "Clothes", "Shoes", "Bags"};
    int[] imageArray = {R.drawable.electronics, R.drawable.books, R.drawable.clothes, R.drawable.shoes, R.drawable.bags};


    RecyclerView category_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        category_recycler = findViewById(R.id.category_recycler);

        category_recycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));


        CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, idArray, nameArray, imageArray);
        category_recycler.setAdapter(adapter);




    }
}