
package com.example.artspace;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    private TextView tvDetailTitle;
    private TextView tvDetailArtist;
    private TextView tvDetailYear;
    private Button btnBack;
    private Button btnVisitWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailArtist = findViewById(R.id.tvDetailArtist);
        tvDetailYear = findViewById(R.id.tvDetailYear);
        btnBack = findViewById(R.id.btnBack);
        btnVisitWebsite = findViewById(R.id.btnVisitWebsite);

        // Receive artwork information from MainActivity
        Intent intent = getIntent();

        String title = intent.getStringExtra("artwork_title");
        String artist = intent.getStringExtra("artwork_artist");
        String year = intent.getStringExtra("artwork_year");

        // Display the received information
        tvDetailTitle.setText(title);
        tvDetailArtist.setText(artist);
        tvDetailYear.setText(year);

        // Return to the gallery
        btnBack.setOnClickListener(v -> finish());

        // Open an external website using an implicit Intent
        btnVisitWebsite.setOnClickListener(v -> {
            String url = "https://www.google.com/search?q="
                    + Uri.encode(title);

            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
            );

            startActivity(browserIntent);
        });
    }
}
