package com.patrick.linar;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.baidu.ar.ARFragment;

import com.baidu.ar.constants.ARConfigKey;

import org.json.JSONException;
import org.json.JSONObject;

public class ARActivity extends FragmentActivity {

    private ARFragment linARfrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        if(findViewById(R.id.bdar_id_fragment_container) != null){
            FragmentManager fragmanager=getSupportFragmentManager();
            FragmentTransaction fragtrans=fragmanager.beginTransaction();
            Bundle data=new Bundle();
            JSONObject jsobj=new JSONObject();
            try{
                jsobj.put(ARConfigKey.AR_TYPE,0);
                jsobj.put(ARConfigKey.AR_KEY,"10302096");
            }catch (JSONException e){
                e.printStackTrace();
            }
            data.putString(ARConfigKey.AR_VALUE,jsobj.toString());
            if (linARfrag != null){
                linARfrag.release();
                linARfrag=null;
            }
            linARfrag=new ARFragment();
            linARfrag.setArguments(data);
            fragtrans.replace(R.id.bdar_id_fragment_container,linARfrag);
            fragtrans.commitAllowingStateLoss();
        }
    }
}
