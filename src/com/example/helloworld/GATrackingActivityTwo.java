package com.example.helloworld;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.os.Bundle;

public class GATrackingActivityTwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ga_tracking2_activity);
		
		
	}
	
	
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}
	
	@Override
	protected void onStop() {
		EasyTracker.getInstance(this).activityStart(this);
		super.onStop();
	}
	
}
