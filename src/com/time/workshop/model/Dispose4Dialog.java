package com.time.workshop.model;

import android.app.Dialog;
import android.view.View;

public abstract class Dispose4Dialog {
	private String value;
	private boolean isRecommend;

	public Dispose4Dialog(String value, boolean isRecommend) {
		super();
		this.value = value;
		this.isRecommend = isRecommend;
	}

	public Dispose4Dialog(String value) {
		super();
		this.value = value;
	}

	public final boolean isRecommend() {
		return isRecommend;
	}

	public void onclick(View arg0, Dialog dialog) {
		dialog.dismiss();
	}

	public final String getValue() {
		return value;
	}

}
