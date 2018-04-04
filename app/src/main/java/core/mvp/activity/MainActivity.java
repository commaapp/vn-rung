package core.mvp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import core.config.Config;
import demo.eco.com.eco_vibrate_massage.R;
import es.dmoral.prefs.Prefs;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.imageView)
    ImageView imageHeart;
    @BindView(R.id.txtState)
    TextView txtState;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    AppEventsLogger appEventsLogger;
    @BindView(R.id.title_app)
    TextView titleApp;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_setting)
    ImageView imgSetting;
    @BindView(R.id.im_store)
    ImageView imStore;
    @BindView(R.id.button6)
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        ButterKnife.bind(this);

        appEventsLogger = AppEventsLogger.newLogger(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "font_button.otf");
        txtState.setTypeface(font);
        titleApp.setTypeface(Typeface.createFromAsset(getAssets(), "font_tenapp.ttf"));
        button1.setTypeface(font);
        button2.setTypeface(font);
        button3.setTypeface(font);
        button4.setTypeface(font);
        button5.setTypeface(font);
//        logger.logEvent("Button_start_click");

//        getSupportActionBar().hide();
//        Config.chayRung(getBaseContext(), 1000, 1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appEventsLogger.logEvent("Mainscreen_ButtonSetting_Clicked");
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }


    public void onClick(View view) {
        Config.isRunning = true;
        int positon = Config.rungPosition(this, Integer.parseInt((String) view.getTag()));
        if (Config.isRunning) {
            updateState(positon);
            txtState.setText(getResources().getText(R.string.stop_massa));
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_free);
            switch (positon) {
                case 1:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber1_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_free);
                    break;
                case 2:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber2_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_250);
                    break;
                case 3:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber3_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_250);
                    break;
                case 4:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber4_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_500);
                    break;
                case 5:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber5_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_500);
                    break;
                case 6:
                    appEventsLogger.logEvent("Mainscreen_ButtonNumber6_Clicked");
                    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_vibration_600);

                    break;
            }
            relativeLayout.startAnimation(animation);
        } else {
            relativeLayout.clearAnimation();
            txtState.clearAnimation();
            txtState.setText(getResources().getText(R.string.stop_massa));
            updateState(0);
        }
    }

    private void updateState(int positon) {
        Button[] btnItems = new Button[]{button1, button2, button3, button4, button5, button6};
        for (Button btn : btnItems)
            btn.clearAnimation();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && positon != 0) {
//            btnItems[positon - 1].setAnimation();
//            Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
//            zoomin.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    btnItems[positon - 1].startAnimation(zoomout);
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//            });
//            zoomout.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    btnItems[positon - 1].startAnimation(zoomin);
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//            });
            btnItems[positon - 1].startAnimation(zoomout);
        }

    }

    private void showDialogRateApp() {
        final Dialog dialog1 = new Dialog(MainActivity.this);
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.custom_dialog_rate);
        dialog1.setCancelable(true);

        final TextView btnRate = dialog1.findViewById(R.id.btnRate);
        TextView btnLater = dialog1.findViewById(R.id.btnLater);


        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Prefs.with(MainActivity.this).readBoolean("change", false)) {
//                    logger.logEvent("DialogRate_ButtonRate_Clicked");
                    Prefs.with(MainActivity.this).writeBoolean("rate", true);
                    dialog1.dismiss();
                    finish();
                }

            }
        });
        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prefs.with(MainActivity.this).writeBoolean("rate", false);
                dialog1.dismiss();
                finish();
            }
        });
        RatingBar mRatingBar = dialog1.findViewById(R.id.mRatingBar);
        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.colorLater), PorterDuff.Mode.SRC_ATOP);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating > 3) {
                    Toast.makeText(MainActivity.this, getString(R.string.sms_thank_you_rate), Toast.LENGTH_SHORT).show();
                    Prefs.with(MainActivity.this).writeBoolean("rate", true);
                    Intent i = new Intent("android.intent.action.VIEW");
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                    startActivity(i);
                    finish();
                } else if (rating <= 3) {
                    Prefs.with(MainActivity.this).writeBoolean("change", true);
//                    btnRate.setBackgroundResource(R.drawable.custom_button_rate);
                } else {
//                    btnRate.setBackgroundResource(R.drawable.custom_button_later);
                }
            }
        });

        dialog1.show();
    }

    @Override
    public void onBackPressed() {
        boolean action = Prefs.with(getApplicationContext()).readBoolean("action");

        if (!action) {
            if (!Prefs.with(MainActivity.this).readBoolean("rate", false)) {
                showDialogRateApp();
            } else {
                super.onBackPressed();

            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
        imgBg.setImageDrawable(getResources().getDrawable(R.drawable.bg));

        if (Hawk.contains("VIBRATTOR_COLOR")) {
            int vibPos = Hawk.get("VIBRATTOR_COLOR");
        } else {
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        relativeLayout.clearAnimation();
        txtState.clearAnimation();
        txtState.setText(getResources().getText(R.string.start_massa));
        Config.isRunning = false;
        updateState(0);
        Config.stopRung();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Config.isRunning = false;
//        updateState(0);
//        Config.stopRung();
        AppEventsLogger.deactivateApp(this);
    }

    @OnClick(R.id.relativeLayout)
    public void onViewClicked() {
        Config.isRunning = !Config.isRunning;
        if (Config.isRunning) {
            View view = new View(this);
            view.setTag("1");
            onClick(view);
        } else Config.stopRung();
        if (Config.isRunning) {
            txtState.setText(getResources().getText(R.string.stop_massa));
            updateState(1);
            appEventsLogger.logEvent("Mainscreen_ButtonStart_Clicked");
        } else {
            relativeLayout.clearAnimation();
            txtState.setText(getResources().getText(R.string.start_massa));
            updateState(0);
            appEventsLogger.logEvent("Mainscreen_ButtonStop_Clicked");

//            txtState.clearAnimation();

        }


    }

    @OnClick(R.id.im_store)
    public void onViewClicked2() {
        appEventsLogger.logEvent("Mainscreen_ButtonRate_Clicked");
//        Intent i = new Intent("android.intent.action.VIEW");
//        i.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
//        startActivity(i);
    }
}
