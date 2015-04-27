package com.time.workshop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.time.workshop.util.CommonUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Fragment基类，实现了触摸事件拦截
 */
@SuppressLint("NewApi")
public class BaseFragment extends Fragment implements OnTouchListener {

	public static String TAG = null;
	public SuperActivity mSuperActivity;
	private Dialog mDialog;

	public BaseFragment() {
		TAG = ((Object) this).getClass().getName();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mSuperActivity = (SuperActivity) getActivity();
	}

	protected void back() {
		getFragmentManager().popBackStack();
	}

	//	protected void onRefreshComplete(PullToRefreshAdapterViewBase refreshView) {
	//		// refreshView.onRefreshComplete();
	//		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(getActivity().getString(R.string.Class308)
	//		// + sf.format(new Date()));
	//		try {
	//			refreshView.onRefreshComplete();
	//			SimpleDateFormat sf = null;
	//			if (App.getInstance().isCN()) {
	//				 sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//			} else {
	//				sf = new SimpleDateFormat("dd MMMM HH:mm:ss",  
	//		                Locale.ENGLISH);
	//			}
	//			//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//			refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
	//					getString(R.string.Class308) + sf.format(new Date()));
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	public void addFragment(FragmentManager fm,
			Class<? extends Fragment> fragmentClass, String tag, Bundle args) {
		mSuperActivity.addFragment(fm, fragmentClass, tag, args);
	}

	public void showDialog() {
		if (getActivity() == null || getActivity().isFinishing())
			return;
		if (mDialog == null)
			mDialog = CommonUtils.createLoadingDialog(mSuperActivity);
		if (!mDialog.isShowing())
			mDialog.show();
	}

	public void dismissDialog() {
		if (getActivity() == null || getActivity().isFinishing())
			return;
		if (mDialog != null && mDialog.isShowing()) {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mDialog.dismiss();
				}
			}, 500);
		}
	}

	private Handler mHandler = new Handler();

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mDialog != null)
			mDialog.dismiss();
	}

	public void addFragment(FragmentManager fm, Fragment fragment, String tag,
			Bundle args) {
		mSuperActivity.addFragment(fm, fragment, tag, args);
	}

	public void addFragment(FragmentManager fm,
			Class<? extends Fragment> fragmentClass, String tag, Bundle args,
			int[] anims) {
		mSuperActivity.addFragment(fm, fragmentClass, tag, args, anims);
	}

	public void addFragment(FragmentManager fm, Fragment fragment, String tag,
			Bundle args, int[] anims) {
		mSuperActivity.addFragment(fm, fragment, tag, args, anims);
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(this.getClass().getName()); // 统计页面
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(this.getClass().getName());
	}
}
