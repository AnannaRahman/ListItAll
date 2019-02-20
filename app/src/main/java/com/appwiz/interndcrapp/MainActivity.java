package com.appwiz.interndcrapp;

import android.app.AlertDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appwiz.interndcrapp.Model.InternDRC;
import com.appwiz.interndcrapp.Utils.NetworkService;

import java.lang.reflect.Array;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbartitle;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBar);
//findViewById(R.id.spinner1);

        setSupportActionBar(toolbar);
      //  getInternDRC();
        // adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, );
        //spinner1.adapter = adapter;
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
                    public void onNext(InternDRC headLine) {
                        Log.v("git", "");
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(MainActivity.this);
                        }


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

}
