package com.ihealthy.ihealthy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class RestaurantViewActivity extends AppCompatActivity {

    private Intent intent;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intent = getIntent();

        restaurantName = intent.getExtras().getString("restaurantName");

        setTitle(restaurantName);//Seta o título da activity

        loadMenuList();

    }

    private void loadMenuList(){

        final ArrayList<MenuItem> menuList = getMenuInfo();
        ListView listView = findViewById(R.id.menu_list_view);

        ArrayAdapter listViewAdapter = new ArrayAdapter(
                this,
                R.layout.restaurant_menu_item,
                android.R.id.text1,
                menuList
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.restaurant_menu_item, parent, false);

                TextView text1 = (TextView) view.findViewById(R.id.text1);
                TextView text2 = (TextView) view.findViewById(R.id.text2);
                Button button = (Button) view.findViewById(R.id.button1);

                text1.setText(menuList.get(position).getName());
                text2.setText("Calories: "+menuList.get(position).getCalories());
                button.setText("R$"+menuList.get(position).getPrice());

                return view;
            }
        };

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "FOOOOOOI",Toast.LENGTH_LONG);

            }
        });
    }

    private ArrayList<MenuItem> getMenuInfo(){

        ArrayList<MenuItem> menuItems = new ArrayList<>();

        //Criando objeto de busca
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");
        query.whereEqualTo("name", restaurantName);

        try {

            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            if(objects.size()>0) {
                final ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Food");
                query1.whereEqualTo("fromRestaurant", ParseObject.createWithoutData("Restaurant", objects.get(0).getObjectId()));

                List<ParseObject> objects1 = query1.find();
                for (int i = 0; i < objects1.size(); i++){
                    MenuItem item = new MenuItem();
                    item.setName(objects1.get(i).getString("foodname"));
                    item.setCalories(objects1.get(i).getString("calories"));
                    item.setPrice(objects1.get(i).getInt("price"));
                    item.setIngredients(objects1.get(i).getString("foodname"));
                    menuItems.add(item);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    public class MenuItem {
        private String name;
        private String ingredients;
        private String calories;
        private float price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
}
