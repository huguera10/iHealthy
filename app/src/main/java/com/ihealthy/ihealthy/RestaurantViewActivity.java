package com.ihealthy.ihealthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RestaurantViewActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        String restaurantName = intent.getExtras().getString("restaurantName");

        setTitle(restaurantName);//Seta o título da activity

        loadMenuList();

    }

    private void loadMenuList(){

        final String [] menuList = getMenuInfo();
        ListView listView = findViewById(R.id.menu_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                menuList
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: CONFIRM ORDER ACTIVITY

            }
        });
    }

    private String [] getMenuInfo(){
        String[] menuList = {
                "Fish",
                "Meat",
                "Rice"
        };

        return menuList;
    }
}
