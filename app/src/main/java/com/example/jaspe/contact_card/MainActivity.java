package com.example.jaspe.contact_card;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView _contactListView;
    private ContactAdapter _contactAdapter;
    private ArrayList<Contact> _contacten = new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PersonsDatabase adb = new PersonsDatabase(getApplicationContext());
        Cursor cursor = adb.getPersons();

        getPerson(cursor);

        while (cursor.moveToNext()) {
            getPerson(cursor);
        }

        _contactListView = (ListView) findViewById(R.id.contactenListView);

        _contactAdapter = new ContactAdapter(this,
                getLayoutInflater(),
                _contacten);
        _contactListView.setAdapter(_contactAdapter);

        _contactAdapter.notifyDataSetChanged();
        _contactListView.setOnItemClickListener(this);
    }

    private void getPerson(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String naam = cursor.getString(cursor.getColumnIndex("naam"));
        String email = cursor.getString(cursor.getColumnIndex("email"));
        String telefoon = cursor.getString(cursor.getColumnIndex("telefoon"));
        String adres = cursor.getString(cursor.getColumnIndex("adres"));
        String postcode = cursor.getString(cursor.getColumnIndex("postcode"));
        String stad = cursor.getString(cursor.getColumnIndex("stad"));
        String plaatje = cursor.getString(cursor.getColumnIndex("plaatje"));

        Contact contact = new Contact(id, naam, adres, telefoon, email, postcode, stad, plaatje);
        
        _contacten.add(contact);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(), DetailedView.class);
        i.putExtra("CONTACT", _contacten.get(position));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
