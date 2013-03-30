package com.example.joevents;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class UserActivity extends Activity{

  public static Boolean canClick = false;

  public static ArrayList<HashMap<String, String>> eventsArray;

  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);

    TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
    // create a new TableRow
    TableRow row = new TableRow(this);
    TextView t = new TextView(this);
    if(eventsArray.size()== 0){
      row = new TableRow(this);
      t = new TextView(this);

      t.setTextSize(21);
      t.setText("You have no tickets");
      row.addView(t);
      table.addView(row);
    }

    for (int i = 0; i < eventsArray.size(); i++) {
      HashMap<String, String> cEvent = eventsArray.get(i);

      row = new TableRow(this);
      t = new TextView(this);

      t.setTextSize(21);

      if((i+1) % 2 == 0)
      {
        t.setBackgroundResource(R.color.fb_blue);
        t.setTextColor(0xffffffff);
      }

      t.setText(cEvent.get("name"));
      row.addView(t);

      row.setTag(cEvent.get("data"));
      table.addView(row);
      RowOnClick.type = "user";
      row.setOnClickListener(new RowOnClick());

    }



  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_user, menu);
    return true;
  }

}
