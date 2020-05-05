package com.example.wafil.Wafil.wafil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wafil.R;
import com.example.wafil.Wafil.LogoutActivity;


public class SettingFragment extends Fragment {

    private TextView user_name, user_first_name;
    private Button btn_logout, btn_edit_profile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        user_name = v.findViewById(R.id.show_user_name);
        user_first_name = v.findViewById(R.id.show_first_name);
        btn_logout = v.findViewById(R.id.btn_logout);
        btn_edit_profile = v.findViewById(R.id.btn_edit_profile);

        String mName = this.getArguments().getString("user_name");
        String mFName = this.getArguments().getString("user_first_name");

        user_name.setText(mName);
        user_first_name.setText(mFName);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LogoutActivity.class));
            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
