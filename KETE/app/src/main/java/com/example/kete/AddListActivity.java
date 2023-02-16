package com.example.kete;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class AddListActivity extends AppCompatActivity {

    String item;
    String  price;
    private ListView listView;
    private FloatingActionButton fab;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(AddListActivity.this);
                dialog.setContentView(R.layout.fabitem);
                EditText item_name = dialog.findViewById(R.id.item_name);
                EditText item_price = dialog.findViewById(R.id.item_price);
                Button btnsave = dialog.findViewById(R.id.btnsave);



                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item = item_name.getText().toString();
                        price = item_price.getText().toString();

                        LvItem lvItem = new LvItem();
                        lvItem.setItem_name(item);
                        lvItem.setPrice(price);
                        arrayList.add(lvItem);
                        dialog.dismiss();

                        ContectAdapter contectAdapter = new ContectAdapter(arrayList, AddListActivity.this);
                        listView.setAdapter(contectAdapter);
                    }
                });
                dialog.show();
            }
        });
    }
}
