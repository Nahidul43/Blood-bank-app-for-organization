package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateActivity extends AppCompatActivity {

    TextInputEditText edClass, edAge, edNumber,edName;
    TextView upDate;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edClass = findViewById(R.id.edClass);
        edAge = findViewById(R.id.edAge);
        edNumber = findViewById(R.id.edNumber);
        upDate = findViewById(R.id.upDate);
        edName = findViewById(R.id.edName);
        helper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("name");
        String clas = intent.getStringExtra("clas");
        String age = intent.getStringExtra("age");
        String number = intent.getStringExtra("number");

        edClass.setText(clas);
        edAge.setText(age);
        edNumber.setText(number);
        edName.setText(name);

        upDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(id);
                String updatedClas = edClass.getText().toString();
                String updatedAge = edAge.getText().toString();
                String updatedNumber = edNumber.getText().toString();
                String name = edName.getText().toString();

                helper.updateData(updatedClas, updatedAge, i, updatedNumber,name);
                Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                startActivity(new Intent(UpdateActivity.this, HomeActivity.class));
            }
        });
    }
}
