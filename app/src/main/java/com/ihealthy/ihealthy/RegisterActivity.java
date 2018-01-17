package com.ihealthy.ihealthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordAgainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameView = (EditText) findViewById(R.id.register_username);
        mEmailView = (EditText) findViewById(R.id.register_email);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mPasswordAgainView = (EditText) findViewById(R.id.register_password_again);

        Button mRegisterButton = (Button) findViewById(R.id.register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validationError = false;
                StringBuilder validationErrorMenssage = new StringBuilder("Please ");

                if(isEmpty(mUsernameView)){
                    validationError = true;
                    validationErrorMenssage.append("enter a username");
                }
                if(isEmpty(mPasswordView)){
                    if (validationError){
                        validationErrorMenssage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMenssage.append("enter a password");
                } else  if(!isMatching(mPasswordView, mPasswordAgainView)){
                    if (validationError){
                        validationErrorMenssage.append(", and ");
                    }
                    validationError = true;
                    validationErrorMenssage.append("enter the password again");
                }
                validationErrorMenssage.append(".");

                if (validationError){
                    Toast.makeText(RegisterActivity.this, validationErrorMenssage.toString(), Toast.LENGTH_LONG).show();
                    return;
                }

                register();
            }
        });
    }

    private void register(){
        String username = mUsernameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    //Register Successful
                    //You may choose what to do or display here

                    Log.d("!!!!!!!!!!!!", "Register Success");
                    Intent intent = new Intent(RegisterActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    //Register Fail
                    //get error by calling e.getMessage()

                    Log.d("!!!!!!!!!!!", "Finish Fail");
                }
            }
        });

    }

    private boolean isEmpty(EditText etText){
        if (etText.getText().toString().trim().length() > 0){
            return false;
        } else
            return true;
    }

    private boolean isMatching(EditText etText1, EditText etText2){
        if (etText1.getText().toString().equals(etText2.getText().toString())){
            return true;
        } else
            return false;
    }
}
