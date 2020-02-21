package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    Button btnClick, btnRead, btnDelete, btnUpdate;
    EditText txtName, txtSurname, txtMark, txtId;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        txtMark = findViewById(R.id.idMark);
        txtSurname = findViewById(R.id.idSurname);
        txtName = findViewById(R.id.idName);
        btnClick = findViewById(R.id.idBtn);
        btnRead = findViewById(R.id.idBtnRead);
        txtResult = findViewById(R.id.idResult);
        txtId = findViewById(R.id.idID);
        btnDelete = findViewById(R.id.idHapus);
        btnUpdate = findViewById(R.id.idUpdate);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadMe();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteMe();
                ReadMe();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateMe();
            }
        });

    }

    private void UpdateMe() {

        String id = txtId.getText().toString();
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String mark = txtMark.getText().toString();
    Boolean result = myDb.uodateData(id, name, surname, mark);
    if (result == true)
    {
        Toast.makeText(this, "Data Update Success", Toast.LENGTH_SHORT).show();
    }
    else
    {
        Toast.makeText(this, "Data Update Failed", Toast.LENGTH_SHORT).show();
    }

    }


    private void DeleteMe() {

        String id = txtId.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this, result + ":Row Affected", Toast.LENGTH_SHORT).show();

    }

    private void ReadMe() {

        Cursor res = myDb.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("Id :" + res.getString(0) + "\n");
                stringBuffer.append("Name :" + res.getString(1) + "\n");
                stringBuffer.append("Surname :" + res.getString(2) + "\n");
                stringBuffer.append("Marks :" + res.getString(3) + "\n");
            }

            txtResult.setText(stringBuffer.toString());
            Toast.makeText(this, "Data Retrieved Succesfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Retrieved Failed", Toast.LENGTH_SHORT).show();
        }


    }

    private void ClickMe() {

        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String mark = txtMark.getText().toString();
        Boolean result = myDb.insertData(name, surname, mark);

        if (result == true) {
            Toast.makeText(this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Inserted Failed", Toast.LENGTH_SHORT).show();

        }
    }
}
