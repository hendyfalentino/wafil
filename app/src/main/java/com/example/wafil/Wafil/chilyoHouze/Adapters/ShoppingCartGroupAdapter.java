package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.VendorProductDashboard;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCartGroup;

import java.util.List;

public class ShoppingCartGroupAdapter extends RecyclerView.Adapter<ShoppingCartGroupAdapter.ViewHolder> {

    private RecyclerView.LayoutManager layoutManager;
    private ShoppingCartItemAdapter itemAdapter;
    private List<ShoppingCartGroup> dataList;
    private Context context;
    private deleteGroupClickListener clickGroupListener;

    public ShoppingCartGroupAdapter(Context context, List<ShoppingCartGroup> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public void setClickGroupListener(deleteGroupClickListener clickGroupListener) {
        this.clickGroupListener = clickGroupListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shopping_cart_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShoppingCartGroup v = dataList.get(position);
        holder.vendor_name.setText(v.getVendor_name());

        // recycler view
        layoutManager = new LinearLayoutManager(context);
        itemAdapter = new ShoppingCartItemAdapter(context, v.getVendor_products());

        itemAdapter.SetClickListener(new ShoppingCartItemAdapter.deleteClickListener() {
            @Override
            public void onDeleteClick(String id) {
                clickGroupListener.onDeleteClick(id);
            }
        });

        holder.vendor_products.setLayoutManager(layoutManager);
        holder.vendor_products.setAdapter(itemAdapter);

        // pergi ke menu utama halaman vendor
        holder.vendor_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), VendorProductDashboard.class);
                mIntent.putExtra("vendor_id", v.getVendor_id());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView vendor_name;
        RecyclerView vendor_products;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            vendor_name = itemView.findViewById(R.id.vendor_name);
            vendor_products = itemView.findViewById(R.id.rv_shopping_cart_item);
        }
    }

    public interface deleteGroupClickListener{
        void onDeleteClick (String id);
    }

}
