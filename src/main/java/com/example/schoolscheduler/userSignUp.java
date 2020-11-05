package com.example.schoolscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userSignUp extends AppCompatActivity {

    private EditText sUserEmail;
    private EditText sUserPw;
    private EditText sReUserPw;
    private Button LogInButton;
    private Button signUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        sUserEmail = (EditText) findViewById(R.id.SignUpEmail);
        sUserPw = (EditText) findViewById(R.id.SignUpPassword);
        sReUserPw = (EditText) findViewById(R.id.rePW);
        Button signUpButton = (Button) findViewById(R.id.signUp);
        LogInButton = (Button) findViewById(R.id.gotologin);




        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = sUserEmail.getText().toString();
                String inputPW = sUserPw.getText().toString();
                String inputRePW = sReUserPw.getText().toString();

                //!validateBothPasswords(inputRePW, inputPW

                if(!inputPW.equals(inputRePW)){
                    Toast.makeText(userSignUp.this, "PASSWORD AND RE-PASSWORD DID NOT MATCH", Toast.LENGTH_SHORT).show();
                }
                else if(!validatePassword(inputPW)){
                    Toast.makeText(userSignUp.this, "INVALID PASSWORD: MISSING CREDENTIAL", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(userSignUp.this, "SUCCESSFULLY CREATED ACCOUNT", Toast.LENGTH_SHORT).show();
                    Intent transfer = new Intent(userSignUp.this, Setting.class);
                    startActivity(transfer);
                }

            }

        });

        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfer = new Intent(userSignUp.this, userLogin.class);
                startActivity(transfer);
            }
        });

    }

//    private boolean validateBothPasswords(String rePw, String password){
//        return !rePw.equals(password);
//    }

    private boolean validatePassword(String password){
        boolean num = false;
        boolean uppercase = false;
        boolean lowercase = false;

        for(int i = 0; i < password.length(); i++){
            char character = password.charAt(i);
            if(Character.isDigit(character)){
                num = true;
            }
            if(Character.isUpperCase(character)){
                uppercase = true;
            }
            if(Character.isLowerCase(character)){
                lowercase = true;
            }
        }

        if(password.length() < 8){
            return false;
        }

        return num && uppercase && lowercase;
    }

}