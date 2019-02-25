package com.appwiz.interndcrapp.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appwiz.interndcrapp.model.GiftList;
import com.appwiz.interndcrapp.model.data;
import com.appwiz.interndcrapp.model.LiteratureList;
import com.appwiz.interndcrapp.model.PhysicianSampleList;
import com.appwiz.interndcrapp.model.ProductGroupList;
import com.appwiz.interndcrapp.R;
import com.appwiz.interndcrapp.utils.NetworkService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Spinner spnGift;
    private Spinner spnPhysicianList;
    private Spinner spnLiteratureList;
    private Spinner spnProductGroup;
    private Button btnSubmit;
    private Bundle bundle;
    private EditText etGiftQuantity;
    private EditText etLiteratureLQuantity;
    private EditText etProductGroupQuantity;
    private EditText etPhysicianLQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        btnSubmit = findViewById(R.id.btnSubmit);
        spnGift = findViewById(R.id.spnGift);
        spnProductGroup = findViewById(R.id.spnProductGroup);
        spnPhysicianList = findViewById(R.id.spnPhysicianList);
        spnLiteratureList = findViewById(R.id.spnLiteratureList);
        etGiftQuantity = findViewById(R.id.etGiftQuantity);
        etLiteratureLQuantity = findViewById(R.id.etLiteratureListQuantity);
        etProductGroupQuantity = findViewById(R.id.etProductGroupQuantity);
        etPhysicianLQuantity = findViewById(R.id.etPhysicianLQuantity);
        setSupportActionBar(toolbar);

        getSampleData();
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("spnGift", spnGift.getSelectedItemPosition());
        outState.putInt("spnProductGroup", spnProductGroup.getSelectedItemPosition());
        outState.putInt("spnPhysicianList", spnPhysicianList.getSelectedItemPosition());
        outState.putInt("spnLiteratureList", spnLiteratureList.getSelectedItemPosition());
        outState.putString("etGiftQuantity", etGiftQuantity.getText().toString());
        outState.putString("etLiteratureLQuantity", etLiteratureLQuantity.getText().toString());
        outState.putString("etPhysicianLQuantity", etPhysicianLQuantity.getText().toString());
        outState.putString("etProductGroupQuantity", etProductGroupQuantity.getText().toString());

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bundle = savedInstanceState;
    }

    private void getSampleData() {

        NetworkService networkService = new NetworkService();
        networkService
                .GetSample()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<data>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(data sample) {
                        Log.v("git", "");
                        loadProductGroupSpinner(sample);
                        loadGiftSpinner(sample);
                        loadPhysicianSpinner(sample);
                        loadLiteratureSpinner(sample);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadGiftSpinner(data sample) {
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
                ((TextView) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (bundle != null) {
            //int spnGiftPosition = bundle.getInt("spnGift");
            spnGift.setSelection(bundle.getInt("spnGift", 0));
            etGiftQuantity.setText(bundle.getString("etGiftQuantity", "00"));


        }

    }

    private void loadPhysicianSpinner(data sample) {
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
                //   Toast.makeText(MainActivity.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                ((TextView) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnPhysicianList.setSelection(bundle.getInt("spnPhysicianList", 0));
            etPhysicianLQuantity.setText(bundle.getString("etPhysicianLQuantity", "00"));

        }
    }

    private void loadLiteratureSpinner(data sample) {
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
                ((TextView) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnLiteratureList.setSelection(bundle.getInt("spnLiteratureList", 0));
            etLiteratureLQuantity.setText(bundle.getString("etLiteratureLQuantity", "00"));

        }
    }

    private void loadProductGroupSpinner(data sample) {
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
                ((TextView) view).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (bundle != null) {
            spnProductGroup.setSelection(bundle.getInt("spnProductGroup", 0));
            etProductGroupQuantity.setText(bundle.getString("etProductGroupQuantity", "00"));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
