package core.mvp.runservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import core.config.Config;

/**
 * Created by d on 9/27/2017.
 */

public class RunService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.hasExtra("indensityVibrate")) {
            int indensityVibrate = intent.getIntExtra("indensityVibrate", 0);
            Config.isRunning = true;
            Config.rungPosition(this, indensityVibrate);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Config.stopRung();
    }
}
