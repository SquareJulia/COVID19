package com.coronavirus.android.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.coronavirus.android.R;
import com.coronavirus.android.fragment.LightFlyFragment;

import java.util.Calendar;

/**
 * 孔明灯祈福的activity
 * @author zjy
 */
public class LightMainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_main);

        initView();
        light.setOnClickListener(this);

    }

    /**
     * 初始化视图以及孔明灯闪烁动画
     * TODO
     */
    @SuppressLint("SetTextI18n")
    private void initView(){
        light=findViewById(R.id.finish_button);

        TextView dateText=findViewById(R.id.date_text);
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        dateText.setText(year+"/"+month+"/"+day);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(light,"alpha",1f,0f,1f);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.finish_button){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            transaction.replace(R.id.layout_before,new LightFlyFragment());
            transaction.commit();
        }
    }
}
