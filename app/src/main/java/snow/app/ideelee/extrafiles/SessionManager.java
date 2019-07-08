package snow.app.ideelee.extrafiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import snow.app.ideelee.Login;


public class SessionManager {
    private static final String PREF_NAME = "snow.app.ideelee";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_OAUTH_PROVIDER = "oauth_provider";
    public static final String KEY_CONTACT_NUMBER = "contact_no";
    public static final String KEY_PROFILE_IMAGE = "profile_image";

    public static final String KEY_ADDRESS = "address";
    public static final String KEY_TOKEN = "token";
     public static final String KEY_EMAIL = "email";
     public static final String KEY_LAT = "lat";
     public static final String KEY_LNG = "lng";
    public static final String KEY_TYPE = "type";   //1= USER    2= SERVICE PROVIDER
    // Sharedpref file name
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    /**
     * Create login session
     */
    public void createLoginSession(String id, String name, String email, String pass, String oAUth,
                                   String contact,String profile,String address,String token) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, pass);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_OAUTH_PROVIDER, oAUth);
        editor.putString(KEY_CONTACT_NUMBER, contact);
        editor.putString(KEY_PROFILE_IMAGE, profile);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }


    public String getKeyUserType() {
        return pref.getString(KEY_TYPE, "0");
    }


    /**
     * Clear session details
     */
    public void logoutUser() {
        editor.clear();
        editor.commit();

    }


    public String getKeyId() {
        return pref.getString(KEY_ID, "");
    }
    public String getKeyAddress() {
        return pref.getString(KEY_ADDRESS, "");
    }

    public String getKeyToken() {
        return pref.getString(KEY_TOKEN, "");
    }

    public   String getKeyName() {
        return pref.getString(KEY_NAME, "");
    }



    public   String getKeyEmail() {
        return pref.getString(KEY_EMAIL, "");
    }

    public   String getKeyProfileImage() {
        return pref.getString(KEY_PROFILE_IMAGE, "");
    }



    public   String getKeyLat() {
        return pref.getString(KEY_LAT, "0");

    }

    public   String getKeyLng() {
        return pref.getString(KEY_LNG, "0");

    }

    public   void setKeyLat(String lat) {
        editor.putString(KEY_LAT,lat);
        editor.commit();
    }

    public   void setKeyLng(String lat) {
        editor.putString(KEY_LNG,lat);
        editor.commit();
    }  public   void setKeyAddress(String lat) {
        editor.putString(KEY_ADDRESS,lat);
        editor.commit();
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}