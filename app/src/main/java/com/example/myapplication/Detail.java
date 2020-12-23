package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail extends AppCompatActivity {

    TextView name_text, email_text,Twitter_text, publicRepo_text, publicGits_text, followers_text,following_text;

    private JsonArrayRequest request;
    private JsonObjectRequest obj;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        name_text = (TextView)findViewById(R.id.name);
        email_text = (TextView)findViewById(R.id.email);
        Twitter_text = (TextView)findViewById(R.id.TwitterName);
        publicRepo_text = (TextView)findViewById(R.id.publicRepos);
        publicGits_text = (TextView)findViewById(R.id.publicGits);
        followers_text = (TextView)findViewById(R.id.followers);
        following_text = (TextView)findViewById(R.id.following);

        Intent i = getIntent();
        final String url_string = i.getStringExtra("img");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (url_string,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject=response;

                            String name=jsonObject.getString("name");
                            String email=jsonObject.getString("email");
                            String twitter_username=jsonObject.getString("twitter_username");
                            int public_repos=jsonObject.getInt("public_repos");
                            int public_gists=jsonObject.getInt("public_gists");
                            int followers=jsonObject.getInt("followers");
                            int following=jsonObject.getInt("following");


                            name_text.setText(name);
                            email_text.setText(email);
                            Twitter_text.setText(twitter_username);
                            publicRepo_text.setText(String.valueOf(public_repos));
                            publicGits_text.setText(String.valueOf(public_gists));
                            followers_text.setText(String.valueOf(followers));
                            following_text.setText(String.valueOf(following));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}