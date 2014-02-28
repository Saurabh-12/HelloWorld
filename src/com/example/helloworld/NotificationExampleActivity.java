package com.example.helloworld;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NotificationExampleActivity extends Activity implements OnClickListener {
	TextView basic_example_tv,bigtext_example_tv,bigpic_example_tv,inbox_example_tv;
    public final static int NOTI_MODE_BASIC = 0;
    public final static int NOTI_MODE_BIG_TEXT = 1;
    public final static int NOTI_MODE_BIG_PIC = 2;
    public final static int NOTI_MODE_INBOX = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_example_activity);
		
		basic_example_tv = (TextView)findViewById(R.id.basic_example_tv);
		bigtext_example_tv = (TextView)findViewById(R.id.bigtext_example_tv);
		bigpic_example_tv = (TextView)findViewById(R.id.bigpic_example_tv);
		inbox_example_tv = (TextView)findViewById(R.id.inbox_example_tv);
		
		basic_example_tv.setOnClickListener(this);
		bigtext_example_tv.setOnClickListener(this);
		bigpic_example_tv.setOnClickListener(this);
		inbox_example_tv.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == basic_example_tv){
			setNotification(NOTI_MODE_BASIC);
		}
		if(view == bigtext_example_tv){
			setNotification(NOTI_MODE_BIG_TEXT);
		}
		if(view == bigpic_example_tv){
			setNotification(NOTI_MODE_BIG_PIC);
		}
		if(view == inbox_example_tv){
			setNotification(NOTI_MODE_INBOX);
		}
	}
	
    private void setNotification(int mode){
    	int mId = 1;
    	String contentTitle = "Notification Test";
    	String contentText = "Service Content for basic notification";
    	String contentInfo = "Service Info";
    	String contentText2 = "Service Content2";
    	// Using NotificationCompat to create the notification builder can use
    	// the new features introduced after API level 4 without compatibility  
    	// problem with lower API level
    	NotificationCompat.Builder mBuilder =
    	        new NotificationCompat.Builder(this)
    	        .setSmallIcon(R.drawable.ic_launcher)
    	        .setContentTitle(contentTitle)
    	        .setContentText(contentText)
    	        .setContentInfo(contentInfo);
    	
    	// A notification's big view appears only when the notification is expanded, 
    	// which happens when the notification is at the top of the notification 
    	// drawer, or when the user expands the notification with a gesture. 
    	// Expanded notifications are available starting with Android 4.1.
    	switch (mode){
    	case NOTI_MODE_BIG_TEXT:
    		NotificationCompat.BigTextStyle bigTextStyle 
    			= new NotificationCompat.BigTextStyle();
    		bigTextStyle.bigText("Android is a Linux-based operating system designed primarily for touchscreen mobile devices such as smartphones and tablet computers.\nInitially developed by Android, Inc., which Google backed financially and later bought in 2005,[12] Android was unveiled in 2007 along with the founding of the Open Handset Alliance:\n a consortium of hardware, software, and telecommunication companies devoted to advancing open standards for mobile devices.[13] \nThe first Android-powered phone was sold in October 2008");
    		// summary is displayed replacing the position of contentText
    		// if summary is not set, contentInfo will not be displayed too
    		bigTextStyle.setSummaryText(contentText2);    	    		
    		// Moves the big view style object into the notification object.
    		mBuilder.setStyle(bigTextStyle);
	    	break;
    	case NOTI_MODE_BIG_PIC:
    		NotificationCompat.BigPictureStyle bigPicStyle 
    			= new NotificationCompat.BigPictureStyle();
    		Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.interact);
    		bigPicStyle.bigPicture(bm);	
    		bigPicStyle.setSummaryText(contentText2);    		
    		// Moves the big view style object into the notification object.
    		mBuilder.setStyle(bigPicStyle);
    		break;
    	case NOTI_MODE_INBOX:
	    	NotificationCompat.InboxStyle inboxStyle 
	    		= new NotificationCompat.InboxStyle();
	    	String[] events = new String[4];
	    	events[0] = "Inbox Line 1";
	    	events[1] = "Inbox Line 2";
	    	events[2] = "Inbox Line 3";
	    	events[3] = "Inbox Line 4";
	    	// Moves events into the big view
	    	for (int i=0; i < events.length; i++) {
	    		inboxStyle.addLine(events[i]);
	    	}		
	    	inboxStyle.setSummaryText(contentText2);
	    	// Moves the big view style object into the notification object.
	    	mBuilder.setStyle(inboxStyle);    		
	    	break;
    	case NOTI_MODE_BASIC:
    		break;
    	}
    	
    	// Creates an explicit intent for an Activity in your app
    	Intent resultIntent = new Intent(this, MainActivity.class);

    	// The stack builder object will contain an artificial back stack
    	// for the started Activity
    	// This ensures that navigating backward from the Activity leads out of
    	// your application to the Home screen.
    	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
    	// Adds the back stack for the Intent (but not the Intent itself)
    	stackBuilder.addParentStack(MainActivity.class);
    	// Adds the Intent that starts the Activity to the top of the stack
    	stackBuilder.addNextIntent(resultIntent);
    	// A PendingIntent is used to specify the action which should be performed 
    	// once the user select the notification.
    	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
    	mBuilder.setContentIntent(resultPendingIntent);
    	// Just add a fake action here, max 3
/*    	mBuilder.addAction(R.drawable.ic_action_search, "Search", resultPendingIntent);
    	mBuilder.addAction(R.drawable.ic_action_search, "Add", resultPendingIntent);
    	mBuilder.addAction(R.drawable.ic_action_search, "Save", resultPendingIntent);*/
    	// Hide the notification after its selected
    	mBuilder.setAutoCancel(true);
    	
    	NotificationManager mNotificationManager =
    	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	// mId allows you to update the notification later on.
    	mNotificationManager.notify(mId, mBuilder.build());
    }

}
