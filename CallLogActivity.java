package com.example.getcontact;

import java.sql.Date;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class CallLogActivity extends Activity {
	
	Button 		btnGetLog1 ;

	EditText	callLog ; // 	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_log);
		
		
		btnGetLog1 		=	(Button) 	findViewById(R.id.btnGetLog);
		callLog 		= 	(EditText) 	findViewById(R.id.callLog);
		
		btnGetLog1.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
						   
				callLog.setText(getCallDetails()	)  ;		   
			   }  
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.call_log, menu);
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
}
