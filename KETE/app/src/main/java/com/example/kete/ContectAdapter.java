package com.example.kete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContectAdapter extends BaseAdapter {
    ArrayList<LvItem>arrayList;
    Context context;
    LayoutInflater layoutInflater;

    public ContectAdapter(ArrayList<LvItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view1 = layoutInflater.inflate(R.layout.lvitem,viewGroup, false);
        TextView itemname = view1.findViewById(R.id.itemname);
        TextView itemprice = view1.findViewById(R.id.itemprice);

        itemname.setText(arrayList.get(i).getItem_name());
        itemprice.setText(arrayList.get(i).getPrice());
        return null;
    }
}

