package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.ProductDetail;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorProduct;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;

import java.util.List;

public class MainServiceVendorProductsAdapter extends RecyclerView.Adapter<MainServiceVendorProductsAdapter.ViewHolder> {

    private List<VendorProduct> dataList;
    private Context context;

    public MainServiceVendorProductsAdapter(Context context, List<VendorProduct> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_service_vendor_dashboard_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VendorProduct v = dataList.get(position);
        holder.main_service_group_name.setText(v.getProduct_name());
        String userBalance = Support.rupiahFormat(v.getProduct_price()) + " koin";
        holder.main_service_group_price.setText(userBalance);

        holder.main_service_group_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", v.getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });

        //String imageurl =  v.getPic_user();
        //Picasso.with(context).load(imageurl).into(holder.main_service_vendor_online_item_image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView main_service_group_name, main_service_group_price;
        ConstraintLayout main_service_dashboard_item;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            main_service_group_name = itemView.findViewById(R.id.main_service_group_name);
            main_service_group_price = itemView.findViewById(R.id.main_service_group_price);
            main_service_dashboard_item = itemView.findViewById(R.id.main_service_dashboard_item);
        }
    }
}
