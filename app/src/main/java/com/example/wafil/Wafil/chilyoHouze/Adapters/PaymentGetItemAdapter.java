package com.example.wafil.Wafil.chilyoHouze.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.Model.PaymentItem;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;

import java.util.List;

public class PaymentGetItemAdapter extends RecyclerView.Adapter<PaymentGetItemAdapter.ViewHolder> {

    private List<PaymentItem> dataList;
    private Context context;

    public PaymentGetItemAdapter(Context context, List<PaymentItem> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public PaymentGetItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.payment_item, parent, false);
        return new PaymentGetItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentGetItemAdapter.ViewHolder holder, int position) {
        final PaymentItem v = dataList.get(position);
        holder.order_qty.setText(v.getProduct_qty()+" x");
        holder.order_name.setText(v.getProduct_name());
        holder.order_price.setText(Support.rupiahFormat(v.getProduct_price())+" coin");
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
