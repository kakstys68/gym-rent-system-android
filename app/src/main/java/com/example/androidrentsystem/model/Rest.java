package com.example.androidrentsystem.model;

import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Rest {
    private static BufferedWriter bufferedWriter;
    private static OutputStream outputStream;
    private static JSONArray jsonArray = null;
    static String result = "";

    public static JSONArray sendGet(String urlGet) throws IOException {
        URL url = new URL(urlGet);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("content-type", "application/json; charset=UTF8");
        int code = httpURLConnection.getResponseCode();
        System.out.println("GET code recieved: " + code);
        if(code == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            System.out.println(response);
            if ((line = in.readLine()) != null){
                response.append(line);
            }
            in.close();
            result = response.toString();
            try{
                jsonArray = new JSONArray(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonArray;
        } else{
            System.out.println("ERROR ON GET REQUEST");
            return null;
        }
    }
    public static String sendPost(String urlPost, String postDataParams) throws IOException {
        URL url = new URL(urlPost);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("content-type", "application/json; charset=UTF8");

        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.setRequestProperty("Accept", "application/json");

        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        System.out.println(url);
        System.out.println(postDataParams);
        outputStream = httpURLConnection.getOutputStream();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        }
        bufferedWriter.write(postDataParams);
        bufferedWriter.close();
        outputStream.close();
        int code = httpURLConnection.getResponseCode();
        System.out.println("POST code recieved: " + code);
        if (code == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null){
                response.append(line);
            }
            in.close();
            return response.toString();
        } else {
            return "ERROR ON POST REQUEST";
        }
    }
}
