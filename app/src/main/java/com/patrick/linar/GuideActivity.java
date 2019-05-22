package com.patrick.linar;

import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    /*
    private ImageView ai1;
    private ImageView ai2;
    private ImageView ai3;
    private ImageView ai4;
    private ImageView msgimage1;
    private ImageView msgimage2;*/
    private TextView msgtext1;
    private TextView msgtext2;
    private TextView msgtext3;
    private TextView msgtext4;
    private TextView msgtext5;
    private TextView msgtext6;
    private ImageView map_img;
    private List<ImageView> imagelist=new ArrayList<>();
    private List<View> msglist=new ArrayList<>();
    private EditText edit;
    private Thread inturn;
    private ImageButton mic;
    private ImageView record;
    private ConstraintLayout maplayout;
    private ImageButton closemap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initview();
        /*
        final Handler aivHandler=new Handler(){
            public void handleMessage(Message msg){
                imagelist.get(msg.what).setVisibility(View.VISIBLE);
                if (msg.what-1<0){
                    imagelist.get(3).setVisibility(View.INVISIBLE);
                }else{
                    imagelist.get(msg.what-1).setVisibility(View.INVISIBLE);
                }
            }
        };*/
        /*
        final Handler msgHandler1=new Handler(){
            public void handleMessage(Message msg){
                msglist.get(msg.what).setVisibility(View.VISIBLE);
                msglist.get(msg.what+1).setVisibility(View.VISIBLE);
            }
        };

        final Handler msgHandler2=new Handler(){
            public void handleMessage(Message msg){
                msglist.get(msg.what).setVisibility(View.VISIBLE);
                msglist.get(msg.what+1).setVisibility(View.VISIBLE);
            }
        };*/
        final Handler msgHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what<6){
                    msglist.get(msg.what).setVisibility(View.VISIBLE);
                }else{
                    ViewGroup.LayoutParams params=maplayout.getLayoutParams();
                    params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
                    maplayout.setLayoutParams(params);
                    //map_img.setImageResource(R.drawable.map_with_icon);
                }
            }
        };
        /*
        inturn=new  Thread(new Runnable() {
            @Override
            public void run() {
                int nextin=0;
                while (true){
                    try{
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    nextin++;
                    if (nextin==4){
                        nextin=0;
                    }
                    Message msg=new Message();
                    msg.what=nextin;
                    aivHandler.sendMessage(msg);
                }
            }
        });
        inturn.start();*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    Message msg = new Message();
                    try {
                        Thread.sleep(1500);
                        msg.what = i;
                        msgHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        final Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 2; i < 7; i++) {
                    Message msg = new Message();
                    try {
                        if (i==4){
                            Thread.sleep(2500);
                        }
                        else {
                            Thread.sleep(1500);
                        }
                        msg.what = i;
                        msgHandler.sendMessage(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record.setImageResource(R.drawable.bdar_drawable_scan_center);
                ViewGroup.LayoutParams params=record.getLayoutParams();
                params.height=ViewGroup.LayoutParams.WRAP_CONTENT;
                record.setLayoutParams(params);
                t1.start();
            }
        });
    }
    private void initview(){
        /*
        ai1=findViewById(R.id.aiimageView1);
        imagelist.add(ai1);
        ai2=findViewById(R.id.aiimageView2);
        imagelist.add(ai2);
        ai3=findViewById(R.id.aiimageView3);
        imagelist.add(ai3);
        ai4=findViewById(R.id.aiimageView4);
        imagelist.add(ai4);*/
        /*
        msgimage1=findViewById(R.id.msgimageView1);
        msglist.add(msgimage1);*/
        ImageView imgv=findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.snap).asGif().into(imgv);
        msgtext1=findViewById(R.id.msgtextView1);
        msglist.add(msgtext1);
        /*
        msgimage2=findViewById(R.id.msgimageView2);
        msglist.add(msgimage2);*/
        msgtext2=findViewById(R.id.msgtextView2);
        msglist.add(msgtext2);
        msgtext5=findViewById(R.id.msgtextView5);
        msglist.add(msgtext5);
        msgtext3=findViewById(R.id.msgtextView3);
        msglist.add(msgtext3);
        msgtext4=findViewById(R.id.msgtextView4);
        msglist.add(msgtext4);
        msgtext6=findViewById(R.id.msgTextView6);
        msglist.add(msgtext6);
        map_img=findViewById(R.id.mapimg);
        edit=findViewById(R.id.editText);
        mic=findViewById(R.id.micimageView);
        record=findViewById(R.id.recordimg);
        maplayout=findViewById(R.id.maplayout);
        closemap=findViewById(R.id.closemap);
        closemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params=maplayout.getLayoutParams();
                params.height=0;
                maplayout.setLayoutParams(params);
            }
        });
        if(LinArApp.currentLangguage==LinArApp.Languuage_KR){
            /*
            ai1.setImageResource(R.drawable.ai_tiger1);
            ai2.setImageResource(R.drawable.ai_tiger2);
            ai3.setImageResource(R.drawable.ai_tiger3);
            ai4.setImageResource(R.drawable.ai_tiger4);
            msgimage1.setImageResource(R.drawable.ai_tiger_icon);
            msgimage2.setImageResource(R.drawable.ai_tiger_icon);
            */
            msgtext1.setText(R.string.ai_hello_kr);
            msgtext2.setText(R.string.ai_ask_kr);
            edit.setHint(R.string.ai_input_hint_kr);
        }
    }
}
