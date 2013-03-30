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
import android.util.Log;
import android.view.View;



public class JSONParser {

  public static String type_tag;
  private String hash_url;
  private Object list;

  public JSONParser(String e, String p,final View v, String ip){
    Log.v("ip", ip);
    hash_url = "http://192.168.15.7:3000/api/login";
    String key[] = { "email", "password"};
    String value[] = { e , p };
    list = makeReadyToPost( 2, key, value);
    startRequest(v);
  }

  public JSONParser(String k, String v){

    hash_url = "http://192.168.15.7:3000/api/ticket";
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
            parseJSONObject(jObject, v);
          }
          else{
            Intent intent = new Intent(v.getContext(), LoginRedirect.class);
            v.getContext().startActivity(intent);
          }


        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }.start();
  }


  public void parseJSONObject(final JSONObject jObject, View v) {
    try {

      JSONArray events = jObject.getJSONArray("events");
      JSONObject d = events.getJSONObject(0);

      JSONArray tickets = jObject.getJSONArray("tickets");
      JSONObject t = tickets.getJSONObject(0);

      JSONArray e_names = d.getJSONArray("e_name");
      JSONArray e_data = d.getJSONArray("e_data");

      JSONArray names = t.getJSONArray("name");
      JSONArray data = t.getJSONArray("data");

      ArrayList<HashMap<String, String>> tempArray = new ArrayList<HashMap<String, String>>();
      tempArray = fillJSONData(e_names, e_data);
      ArrayList<HashMap<String, String>> tempTArray = new ArrayList<HashMap<String, String>>();
      tempTArray = fillJSONData(names, data);

      UsersActivity.eventsArray = tempArray;
      UsersActivity.ticketsArray = tempTArray;
      //UsersActivity.canClick = true;
      Intent intent = new Intent(v.getContext(), UsersActivity.class);
      v.getContext().startActivity(intent);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private ArrayList<HashMap<String, String>> fillJSONData(JSONArray names,
      JSONArray data) {
    String current_name, current_data;
    String cName, cData = "";


    ArrayList<HashMap<String, String>> tempArray = new ArrayList<HashMap<String, String>>();

    for (int i = 0; i < names.length(); i++) {
      try {
        if (names.getString(i).equals("null"))
          continue;
        else{
          current_name = names.getString(i);
          current_data = data.getString(i);

          cName = current_name;
          cData = current_data;

          HashMap<String, String> cObjectHash = new HashMap<String, String>();

          cObjectHash.put("name", cName);
          cObjectHash.put("data", cData);

          tempArray.add(cObjectHash);
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    return tempArray;
  }

  public Object makeReadyToPost(int numberOfKeys, String[] key, String[] value){

    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(numberOfKeys);
    for(int i = 0; i < numberOfKeys; i++){
      nameValuePairs.add(new BasicNameValuePair(key[i] ,value[i]));
    }

    return nameValuePairs;


  }

  public Object makeReadyToPost(String key, String value){

    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
    nameValuePairs.add(new BasicNameValuePair("event_id", key));
    nameValuePairs.add(new BasicNameValuePair("secret", value));
    return nameValuePairs;
  }

}
