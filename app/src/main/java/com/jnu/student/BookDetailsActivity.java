package com.jnu.student;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        Button buttonOk = findViewById(R.id.button_item_details_ok);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                EditText editBookTitle = findViewById(R.id.edittext_book_name);
                intent.putExtra("title",editBookTitle.getText().toString());
                setResult(Activity.RESULT_OK,intent);
                BookDetailsActivity.this.finish();
            }
        });
    }
}