package com.upmc.tdc.keepcalm.adapter;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.android.gms.maps.SupportMapFragment;
import com.upmc.tdc.keepcalm.R;
import com.upmc.tdc.keepcalm.fragment.HelpFragment;
import com.upmc.tdc.keepcalm.fragment.ReportFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

	private static final int TAB_COUNT = 3;

	public TabsPagerAdapter( FragmentManager fm ) {
		super(fm);
	}
	
	@Override
	public int getCount() {
		return TAB_COUNT;
	}

	@Override
	public Fragment getItem( int position )
	{
		Fragment pageFragment;
		switch ( position ) {
			case 0:
				pageFragment = new ReportFragment();
				break;
			case 1:
				pageFragment = SupportMapFragment.newInstance();
				break;
			case 2:
				pageFragment = new HelpFragment();
				break;
			default:
				pageFragment = null;
				break;
		}

		return pageFragment;
	}
	
	public CharSequence getPageTitle( Context context, int position)
	{
		Locale l = Locale.getDefault();
		switch (position) {
			case 0:
				return context.getString(R.string.tab_title_report).toUpperCase(l);
			case 1:
				return context.getString(R.string.tab_title_map).toUpperCase(l);
			case 2:
				return context.getString(R.string.tab_title_help).toUpperCase(l);
		}
		return null;
	}
}
