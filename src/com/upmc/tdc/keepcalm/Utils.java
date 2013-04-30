package com.upmc.tdc.keepcalm;

import android.content.Context;
import android.widget.Toast;

public class Utils {

	public static void toastLong( Context context, String message ) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
	
	public static void toastShort( Context context, String message ) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
