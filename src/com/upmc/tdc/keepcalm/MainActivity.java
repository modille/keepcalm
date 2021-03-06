package com.upmc.tdc.keepcalm;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.upmc.tdc.keepcalm.adapter.TabsPagerAdapter;
import com.upmc.tdc.keepcalm.fragment.HelpFragment;
import com.upmc.tdc.keepcalm.fragment.ReportFragment;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, ReportFragment.OnFragmentInteractionListener, HelpFragment.OnFragmentInteractionListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	TabsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	int notifications = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new TabsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle( getApplicationContext(), i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle settings item selection
		switch (item.getItemId()) {
			case R.id.menu_item_import_data:
				Utils.toastShort( getApplicationContext(), "TODO: import data from seed file" );
				return true;
			case R.id.menu_item_set_location:
				Utils.toastShort( getApplicationContext(), "TODO: prompt for lat/long and set location" );
				return true;
			case R.id.menu_item_settings:
				Utils.toastShort( getApplicationContext(), "TODO: add more settings here" );
				return true;
			case R.id.menu_item_notification:
				onNotifiction("Outbreak Detected",
						      "P3N5 outbreak was reported within 5 miles of your area.",
						      "P3N5 outbreak.  See new notification");
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	public void onNotifiction( String title, String text, String short_text ) {
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(ns);
        int icon = R.drawable.ic_launcher;           

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(alarmSound == null){
        	alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if(alarmSound == null){
            	alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }           

        Intent intent = new Intent();
        PendingIntent pendingIntent 
        	= PendingIntent.getActivity(this, 0, intent, 0);    

        NotificationCompat.BigTextStyle bigxtstyle =
        		new NotificationCompat.BigTextStyle();          
        bigxtstyle.bigText(text);               
        bigxtstyle.setBigContentTitle(title);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
        	.setContentTitle(title)
        	.setContentText(short_text)
            .setStyle(bigxtstyle)
            .setSmallIcon(icon)
            .setAutoCancel(true)
            .setSound(alarmSound)
            .setDeleteIntent(pendingIntent)                     
            .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0));     

        Notification noti = mBuilder.build();

        mNotificationManager.notify(notifications++, noti);
	}
	
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onFragmentInteraction(Uri uri) {
		// TODO Auto-generated method stub
		
	}

}
