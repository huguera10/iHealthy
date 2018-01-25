package com.ihealthy.ihealthy;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {

    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_restaurant, container, false);

        getActivity().setTitle("Restaurants");

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterNewCaloriesActivity.class);
                startActivity(intent);
            }
        });

        loadRestaurantList(view);

        return view;
    }

    private void loadRestaurantList(View view){

        final ArrayList<Restaurant> restaurantList = getRestaurantInfo();
        ListView listView = (ListView)view.findViewById(R.id.restaurant_list_view);

        ArrayAdapter listViewAdapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                restaurantList
        ){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(restaurantList.get(position).getName());
                text2.setText(restaurantList.get(position).getInfo());
                return view;
            }
        };

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), RestaurantViewActivity.class);
                intent.putExtra("restaurantName", restaurantList.get(i).getName());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Restaurant> getRestaurantInfo(){

        ArrayList<Restaurant> restaurants = new ArrayList<>();

        //Criando objeto de busca
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");

        try {

            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            for (int i = 0; i < objects.size(); i++) {
                Restaurant restaurant = new Restaurant();
                restaurant.setName(objects.get(i).getString("name"));
                restaurant.setInfo(objects.get(i).getString("info"));
                restaurants.add(restaurant);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public class Restaurant{
        private String name;
        private String info;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

}
