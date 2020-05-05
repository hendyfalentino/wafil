package com.example.wafil.Wafil.wafil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<Product> product;
    private Context context;

    public Adapter(List<Product> product, Context context) {
        this.product = product;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name_product.setText(product.get(position).getName_product());
        holder.price.setText(product.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name_product, price;

        public MyViewHolder(View itemView){
            super(itemView);
            name_product = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price);
        }
    }
}
