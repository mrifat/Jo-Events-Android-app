package com.example.joevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginRedirect extends Activity implements OnClickListener {

  Button back;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_redirect);

    back = (Button) findViewById(R.id.login_back);
    back.setOnClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_login_redirect, menu);
    return true;
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(v.getContext(), LoginActivity.class);
    v.getContext().startActivity(intent);

  }

}
