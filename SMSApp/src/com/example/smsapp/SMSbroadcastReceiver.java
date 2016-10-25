package com.example.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSbroadcastReceiver extends BroadcastReceiver {

	
	private Context context;

	@Override
	public void onReceive(Context content, Intent intent) {
		// TODO Auto-generated method stub
    Bundle bundle = intent.getExtras();
    if(bundle != null)
    {
    	Object[] sms = (Object[]) bundle.get("pdus");
    	String smsMessageStr = "";
    	
    	for(int i = 0; i < sms.length; ++i){
    		
    		SmsMessage message = SmsMessage.createFromPdu((byte[]) sms[i]);
    	
    		String smsBody = message.getMessageBody().toString();
    		String address = message.getOriginatingAddress();
    		
    		smsMessageStr += "SMS From: " + address + "\n";
    		smsMessageStr += smsBody + "\n";
    		
    	}
		Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
		
		SmsInboxActivity inst = SmsInboxActivity.instance();
		inst.ReceivedMessageUpdate(smsMessageStr);
		
    }
		
	}

}
