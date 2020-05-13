package com.example.wafil.Wafil.basBangunan.ui.Akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.wafil.R;

public class AkunFragment extends Fragment {
    private AkunViewModel akunViewModel;
    TextView txtnama;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        akunViewModel =
                ViewModelProviders.of(this).get(AkunViewModel.class);
        View root = inflater.inflate(R.layout.fragment_akun, container, false);

        txtnama = root.findViewById(R.id.texview_nama);



       final Button btn_edit = root.findViewById(R.id.button_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), EditProfil.class);
                startActivity(inte);
            }
        });


        return root;
    }


}
