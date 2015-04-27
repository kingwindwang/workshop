package com.time.workshop;

import java.io.Serializable;

public class NewBaseData implements Serializable {
    private static final long serialVersionUID = 17917916848031777L;
    /**
	 * 
	 */

	// 是否成功
	protected int requestid;

	protected String errmsg = "";

	public int getRequestid() {
		return requestid;
	}

	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
