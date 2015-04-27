package com.time.workshop.view;


import com.time.workshop.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lingcheng on 2014/7/23.
 */
public class TabHeadView extends RelativeLayout{
	private ImageView mLogo;

	public RelativeLayout getTitlebg() {
		return titlebg;
	}

	public void setTitlebg(RelativeLayout titlebg) {
		this.titlebg = titlebg;
	}

	private RelativeLayout titlebg;

	public TabHeadView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public TabHeadView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public void setRightInViliable() {
		mRightButton.setVisibility(View.INVISIBLE);
	}

	private ViewGroup mViewContainer;
	private Button mLeftButton;
	private Button mRightButton;
	private TextView titlebar_right_text;

	public TextView getTitle() {
		return mTitle;
	}

	private TextView mTitle;

	public void init() {
		mViewContainer = (ViewGroup) View.inflate(getContext(),
				R.layout.tab_titlebar_layout, this);
		mLeftButton = (Button) mViewContainer
				.findViewById(R.id.titlebar_left_btn);
		mRightButton = (Button) mViewContainer
				.findViewById(R.id.titlebar_right_btn);
		titlebar_right_text = (TextView) mViewContainer.findViewById(R.id.titlebar_right_text);
		mTitle = (TextView) mViewContainer.findViewById(R.id.titlebar_title);
		titlebg = (RelativeLayout) mViewContainer.findViewById(R.id.titlebar_content);
		mLogo = (ImageView) mViewContainer.findViewById(R.id.img_logo);
	}

	public void setLeftOnClick(OnClickListener click) {
		mLeftButton.setOnClickListener(click);
	}

	public void setTitle(String title) {
		mTitle.setText(title);
	}

	public void setTitleLogo(int id) {
		mLogo.setVisibility(VISIBLE);
		mLogo.setImageResource(id);
	}

	public void setRightOnClick(OnClickListener click) {
		mRightButton.setOnClickListener(click);
	}

	public Button getLeftButton() {
		return mLeftButton;
	}

	public Button getRightButton() {
		return mRightButton;
	}

	public TextView getTitlebar_right_text() {
		return titlebar_right_text;
	}
}
