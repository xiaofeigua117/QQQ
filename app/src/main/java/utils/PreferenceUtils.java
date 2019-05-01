package utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
	public static SharedPreferences mSp;

	private static void getSharedPreference(Context context) {
		if (mSp == null) {
			mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		}
	}

	public static void putBoolean(Context context, String key, boolean value) {
		getSharedPreference(context);
		mSp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		getSharedPreference(context);
		return mSp.getBoolean(key, defValue);
	}

	public static void putString(Context context, String key, String value) {
		getSharedPreference(context);
		mSp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String key, String defValue) {
		getSharedPreference(context);
		return mSp.getString(key, defValue);
	}

	public static void putInt(Context context, String key, int value) {
		getSharedPreference(context);
		mSp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context context, String key, int defValue) {
		getSharedPreference(context);
		return mSp.getInt(key, defValue);
	}

	/**
	 *  移除一个key
	 * @param applicationContext
	 * @param sjfdSimNum
	 */
	public static void remove(Context context, String key) {
		getSharedPreference(context);
		mSp.edit().remove(key).commit();
	}
}
