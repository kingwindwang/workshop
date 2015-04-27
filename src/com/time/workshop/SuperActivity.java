package com.time.workshop;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.time.workshop.util.CommonUtils;
import com.umeng.analytics.MobclickAgent;

public class SuperActivity extends FragmentActivity implements
		android.view.View.OnClickListener, GestureDetector.OnGestureListener {
	
	// public static final String TAG = "SuperActivity";
	// public FragmentManager fragmentManager;
	public FragmentActivity context;
	public BaseView baseView;
	private Dialog mDialog;
	// public Resources resources;
	// public static String sessionId = "";
	// public static boolean isInside;// true内，1外
	// // public BeemApplication app;
	// public FinalDb fdb;
	// // private NetWorkMonitor netWorkMonitor;
	// private TextView networkStatus;
	// public int curPage = 1;
	// public int cLastIndex;
	// public void setCurPage() {
	// curPage++;
	// }
	private int flingLength, flingHeight;

	public GestureDetector getDetector() {
		return detector;
	}

	private GestureDetector detector;
	private boolean gesture;
	private boolean startAnim = true;

	public boolean isStartAnim() {
		return startAnim;
	}

	public void setStartAnim(boolean startAnim) {
		this.startAnim = startAnim;
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		baseView = new BaseView();
		context = this;
		detector = new GestureDetector(context, this);
		flingLength = App.getInstance().getSizeUtil().getScreenWidth() / 3;
		flingHeight = App.getInstance().getSizeUtil().dip2px(60);
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
	}

	protected void back() {
		back(true);
	}
		
	protected void back(boolean anim) {
		finish();
		if (android.os.Build.VERSION.SDK_INT > 10) {
			if (anim)
				overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
			else {
				
			}
		}
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(this.getClass().getName()); // 统计页面
		MobclickAgent.onResume(this);
//		EMChatManager.getInstance().activityResumed();// TODO
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(this.getClass().getName()); // 统计页面
		MobclickAgent.onPause(this);
	}

	// Fragment跳转
	public void addFragment(FragmentManager fm,
	                        Class<? extends Fragment> fragmentClass, String tag, Bundle args) {
		addFragment(fm, fragmentClass, tag, args, null);
	}

	public void addFragment(FragmentManager fm, Fragment fragment, String tag,
	                        Bundle args) {
		// addFragment(fm, fragment, tag, args, new int[] {
		// android.R.anim.fade_in, android.R.anim.fade_out,
		// android.R.anim.fade_in, android.R.anim.fade_out });
		addFragment(fm, fragment, tag, args, null);
	}

	public void addFragment(FragmentManager fm,
	                        Class<? extends Fragment> fragmentClass, String tag, Bundle args,
	                        int[] anims) {
		baseFragmentOperate(fm, fragmentClass, R.id.basecontent, tag, args,
				anims, true, true);
	}

	public void addFragment(FragmentManager fm, Fragment fragment, String tag,
	                        Bundle args, int[] anims) {
		baseFragmentOperate(fm, fragment, R.id.basecontent, tag, args, anims,
				true, true);
	}
	
	/**
	 * @param fm
	 * @param fragmentClass
	 * @param fragmentContainerId
	 * @param tag
	 * @param args
	 * @param anims               切换过渡动画数组，如果数组长度为 2 那么就是 enterAnim, exitAnim, 如果为 4 就是
	 *                            enterAnim, exitAnim, popEnterAnim, popExitAnim
	 * @param isAdd               是否添加 该 Fragment, true 添加（add）， false 替换（replace）
	 */
	public void baseFragmentOperate(FragmentManager fm,
	                                Class<? extends Fragment> fragmentClass, int fragmentContainerId,
	                                String tag, Bundle args, int[] anims, boolean isAdd,
	                                boolean isAddBackStack) {
		Fragment fragment = getFragmentInstance(fm, fragmentClass, tag, args);
		baseFragmentOperate(fm, fragment, fragmentContainerId, tag, args,
				anims, isAdd, isAddBackStack);
	}
	
	/*
	 * fragment的跳转方法的实现
	 */
	public void baseFragmentOperate(FragmentManager fm, Fragment fragment,
	                                int fragmentContainerId, String tag, Bundle args, int[] anims,
	                                boolean isAdd, boolean isAddBackStack) {
		// mIsCanEixt = false;
		if (args != null) {
			Bundle oldArgs = fragment.getArguments();
			if (oldArgs != null) {
				oldArgs.putAll(args);
			} else {
				fragment.setArguments(args);
			}
		}
		FragmentTransaction ft = fm.beginTransaction();
		if (fragment.isAdded()) {
			ft.remove(fragment);
		}
		setCustomAnim(ft, anims);
		if (isAdd) {
			ft.add(fragmentContainerId, fragment, tag);
		} else {
			ft.replace(fragmentContainerId, fragment, tag);
		}
		if (isAddBackStack) {
			ft.addToBackStack(tag);
		}
		ft.commitAllowingStateLoss();
	}
	
	public void replaceFragment(FragmentManager fm,
	                            Class<? extends Fragment> fragmentClass, String tag, Bundle args) {
		replaceFragment(fm, fragmentClass, tag, args, null);
	}
	
	public void replaceFragment(FragmentManager fm,
	                            Class<? extends Fragment> fragmentClass, String tag, Bundle args,
	                            int[] anims) {
		baseFragmentOperate(fm, fragmentClass, R.id.basecontent, tag, args,
				anims, false, true);
	}
	
	public Fragment getFragmentInstance(FragmentManager fm,
	                                    Class<? extends Fragment> fragmentClass, String tag, Bundle args) {
		Fragment fragment = fm.findFragmentByTag(tag);
		if (fragment == null) {
			fragment = createNewFragment(fm, fragmentClass);
		}

		if (args != null && !args.isEmpty()) {
			fragment.getArguments().putAll(args);
		}
		return fragment;
	}
	
	public Fragment createNewFragment(FragmentManager fm, Class<? extends Fragment> fragmentClass) {
		Fragment fragment = null;
		try {
			fragment = fragmentClass.newInstance();
			fragment.setArguments(new Bundle());
		} catch (java.lang.InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return fragment;
	}
	
	/**
	 * @param ft
	 * @param anims 切换过渡动画数组，如果数组长度为 2 那么就是 enterAnim, exitAnim, 如果为 4 就是
	 *              enterAnim, exitAnim, popEnterAnim, popExitAnim
	 */
	private void setCustomAnim(FragmentTransaction ft, int[] anims) {
		if (anims == null) {
			return;
		}
		int animCount = anims.length;
		if (animCount == 2) {
			ft.setCustomAnimations(anims[0], anims[1]);
		} else if (animCount == 4) {
			ft.setCustomAnimations(anims[0], anims[1], anims[2], anims[3]);
		}
	}
	
	public void popBackAllStack() {
		getSupportFragmentManager().popBackStack(null,
				FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
	
	@Override
	public void onClick(View view) {
		
	}
	
	public void startActivityForResult(Class<?> clazz, int requestCode, boolean anim) {
		startAnim = anim;
		startActivityForResult(new Intent(context, clazz), requestCode);
		if (anim) {
			// overridePendingTransition(R.anim.push_right_in,
			// R.anim.push_left_out);
		} else {
			// overridePendingTransition(0, 0);
		}
	}

	public void startActivityForResult(Intent intent, int requestCode, boolean anim) {
		startAnim = anim;
		startActivityForResult(intent, requestCode);
		if (anim) {
			// overridePendingTransition(R.anim.push_right_in,
			// R.anim.push_left_out);
		} else {
			// overridePendingTransition(0, 0);
		}
	}

	protected void startActivity(Class<?> clazz, boolean anim) {
		startAnim = anim;
		startActivity(new Intent(context, clazz));
		if (android.os.Build.VERSION.SDK_INT > 10) {
			if (anim) {
				overridePendingTransition(R.anim.push_right_in,
				R.anim.push_left_out);
			} else {
				overridePendingTransition(0, 0);
			}
		}
	}

	public void startActivity(Intent intent, boolean anim) {
		startAnim = anim;
		startActivity(intent);
		if (android.os.Build.VERSION.SDK_INT > 10) {
			if (anim) {
				overridePendingTransition(R.anim.push_right_in,
				R.anim.push_left_out);
			} else {
				overridePendingTransition(0, 0);
			}
		}
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
		if (android.os.Build.VERSION.SDK_INT > 10) {
			if (startAnim)
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_left_out);
		}
	}
	
	@Override
	public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
		super.startActivityFromFragment(fragment, intent, requestCode);
		if (android.os.Build.VERSION.SDK_INT > 10) {
			if (startAnim)
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_left_out);
		}
	}

	@Override
	public void finish() {
		super.finish();
	}

	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}
	
