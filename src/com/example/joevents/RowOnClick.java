package com.example.joevents;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;

class RowOnClick implements OnClickListener {
	
	public static String type = "";
	
	@Override
	public void onClick(View v) {
		if(type.equals("company user")){
			if (CompanyUserActivity.canClick) {
				// TODO Auto-generated method stub
				TableRow tr = (TableRow) v;
				TextView t = (TextView) tr.getChildAt(0);
				t.setTextColor(0xff888888);
				Intent intent = new Intent(v.getContext(), ScannerActivity.class);
				intent.putExtra("data", "" + tr.getTag());
				v.getContext().startActivity(intent);
			}
		}
		else if(type.equals("user")){
			if (UserActivity.canClick) {
				// TODO Auto-generated method stub
				TableRow tr = (TableRow) v;
				TextView t = (TextView) tr.getChildAt(0);
				t.setTextColor(0xff888888);
				Intent intent = new Intent(v.getContext(), UserTicketsActivity.class);
				intent.putExtra("data", "" + tr.getTag());
				v.getContext().startActivity(intent);
			}
		}
	}
}