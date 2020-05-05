package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.VendorProductDashboard;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorMainOnline;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainServiceVendorOnlineAdapter extends RecyclerView.Adapter<MainServiceVendorOnlineAdapter.ViewHolder> {

    private List<VendorMainOnline> dataList;
    private Context context;

    public MainServiceVendorOnlineAdapter(Context context, List<VendorMainOnline> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_service_vendor_online_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final VendorMainOnline v = dataList.get(position);
        holder.main_service_vendor_online_item_title.setText(v.getName_user());
        holder.main_service_vendor_online_item_phone_num.setText(v.getPhone_num_user());
        holder.main_service_vendor_online_item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), VendorProductDashboard.class);
                mIntent.putExtra("vendor_id", v.getId_user());
                view.getContext().startActivity(mIntent);
            }
        });

        String imageurl =  v.getPic_user();
        Picasso.with(context).load(imageurl).into(holder.main_service_vendor_online_item_image);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView main_service_vendor_online_item_title, main_service_vendor_online_item_phone_num;
        ImageView main_service_vendor_online_item_image;
        CardView main_service_vendor_online_item_card;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            main_service_vendor_online_item_title = itemView.findViewById(R.id.main_service_vendor_online_item_title);
            main_service_vendor_online_item_phone_num = itemView.findViewById(R.id.main_service_vendor_online_item_phone_num);
            main_service_vendor_online_item_image = itemView.findViewById(R.id.main_service_vendor_online_item_image);
            main_service_vendor_online_item_card = itemView.findViewById(R.id.main_service_vendor_online_item_card);
        }
    }
}