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

    private void registerCalories(View view, String weekday, EditText et_calories){
        ParseUser parseUser = ParseUser.getCurrentUser();
        String userId = parseUser.getObjectId();

        ParseObject objClass = new ParseObject("myconsumeddiet");

        objClass.put("userID", ParseObject.createWithoutData(ParseUser.class, userId) );
        System.out.println("\n\n\n\n\nweekday "+weekday+" - ");
        System.out.println(Integer.parseInt(et_calories.getText().toString()));
        String et_calories_str = et_calories.getText().toString();
        objClass.put(weekday.toLowerCase(), Integer.parseInt(et_calories_str));

        objClass.saveInBackground(); //TODO FICAR ESPERTO COM O BACKGROUND

        Snackbar.make(view, "Saved!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        finish();

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