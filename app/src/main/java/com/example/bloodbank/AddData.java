package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddData extends AppCompatActivity {

    TextInputEditText edName, edClass, edAge, edBlood, edNumber;
    TextView submit;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        edName = findViewById(R.id.edName);
        edClass = findViewById(R.id.edClass);
        edAge = findViewById(R.id.edAge);
        edBlood = findViewById(R.id.edBlood);
        edNumber = findViewById(R.id.edNumber);
        submit = findViewById(R.id.submit);
        helper = new DatabaseHelper(this);


      loadData();
    }
    public void loadData(){
        submit.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String clas = edClass.getText().toString();
            String age = edAge.getText().toString();
            String blood = edBlood.getText().toString();
            String number = edNumber.getText().toString();

            if (!name.isEmpty() && !clas.isEmpty() && !age.isEmpty() && !blood.isEmpty() && !number.isEmpty()) {
                helper.insertData(name, clas, age, blood, number);
                Toast.makeText(AddData.this, "Inserted Data", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(AddData.this, "Please Fill in All Information", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadData();



    }
}
