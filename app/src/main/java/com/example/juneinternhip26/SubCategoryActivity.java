package com.example.juneinternhip26;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        subategory_recycler = findViewById(R.id.subcategory_recycler);

        subategory_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        SubCategoryAdapter adapter = new SubCategoryAdapter(SubCategoryActivity.this, subIdArray, catIdArray, nameArray, imageArray);
        subategory_recycler.setAdapter(adapter);


    }
}