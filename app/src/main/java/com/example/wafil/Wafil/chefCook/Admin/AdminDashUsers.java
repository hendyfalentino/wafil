package com.example.wafil.Wafil.chefCook.Admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chefCook.ListAdapter;
import com.example.wafil.Wafil.chefCook.ui.ListItem;

import java.util.ArrayList;
import java.util.List;

public class AdminDashUsers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_users);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0 ; i<=10; i++) {
            ListItem listItem = new ListItem("head" + i, "Lorem ipsum");
            listItems.add(listItem);
        }
//        listItems.add((ListItem) listItems);

        adapter= new ListAdapter(listItems , this);

        recyclerView.setAdapter(adapter);
    }
}
