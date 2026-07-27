package com.example.juneinternhip26;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImage, wishlist;
    TextView productName, productOriginalPrice, productDiscountedPrice, productDescription;
    Button btnAddToCart, btnBuyNow;

    SharedPreferences sp;

    Boolean isWishlist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productOriginalPrice = findViewById(R.id.product_original_price);
        productDiscountedPrice = findViewById(R.id.product_discount_price);
        productDescription = findViewById(R.id.product_description);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnBuyNow = findViewById(R.id.btn_buy_now);
        wishlist = findViewById(R.id.product_detail_wishlist);

        productImage.setImageResource(Integer.parseInt(sp.getString(ConstantSp.productimage, "")));
        productName.setText(sp.getString(ConstantSp.productname, ""));
        productOriginalPrice.setText(ConstantSp.rupees_symbol+sp.getString(ConstantSp.originalprice, ""));
        productDiscountedPrice.setText(ConstantSp.rupees_symbol+sp.getString(ConstantSp.discountprice, ""));
        productDescription.setText(sp.getString(ConstantSp.productdescription, ""));





        wishlist.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {

            if(isWishlist){
                isWishlist = false;
                wishlist.setImageResource(R.drawable.wishlist_empty);
                Toast.makeText(ProductDetailActivity.this, "Removed from Wishlist", Toast.LENGTH_SHORT).show();
            }else{
                isWishlist = true;
                wishlist.setImageResource(R.drawable.wishlist_fill);
                Toast.makeText(ProductDetailActivity.this, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            }



            }});






    }
}