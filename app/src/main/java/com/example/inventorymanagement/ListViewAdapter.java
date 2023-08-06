package com.example.inventorymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> itemList;

    public ListViewAdapter(Context context, List<String> itemList) {
        super(context, R.layout.activity_list_view, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_list_view, parent, false);

        TextView textView = rowView.findViewById(R.id.namelist);
        textView.setText(itemList.get(position));

        return rowView;
    }
}

