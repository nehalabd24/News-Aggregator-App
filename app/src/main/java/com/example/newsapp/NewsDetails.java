package com.example.newsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

public class NewsDetails extends AppCompatActivity {

    @Override
    public void setActionBar(@Nullable Toolbar toolbar) {
        super.setActionBar(toolbar);
    }

    TextView headingView;
    TextView descView;
    TextView idView;
    ImageView imageView;
    Button share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        headingView = findViewById(R.id.newsHeadingDetail);
        idView = findViewById(R.id.newsIDDetail);
        descView = findViewById(R.id.newsDescriptionDetail);
        imageView = findViewById(R.id.newsImageDetail);
        share = findViewById(R.id.share);

        Intent intent = getIntent();
        String reference = intent.getStringExtra("reference");
        String head = intent.getStringExtra("heading");
        headingView.setText(head);
        String desc = intent.getStringExtra("description");
        descView.setText(desc);
        String id  = intent.getStringExtra("id");
        idView.setText(id);
        String link = intent.getStringExtra("url");
        Glide.with(this).load(link).into(imageView);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, reference);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });
    }
}