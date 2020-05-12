package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.ProductDetail;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorProduct;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;

import java.util.List;

public class ShoppingCartItemAdapter extends RecyclerView.Adapter<ShoppingCartItemAdapter.ViewHolder> {

    private List<VendorProduct> dataList;
    private Context context;
    private deleteClickListener click_listener;

    public ShoppingCartItemAdapter(Context context, List<VendorProduct> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public void SetClickListener(deleteClickListener listener){
        this.click_listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.shopping_cart_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VendorProduct v = dataList.get(position);
        String price = v.getProduct_qty() + "@" + Support.rupiahFormat(v.getProduct_price()) + " koin";

        holder.product_name.setText(v.getProduct_name());
        holder.product_price.setText(price);

        // ke product detail
        holder.shopping_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", v.getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });

        holder.shopping_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", v.getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });

        // hapus item dari cart
        holder.shopping_item_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click_listener.onDeleteClick(v.getShopcart_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView product_name, product_price;
        ImageView shopping_item_remove, shopping_item_image;
        ConstraintLayout shopping_item_container;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            shopping_item_remove = itemView.findViewById(R.id.shopping_item_remove);
            shopping_item_image = itemView.findViewById(R.id.shopping_item_image);
            shopping_item_container = itemView.findViewById(R.id.shopping_item_container);
        }
    }

    public interface deleteClickListener{
        void onDeleteClick (String id);
    }

}
