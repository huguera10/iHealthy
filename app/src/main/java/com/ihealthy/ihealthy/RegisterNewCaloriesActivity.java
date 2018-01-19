package com.ihealthy.ihealthy;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class RegisterNewCaloriesActivity extends AppCompatActivity {

    private Spinner spinner=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_calories);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Register calories");//Seta o título da activity

        setWeekDaysSpinner();

        setSaveButton();

    }

    private void setWeekDaysSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner_weekdays);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.weekdays, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void setSaveButton() {
        Button btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_calories = findViewById(R.id.et_calories);
                String weekday = "Sunday";
                if(spinner!=null) {
                    weekday = spinner.getSelectedItem().toString();
                }

                registerCalories(v, weekday, et_calories);

            }
        });
    }

    private void registerCalories(View view, final String weekday, final EditText et_calories){
        ParseUser parseUser = ParseUser.getCurrentUser();
        String userId = parseUser.getObjectId();

        // dar um select e ver se esse user já tem dados salvos.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("myconsumeddiet");
        query.whereEqualTo("userID",  ParseObject.createWithoutData("_User",userId));

        try {
            //busca na web a lista de resultados da querry
            final List<ParseObject> objects = query.find();

            if (objects.size() > 0) { //se já tem dados, precisamos do update

                String objId = objects.get(0).getObjectId();
                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("myconsumeddiet");
                query1.getInBackground(objId, new GetCallback<ParseObject>() {
                    public void done(ParseObject objClass, ParseException e) {
                        System.out.println(e);
                        if (e == null) {

                            int consumedCalories = objects.get(0).getInt(weekday.toLowerCase());

                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            String et_calories_str = et_calories.getText().toString();
                            objClass.put(weekday.toLowerCase(), Integer.parseInt(et_calories_str) + consumedCalories);

                            objClass.saveInBackground(); //TODO FICAR ESPERTO COM O BACKGROUND

                        }
                    }
                });


            } else { //senao adiciona os dados novos

                ParseObject objClass = new ParseObject("myconsumeddiet");
                objClass.put("userID", ParseObject.createWithoutData(ParseUser.class, userId) );

                if(!weekday.toLowerCase().equals("sunday")) {
                    objClass.put("sunday", 0);
                }
                if(!weekday.toLowerCase().equals("monday")) {
                    objClass.put("monday", 0);
                }
                if(!weekday.toLowerCase().equals("tuesday")) {
                    objClass.put("tuesday", 0);
                }
                if(!weekday.toLowerCase().equals("wednesday")) {
                    objClass.put("wednesday", 0);
                }
                if(!weekday.toLowerCase().equals("thursday")) {
                    objClass.put("thursday", 0);
                }
                if(!weekday.toLowerCase().equals("friday")) {
                    objClass.put("friday", 0);
                }
                if(!weekday.toLowerCase().equals("saturday")) {
                    objClass.put("saturday", 0);
                }

                String et_calories_str = et_calories.getText().toString();
                objClass.put(weekday.toLowerCase(), Integer.parseInt(et_calories_str));

                objClass.saveInBackground(); //TODO FICAR ESPERTO COM O BACKGROUND
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }



        Snackbar.make(view, "Saved!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

//        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}