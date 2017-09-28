package com.qiaoxg.java.listener;

public interface DownloadListener {
	
	void startDownload(String msg);

	void onSuccess(String msg);

	void downloading(int progress);

	void downloadFail(String msg);

}
