package com.example.androidrentsystem;

import static com.example.androidrentsystem.Constants.GYM_LIST_URL;
import static com.example.androidrentsystem.Constants.GYM_SEARCH_URL;
import static com.example.androidrentsystem.Constants.USER_LOGIN_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.androidrentsystem.model.Rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GymList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_list);

        ListView listView = findViewById(R.id.listView);
        ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(()->{
            //atitinka is asyncTask doInBackground
            try {
                JSONArray gyms = Rest.sendGet(GYM_LIST_URL);
                if (gyms != null) {
                    for(int i=0; i<gyms.length(); i++){
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject e = gyms.getJSONObject(i);
                        map.put("id", e.getString("id"));
                        map.put("name", e.getString("name"));
                        map.put("address", e.getString("address"));
                        myList.add(map);
                    }
                    System.out.println(gyms);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        ListAdapter adapter = new SimpleAdapter(this, myList, R.layout.row_layout, new String[] {"name", "address"}, new int[] {R.id.gym_title, R.id.gym_address});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //map=adapter.getItem(i);
                final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
                int lastNumberInt=0;
                String input = adapter.getItem(i).toString();
                input = removeLastChar(input);
                Matcher matcher = lastIntPattern.matcher(input);
                if (matcher.find()) {
                    String someNumberStr = matcher.group(1);
                    lastNumberInt = Integer.parseInt(someNumberStr);
                }
                Toast.makeText(GymList.this, "id = " + lastNumberInt,Toast.LENGTH_SHORT).show();
                final String GYM_ID = Integer.toString(lastNumberInt);

                Intent intent = new Intent(GymList.this, GymCard.class);
                intent.putExtra("key", GYM_ID);
                startActivity(intent);

            }
        });
    }
    private String removeLastChar(String s)
    {
        return s.substring(0, s.length() - 1);
    }
}