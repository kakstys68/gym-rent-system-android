package com.example.androidrentsystem;

import static com.example.androidrentsystem.Constants.USER_LOGIN_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.androidrentsystem.model.Rest;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void validate (View view){
        EditText username = findViewById(R.id.usernameField);
        EditText password = findViewById(R.id.passwordField);
        CheckBox checkBox = findViewById(R.id.checkBoxField);

        String data = "{\"username\":\"" + username.getText() + "\",\n" +
                       "\"password\":\"" + password.getText() + "\"\n}";

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(()->{
            //atitinka is asyncTask doInBackground
            try {
                String response = Rest.sendPost(USER_LOGIN_URL, data);
                System.out.println(response);
                handler.post(()->{
                    if(!response.equals("ERROR ON POST REQUEST") && !response.equals("No such user")){
                        Intent intent = new Intent(MainActivity.this, GymList.class);
                        intent.putExtra("User", response);
                        startActivity(intent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}