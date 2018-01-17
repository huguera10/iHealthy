package com.ihealthy.ihealthy;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class RegisterNewCaloriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_calories);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Register calories");//Seta o título da activity

        setSaveButton();

    }

    private void setSaveButton() {
        Button btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_calories = findViewById(R.id.et_calories);
                EditText et_week_day = findViewById(R.id.et_week_day);

                registerCalories(v, et_calories, et_week_day);
            }
        });
    }

    private void registerCalories(View view, EditText et_calories,EditText et_week_day){
        Snackbar.make(view, "Implementar registro de calorias", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}