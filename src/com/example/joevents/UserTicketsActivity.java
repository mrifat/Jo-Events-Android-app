package com.example.joevents;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class UserTicketsActivity extends Activity implements OnClickListener{

  Button backButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_tickets);

    backButton = (Button) findViewById(R.id.back);
    backButton.setOnClickListener(this);

    Intent intnt = getIntent();
    String secret = intnt.getStringExtra("data");

    getImage(secret);
  }

  private void getImage(String secret) {

    URL newUrl;

    try {
      newUrl = new URL("https://chart.googleapis.com/chart?cht=qr&chl="+secret+"&choe=UTF-8&chs=200x200");
      Bitmap mIcon_val = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());

      ImageView qr_image = (ImageView)findViewById(R.id.qr_image);
      qr_image.setImageBitmap(mIcon_val);
    } catch (MalformedURLException  e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_user_tickets, menu);
    return true;
  }

  @Override
  public void onClick(View v) {

    Intent intent = new Intent(this, UserActivity.class);
    startActivity(intent);

  }

}
