package com.example.foodrecipeproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView FoodDescription;
    ImageView FoodImage;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FoodDescription = (TextView)findViewById(R.id.txtdescription);
        FoodImage = (ImageView)findViewById(R.id.ivImage2);


        Bundle mBundle = getIntent().getExtras();

        if(mBundle!=null){
            FoodDescription.setText(mBundle.getString("Description"));
           // FoodImage.setImageResource(mBundle.getInt("Image"));


            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(FoodImage);

        }
    }


}