package com.tp.animationcomplex;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tp.animationcomplex.R;

public class MainActivity extends Activity
{
    private AlertDialog redPacketDialog, redPacketOpenDialog;
    private Button animationBtn1, animationBtn2, animationBtn3;
    private RelativeLayout giftSender1Layout, giftSender2Layout, carLayout, mainRealtiveLayout;
    private Animation layout1Animation1, layout1Animation2, layout1Animation3, layout1alphaAnimation;
    private Animation layout2Animation1, layout2Animation2, layout2Animation3, layout2alphaAnimation;
    private int userNumber1 = 0, userNumber2 = 0;
    private boolean isCombo1 = false, isCombo2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnListener();
    }
    private void init()
    {
        animationBtn1 = (Button) findViewById(R.id.animation1);
        animationBtn2 = (Button) findViewById(R.id.animation2);
        animationBtn3 = (Button) findViewById(R.id.animation3);
        giftSender1Layout = (RelativeLayout) findViewById(R.id.giftSender1Info);
        giftSender2Layout = (RelativeLayout) findViewById(R.id.giftSender2Info);
        carLayout = (RelativeLayout) findViewById(R.id.car_layout);
        mainRealtiveLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        layout1Animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_in);
        layout1Animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_in);
        layout1Animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
        layout1alphaAnimation = new AlphaAnimation(1,0);
        layout1alphaAnimation.setDuration(1000);
        layout1alphaAnimation.setStartOffset(3000);

        layout2Animation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_in);
        layout2Animation2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slide_left_in);
        layout2Animation3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_out);
        layout2alphaAnimation = new AlphaAnimation(1,0);
        layout2alphaAnimation.setDuration(1000);
        layout2alphaAnimation.setStartOffset(3000);


        // giftNumber.setText("X1");
    }

    private void setOnListener()
    {
        animationBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                giftSender1Layout.setVisibility(View.VISIBLE);
                giftSender1Layout.findViewById(R.id.gift).setVisibility(View.GONE);
                giftSender1Layout.findViewById(R.id.giftNumber).setVisibility(View.GONE);
                if (isCombo1 == true) {
                    giftSender1Layout.findViewById(R.id.giftNumber).setVisibility(View.VISIBLE);
                    giftSender1Layout.findViewById(R.id.gift).setVisibility(View.VISIBLE);
                    giftSender1Layout.findViewById(R.id.giftNumber).startAnimation(layout1Animation3);
                }
                else
                {
                    giftSender1Layout.startAnimation(layout1Animation1);
                    layout1Animation1.setAnimationListener(new TranslateAnimationListener1(giftSender1Layout, true));
                    layout1Animation2.setAnimationListener(new TranslateAnimationListener2(giftSender1Layout, true));
                    layout1Animation3.setAnimationListener(new ZoomOutAnimationListener(giftSender1Layout, true));
                    layout1alphaAnimation.setAnimationListener(new AlphaAnimationListener(giftSender1Layout, true));
                }
            }
        });
        animationBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giftSender2Layout.setVisibility(View.VISIBLE);
                giftSender2Layout.findViewById(R.id.gift).setVisibility(View.GONE);
                giftSender2Layout.findViewById(R.id.giftNumber).setVisibility(View.GONE);
                if (isCombo2 == true) {
                    giftSender2Layout.findViewById(R.id.giftNumber).setVisibility(View.VISIBLE);
                    giftSender2Layout.findViewById(R.id.gift).setVisibility(View.VISIBLE);
                    giftSender2Layout.findViewById(R.id.giftNumber).startAnimation(layout2Animation3);
                }
                else
                {
                    giftSender2Layout.startAnimation(layout2Animation1);
                    layout2Animation1.setAnimationListener(new TranslateAnimationListener1(giftSender2Layout, false));
                    layout2Animation2.setAnimationListener(new TranslateAnimationListener2(giftSender2Layout, false));
                    layout2Animation3.setAnimationListener(new ZoomOutAnimationListener(giftSender2Layout, false));
                    layout2alphaAnimation.setAnimationListener(new AlphaAnimationListener(giftSender2Layout, false));
                }
            }
        });
        animationBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRedPacketDialog();
            }
        });
    }
    private void showRedPacketDialog()
    {
        redPacketDialog = new AlertDialog.Builder(MainActivity.this).create();
        redPacketDialog.setCanceledOnTouchOutside(true);
        redPacketDialog.show();
        redPacketDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        redPacketDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        final Window window = redPacketDialog.getWindow();
        window.setContentView(R.layout.dialog_room_redpacket);
        setOnPacketListener(window);
    }
    private void showRedPacketOpenDialog()
    {
        redPacketOpenDialog = new AlertDialog.Builder(MainActivity.this).create();
        redPacketOpenDialog.setCanceledOnTouchOutside(true);
        redPacketOpenDialog.show();
        redPacketOpenDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        redPacketOpenDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        final Window window = redPacketOpenDialog.getWindow();
        window.setContentView(R.layout.dialog_room_redpacket_open);
        setOnPacketOpenListener(window);
    }
    private void setOnPacketListener(Window window)
    {
        final ProgressBar openProgress = (ProgressBar) window.findViewById(R.id.progress_loading);
        final ImageView openPacket = (ImageView) window.findViewById(R.id.img_open);
        final ImageView closeImage = (ImageView) window.findViewById(R.id.img_close);
        openPacket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProgress.setVisibility(View.VISIBLE);


                //redPacketDialog.cancel();
                //showRedPacketOpenDialog();

            }
        });
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRedPacketOpenDialog();

            }
        });
    }
    private void setOnPacketOpenListener(Window window)
    {
        final ImageView closDialog = (ImageView) window.findViewById(R.id.img_close);
        closDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redPacketOpenDialog.cancel();
            }
        });
    }
    class TranslateAnimationListener1 implements Animation.AnimationListener{
        private RelativeLayout layout;
        private ImageView giftImageView;
        private boolean flag;
        public TranslateAnimationListener1(RelativeLayout relativeLayout, boolean isLayout1)
        {
            this.layout = relativeLayout;
            this.giftImageView = (ImageView) relativeLayout.findViewById(R.id.gift);
            this.flag = isLayout1;
        }
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.giftImageView.setVisibility(View.VISIBLE);
            if (flag) {
                isCombo1 = true;
                this.giftImageView.startAnimation(layout1Animation2);
            }
            else {
                isCombo2 = true;
                this.giftImageView.startAnimation(layout2Animation2);

            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    class TranslateAnimationListener2 implements Animation.AnimationListener{
        private RelativeLayout layout;
        private boolean flag;
        private TextView giftNumber;
        public TranslateAnimationListener2(RelativeLayout relativeLayout, boolean isLayout1)
        {
            this.layout = relativeLayout;
            this.flag = isLayout1;
            this.giftNumber = (TextView) relativeLayout.findViewById(R.id.giftNumber);
        }
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.giftNumber.setVisibility(View.VISIBLE);
            if (flag)
                this.giftNumber.startAnimation(layout1Animation3);
            else
                this.giftNumber.startAnimation(layout2Animation3);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    class ZoomOutAnimationListener implements Animation.AnimationListener{
        private RelativeLayout layout;
        private boolean flag;
        private TextView giftNumber;
        public ZoomOutAnimationListener(RelativeLayout relativeLayout, boolean isLayout1)
        {
            this.layout = relativeLayout;
            this.flag = isLayout1;
            this.giftNumber = (TextView) relativeLayout.findViewById(R.id.giftNumber);
        }
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (flag)
            {
                userNumber1++;
                this.giftNumber.setText("X" + String.valueOf(userNumber1));
                this.layout.startAnimation(layout1alphaAnimation);
            }

            else
            {
                userNumber2++;
                this.giftNumber.setText("X" + String.valueOf(userNumber2));
                this.layout.startAnimation(layout2alphaAnimation);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    class AlphaAnimationListener implements Animation.AnimationListener{
        private RelativeLayout layout;
        private ImageView giftImageView;
        private TextView giftNumber;
        boolean flag;
        public AlphaAnimationListener(RelativeLayout relativeLayout, boolean isLayout1)
        {
            this.layout = relativeLayout;
            this.giftImageView = (ImageView) relativeLayout.findViewById(R.id.gift);
            this.flag = isLayout1;
        }
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.layout.setVisibility(View.INVISIBLE);
            if (flag)
                isCombo1 = false;
            else
                isCombo2 = false;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


}
