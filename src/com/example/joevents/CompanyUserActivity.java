package com.example.joevents;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;




@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class CompanyUserActivity extends Activity{

	public static Boolean canClick = false;

	public static ArrayList<HashMap<String, String>> eventsArray;
	
	Button btn;
	

	@SuppressLint("ResourceAsColor")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company_user);
		
		TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
		// create a new TableRow
		TableRow row = new TableRow(this);
		TextView t = new TextView(this);
				
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
			RowOnClick.type = "company user";
			row.setOnClickListener(new RowOnClick());
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_company_user, menu);
		return true;
	}
}
