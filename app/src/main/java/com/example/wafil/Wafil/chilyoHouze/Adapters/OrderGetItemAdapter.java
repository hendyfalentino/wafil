package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.Model.OrderItem;
import com.example.wafil.Wafil.chilyoHouze.Model.PaymentItem;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;

import java.util.List;

public class OrderGetItemAdapter extends RecyclerView.Adapter<OrderGetItemAdapter.ViewHolder> {

    private List<OrderItem> dataList;
    private Context context;

    public OrderGetItemAdapter(Context context, List<OrderItem> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OrderGetItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_item, parent, false);
        return new OrderGetItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderGetItemAdapter.ViewHolder holder, int position) {
        final OrderItem v = dataList.get(position);
        int qty = Integer.parseInt(v.getProduct_qty());
        int price = Integer.parseInt(v.getProduct_price());
        int price_qty = qty * price;
        String price_and_qty = Support.rupiahFormat(String.valueOf(price_qty) + " k");
        holder.order_qty.setText(String.valueOf(qty)+" x");
        holder.order_name.setText(v.getProduct_name());
        holder.order_price.setText(price_and_qty);
    }

    @Override
    public int getItemCount() {return dataList.size();}

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView order_qty, order_name, order_price;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            order_qty = itemView.findViewById(R.id.order_qty);
            order_name = itemView.findViewById(R.id.order_name);
            order_price = itemView.findViewById(R.id.order_price);
        }
    }
}
