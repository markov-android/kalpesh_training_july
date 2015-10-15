package com.intelematics.interview.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.JsonReader;
import android.util.Log;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 */
public class ConnectionManager {
	private HttpURLConnection httpConnection = null;
	private URL url = null;
	private InputStream is = null;
	private JsonReader jsonReader = null;
	
	private Context context;
	
	
	public ConnectionManager(Context context, String requestURL){
		this.context = context;
		
		try {
			url = new URL(requestURL);
			
		} catch (MalformedURLException e) {
		}
	}

	private boolean isNetworkAvailable() {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			Log.d("CATLOG", networkInfo.isConnected() + " is it true");
			return (networkInfo != null && networkInfo.isConnected());
		}
		catch(Exception ex) {
			Log.d("CATLOG", ex.getMessage());
		}

		return false;
	}

	
	public JsonReader requestJson(){
		try {
			Log.d("CATLOG", "New Json Reader to enter");
			jsonReader = new JsonReader(new InputStreamReader(request(), "UTF-8"));
			Log.d("CATLOG", "New Json Reader");
		} catch (UnsupportedEncodingException e) {
			Log.e("CATLOG", e.getMessage());
		}
		
		return jsonReader;
	}
	
	public InputStream request(){

	    try {
	        httpConnection = (HttpURLConnection) url.openConnection();
			Log.d("CATLOG", "New HTTPURLConnection Opened");
	        int responseCode = httpConnection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            is = httpConnection.getInputStream();
	            
	        }
	        
	    } catch (Exception ex) {
			Log.d("CATLOG", ex.getMessage());
	    }
	    
	    return is;
	}
	
	public void closeConnection(){
	    try{
	    	if(is != null){
	    		is.close();
	    	}
	    	if(httpConnection != null){
	    		httpConnection.disconnect();
	    	}
		} catch(Exception e){
		}
	}
	
	
	public ByteArrayBuffer requestImage(){
		HttpURLConnection httpConnection = null;
		ByteArrayBuffer baf = new ByteArrayBuffer(1024);
		BufferedInputStream bis = null;
		if(!isNetworkAvailable()){
			return null;
		}
		
	    try {
	        httpConnection = (HttpURLConnection) url.openConnection();
			Log.d("CATLOG", "httpConnection");
	        //int responseCode = httpConnection.getResponseCode();
			Log.d("CATLOG", "Getting response code");
	        //if (responseCode == HttpURLConnection.HTTP_OK) {
	        	bis = new BufferedInputStream(httpConnection.getInputStream(), 1024);
				Log.d("CATLOG", "bis gotten");
				int current = 0;
				while ((current = bis.read()) != -1) {
					baf.append((byte) current);
				}

	            
	        //}
	        
	    } catch (Exception ex) {
			Log.d("CATLOG", "Sub try" + ex.getLocalizedMessage());
	    }
		Log.d("CATLOG", "Returning Baf");
	    return baf;
	} 
	
}
