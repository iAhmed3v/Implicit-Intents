package com.ahmed3v.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText webEditText, locationEditText, shareEditText;
    private Button webButton, locationButton, shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webEditText = findViewById(R.id.web_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        shareEditText = findViewById(R.id.share_edit_text);

        webButton = findViewById(R.id.web_btn);
        locationButton = findViewById(R.id.location_btn);
        shareButton = findViewById(R.id.share_btn);

        webButton.setOnClickListener(v -> {

            // Get the URL text.
            String url = webEditText.getText().toString().trim();

            // Parse the URI and create the intent.
            Uri webPage = Uri.parse(url);

            Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);

            // Find an activity to hand the intent and start that activity.
            if(webIntent.resolveActivity(getPackageManager()) != null){

                startActivity(webIntent);

            }else {
                Toast.makeText(this , "Something went wrong!" , Toast.LENGTH_SHORT).show();
            }
        });



        locationButton.setOnClickListener(v -> {

            // Get the URL text.
            String url = locationEditText.getText().toString().trim();

            // Parse the location and create the intent.
            Uri location = Uri.parse("geo:0,0?q=" + url);

            Intent locationIntent = new Intent(Intent.ACTION_VIEW, location);

            // Find an activity to hand the intent and start that activity.
            if(locationIntent.resolveActivity(getPackageManager()) != null){

                startActivity(locationIntent);

            }else {
                Toast.makeText(this , "Something went wrong!" , Toast.LENGTH_SHORT).show();
            }
        });

        shareButton.setOnClickListener(v -> {

            // Get the text.
            String txt = shareEditText.getText().toString().trim();

            //Set the text type
            String textType = "text/plain";

            //Set the ShareCompat.IntentBuilder to help us to share this is text
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(textType)
                    .setChooserTitle(R.string.share_text_with)
                    .setText(txt)
                    .startChooser();
        });
    }
}