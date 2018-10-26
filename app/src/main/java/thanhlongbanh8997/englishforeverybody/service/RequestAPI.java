package thanhlongbanh8997.englishforeverybody.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.security.KeyStore;

import thanhlongbanh8997.englishforeverybody.utils.CommonUtils;
import thanhlongbanh8997.englishforeverybody.utils.DialogUtils;
import thanhlongbanh8997.englishforeverybody.R;


public class RequestAPI {


    public static void get(final Context context, final String url, final RequestParams params, final ProgressDialog dialog, final RequestComplete requestComplete) {

        if (CommonUtils.checkNetworkWithAlert(context)) {
            final AsyncHttpClient client = new AsyncHttpClient();

            if (url.startsWith("https://")) {
                try {
                    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    trustStore.load(null, null);
                    MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                    sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                    client.setSSLSocketFactory(sf);
                }
                catch (Exception e) {
                }
            }

            client.setMaxRetriesAndTimeout(3, 10000);

            client.get(url, params, new TextHttpResponseHandler() {

                @Override
                public void onStart() {
                    if (dialog != null) {
                        dialog.show();
                    } else {
                        super.onStart();
                    }
                }

                @Override
                public void onFailure(final int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    final Activity activity =((Activity) context);
                    if (!activity.hasWindowFocus()) {
                        throwable.printStackTrace();
                        DialogUtils.showOKWarningDialog(context, R.string.dialog_error_title, R.string.dialog_server_error, null);
                    }
                }


                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (requestComplete != null) {
                        requestComplete.onComplete(true, 200, "", responseString);
                    }
                }

                @Override
                public void onFinish() {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    } else {
                        super.onFinish();
                    }
                }
            });
        }
    }

}

