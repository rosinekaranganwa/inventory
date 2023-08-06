package com.example.inventorymanagement;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        List<Item> items= new ArrayList<Item>();
        items.add(new Item("Rosine Karanganwa","karanganwatuyisenge@gmail.com",R.drawable.book1));
        items.add(new Item("Parfaite Irimaso","parfaiteirimaso@gmail.com",R.drawable.book1));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));

    }
}
