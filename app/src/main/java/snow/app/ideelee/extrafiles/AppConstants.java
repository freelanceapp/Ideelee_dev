package snow.app.ideelee.extrafiles;

public class AppConstants {
    public static final String BASE_URL = "http://112.196.5.115/";
    public static final String PATH = "bestprice/api/auth/";
    public static final String LOGIN = PATH + "login";
    public static final String OPERATORTYPE = PATH + "get-operator-type";
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
