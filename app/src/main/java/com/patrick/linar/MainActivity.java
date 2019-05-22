package com.patrick.linar;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.patrick.linar.usercontent.PersonalInfoActivity;
import com.patrick.linar.usercontent.UserAgendaActivity;
import com.patrick.linar.usercontent.UserContactActivity;
import com.patrick.linar.usercontent.UserGalleryActivity;
import com.patrick.linar.usercontent.UserOptionActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton foxBtn;
    private DrawerLayout mainlayout;
    private LinearLayout lablecontainer;
    private String[] classlable={"为你推荐","历史人文","自然风光","购物天堂","科技之光","美食胜地","名人之乡","更多"};
    private Button lastbtn;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        //dialog
        /*
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setMessage("Tell me which langguage you prefer")
                .setPositiveButton("Chinese", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinArApp.currentLangguage=LinArApp.Langguage_ZN;
                        foxBtn.setImageResource(R.drawable.ai_panda_icon);

                    }
                })
                .setNegativeButton("Korean", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LinArApp.currentLangguage=LinArApp.Languuage_KR;
                        foxBtn.setImageResource(R.drawable.ai_tiger_icon);
                    }
                }).create();
        dialog.show();*/
        Glide.with(this).load(R.drawable.eyes).asGif().into(foxBtn);
        //floating button
        foxBtn.setOnClickListener(new ClickFoxListener(this));
        foxBtn.setOnLongClickListener(new LongClickFoxListener(this));
        //recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.newsAdapter);
    }
    private void initview(){
        //悬浮按钮
        this.foxBtn=findViewById(R.id.foxButton);
        //侧滑菜单键
        this.mainlayout=findViewById(R.id.mainlayout);
        Toolbar bar=findViewById(R.id.launchbar);
        setSupportActionBar(bar);
        ActionBar actionbar=getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.bar_avatar);
        }
        //分类滑动菜单
        this.lablecontainer=findViewById(R.id.classlayout);
        Button btn = new Button(this);
        btn.setText(this.classlable[0]);
        btn.setTextColor(Color.WHITE);
        btn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        this.lastbtn=btn;
        View.OnClickListener listener=new LableClickListener(this,this.lastbtn);
        btn.setOnClickListener(listener);
        this.lablecontainer.addView(btn);
        for (int i=1;i<this.classlable.length;i++){
            btn = new Button(this);
            btn.setText(this.classlable[i]);
            btn.setTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
            btn.setBackgroundColor(Color.WHITE);
            btn.setOnClickListener(listener);
            this.lablecontainer.addView(btn);
        }
        //内容显示
        NewsItem[] newsarray={new NewsItem("京义林荫道",R.drawable.road),new NewsItem("《太阳的后裔》公寓",R.drawable.spot),new NewsItem("漫画咖啡厅",R.drawable.compartment),
                                new NewsItem("Hello Indiebooks",R.drawable.bookstore),new NewsItem("东进市场",R.drawable.market)};
        List<NewsItem> newslist=new ArrayList<>();
        for (int i=0;i<newsarray.length;i++){
            newslist.add(newsarray[i]);
        }
        /*
        for (int i=0;i<10;i++) {
            Random random = new Random();
            int index = random.nextInt(newsarray.length);
            newslist.add(newsarray[index]);
        }*/
        this.recyclerView=findViewById(R.id.recycler);
        this.newsAdapter=new NewsAdapter(newslist);
        //侧滑菜单按键
        Button info=findViewById(R.id.user_info);
        final Context context=this;
        final Intent toPerson=new Intent(this, PersonalInfoActivity.class);
        Intent toAgenda=new Intent(this, UserAgendaActivity.class);
        Intent toContact=new Intent(this, UserContactActivity.class);
        Intent toGallery=new Intent(this, UserGalleryActivity.class);
        Intent toOption=new Intent(this, UserOptionActivity.class);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(toPerson);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.mainlayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
class LableClickListener implements View.OnClickListener{
    private Context context;
    private Button lastbtn;
    LableClickListener(Context context, Button lastbtn){
        super();
        this.context=context;
        this.lastbtn=lastbtn;
    }
    @Override
    public void onClick(View v) {
        if(v != this.lastbtn){
            this.lastbtn.setTextColor(ContextCompat.getColor(this.context,R.color.colorPrimary));
            this.lastbtn.setBackgroundColor(Color.WHITE);
            Button btn=(Button)v;
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(ContextCompat.getColor(this.context,R.color.colorPrimary));
            this.lastbtn=btn;
        }
    }
}
class ClickFoxListener implements View.OnClickListener{
    private AppCompatActivity appcontext;
    ClickFoxListener(AppCompatActivity context){
        super();
        this.appcontext=context;
    }
    @Override
    public void onClick(View v) {
        Intent toGuideActivity=new Intent(this.appcontext,GuideActivity.class);
        this.appcontext.startActivity(toGuideActivity);
    }
}
class LongClickFoxListener implements View.OnLongClickListener{
    private AppCompatActivity parentActivity;
    LongClickFoxListener(AppCompatActivity activity){
        super();
        this.parentActivity=activity;
    }
    @Override
    public boolean onLongClick(View v) {
        Intent toARactivity=new Intent(this.parentActivity,ARActivity.class);
        this.parentActivity.startActivity(toARactivity);
        return true;
    }
}