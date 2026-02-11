package com.example.foodrecipeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FoodData> myFoodList;
    FoodData mFoodData;
    EditText txt_search;
//    MyAdapter myAdapter;

    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    FloatingActionButton upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txt_search = findViewById(R.id.txt_searchText);
        mRecyclerView = findViewById(R.id.recycleview);
        upload = findViewById(R.id.btn_uploadActivity);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Home.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Loading items....");

        myFoodList = new ArrayList<>();


        MyAdapter myAdapter = new MyAdapter(Home.this,myFoodList);
        mRecyclerView.setAdapter(myAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myFoodList.clear();


                for(DataSnapshot itemSnapshot: snapshot.getChildren()){

                    FoodData foodData = itemSnapshot.getValue(FoodData.class);
                    myFoodList.add(foodData);
                }

                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });


        //Search Bar code
//        txt_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//
//
//            }
//        });




        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Upload_recipe.class));
            }
        });



    }

//    private void filter(String string) {
//
//        ArrayList<FoodData> filterList = new ArrayList<>();
//
//        for(FoodData item: myFoodList){
//            if(item.getItemName().toLowerCase().contains(string.toLowerCase()));
//
//            filterList.add(item);
//        }
////        myAdapter.filteredList(filterList);
//
//    }


}