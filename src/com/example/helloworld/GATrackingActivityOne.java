package com.example.helloworld;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Logger.LogLevel;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.StandardExceptionParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class GATrackingActivityOne extends Activity{

	private EasyTracker easyTracker = null;
	private static GoogleAnalytics mGa = null;
	
	  // Dispatch period in seconds.
	  private static final int GA_DISPATCH_PERIOD = 30;

	  // Prevent hits from being sent to reports, i.e. during testing.
	// When true, dryRun flag prevents data from being processed with reports.
	  private static final boolean GA_IS_DRY_RUN = false;

	  // GA Logger verbosity.
	  private static final LogLevel GA_LOG_VERBOSITY = LogLevel.VERBOSE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ga_tracking_activity);

		// Initialize a Easy tracker
		easyTracker = EasyTracker.getInstance(GATrackingActivityOne.this);
		 mGa = GoogleAnalytics.getInstance(this);
		 
		// Set dispatch period.
	    GAServiceManager.getInstance().setLocalDispatchPeriod(GA_DISPATCH_PERIOD);

	    // Set dryRun flag.
	    mGa.setDryRun(GA_IS_DRY_RUN);

	    // Set Logger verbosity.
	    mGa.getLogger().setLogLevel(GA_LOG_VERBOSITY);


		findViewById(R.id.trackEvent).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// MapBuilder.createEvent().build() returns a Map of event fields and values
				// that are set and sent with the hit.
				easyTracker.send(MapBuilder
						.createEvent("Click On Event",     // Event category (required)...ui_action
								"S_1 Button Pressed",  // Event action (required)...button_press
								"Event_button",   // Event label
								null)            // Event value
								.build()
						);


			}
		});

		findViewById(R.id.trackCrash).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try {
					int i = 3/0;
				} catch (ArithmeticException e) {
					e.printStackTrace();
					// StandardExceptionParser is provided to help get meaningful Exception descriptions.
					easyTracker.send(MapBuilder
							.createException(new StandardExceptionParser(GATrackingActivityOne.this, null) // Context and optional collection of package names
							// to be used in reporting the exception.
							.getDescription(Thread.currentThread().getName(),    // The name of the thread on which the exception occurred.
									e),                                  // The exception.
									false).build() );  }                                          // False indicates a fatal exception
			}
		});
		
		
		findViewById(R.id.second_screen).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
			startActivity(new Intent(GATrackingActivityOne.this, GATrackingActivityTwo.class));	
				
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		 EasyTracker.getInstance(this).activityStart(this);
	}
	@Override
	protected void onStop() {
		super.onStop();
		 EasyTracker.getInstance(this).activityStop(this);
	}
}
