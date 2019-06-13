package snow.app.ideelee.api_request_retrofit;

import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import snow.app.ideelee.Responses.LoginResponse.Data;
import snow.app.ideelee.Responses.LoginResponse.LoginRes;
import snow.app.ideelee.Responses.opertaorres.OperatorRes;
import snow.app.ideelee.extrafiles.AppConstants;

import static snow.app.ideelee.extrafiles.AppConstants.LOGIN;
import static snow.app.ideelee.extrafiles.AppConstants.OPERATORTYPE;

public interface ApiService {
    // Register new user
    @POST(LOGIN)
    @FormUrlEncoded
    Observable<LoginRes> loginUser(@FieldMap HashMap<String, String> params);

    @POST(OPERATORTYPE)
    Observable<OperatorRes> getOperatorlist();
}