package com.example.khushi.jsonex;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;

public class MainActivity extends AppCompatActivity {
Button button;
    TextView textview;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button1);
        textview = (TextView) findViewById(R.id.tv);
        requestQueue = Volley.newRequestQueue(this);

       button.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,"http://192.168.43.22/test.json",
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray jsonArray= null;
                            try {
                                jsonArray = response.getJSONArray("students");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            for(int i=0;i<jsonArray.length();i++){
                                try {
                                    JSONObject student=jsonArray.getJSONObject(i);

                                    String firstname=student.getString("firstname");
                                    String lastname=student.getString("lastname");
                                    String age=student.getString("age");

                                    textview.append(firstname+""+lastname+""+age+"\n ");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
               requestQueue.add(jsonObjectRequest);
           }
       }
       );
    }
}