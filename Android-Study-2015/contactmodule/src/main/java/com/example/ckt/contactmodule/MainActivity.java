package com.example.ckt.contactmodule;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

import static com.example.ckt.contactmodule.R.layout.activity_addcontacter;
import static com.example.ckt.contactmodule.R.layout.activity_contacter;


public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout mainLayout;// zhu caidan
    private LinearLayout contacterLayout; // lianxiren layout
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        findViews();

        btnAdd.setOnClickListener(this);
    }

    private void findViews() {

        LayoutInflater inflater = getLayoutInflater();

        mainLayout = (LinearLayout)inflater.inflate(R.layout.activity_main, null);
        contacterLayout = (LinearLayout)inflater.inflate(R.layout.activity_contacter, null);

        btnAdd = (Button) findViewById(R.id.btnAdd);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                ImageView img = (ImageView) contacterLayout.findViewById(R.id.imgContacter);
                TextView tvName = (TextView) contacterLayout.findViewById(R.id.tvNameContacter);
                TextView tvTel = (TextView) contacterLayout.findViewById(R.id.tvTelContacter);

                tvName.setText("abc");
                tvTel.setText("1234567");

                main.addView(contacterLayout);
                break;
        }
    }

    private void init() {

        ImageView img = (ImageView) contacterLayout.findViewById(R.id.imgContacter);
        TextView tvName = (TextView) contacterLayout.findViewById(R.id.tvNameContacter);
        TextView tvTel = (TextView) contacterLayout.findViewById(R.id.tvTelContacter);


        tvName.setText("abc");
        tvTel.setText("1234567");


        mainLayout.addView(contacterLayout);
    }
}
