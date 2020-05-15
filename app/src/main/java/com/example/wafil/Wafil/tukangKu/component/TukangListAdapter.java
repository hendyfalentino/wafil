package com.example.wafil.Wafil.tukangKu.component;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.Wafil.tukangKu.OrderActivity;
import com.example.wafil.R;

import java.util.ArrayList;
import java.util.List;

public class TukangListAdapter extends RecyclerView.Adapter<TukangItemViewHolder> {
    private List<TukangItem> mData = new ArrayList<>();
    private Context mContext;
    private String mAddress;

    public TukangListAdapter(List<TukangItem>data, String address) {
        mData = data;
        mAddress = address;
    }

    @NonNull
    @Override
    public TukangItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_tukang, parent, false);
        return new TukangItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TukangItemViewHolder holder, int position) {
        final TukangItem datum = mData.get(position);

        holder.address.setText(datum.getAddress());
        holder.name.setText(datum.getName());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("name", datum.getName());
                intent.putExtra("address", datum.getAddress());
                intent.putExtra("id", datum.getId());
                intent.putExtra("worktype", datum.getWorktype());
                intent.putExtra("workaddress", mAddress);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
