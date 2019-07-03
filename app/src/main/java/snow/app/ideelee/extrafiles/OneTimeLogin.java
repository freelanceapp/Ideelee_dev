package snow.app.ideelee.extrafiles;

import android.content.Context;
import android.content.SharedPreferences;

public class OneTimeLogin {
    // Shared preferences file name
    private static final String PREF_NAME = "LoginOneTime";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;

    public OneTimeLogin(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean ispunchin) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, ispunchin);
        editor.commit();
    }

}