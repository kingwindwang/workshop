package com.time.workshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.time.workshop.BaseFragment;
import com.time.workshop.R;
import com.time.workshop.view.TabHeadView;

public class HeadFragment extends BaseFragment{
	
	public TabHeadView headView_head;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_head, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		headView_head = (TabHeadView) view.findViewById(R.id.headView_head);
		headView_head.getRightButton().setBackgroundResource(R.drawable.btn_title_clock_selector);
		headView_head.getTitle().setText("首页");
		headView_head.getLeftButton().setVisibility(View.GONE);
	}

	
}
