package com.example.newsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<News> list;
    String link = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.custom_list_view);
        new fetchData().execute();

    }

    class fetchData extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Fetching News");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String data = "";

            try {
                OkHttpClient client = new OkHttpClient();
                String link = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";
                Request request = new Request.Builder().url(link).build();
                Response response = client.newCall(request).execute();
                InputStream is = response.body().byteStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    data += line;
                }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                list = new ArrayList<>();
                News newNews;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject input = jsonArray.getJSONObject(i);
                    String id = input.getString("id");
                    String url = input.getString("url");
                    String description = input.getString("description");
                    String heading = input.getString("heading");
                    String reference = input.getString("reference");
                    newNews = new News(Integer.parseInt(id), url, heading, description, reference);
                    list.add(newNews);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomAdapter adapter = new CustomAdapter(MainActivity.this, R.layout.row, list);
            listView.setAdapter(adapter);

            listView.setClickable(true);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, NewsDetails.class);
                    intent.putExtra("ID", position);
                }
            });
        }
    }
}
