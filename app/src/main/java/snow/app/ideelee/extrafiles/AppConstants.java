package snow.app.ideelee.extrafiles;

public class AppConstants {
    public static final String BASE_URL = "http://112.196.5.117";
    public static final String PATH = "/ideeleeplus/api/userauth/";
    public static final String PATH_HOME = "/ideeleeplus/api/userapi/";
    public static final String LOGIN = PATH + "userlogin";
    public static final String LOGOUT = PATH + "logout";
    public static final String REGISTER = PATH + "userSignup";
    public static final String CONFIRM_OTP = PATH + "confirmOtp";
    public static final String FORGOT_PASSWORD = PATH + "forgetpassword";
    public static final String HOME_SCREEN_DATA = PATH_HOME + "homeScreenData";
    public static final String MORE_CAT_API = PATH_HOME + "moreCategories";
    public static final String MORE_SUB_CAT_API = PATH_HOME + "getSubCategories";
    public static final String  GET_ONDEMAND_SERVICEPROVIDER_LIST = PATH_HOME + "getOnDemandProvidersList";
    public static final String  SUB_SUB_CAT_FILTERATION_LIST = PATH_HOME + "getAllSubSubCategories";
    public static final String  GET_USER_PROFILE = PATH_HOME + "getuserprofile";
    public static final String  GET_HELP_CAT = PATH_HOME + "getHelpCategories";
    public static final String  SEND_HELP_MSG = PATH_HOME + "sendHelpMessage";
    public static final String  GET_COUPONS = PATH_HOME + "getCoupons";
    public static final String  UPDATE_USER_PROFILE = PATH_HOME + "updateUserdata";
    public static final String  UPDATE_USER_ADDRESS = PATH_HOME + "updateUserAddress";
    public static final String  GET_COUPON_DETAILS = PATH_HOME + "singleCouponDetail";
    public static final String  GET_STORE_DETAILS = PATH_HOME + "getStoreDetail";




    public static class LoginProcess {
        public static String mDeviceType = "android";
        public static String mMobileNumber = "";
        public static String mUserIdForActivationAccountAfterOTPVerification = "";
        public static boolean mMobileNumberExistFlag = false;

    }

    public static class OTPVerification {

        public static final String APPLICATION_KEY = "813aa1fb-3016-429c-9f3e-fd9e860e26b9";
        public static final String APPLICATION_HASH = "sYhmYsFd0kOOV827tPJGSg==";

        public static final String SMS = "sms";
        public static final String INTENT_PHONENUMBER = "phonenumber";
        public static final String INTENT_METHOD = "method";

    }


}
