package com.baidu.ardemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntroActivity extends Activity {
    private String mKey;
    private int mType;
    private String mTitle;
    private String mDescription;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mKey = bundle.getString("ar_key");
            mType = bundle.getInt("ar_type");
            mPath = bundle.getString("ar_path");
            mTitle = bundle.getString("name");
            mDescription = bundle.getString("description");
        }
        initView();
    }

    private void initView() {
        ((TextView) findViewById(R.id.intro_title)).setText(mTitle);
        ((TextView) findViewById(R.id.intro_detail)).setText(mDescription);
        findViewById(R.id.call_ar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, ARActivity.class);
                intent.putExtra("ar_key", mKey);
                intent.putExtra("ar_type", mType);
                intent.putExtra("ar_path", mPath);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

}