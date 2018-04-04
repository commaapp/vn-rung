package core.config;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;

/**
 * Created by d on 9/26/2017.
 */

public class Config {
    public static Vibrator vibrator;
    public static boolean isRunning = false;

    public static void chayRung(Context context, int running, int sleep) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] run = {0, running, sleep};
        isRunning = true;
        vibrator.vibrate(run, 0);
    }

    public static void stopRung() {
        isRunning = false;
        if (vibrator != null)
            vibrator.cancel();
    }

    public static void goToStoreByPackageName(Context context) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=VTOOL")));
    }

    public static void goToStoreByPackageNameGotoStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
//        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
//                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
//                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static int rungPosition(Context context, int positon) {
        if (isRunning) {
            switch (positon) {
                case 1:
                    chayRung(context, 1000, 1);
                    return 1;
                case 2:
//                    chayRung(context, 250, 1);
                    chayRung(context, 250, 250);
                    return 2;
                case 3:
//                    chayRung(context, 500, 1);
                    chayRung(context, 500, 250);
                    return 3;
                case 4:
//                    chayRung(context, 500, 1);
                    chayRung(context, 500, 500);
                    return 4;
                case 5:
//                    chayRung(context, 1000, 1);
                    chayRung(context, 1000, 500);
                    return 5;
                case 6:
//                    chayRung(context, 1000, 1);
                    chayRung(context, 1200, 500);
                    return 6;
            }
        } else {
            stopRung();
            return 0;
        }

        return positon;
    }
}
