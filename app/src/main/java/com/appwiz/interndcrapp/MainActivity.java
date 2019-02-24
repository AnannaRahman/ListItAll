package com.appwiz.interndcrapp;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appwiz.interndcrapp.Model.GiftList;
import com.appwiz.interndcrapp.Model.InternDCR;
import com.appwiz.interndcrapp.Model.LiteratureList;
import com.appwiz.interndcrapp.Model.PhysicianSampleList;
import com.appwiz.interndcrapp.Model.ProductGroupList;
import com.appwiz.interndcrapp.Utils.NetworkService;

import java.util.ArrayList;
import java.util.List;

import icepick.State;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @State
    String Giftname;
    private Toolbar toolbar;
    private TextView toolbartitle;
    private Adapter adapter;
    private Spinner spnGift;
    private Spinner spnPhysicianList;
    private Spinner spnLiteratureList;
    private Spinner spnProductGroup;
    private Button btnSubmit;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnGift = findViewById(R.id.spnGift);
        spnProductGroup = findViewById(R.id.spnProductGroup);
        spnPhysicianList = findViewById(R.id.spnPhysicianList);
        spnLiteratureList =  findViewById(R.id.spnLiteratureList);
        setSupportActionBar(toolbar);

        getInternDRC();
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("spnGift", spnGift.getSelectedItemPosition());
        outState.putInt("spnProductGroup", spnProductGroup.getSelectedItemPosition());
        outState.putInt("spnPhysicianList", spnPhysicianList.getSelectedItemPosition());
        outState.putInt("spnLiteratureList", spnLiteratureList.getSelectedItemPosition());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bundle = savedInstanceState;
    }

    private void getInternDRC() {

        NetworkService networkService = new NetworkService();
        networkService
                .GetInternDRC()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InternDCR>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InternDCR sample) {
                        Log.v("git", "");
                        loadProductGroupSpinner(sample);
                        loadGiftSpinner(sample);
                        loadPhysicianSpinner(sample);
                        loadLiteratureSpinner(sample);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //    mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadGiftSpinner(InternDCR sample) {
        List<GiftList> list = sample.getGiftList();
        List<String> item = new ArrayList<>();

        item.add("Choose");
        for (GiftList i : list) {
            item.add(i.getGift());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnGift.setAdapter(adapter);
        spnGift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(ContextCompat.getColor( MainActivity.this,R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (bundle != null) {
            //int spnGiftPosition = bundle.getInt("spnGift");
            spnGift.setSelection(bundle.getInt("spnGift", 0));


        }

    }

    private void loadPhysicianSpinner(InternDCR sample) {
        List<PhysicianSampleList> list = sample.getPhysicianSampleList();
        List<String> item = new ArrayList<>();
        item.add("Choose");
        for (PhysicianSampleList i : list
                ) {
            item.add(i.getSample());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnPhysicianList.setAdapter(adapter);
        spnPhysicianList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  ((TextView) view).setTextColor(ContextCompat.getColor( MainActivity.this,R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnPhysicianList.setSelection(bundle.getInt("spnPhysicianList", 0));
        }
    }

    private void loadLiteratureSpinner(InternDCR sample) {
        List<LiteratureList> list = sample.getLiteratureList();
        List<String> item = new ArrayList<>();
        item.add("Choose");
        for (LiteratureList i : list
                ) {
            item.add(i.getLiterature());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnLiteratureList.setAdapter(adapter);
        spnLiteratureList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(ContextCompat.getColor( MainActivity.this,R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnLiteratureList.setSelection(bundle.getInt("spnLiteratureList", 0));
        }
    }

    private void loadProductGroupSpinner(InternDCR sample) {
        List<ProductGroupList> list = sample.getProductGroupList();
        List<String> item = new ArrayList<>();
        item.add("Choose");
        for (ProductGroupList i : list
                ) {
            item.add(i.getProductGroup());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnProductGroup.setAdapter(adapter);
        spnProductGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(ContextCompat.getColor( MainActivity.this,R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnProductGroup.setSelection(bundle.getInt("spnProductGroup", 0));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() ) {
            case R.id.btnSubmit:
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
