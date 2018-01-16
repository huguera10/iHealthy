package com.ihealthy.ihealthy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    private List<String> fetched_data = new ArrayList<>();


    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_restaurant, container, false);

        loadRestaurantList(view);

        return view;
    }

    private void loadRestaurantList(View view){

        final String [] restaurantList = getRestaurantInfo();
        ListView listView = (ListView)view.findViewById(R.id.restaurant_list_view);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                restaurantList
        );

        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), RestaurantViewActivity.class);
                intent.putExtra("restaurantName", restaurantList[i]);
                startActivity(intent);
            }
        });
    }

    private String [] getRestaurantInfo(){

        //Criando objeto de busca
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("Restaurant");

        try {

            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            for (int i = 0; i < objects.size(); i++) {
                fetched_data.add(objects.get(i).getString("name"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator<String> i = fetched_data.iterator();

        String[] restaurantList = new String[fetched_data.size()];

        for (int j = 0; j < fetched_data.size(); j++){
            restaurantList[j] = i.next();
        }

        return restaurantList;
    }


}
