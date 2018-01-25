package com.ihealthy.ihealthy;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDietFragment extends Fragment {

    public MyDietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_my_diet, container, false);
        getActivity().setTitle("My Diet");

        setSaveButton(view);

        return view;
    }

    private void setSaveButton(final View view) {
        Button btnSave = (Button) view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_sunday = (EditText) view.findViewById(R.id.et_sunday);
                EditText et_monday = (EditText) view.findViewById(R.id.et_monday);
                EditText et_tuesday = (EditText) view.findViewById(R.id.et_tuesday);
                EditText et_wednesday = (EditText) view.findViewById(R.id.et_wednesday);
                EditText et_thursday = (EditText) view.findViewById(R.id.et_thursday);
                EditText et_friday = (EditText) view.findViewById(R.id.et_friday);
                EditText et_saturday = (EditText) view.findViewById(R.id.et_saturday);

                registerDiet(v, et_sunday,et_monday,et_tuesday,et_wednesday,et_thursday,et_friday,et_saturday);
            }
        });
    }

    private void registerDiet(View view, final EditText et_sunday, final EditText et_monday, final EditText et_tuesday,
                              final EditText et_wednesday, final EditText et_thursday, final EditText et_friday, final EditText et_saturday){

        ParseUser parseUser = ParseUser.getCurrentUser();
        String userId = parseUser.getObjectId();

        // dar um select e ver se esse user já tem dados salvos.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("mydiet");
        query.whereEqualTo("userID",  ParseObject.createWithoutData("_User",userId));

        try {
            //busca na web a lista de resultados da querry
            List<ParseObject> objects = query.find();

            if (objects.size() > 0) { //se já tem dados, precisamos do update

                String objId = objects.get(0).getObjectId();
                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("mydiet");
                query1.getInBackground(objId, new GetCallback<ParseObject>() {
                    public void done(ParseObject objClass, ParseException e) {
                        System.out.println(e);
                        if (e == null) {

                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.

                            saveInfo(objClass, et_sunday,et_monday,et_tuesday,et_wednesday,et_thursday,et_friday,et_saturday);

                        }
                    }
                });


            } else { //senao adiciona os dados novos
                ParseObject objClass = new ParseObject("mydiet");
                objClass.put("userID", ParseObject.createWithoutData(ParseUser.class, userId) );

                saveInfo(objClass, et_sunday,et_monday,et_tuesday,et_wednesday,et_thursday,et_friday,et_saturday);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Snackbar.make(view, "Diet saved!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void saveInfo(ParseObject objClass, final EditText et_sunday, final EditText et_monday, final EditText et_tuesday,
                          final EditText et_wednesday, final EditText et_thursday, final EditText et_friday, final EditText et_saturday){
        int dayValue;

        String value= et_sunday.getText().toString();
        if(!value.equals("")) {
            dayValue  = Integer.parseInt(value);
            objClass.put("sunday", dayValue);
        }

        value= et_monday.getText().toString();
        if(!value.equals("")) {
            dayValue = Integer.parseInt(value);
            objClass.put("monday", dayValue);
        }

        value= et_tuesday.getText().toString();
        if(!value.equals("")) {
            dayValue=Integer.parseInt(value);
            objClass.put("tuesday", dayValue);
        }

        value= et_wednesday.getText().toString();
        if(!value.equals("")) {
            dayValue = Integer.parseInt(value);
            objClass.put("wednesday", dayValue);
        }

        value= et_thursday.getText().toString();
        if(!value.equals("")) {
            dayValue=Integer.parseInt(value);
            objClass.put("thursday", dayValue);
        }

        value= et_friday.getText().toString();
        if(!value.equals("")) {
            dayValue = Integer.parseInt(value);
            objClass.put("friday", dayValue);
        }

        value= et_saturday.getText().toString();
        if(!value.equals("")) {
            dayValue=Integer.parseInt(value);
            objClass.put("saturday", dayValue);
        }

        objClass.saveInBackground(); //TODO FICAR ESPERTO COM O BACKGROUND
    }
}
