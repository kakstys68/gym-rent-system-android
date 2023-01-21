package com.example.androidrentsystem;

import static com.example.androidrentsystem.Constants.GYM_LIST_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;

import com.example.androidrentsystem.model.Rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GymCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_card);
        EditText gymName = findViewById(R.id.gymNameField);
        EditText gymAddress = findViewById(R.id.gymAddressField);
        EditText gymPrice = findViewById(R.id.gymPriceField);
        EditText gymType = findViewById(R.id.gymTypeField);
        Bundle extras = getIntent().getExtras();
        final String GYM_ID = extras.getString("key");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(()->{
            try {
                JSONArray gym = Rest.sendGet(GYM_LIST_URL + GYM_ID);
                JSONObject e = gym.getJSONObject(0);
                gymName.setText(e.getString("name"));
                gymAddress.setText(e.getString("address"));
                gymPrice.setText(e.getString("hourlyPrice"));
                gymType.setText(e.getString("type"));
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        });
        //System.out.println(gym);
    }


}
