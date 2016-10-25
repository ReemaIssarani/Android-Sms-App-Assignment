package com.example.smsapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SmsInboxActivity extends Activity implements OnItemClickListener {

	private static SmsInboxActivity instance;
	ArrayList<String> smslist = new ArrayList<String>();
	ListView smslistView;
	ArrayAdapter<String> arrayAdapter;
	
	public static SmsInboxActivity instance(){
		return instance;
	} 
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		instance = this;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inbox_sms);
		smslistView = (ListView) findViewById(R.id.messagelist);
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,smslist);
		smslistView.setAdapter(arrayAdapter);
		smslistView.setOnItemClickListener(this);
		
		UpdateSmsInbox();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.sms_actions, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
	
		switch (item.getItemId()){
		
		case R.id.action_compose:
			compose();
			
		default:
			return super.onOptionsItemSelected(item);
		
		}
	
	}
	
	
	private void compose() {
	
		Intent i = new Intent(SmsInboxActivity.this,MainActivity.class);
		startActivity(i);
		
	}
	
	
    public void UpdateSmsInbox() {
    	
		ContentResolver contentresolver = getContentResolver();
		Cursor InboxCursor = contentresolver.query(Uri.parse("content://sms/inbox"),null,null,null,null);
		int indexofmessage = InboxCursor.getColumnIndex("body");
		int indexofaddress = InboxCursor.getColumnIndex("address");
		if(indexofmessage<0 || !InboxCursor.moveToFirst())return;
		arrayAdapter.clear();
		do{
			String str = "SMS From: " + InboxCursor.getString(indexofaddress) + "\n" + InboxCursor.getString(indexofmessage) + "\n";
		    arrayAdapter.add(str); 
		}while(InboxCursor.moveToNext());
    	
	}
    
    public void ReceivedMessageUpdate(final String smsMessage) {
		
    	arrayAdapter.insert(smsMessage,0);
    	arrayAdapter.notifyDataSetChanged();
    	
	}
    
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		// TODO Auto-generated method stub
		try{
			String[] smsMessages = smslist.get(pos).split("\n");
			String address = smsMessages[0];
			String smsMessage = "";
			for(int i = 1; i < smsMessages.length; ++i)
			{
				smsMessage += smsMessages[i];
			}
			
			String smsMessageStr = address + "\n";
			smsMessageStr += smsMessage;
			Toast.makeText(this, smsMessageStr, Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
