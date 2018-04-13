package com.example.root.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName, editID, editEmail, editMobile;
    Button btnAddData;
    Button btnViewAll;
    Button btnDelete;
    Button btnViewUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        editID = (EditText)findViewById(R.id.editText1);
        editName = (EditText)findViewById(R.id.editText2);
        editEmail = (EditText)findViewById(R.id.editText3);
        editMobile = (EditText)findViewById(R.id.editText4);
        btnAddData = (Button) findViewById(R.id.button2);
        btnViewAll = (Button) findViewById(R.id.button3);
        btnDelete = (Button) findViewById(R.id.button4);
        btnViewUpdate = (Button) findViewById(R.id.button);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    private void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDB.deleteData(editID.getText().toString());
                        if(deletedRows > 0){
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    private void UpdateData() {
        btnViewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDB.updateData(editID.getText().toString(), editName.getText().toString(), editEmail.getText().toString(), editMobile.getText().toString());
                        if(isUpdate){
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else{

                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }




    private void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDB.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID: " + res.getString(0) + "\n");
                            buffer.append("Name: " + res.getString(1) + "\n");
                            buffer.append("E-Mail: " + res.getString(2) + "\n");
                            buffer.append("Mobile: " + res.getString(3) + "\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    private void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertData(editName.getText().toString(), editEmail.getText().toString(), editMobile.getText().toString());
                        if(isInserted){
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
