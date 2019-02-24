package com.appwiz.interndcrapp;

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
import com.appwiz.interndcrapp.Model.InternDRC;
import com.appwiz.interndcrapp.Model.LiteratureList;
import com.appwiz.interndcrapp.Model.PhysicianSampleList;
import com.appwiz.interndcrapp.Model.ProductGroupList;
import com.appwiz.interndcrapp.Utils.NetworkService;

import java.util.ArrayList;
import java.util.List;

import icepick.Icepick;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   Icepick.restoreInstanceState(this, savedInstanceState);
        setContentView(R.layout.activity_main);

            getInternDRC();


        toolbar = findViewById(R.id.toolBar);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnGift = (Spinner) findViewById(R.id.spnGift);
        spnProductGroup = (Spinner) findViewById(R.id.spnProductGroup);
        spnPhysicianList = (Spinner) findViewById(R.id.spnPhysicianList);
        spnLiteratureList = (Spinner) findViewById(R.id.spnLiteratureList);
        setSupportActionBar(toolbar);
//findViewById(R.id.spnGift);

        btnSubmit.setOnClickListener(this);
        // adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, );
        //spnGift.adapter = adapter;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("spnGiftPosition", spnGift.getSelectedItemPosition());

        // Icepick.saveInstanceState(this, outState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        int spnGiftPosition = savedInstanceState.getInt("spnGiftPosition");
    }
    private void getInternDRC() {

        NetworkService networkService = new NetworkService();
        networkService
                .GetInternDRC()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InternDRC>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InternDRC sample) {
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

    private void loadGiftSpinner(InternDRC sample) {
        List<GiftList> list = sample.getGiftList();
        List<String> gift = new ArrayList<>();
                      /*  for (int i=0; i<list.size();i++)
                        {
                            gift.add(list.get(i).getGift());
                        }
*/
        for (GiftList i : list
                ) {
            gift.add(i.getGift());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, gift);
        spnGift.setAdapter(adapter);
        spnGift.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Giftname = parent.getItemAtPosition(position).toString();
                //  Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               /* if (Giftname!=null)
                {

                }*/
            }
        });
    }

    private void loadPhysicianSpinner(InternDRC sample) {
        List<PhysicianSampleList> list = sample.getPhysicianSampleList();
        List<String> item = new ArrayList<>();

        for (PhysicianSampleList i : list
                ) {
            item.add(i.getSample());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnPhysicianList.setAdapter(adapter);
        spnPhysicianList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadLiteratureSpinner(InternDRC sample) {
        List<LiteratureList> list = sample.getLiteratureList();
        List<String> item = new ArrayList<>();

        for (LiteratureList i : list
                ) {
            item.add(i.getLiterature());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnLiteratureList.setAdapter(adapter);
        spnLiteratureList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //      Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadProductGroupSpinner(InternDRC sample) {
        List<ProductGroupList> list = sample.getProductGroupList();
        List<String> item = new ArrayList<>();

        for (ProductGroupList i : list
                ) {
            item.add(i.getProductGroup());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spnProductGroup.setAdapter(adapter);
        spnProductGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //        Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() /*to get clicked view id**/) {
            case R.id.btnSubmit:
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
