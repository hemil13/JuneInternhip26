package com.example.juneinternhip26;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {
    Context context;
    int[] idArray;
    String[] nameArray;
    int[] imageArray;

    ArrayList<CategoryList> arrayList;

//    public CategoryAdapter(Context context, int[] idArray, String[] nameArray, int[] imageArray) {
//        this.context = context;
//        this.idArray = idArray;
//        this.nameArray = nameArray;
//        this.imageArray = imageArray;
//    }

    public CategoryAdapter(Context context, ArrayList<CategoryList> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new MyHolder(view);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView category_image;
        TextView category_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_image);
            category_name = itemView.findViewById(R.id.category_name);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyHolder holder, int position) {
//        holder.category_image.setImageResource(imageArray[position]);
//        holder.category_name.setText(nameArray[position]);
        holder.category_image.setImageResource(arrayList.get(position).getImage());
        holder.category_name.setText(arrayList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubCategoryActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
