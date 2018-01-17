package com.ihealthy.ihealthy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =inflater.inflate(R.layout.fragment_history, container, false);


        return view;
    }

    private void fillHistoryValues(View view){
        int [] alreadyConsumedCalories = getAlreadyConsumedCalories();
        TextView tv_sunday = view.findViewById(R.id.tv_sunday);
        tv_sunday.setText(alreadyConsumedCalories[0]);
        TextView tv_monday = view.findViewById(R.id.et_monday);
        tv_monday.setText(alreadyConsumedCalories[1]);
        TextView tv_tuesday = view.findViewById(R.id.et_tuesday);
        tv_tuesday.setText(alreadyConsumedCalories[2]);
        TextView tv_wednesday = view.findViewById(R.id.et_wednesday);
        tv_wednesday.setText(alreadyConsumedCalories[3]);
        TextView tv_thursday = view.findViewById(R.id.et_thursday);
        tv_thursday.setText(alreadyConsumedCalories[4]);
        TextView tv_friday = view.findViewById(R.id.et_friday);
        tv_friday.setText(alreadyConsumedCalories[5]);
        TextView tv_saturday = view.findViewById(R.id.et_saturday);
        tv_saturday.setText(alreadyConsumedCalories[6]);

        int [] expectedCalories = getExpectedCalories();
        TextView tv_expected_sunday = view.findViewById(R.id.tv_expected_sunday);
        tv_expected_sunday.setText(expectedCalories[0]);
        TextView tv_expected_monday = view.findViewById(R.id.et_expected_monday);
        tv_expected_monday.setText(expectedCalories[1]);
        TextView tv_expected_tuesday = view.findViewById(R.id.et_expected_tuesday);
        tv_expected_tuesday.setText(expectedCalories[2]);
        TextView tv_expected_wednesday = view.findViewById(R.id.et_expected_wednesday);
        tv_expected_wednesday.setText(expectedCalories[3]);
        TextView tv_expected_thursday = view.findViewById(R.id.et_expected_thursday);
        tv_expected_thursday.setText(expectedCalories[4]);
        TextView tv_expected_friday = view.findViewById(R.id.et_expected_friday);
        tv_expected_friday.setText(expectedCalories[5]);
        TextView tv_expected_saturday = view.findViewById(R.id.et_expected_saturday);
        tv_expected_saturday.setText(expectedCalories[6]);

    }

    private int[] getAlreadyConsumedCalories(){
        int[] fetched_data = {0,0,0,0,0,0,0};

        //Criando objeto de busca
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("myconsumeddiet");
        query.whereEqualTo("UserId", "0");

        try {
            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            if(objects.size()>0){
                fetched_data[0] = objects.get(0).getInt("sunday");
                fetched_data[1] = objects.get(0).getInt("monday");
                fetched_data[2] = objects.get(0).getInt("tuesday");
                fetched_data[3] = objects.get(0).getInt("wednesday");
                fetched_data[4] = objects.get(0).getInt("thursday");
                fetched_data[5] = objects.get(0).getInt("friday");
                fetched_data[6] = objects.get(0).getInt("saturday");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fetched_data;
    }

    private int[] getExpectedCalories(){
        int[] fetched_data = {0,0,0,0,0,0,0};

        //Criando objeto de busca
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("mydiet");
        query.whereEqualTo("UserId", "0");

        try {
            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            if(objects.size()>0){
                fetched_data[0] = objects.get(0).getInt("sunday");
                fetched_data[1] = objects.get(0).getInt("monday");
                fetched_data[2] = objects.get(0).getInt("tuesday");
                fetched_data[3] = objects.get(0).getInt("wednesday");
                fetched_data[4] = objects.get(0).getInt("thursday");
                fetched_data[5] = objects.get(0).getInt("friday");
                fetched_data[6] = objects.get(0).getInt("saturday");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fetched_data;
    }

}
