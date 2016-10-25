package com.example.smsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SmsSplashScreen extends Activity {
	
	private int TIMER = 3000;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_service);
		
		Toast.makeText(getApplicationContext(),"SMS APP",Toast.LENGTH_SHORT).show();
		
		new Handler().postDelayed(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SmsSplashScreen.this,SmsInboxActivity.class);
				startActivity(intent);
				finish();
				
			}
			
		}, TIMER);
	}

}
