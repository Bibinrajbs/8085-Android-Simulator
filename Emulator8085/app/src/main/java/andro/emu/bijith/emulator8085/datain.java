package andro.emu.bijith.emulator8085;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class datain extends Activity {
    Button ipbtn,nxtbtn,finishbtn;
    EditText dataip,dataip1;
    TextView addr,val;
    int ip=0,i=0,k=0;
    String data1;
    String data2[]=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datain);
        ipbtn=(Button)findViewById(R.id.ipbtn);
        nxtbtn=(Button)findViewById(R.id.nxtbtn);
        finishbtn=(Button)findViewById(R.id.finishbtn);
        dataip=(EditText)findViewById(R.id.dataip);
        addr=(TextView)findViewById(R.id.addr);
        dataip1=(EditText)findViewById(R.id.dataip1);
        addr.setVisibility(View.INVISIBLE);
        dataip1.setVisibility(View.INVISIBLE);
        nxtbtn.setVisibility(View.INVISIBLE);
        finishbtn.setVisibility(View.INVISIBLE);
        val=(TextView)findViewById(R.id.val);
        ipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ip=Integer.parseInt(dataip.getText().toString(),16);
                ipbtn.setVisibility(View.INVISIBLE);
                dataip.setVisibility(View.INVISIBLE);
                val.setVisibility(View.INVISIBLE);
                data1=Integer.toHexString(ip).toUpperCase();
                addr.setText(data1);
                addr.setVisibility(View.VISIBLE);
                dataip1.setVisibility(View.VISIBLE);
                nxtbtn.setVisibility(View.VISIBLE);
                finishbtn.setVisibility(View.VISIBLE);
                data2= new String[10];

            }
        });
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=Integer.parseInt(dataip1.getText().toString(),16);
                ip++;
                data1=Integer.toHexString(ip);
                addr.setText(data1.toUpperCase());
                dataip1.setText("");
                data1=Integer.toHexString(k);
                data2[i++]=data1;

            }
        });
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(datain.this,newpage.class);
                /*Bundle b=new Bundle();
                b.putStringArray(key,data2);
                i.putExtras(b);*/
                i.putExtra("key",data2);
                datain.this.startActivity(i);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent i1=new Intent(datain.this,hexinput.class);
        datain.this.startActivity(i1);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datain, menu);
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
