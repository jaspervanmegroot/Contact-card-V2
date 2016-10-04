package com.example.jaspe.contact_card;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class DetailedView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);
        Intent i = getIntent();
        Contact contact = (Contact) i.getSerializableExtra("CONTACT");

        new DownloadImageTask((ImageView)findViewById(R.id.detailedImage)).execute(contact.Plaatje);

        TextView naamTextView =(TextView)findViewById(R.id.naamTextView);
        naamTextView.setText(contact.Naam);

        TextView emailTextView =(TextView)findViewById(R.id.emailTextView);
        emailTextView.setText(contact.Email);

        TextView telefoonTextView =(TextView)findViewById(R.id.telefoonTextView);
        telefoonTextView.setText(contact.Telefoon);

        TextView adresTextView =(TextView)findViewById(R.id.adresTextView);
        adresTextView.setText(contact.Adres);

        TextView postcodeTextView =(TextView)findViewById(R.id.postcodeTextView);
        postcodeTextView.setText(contact.Postcode);

        TextView woonplaatsTextView =(TextView)findViewById(R.id.woonplaatsTextView);
        woonplaatsTextView.setText(contact.Stad);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
