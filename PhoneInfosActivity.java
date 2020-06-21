package com.example.getcontact;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneInfosActivity extends Activity {
	
	Button 		btnGetPhoneInfos ;

	EditText	PhoneInfos1 ; // 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_infos);
		
		btnGetPhoneInfos 		=	(Button) 	findViewById(R.id.btnGetPhoneInfos);
		PhoneInfos1 			= 	(EditText) 	findViewById(R.id.PhoneInfos);
		
		btnGetPhoneInfos.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			    // TODO Auto-generated method stub
						   
				   PhoneInfos1.setText(getPhoneInfos()	)  ;		   
			   }  
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone_infos, menu);
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
}
