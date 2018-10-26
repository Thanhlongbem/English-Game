package thanhlongbanh8997.englishforeverybody.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Html;
import android.text.Layout;
import android.text.Spanned;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import thanhlongbanh8997.englishforeverybody.R;

public class CommonUtils {
    private static boolean enableDebug = true;

    public static boolean checkNetworkWithAlert(final Context context) {
        if (!checkNetwork(context)) {

            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_default);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(CommonUtils.ScreenSize(context)[0] * 7 / 8, WindowManager.LayoutParams.WRAP_CONTENT);

            TextView tvSubTitle = dialog.findViewById(R.id.tvSubTitle);
            tvSubTitle.setText(context.getString(R.string.dialog_no_network_sub_title));

            TextView tvDescription = dialog.findViewById(R.id.tvDescription);
            tvDescription.setText(context.getString(R.string.dialog_no_network_description));

            TextView  tvTitle = dialog.findViewById(R.id.tvTitle);
            tvTitle.setText(context.getString(R.string.dialog_no_network_title));

            Button button = dialog.findViewById(R.id.btnSubmit);
            button.setText(context.getString(R.string.dialog_btn_setting));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    context.startActivity(intent);
                }
            });

            dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    ((Activity) context).finish();
                }
            });

            dialog.show();

            return false;
        }
        return true;
    }

    public static int[] ScreenSize(Context context) {
        int[] size = new int[2];
        DisplayMetrics displaymetrics = context.getResources()
                .getDisplayMetrics();
        size[0] = displaymetrics.widthPixels;
        size[1] = displaymetrics.heightPixels;

        return size;
    }

    public static boolean checkNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static String readStringJsonFromAssetFile(String path,
                                                     Context context) {
        InputStream fis;
        try {
            fis = context.getAssets().open(path);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n;
            while ((n = fis.read(buffer)) != -1) {
                fileContent.append(new String(buffer, 0, n));
            }
            return fileContent.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getPixelsFromDP(float dp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int pixels = (int) (metrics.density * dp + 0.5f);
        return pixels;
    }

    public static String timeStringFromLong(long totalSeconds) {
        int seconds = (int) totalSeconds % 60;
        int minutes = (int) (totalSeconds / 60) % 60;
        int hours = (int) totalSeconds / 3600;
        int days = (int) totalSeconds / (24 * 3600);

        StringBuilder builder = new StringBuilder();
        if (days > 0) {
            if (days == 1) {
                builder.append(hours).append(" day");
            } else {
                builder.append(hours).append(" days");
            }

            return builder.toString();
        }

        if (hours > 0) {
            if (hours == 1) {
                builder.append(hours).append(" hour");
            } else {
                builder.append(hours).append(" hours");
            }

        }

        if (minutes > 0) {
            if (minutes == 1) {
                builder.append(minutes).append(" minute");
            } else {
                builder.append(minutes).append(" minutes");
            }
            return builder.toString();
        }

        if (seconds > 0) {
            builder.append(seconds).append(" seconds");
        }

        return builder.toString();
    }

    public static void toggleGPS(Context context, boolean enable) {
        String provider = Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.contains("gps") == enable) {
            return; // the GPS is already in the requested state
        }
        final Intent poke = new Intent();
        poke.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
        poke.setData(Uri.parse("3"));
        context.sendBroadcast(poke);
    }

    public static String formattedDateTime(long time, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.US);
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String getDateStringFrom(Date date, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString, Locale.US);
        return sdf.format(date);
    }


    public static boolean stringIsValid(String str) {
        if (str != null && !str.trim().equals("")
                && !str.toLowerCase().equals("null")) {
            return true;
        }
        return false;
    }

    public static void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]), new File(
                        targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }

    public static String getCurrentTimeWithFormat(String formatString) {
        long currentTime = System.currentTimeMillis();
        return formattedDateTime(currentTime, formatString);
    }

    public static Date getDateFromString(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        return null;
    }

    public static Date getDateWithoutTime(Date date) {
        Date res = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        res = calendar.getTime();
        return res;
    }

    public static String getFormattedTime(long millis, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date(millis));
    }

    public static void setupUI(final Activity activity, View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(activity, innerView);
            }
        }
    }

    public static String getDurationTime(long startTimeInMillis) {
        long currentMillisTime = System.currentTimeMillis() - startTimeInMillis;
        long totalSeconds = currentMillisTime / 1000;
        int seconds = (int) totalSeconds % 60;
        int minutes = (int) (totalSeconds / 60) % 60;
        int hours = (int) totalSeconds / 3600;
        int days = (int) totalSeconds / (24 * 3600);

        StringBuilder builder = new StringBuilder();
        String minutesSeconds = String.format("%02d:%02d", minutes, seconds);
        if (hours > 0) {
            String hoursStr = String.format("%02d", hours);
            builder.append(hoursStr).append(":");
        }

        if (days > 0) {
            String daysStr = String.format("%02d", days);
            builder.append(daysStr).append(":");
        }

        builder.append(minutesSeconds);

        return builder.toString();
    }

    public static boolean checkTexViewEllipsized(TextView tv) {
        Layout l = tv.getLayout();
        if (l != null) {
            int lines = l.getLineCount();
            if (lines > 0) {
                if (l.getEllipsisCount(lines - 1) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static long getMillisTimeStr(String time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            long millis = sdf.parse(time).getTime();
            return millis;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getListViewHeight(ListView list) {
        ListAdapter adapter = list.getAdapter();

        int listviewHeight = 0;

        list.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED));

        listviewHeight = list.getMeasuredHeight() * adapter.getCount()
                + (adapter.getCount() * list.getDividerHeight());

        return listviewHeight;
    }

    public static String getBase64StringFromFile(String path) {
        File f = new File(path);
        if (f.exists()) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                FileInputStream fis = new FileInputStream(f);

                byte[] buf = new byte[1024];
                int n;
                while (-1 != (n = fis.read(buf))) {
                    baos.write(buf, 0, n);
                }

                byte[] videoBytes = baos.toByteArray();
                String stringBase64 = Base64.encodeToString(videoBytes,
                        Base64.DEFAULT);
                fis.close();
                baos.close();
                return stringBase64;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void printLogE(Class<?> c, String log) {
        Log.e(c.getSimpleName(), log);
    }

    public static void printLogD(Class<?> c, String log) {
        Log.d(c.getSimpleName(), log);
    }

    public static void printLogI(Class<?> c, String log) {
        Log.i(c.getSimpleName(), log);
    }

    public static void printLogW(Class<?> c, String log) {
        Log.w(c.getSimpleName(), log);
    }

    public static void printLogRequest(Class<?> c, String url, String data) {
        if (enableDebug) {
            Log.e(c.getSimpleName(), "url/data: " + url + "/" + data);
        }
    }

    public static File getTempFileFromBitmap(Context context, Bitmap bm) {
        File f = new File(Environment.getExternalStorageDirectory(),
                "tms-temp.png");
        if (bm != null) {
            try {
                FileOutputStream out = new FileOutputStream(f);
                bm.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

//    public static Dialog showOkCancelDialog(Context context, String title,
//                                            String message, final View.OnClickListener okClickListener) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.setPositiveButton("Ok", okClickListener);
//        builder.setCancelable(false);
//        builder.setNegativeButton("Cancel", null);
//        Dialog dialog = builder.create();
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
//        return dialog;

//    }

    public static Dialog showServerError(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ERROR");
        builder.setMessage("Have an problem with server now, please try again late");
        builder.setPositiveButton("Ok", null);
        builder.show();
        return builder.create();
    }



    public static Dialog showOkLoadAgainDialog(Context context, String title,
                                               String message, DialogInterface.OnClickListener okClickListener, DialogInterface.OnClickListener cancelClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", okClickListener);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", cancelClickListener);
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        return dialog;
    }

    public static boolean gpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            return false;
        }

        return true;
    }

//    public static String getEmail(Context context) {
//        AccountManager accountManager = AccountManager.get(context);
//        Account account = getAccount(accountManager);
//
//        if (account == null) {
//            return null;
//        } else {
//            return account.name;
//        }
//    }

//    private static Account getAccount(AccountManager accountManager) {
//        Account[] accounts = accountManager.getAccountsByType("com.google");
//        Account account = null;
//        if (accounts.length > 0) {
//            account = accounts[0];
//        }
//        return account;
//    }

    // WeekDay time
    public static Date getNextDate(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, days);
        return c.getTime();
    }

    /**
     * @param day - two digits: 01,28
     * @return
     */
    public static String getDayWithSuffix(String day) {
        int secondDigit = Integer.valueOf(day.subSequence(1, 2).toString());
        if (day.charAt(0) == '0') {
            day = day.replace("0", "");
        }

        StringBuilder builder = new StringBuilder();
        switch (secondDigit) {
            case 1:// Day 01, 11, 21, 31
                builder.append(day).append("st");
                break;
            case 2: // Day 22nd
                builder.append(day).append("nd");
                break;
            case 3: // Day 23rd
                builder.append(day).append("rd");
                break;
            default:
                builder.append(day).append("th");
                break;
        }
        return builder.toString();
    }

    public static void sendEmail(Context context, String email, String subject,
                                 String content) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});

        if (stringIsValid(subject)) {
            i.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        if (stringIsValid(content)) {
            i.putExtra(Intent.EXTRA_TEXT, content);
        }

        try {
            context.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static void openUrl(Context context, String url) {
        if (stringIsValid(url)) {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            context.startActivity(browserIntent);
        }
    }

    public static void openAppInStore(Context context, String appPackageName) {
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static String getPhoneNumber(Context context) {
//        TelephonyManager tMgr = (TelephonyManager) context
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        String phoneNumber = tMgr.getLine1Number();
//        if (!stringIsValid(phoneNumber)) {
//            phoneNumber = tMgr.getSubscriberId();
//        }
//        Log.e("Phone", phoneNumber + "");
//        return phoneNumber;
//    }

    public static String getDeviceID(Context context) {
        String deviceID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceID;
    }

//    public static String getUsername(Context context) {
//        AccountManager manager = AccountManager.get(context);
//        Account[] accounts = manager.getAccountsByType("com.google");
//        List<String> possibleEmails = new LinkedList<String>();
//
//        for (Account account : accounts) {
//            possibleEmails.add(account.name);
//        }
//
//        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
//            String email = possibleEmails.get(0);
//            String[] parts = email.split("@");
//            if (parts.length > 0 && parts[0] != null)
//                return parts[0];
//            else
//                return null;
//        } else
//            return null;
//    }

    public static String getFileNameFromUrl(String url) {
        int index = url.lastIndexOf('?');
        String filename;
        int length = url.length();
        String extension = url.substring(length - 4, length);
        if (index > 1) {
            filename = url.substring(url.lastIndexOf('/') + 1, index);
        } else {
            filename = url.substring(url.lastIndexOf('/') + 1);
        }

        if (filename == null || "".equals(filename.trim())) {
            filename = UUID.randomUUID() + extension;
        }
        return filename;
    }

    public static boolean checkEmailValid(String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean checkPhoneValid(String phone) {
        return phone != null && (phone.startsWith("+84") || phone.startsWith("0"));
    }

    public static String getFormattedTime(String timeString,
                                          String fromPattern, String toPattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromPattern);
        try {
            Date date = sdf.parse(timeString);
            sdf = new SimpleDateFormat(toPattern);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return null;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static byte[] convertFileToByteArray(File f) {
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(f);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024 * 8];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }

            byteArray = bos.toByteArray();
            inputStream.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static void unzip(File zipFile, File destDir) {
        try {
            FileInputStream fin = new FileInputStream(zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                Log.v("Decompress", "Unzipping " + ze.getName());

                if (ze.isDirectory()) {
                    File f = new File(destDir, ze.getName());
                    if (!f.isDirectory()) {
                        f.mkdirs();
                    }
                } else {
                    FileOutputStream fout = new FileOutputStream(
                            destDir.getAbsolutePath() + "/" + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }

                    zin.closeEntry();
                    fout.close();
                }

            }
            zin.close();
        } catch (Exception e) {
            Log.e("Decompress", "unzip", e);
        }
    }

    public static String getValidString(String inputString) {
        if (inputString == null || inputString.equals("null")) {
            return "";

        }
        return inputString;
    }

    public static double getDoubleFromString(String doubleString) {
        double value = 0;
        try {
            value = Double.valueOf(doubleString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getFacebookUserAvatar(String facebookId) {
        return "https://graph.facebook.com/" + facebookId + "/picture?height=250&width=250";
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/348466678861138"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/xemtivimienphi"));
        }
    }

    public static void shareApp(Activity context) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + context.getPackageName());
        context.startActivity(Intent.createChooser(sharingIntent, "Choose one"));
    }

    public static void shareAppToFacebook(Activity context) {
        String urlToShare = "http://stackoverflow.com/questions/7545254";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
// intent.putExtra(Intent.EXTRA_SUBJECT, "Foo bar"); // NB: has no effect!
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

// See if official Facebook app is found
        boolean facebookAppFound = false;
        List<ResolveInfo> matches = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

// As fallback, launch sharer.php in a browser
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        context.startActivity(intent);
    }

//    public static   void shareFacebook(Activity context){
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
//        if (intent != null) {
//            // The application exists
//            Intent shareIntent = new Intent();
//            shareIntent.setAction(Intent.ACTION_SEND);
//            shareIntent.setPackage("com.facebook.katana");
//
//            shareIntent.putExtra(android.content.Intent.EXTRA_TITLE, "");
//            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + context.getPackageName());
//            // Start the specific social application
//            context.startActivity(shareIntent);
//        } else {
//            // The application does not exist
//            // Open GooglePlay or use the default system picker
//        }
//    }

//    public static boolean checkPlayServices(Activity context) {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(context, resultCode, 9000)
//                        .show();
//            } else {
//                Log.i("Google Play Services", "This device is not supported.");
//            }
//            return false;
//        }
//        return true;
//    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static String removeHtmlTags(String html) {
        if (html != null) {
            html = html.replaceAll("<(.*?)\\>", " ");//Removes all items in brackets
            html = html.replaceAll("<(.*?)\\\n", " ");//Must be undeneath
            html = html.replaceFirst("(.*?)\\>", " ");//Removes any connected item to the last bracket
            html = html.replaceAll("&nbsp;", " ");
            html = html.replaceAll("&amp;", " ");
            html = html.trim();
        }
        return html;
    }

    public static void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", text);
        clipboard.setPrimaryClip(clip);
    }

    public static void disableView(final Activity activity, View view) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                disableView(activity, innerView);
            }
        } else {
            view.setEnabled(false);
        }
    }
    public static void hideKeyBoard(final Activity activity, View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyBoard(activity, innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(new View(activity).getWindowToken(), 0);
    }

    public static String keyHashGenerate(Context context) {
        String keyHash = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("KeyHash:", keyHash);
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
        return keyHash;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static String convertTime(String timeOld) {
        final String NEW_FORMAT = "HH:mm";
        final String OLD_FORMAT = "HH:mm:ss";
        String formatDate;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = null;
        try {
            d = sdf.parse(timeOld);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sdf.applyPattern(NEW_FORMAT);
        return sdf.format(d);
    }

    public static Spanned formatHtmlString(String html) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(html);
        }
    }

    public static int compare(String version1, String version2){

        String ver1[] = version1.split("\\.");
        String ver2[] = version2.split("\\.");
        int counter = 0;
        int len1= ver1.length;
        int len2= ver2.length;

        for (String v:ver1) {

            if(len1>0 && len2>0 && v.compareTo(ver2[counter])>0 ){
                return 1;
            } else {
                if (len1>0 && len2>0 && v.compareTo(ver2[counter])<0) {
                    return -1;
                } else {
                    len1--;
                    len2--;
                    counter++;
                }
            }
        }

        if(ver1.length > ver2.length){
            return 1;
        } else if(ver1.length < ver2.length){
            return -1;
        }

        return 0;
    }

    public static void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }

        if (view.getLayoutParams() instanceof LinearLayout.MarginLayoutParams) {
            LinearLayout.MarginLayoutParams p = (LinearLayout.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }

        if (view.getLayoutParams() instanceof RelativeLayout.MarginLayoutParams) {
            RelativeLayout.MarginLayoutParams p = (RelativeLayout.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }

        if (view.getLayoutParams() instanceof FrameLayout.MarginLayoutParams) {
            FrameLayout.MarginLayoutParams p = (FrameLayout.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }

    }
}

