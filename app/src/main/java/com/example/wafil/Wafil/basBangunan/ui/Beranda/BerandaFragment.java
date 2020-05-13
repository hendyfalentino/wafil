package com.example.wafil.Wafil.basBangunan.ui.Beranda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.wafil.R;
import com.example.wafil.Wafil.basBangunan.memesan_Jasa.Pesan_Jasa;

public class BerandaFragment extends Fragment {

    private BerandaViewModel homeViewModel;
    ImageButton btn_pipa_air,btn_atap_rumah, btn_wc,btn_sofa,btn_cat_dinding, btn_ubin,btn_ac,btn_listrik,btn_kulkas,btn_kipas_angin,btn_komputer,btn_mesin_cuci,btn_tv;

    double pipa_air = 50.000;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(BerandaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_beranda, container, false);

        btn_pipa_air = root.findViewById(R.id.img_pipa_air);
        btn_atap_rumah =root.findViewById(R.id.img_atap_rumah);
        btn_wc =root.findViewById(R.id.img_wc);
        btn_sofa=root.findViewById(R.id.img_sofa);
        btn_cat_dinding=root.findViewById(R.id.img_cat_dinding);
        btn_ubin=root.findViewById(R.id.img_ubin);
        btn_ac=root.findViewById(R.id.img_ac);
        btn_listrik=root.findViewById(R.id.img_listrik);
        btn_kulkas=root.findViewById(R.id.img_kulkas);
        btn_kipas_angin=root.findViewById(R.id.img_kipas_angin);
        btn_komputer=root.findViewById(R.id.img_komputer);
        btn_mesin_cuci=root.findViewById(R.id.img_mesin_cuci);
        btn_tv=root.findViewById(R.id.img_tv);


        btn_pipa_air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Pipa Air");
                startActivity(inte);
            }
        });
        btn_atap_rumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Atap Rumah");
                startActivity(inte);
            }
        });
        btn_wc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan WC");
                startActivity(inte);
            }
        });
        btn_sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Sofa");
                startActivity(inte);
            }
        });
        btn_cat_dinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Pengecetan Dinding");
                startActivity(inte);
            }
        });
        btn_ubin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Ubin");
                startActivity(inte);
            }
        });
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Air Conditioner (AC)");
                startActivity(inte);
            }
        });
        btn_listrik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Listrik");
                startActivity(inte);
            }
        });
        btn_kulkas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Kulkas");
                startActivity(inte);
            }
        });
        btn_kipas_angin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Kipas Angin");
                startActivity(inte);
            }
        });
        btn_komputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Komputer");
                startActivity(inte);
            }
        });
        btn_mesin_cuci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Mesin Cuci");
                startActivity(inte);
            }
        });
        btn_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(getActivity(), Pesan_Jasa.class);
                inte.setType("Kerusakan Televisi");
                startActivity(inte);
            }
        });

        return root;
    }

}
