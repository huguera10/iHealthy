package com.ihealthy.ihealthy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


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
        String[] restaurantList = {
                "Ali",
                "RU",
                "Tia Julia"
        };

        return restaurantList;
    }


}
