package snow.app.ideelee.extrafiles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.HashMap;

import snow.app.ideelee.Login;


public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
     
    // Sharedpref file name
    private static final String PREF_NAME = "snow_kooka_pref";
     
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";




    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ID = "id";
    public static final String KEY_OAUTH_PROVIDER= "oauth_provider";
    public static final String KEY_CONTACT_NUMBER= "contact_no";
    public static final String KEY_STATUS= "status";
    public static final String KEY_CREATED_ON= "created_on";
    public static final String KEY_UPDATED_ON= "updated_on";
    public static final String KEY_PROFILE_IMAGE= "profile_image";
    public static final String KEY_OAUTH_UID= "oauth_uid";
    public static final String KEY_ADDRESS= "address";
    public static final String KEY_TOKEN= "token";


    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_TYPE = "type";   //1= USER    2= SERVICE PROVIDER

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
     
    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email,String mobile,String pass,String id, String status,String address,
                                   String profileimage,String usertype,String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
         
        // Storing name in pref
        editor.putString(KEY_NAME, name);
         
        // Storing email in pref
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_CONTACT_NUMBER,mobile );
        editor.putString(KEY_ADDRESS, address);

        editor.putString(KEY_ID, id);
        editor.putString(KEY_PROFILE_IMAGE, profileimage);
        editor.putString(KEY_STATUS, status);
        editor.putString(KEY_TYPE, usertype);
        editor.putString(KEY_TOKEN,token);


        // commit changes
        editor.commit();
    }   
     
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
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


    public   String getKeyUserType() {
        return pref.getString(KEY_TYPE, "0");
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_TOKEN, pref.getString(KEY_TOKEN, null));

        // return user
        return user;
    }
     
    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
         
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         
        // Staring Login Activity
        _context.startActivity(i);
    }
     
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}