package com.example.inventorymanagement;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView=findViewById(R.id.listview);
        itemList=new ArrayList<>();
        itemList.add("History");
        itemList.add("Geography");
        itemList.add("Biology");
        itemList.add("Chemistry");
        itemList.add("English");
        itemList.add("Math");
        itemList.add("Physics");
        itemList.add("Kinyarwanda");

        ListViewAdapter adapter = new ListViewAdapter(this, itemList);
        listView.setAdapter(adapter);
    }


}