//	protected void onRefreshComplete(PullToRefreshAdapterViewBase refreshView) {
//		refreshView.onRefreshComplete();
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(
//				getString(R.string.Class308) + sf.format(new Date()));
//	}
	
	public void showDialog() {
		if (mDialog == null)
			mDialog = CommonUtils.createLoadingDialog(context);
		if (!mDialog.isShowing() && !this.isFinishing())
			mDialog.show();
	}
	
	public void showDialog(boolean[] close) {
		if (mDialog == null)
			mDialog = CommonUtils.createLoadingDialog(context, close);
		if (!mDialog.isShowing() && !this.isFinishing())
			mDialog.show();
	}

	public void dismissDialog() {
		if (mDialog != null && mDialog.isShowing() && !this.isFinishing()) {
			mDialog.dismiss();
		}
	}
	
	private Handler mHandler = new Handler();
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			back();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean isGesture() {
		return gesture;
	}

	public void setGesture(boolean gesture) {
		this.gesture = gesture;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		try {
			if (e1.getX() - e2.getX() > flingLength) {
				// Intent intent = new Intent();
				// intent.setClass(MainActivity.this, MainActivity.class);
				// startActivity(intent);
				// overridePendingTransition(R.anim.push_left_out,
				// R.anim.push_left_in);
				return true;
			} else if (e2.getX() - e1.getX() > flingLength && Math.abs(e1.getY() - e2.getY()) < flingHeight) {
				back(true);
				// Intent intent = new Intent();
				// intent.setClass(MainActivity.this, MainActivity.class);
				// startActivity(intent);
				// overridePendingTransition(R.anim.push_right_out,
				// R.anim.push_right_in);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (gesture)
			return this.detector.onTouchEvent(event);
		else
			return super.onTouchEvent(event);
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
	                        float distanceY) {
		return false;
	}
	
	@Override
	public void onShowPress(MotionEvent e) {

	}
		
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDialog != null)
			mDialog.dismiss();
//		FinalHttp.getInstance().cancel();
	}
}
