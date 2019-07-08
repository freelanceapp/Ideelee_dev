package snow.app.ideelee.HomeScreen.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.squareup.picasso.Picasso;

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
import snow.app.ideelee.AddAddress;
import snow.app.ideelee.AppUtils.CircleTransform;
import snow.app.ideelee.R;
import snow.app.ideelee.api_request_retrofit.ApiService;
import snow.app.ideelee.api_request_retrofit.retrofit_client.ApiClient;
import snow.app.ideelee.coupons.SelectCouponCat;
import snow.app.ideelee.extrafiles.ImagePickerActivity;
import snow.app.ideelee.responses.getuserprofileres.GetUserProfileRes;

import static android.content.Context.MODE_PRIVATE;
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
    ApiService apiService;
    String userid, token, servicetype;
    HashMap<String, String> map;
    SharedPreferences prefs;
    private Unbinder unbinder;

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
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
          prefs = getActivity().getSharedPreferences("Login", MODE_PRIVATE);
        userid = prefs.getString("userid", "0");
        token = prefs.getString("token", "0");


        Log.e("userid--",userid+token);
        apiService = ApiClient.getClient(getActivity())
                .create(ApiService.class);
/*
        Picasso.with(getActivity())
                .load("https://pbs.twimg.com/profile_images/572905100960485376/GK09QnNG.jpeg")
                .resize(width / 4, width / 4)
                .transform(new CircleTransform())
                .centerCrop()
                .into(img);*/
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileImageClick();
            }
        });
      /*  ed_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddAddress.class);
                startActivity(intent);
            }
        });

        changeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AddAddress.class);
                startActivity(intent);
            }
        });*/

        handlegetUserProfileData();
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
                            Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            ed_name.setText(res.getServicedata().getName());
                            ed_address.setText(res.getServicedata().getAddress());
                            ed_phone.setText(res.getServicedata().getContactNo());
                            ed_email.setText(res.getServicedata().getEmail());



                            Glide.with(getActivity()).load(res.getServicedata().getProfileImage())
                                    .apply(new RequestOptions().override(300, 300))
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(img);

/*

                            Picasso.with(getActivity())
                                    .load(res.getServicedata().getProfileImage())
//                                    .resize(width / 4, width / 4)
                                    .transform(new CircleTransform())
                                    .centerCrop()
                                    .into(img);
*/

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




    //update---user profile






    public void updateUserProfileData(HashMap<String, String> map) {
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
                            Toast.makeText(getActivity(), res.getMessage(), Toast.LENGTH_SHORT).show();
                            ed_name.setText(res.getServicedata().getName());
                            ed_address.setText(res.getServicedata().getAddress());
                            ed_phone.setText(res.getServicedata().getContactNo());
                            ed_email.setText(res.getServicedata().getEmail());



                            Glide.with(getActivity()).load(res.getServicedata().getProfileImage())
                                    .apply(new RequestOptions().override(300, 300))
                                    .apply(RequestOptions.circleCropTransform())
                                    .into(img);

/*

                            Picasso.with(getActivity())
                                    .load(res.getServicedata().getProfileImage())
//                                    .resize(width / 4, width / 4)
                                    .transform(new CircleTransform())
                                    .centerCrop()
                                    .into(img);
*/

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


    private void handleupdateUserProfileData() {
        map = new HashMap<>();
        map.put("userid", userid);
        map.put("token", token);
        map.put("name", ed_name.getText().toString());
        map.put("email", ed_email.getText().toString());
        map.put("phone", ed_phone.getText().toString());
        map.put("address", ed_address.getText().toString());
        map.put("latitude", token);
        map.put("longitude", token);
        map.put("profile_image", token);
        getUserProfileData(map);
    }











}
