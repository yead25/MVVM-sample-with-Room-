package com.example.mynotes.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.R;

public class InsertActivity extends AppCompatActivity {

    EditText title,description;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        button = findViewById(R.id.done_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(title.getText()) || TextUtils.isEmpty(description.getText())){
                    Toast.makeText(InsertActivity.this, "Insert Correctly", Toast.LENGTH_SHORT).show();

                }else {
                    Intent retIntent = new Intent();
                    String retTitle = title.getText().toString();
                    String retDescription = description.getText().toString();
                    retIntent.putExtra(MainActivity.TITLE_TAG,retTitle);
                    retIntent.putExtra(MainActivity.DESCRIPTION_TAG,retDescription);
                    setResult(RESULT_OK,retIntent);
                    finish();
                }
            }
        });
    }




}