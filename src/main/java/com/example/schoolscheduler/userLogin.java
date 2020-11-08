package com.example.schoolscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class userLogin extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPw;
    private Button userLogin;
    private Button switchToSignUp;
    private TextView userAttempts;
    private int attempt = 5;
    FirebaseAuth auth;

    String inputEmail = "";
    String inputPw = "";

    String email = "kenzhou@buffalo.edu";
    String pw = "1234";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        userEmail = (EditText) findViewById(R.id.loginEmail);
        userPw = (EditText) findViewById(R.id.loginPW);
        userLogin = (Button) findViewById(R.id.login);
        userAttempts = (TextView) findViewById(R.id.loginAttempts);
        switchToSignUp = (Button) findViewById(R.id.goToSignUP);
        auth = FirebaseAuth.getInstance();

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEmail = userEmail.getText().toString();
                inputPw = userPw.getText().toString();

                if(inputEmail.isEmpty() || inputPw.isEmpty()){
                    Toast.makeText(userLogin.this, "PLEASE ENTER ALL DETAILS CORRECTLY!", Toast.LENGTH_SHORT).show();
                }
                //else if(!validate(inputEmail, inputPw))
                else
                {
                    auth.signInWithEmailAndPassword(inputEmail,inputPw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(userLogin.this, "SUCCESSFULLY LOGGED IN", Toast.LENGTH_SHORT).show();
                                Intent transfer = new Intent(userLogin.this, Setting.class);
                                startActivity(transfer);
                            }
                            else{
                                Toast.makeText(userLogin.this, "THERE WAS A ERROR! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                attempt --;
                                userAttempts.setText("NUMBER OF ATTEMPTS REMAINING:" + attempt);

                                if(attempt == 0){
                                    userLogin.setEnabled(false);
                                }
                            }
                        }
                    });

                    //Toast.makeText(userLogin.this, "INCORRECT EMAIL OR PASSWORD!", Toast.LENGTH_SHORT).show();
                }
//                else{
//                    Intent transfer = new Intent(userLogin.this, Setting.class);
//                    startActivity(transfer);
//                }
            }
        });

        switchToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transfer = new Intent(userLogin.this, userSignUp.class);
                startActivity(transfer);
            }
        });
    }

    private boolean validate(String name, String password){

        return name.equals(email) && password.equals(pw);
    }
}