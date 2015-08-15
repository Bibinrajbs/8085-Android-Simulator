package andro.emu.bijith.emulator8085;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class test extends Activity implements AdapterView.OnItemSelectedListener
{
    Button ok,run,inbt,quit;
    Spinner sp1,sp2,sp3;
    EditText inmvi,adiin,lxin,jzin;
    TextView A1,B1,C1,D1,E1,H1,L1,PC1,Z1,CY1;
    TextView A2,B2,C2,D2,E2,H2,L2,PC2,Z2,CY2,err;
    String ar[] = new  String[]{"Select","ADD","ADI","ANA","ANI","CMA","CMC","HLT","INX","JC","JMP","JNC","JNZ","JZ",
            "LXI","MOV","MVI","ORA","ORI","RLC","RRC","SUB","SUI","XRA","XRI"};
    String add []= new String[]{"Select","A","B","C","D","E","H","L","M"};
    String mov []=new String[] {"Select","A","B","C","D","E","H","L","M"};
    String mvi []=new String[] {"Select","A","B","C","D","E","H","L","M"};
    String lxi []=new String[]{"Select","B","D","H"};
    String item,itemmov,itemadd,itemmvi;
    String mviItem,movItem1,movItem2,addItem1,subItem1,anaItem1,oraItem1,xraItem1,lxiItem1;
    String help="Enter";
    String data2[]=null;
    String a0,a1,a2,a3,a4,w1,w2,w;
    String a,b,c,d,e,h,l,pc,cy,zf;
    int i=0,tmp;
    int input,a_reg=0,b_reg=0,c_reg=0,d_reg=0,e_reg=0,h_reg=0,l_reg=0,pc_reg=0,z_flag=0,m_reg=0;
    int cy_flag=0;
    int stop=0;
    int j=0,go=0;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        pc_reg=getIntent().getIntExtra("pass",pc_reg);
        pc=Integer.toHexString(pc_reg);
        data2=getIntent().getStringArrayExtra("key1");
        sp1 = (Spinner)findViewById(R.id.sp1);
        sp2 = (Spinner)findViewById(R.id.sp2);
        sp3 = (Spinner)findViewById(R.id.sp3);
        ok = (Button)findViewById(R.id.ok);
        run = (Button)findViewById(R.id.run);
        quit = (Button)findViewById(R.id.quit);
        inmvi = (EditText)findViewById(R.id.inmvi);
        jzin=(EditText)findViewById(R.id.jzin);
        lxin=(EditText)findViewById(R.id.lxin);
        inbt = (Button)findViewById(R.id.inbt);
        A2=(TextView)findViewById(R.id.A2);
        B2=(TextView)findViewById(R.id.B2);
        C2=(TextView)findViewById(R.id.C2);
        D2=(TextView)findViewById(R.id.D2);
        E2=(TextView)findViewById(R.id.E2);
        H2=(TextView)findViewById(R.id.H2);
        L2=(TextView)findViewById(R.id.L2);
        PC2=(TextView)findViewById(R.id.PC2);
        A1=(TextView)findViewById(R.id.A1);
        B1=(TextView)findViewById(R.id.B1);
        C1=(TextView)findViewById(R.id.C1);
        D1=(TextView)findViewById(R.id.D1);
        E1=(TextView)findViewById(R.id.E1);
        H1=(TextView)findViewById(R.id.H1);
        L1=(TextView)findViewById(R.id.L1);
        PC1=(TextView)findViewById(R.id.PC1);
        Z1=(TextView)findViewById(R.id.Z1);
        CY1=(TextView)findViewById(R.id.CY1);
        adiin=(EditText)findViewById(R.id.adiin);
        adiin.setHint(help);
        Z2=(TextView)findViewById(R.id.Z2);
        CY2=(TextView)findViewById(R.id.CY2);
        err=(TextView)findViewById(R.id.err);


        ok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pc=Integer.toHexString(pc_reg);
                pc=String.format("%04x",(0xFFFF & pc_reg));
                PC2.setText(pc.toUpperCase());
                a=Integer.toHexString(a_reg);
                a=String.format("%02x",(0xFF & a_reg));
                A2.setText(a.toUpperCase());
                b=Integer.toHexString(b_reg);
                b=String.format("%02x",(0xFF & b_reg));
                B2.setText(b.toUpperCase());
                c=Integer.toHexString(c_reg);
                c=String.format("%02x",(0xFF & c_reg));
                C2.setText(c.toUpperCase());
                d=Integer.toHexString(d_reg);
                d=String.format("%02x",(0xFF & d_reg));
                D2.setText(d.toUpperCase());
                e=Integer.toHexString(e_reg);
                e=String.format("%02x",(0xFF & e_reg));
                E2.setText(e.toUpperCase());
                h=Integer.toHexString(h_reg);
                h=String.format("%02x",(0xFF & h_reg));
                H2.setText(h.toUpperCase());
                l=Integer.toHexString(l_reg);
                l=String.format("%02x",(0xFF & l_reg));
                L2.setText(l.toUpperCase());
                zf=Integer.toHexString(z_flag);
                zf=String.format("%02x",(0xFF & z_flag));
                Z2.setText(zf.toUpperCase());cy=Integer.toHexString(cy_flag);
                cy=String.format("%02x",(0xFF & cy_flag));
                CY2.setText(cy.toUpperCase());
                sp1.setSelection(0);
                inmvi.setText("");
                adiin.setText("");
                lxin.setText("");

            }
        });
        run.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sp1.setSelection(0);
                sp1.setVisibility(View.INVISIBLE);
                run.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.INVISIBLE);
                if(stop==0)
                {
                    err.setVisibility(View.VISIBLE);
                    PC2.setVisibility(View.INVISIBLE);
                    A2.setVisibility(View.INVISIBLE);
                    B2.setVisibility(View.INVISIBLE);
                    C2.setVisibility(View.INVISIBLE);
                    D2.setVisibility(View.INVISIBLE);
                    E2.setVisibility(View.INVISIBLE);
                    H2.setVisibility(View.INVISIBLE);
                    L2.setVisibility(View.INVISIBLE);
                    CY2.setVisibility(View.INVISIBLE);
                    Z2.setVisibility(View.INVISIBLE);
                    PC1.setVisibility(View.INVISIBLE);
                    A1.setVisibility(View.INVISIBLE);
                    B1.setVisibility(View.INVISIBLE);
                    C1.setVisibility(View.INVISIBLE);
                    D1.setVisibility(View.INVISIBLE);
                    E1.setVisibility(View.INVISIBLE);
                    H1.setVisibility(View.INVISIBLE);
                    L1.setVisibility(View.INVISIBLE);
                    CY1.setVisibility(View.INVISIBLE);
                    Z1.setVisibility(View.INVISIBLE);

                }
                quit.setVisibility(View.VISIBLE);
            }
        });
        quit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i;
                i= new Intent(test.this,scndpg.class);
                test.this.startActivity(i);
                finish();
            }
        });

            ArrayAdapter<String> ard = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, ar);
            sp1.setAdapter(ard);
            sp1.setOnItemSelectedListener(this);


