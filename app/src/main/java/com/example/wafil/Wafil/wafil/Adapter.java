package com.example.wafil.Wafil.wafil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.ProductDetail;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name_product.setText(product.get(position).getName_product());
        holder.price.setText(product.get(position).getPrice());

        String test = product.get(position).getPic_user();
        String img = "http://carexports.uk/Admin/assets/img/" + test + ".png";
        Picasso.with(context).load(img).into(holder.imageView);

        // ke product detail
        holder.shopping_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", product.get(position).getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name_product, price;
        ImageView imageView;
        ConstraintLayout shopping_item_container;
        public MyViewHolder(View itemView){
            super(itemView);
            name_product = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.shopping_item_image);
            shopping_item_container = itemView.findViewById(R.id.shopping_item_container);
        }
    }
}
