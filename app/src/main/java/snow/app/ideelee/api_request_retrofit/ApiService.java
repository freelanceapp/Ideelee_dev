package snow.app.ideelee.api_request_retrofit;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import snow.app.ideelee.responses.confirmotpresponse.ConfirmOtpRes;
import snow.app.ideelee.responses.forgotpassword.ForgotPassRes;
import snow.app.ideelee.responses.homescreenres.HomeScreenRes;
import snow.app.ideelee.responses.loginres.LoginRes;
import snow.app.ideelee.responses.logoutres.LogoutRes;
import snow.app.ideelee.responses.morecatres.MoreCategoryRes;
import snow.app.ideelee.responses.registerres.RegisterRes;
import static snow.app.ideelee.extrafiles.AppConstants.*;

public interface ApiService {


    //register api
    @POST(REGISTER)
    @FormUrlEncoded
    Observable<RegisterRes> registerUser(@FieldMap HashMap<String, String> params);

    //otp api
    @POST(CONFIRM_OTP)
    @FormUrlEncoded
    Observable<ConfirmOtpRes> confirmOtp(@FieldMap HashMap<String, String> params);


    //login api
    @POST(LOGIN)
    @FormUrlEncoded
    Observable<LoginRes> loginUser(@FieldMap HashMap<String, String> params);


    //logout api
    @POST(LOGOUT)
    @FormUrlEncoded
    Observable<LogoutRes> logoutUser(@FieldMap HashMap<String, String> params);

    //forgot password api
    @POST(FORGOT_PASSWORD)
    @FormUrlEncoded
    Observable<ForgotPassRes> forgotpass(@FieldMap HashMap<String, String> params);


    //Home Screen data api
    @POST(HOME_SCREEN_DATA)
    @FormUrlEncoded
    Observable<HomeScreenRes> getHomeScreenData(@FieldMap HashMap<String, String> params);


    //More Categories data api
    @POST(MORE_CAT_API)
    @FormUrlEncoded
    Observable<MoreCategoryRes> getMoreCats(@FieldMap HashMap<String, String> params);

}