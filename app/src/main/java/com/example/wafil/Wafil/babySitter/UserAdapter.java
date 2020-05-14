package com.example.wafil.Wafil.babySitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.wafil.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<User> userList;
    private UserAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(UserAdapter.OnItemClickListener listener){
        mListener = listener;
    }

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @Override
    public UserAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_list, null);
        return new UserAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ProductViewHolder holder, int position) {
        User user = userList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(user.getImage())
                .into(holder.imageViewUser);

        holder.textViewNameUser.setText(user.getName());
        holder.textViewEmailUser.setText(user.getEmail());
        holder.textViewPasswordUser.setText(user.getPassword());
        holder.textViewAddressUser.setText(user.getAddress());
        holder.textViewPhoneNumberUser.setText(user.getPhone_number());
        holder.textViewGenderUser.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNameUser, textViewEmailUser,textViewPasswordUser,textViewAddressUser,textViewPhoneNumberUser,textViewGenderUser;
        ImageView imageViewUser;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNameUser = itemView.findViewById(R.id.textViewNameUser);
            textViewEmailUser = itemView.findViewById(R.id.textViewEmailUser);
            imageViewUser = itemView.findViewById(R.id.imageViewUser);
            textViewPasswordUser = itemView.findViewById(R.id.textViewPasswordUser);
            textViewAddressUser = itemView.findViewById(R.id.textViewAddressUser);
            textViewPhoneNumberUser = itemView.findViewById(R.id.textViewPhoneUser);
            textViewGenderUser = itemView.findViewById(R.id.textViewGenderUser);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}


