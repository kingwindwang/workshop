package com.time.workshop.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户资料\数据工具类
 * <p>
 * 利用ShardPreferences存取用户的资料
 * </p>
 * 
 * @author hjt
 */
public class PreferenceUtil {

	/**
	 * 由数据的key得到单个用户字符串数据
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @return 一个字符串数据
	 */
	public static String getString(Context context, String prefName, String key) {
		return key == null ? null : getPreference(context, prefName).getString(
				key, null);

	}

	/**
	 * 将用户字符串数据写入到sharePreferences中
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @param value
	 *            数据的值
	 */
	public static void putString(Context context, String prefName, String key,
			String value) {
		if (key != null) {
			getPreference(context, prefName).edit().putString(key, value)
					.commit();
		}
	}

	/**
	 * 由数据的key得到单个用户整型数据
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @return 一个字符串数据
	 */
	public static int getInt(Context context, String prefName, String key) {
		return key == null ? 0 : getPreference(context, prefName)
				.getInt(key, 0);
	}

	/**
	 * 将用户整型数据写入到sharePreferences中
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @param value
	 *            数据的值
	 */
	public static void putInt(Context context, String prefName, String key,
			int value) {
		if (key != null) {
			getPreference(context, prefName).edit().putInt(key, value).commit();
		}
	}

	/**
	 * 由数据的key得到单个用户浮点型数据
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @return 一个浮点型数据
	 */
	public static float getFloat(Context context, String prefName, String key) {
		return key == null ? 0 : getPreference(context, prefName).getFloat(key,
				0F);
	}

	/**
	 * 将用户浮点型数据写入到sharePreferences中
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @param value
	 *            数据的值
	 */
	public static void putFloat(Context context, String prefName, String key,
			float value) {
		if (key != null) {
			getPreference(context, prefName).edit().putFloat(key, value)
					.commit();
		}
	}

	/**
	 * 由数据的key得到单个用户布尔型数据
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @return 一个布尔型数据
	 */
	public static boolean getBoolean(Context context, String prefName,
			String key) {
		return key == null ? false : getPreference(context, prefName)
				.getBoolean(key, false);
	}

	/**
	 * 将用户布尔型数据写入到sharePreferences中
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 * @param value
	 *            数据的值
	 */
	public static void putBoolean(Context context, String prefName, String key,
			boolean value) {
		if (key != null) {
			getPreference(context, prefName).edit().putBoolean(key, value)
					.commit();
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 * @param key
	 *            数据的键
	 */
	public static void remove(Context context, String prefName, String key) {
		if (key != null) {
			getPreference(context, prefName).edit().remove(key).commit();
		}
	}

	/**
	 * 清除文件中的所有内容
	 * 
	 * @param context
	 *            上下文对象,用以取得shardPreferences
	 * @param prefName
	 *            shardPreferences文件名
	 */
	// public static void clearAll(Context context, String prefName){
	// getPreference(context, prefName).edit().clear().commit();
	// }

	/**
	 * 通过上下文对象得到sharePreferences对象
	 * 
	 * @param context
	 *            上下文对象
	 * @param prefName
	 *            sharePreferences文件名
	 * @return sharePreferences对象
	 */
	private static SharedPreferences getPreference(Context context,
			String prefName) {
		return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
	}
}
