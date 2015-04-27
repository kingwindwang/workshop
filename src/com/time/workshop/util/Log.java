package com.time.workshop.util;

public final class Log {
	public static final boolean IS_SHOW_LOG = true;// false,不显示
	public static final String TAG = "SERVER";

	public static void v(String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				android.util.Log.v(TAG, "-" + msg);
			}

		}
	}

	public static void v(String tag, String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.v(tag, "-" + msg);
			}

		}
	}

	public static void i(String tag, String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.i(tag, "-" + msg);
			}

		}
	}

	public static void e(String tag, String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.e(tag, "-" + msg);
			}

		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.e(tag, "-" + msg);
			}

		}
	}

	public static void w(String tag, String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.w(tag, "-" + msg);
			}

		}
	}

	public static void d(String tag, String msg) {
		if (IS_SHOW_LOG) {
			if (null == msg || "".equals(msg)) {
				return;
			} else {
				if (null == tag || "".equals(tag)) {
					tag = "errorLog";
				}
				android.util.Log.d(tag, "-" + msg);
			}

		}
	}
}
