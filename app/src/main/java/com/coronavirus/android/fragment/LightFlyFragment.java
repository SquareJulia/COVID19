package com.coronavirus.android.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.coronavirus.android.R;
import com.coronavirus.android.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LightFlyFragment extends Fragment {

    private ImageView lightToFly;
    private ObjectAnimator shiny;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fly_light_fragment,container,false);
        lightToFly=view.findViewById(R.id.flying_light);
        flyLight();
        initView();
        return view;
    }

    /**
     * 初始化视图，孔明灯闪烁
     */
    private void initView(){
        shiny= ObjectAnimator.ofFloat(lightToFly,"alpha",1f,0f,1f);
        shiny.setDuration(1200);
        shiny.setRepeatMode(ObjectAnimator.RESTART);
        shiny.setRepeatCount(ObjectAnimator.INFINITE);
        shiny.start();
    }

    /**
     * 给孔明灯图片添加点击事件
     */
    private void flyLight(){
        lightToFly.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                shiny.end();
                flyAnimator();
            }
        });
    }

    /**
     * 孔明灯飞简易动画
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void flyAnimator(){
        float startX=lightToFly.getX();
        float startY=lightToFly.getY();
        ObjectAnimator transX=ObjectAnimator.ofFloat(lightToFly,"X",startX,startX+50,startX-50,startX+50,startX-80,0);
        ObjectAnimator transY=ObjectAnimator.ofFloat(lightToFly,"Y",startY,0);

        ObjectAnimator scaleXAnimator=ObjectAnimator.ofFloat(lightToFly,"scaleX",1f,0.8f,0.5f);
        ObjectAnimator scaleYAnimator=ObjectAnimator.ofFloat(lightToFly,"scaleY",1f,0.8f,0.5f);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(transX).with(transY).with(shiny).with(scaleXAnimator).with(scaleYAnimator);
        animatorSet.setDuration(3000);
        animatorSet.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog();
            }
        },4000);
    }

    /**
     * 弹出完成提示框
     */
    private void dialog(){
        Log.d(TAG, "dialog: 执行dialog");
        final Activity thisActivity=this.getActivity();
        assert thisActivity != null;
        AlertDialog.Builder finishDialog=new AlertDialog.Builder(thisActivity);
        finishDialog.setTitle("提示");
        finishDialog.setMessage("感谢你的祝福！\n点击确定将返回程序界面。");
        finishDialog.setCancelable(false);
        finishDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent backToMain=new Intent(thisActivity, MainActivity.class);
                startActivity(backToMain);
                thisActivity.finish();
            }
        });
        finishDialog.show();
    }

}
