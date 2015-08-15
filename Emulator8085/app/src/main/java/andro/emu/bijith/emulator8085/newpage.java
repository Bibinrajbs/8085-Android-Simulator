package andro.emu.bijith.emulator8085;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class newpage extends Activity
{

    int pc_reg1;
    Button str1;
    EditText in1;
    TextView stradr;
    String data2[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpage);
        str1 = (Button) findViewById(R.id.str1);
        in1 = (EditText)findViewById(R.id.in1);
        stradr=(TextView)findViewById(R.id.stradr);
        //Bundle b=this.getIntent().getExtras();
        //String [] a=b.getStringArray(key);
        //stradr.setText(a[0]);
        data2= getIntent().getStringArrayExtra("key");
        str1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pc_reg1=Integer.parseInt(in1.getText().toString(),16);
                Intent i;
                i = new Intent(newpage.this, test.class);
                i.putExtra("pass",pc_reg1);
                i.putExtra("key1",data2);
                newpage.this.startActivity(i);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(newpage.this,scndpg.class);
        newpage.this.startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_newpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
