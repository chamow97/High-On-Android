package com.example.root.writing_to_storage;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textmsg=(EditText)findViewById(R.id.editText1);
    }

    public void WriteBtn(View v) {
        // add-write text into file
//
        try{
            FileOutputStream fileout = openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();
            Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
//
            String s = "";
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader inputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            while((charRead = inputRead.read(inputBuffer)) > 0){
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
            }
            inputRead.close();
            textmsg.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
