package com.example.work12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The type Main activity 2.
 */
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(0,0,200,"Back");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent si;
        si = new Intent(this,MainActivity2.class);
        String st = menuItem.getTitle().toString();
        if(st.equals("back")){
            startActivity(si);
        }
        return true;
    }
}