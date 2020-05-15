package com.example.wafil.Wafil.tukangKu.component;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;


public class TukangItemViewHolder extends RecyclerView.ViewHolder {
    public TextView name, address;
    public LinearLayout container;
    public TukangItemViewHolder(@NonNull View v) {
        super(v);

        name = v.findViewById(R.id.tv_item_tukang_name);
        address = v.findViewById(R.id.tv_item_tukang_address);

        container = v.findViewById(R.id.ll_item_tukang_container);
    }
}
