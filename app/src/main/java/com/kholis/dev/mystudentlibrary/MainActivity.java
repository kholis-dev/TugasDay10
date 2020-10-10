package com.kholis.dev.mystudentlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText etName;
    TextView tvAllName;
    ArrayList<String> dataNameArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextTextPersonName);
        tvAllName = findViewById(R.id.textView2);
        db = new DatabaseHelper(this);
        findViewById(R.id.idSimpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Objects.equals(etName.getText(), "")){
                    db.addStudentDetails(etName.getText().toString());
                    etName.setText("");
                    Toast.makeText(MainActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Jangan Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.idTampil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataNameArr = db.getAllStudent();
                tvAllName.setText("");
                boolean isFirst = true;
                StringBuilder builder = new StringBuilder();
                for(String data:dataNameArr){
                    if (isFirst){
                        builder.append(data);
                        isFirst = false;
                    } else {
                        builder.append(", "+data);
                    }
                }
                tvAllName.setText(builder.toString());
            }
        });
    }
}