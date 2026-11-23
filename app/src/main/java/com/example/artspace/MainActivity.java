
package com.example.artspace;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvCounter;
    private ImageView ivArtwork;
    private TextView tvTitle;
    private TextView tvArtist;
    private Button btnPrevious;
    private Button btnNext;

    private int currentPosition = 0;

    private static final String KEY_POSITION = "current_position";

    private int[] artworkImages = {
            R.drawable.starry_night,
            R.drawable.mona_lisa,
            R.drawable.the_scream,
            R.drawable.great_wave,
            R.drawable.girl_with_pearl_earring,
            R.drawable.water_lilies
    };

    private String[] artworkTitles = {
            "Starry Night",
            "Mona Lisa",
            "The Scream",
            "The Great Wave",
            "Girl with a Pearl Earring",
            "Water Lilies"
    };

    private String[] artworkArtists = {
            "Vincent van Gogh",
            "Leonardo da Vinci",
            "Edvard Munch",
            "Katsushika Hokusai",
            "Johannes Vermeer",
            "Claude Monet"
    };

    private String[] artworkYears = {
            "1889",
            "1503-1519",
            "1893",
            "1831",
            "1665",
            "1906"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.ivArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtist = findViewById(R.id.tvArtist);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(KEY_POSITION, 0);
        }

        showArtwork();

        btnNext.setOnClickListener(v -> {
            if (currentPosition < artworkImages.length - 1) {
                currentPosition++;
                showArtwork();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                showArtwork();
            }
        });

        // Open artwork details using an explicit Intent
        ivArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ArtworkDetailsActivity.class
            );

            intent.putExtra("artwork_title", artworkTitles[currentPosition]);
            intent.putExtra("artwork_artist", artworkArtists[currentPosition]);
            intent.putExtra("artwork_year", artworkYears[currentPosition]);

            startActivity(intent);
        });
    }

    private void showArtwork() {
        ivArtwork.setImageResource(artworkImages[currentPosition]);

        tvTitle.setText(artworkTitles[currentPosition]);
        tvArtist.setText(
                artworkArtists[currentPosition] + ", " +
                        artworkYears[currentPosition]
        );

        tvCounter.setText(
                (currentPosition + 1) + "/" + artworkImages.length
        );

        btnPrevious.setEnabled(currentPosition > 0);

        btnNext.setEnabled(
                currentPosition < artworkImages.length - 1
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_POSITION, currentPosition);
    }
}

