package snow.app.ideelee.HomeScreen.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import snow.app.ideelee.Login;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.extrafiles.ImagePickerActivity;
import snow.app.ideelee.extrafiles.SessionManager;
import snow.app.ideelee.responses.getuserprofileres.GetUserProfileRes;
import snow.app.ideelee.responses.sendhelpmsgres.SendHelpMsgRes;
import snow.app.ideelee.responses.updateuserprofileres.UpdateUserProfileRes;

import static android.content.Context.WINDOW_SERVICE;


public class ProfileFragment extends Fragment {


    public static final int REQUEST_IMAGE = 100;
    private static final String TAG = ProfileFragment.class.getSimpleName();
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.email)
    EditText ed_email;
    @BindView(R.id.phone)
    EditText ed_phone;
    @BindView(R.id.address)
    EditText ed_address;
    @BindView(R.id.name)
    EditText ed_name;
    @BindView(R.id.parent)
    RelativeLayout parent;

    @BindView(R.id.changeaddress)
    TextView changeaddress;

    @BindView(R.id.update)
    Button update;

int height,width;
    ApiService apiService;
    String userid, token, servicetype;
    HashMap<String, String> map;
    SessionManager sessionManager;
    String PROFILE_IMAGE = "";
    private Unbinder unbinder;

    Uri IMAGE_URI=null;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, v);
        WindowManager wm = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
          height = displayMetrics.heightPixels;
          width = displayMetrics.widthPixels;
        sessionManager = new SessionManager(getActivity());
        userid = sessionManager.getKeyId();
        token = sessionManager.getKeyToken();

        PROFILE_IMAGE = sessionManager.getKeyProfileImage();
        Log.e("userid--", userid + token);
        apiService = ApiClient.getClient(getActivity())
                .create(ApiService.class);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileImageClick();
            }
        });


        handlegetUserProfileData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> data = new HashMap<>();

                if (ed_name.getText().toString().isEmpty()) {
                    ed_name.setError("Required");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches()) {
                    ed_email.setError("Invalid");
                } else if (ed_phone.getText().toString().isEmpty()) {
                    ed_phone.setError("Required");
                } else if (ed_phone.getText().toString().length() < 10) {
                    ed_phone.setError("Invalid");
                } else if (ed_address.getText().toString().isEmpty()) {
                    ed_address.setError("Required");
                } else {
                    data.put("userid", userid);
                    data.put("token", token);
                    data.put("name", ed_name.getText().toString());
                    data.put("email", ed_email.getText().toString());
                    data.put("phone", ed_phone.getText().toString());
                    data.put("address", ed_address.getText().toString());
                    data.put("latitude", sessionManager.getKeyLat());
                    data.put("longitude", sessionManager.getKeyLng());
                    data.put("Profile_image", PROFILE_IMAGE);
                    uploadFile(data);
                }

            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    void onProfileImageClick() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        } else {
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getActivity(), new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getActivity(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    IMAGE_URI=uri;
                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);
        Glide.with(this).load(url).apply(RequestOptions.circleCropTransform())
                .apply(new RequestOptions().override(width/3))
                .into(img);
        img.setColorFilter(ContextCompat.getColor(getActivity(), android.R.color.transparent));
    }


    public void getUserProfileData(HashMap<String, String> map) {
        //  createProgressDialog();
        Observer<GetUserProfileRes> observer = apiService.getUserProfiledata(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<GetUserProfileRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GetUserProfileRes res) {
                        if (res.getStatus()) {
                            ed_name.setText(res.getServicedata().getName());
                            ed_address.setText(res.getServicedata().getAddress());
                            ed_phone.setText(res.getServicedata().getContactNo());
                            ed_email.setText(res.getServicedata().getEmail());

                            Glide.with(getActivity()).load(res.getServicedata().getProfileImage())
                                    .apply(new RequestOptions().override(width/3))
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(img);








                        } else {
                            Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        // dismissProgressDialog();

                    }
                });
    }


    private void handlegetUserProfileData() {
        map = new HashMap<>();
        map.put("userid", userid);
        map.put("token", token);
        getUserProfileData(map);
    }



    private void uploadFile( HashMap<String,String >map) {
        MultipartBody.Part profile_image=null;
        if (IMAGE_URI!=null){

            Uri uri = IMAGE_URI;
            File file = new File(uri.getPath());//create path from uri



            // File file = new File(getRealPathFromURI(IMAGE_URI));
            RequestBody requestFile =RequestBody.create(MediaType.parse("multipart/form-data"), file);
             profile_image = MultipartBody.Part.createFormData("profile_image", file.getName(), requestFile);

        }
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("userid"));
        RequestBody token = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("token"));
        RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("name"));
        RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("email"));
        RequestBody phone = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("phone"));
        RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("address"));
        RequestBody latitude = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("latitude"));
        RequestBody longitude = RequestBody.create(MediaType.parse("multipart/form-data"), map.get("longitude"));

        apiService.updateProfile(userid, token, name, email,phone,address,latitude,longitude, profile_image).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Observer<UpdateUserProfileRes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateUserProfileRes res) {

                        Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();
                        SessionManager sessionManager = new SessionManager(getActivity());
                        sessionManager.createLoginSession(String.valueOf(res.getData().getId()), res.getData().getName()
                                , res.getData().getEmail()
                                , res.getData().getPassword(), res.getData().getOauthProvider()
                                , res.getData().getContactNo(), res.getData().getProfileImage()
                                , res.getData().getAddress(), sessionManager.getKeyToken());

getActivity().finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //  dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        // dismissProgressDialog();

                    }
                });
    }


    private String getRealPathFromURI(Uri contentUri) {


        Log.e("content uri----","cu---"+contentUri.toString());
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

}
