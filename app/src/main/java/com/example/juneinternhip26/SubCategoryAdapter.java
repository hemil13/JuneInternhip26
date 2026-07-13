package com.example.juneinternhip26;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyHolder> {

    Context context;
    int[] subIdArray;
    int[] catIdArray;
    String[] nameArray;
    int[] imageArray;

    public SubCategoryAdapter(Context context, int[] subIdArray, int[] catIdArray, String[] nameArray, int[] imageArray) {
        this.context = context;
        this.subIdArray = subIdArray;
        this.catIdArray = catIdArray;
        this.nameArray = nameArray;
        this.imageArray = imageArray;
    }

    @NonNull
    @Override
    public SubCategoryAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new SubCategoryAdapter.MyHolder(view);
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
    public void onBindViewHolder(@NonNull SubCategoryAdapter.MyHolder holder, int position) {
        holder.category_image.setImageResource(imageArray[position]);
        holder.category_name.setText(nameArray[position]);
    }

    @Override
    public int getItemCount() {
        return subIdArray.length;
    }
}
