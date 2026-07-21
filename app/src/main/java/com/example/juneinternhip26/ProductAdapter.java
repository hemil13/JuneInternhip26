package com.example.juneinternhip26;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyHolder> {

    Context context;
    ArrayList<ProductList> arrayList;
    public ProductAdapter(Context context, ArrayList<ProductList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        SharedPreferences sp;
    }

    @NonNull
    @Override
    public ProductAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductAdapter.MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name, originalPrice, discountedPrice;
        ImageView productImage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            originalPrice = itemView.findViewById(R.id.product_original_price);
            discountedPrice = itemView.findViewById(R.id.product_discounted_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getProductname());
        holder.originalPrice.setText(ConstantSp.rupees_symbol+arrayList.get(position).getOriginalPrice());
        holder.discountedPrice.setText(ConstantSp.rupees_symbol+arrayList.get(position).getDiscountPrice());
        holder.productImage.setImageResource(arrayList.get(position).getProductimage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
