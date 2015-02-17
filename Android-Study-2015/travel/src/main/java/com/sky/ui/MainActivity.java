package com.sky.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.sky.ui.fragment.AddFragment;
import com.sky.ui.fragment.MeFragment;
import com.sky.ui.fragment.PlanFragment;
import com.sky.ui.fragment.RecordFragment;


public class MainActivity extends Activity implements View.OnClickListener {

    private final String TAG = "SKY";

    private final int RECORD_CHOICE = 0;
    private final int ADD_CHOICE = 1;
    private final int PLAN_CHOICE = 2;
    private final int ME_CHOICE = 3;

    private RecordFragment fmRecord;
    private AddFragment fmAdd;
    private PlanFragment fmPlan;
    private MeFragment fmMe;

    private Button btnRecord;
    private Button btnAdd;
    private Button btnPlan;
    private Button btnMe;

    private FragmentManager fManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setListeners();
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
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initUI() {

        initViews();

        setChoiceItem(PLAN_CHOICE);
    }


    private void initViews() {

        fmRecord = null;
        fmAdd = null;
        fmPlan = null;
        fmMe = null;

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnPlan = (Button) findViewById(R.id.btnPlan);
        btnMe = (Button) findViewById(R.id.btnMe);
    }


    private void setListeners() {

        btnRecord.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnPlan.setOnClickListener(this);
        btnMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRecord:
                setChoiceItem(RECORD_CHOICE);
                break;
            case R.id.btnAdd:
                setChoiceItem(ADD_CHOICE);
                break;
            case R.id.btnPlan:
                setChoiceItem(PLAN_CHOICE);
                break;
            case R.id.btnMe:
                setChoiceItem(ME_CHOICE);
                break;
            default:
                break;
        }
    }


    private void setChoiceItem(int choice) {

        fManager = getFragmentManager();
        FragmentTransaction fTrans = fManager.beginTransaction();

        hideItems(fTrans);

        switch (choice) {
            case RECORD_CHOICE:
                if (null == fmRecord) {
                    fmRecord = new RecordFragment();
                    fTrans.add(R.id.flMainContent, fmRecord);
                } else {
                    fTrans.show(fmRecord);
                }
                break;
            case ADD_CHOICE:
                if (null == fmAdd) {
                    fmAdd = new AddFragment();
                    fTrans.add(R.id.flMainContent, fmAdd);
                } else {
                    fTrans.show(fmAdd);
                }
                break;
            case PLAN_CHOICE:
                if (null == fmPlan) {
                    fmPlan = new PlanFragment();
                    fTrans.add(R.id.flMainContent, fmPlan);
                } else {
                    fTrans.show(fmPlan);
                }
                break;
            case ME_CHOICE:
                if (null == fmMe) {
                    fmMe = new MeFragment();
                    fTrans.add(R.id.flMainContent, fmMe);
                } else {
                    fTrans.show(fmMe);
                }
                break;
            default:
                break;
        }

        fTrans.commit();
    }


    private void hideItems(FragmentTransaction fTrans) {

        if (null != fmRecord) {
            fTrans.hide(fmRecord);
        }

        if (null != fmAdd) {
            fTrans.hide(fmAdd);
        }

        if (null != fmPlan) {
            fTrans.hide(fmPlan);
        }

        if (null != fmMe) {
            fTrans.hide(fmMe);
        }
    }
}
