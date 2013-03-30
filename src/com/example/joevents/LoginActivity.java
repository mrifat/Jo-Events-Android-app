package com.example.joevents;




import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

  @SuppressLint("NewApi")
  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  public class LoginActivity extends Activity implements OnClickListener{
    Button login;
    TextView result;

    TextView email, password;
    public static String e;
    public static String p;
    public static String ip;


    /** Called when the activity is first created. */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);


      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);

      if (!isNetworkAvailable()){
        Toast.makeText(getApplicationContext(), "No Network Available\n Please connect to a network", Toast.LENGTH_LONG).show();
      }

      login = (Button) findViewById(R.id.btn_login);
      login.setOnClickListener(this);

      email = (TextView) findViewById(R.id.txt_email);
      password = (TextView) findViewById(R.id.txt_password);
      result = (TextView) findViewById(R.id.lbl_top);
      result.setText("Please login first");
      ip = getLocalIpAddress();
      Log.v("ip", ip);
    }

    public String getLocalIpAddress() {
      try {
        for (Enumeration<NetworkInterface> en = NetworkInterface
            .getNetworkInterfaces(); en.hasMoreElements();) {
          NetworkInterface intf = en.nextElement();
          for (Enumeration<InetAddress> enumIpAddr = intf
              .getInetAddresses(); enumIpAddr.hasMoreElements();) {
            InetAddress inetAddress = enumIpAddr.nextElement();
            if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {

              return inetAddress.getHostAddress().toString();

            }
              }
            }
      } catch (SocketException ex) {
        Log.e("Exception", ex.toString());
      }
      return "";
    }



    @Override
    public void onClick(View v) {
      if (!isNetworkAvailable()){
        System.exit(0);
      }

      result.setText("Logging in Please wait...");
      e = email.getText().toString();
      p = password.getText().toString();
      JSONParser jp = new JSONParser( e, p, v, ip);

    }

    public boolean isNetworkAvailable() {
      ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      // if no network is available networkInfo will be null
      // otherwise check if we are connected
      if (networkInfo != null && networkInfo.isConnected()) {
        return true;
      }
      return false;
    }
  }
