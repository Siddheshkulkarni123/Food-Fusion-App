package com.example.foodrecipeproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_recipe extends AppCompatActivity {

    ImageView recipeimage;
    Uri uri;
    Button selectImage , uploadRecipe;
    EditText name,description,time;
    String imageUrl;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        recipeimage = findViewById(R.id.iv_foodImage);
        selectImage = findViewById(R.id.btnSelectImage);
        uploadRecipe = findViewById(R.id.btnUploadRecipe);

        name = (EditText) findViewById(R.id.txt_recipe_name);
        description = (EditText) findViewById(R.id.text_description);
        time = (EditText)findViewById(R.id.text_time);



    }
    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            uri = data.getData();
            recipeimage.setImageURI(uri);
        }
        else{
            Toast.makeText(this, "You Haven't picked any Image", Toast.LENGTH_LONG).show();
        }
    }//end of activityResult

    public void uploadRecipe(){

        FoodData foodData = new FoodData(name.getText().toString(),
                description.getText().toString(),
                time.getText().toString(),
                imageUrl
        );

        String myCurrentdateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Recipe")
                .child(myCurrentdateTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Upload_recipe.this, "Recipe Uploaded", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(Upload_recipe.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void uploadImage(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("RecipeImage").child(uri.getLastPathSegment());

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe uploading");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
            }
        });
    }//End of Upload Image method





    public void btnUploadRecipe(View view) {
        uploadImage();
    }
}