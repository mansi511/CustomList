package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustemAdapter extends BaseAdapter {

    Context c;
    ArrayList<List_Data> listdata;
    LayoutInflater inflater;

    public CustemAdapter(Context c, ArrayList<List_Data> listdata) {
        this.c = c;
        this.listdata = listdata;
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = inflater.inflate(R.layout.listdata, null, true);
        TextView txtRepoName = (TextView) view1.findViewById(R.id.repoName);
        TextView txtOwnerName = (TextView) view1.findViewById(R.id.ownerName);

        final String name_text = listdata.get(i).getNameString();
        txtRepoName.setText(name_text);
        txtOwnerName.setText(listdata.get(i).getOwnerName());
        return view1;
    }
}


