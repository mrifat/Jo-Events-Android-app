package com.example.joevents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.view.View;



public class JSONParser {
	
	public static String type_tag;
	private String hash_url;
	private Object list;
	
	public JSONParser(String e, String p,final View v){
		hash_url = "http://192.168.15.3:3000/api/login";
		String key[] = { "email", "password"};
		String value[] = { e , p };
		list = makeReadyToPost( 2, key, value);
		startRequest(v);		
	}
	
	public JSONParser(String k, String v){
		
		hash_url = "http://192.168.15.3:3000/api/ticket";
		Networking jP = new Networking();
		final JSONObject jObject = jP.getJSONFromUrl(hash_url, makeReadyToPost(k, v));
		String is_valid;
		try {
			is_valid = jObject.getString("valid");
			
			if(is_valid.equals("true")){
				ScannerActivity.result = "This Ticket is valid";
				ScannerActivity.color = 0xff669900;
			}
			else{
				
				ScannerActivity.result = "This Ticket is not valid";
				ScannerActivity.color = 0xffff0000;
			}			
				
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public void startRequest(final View v) {
	
		new Thread() {
            public void run() {
            	Networking jP = new Networking();
            	final JSONObject jObject = jP.getJSONFromUrl(hash_url, list);
				String is_valid;
				try {
					is_valid = jObject.getString("valid");
				
				
				if (is_valid.toString().equals("true"))
				{
					type_tag = jObject.getString("type");					
					parseJSONObject(jObject, v);
					
				}
				else{
					Intent intent = new Intent(v.getContext(), LoginRedirect.class);
					v.getContext().startActivity(intent);				
				}
				
				
            } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
		}.start();
	}
		
	
	public void parseJSONObject(final JSONObject jObject, View v) {
		try {
			
			JSONArray events = jObject.getJSONArray("events");
			JSONObject d = events.getJSONObject(0);
			
			JSONArray names = d.getJSONArray("name");
			JSONArray data = d.getJSONArray("data");
			
			
						
			String current_name, current_data;
			String cName, cData = "";
						
			ArrayList<HashMap<String, String>> tempArray = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < names.length(); i++) {
				current_name = names.getString(i);
				current_data = data.getString(i);
				
				cName = current_name;
				cData = current_data;
				
				HashMap<String, String> cObjectHash = new HashMap<String, String>();
				
				cObjectHash.put("name", cName);
				cObjectHash.put("data", cData);
				
				tempArray.add(cObjectHash);
			}

			if(type_tag.equals("CompanyUser")){
				CompanyUserActivity.eventsArray = tempArray;
				CompanyUserActivity.canClick = true;
				Intent intent = new Intent(v.getContext(), CompanyUserActivity.class);
				v.getContext().startActivity(intent);
			}else{
				UserActivity.eventsArray = tempArray;
				UserActivity.canClick = true;
				Intent intent = new Intent(v.getContext(), UserActivity.class);
				v.getContext().startActivity(intent);
			}					
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object makeReadyToPost(int numberOfKeys, String[] key, String[] value){
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(numberOfKeys);
		for(int i = 0; i < numberOfKeys; i++){
			nameValuePairs.add(new BasicNameValuePair(key[i] ,value[i]));
		}
		
		return nameValuePairs;
		
	
	}
	
public Object makeReadyToPost(String key, String value){
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair(key, value));
		return nameValuePairs;	
	}

}
