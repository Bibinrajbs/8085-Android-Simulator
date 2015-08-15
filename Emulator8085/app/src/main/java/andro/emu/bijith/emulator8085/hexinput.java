package andro.emu.bijith.emulator8085;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class hexinput extends Activity
{

    Button yesbtn,nobtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hexinput);
        yesbtn=(Button)findViewById(R.id.yesbtn);
        nobtn=(Button)findViewById(R.id.nobtn);
        nobtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(hexinput.this,newpage.class);
                hexinput.this.startActivity(i);
                finish();
            }
        });
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(hexinput.this,datain.class);
                hexinput.this.startActivity(i2);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent i1=new Intent(hexinput.this,scndpg.class);
        hexinput.this.startActivity(i1);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hexinput, menu);
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
