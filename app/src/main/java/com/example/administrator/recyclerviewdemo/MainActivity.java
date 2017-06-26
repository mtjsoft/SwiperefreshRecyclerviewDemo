package com.example.administrator.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class MainActivity extends AppCompatActivity {

    private TextView activityTextView;
    private TextView fragmentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityTextView = (TextView) this.findViewById(R.id.tv_activity);
        fragmentTextView = (TextView) this.findViewById(R.id.tv_fragment);
        initListeners();
    }

    public void initListeners() {

        activityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivityDemo.class);
                startActivity(intent);
            }
        });

        fragmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fl_add_fragment, new ListFragmentDemo());
                ft.commit();
            }
        });
    }
}
