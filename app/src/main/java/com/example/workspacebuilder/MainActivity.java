package com.example.workspacebuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.SystemCleaner;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton exit;
    ImageButton imgB2;
    ImageButton imgB3;
    Guideline guideline;

    ConstraintLayout layout;

    Context context = this;

    RelativeLayout canvas_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        exit = findViewById(R.id.imgB1);
        ClickListener listener = new ClickListener(exit);


        exit.setOnClickListener(listener);
        imgB2 = findViewById(R.id.imgB2);
        imgB2.setOnClickListener(listener);
        imgB3 = findViewById(R.id.imgB3);
        imgB3.setOnClickListener(listener);
        guideline = findViewById(R.id.guideline);
        layout = findViewById(R.id.layout);

        canvas_layout = findViewById(R.id.canvas);
    }

    private class ClickListener implements View.OnClickListener{
        private boolean b2_on = false;
        private boolean b3_on = false;

        View last;

        public ClickListener(View last){
            this.last = last;
        }

        @Override
        public void onClick(View view) {
            if(view == exit) {
                //SystemClock.sleep(10);
                System.exit(0);
            } else if (view == imgB2) {
                if(!b2_on) imgB2.setImageResource(R.drawable.but_on);
                else imgB2.setImageResource(R.drawable.but_off);
                b2_on = !b2_on;
                try{
                    addButton();
                }catch (Exception e){
                    e.printStackTrace();
                }

            } else if (view == imgB3) {
                if(!b3_on) imgB3.setImageResource(R.drawable.but_on);
                else imgB3.setImageResource(R.drawable.but_off);
                b3_on = !b3_on;
                //moveAnimation();
                last.setX(last.getX() + 200);
            }
        }

        private void moveAnimation(){
            Animation img = new TranslateAnimation(Animation.ABSOLUTE, 200, Animation.ABSOLUTE, Animation.ABSOLUTE);
            img.setDuration(2000);
            img.setFillAfter(true);
            imgB3.startAnimation(img);
        }

        private void addButton(){
            ImageButton b = new ImageButton(context);
            b.setImageResource(R.drawable.but_on);
            b.layout(0,0,0,0);
            b.setY(last.getY()+100);
            b.setId((int)System.currentTimeMillis());
            last = b;
            layout.addView(b);
        }
    }
}