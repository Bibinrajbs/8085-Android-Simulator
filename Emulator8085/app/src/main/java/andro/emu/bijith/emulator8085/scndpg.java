package andro.emu.bijith.emulator8085;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class scndpg extends Activity
{

    Button newbt,openbt,backbt;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scndpg);
        newbt=(Button) findViewById(R.id.newbt);
        openbt=(Button) findViewById(R.id.openbt);
        backbt=(Button) findViewById(R.id.backbt);
        backbt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i;
                i=new Intent(scndpg.this,StartPage.class);
                scndpg.this.startActivity(i);
                finish();

            }
        });
        newbt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i1;
                i1=new Intent(scndpg.this,hexinput.class);
                scndpg.this.startActivity(i1);
                finish();
            }
        });
        openbt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v1)
            {
                Intent i;
                i=new Intent(scndpg.this,openpg.class);
                scndpg.this.startActivity(i);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(scndpg.this,StartPage.class);
        scndpg.this.startActivity(i);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scndpg, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
