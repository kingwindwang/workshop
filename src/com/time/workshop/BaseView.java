package com.time.workshop;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseView {
	
	// private TextView tv_network_status;
	// private LinearLayout layout_network_status;
	private LinearLayout rl_empty;
	// private ImageView img_empty;
	private TextView tv_empty_joke;
	private TextView tv_noorder;
	private Context context;
	private String[] jokes;

	public LinearLayout getRelative_title() {
		return relative_title;
	}

	public void setRelative_title(LinearLayout relative_title) {
		this.relative_title = relative_title;
	}

	private LinearLayout relative_title;

	// public TextView getTv_network_status() {
	// return tv_network_status;
	// }
	//
	// public void setTv_network_status(TextView tv_network_status) {
	// this.tv_network_status = tv_network_status;
	// }
	// public void networkStatusShow() {
	// layout_network_status.setVisibility(View.VISIBLE);
	// }
	//
	// public void networkStatusHide() {
	// layout_network_status.setVisibility(View.GONE);
	// }
	public void setEmptyViewClickListener(final View.OnClickListener clickListener) {
		tv_noorder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				tv_empty_joke.setText(jokes[(int) (Math.random() * jokes.length)]);
				if (clickListener != null)
					clickListener.onClick(arg0);
			}
		});
	}
	
	public void showEmptyView() {
		if (jokes == null || jokes.length == 0) {
			if (BaseConstant.SHOWJOKE) {
				tv_empty_joke.setVisibility(View.VISIBLE);
			} else {
				tv_empty_joke.setVisibility(View.GONE);
			}
			Resources res = context.getResources();
			jokes = res.getStringArray(R.array.joke);
			tv_empty_joke.setText(jokes[(int) (Math.random() * jokes.length)]);
		}
		rl_empty.setVisibility(View.VISIBLE);
	}

	public void HideEmptyView() {
		rl_empty.setVisibility(View.GONE);
	}

	public BaseView() {

	}

	private TitleView titleView;

	public TitleView getTitleView() {
		return titleView;
	}

	/**
	 * 生成含有导航栏的view
	 * @param context
	 * @param leftLayoutID
	 * @param centerLayoutID
	 * @param rightLayoutID
	 * @param contentLayoutID
	 */
	public void setTitleView(Context context, int leftLayoutID, //
			int centerLayoutID, int rightLayoutID, int contentLayoutID) {
		this.context = context;
		titleView = new TitleView(context, leftLayoutID, centerLayoutID,
				rightLayoutID, contentLayoutID);
	}

	/**
	 * 生成不含导航栏的view
	 * @param context
	 * @param titleUIListener
	 * @param contentLayoutID
	 * @param noTitle
	 */
	public void setTitleView(Context context, int contentLayoutID,
			boolean noTitle) {
		this.context = context;
		titleView = new TitleView(context, contentLayoutID, noTitle);
	}

	private class TitleView extends RelativeLayout {
		
		private Context context;
		private int leftLayoutID;// 导航栏布局ID 左部分
		private int centerLayoutID;// 导航栏布局ID 中部分
		private int rightLayoutID;// 导航栏布局ID 右部分
		private int contentLayoutID;// 正文布局ID
		private boolean noTitle = false;// 没有导航栏

		/**
		 * 含有导航栏的界面
		 * @param context
		 * @param leftLayoutID
		 * @param centerLayoutID
		 * @param rightLayoutID
		 * @param contentLayoutID
		 */
		public TitleView(Context context, int leftLayoutID, //
				int centerLayoutID, int rightLayoutID, int contentLayoutID) {
			super(context);
			this.context = context;
			this.leftLayoutID = leftLayoutID;
			this.centerLayoutID = centerLayoutID;
			this.rightLayoutID = rightLayoutID;
			this.contentLayoutID = contentLayoutID;
			init();
		}

		/**
		 * 不含有导航栏的界面
		 * @param context
		 * @param contentLayoutID
		 * @param noTitle
		 */
		public TitleView(Context context, int contentLayoutID, boolean noTitle) {
			super(context);
			this.context = context;
			this.noTitle = noTitle;
			this.contentLayoutID = contentLayoutID;
			this.context = context;
			init();
		}

		private void init() {
			View mainView = LayoutInflater.from(context).inflate(
					R.layout.base_view, this, true);
			relative_title = (LinearLayout) mainView
					.findViewById(R.id.relative_title);

			if (noTitle) {// 如果没有导航栏，隐藏布局
				relative_title.setVisibility(View.GONE);
			}

			/* 导航栏部分 start */
			if (leftLayoutID != 0) {// 导航栏 左部分
				RelativeLayout relative_title_leftUI = (RelativeLayout) mainView
						.findViewById(R.id.relative_title_leftUI);
				LayoutInflater.from(context).inflate(leftLayoutID,
						relative_title_leftUI);
			}

			if (centerLayoutID != 0) {// 导航栏 中部分
				RelativeLayout relative_title_centerUI = (RelativeLayout) mainView
						.findViewById(R.id.relative_title_centerUI);
				LayoutInflater.from(context).inflate(centerLayoutID,
						relative_title_centerUI);
			}

			if (rightLayoutID != 0) {// 导航栏 右部分
				RelativeLayout relative_title_rightUI = (RelativeLayout) mainView
						.findViewById(R.id.relative_title_rightUI);
				LayoutInflater.from(context).inflate(rightLayoutID,
						relative_title_rightUI);
			}
			/* 导航栏部分 end */

			/* 正文部分 start */
			if (contentLayoutID != 0) {
				LinearLayout linear_contentView = (LinearLayout) mainView
						.findViewById(R.id.linear_contentView);
				LayoutInflater.from(context).inflate(contentLayoutID,
						linear_contentView);
			}

			// layout_network_status = (LinearLayout) mainView
			// .findViewById(R.id.rl_empty);
			// tv_network_status = (TextView) mainView
			// .findViewById(R.id.tv_network_status);
			rl_empty = (LinearLayout) mainView.findViewById(R.id.rl_empty);
			tv_empty_joke = (TextView) mainView.findViewById(R.id.tv_joke);
			tv_noorder = (TextView) mainView.findViewById(R.id.tv_noorder);
			// img_empty = (ImageView) mainView.findViewById(R.id.empty_img);
			rl_empty.setVisibility(View.GONE);
		}
	}
}
