package andro.emu.bijith.emulator8085;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class StartPage extends Activity
{

    Button strtbt,quitbt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        strtbt = (Button)findViewById(R.id.strtbt);
        quitbt = (Button)findViewById(R.id.quitbt);
        strtbt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i;
                i=new Intent(StartPage.this,scndpg.class);
                StartPage.this.startActivity(i);
                finish();

            }
        });
        quitbt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v1)
            {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
