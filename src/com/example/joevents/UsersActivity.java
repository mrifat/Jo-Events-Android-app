package com.example.joevents;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

  @SuppressLint("NewApi")
  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  public class UsersActivity extends Activity implements OnClickListener {
    Button events, tickets;
    public static ArrayList<HashMap<String, String>> eventsArray, ticketsArray;

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_users);

      events = (Button) findViewById(R.id.btn_events);
      tickets = (Button) findViewById(R.id.btn_tickets);
      events.setOnClickListener(this);
      tickets.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.activity_users, menu);
      return true;
    }

    public void onClick(View v) {
      Intent intent;
      switch(v.getId())
      {
        case R.id.btn_events:
          CompanyUserActivity.eventsArray = eventsArray;
          CompanyUserActivity.canClick = true;
          intent = new Intent(v.getContext(), CompanyUserActivity.class);
          v.getContext().startActivity(intent);
          break;
        case R.id.btn_tickets:
          UserActivity.eventsArray = ticketsArray;
          UserActivity.canClick = true;
          intent = new Intent(v.getContext(), UserActivity.class);
          v.getContext().startActivity(intent);
          break;
        default:
          throw new RuntimeException("Unknown button ID");
      }

    }

  }
