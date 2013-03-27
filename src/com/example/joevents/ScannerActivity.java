package com.example.joevents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScannerActivity extends Activity implements OnClickListener {
	
	Button btn;
	String event_id;
	TextView txt_result, txt_scanning;
	View ls;
	public static String result;
	public static int color = 0xff3b5998;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanner);
		Intent intent = getIntent();
		event_id = intent.getStringExtra("data");
		txt_result = (TextView) findViewById(R.id.result);
		txt_scanning = (TextView) findViewById(R.id.scanning);
		ls = findViewById(R.id.scanner_relative_id);
		btn = (Button) findViewById(R.id.StartScanning);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_scanner, menu);
		return true;
	}
	
public void scanSomething() {
		
		try {
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");

			// This flag clears the called app from the activity stack, so users arrive in the expected
			// place next time this application is restarted.
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
		} catch (Exception e) {
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            //  The Intents Fairy has delivered us some data!
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            
	            JSONParser jp = new JSONParser(event_id, contents);
	            txt_result.setTextSize(18);
	            txt_result.setTextColor(color);
	            txt_result.setText(result);
	            txt_scanning.setText("");                 
	           
	            // Handle successful scan
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		txt_scanning.setTextColor(0xff3b5998);
		txt_scanning.setText("Starting Scanner");
		scanSomething();
		
	}

}
