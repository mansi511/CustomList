package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String HI = "https://api.github.com/repositories";
    private ArrayList<List_Data> list_data;
    private ListView lv;
    private CustemAdapter adapter;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.list_view);
        list_data=new ArrayList<>();

        getdata();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                List_Data listData=list_data.get(i);

                final String url_own=listData.getOwnerUrl();
                Intent intent=new Intent(MainActivity.this,Detail.class);
                intent.putExtra("img",url_own);
                startActivity(intent);

            }
        });

    }

    private void getdata() {


        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        jsonObject=response.getJSONObject(i);

                        // get sub-object which contains repo owner info
                        JSONObject ownerObject=jsonObject.getJSONObject("owner");

                        // From the owner info object get the name of the owner.
                        String ownerName=ownerObject.getString("login");

                        // Repo name of the user
                        String repoName=jsonObject.getString("name");


                        JSONObject new_owner = jsonObject.getJSONObject("owner");
                        String Url_owner = new_owner.getString("url");

                        // List_Data listData=new List_Data(repoName,ownerName);
                        list_data.add(new List_Data(repoName,ownerName,Url_owner));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupData(list_data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setupData(ArrayList<List_Data> list_data) {
        adapter=new CustemAdapter(getApplicationContext(),list_data);
        lv.setAdapter(adapter);
    }
}