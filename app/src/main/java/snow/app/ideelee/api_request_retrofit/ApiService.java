package snow.app.ideelee.api_request_retrofit;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

import retrofit2.http.Part;
import snow.app.ideelee.responses.confirmotpresponse.ConfirmOtpRes;
import snow.app.ideelee.responses.couponcatres.CouponCatRes;
import snow.app.ideelee.responses.coupondetails.CouponDetailRes;
import snow.app.ideelee.responses.deliverysubcatres.DeliverySubCatRes;
import snow.app.ideelee.responses.forgotpassword.ForgotPassRes;
import snow.app.ideelee.responses.getcouponsres.GetCouponsRes;
import snow.app.ideelee.responses.gethelpcat.GetHelpCatRes;
import snow.app.ideelee.responses.getstoredetailsres.GetStoreDetailsRes;
import snow.app.ideelee.responses.getuserprofileres.GetUserProfileRes;
import snow.app.ideelee.responses.googleloginres.GetGoogleLoginRes;
import snow.app.ideelee.responses.homescreenres.HomeScreenRes;
import snow.app.ideelee.responses.loginres.LoginRes;
import snow.app.ideelee.responses.logoutres.LogoutRes;
import snow.app.ideelee.responses.morecatres.MoreCategoryRes;
import snow.app.ideelee.responses.ondemandserviceproviderlistres.GetOnDemandProvidersListRes;
import snow.app.ideelee.responses.ondemandservicessubcatres.OnDemandSubCatRes;
import snow.app.ideelee.responses.registerres.RegisterRes;
import snow.app.ideelee.responses.sendhelpmsgres.SendHelpMsgRes;
import snow.app.ideelee.responses.subsubcatfileration.SubSubCatFilterationRes;
import snow.app.ideelee.responses.updateuseraddressres.UpdateUserAddressRes;
import snow.app.ideelee.responses.updateuserprofileres.UpdateUserProfileRes;

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


  //Sub Categories  API
    @POST(MORE_SUB_CAT_API)
    @FormUrlEncoded
    Observable<DeliverySubCatRes> getDeliverySubCat(@FieldMap HashMap<String, String> params);


    // On Demamnd Sub Categories  API
    @POST(MORE_SUB_CAT_API)
    @FormUrlEncoded
    Observable<OnDemandSubCatRes> getOnDemandSubCat(@FieldMap HashMap<String, String> params);

    //Coupons Sub Categories  API
    @POST(MORE_SUB_CAT_API)
    @FormUrlEncoded
    Observable<CouponCatRes> getCouponsCat(@FieldMap HashMap<String, String> params);

    //On Demand service provider list   API
    @POST(GET_ONDEMAND_SERVICEPROVIDER_LIST)
    @FormUrlEncoded
    Observable<GetOnDemandProvidersListRes> getOnDemandServiceProviderList(@FieldMap HashMap<String, String> params);

    //Sub Sub cat filteration list   API
    @POST(SUB_SUB_CAT_FILTERATION_LIST)
    @FormUrlEncoded
    Observable<SubSubCatFilterationRes> getSubSubCatFilterationList(@FieldMap HashMap<String, String> params);


    //get user profile  API
    @POST(GET_USER_PROFILE)
    @FormUrlEncoded
    Observable<GetUserProfileRes> getUserProfiledata(@FieldMap HashMap<String, String> params);

    //user profile update    API
    @POST(UPDATE_USER_PROFILE)
    @FormUrlEncoded
    Observable<UpdateUserProfileRes> updateUser(@FieldMap HashMap<String, String> params);


  //user addrss update   API
    @POST(UPDATE_USER_ADDRESS)
    @FormUrlEncoded
    Observable<UpdateUserAddressRes> updateuseraddressdata(@FieldMap HashMap<String, String> params);


  //get help cat   API
    @POST(GET_HELP_CAT)
    @FormUrlEncoded
    Observable<GetHelpCatRes> getHelpCat(@FieldMap HashMap<String, String> params);


  //get help cat   API
    @POST(SEND_HELP_MSG)
    @FormUrlEncoded
    Observable<SendHelpMsgRes> sendHelpMsg(@FieldMap HashMap<String, String> params);


  //getCoupons   API
    @POST(GET_COUPONS)
    @FormUrlEncoded
    Observable<GetCouponsRes> getCoupons(@FieldMap HashMap<String, String> params);


  //Coupons Details   API
    @POST(GET_COUPON_DETAILS)
    @FormUrlEncoded
    Observable<CouponDetailRes> getCouponDetails(@FieldMap HashMap<String, String> params);

    @Multipart
    @POST(UPDATE_USER_PROFILE)
    Observable<UpdateUserProfileRes> updateProfile(@Part("userid") RequestBody userid,
                                             @Part("token") RequestBody token,
                                             @Part("name") RequestBody name,
                                             @Part("email") RequestBody email,
                                             @Part("phone") RequestBody phone,
                                             @Part("address") RequestBody address,
                                             @Part("latitude") RequestBody latitude,
                                             @Part("longitude") RequestBody longitude,
                                             @Part MultipartBody.Part image);



    //Store Details   API
    @POST(GET_STORE_DETAILS)
    @FormUrlEncoded
    Observable<GetStoreDetailsRes> getStoreDetails(@FieldMap HashMap<String, String> params);

    //Google Login   API
    @POST(GOOGLE_LOGIN)
    @FormUrlEncoded
    Observable<GetGoogleLoginRes> getGoogleLogin(@FieldMap HashMap<String, String> params);


}