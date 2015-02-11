package com.example.ckt.contactermodule;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ckt on 2/10/15.
 */
public class AddContacterActivity extends Activity implements View.OnClickListener {

    private Spinner spTel;
    private Spinner spAddress;
    private Spinner spDate;
    private Spinner spMail;

    private ImageButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addcontacter);

        findViews();

        initUI();

        btnOk.setOnClickListener(this);
    }

    private void findViews() {

        spTel = (Spinner) findViewById(R.id.spTel);
        spAddress = (Spinner) findViewById(R.id.spAddress);
        spDate = (Spinner) findViewById(R.id.spDate);
        spMail = (Spinner) findViewById(R.id.spMail);

        btnOk = (ImageButton) findViewById(R.id.btnOk);
    }

    private void initUI() {

        // telephone
        List<String> telList = new ArrayList<String>();
        telList.add("telphone");
        telList.add("at home");
        telList.add("at company");

        ArrayAdapter<String> telAdapter = new ArrayAdapter<String>(AddContacterActivity.this, android.R.layout.simple_spinner_item, telList);
        telAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTel.setAdapter(telAdapter);
        spTel.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // address
        String[] address = getResources().getStringArray(R.array.address);

        ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(AddContacterActivity.this, android.R.layout.simple_spinner_item, address);

        spAddress.setAdapter(addressAdapter);
        spAddress.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // date
        String [] date = getResources().getStringArray(R.array.date);
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,date);
        spDate.setAdapter(dateAdapter);


        // mail
        String [] mail = getResources().getStringArray(R.array.mail);

        ArrayAdapter<String> mailAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,mail);

        spMail.setAdapter(mailAdapter);

        spMail.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d("msgsky",String.valueOf(position));
                Log.d("msgsky",parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                break;
        }
    }
}