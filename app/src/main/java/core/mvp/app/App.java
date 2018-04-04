package core.mvp.app;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d on 11/1/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "font_tenapp.ttf");
    }
}
