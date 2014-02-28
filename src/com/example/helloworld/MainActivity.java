package com.example.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private TextView google_analytics_example_tv,notification_example_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		google_analytics_example_tv = (TextView)findViewById(R.id.google_analytics_example_tv);
		notification_example_tv = (TextView)findViewById(R.id.notification_example_tv);



		google_analytics_example_tv.setOnClickListener(this);
		notification_example_tv.setOnClickListener(this);



	}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub

		if(view == google_analytics_example_tv){
			startActivity(new Intent(MainActivity.this, GATrackingActivityOne.class));

		}
		if(view == notification_example_tv){
			startActivity(new Intent(MainActivity.this, NotificationExampleActivity.class));

		}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
