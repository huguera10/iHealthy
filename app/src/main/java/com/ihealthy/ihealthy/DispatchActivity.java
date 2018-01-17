package com.ihealthy.ihealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        //Se o usuario ja estiver logado, vai para a main activity
        if (ParseUser.getCurrentUser() != null){
            startActivity(new Intent(this, MainActivity.class));
        } else
            //Se o usuario não estiver logado, vai para pagina de login
            startActivity(new Intent(this, LoginActivity.class));
    }
}
