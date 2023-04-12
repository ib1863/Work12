package com.example.work12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The Et.
     */
    EditText et;
    /**
     * The Tv.
     */
    TextView tv;
    /**
     * The Btn 1.
     */
    Button btn1, /**
     * The Btn 2.
     */
    btn2, /**
     * The Btn 3.
     */
    btn3;
    /**
     * The Fis.
     */
    FileInputStream fis;
    /**
     * The Fos.
     */
    FileOutputStream fos;
    /**
     * The Words.
     */
    String words = "";
    /**
     * The Line.
     */
    String line;

    /**
     * The Isr.
     */
    InputStreamReader isr;
    /**
     * The Br.
     */
    BufferedReader br;
    /**
     * The Sb.
     */
    StringBuffer sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editTextTextPersonName);
        tv = (TextView) findViewById(R.id.textView);
        try {
            fis = openFileInput("test.txt");
        } catch (FileNotFoundException e) {
            try {
                 fos = openFileOutput("test.txt", MODE_APPEND);
                fis = openFileInput("test.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             isr = new InputStreamReader(fis);
             br = new BufferedReader(isr);
            sb = new StringBuffer();
            try {
                line = br.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            while (line != null) {
                sb.append(line + '\n');
                try {
                    line = br.readLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            words = sb.toString();
            try {
                isr.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            tv.setText(words);
        }
    }


    /**
     * Save.
     *
     * @param view the view
     */
    public void Save(View view) {
        String words2 = et.getText().toString();
        fos = null;
        try {
            fos = openFileOutput("test.txt", MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
            bw.write(tv.getText() + words2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fis= openFileInput("test.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        try {
            line = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (line != null) {
            sb.append(line+'\n');
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        words=sb.toString();
        try {
            isr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tv.setText(words);
        et.setText("");
    }

    /**
     * Reset.
     *
     * @param view the view
     */
    public void Reset(View view) {
        try {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("");
            bw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Exit.
     *
     * @param view the view
     */
    public void Exit(View view) {
        String st = et.getText().toString();
        try {
             fos = openFileOutput("test.txt",MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);
        try {
            bw.write(tv.getText()+st);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent si;
        si = new Intent(this,MainActivity.class);
        String st = menuItem.getTitle().toString();
        if(st.equals("Credit Screen")){
            startActivity(si);
        }
        return true;
    }

}