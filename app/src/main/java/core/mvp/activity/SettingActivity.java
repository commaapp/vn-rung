package core.mvp.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import com.facebook.appevents.AppEventsLogger;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.eco.com.eco_vibrate_massage.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.swt_noti)
    SwitchCompat swtNoti;
    @BindView(R.id.ads_view)
    LinearLayout nativeAdContainer;

    boolean statusNoti;
    NotificationManager notificationmanager;

    AppEventsLogger appEventsLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        appEventsLogger = AppEventsLogger.newLogger(this);
        swtNoti.setOnCheckedChangeListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        imgBg.setImageDrawable(getResources().getDrawable(R.drawable.bg));
        if (Hawk.contains("statusNotification")) {
            statusNoti = Hawk.get("statusNotification");
            swtNoti.setChecked(statusNoti);
        }
    }

    @OnClick({R.id.layout_notification, /*R.id.layout_wall_paper,*/ R.id.layout_Vibrate_color, R.id.img_back,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                appEventsLogger.logEvent("SettingScreen_ButtonBack_Clicked");
                finish();
                break;
            case R.id.layout_notification:
                appEventsLogger.logEvent("SettingScreen_ToggleNotifi_Clicked");
                notificationAction();
                break;
            case R.id.layout_Vibrate_color:
                appEventsLogger.logEvent("SettingScreen_ButtonBack_Clicked");
                break;
        }
    }

    private void notificationAction() {
        if (statusNoti) {
            statusNoti = false;
            swtNoti.setChecked(statusNoti);

            notificationmanager.cancel(1);
        } else {
            statusNoti = true;
            swtNoti.setChecked(statusNoti);

            turnOnNotification();
        }
    }

    private void turnOnNotification() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
        notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .setOngoing(true)
                .setContent(remoteViews);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        notificationmanager.notify(1, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.swt_noti:
                appEventsLogger.logEvent("SettingScreen_ToggleNotifi_Clicked");
                statusNoti = b;
                swtNoti.setChecked(b);
                if (b) {
                    turnOnNotification();
                } else {
                    notificationmanager.cancel(1);
                }
                Hawk.put("statusNotification", swtNoti.isChecked());
                break;
        }
    }
}
