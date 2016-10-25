package com.example.smsapp;



import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button send;
	EditText phonenumber;
	EditText message;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		send = (Button)findViewById(R.id.sendSMSbutton);
		phonenumber = (EditText)findViewById(R.id.EditMobileNumber);
		message = (EditText)findViewById(R.id.EditSMS);
		
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String recipient = phonenumber.getText().toString();
				String sms = message.getText().toString();
				
				try{
					SmsManager smsmanager = SmsManager.getDefault();
					smsmanager.sendTextMessage(recipient, null, sms, null, null);
					Toast.makeText(getApplicationContext(),"SMS Sent Successfully", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					
					Toast.makeText(getApplicationContext(), "SMS failed,Try Again", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
