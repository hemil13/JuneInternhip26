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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    int[] productid = {1,2,3,4,5,6,7,8,9};
    int[] subcategoryid = {1,1,1,2,2,2,3,3,3};
    String[] productname = {"One Plus", "Redmi", "Sony", "Noise", "Airpods Max", "Sony Headphones", "Noise Aura", "Boat 120", "Airpods Pro 2"};
    int[] productimage = {R.drawable.oneplus, R.drawable.redmi, R.drawable.sony, R.drawable.noise, R.drawable.airpods_max, R.drawable.sony_headphones, R.drawable.noiseaura, R.drawable.boat120, R.drawable.airpodspro2};
    int[] originalPrice = {30000,20000,10000,5000,4000,3000,2000,1000,500};
    int[] discountPrice = {25000,15000,9000,4500,3500,2500,1500,900,450};

    RecyclerView product_recycler;

    ArrayList<ProductList> arrayList;

    SQLiteDatabase db;

    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        db = openOrCreateDatabase(ConstantSp.pref, MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);

        String categoryTable = "CREATE TABLE IF NOT EXISTS category(categoryid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(categoryTable);

        String subCategoryTable = "CREATE TABLE IF NOT EXISTS subcategory(subcategoryid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, name VARCHAR (50), image VARCHAR (100))";
        db.execSQL(subCategoryTable);

        String productTable = "CREATE TABLE IF NOT EXISTS product(productid INTEGER PRIMARY KEY AUTOINCREMENT, subcategoryid INTEGER, name VARCHAR (50), image VARCHAR (100), originalprice INTEGER, discountprice INTEGER)";
        db.execSQL(productTable);

        product_recycler = findViewById(R.id.product_recycler);


        for(int i = 0; i < productid.length; i++){
            String checkSubcategory = "SELECT * FROM product WHERE name = '"+productname[i]+"' and subcategoryid = '"+subcategoryid[i]+"'";
            Cursor productCursor = db.rawQuery(checkSubcategory, null);
            if(productCursor.getCount() == 0){
                String insertProduct = "INSERT INTO product VALUES(NULL, '"+subcategoryid[i]+"', '"+productname[i]+"', '"+productimage[i]+"', '"+originalPrice[i]+"', '"+discountPrice[i]+"')";
                db.execSQL(insertProduct);
            }
        }

        String fetchProduct = "SELECT * FROM product WHERE subcategoryid = '"+sp.getString(ConstantSp.subcategoryid, "")+"'";
        Cursor cursor = db.rawQuery(fetchProduct, null);

        arrayList = new ArrayList<>();

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                ProductList list = new ProductList();
                list.setProductid(cursor.getInt(0));
                list.setSubcategoryid(cursor.getInt(1));
                list.setProductname(cursor.getString(2));
                list.setProductimage(cursor.getInt(3));
                list.setOriginalPrice(cursor.getInt(4));
                list.setDiscountPrice(cursor.getInt(5));
                arrayList.add(list);
            }

        }

        product_recycler.setLayoutManager(new LinearLayoutManager(ProductActivity.this));

        ProductAdapter adapter = new ProductAdapter(ProductActivity.this, arrayList);
        product_recycler.setAdapter(adapter);





    }
}