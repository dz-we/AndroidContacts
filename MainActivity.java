package com.example.getcontact;

import java.sql.Date;

//import com.example.appmobiletpgallery.R;

	

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;






public class MainActivity extends Activity {
	
	Button 		btnGetLog, btnPhoneInfos ;
	Button 		btnSms ;
	TextView 	result ; //
	EditText	listVW ; // 



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnGetLog 			=	(Button) 	findViewById(R.id.btnCall);
		btnSms 				=	(Button) 	findViewById(R.id.btnGoSms);
		btnPhoneInfos 		=	(Button) 	findViewById(R.id.GoToPhoneInfos);
		
		result 		= 	(TextView) 	findViewById(R.id.callLog);
		listVW 		= 	(EditText) 	findViewById(R.id.editText1);
		 		
		
		btnGetLog.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
				
				   Intent myIntent = new Intent(getApplicationContext(), CallLogActivity.class);
	                startActivity(myIntent);	   
			   }  
		});
		
		
		
		btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), SecureMessagesActivity.class);
                startActivity(myIntent);
            }
        });
		
		btnPhoneInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), PhoneInfosActivity.class);
                startActivity(myIntent); 
            }
        });
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String getCallDetails() { 

        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number 		= managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type 		= managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date 		= managedCursor.getColumnIndex(CallLog.Calls.DATE);             
        int duration 	= managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        
        
        int name 		= managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
             
        
        sb.append("Call Details :");
        while (managedCursor.moveToNext()) {
        	String phNumber = managedCursor.getString(number);
        	
        	String phName = managedCursor.getString(name);
        	
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
            case CallLog.Calls.OUTGOING_TYPE:
                dir = "OUTGOING";
                break;

            case CallLog.Calls.INCOMING_TYPE:
                dir = "INCOMING";
                break;

            case CallLog.Calls.MISSED_TYPE:
                dir = "MISSED";
                break;
            }
            sb.append( 
            		
            		 "\nPhone Name:--- " + phName
            		
            		 + "\nPhone Number:--- " + phNumber
            		
            		+ " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
            
            
            sb.append("\n----------------------------------");

        }
        
        managedCursor.close();
        return sb.toString();

    }


	private String getPhoneInfos() {
		
			String 	phInf 			= null ;
			String 	manufacturer 	= Build.MANUFACTURER;
			String 	model 			= Build.MODEL;
			int 	api 			= Build.VERSION.SDK_INT;
			String 	AndroidVer 		= Build.VERSION.RELEASE;
		   
			TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId();
		   
		   phInf = (" Manufacturer -----: " 		+ manufacturer
		            + " \n Model -------: "			+ model
		            + " \n API----------: " 		+ api
		            + " \n Android Ver--: " 		+ AndroidVer
		            + " \n Phone IMEI---: " 		+ imei
				   );
		return phInf;
		
	}
	
	private String getAllSmss() {
		
		// public static final String INBOX = "content://sms/inbox";
		// public static final String SENT = "content://sms/sent";
		// public static final String DRAFT = "content://sms/draft";
		Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
		
		String msgData = "";
		
		if (cursor.moveToFirst()) { // must check the result to prevent exception
		    do {
		        
		       for(int idx=0;idx<cursor.getColumnCount();idx++)
		       {
		           msgData += "\n " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
		       }
		       // use msgData
		    } while (cursor.moveToNext());
		} else {
		   // empty box, no SMS
		}
		
	return msgData;
	
}
	

	
}