//        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),ar1[position],3000).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }

    @Override
    public void onBackPressed()
    {
        Intent i;
        i= new Intent(test.this,newpage.class);
        startActivity(i);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(test.this,"trigger",Toast.LENGTH_LONG).show();

        int viewId = parent.getId();

        if(viewId==R.id.sp1)
        {
                item = ar[position];
            if("Select".equalsIgnoreCase(item))
            {
                pc=String.format("%04x",(0xFFFF & pc_reg));
                PC2.setText(pc.toUpperCase());
                sp2.setVisibility(View.INVISIBLE);
                sp3.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.INVISIBLE);
                inmvi.setVisibility(View.INVISIBLE);
                lxin.setVisibility(View.INVISIBLE);
                jzin.setVisibility(View.INVISIBLE);
                inbt.setVisibility(View.INVISIBLE);
                quit.setVisibility(View.INVISIBLE);
                adiin.setVisibility(View.INVISIBLE);
                err.setVisibility(View.INVISIBLE);
                return;
            }
            else
            {
                if ("ADD".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    itemadd=item;
                    ArrayAdapter<String> ard1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, add);
                    sp2.setAdapter(ard1);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }

                else if ("MOV".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    itemmov=item;
                    ArrayAdapter<String> ard2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,mov);
                    sp2.setAdapter(ard2);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();

                }
                else if("MVI".equals(item))
                {
                   sp2.setVisibility(View.VISIBLE);
                   itemmvi=item;
                   ArrayAdapter<String> ard3 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,mvi);
                   sp2.setAdapter(ard3);
                   sp2.setOnItemSelectedListener(test.this);
                   Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("SUB".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard4 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,add);
                    sp2.setAdapter(ard4);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("ADI".equals(item))
                {
                    adiin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            input=Integer.parseInt(adiin.getText().toString(),16);
                            if(j==0)
                            {
                                a_reg += input;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                if(a_reg>Integer.parseInt("FF",16)) {
                                    cy_flag = 1;
                                    a_reg &= Integer.parseInt("0ff", 16);
                                }
                                else
                                    cy_flag=0;
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("SUI".equals(item))
                {
                    adiin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            if (j==0)
                            {
                                input = Integer.parseInt(adiin.getText().toString(),16);
                                if (a_reg < input) {
                                    cy_flag = 1;
                                    a_reg &= Integer.parseInt("0ff", 16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= input;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("JMP".equals(item))
                {
                    jzin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(pc_reg==go)
                                j=0;
                            input=Integer.parseInt(jzin.getText().toString(),16);
                            if(j==0)
                            {
                                go=input;
                                j=1;
                            }
                            pc_reg+=3;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("JC".equals(item))
                {
                    jzin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(pc_reg==go)
                                j=0;
                            input=Integer.parseInt(jzin.getText().toString(),16);
                            if(j==0)
                            {
                                if(cy_flag==1)
                                {
                                    go = input;
                                    j = 1;
                                }
                            }
                            pc_reg+=3;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if ("JNC".equals(item))
                {
                    jzin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(pc_reg==go)
                                j=0;
                            input=Integer.parseInt(adiin.getText().toString(),16);
                            if(j==0)
                            {
                                if(cy_flag==0)
                                {
                                    go = input;
                                    j = 1;
                                }
                            }
                            pc_reg+=3;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("JZ".equals(item))
                {
                    jzin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(pc_reg==go)
                                j=0;
                            input=Integer.parseInt(jzin.getText().toString(),16);
                            if(j==0)
                            {
                                if(z_flag==1)
                                {
                                    go = input;
                                    j = 1;
                                }
                            }
                            pc_reg+=3;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("JNZ".equals(item))
                {
                    jzin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(pc_reg==go)
                                j=0;
                            input=Integer.parseInt(jzin.getText().toString(),16);
                            if(j==0)
                            {
                                if(z_flag==0)
                                {
                                    go = input;
                                    j = 1;
                                }
                            }
                            pc_reg+=3;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("ANA".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard5 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,add);
                    sp2.setAdapter(ard5);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("ANI".equals(item))
                {
                    adiin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            input=Integer.parseInt(adiin.getText().toString(),16);
                            if(j==0)
                            {
                                a_reg &= input;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("ORA".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard6 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,add);
                    sp2.setAdapter(ard6);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("ORI".equals(item))
                {
                    adiin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            input=Integer.parseInt(adiin.getText().toString(),16);
                            if(j==0)
                            {
                                a_reg |= input;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("XRA".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard7 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,add);
                    sp2.setAdapter(ard7);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("XRI".equals(item))
                {
                    adiin.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            input=Integer.parseInt(adiin.getText().toString(),16);
                            if(j==0)
                            {
                                a_reg ^= input;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
                else if("RLC".equals(item))
                {
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {

                        int temp=a_reg & 0x80;
                                a_reg=a_reg<<1;
                                temp=temp>>7;
                        a_reg+=temp;
                        a_reg &=0x0ff;
                        if (a_reg == 0)
                            z_flag = 1;
                        else
                            z_flag = 0;
                    }
                    pc_reg+=1;
                    ok.setVisibility(View.VISIBLE);
                }
                else if ("RRC".equals(item))
                {
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        int temp=a_reg & 0x01;
                        a_reg =a_reg>>1;
                        temp=temp<<7;
                        a_reg+=temp;
                        a_reg &=0x0ff;
                        if (a_reg == 0)
                            z_flag = 1;
                        else
                            z_flag = 0;
                    }
                    pc_reg+=1;
                    ok.setVisibility(View.VISIBLE);
                }
                else if("CMC".equals(item))
                {
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        cy_flag=~cy_flag;
                        cy_flag &=0x01;
                    }
                    pc_reg+=1;
                    ok.setVisibility(View.VISIBLE);
                }
                else if("CMA".equals(item))
                {

                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        a_reg=~a_reg;
                        a_reg &=0xff;
                    }
                    pc_reg+=1;
                    ok.setVisibility(View.VISIBLE);
                }
                else if("LXI".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard8 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,lxi);
                    sp2.setAdapter(ard8);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("INX".equals(item))
                {
                    sp2.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ard9 =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,lxi);
                    sp2.setAdapter(ard9);
                    sp2.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), ar[position], Toast.LENGTH_LONG).show();
                }
                else if("HLT".equals(item))
                {

                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        stop=1;
                    }
                    pc_reg+=1;
                    ok.setVisibility(View.VISIBLE);
                }
            }

        }
        else if(viewId == R.id.sp2)
        {

            if("MOV".equals(item))
            {
                movItem1 = mov[position];
                if ("A".equals(movItem1) || "B".equals(movItem1) || "C".equals(movItem1) ||
                        "D".equals(movItem1) || "E".equals(movItem1) || "H".equals(movItem1) || "L".equals(movItem1)||"M".equals(movItem1))
                {
                    Toast.makeText(getApplicationContext(),mov[position],Toast.LENGTH_LONG).show();
                    sp3.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> ardmov = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, mov);
                    sp3.setAdapter(ardmov);
                    sp3.setOnItemSelectedListener(test.this);
                    Toast.makeText(getApplicationContext(), mov[position], Toast.LENGTH_LONG).show();
                }
            }
            else if("ADD".equals(item))
            {
                addItem1 = add[position];
                if ("Select".equalsIgnoreCase(addItem1))
                {
                    return;
                }
                else
                {
                        Toast.makeText(getApplicationContext(), add[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                        if (j==0) {
                            switch (addItem1) {
                                case "A":
                                    a_reg += a_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "B":
                                    a_reg += b_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "C":
                                    a_reg += c_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "D":
                                    a_reg += d_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "E":
                                    a_reg += e_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "H":
                                    a_reg += h_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "L":
                                    a_reg += l_reg;
                                    if (a_reg == 0)
                                        z_flag = 1;
                                    else
                                        z_flag = 0;
                                    break;
                                case "M":
                                a_reg += m_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            }
                            if(a_reg>Integer.parseInt("FF",16))
                            {
                                cy_flag = 1;
                                a_reg &=Integer.parseInt("0ff",16);
                            }
                            else
                                cy_flag=0;
                        }
                        pc_reg += 1;
                        ok.setVisibility(View.VISIBLE);

                    }
            }
            else if("SUB".equals(item)) {
                subItem1 = add[position];
                if ("Select".equals(subItem1)) {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), add[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                    if(j==0) {
                        switch (subItem1) {
                            case "A":
                                a_reg -= a_reg;
                                z_flag = 1;
                                break;
                            case "B":
                                if (a_reg < b_reg)
                                {
                                    cy_flag = 1;
                                    a_reg &=Integer.parseInt("0ff",16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= b_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "C":
                                if (a_reg < c_reg)
                                {
                                    cy_flag = 1;
                                    a_reg &=Integer.parseInt("0ff",16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= c_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "D":
                                if (a_reg < d_reg) {
                                    cy_flag = 1;
                                    a_reg &=Integer.parseInt("0ff",16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= d_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "E":
                                if (a_reg < e_reg) {
                                    cy_flag = 1;
                                    a_reg &=Integer.parseInt("0ff",16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= e_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "H":
                                if (a_reg < h_reg) {
                                    cy_flag = 1;
                                    a_reg &=Integer.parseInt("0ff",16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= h_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "L":
                                if (a_reg < l_reg) {
                                    cy_flag = 1;
                                    a_reg &= Integer.parseInt("0ff", 16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= l_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                            case "M":
                                if (a_reg < m_reg) {
                                    cy_flag = 1;
                                    a_reg &= Integer.parseInt("0ff", 16);
                                }
                                else
                                    cy_flag = 0;
                                a_reg -= m_reg;
                                if (a_reg == 0)
                                    z_flag = 1;
                                else
                                    z_flag = 0;
                                break;
                        }

                    }
                    pc_reg += 1;
                    ok.setVisibility(View.VISIBLE);
                }
            }
            else if("MVI".equals(item))
            {
                mviItem = mvi[position];

                if ("A".equals(mviItem) || "B".equals(mviItem) || "C".equals(mviItem) ||
                        "D".equals(mviItem) || "E".equals(mviItem) || "H".equals(mviItem) || "L".equals(mviItem)||"M".equals(movItem1))
                {
                    Toast.makeText(getApplicationContext(),mov[position],Toast.LENGTH_LONG).show();

                    inmvi.setVisibility(View.VISIBLE);
                    inbt.setVisibility(View.VISIBLE);
                    inbt.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if (go==pc_reg)
                                j=0;
                            input=Integer.parseInt(inmvi.getText().toString(),16);
                            if (j==0) {
                                switch (mviItem) {
                                    case "A":
                                        a_reg = input;
                                        break;
                                    case "B":
                                        b_reg = input;
                                        break;
                                    case "C":
                                        c_reg = input;
                                        break;
                                    case "D":
                                        d_reg = input;
                                        break;
                                    case "E":
                                        e_reg = input;
                                        break;
                                    case "H":
                                        h_reg = input;
                                        break;
                                    case "L":
                                        l_reg = input;
                                        break;
                                    case "M":
                                        d_reg = input;
                                        break;
                                }
                            }
                            pc_reg+=2;
                            ok.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
            else if("ANA".equals(item))
            {
                anaItem1 = add[position];
                if ("Select".equals(subItem1))
                {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), add[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        switch (anaItem1)
                        {
                            case "A":
                                a_reg &= a_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "B":
                                a_reg &=b_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "C":
                                a_reg &=c_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "D":
                                a_reg &=d_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "E":
                                a_reg &=e_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "H":
                                a_reg &=h_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "L":
                                a_reg &=l_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "M":
                                a_reg &=m_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                        }
                    }
                    pc_reg += 1;
                    ok.setVisibility(View.VISIBLE);
                }
            }
            else if("ORA".equals(item))
            {
                oraItem1 = add[position];
                if ("Select".equals(subItem1))
                {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), add[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        switch (oraItem1)
                        {
                            case "A":
                                a_reg |= a_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "B":
                                a_reg |=b_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "C":
                                a_reg |=c_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "D":
                                a_reg |=d_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "E":
                                a_reg |=e_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "H":
                                a_reg |=h_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "L":
                                a_reg |=l_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "M":
                                a_reg |=m_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                        }
                    }
                    pc_reg += 1;
                    ok.setVisibility(View.VISIBLE);
                }
            }
            else if("INX".equals(item))
            {
                lxiItem1 = lxi[position];
                if ("Select".equals(subItem1))
                {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), lxi[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        switch (lxiItem1)
                        {
                            case "B":
                                c_reg++;
                                tmp=Integer.parseInt("FF",16);
                                if(c_reg>tmp)
                                {
                                    c_reg=0;
                                    b_reg++;
                                }
                                m_reg=Integer.parseInt(data2[++i],16);
                                break;
                            case "D":
                                d_reg++;
                                tmp=Integer.parseInt("FF",16);
                                if(d_reg>tmp)
                                {
                                    e_reg=0;
                                    d_reg++;
                                }
                                m_reg=Integer.parseInt(data2[++i],16);
                                break;
                            case "H":
                                l_reg++;
                                tmp=Integer.parseInt("FF",16);
                                if(l_reg>tmp)
                                {
                                    l_reg=0;
                                    h_reg++;
                                }
                                m_reg=Integer.parseInt(data2[++i],16);
                                break;
                        }
                    }
                    pc_reg += 1;
                    ok.setVisibility(View.VISIBLE);
                }
            }
            else if("XRA".equals(item))
            {
                xraItem1 = add[position];
                if ("Select".equals(subItem1))
                {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), add[position], Toast.LENGTH_LONG).show();
                    if (go==pc_reg)
                        j=0;
                    if(j==0)
                    {
                        switch (xraItem1)
                        {
                            case "A":
                                a_reg ^= a_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "B":
                                a_reg ^=b_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "C":
                                a_reg ^=c_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "D":
                                a_reg ^=d_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "E":
                                a_reg ^=e_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "H":
                                a_reg ^=h_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "L":
                                a_reg ^=l_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                            case "M":
                                a_reg ^=m_reg;
                                if(a_reg==0)
                                    z_flag=1;
                                else
                                    z_flag=0;
                                break;
                        }
                    }
                    pc_reg += 1;
                    ok.setVisibility(View.VISIBLE);
                }
            }
            else if("LXI".equals(item))
            {
                lxiItem1 = lxi[position];
                if ("Select".equals(subItem1))
                {
                    return;
                }
                else
                {

                    Toast.makeText(getApplicationContext(), lxi[position], Toast.LENGTH_LONG).show();

                        switch (lxiItem1) {
                            case "B":
                                lxin.setVisibility(View.VISIBLE);
                                inbt.setVisibility(View.VISIBLE);
                                inbt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        if (go == pc_reg)
                                            j = 0;
                                        input = Integer.parseInt(lxin.getText().toString(), 16);
                                        if (j == 0)
                                        {
                                            a0=Integer.toHexString(input);
                                            a1=String.valueOf(a0.charAt(0));
                                            a2=String.valueOf(a0.charAt(1));
                                            a3=String.valueOf(a0.charAt(2));
                                            a4=String.valueOf(a0.charAt(3));
                                            w1=a1+a2;
                                            w2=a3+a4;
                                            w=w1+w2;
                                            b_reg=Integer.parseInt(w1,16);
                                            c_reg=Integer.parseInt(w2,16);
                                            m_reg=Integer.parseInt(data2[i],16);

                                        }
                                        pc_reg += 3;
                                        ok.setVisibility(View.VISIBLE);
                                    }

                                });
                                break;
                            case "D":
                                lxin.setVisibility(View.VISIBLE);
                                inbt.setVisibility(View.VISIBLE);
                                inbt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        if (go == pc_reg)
                                            j = 0;
                                        input = Integer.parseInt(lxin.getText().toString(), 16);
                                        if (j == 0)
                                        {
                                            a0=Integer.toHexString(input);
                                            a1=String.valueOf(a0.charAt(0));
                                            a2=String.valueOf(a0.charAt(1));
                                            a3=String.valueOf(a0.charAt(2));
                                            a4=String.valueOf(a0.charAt(3));
                                            w1=a1+a2;
                                            w2=a3+a4;
                                            w=w1+w2;
                                            d_reg=Integer.parseInt(w1,16);
                                            e_reg=Integer.parseInt(w2,16);
                                            m_reg=Integer.parseInt(data2[i],16);

                                        }
                                        pc_reg += 3;
                                        ok.setVisibility(View.VISIBLE);
                                    }

                                });

                                break;
                            case "H":
                                lxin.setVisibility(View.VISIBLE);
                                inbt.setVisibility(View.VISIBLE);
                                inbt.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        if (go == pc_reg)
                                            j = 0;
                                        input = Integer.parseInt(lxin.getText().toString(), 16);
                                        if (j == 0)
                                        {
                                             a0=Integer.toHexString(input);
                                             a1=String.valueOf(a0.charAt(0));
                                             a2=String.valueOf(a0.charAt(1));
                                             a3=String.valueOf(a0.charAt(2));
                                             a4=String.valueOf(a0.charAt(3));
                                             w1=a1+a2;
                                             w2=a3+a4;
                                             w=w1+w2;
                                            h_reg=Integer.parseInt(w1,16);
                                            l_reg=Integer.parseInt(w2,16);
                                            m_reg=Integer.parseInt(data2[i],16);

                                        }
                                        pc_reg += 3;
                                        ok.setVisibility(View.VISIBLE);
                                    }

                                });
                            break;
                        }
                    }

                }
            }
        else if(viewId==R.id.sp3)
        {
            if("MOV".equals(item))
                {
                    movItem2=mov[position];
                    if("Select".equals(movItem2))
                    {
                        return;
                    }
                    else
                    {
                        if (go==pc_reg)
                            j=0;
                        if(j==0) {
                            if ("A".equals(movItem1))
                            {
                                switch (movItem2) {
                                    case "A":
                                        break;  case "B":
                                        a_reg = b_reg;
                                        break;
                                    case "C":
                                        a_reg = c_reg;
                                        break;
                                    case "D":
                                        a_reg = d_reg;
                                        break;
                                    case "E":
                                        a_reg = e_reg;
                                        break;
                                    case "H":
                                        a_reg = h_reg;
                                        break;
                                    case "L":
                                        a_reg = l_reg;
                                        break;
                                    case "M":
                                        a_reg=m_reg;
                                        break;
                                }
                                //A2.setText(a_reg+"");
                            } else if ("B".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        b_reg = a_reg;
                                        break;
                                    case "C":
                                        b_reg = c_reg;
                                        break;
                                    case "D":
                                        b_reg = d_reg;
                                        break;
                                    case "E":
                                        b_reg = e_reg;
                                        break;
                                    case "H":
                                        b_reg = h_reg;
                                        break;
                                    case "L":
                                        b_reg = l_reg;
                                        break;
                                    case "M":
                                        b_reg=m_reg;
                                        break;
                                }
                                // B2.setText(b_reg+"");
                            } else if ("C".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        c_reg = a_reg;
                                        break;
                                    case "B":
                                        c_reg = b_reg;
                                        break;
                                    case "D":
                                        c_reg = d_reg;
                                        break;
                                    case "E":
                                        c_reg = e_reg;
                                        break;
                                    case "H":
                                        c_reg = h_reg;
                                        break;
                                    case "L":
                                        c_reg = l_reg;
                                        break;
                                    case "M":
                                        c_reg=m_reg;
                                        break;
                                }
                                //C2.setText(c_reg+"");
                            } else if ("D".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        d_reg = a_reg;
                                        break;
                                    case "B":
                                        d_reg = b_reg;
                                        break;
                                    case "C":
                                        d_reg = c_reg;
                                        break;
                                    case "E":
                                        d_reg = e_reg;
                                        break;
                                    case "H":
                                        d_reg = h_reg;
                                        break;
                                    case "L":
                                        d_reg = l_reg;
                                        break;
                                    case "M":
                                        d_reg=m_reg;
                                        break;
                                }
                                //D2.setText(d_reg+"");
                            } else if ("E".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        e_reg = a_reg;
                                        break;
                                    case "B":
                                        e_reg = b_reg;
                                        break;
                                    case "C":
                                        e_reg = c_reg;
                                        break;
                                    case "D":
                                        e_reg = d_reg;
                                        break;
                                    case "H":
                                        e_reg = h_reg;
                                        break;
                                    case "L":
                                        e_reg = l_reg;
                                        break;
                                    case "M":
                                        e_reg=m_reg;
                                        break;
                                }
                                //E2.setText(e_reg+"");
                            } else if ("H".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        h_reg = a_reg;
                                        break;
                                    case "B":
                                        h_reg = b_reg;
                                        break;
                                    case "C":
                                        h_reg = c_reg;
                                        break;
                                    case "D":
                                        h_reg = d_reg;
                                        break;
                                    case "E":
                                        h_reg = e_reg;
                                        break;
                                    case "L":
                                        h_reg = l_reg;
                                        break;
                                    case "M":
                                        h_reg=m_reg;
                                        break;
                                }
                                //H2.setText(h_reg+"");
                            } else if ("L".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        l_reg = a_reg;
                                        break;
                                    case "B":
                                        l_reg = b_reg;
                                        break;
                                    case "C":
                                        l_reg = c_reg;
                                        break;
                                    case "D":
                                        l_reg = d_reg;
                                        break;
                                    case "E":
                                        l_reg = e_reg;
                                        break;
                                    case "H":
                                        l_reg = h_reg;
                                        break;
                                    case "M":
                                        l_reg=m_reg;
                                        break;
                                }
                            }
                            else if ("M".equals(movItem1)) {
                                switch (movItem2) {
                                    case "A":
                                        m_reg = a_reg;
                                        break;
                                    case "B":
                                        m_reg = b_reg;
                                        break;
                                    case "C":
                                        m_reg = c_reg;
                                        break;
                                    case "D":
                                        l_reg = d_reg;
                                        break;
                                    case "E":
                                        m_reg = e_reg;
                                        break;
                                    case "H":
                                        m_reg = h_reg;
                                        break;
                                    case "L":
                                        m_reg=l_reg;
                                        break;
                                    case "M":
                                        break;
                                }
                            }
                        }
                        pc_reg+=1;
                        ok.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
