package com.example.jaspe.contact_card;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jaspe on 27-9-2016.
 */
public class Contact implements Serializable {

    public Bitmap bitmap;
    public int ID;
    public String Naam;
    public String Adres;
    public String Telefoon;
    public String Email;
    public String Postcode;
    public String Stad;
    public String Plaatje;
    public ImageView image;

    public Contact(int id, String naam, String adres, String telefoon, String email, String postcode, String stad, String plaatje) {
        this.ID = id;
        this.Naam = naam;
        this.Adres = adres;
        this.Telefoon = telefoon;
        this.Email = email;
        this.Postcode = postcode;
        this.Stad = stad;
        this.Plaatje = plaatje;
    }
}

