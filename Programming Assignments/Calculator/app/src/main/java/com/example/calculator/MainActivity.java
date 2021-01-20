package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class MainActivity extends AppCompatActivity {

    private EditText result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());

        result = (EditText) findViewById(R.id.result);
        TableLayout rootView = (TableLayout) findViewById(R.id.root_view);
        int rowCount = rootView.getChildCount();
        Logger.e(String.valueOf(rowCount));


    }
}
