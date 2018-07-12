package com.example.eodhuno.ebelle_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    //ImageView catImage;
    EditText imagTextView, catName, catDescr;
    Button addCategory;

    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_category);

        mDatabase = new DatabaseManager(this);

        imagTextView = (EditText) findViewById(R.id.imageTextView);
        catName = (EditText) findViewById(R.id.Category_name_edit_text);
        catDescr = (EditText) findViewById(R.id.Category_description_edit_text);

        findViewById(R.id.AddCategory_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCategory();
            }
        });

    }

    private void addNewCategory() {
        String categoryImageText = imagTextView.getText().toString().trim();
        String categoryName = catName.getText().toString().trim();
        String categoryDescr = catDescr.getText().toString().trim();


        if (categoryImageText.isEmpty()) {
            imagTextView.setError("Cannot be empty");
            imagTextView.requestFocus();
            return;
        }
        if (categoryName.isEmpty()) {
            catName.setError("Cannot be empty");
            catName.requestFocus();
            return;
        }
        if (categoryDescr.isEmpty()) {
            catDescr.setError("Cannot be empty");
            catDescr.requestFocus();
            return;
        }

        if (mDatabase.addCategory(categoryImageText, categoryName, categoryDescr)){
            Log.d("CATEGORY ADDED", "IMAGE TEXT: " + categoryImageText +" " +"CATEGORY NAME: " + categoryName +" " +"DESCR :" + categoryDescr);
            Toast.makeText(this, "Category created successfully", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(this, "Category not created", Toast.LENGTH_SHORT).show();
    }

    }
}
