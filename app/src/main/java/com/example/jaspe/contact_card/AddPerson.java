package com.example.jaspe.contact_card;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class AddPerson extends AppCompatActivity implements  Response.Listener, Response.ErrorListener{
    private RequestQueue mQueue;
    private Contact contacten[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        Button clickButton = (Button) findViewById(R.id.getPersonButton);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url = "https://randomuser.me/api/";

        final MyJSONObjectRequest req = new MyJSONObjectRequest(Request.Method.GET,
                url,
                new JSONObject(),
                this,
                this);
        req.setTag("MyVolleyActivityTAG");
        mQueue.add(req);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mQueue != null )
            mQueue.cancelAll("MyVolleyActivityTAG");
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("",error.getMessage());
    }

    @Override
    public void onResponse(Object response) {
        try {
            JSONObject json = ((JSONObject) response);
            Iterator itr = json.keys();

            while(itr.hasNext()){
                String key = (String)itr.next();
                JSONObject object = (JSONObject) json.get(key);
                JSONObject state = object.getJSONObject("state");
                String lamp = object.getString("name");
                String type = object.getString("type");

                if(!(type.contains("Dimmable light"))) {
                    boolean powerOn = state.getBoolean("on");
                    int hue = state.getInt("hue");
                    int verzadigheid = state.getInt("sat");
                    int helderheid = state.getInt("bri");

                    //lampen.add(new HueLamp(key, lamp, powerOn, hue, verzadigheid, helderheid));
                }
            }
        }
        catch (JSONException exception) {
            exception.printStackTrace();
        }
    }
}
