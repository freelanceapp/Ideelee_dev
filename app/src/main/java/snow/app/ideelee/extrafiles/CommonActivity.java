//package snow.app.ideelee.extrafiles;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.Signature;
//import android.graphics.*;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.ColorDrawable;
//import android.location.Address;
//import android.location.Geocoder;
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.provider.SyncStateContract;
//import android.support.design.widget.Snackbar;
//import android.support.v4.content.FileProvider;
//import android.support.v7.widget.RecyclerView;
//import android.text.Selection;
//import android.text.Spannable;
//import android.text.SpannableString;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.*;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.view.animation.LinearInterpolator;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.kaopiz.kprogresshud.KProgressHUD;
//import com.squareup.picasso.Picasso;
//import de.hdodenhof.circleimageview.CircleImageView;
//
//import java.io.*;
//import java.security.MessageDigest;
//import java.text.DateFormat;
//import java.text.DateFormatSymbols;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//import static android.os.Environment.getExternalStoragePublicDirectory;
//
//
//
//public abstract class CommonActivity extends BaseActivity {
//
//    public static final int REQUEST_SCANNER_CODE = 101;
//    public static final int MY_PERMISSIONS_REQUEST_CODE = 123;
//    // ########## Constant field for permission request ###########
//    public static final int MY_PERMISSIONS_REQUEST_CAMERA_PERMISSION = 101;
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//    public static final int MEDIA_TYPE_VIDEO = 202;
//    public static final List<Long> times = Arrays.asList(
//            TimeUnit.DAYS.toMillis(365),
//            TimeUnit.DAYS.toMillis(30),
//            TimeUnit.DAYS.toMillis(1),
//            TimeUnit.HOURS.toMillis(1),
//            TimeUnit.MINUTES.toMillis(1),
//            TimeUnit.SECONDS.toMillis(1));
//    public static final List<String> timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second");
//    //         Messages
//    public String ERROR_EMPTY_JSON = "Something went wrong! Please check internet connection and retry again";
//    public String NETWORK_ERROR = "Internet not found";
//
//    public int PLACE_PICKER_REQUEST = 221;
//    protected Uri photoURI;
//    protected File photoFile = null;
//    protected File mediaFile;
//    private ProgressDialog mDialog = null;
//    private KProgressHUD mKProgressHUD, mKProgressHUDWithText;
//    //    Record video in mp4 format
//    private Uri fileUri;
//    private String mCurrentPhotoPath = "";
//
//    private String mDataTimeFormat = "yyyy-MM-dd hh:mm:ss"; // dd/MM/yyyyIn which you need put here
//    protected SimpleDateFormat sdfDateTimeForApi = new SimpleDateFormat(mDataTimeFormat, Locale.US);
//    private String myFormat = "dd-MM-yyyy"; // dd/MM/yyyyIn which you need put here
//    protected SimpleDateFormat sdfDate = new SimpleDateFormat(myFormat, Locale.US);
//    private String myFormatTime = "hh:mma"; // dd/MM/yyyyIn which you need put here
//    protected SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.US);
//
//    /**
//     * Here is the key method to apply the animation
//     **/
//
//    // Allows to remember the last item shown on screen
//    private int lastPosition = -1;
//
//    /**
//     * Create a file Uri for saving an image or video
//     */
//    private static Uri getOutputMediaFileUri(int type) {
//
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
//
//    /**
//     * Create a File for saving an image or video
//     */
//    private static File getOutputMediaFile(int type) {
//
//        // Check that the SDCard is mounted
//        File mediaStorageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera");
//        Log.d("path", mediaStorageDir + "");
//
//        // Create the storage directory(MyCameraVideo) if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                // ("Failed to create directory MyCameraVideo.");
//                Log.d("MyCameraVideo", "Failed to create directory MyCameraVideo.");
//                return null;
//            }
//        }
//        // Create a media file name
//
//        // For unique file name appending current timeStamp with file name
//        Date date = new Date();
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());
//        File mediaFile;
//
//        if (type == MEDIA_TYPE_VIDEO) {
//            // For unique video file name appending current timeStamp with file name
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
//            //send broadcast to gallery to show new incomimng data
//
//        } else {
//            return null;
//        }
//        return mediaFile;
//    }
//
//    public static String getlongtoago(long createdAt) {
//        DateFormat userDateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//        DateFormat dateFormatNeeded = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS");
//        Date date = null;
//        date = new Date(createdAt);
//        String crdate1 = dateFormatNeeded.format(date);
//
//        // Date Calculation
//        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        crdate1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);
//
//        // get current date time with Calendar()
//        Calendar cal = Calendar.getInstance();
//        String currenttime = dateFormat.format(cal.getTime());
//
//        Date CreatedAt = null;
//        Date current = null;
//        try {
//            CreatedAt = dateFormat.parse(crdate1);
//            current = dateFormat.parse(currenttime);
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        // Get msec from each, and subtract.
//        long diff = current.getTime() - CreatedAt.getTime();
//        long diffSeconds = diff / 1000;
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
//        long diffDays = diff / (24 * 60 * 60 * 1000);
//
//        String time = null;
//        if (diffDays > 0) {
//            if (diffDays == 1) {
//                time = diffDays + "day ago ";
//            } else {
//                time = diffDays + "days ago ";
//            }
//        } else {
//            if (diffHours > 0) {
//                if (diffHours == 1) {
//                    time = diffHours + "hr ago";
//                } else {
//                    time = diffHours + "hrs ago";
//                }
//            } else {
//                if (diffMinutes > 0) {
//                    if (diffMinutes == 1) {
//                        time = diffMinutes + "min ago";
//                    } else {
//                        time = diffMinutes + "mins ago";
//                    }
//                } else {
//                    if (diffSeconds > 0) {
//                        time = diffSeconds + "secs ago";
//                    }
//                }
//
//            }
//
//        }
//        return time;
//    }
//
//    public static String toDuration(long duration) {
//
//        StringBuffer res = new StringBuffer();
//
//        for (int i = 0; i < CommonActivity.times.size(); i++) {
//            Long current = CommonActivity.times.get(i);
//            long temp = duration / current;
//            if (temp > 0) {
//                res.append(temp).append(" ").append(CommonActivity.timesString.get(i))
//                        .append(temp != 1 ? "s" : "").append(" ago");
//                break;
//            }
//        }
//        if ("".equals(res.toString()))
//            return "0 seconds ago";
//        else
//            return res.toString();
//    }
//
//    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
//        ExifInterface ei = new ExifInterface(image_absolute_path);
//        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//
//        switch (orientation) {
//            case ExifInterface.ORIENTATION_ROTATE_90:
//                return rotate(bitmap, 90);
//
//            case ExifInterface.ORIENTATION_ROTATE_180:
//                return rotate(bitmap, 180);
//
//            case ExifInterface.ORIENTATION_ROTATE_270:
//                return rotate(bitmap, 270);
//
//            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
//                return flip(bitmap, true, false);
//
//            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
//                return flip(bitmap, false, true);
//
//            default:
//                return bitmap;
//        }
//    }
//
//    public static Bitmap rotate(Bitmap bitmap, float degrees) {
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degrees);
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    }
//
//    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
//        Matrix matrix = new Matrix();
//        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
//        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//    }
///*
//
//    protected void goForPlacePickerDialog() {
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//        try {
//            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//    }
//*/
//
//    public static int calculateNoOfColumns(Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        int noOfColumns = (int) (dpWidth / 250);
//        return noOfColumns;
//    }
//
//
//    // choose image dialog from Gallary and Camera
//    protected void Image_Picker_Dialog() {
//        //      AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.DialogSlideAnim));
//        System.gc();
//        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
//        myAlertDialog.setMessage("Choose");
//        myAlertDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//                //      call gallary intent
//                galleryIntent();
//            }
//        });
//
//        myAlertDialog.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//
//                //      calling camera intent
//                cameraIntent();
//            }
//        });
//
//        myAlertDialog.show();
//
//    }
//
//    // Choose image from gallery
//    protected void galleryIntent() {
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto, SELECT_FILE);     // one can be replaced with any action code
//
//    }
//
//    // click image from camera
//    protected void cameraIntent() {
//        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//        takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
//        try {
//            photoFile = createImageFile();
//        } catch (IOException ex) {
//            // Error occurred while creating the File
//            Log.d("mylog", "Exception while creating file: " + ex.toString());
//        }
//        // Continue only if the File was successfully created
//        if (photoFile != null) {
//            Log.d("mylog", "Photofile not null");
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", photoFile);
//            } else {
//                photoURI = Uri.parse("file:" + photoFile.getAbsolutePath());
//            }
//
//            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//            startActivityForResult(takePictureIntent, SyncStateContract.Constants.TAKE_PICTURE);
//        }
//        //   startActivityForResult(intent, Constants.TAKE_PICTURE); //zero can be replaced with any action code
//
//    }
//
//    private File createImageFile() throws IOException {
//        //       Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        Log.d("mylog", "Path: " + mCurrentPhotoPath);
//        return image;
//    }
//
//    // pick a audio from android
//    protected void audioIntent() {
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto, SyncStateContract.Constants.PICK_SONG);     // one can be replaced with any action code
//
//      /*  Intent intent_upload = new Intent();
//        intent_upload.setType("audio*//*");
//        intent_upload.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent_upload,Constants.PICK_SONG);*/
//    }
//
//    protected void Video_Picker_Dialog() {
//        //      AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.DialogSlideAnim));
//        System.gc();
//        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
//        myAlertDialog.setMessage("Choose");
//        myAlertDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//                galleryVideoIntent();
//            }
//        });
//
//        myAlertDialog.setNegativeButton("Camera", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface arg0, int arg1) {
//                fireCaptureVideo();
//
//            }
//        });
//
//        myAlertDialog.show();
//
//    }
//
//    // record video from camera
//    public void fireCaptureVideo() {
//
//        Intent videoCaptureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        videoCaptureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myvideo.mp4");
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            fileUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", mediaFile);
//        } else {
//            fileUri = Uri.parse("file:" + mediaFile.getAbsolutePath());
//        }
//        // create a file to save the video
//        //  fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
//
//        // set video quality
//        videoCaptureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//        // videoCaptureIntent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 10 * 1024 * 1024);
//        videoCaptureIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 59); // upto 59 second this video will be recorded
//
//        // set the image file name
//        videoCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        if (videoCaptureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(videoCaptureIntent, SyncStateContract.Constants.RECORD_VIDEO_);
//        }
//
//    }
//
//    //    Choose image from gallery
//    protected void galleryVideoIntent() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("video/*");
//        startActivityForResult(intent, SyncStateContract.Constants.REQUEST_TAKE_GALLERY_VIDEO);
//
//    }
//
//    public String numberInShortFormat(long count) {
//        if (count < 1000) return "" + count;
//        int exp = (int) (Math.log(count) / Math.log(1000));
//        return String.format("%.1f %c",
//                count / Math.pow(1000, exp),
//                "kMGTPE".charAt(exp - 1));
//    }
//
//    protected void onPlaySongWithInbuiltMusicApps() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri data = Uri.parse("file:///sdcard/Music");
//        String type = "audio/mp3";
//        intent.setDataAndType(data, type);
//        startActivityForResult(intent, SyncStateContract.Constants.PICK_SONG);
//
//    }
//
//    public String getLanguage() {
//
//        Locale systemLocale = getResources().getConfiguration().locale;
//        String strLanguage = systemLocale.getLanguage();
//
//        return strLanguage;
//    }
//
//    public void byPassCall(Class c) {
//        startActivity(new Intent(this, c));
//    }
//
//    public void showProgressDialog(String message) {
//        mDialog = ProgressDialog.show(this, "Please Wait", message, false, false);
//    }
//
//    public void dismissProgress() {
//        if (mDialog != null && mDialog.isShowing()) {
//            mDialog.dismiss();
//        }
//    }
//
//    public SessionBestPrice getSessionInstance() {
//        return new SessionBestPrice(this);
//    }
//
//    public SessionNotNull getSessionInstanceNotNull() {
//        return new SessionNotNull(this);
//    }
//    // public SessionForProfile getSessionForProfileInstance() {
//    // return new SessionForProfile(this);
//    // }
//
//    public void showIOSProgress() {
//        try {
//            mKProgressHUD = KProgressHUD.create(this)
//                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                    //  .setLabel("Please wait")
//                    //  .setDetailsLabel(msg)
//                    .setCancellable(true)
//                    .setAnimationSpeed(1)
//                    .setDimAmount(.2f)
//                    .show();
//        } catch (Exception ex) {
//            Log.wtf("IOS_error_starting", ex.getCause());
//        }
//
//    }
//
//    public void dismissIOSProgress() {
//        try {
//            if (mKProgressHUD != null) {
//                if (mKProgressHUD.isShowing()) {
//                    mKProgressHUD.dismiss();
//                }
//            }
//        } catch (Exception ex) {
//            Log.wtf("IOS_error_dismiss", ex.getCause());
//        }
//
//
//    }
//
//    public void showIOSProgressWithText(String msg) {
//        mKProgressHUDWithText = KProgressHUD.create(this)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel(msg)
//                .setCancellable(true)
//                .setAnimationSpeed(1)
//                .setDimAmount(.2f)
//                .show();
//    }
//
//    public void dismissIOSProgressWithText() {
//        if (mKProgressHUDWithText.isShowing()) {
//            mKProgressHUDWithText.dismiss();
//        }
//    }
//
//    public String refinePhoneNumber(String mFormatedPhone) {
//        return mFormatedPhone.replaceAll("[-.^:(,)]", "").replaceAll(" ", "");
//    }
//
//    public String refineNumberFromExtraSymbols(String num) {
//        return num.replaceAll("[-+.^:(,)]", "").replaceAll(" ", "");
//    }
//
//    public boolean isNetworkConnected() {
//        return new CheckConnectivity(this).isNetworkAvailable();
//    }
//
//
//    public Bitmap getBitmapFromView(ImageView mView) {
//        try {
//            if (mView != null && mView.getDrawable() != null) {
//                return ((BitmapDrawable) mView.getDrawable()).getBitmap();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//        return null;
//
//    }
//
//    public void generateHashKey() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "snowf.app.celiguide", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public String displayQuoteTime(long time_ago) {
//        time_ago = time_ago / 1000;
//        long cur_time = (Calendar.getInstance().getTimeInMillis()) / 1000;
//        long time_elapsed = cur_time - time_ago;
//        long seconds = time_elapsed;
//        // Seconds
//        if (seconds <= 60) {
//            return "Just now";
//        }
//        //Minutes
//        else {
//            int minutes = Math.round(time_elapsed / 60);
//
//            if (minutes <= 60) {
//                if (minutes == 1) {
//                    return "a min ago";
//                } else {
//                    return minutes + " mins ago";
//                }
//            }
//            //Hours
//            else {
//                int hours = Math.round(time_elapsed / 3600);
//                if (hours <= 24) {
//                    if (hours == 1) {
//                        return "An hour ago";
//                    } else {
//                        return hours + " hrs ago";
//                    }
//                }
//                //Days
//                else {
//                    int days = Math.round(time_elapsed / 86400);
//                    if (days <= 7) {
//                        if (days == 1) {
//                            return "Yesterday";
//                        } else {
//                            return days + " days ago";
//                        }
//                    }
//                    //Weeks
//                    else {
//                        int weeks = Math.round(time_elapsed / 604800);
//                        if (weeks <= 4.3) {
//                            if (weeks == 1) {
//                                return "A week ago";
//                            } else {
//                                return weeks + " weeks ago";
//                            }
//                        }
//                        //Months
//                        else {
//                            int months = Math.round(time_elapsed / 2600640);
//                            if (months <= 12) {
//                                if (months == 1) {
//                                    return "A month ago";
//                                } else {
//                                    return months + " months ago";
//                                }
//                            }
//                            //Years
//                            else {
//                                int years = Math.round(time_elapsed / 31207680);
//                                if (years == 1) {
//                                    return "One year ago";
//                                } else {
//                                    return years + " years ago";
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    public String getDataTimeFromMillisecondsForReacted(long timeStamp) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(timeStamp);
//        SimpleDateFormat f = new SimpleDateFormat("MMM");
//
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
//        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//        int mHour = calendar.get(Calendar.HOUR);
//        int mMin = calendar.get(Calendar.MINUTE);
//        int mSecond = calendar.get(Calendar.SECOND);
//
//        return "" + mDay + ", " + getMonthForInt(mMonth);
//
//    }
//
//    public String getDataTimeFromMilliseconds(long timeStamp) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(timeStamp);
//        SimpleDateFormat f = new SimpleDateFormat("MMM");
//
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
//        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//        int mHour = calendar.get(Calendar.HOUR);
//        int mMin = calendar.get(Calendar.MINUTE);
//        int mSecond = calendar.get(Calendar.SECOND);
//
//        return "" + getMonthForInt(mMonth) + ", " + mYear;
//
//    }
//
//    private String getMonthForInt(int num) {
//        String month = "wrong";
//        DateFormatSymbols dfs = new DateFormatSymbols();
//        String[] months = dfs.getMonths();
//        if (num >= 0 && num <= 11) {
//            month = months[num];
//        }
//        return month.replace(month.substring(3), "");
//    }
//
//    public String getRandomUUID() {
//        UUID uuid = UUID.randomUUID();
//        return uuid.toString();
//
//    }
//
//    public String getStringImage(Bitmap bmp) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }
//
//    public void setImageWithPicasso(ImageView imageHolder, String path, int mImgOffline) {
//        if (TextUtils.isEmpty(path)) {
//            Picasso.with(this).load(mImgOffline)
//                    .error(mImgOffline)
//                    .placeholder(mImgOffline)
//                    .fit()
//                    .into(imageHolder);
//        } else {
//            Picasso.with(this).load(path)
//                    .error(mImgOffline)
//                    .placeholder(mImgOffline)
//                    .fit()
//                    .into(imageHolder);
//        }
//    }
//
// /*   protected void getImageWithVolley(final String profilePic, final RelativeLayout mImgUserPro) {
//        ImageLoader mImageLoader = CeliGuideApp.getInstance().getImageLoader();
//        boolean mb = mImageLoader.isCached(profilePic, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
//
//        mImageLoader.get(profilePic, new ImageLoader.ImageListener() {
//
//            @Override
//            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//                if (response.getBitmap() != null) {
//                    Drawable drawable = (Drawable) new BitmapDrawable(response.getBitmap());
//                    mImgUserPro.setBackground(drawable);
//                } else {
//                    mImgUserPro.setBackground(getResources().getDrawable(R.drawable.a));
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //   NetworkResponse mResCode = error.networkResponse;
//                //   Log.e("error", error.getMessage() + mResCode.statusCode);
//            }
//        });
//
//       *//*
//        Glide.with(this)
//                .load(profilePic.replaceFirst("s", "").replaceAll(" ", "%20"))
//                .asBitmap()
//                .placeholder(R.drawable.a)
//                .error(R.drawable.a)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(mImgUserPro);*//*
//    }*/
//
//    private void setAnimation(View viewToAnimate, int position) {
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
//
//
//    public AlphaAnimation getBlinkingAnimation() {
//        AlphaAnimation blinkanimation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
//        blinkanimation.setDuration(300); // duration
//        blinkanimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
//        blinkanimation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
//        blinkanimation.setRepeatMode(Animation.REVERSE);
//        return blinkanimation;
//    }
//
//    public void goForNext(Class cl) {
//        startActivity(new Intent(this, cl));
//        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
//        finish();
//    }
//
//    public void goToNextWithClearBackStack(Class cls) {
//        Intent i = new Intent(this, cls);
//        // set the new task and clear flags
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
//        finish();
//
//    }
//
//    public void goBack() {
//        finish();
//        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//
//    }
//
//    protected Animation bounceAnimationOnButton() {
//        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
//        // Use bounce interpolator with amplitude 0.2 and frequency 20
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 10);
//        myAnim.setInterpolator(interpolator);
//
//        return myAnim;
//    }
//
//
//    public void hideSystemBottomBar() {
//        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                | View.SYSTEM_UI_FLAG_IMMERSIVE);
//    }
//
//    public void goForNextScreenWithoutFinish(Class mClass) {
//
//        startActivity(new Intent(this, mClass));
//        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
//    }
//
//    public void showSnackBar(View v, String mMsg) {
//        Snackbar.make(v, mMsg, Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//
//    }
//
//    protected void animationToItemsFadeIn(final RecyclerView mRecyclerView) {
//        mRecyclerView.getViewTreeObserver().addOnPreDrawListener(
//                new ViewTreeObserver.OnPreDrawListener() {
//
//                    @Override
//                    public boolean onPreDraw() {
//                        mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//
//                        for (int i = 0; i < mRecyclerView.getChildCount(); i++) {
//                            View v = mRecyclerView.getChildAt(i);
//                            v.setAlpha(0.0f);
//                            v.animate().alpha(1.0f)
//                                    .setDuration(300)
//                                    .setStartDelay(i * 50)
//                                    .start();
//                        }
//
//                        return true;
//                    }
//                });
//    }
//
//    public void goForNextScreen(Class mClass) {
//
//        Intent i = new Intent(this, mClass);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(i);
//        this.finish();
//        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
//    }
//
//    public void goForHomeFromLeftToRight(Class mClass) {
//        startActivity(new Intent(this, mClass));
//        this.finish();
//        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//    }
//
//    public void warningBox(String msg) {
//        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
//                .setTitleText("Warning")
//                .setContentText(msg)
//                .setConfirmText("Yes")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sDialog) {
//                        sDialog.dismissWithAnimation();
//                    }
//                })
//                .show();
//    }
//
//
//    // ###### send the class name and return object of that class
//    public Object mGiveMeBackWhatYouIGave(Class mClass) {
//        try {
//            Object object = Class.forName(mClass.getName()).getConstructor().newInstance();
//            return object;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public long convertDateIntoMilliseconds(String mDate) {
//        try {  //2018-04-26 10:28:04"
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//            Date date = null;
//            date = sdf.parse(mDate);
//
//            return date.getTime();
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return Long.parseLong(null);
//        }
//
//    }
//
//    public void removeStatusBar() {
//        // ##########################
//        // Remove top system bar
//        // ##########################
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//    }
//
//    /*  public void updateCameraBearing(GoogleMap googleMap, float bearing) {
//          if (googleMap == null) return;
//          CameraPosition camPos = CameraPosition
//                  .builder(
//                          googleMap.getCameraPosition() // current Camera
//                  )
//                  .bearing(bearing)
//                  .build();
//          googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(camPos));
//      }
//  */
//    // apply font to the item menu in the navigation view in the home screen
//    public void applyFontToMenuItem(MenuItem mi) {
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
//        SpannableString mNewTitle = new SpannableString(mi.getTitle());
//        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
//        mi.setTitle(mNewTitle);
//    }
//
//    public void strikeThroughTextView(TextView mTextView) {
//        mTextView.setPaintFlags(mTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//    }
//
//    /******
//     * Check camera present in system or not
//     ******/
//    public boolean hasCamera() {
//        if (this.getPackageManager().hasSystemFeature(
//                PackageManager.FEATURE_CAMERA_FRONT)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    // diff the soft input keypad
//    public void hideSoftKeyboard() {
//        try {
//            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // set cursor after last character in the edittext on page loading
//    public void setCursorOnLast(EditText editText, int length) {
//
//        Selection.setSelection(editText.getText(), length);
//
//
//    }
//
//    public Bitmap loadBitmapFromView(View v) {
//        if (v.getMeasuredHeight() <= 0) {
//            v.measure(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(b);
//            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//            v.draw(c);
//            return b;
//        }
//        return null;
//    }
//
//    public String getGeoAddressFromLatLong(double latitude, double longitude) {
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            //   String knownName = addresses.get(0).getFeatureName(); // Only if available else return
//
//            return address;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
//
//
//    }
//
//    //      Refresh activity
//    public void refreshScreen(Activity mContext) {
//        mContext.finish();
//        mContext.overridePendingTransition(0, 0);
//        mContext.startActivity(mContext.getIntent());
//        mContext.overridePendingTransition(0, 0);
//
//    }
//
//    public void loadImageWithGlideBitmap(CircleImageView mmImageView, String mUrlPath) {
//        Glide.with(this)
//                .asBitmap()
//                .load(mUrlPath)
//
//                .into(mmImageView);
//
//    }
//
//    public void loadImageWithGlideBitmap(ImageView mmImageView, String mUrlPath) {
//        Glide.with(this)
//                .asBitmap()
//                .load(mUrlPath)
//                .into(mmImageView);
//
//    }
//
//    // Hide status bar in the activity
//    public void hideStatusBar() {
//        View decorView = getWindow().getDecorView();
//        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
//
//    }
//
//    public void setStatusBarTranslucent(boolean makeTranslucent) {
//        if (makeTranslucent) {
//            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        } else {
//            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//    }
//
//    public void setStatusBarColor(int color) {
//
//        Window window = this.getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(this.getResources().getColor(color));
//        }
//
//
//    }
//
//    public void showImage(int imageUri) {
//        try {
//            Dialog builder = new Dialog(this);
//            builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialogInterface) {
//                    //nothing;
//                }
//            });
//
//            ImageView imageView = new ImageView(this);
//            imageView.setImageResource(imageUri);
//            builder.addContentView(imageView, new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT));
//            builder.show();
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//
//    }
//
//    protected boolean isValidMobile(String phone) {
//        return android.util.Patterns.PHONE.matcher(phone).matches();
//    }
//
//    public void shareMessageUsingSMSApp(String mMsg) {
//        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//        smsIntent.setType("vnd.android-dir/mms-sms");
//        //   smsIntent.putExtra("address", "12125551212");
//        smsIntent.putExtra("sms_body", mMsg);
//        startActivity(smsIntent);
//    }
//
//    public File createFile_(String file, String extension) throws IOException {
//        // Create an image file name
//        File storageDir = Environment.getExternalStorageDirectory();
//        File attachmentFile = new File(storageDir, file + "." + extension);
//        attachmentFile.createNewFile();
//        return attachmentFile;
//    }
//
//    public void storeImage_(Bitmap image, File pictureFile) {
//        try {
//            FileOutputStream fos = new FileOutputStream(pictureFile);
//            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public File storeImageWithReturnFile(Bitmap image) {
//        File pictureFile = getOutputMediaFile();
//        if (pictureFile == null) {
//            Log.wtf("img_save",
//                    "Error creating media file, check storage permissions: ");// e.getMessage());
//            return null;
//        } else {
//            try {
//                FileOutputStream fos = new FileOutputStream(pictureFile);
//                image.compress(Bitmap.CompressFormat.PNG, 90, fos);
//                fos.close();
//                return pictureFile;
//            } catch (FileNotFoundException e) {
//                Log.wtf("img_save", "File not found: " + e.getMessage());
//                return null;
//            } catch (IOException e) {
//                Log.wtf("img_save", "Error accessing file: " + e.getMessage());
//                return null;
//            }
//        }
//
//
//    }
//
//    public void showSignatureDialog(InstallerSignatureDialog.InstallerSignatureListener listener) {
//        InstallerSignatureDialog dialog = new InstallerSignatureDialog(this, R.style.Dialog_Default);
//        dialog.setCancelable(true);
//        dialog.setInstallerSignatureListener(listener);
//        dialog.show();
//    }
//
//    /**
//     * Create a File for saving an image or video
//     */
//    private File getOutputMediaFile() {
//        // To be safe, you should check that the SDCard is mounted
//        // using Environment.getExternalStorageState() before doing this.
//        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
//                + "/Android/data/"
//                + getApplicationContext().getPackageName()
//                + "/Files");
//
//        // This location works best if you want the created images to be shared
//        // between applications and persist after your app has been uninstalled.
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
//        File mediaFile;
//        String mImageName = "MI_" + timeStamp + ".jpg";
//        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
//        return mediaFile;
//    }
//
//    public abstract static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
//        GestureDetector mGestureDetector;
//        private OnItemClickListener mListener;
//
//        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
//            mListener = listener;
//            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//            });
//        }
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
//            View childView = view.findChildViewUnder(e.getX(), e.getY());
//            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
//                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
//            }
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
//        }
//
//        protected interface OnItemClickListener {
//            void onItemClick(View view, int position);
//        }
//    }
//}
