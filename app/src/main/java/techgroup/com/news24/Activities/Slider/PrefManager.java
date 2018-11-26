package techgroup.com.news24.Activities.Slider;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

   private SharedPreferences pref;
   private SharedPreferences.Editor editor;
   public Context mContext;

    // shared pref mode
     int  PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "news24_welcomeSlider";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


}
