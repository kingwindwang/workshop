package com.time.workshop.ui;

import java.util.ArrayList;

import com.time.workshop.App;
import com.time.workshop.R;
import com.time.workshop.SuperActivity;
import com.time.workshop.fragment.HeadFragment;
import com.time.workshop.fragment.MyFragment;
import com.time.workshop.fragment.WorkshopFragment;
import com.time.workshop.injector.Injector;
import com.time.workshop.injector.V;
import com.time.workshop.view.MyViewPager;
import com.time.workshop.view.PagerSlidingTabStrip;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends SuperActivity {
	
	@V
	private PagerSlidingTabStrip tabs;
	
	@V
	private MyViewPager pager;

	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private MyPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
    }
    
    private void setView(){
    	Injector.getInstance().inject(context);
    	
    	fragments.add(new HeadFragment());
    	fragments.add(new WorkshopFragment());
    	fragments.add(new MyFragment());
    	
    	tabs.setAllCaps(false);
		tabs.setTabWidth(App.getInstance().getScreenWidth() / 3);
		tabs.setDividerColorResource(android.R.color.black);
		tabs.setIndicatorColor(Color.TRANSPARENT);
		tabs.setTabPaddingLeftRight(0);
		tabs.setDividerPadding(1);
		pager.setOffscreenPageLimit(3);
		pager.setScanScroll(true);
		adapter = new MyPagerAdapter(getSupportFragmentManager(), tabs);
		pager.setAdapter(adapter);
		pager.setCurrentItem(0);
		
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
    }
    
    
    public class MyPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

		private final String[] TITLES;
		private final TabView[] VIEWS;
		private final int[] imgIds = new int[]{R.drawable.app_index_home,
				R.drawable.icon_index_clock,  R.drawable.icon_index_my};
		private final int[] imgPressedIds = new int[]{R.drawable.app_index_home_selected,
				R.drawable.icon_index_clock_selected,
				R.drawable.icon_index_my_selected};
		private final PagerSlidingTabStrip tab;

		public MyPagerAdapter(FragmentManager fm, PagerSlidingTabStrip tab) {
			super(fm);
			this.tab = tab;
			TITLES = getResources().getStringArray(R.array.bottom_navigation);
			VIEWS = new TabView[TITLES.length];
			tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int i, float v, int i2) {

				}

				@Override
				public void onPageSelected(int i) {
					setSelectView(i);
//					if (i == 0) {
//						try {
//							((HomeFragmentNew) fragments.get(0)).doHttpRequest();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//					if (i == 4) {
//						try {
//							((MineFragment) fragments.get(4)).checkIsSign();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
				}

				@Override
				public void onPageScrollStateChanged(int i) {

				}
			});
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public View getPageView(int i) {
			if (VIEWS[i] == null)
				VIEWS[i] = getTabItemView(i);
			return VIEWS[i];
		}

		private void setSelectView(int index) {
			for (int i = 0; i < VIEWS.length; i++) {
				if (i == index)
					VIEWS[i].setSelect(imgPressedIds[i]);
				else
					VIEWS[i].setUnSelect(imgIds[i]);
			}
		}

		/**
		 * 给Tab按钮设置图标和文字
		 */
		private TabView getTabItemView(int index) {
			return new TabView(context, index);
		}

		private class TabView extends LinearLayout {

			private final ImageView imageView;
			private final TextView textView;
			private final View layout;

			public TabView(Context context, int index) {
				super(context);
				LayoutInflater layoutInflater = LayoutInflater.from(context);
				layout = layoutInflater.inflate(R.layout.layout_sliding_tab_item, null);
				imageView = (ImageView) layout.findViewById(R.id.imageView);
				textView = (TextView) layout.findViewById(R.id.txt_cn);
				if (index == 0) {
					setSelect(imgPressedIds[index]);
				} else {
					setUnSelect(imgIds[index]);
				}

				TextView textView = (TextView) layout.findViewById(R.id.txt_cn);
				textView.setText(TITLES[index]);
				textView.setTextSize(getResources().getDimension(R.dimen.system_textSize) / getResources().getDisplayMetrics().density);
				addView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			}

			private void setSelect(int id) {
				imageView.setImageResource(id);
				textView.setTextColor(getResources().getColor(R.color.base_title));
//				layout.setBackgroundColor(getResources().getColor(R.color.base_title_alpth));
			}

			public void setUnSelect(int imgId) {
				imageView.setImageResource(imgId);
				textView.setTextColor(getResources().getColor(R.color.title_gray));
//				layout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
			}
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
