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

public class RegisterNewCaloriesActivity extends AppCompatActivity {
private Spinner spinner;

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
                int weekday_id = -1;
                if(spinner!=null) {
                    weekday_id = spinner.getSelectedItemPosition();
                }

                registerCalories(v, weekday_id, et_calories);

            }
        });
    }

    private void registerCalories(View view, int weekday, EditText et_calories){
        Snackbar.make(view, "Implementar registro de calorias", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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