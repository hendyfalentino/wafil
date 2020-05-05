package com.example.wafil.Wafil.wafil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;

import java.util.List;

public class AdapterCategoryProduct extends RecyclerView.Adapter<AdapterCategoryProduct.MyViewHolder> {

    private List<Category_Product> category_product;
    private Context context;

    public AdapterCategoryProduct(List<Category_Product> category_product, Context context) {
        this.category_product = category_product;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCategoryProduct.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.category_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoryProduct.MyViewHolder holder, int position) {
        holder.name_category_product.setText(category_product.get(position).getName_category_product());
    }

    @Override
    public int getItemCount() {
        return category_product.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name_category_product;

        public MyViewHolder(View itemView){
            super(itemView);
            name_category_product = itemView.findViewById(R.id.name_category_product);
        }
    }
}
